package com.tigerjoys.shark.miai.service.impl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AnchorOnlineStateEnum;
import com.tigerjoys.shark.miai.agent.enums.UserVideoChatRecordChannelTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.dto.dispatch.DoubleVChatVideoDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.enums.VChatVideoTCPVideoTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IBSequenceContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoService;

@Service
public class VChatVideoServiceImpl implements IVChatVideoService {

	private static final int ROOM_CREATE_EXPIRE = 180;
	private static final String ROOM_ID = "roomId";
	private static final String PAY_FLAG = "payFlag";
	private static final String ORDER_ID = "orderId";
	private static final String ENTER_ROOM = "enterRoom";
	private static final String ANCHOR = "anchor";
	private static final String PAYUSER = "payUser";
	private static final String PAYPRICE = "payPrice";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private INeteaseAgent neteaseAgent;

	@Autowired
	private IBSequenceContract bSequenceContract;

	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;

	@Autowired
	private IVchatRoomContract vchatRoomContract;

	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;

	@Autowired
	private IUserDiamondAgent userDiamondAgent;

	public ActionResult dialing(long userId, Long toUserId) throws Exception {
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", toUserId);
		UserBO toUser = userAgent.findById(toUserId);
		long anchorId = toUserId;
		long audienceId = userId;
		if (Tools.isNull(toUser) || Tools.isNull(anchor)) {
			//return ActionResult.fail(ErrorCodeEnum.user_isnull);
			anchor = anchorOnlineContract.findByProperty("userid", userId);
			if(Tools.isNotNull(anchor)){
				anchorId = userId;
				audienceId = toUserId;
			}else {
				return ActionResult.fail(ErrorCodeEnum.user_isnull);
			}
		}
		
		long balance = userDiamondAgent.getDiamondBalance(audienceId);
		if (balance < anchor.getPrice() * 3) {
			return ActionResult.fail(ErrorCodeEnum.hundredResponses_diamond_not_enough);
		}

		boolean anchorRoomFalg = cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + toUserId);
		if (anchorRoomFalg) {
			return ActionResult.fail(ErrorCodeEnum.anchor_calling_video_chat);
		}

		boolean anchorOnline = userOnlineListAgent.existsOnline(toUserId);
		if (!anchorOnline) {
			return ActionResult.fail(ErrorCodeEnum.anchor_unOnline);
		}
	
		long roomId = bSequenceContract.getGeneraterId("roomId");
		long orderId = IdGenerater.generateId();
		// long orderId = 12345L;

		DoubleVChatVideoDto toUserVChat = getRoomInfo(toUser, anchor, audienceId, roomId, orderId, balance);
		UserBO user = userAgent.findById(userId);

		DoubleVChatVideoDto userVChat = getRoomInfo(user, anchor, audienceId, roomId, orderId, balance);

		setRoomRedis(audienceId, anchorId, roomId, orderId, anchor.getPrice());

		VchatRoomEntity roomEntity = new VchatRoomEntity();
		roomEntity.setOrderId(orderId);
		roomEntity.setAnchorid(0L);
		roomEntity.setUserid(0L);
		roomEntity.setPrice(anchor.getPrice());
		roomEntity.setTotal_price(0);
		roomEntity.setCreate_time(new Date());
		vchatRoomContract.insert(roomEntity);

		VChatVideoTCPDto dto = new VChatVideoTCPDto();
		dto.setType(VChatVideoTCPTypeEnum.video.getCode());
		dto.setSubType(VChatVideoTCPVideoTypeEnum.answer.getCode());
		dto.setData(userVChat);
		neteaseAgent.pushOneAttachMessage(userId, toUserId, JsonHelper.toJson(dto),user.getNickname()+"邀请你视频聊天");
		return ActionResult.success(toUserVChat);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult communicateRes(long userId, Long orderId, Long toUserId, int status) throws Exception {
		if (VChatVideoStatusEnum.answer.getCode() == status) {
			VChatVideoTCPDto dto = new VChatVideoTCPDto();
			dto.setType(VChatVideoTCPTypeEnum.video.getCode());
			dto.setSubType(VChatVideoStatusEnum.enter_room.getCode());
			neteaseAgent.pushOneAttachMessage(userId, toUserId, JsonHelper.toJson(dto));
			return ActionResult.success();
		} else if (VChatVideoStatusEnum.enter_room.getCode() == status) {
			return enterRoomEvent(userId, orderId, toUserId);
		} else if (VChatVideoStatusEnum.exit.getCode() == status) {
			return exitRoomEvent(userId, orderId, toUserId);
		}
		return ActionResult.success();
	}

