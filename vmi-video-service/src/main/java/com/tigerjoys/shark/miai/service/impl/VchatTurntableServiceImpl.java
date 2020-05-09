package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.dto.VchatTurntableConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.dto.service.BtnData;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPage;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPageNew;
import com.tigerjoys.shark.miai.dto.service.TurntableCheckDto;
import com.tigerjoys.shark.miai.dto.service.VchatTurntableInfoDto;
import com.tigerjoys.shark.miai.dto.service.VchatTurntableShowDto;
import com.tigerjoys.shark.miai.dto.service.VchatturntablePayDto;
import com.tigerjoys.shark.miai.enums.DlgAndGoPageEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatTurntableContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.service.IVChatVideoYXService;
import com.tigerjoys.shark.miai.service.IVchatTurntableService;

/**
 * VIP服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class VchatTurntableServiceImpl implements IVchatTurntableService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	
	@Autowired
	private IUserChatTurntableContract userChatTurntableContract;
	
	@Autowired
	private IVChatVideoYXService vChatVideoYXService;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;

	@Autowired
	private INeteaseAgent neteaseAgent;


	
	@Override
	public ActionResult homeInfo(long userId, Long otherId) throws Exception {
	
		SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.VCHAT_TURNTABLE_INFO);
		if(Tools.isNull(config)){
			return ActionResult.fail(ErrorCodeEnum.error);
		}
		
		String  orderid = cacheRedis.get(AgentRedisCacheConst.VCHAT_USER_ORDER_PREFIX+userId);
		
		VchatTurntableConfigDto turntableConfig = JsonHelper.toObject(config.getValue(), VchatTurntableConfigDto.class);
		VchatTurntableInfoDto dto = new VchatTurntableInfoDto();
		String ruleText = "";
		if(Tools.isNotNull(turntableConfig.getRuleText())){
			String[] rultTextArray = Tools.split(turntableConfig.getRuleText(),"\\n");
			for(int i=0;i<rultTextArray.length;i++){
				if(i == 0){
					ruleText =ruleText + rultTextArray[i];
				}else{
					ruleText = ruleText+"\n"+rultTextArray[i];
				}
			}
		}
		
		dto.setRuleText(ruleText);
		dto.setTotal(6);
		dto.setTurntablePic(Const.getCdn(turntableConfig.getUserPic()));
		// 如果是主播
		if(Tools.isNotNull(orderid)){
			VchatRoomEntity room = vchatRoomContract.findByProperty("orderId", Long.parseLong(orderid));
			if(Tools.isNotNull(room)){
				if( userId == room.getAnchorid() || userId == vChatVideoYXService.getRealUserId(room.getAnchorid())){
					dto.setRuleText(null);
					dto.setTotal(0);  //0是主播端
					dto.setTurntablePic(Const.getCdn(turntableConfig.getAnchorPic()));
				}
			}
		}
	
		return ActionResult.success(dto);
	}

	@Override
	public ActionResult turntableInfo(long userId, Long otherId) throws Exception {
		
		SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.VCHAT_TURNTABLE_INFO);
		if(Tools.isNull(config)){
			logger.info("turntableXX:config为空");
			return ActionResult.fail(ErrorCodeEnum.error);
		}
		
		VchatTurntableShowDto dto = new VchatTurntableShowDto();
		
		
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", otherId);
		if (Tools.isNull(anchor)) {
			return ActionResult.fail(ErrorCodeEnum.anchor_inexistence);
		}
		List<Long> turnTableIdList = null;
		if (Tools.isNotNull(anchor.getTurntable())) {
			turnTableIdList = new ArrayList<>();
			JSONArray array = JsonHelper.toJsonArray(anchor.getTurntable());
			// 处理标签的选中状态
			for (int i = 0; i < array.size(); i++) {
				TurntableCheckDto checkDto = array.getObject(i, TurntableCheckDto.class);
				if (Tools.isNotNull(checkDto)) {
					turnTableIdList.add(checkDto.getId());
				}
			}
		}
		
		PageModel pageModel = PageModel.getPageModel();
		if(Tools.isNotNull(turnTableIdList)){
			pageModel.addQuery(Restrictions.notin("id", turnTableIdList));
		}
		List<UserChatTurntableEntity> list = userChatTurntableContract.load(pageModel);
		long userBalance = userDiamondAgent.getDiamondBalance(userId);
		if(Tools.isNull(list)){
			logger.info("turntableXX:list为空");
			return ActionResult.fail(ErrorCodeEnum.error);
		}
		Map<Long, String> turntableMap = new HashMap<>();
		 list.forEach(v->{
			 turntableMap.put(v.getId(), v.getName());
		 });
		
		 int avType = 0;
		 String  orderid = cacheRedis.get(AgentRedisCacheConst.VCHAT_USER_ORDER_PREFIX+userId);
		 if(Tools.isNotNull(orderid)){
			 VchatRoomEntity room = vchatRoomContract.findByProperty("orderId", Long.parseLong(orderid));
			 if(Tools.isNotNull(room)){
				 avType =  room.getAv_type()==1? 1:0;
			 }
		 }
			
		List<Long> idList = list.stream().filter(v->v.getDiamond()+anchor.getPrice()*4<=userBalance).map(UserChatTurntableEntity::getId).collect(Collectors.toList());
		if(Tools.isNull(idList)){
	        String hintInfo = "您剩余的通话时长不充足,会影响到您的游戏体验";
			DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
			dlgAndGoPage.setAndroidPageTag(DlgAndGoPageEnum.payDiamondDlg.getAndroidPage());
			dlgAndGoPage.setAndroidPageParam(DlgAndGoPage.getTagParam(hintInfo,avType));
		
			dto.setIosTag(1);
			dto.setIosHintInfo(hintInfo);
			dto.setDlgAndGoPage(dlgAndGoPage);
			return ActionResult.success(dto);
		}
		
		Random random = new Random();
		int index = random.nextInt(idList.size());
		Long id = idList.get(index);
		dto.setIndex(id.intValue());
		dto.setOrderId(IdGenerater.generateId()+"");	
		
		String hintInfo = "您选中了"+turntableMap.get(id)+"\n小姐姐即奖开始与您互动";
		DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
		BtnData cancelBtn = new BtnData();
		cancelBtn.setBtnName("确定");
		dlgAndGoPage.setBtnDataList(Arrays.asList(cancelBtn));
		
		dlgAndGoPage.setHintInfo(hintInfo);
		dto.setIosHintInfo(hintInfo);
		dto.setDlgAndGoPage(dlgAndGoPage);
		
		VchatturntablePayDto payInfo = new VchatturntablePayDto();
		payInfo.setOrderId(dto.getOrderId());
		payInfo.setUserId(userId);
		payInfo.setOtherId(otherId);
		payInfo.setTurntableId(id);
		payInfo.setAnchorShowInfo("用户选中了"+turntableMap.get(id)+"\n开始您的表演吧");
		setUserTurntablePay(dto.getOrderId(),payInfo);
			
		return ActionResult.success(dto);
	}

	
	@Override
	public ActionResult turntablePay(String orderId) throws Exception {
		VchatturntablePayDto payInfo = getUserTurntablePay(orderId);
		if (Tools.isNotNull(payInfo)) {
			DiamondResultDto<Long> result = userDiamondAgent.chatTurntableCost(payInfo.getUserId(),
					payInfo.getOtherId(), payInfo.getTurntableId(), Long.valueOf(payInfo.getOrderId()));
			
			 if(AgentErrorCodeEnum.success.getCode() == result.getCode()){
				 DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
					BtnData cancelBtn = new BtnData();
					cancelBtn.setBtnName("确定");
					dlgAndGoPage.setBtnDataList(Arrays.asList(cancelBtn));
					dlgAndGoPage.setHintInfo(payInfo.getAnchorShowInfo());

					Map<String, Object> outMap = new HashMap<>();
					outMap.put("dlgAndGoPage", dlgAndGoPage);
					outMap.put("iosHintInfo", payInfo.getAnchorShowInfo());
					VChatVideoTCPDto dto = new VChatVideoTCPDto();
					dto.setType(VChatVideoTCPTypeEnum.video.getCode());
					dto.setSubType(VChatVideoStatusEnum.anchor_turntable_info.getCode());
					dto.setData(JsonHelper.toJson(outMap));
					neteaseAgent.pushOneAttachMessage(payInfo.getUserId(),
							vChatVideoYXService.getRealUserId(payInfo.getOtherId()), JsonHelper.toJson(dto)); // 主播用户可能是假账户
			        return ActionResult.success(result.getData());
			      }else{
			        return ActionResult.fail(result.getCode(), result.getMsg());
			      }

		}else{
			logger.info("turntableXX:VchatturntablePayDto为空");
		}
		return ActionResult.success();
	}

	/**
	 * 设置用户支付显示信息
	 * @param orderId
	 * @param userId
	 * @param msg
	 * @throws Exception
	 */
	public void setUserTurntablePay(String orderId,VchatturntablePayDto dto) throws Exception {
		cacheRedis.transaction(tx -> {
			tx.set(AgentRedisCacheConst.VCHAT_USER_TURNTABLE_ORDER_PREFIX +orderId, JsonHelper.toJson(dto));
			tx.expire(AgentRedisCacheConst.VCHAT_USER_TURNTABLE_ORDER_PREFIX + orderId, 15);
		});
	}
	
	/**
	 * 获取用户支付显示信息
	 * @param orderId
	 * @param userId
	 * @param msg
	 * @throws Exception
	 */
	public VchatturntablePayDto getUserTurntablePay(String orderId) throws Exception {
		String info = cacheRedis.get(AgentRedisCacheConst.VCHAT_USER_TURNTABLE_ORDER_PREFIX +orderId);
		if(Tools.isNotNull(info)){
			return JsonHelper.toObject(info, VchatturntablePayDto.class);
		}
		return null;
	}
	
}
