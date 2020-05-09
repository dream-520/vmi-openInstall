package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IIOSUserSmsAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserAutoDialForbiddenAgent;
import com.tigerjoys.shark.miai.agent.IUserOrdinaryOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserSpecialRechargeAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomAvTypeEnum;
import com.tigerjoys.shark.miai.dto.service.AnchorHelperAutoDialDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorRecvUserContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.contract.IUserBlacklistContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFirstRechargeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.shark.miai.inter.entity.UserBlacklistEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.service.IAnchorHelperAutoDialService;

import redis.clients.jedis.Tuple;

/**
 * VIP服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class AnchorHelperAutoDialServiceImpl implements IAnchorHelperAutoDialService {

	private static int RICH_USER_ONLINE_LIMIT = 50;
	
	private static int GET_USER_ONLINE_ROWS = 100;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserOrdinaryOnlineListAgent userOrdinaryOnlineListAgent;
	
	@Autowired
	private IUserDiamondAccountContract userDiamondAccountContract;
	
	@Autowired
	private IAnchorRecvUserContract anchorRecvUserContract;
	
	@Autowired
	private IFreeVideoChatExperienceContract freeVideoChatExperienceContract;

	@Autowired
	private IUserFirstRechargeLogContract userFirstRechargeLogContract;
	
	@Autowired
	private IUserAutoDialForbiddenAgent userAutoDialForbiddenAgent;
	
	@Autowired
	private IUserSpecialRechargeAgent userSpecialRechargeAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IUserBlacklistContract userBlacklistContract;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private IIOSUserSmsAgent iOSUserSmsAgent;
	
	
	

	public ActionResult autoDial(long userId)  throws Exception{
		
		AnchorOnlineEntity anchorOnline =  anchorOnlineContract.findByProperty("userid", userId);
		if(Tools.isNull(anchorOnline)){
			return ActionResult.fail(ErrorCodeEnum.sign_error);
		}
		
		if(anchorOnline.getState() !=1){
			return ActionResult.fail(1,"你已下架状态,不可以拨打");
		}
		
		List<Long> accountList = getUserAllList(anchorOnline);
		
		if(Tools.isNull(accountList)){
			return ActionResult.fail(1,"没获取用户，过些时间请重试");
		}
		
		if(accountList.size()>1 ){
			Collections.shuffle(accountList);
		}
		
		long otherId = 0;
		
		otherId = checkDial(userId,accountList);
		if(otherId == 0){
			return ActionResult.fail(1,"没获取用户，过些时间请重试");
		}
		AnchorHelperAutoDialDto dto = new AnchorHelperAutoDialDto();
		
		int avType = VchatRoomAvTypeEnum.video.getCode();
		if(anchorOnline.getVideo_type() == 1){
			avType = VchatRoomAvTypeEnum.video.getCode();
		}else if(anchorOnline.getAudio_type() == 1){
			avType = VchatRoomAvTypeEnum.audio.getCode();
		}
		dto.setAvType(avType);
		dto.setOtherId(otherId);
		
		return ActionResult.success(dto);
	} 
	
	
	
	private List<Long> getUserAllList(AnchorOnlineEntity anchorOnline) throws Exception{
		Double score = Long.valueOf(System.currentTimeMillis()).doubleValue();
		Set<Tuple> onlineUser = null; 
		List<Long> accountList = new ArrayList<>();
		int maxRunTimes = 20;
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.ge("create_time", cal.getTime()));
		List<VchatRoomEntity> roomList = vchatRoomContract.load(pageModel);
		List<Long> roomUseridList = new ArrayList<>();
		if(Tools.isNotNull(roomList)){
			roomUseridList = roomList.stream().map(VchatRoomEntity::getUserid).collect(Collectors.toList());
		}
		
		List<Long> roomAnchorRecvUser = getAnchorRecvUser(anchorOnline.getUserid());
		do{
			onlineUser = userOrdinaryOnlineListAgent.getOnlineUserWithScores(score,GET_USER_ONLINE_ROWS);
			if(Tools.isNotNull(onlineUser)){
				logger.info("richUserOnline:"+onlineUser.size()+" maxRunTimes:"+maxRunTimes);
				List<Long> userIdList = getUserOnlineLimit(anchorOnline,onlineUser,anchorOnline.getUserid());
				if(Tools.isNotNull(userIdList)){
					//删除测试号
					userIdList.removeAll(userAutoDialForbiddenAgent.getUserIdList());
					userIdList.removeAll(userSpecialRechargeAgent.getUserIdList());
					userIdList.removeAll(roomUseridList);
					userIdList.removeAll(roomAnchorRecvUser);
					
					if(accountList.size()>0){
						accountList.removeAll(userIdList);
					}
					accountList.addAll(userIdList);
					if(accountList.size()>=RICH_USER_ONLINE_LIMIT){
						break;
					}
				}
				if(onlineUser.size()<GET_USER_ONLINE_ROWS){
					break;
				}
				List<Tuple> onlineUserList = new ArrayList<Tuple>(onlineUser);
				Tuple lastUserid =onlineUserList.get(onlineUserList.size()-1);
				//取最后一个score
				score =Double.valueOf(lastUserid.getScore());
				
			}else{
				break;
			}
		}while(accountList.size()<RICH_USER_ONLINE_LIMIT && (--maxRunTimes)>0);
		
		return accountList;
	}
	
	
	/**
	 * 获取在线列表
	 * @param anchorOnline     主播信息
	 * @param onlineUserSet     在线用户列表
	 * @param userId			当前用户ID
	 * @param maxFlag			最大钻石限制
	 * @param tabType			0  所有  1 已聊过的
	 * @return
	 * @throws Exception
	 */
	public List<Long> getUserOnlineLimit(AnchorOnlineEntity anchorOnline,Set<Tuple> onlineUserSet ,long userId) throws Exception{
		List<Long> userIdOut = new ArrayList<>();
		List<String> onlineUserList = new ArrayList<>();
		for (Tuple tuple : onlineUserSet) {
			if(Tools.isNotNull(tuple)) {
				onlineUserList.add(tuple.getElement());
			}
		}
		/*
		Set<String> anchorDialing = userOrdinaryOnlineListAgent.getAnchorDialingUser(userId);
		if(Tools.isNotNull(onlineUserList)){
			if(Tools.isNotNull(anchorDialing)){
				onlineUserList.removeAll(anchorDialing);
			}
		}
		*/
		if (Tools.isNotNull(onlineUserList)) {
			List<Long> idList = onlineUserList.stream().map(v->Long.valueOf(v)).collect(Collectors.toList());
			PageModel pageModel = PageModel.getPageModel();
	        /*
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.eq("anchorId", userId));
			pageModel.addQuery(Restrictions.in("userid", idList));
			List<AnchorRecvUserEntity> anchorRecvUserList = anchorRecvUserContract.load(pageModel);
			if(Tools.isNull(anchorRecvUserList)){
				return userIdOut;
			}
			idList = anchorRecvUserList.stream().map(v->v.getUserid()).collect(Collectors.toList());
			*/
			
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", idList));
			pageModel.addQuery(Restrictions.le("update_time", Tools.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss")));
			List<FreeVideoChatExperienceEntity> freeVideoList = freeVideoChatExperienceContract.load(pageModel);
			
			if(Tools.isNotNull(freeVideoList)){
				freeVideoList.forEach(v->{
					userIdOut.add(v.getUserid());
				});
			}
				
			Integer price = 0;
			if(anchorOnline.getVideo_type() == 1){
				price = anchorOnline.getPrice();
			}else if(anchorOnline.getAudio_type() == 1){
				price = anchorOnline.getAudio_price();
			}
			
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("user_id", idList));
			pageModel.addQuery(Restrictions.ge("balance",price*2 ));
			List<UserDiamondAccountEntity> userDiamondList = userDiamondAccountContract.load(pageModel);
			if (Tools.isNotNull(userDiamondList)) {
				userDiamondList.forEach(v -> {
					if(!userIdOut.contains(v.getUser_id())){
						userIdOut.add(v.getUser_id());
					}

				});
			}

		}
		return userIdOut;
	}
	
	
	private long checkDial(long userId,List<Long> accountList) throws Exception{
		long otherId = 0;
		for(Long id : accountList){
			if(cacheRedis.exists(AgentRedisCacheConst.VCHAT_USER_ORDER_PREFIX + id)){
				continue;
			}
			if(userId == id.longValue()){
				continue;
			}
			if(iOSUserSmsAgent.getUserIdList().containsKey(id)){
				continue;
			}
			
			if (Const.ANCHOR_DIAL_FORBID_USERID_MAP.containsKey(userId)) {
				UserBO audienceUser =userAgent.findById(id);
				if(audienceUser.getCityid() == 4146 || audienceUser.getCityid() == 4149) {
					if (audienceUser.getDegreeid() <= 3) {
						continue;
					}
				}
			}

			UserBlacklistEntity userBlack = userBlacklistContract.getUserBlackList(id,userId);
			if(Tools.isNotNull(userBlack)){
				continue;
			}
			
			//脚本
			String LUA = "local user1,user2 = KEYS[1],KEYS[2]; local ex1 = redis.call('exists',user1); local ex2 = redis.call('exists',user2); if(ex1 and tonumber(ex1) == 0 and ex2 and tonumber(ex2) == 0) then redis.call('set',user1,user1); redis.call('pexpire',user1,35000); redis.call('set',user2,user2); redis.call('pexpire',user2,35000); return 1; else return 0;end";
			String userIdStr = AgentRedisCacheConst.VCHAT_USER_ORDER_PREFIX +userId ;
			String toUserIdStr = AgentRedisCacheConst.VCHAT_USER_ORDER_PREFIX +id ; 
			String result = String.valueOf(cacheRedis.eval(LUA, 2, userIdStr, toUserIdStr));
			if("0".equals(result)){
				continue;
			}else{
				otherId = id;
				setVchatCheckFalg(userId,id,"auto");
				break;
			}
			
		}
		return otherId;
		
	}
	
	
	
	public void setVchatCheckFalg(long userid,long otherid,String dialType) throws Exception {
		cacheRedis.transaction(tx -> {
			tx.set(AgentRedisCacheConst.VCHAT_ROOM_CHECK_PASS_FALG_PREFIX + userid+"_"+otherid, dialType);
			tx.expire(AgentRedisCacheConst.VCHAT_ROOM_CHECK_PASS_FALG_PREFIX + userid+"_"+otherid, 45);
		});
	}
	
	
	public List<Long> getAnchorRecvUser(long anchorId) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -6);
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.ge("create_time", cal.getTime()));
		pageModel.addQuery(Restrictions.eq("anchorid", anchorId));
		pageModel.addQuery(Restrictions.ge("vchat_duration", 1));
		List<VchatRoomEntity> roomList = vchatRoomContract.load(pageModel);
		List<Long> roomUseridList = new ArrayList<>();
		if(Tools.isNotNull(roomList)){
			roomUseridList = roomList.stream().map(VchatRoomEntity::getUserid).collect(Collectors.toList());
		}
		return roomUseridList;
	}
	
}