	@Override
	public ActionResult isEnterRoom(Long orderId) throws Exception {
		boolean isEnterRoom = cacheRedis.exists(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId);
		return ActionResult.success(isEnterRoom);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult payOrder(long userId, Long orderId) throws Exception {
		boolean isEnterRoom = cacheRedis.exists(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId);
		boolean isUserid = cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId);
		VchatRoomEntity vchatRoom = vchatRoomContract.findByProperty("orderId", orderId);

		cacheRedis.zadd(AgentRedisCacheConst.VCHAT_USER_VIDEO_ONLINE, System.currentTimeMillis(), "" + userId);
		if (isUserid) {
			cacheRedis.expire(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ROOM_CREATE_EXPIRE);
		}
		if (isEnterRoom) {
			cacheRedis.expire(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId, ROOM_CREATE_EXPIRE);
		}

		int rows = vchatRoomContract.pay(orderId);
		DiamondResultDto<Map<String, Long>> result = null;
		// HashMap<String, Object> outHsmp = new HashMap<>();
		String text = "";
		String balanceStr = "";
		if (rows > 0) {
			if (vchatRoom.getAnchorid() > 0 && vchatRoom.getUserid() > 0) {
				result = userDiamondAgent.mediaChatInMinute(2, vchatRoom.getUserid(), vchatRoom.getAnchorid(),
						orderId, vchatRoom.getPrice(), UserVideoChatRecordChannelTypeEnum.video_tencent);
				Long balance = result.getData().get("balance");
				balanceStr = balance + "钻";
				if (Tools.isNotNull(balance)) {
					if (balance > vchatRoom.getPrice() * 3 && balance < vchatRoom.getPrice() * 4) {
						if (userId == vchatRoom.getUserid()) {
							text = "还剩3分钟可聊哦";
						}
					}
					if (balance > vchatRoom.getPrice() && balance < vchatRoom.getPrice() * 2) {
						if (userId == vchatRoom.getUserid()) {
							text = "您的余额不足，充值继续聊天~";
						}
					}
					if (balance < vchatRoom.getPrice()) {
						VChatVideoTCPDto dto = new VChatVideoTCPDto();
						dto.setType(VChatVideoTCPTypeEnum.video.getCode());
						dto.setSubType(VChatVideoStatusEnum.exit.getCode());
						dto.setData(userId == vchatRoom.getUserid() ? "余额不足" : "");
						neteaseAgent.pushOneAttachMessage(
								userId == vchatRoom.getUserid() ? vchatRoom.getAnchorid() : vchatRoom.getUserid(),
								userId, JsonHelper.toJson(dto));
					}
				}
			} else {
				logger.info("payOrder_ERROR:userId=" + userId + ",orderId=" + orderId);
				return ActionResult.fail(ErrorCodeEnum.error);
			}
		}

		if (Tools.isNotNull(vchatRoom) && vchatRoom.getUserid()>0 && vchatRoom.getAnchorid()>0 ) {
			boolean isUseridOnline = cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + vchatRoom.getUserid());
			boolean isAnchorOnline = cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + vchatRoom.getAnchorid());
			if (!(isUseridOnline && isAnchorOnline)) {
				VChatVideoTCPDto dto = new VChatVideoTCPDto();
				dto.setType(VChatVideoTCPTypeEnum.video.getCode());
				dto.setSubType(VChatVideoStatusEnum.exit.getCode());
				dto.setData(userId == vchatRoom.getUserid() ? "对方已掉线" : "");
				neteaseAgent.pushOneAttachMessage(
						userId == vchatRoom.getUserid() ? vchatRoom.getAnchorid() : vchatRoom.getUserid(), userId,
						JsonHelper.toJson(dto));
			}
		}

		if (Tools.isNotNull(balanceStr)) {
			VChatVideoTCPDto dto = new VChatVideoTCPDto();
			dto.setType(VChatVideoTCPTypeEnum.video.getCode());
			dto.setSubType(VChatVideoStatusEnum.user_balance.getCode());
			dto.setData(balanceStr);
			neteaseAgent.pushOneAttachMessage(vchatRoom.getAnchorid(), vchatRoom.getAnchorid(), JsonHelper.toJson(dto));
		}
		return ActionResult.success(text);
	}

	@Transactional(rollbackFor = Exception.class)
	public ActionResult enterRoomEvent(long userId, Long orderId, Long toUserId) throws Exception {
		enterRoom(userId, orderId, toUserId);
		anchorOnlineContract.updateState(userId, AnchorOnlineStateEnum.talking.getCode(),true);
		String payFlag = cacheRedis.hget(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, PAY_FLAG);
		if (Tools.isNull(payFlag)) {
			logger.info("enterRoomEvent_ERROR:userId=" + userId + ",orderId=" + orderId + ",toUserId=" + toUserId);
			return ActionResult.fail(ErrorCodeEnum.error);
		}
		int payEvent = 0;
		if ("1".equals(payFlag)) {
			payEvent = vchatRoomContract.enterRoomUserId(orderId, userId);
		} else {
			payEvent = vchatRoomContract.enterRoomAnchor(orderId, userId);
		}
		if (payEvent > 0) {
			VchatRoomEntity vchatRoom = vchatRoomContract.findByProperty("orderId", orderId);
			if (vchatRoom.getAnchorid() > 0 && vchatRoom.getUserid() > 0) {
				DiamondResultDto<Map<String, Long>> result = userDiamondAgent.mediaChatInMinute(2,
						vchatRoom.getUserid(), vchatRoom.getAnchorid(), orderId, vchatRoom.getPrice(),
						UserVideoChatRecordChannelTypeEnum.video_tencent);
				if (result.getCode() != 0) {
					VChatVideoTCPDto dto = new VChatVideoTCPDto();
					dto.setType(VChatVideoTCPTypeEnum.video.getCode());
					dto.setSubType(VChatVideoStatusEnum.exit.getCode());
					dto.setData(userId == vchatRoom.getUserid() ? "余额不足,请充值" : "对方余额不足");
					neteaseAgent.pushOneAttachMessage(
							userId == vchatRoom.getUserid() ? vchatRoom.getAnchorid() : vchatRoom.getUserid(), userId,
							JsonHelper.toJson(dto));
				}
				
			} else {
				logger.info("enterRoomEvent_ERROR:userId=" + userId + ",orderId=" + orderId + ",toUserId=" + toUserId);
				return ActionResult.fail(ErrorCodeEnum.error);
			}
		}
		return ActionResult.success();
	}

	public ActionResult exitRoomEvent(long userId, Long orderId, Long toUserId) throws Exception {
		String cacheOrderId = cacheRedis.hget(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ORDER_ID);
		anchorOnlineContract.updateState(userId, AnchorOnlineStateEnum.online.getCode(),true);
		boolean isUserid = cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + toUserId);
		if (isUserid) {
			VChatVideoTCPDto dto = new VChatVideoTCPDto();
			dto.setType(VChatVideoTCPTypeEnum.video.getCode());
			dto.setSubType(VChatVideoStatusEnum.exit.getCode());
			dto.setData("对方断开通话");
			neteaseAgent.pushOneAttachMessage(userId, toUserId, JsonHelper.toJson(dto));
		}

		cacheRedis.transaction(tx -> {
			tx.del(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId);
			tx.del(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + toUserId);
			if (Tools.isNotNull(orderId)) {
				tx.del(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId);
			} else if (Tools.isNotNull(cacheOrderId)) {
				tx.del(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + cacheOrderId);
			}
			tx.zrem(AgentRedisCacheConst.VCHAT_USER_VIDEO_ONLINE, "" + userId);
		});
		return ActionResult.success();
	}

	public DoubleVChatVideoDto getRoomInfo(UserBO user, AnchorOnlineEntity anchor, long payUserId, long roomId,
			long orderId, long balance) throws Exception {
		DoubleVChatVideoDto dto = new DoubleVChatVideoDto();
		dto.setOrderId(orderId);
		dto.setSeller(user.getUserid() == payUserId ? true : false);
		dto.setOtherBalance(balance + "钻");
		dto.setOtherHeadUrl(Const.getCdn(user.getPhoto()));
		dto.setOtherId(user.getUserid());
		dto.setOtherName(user.getNickname());
		dto.setRoomId(roomId);
		String msg = "";
		if (dto.isSeller()) {
			msg = "每分钟收入" + anchor.getPrice() + "元";
		} else {
			msg = "每分钟支出" + anchor.getPrice() + "钻";
		}
		dto.setIncomePayInfo(msg);
		return dto;
	}
	
	/**
	 * 
	 * @param userId 	普通用户
	 * @param otherId	主播
	 * @param roomId	房间号
	 * @param orderId	房间ID
	 * @param price		钻石
	 */
	public void setRoomRedis(long userId, long otherId, long roomId, long orderId, int price) {
		cacheRedis.transaction(tx -> {
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ROOM_ID, roomId + "");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, PAY_FLAG, "1");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ORDER_ID, orderId + "");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ENTER_ROOM, "0");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, PAYPRICE, price + "");
			tx.expire(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ROOM_CREATE_EXPIRE);
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + otherId, ROOM_ID, roomId + "");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + otherId, PAY_FLAG, "0");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + otherId, ORDER_ID, orderId + "");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + otherId, ENTER_ROOM, "0");
			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + otherId, PAYPRICE, price + "");
			tx.expire(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + otherId, ROOM_CREATE_EXPIRE);
		});
	}

	public void enterRoom(long userId, long orderId, long toUserId) {
		String payFlag = cacheRedis.hget(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, PAY_FLAG);
		cacheRedis.transaction(tx -> {
			if ("1".equals(payFlag)) {
				tx.hset(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId, PAYUSER, userId + "");
			} else {
				tx.hset(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId, ANCHOR, userId + "");
			}
			tx.expire(AgentRedisCacheConst.VCHAT_ROOM_CREATE_PREFIX + orderId, ROOM_CREATE_EXPIRE);

			tx.hset(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ENTER_ROOM, "1");
			tx.expire(AgentRedisCacheConst.VCHAT_USER_ROOM_PREFIX + userId, ROOM_CREATE_EXPIRE);

			tx.zadd(AgentRedisCacheConst.VCHAT_USER_VIDEO_ONLINE, System.currentTimeMillis(), "" + userId);
		});

	}

}
