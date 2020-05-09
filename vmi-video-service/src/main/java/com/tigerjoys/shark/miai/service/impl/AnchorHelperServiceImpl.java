package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IIOSUserSmsAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserAutoDialForbiddenAgent;
import com.tigerjoys.shark.miai.agent.IUserOrdinaryOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserSpecialRechargeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.AnchorHelperDto;
import com.tigerjoys.shark.miai.dto.service.RichUserOnlineDto;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorRecvUserContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFirstRechargeLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorRecvUserEntity;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserFirstRechargeLogEntity;
import com.tigerjoys.shark.miai.service.IAnchorHelperService;

import redis.clients.jedis.Tuple;

/**
 * VIP服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class AnchorHelperServiceImpl implements IAnchorHelperService {

	private static int RICH_USER_ONLINE_LIMIT = 50;
	
	private static int RICH_USER_MIN_DIAMOND = 100;
	
	private static int RICH_USER_MAX_DIAMOND = 160;
	
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
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IRedFlowerAccountContract redFlowerAccountContract;
	
	@Autowired
	private IIOSUserSmsAgent iOSUserSmsAgent;
	
	@Autowired
	private IUserAutoDialForbiddenAgent userAutoDialForbiddenAgent;
	
	@Autowired
	private IUserSpecialRechargeAgent userSpecialRechargeAgent;
	

	public ActionResult richUserOnlineListNew(long userId,int tabType)  throws Exception{
		
		AnchorOnlineEntity anchorOnline =  anchorOnlineContract.findByProperty("userid", userId);
		if(Tools.isNull(anchorOnline)){
			return ActionResult.fail(ErrorCodeEnum.sign_error);
		}
		
		List<Long> accountList = new ArrayList<>();
		
		RichUserOnlineDto onlineDto = new RichUserOnlineDto();
		accountList = getUserAllList(anchorOnline,userId,tabType);
		List<Long> newAccountList = new ArrayList<>();
		int dialHelper = anchorOnline.getDial_helper();
		if(anchorOnline.getDial_helper() == 0 && accountList.size()<RICH_USER_ONLINE_LIMIT){
			anchorOnline.setDial_helper(1);
			newAccountList = getUserAllList(anchorOnline,userId,tabType);
		}
		accountList.removeAll(newAccountList);
		accountList.addAll(newAccountList);
		
		//////////////////////////////////////////////////////////////
		List<Long> userIdAllOut = new ArrayList<>();
		if(Tools.isNotNull(accountList)){
			
			if(accountList.size()>RICH_USER_ONLINE_LIMIT){
				userIdAllOut.addAll(accountList.subList(0, RICH_USER_ONLINE_LIMIT));
			}else{
				userIdAllOut.addAll(accountList);
			}
		}
		List<UserBO> userBOList = new ArrayList<>();
		Map<Long, Long> userFreeVideoMap = new HashMap<>();
		Map<Long, Long> userDiamondMap = new HashMap<>();
		if (Tools.isNotNull(userIdAllOut)) {
			
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.in("userid", userIdAllOut));
			List<FreeVideoChatExperienceEntity> freeVideoList = freeVideoChatExperienceContract.load(pageModel);
			
			if(Tools.isNotNull(freeVideoList)){
				freeVideoList.forEach(v->{
					userFreeVideoMap.put(v.getUserid(), v.getUserid());
				});
				
			}
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("user_id", userIdAllOut));
			pageModel.addQuery(Restrictions.ge("balance",anchorOnline.getPrice() ));
			
			List<UserDiamondAccountEntity> userDiamondList = userDiamondAccountContract.load(pageModel);
			if (Tools.isNotNull(userDiamondList)) {
				userDiamondList.forEach(v -> {
					userDiamondMap.put(v.getUser_id(),v.getUser_id());

				});
			}
			
			Map<Long, UserBO> userList = userAgent.findById(userIdAllOut);
			if (Tools.isNotNull(userList)) {
				
				userIdAllOut.forEach(v->{
					UserBO bo = userList.get(v);
					if(Tools.isNotNull(bo)){
						userBOList.add(bo);
					}
				});
				
			}
		}
		
		List<UserBaseInfo> userBaseList = new ArrayList<>();
		List<UserBaseInfo> flowerList = new ArrayList<>();
		for(UserBO re:userBOList){
			AnchorHelperDto userBase = new AnchorHelperDto();
			userBase.setUserId(re.getUserid());
			userBase.setNickName(re.getNickname());
			userBase.setPhoto(Tools.isNotNull(re.getPhoto())?Const.getCdn(re.getPhoto()):Const.getDefaultUserFace());
			//userBase.setBackText1(Tools.isNotNull(userFreeVideoMap.get(re.getUserid()))?"体验用户":"在线");
			userBase.setBackText1("在线");
			userBase.setAnchorTypeAudio(anchorOnline.getAudio_type());
			userBase.setAnchorTypeVideo(anchorOnline.getVideo_type());
			if(Tools.isNotNull(userDiamondMap.get(re.getUserid())) || Tools.isNotNull(userFreeVideoMap.get(re.getUserid())) ){
				if(anchorOnline.getVideo_type() ==1 ){
					userBase.setBtnAction(2);
				}else if(anchorOnline.getAudio_type() ==1 ){
					userBase.setBtnAction(1);
				}
			}
			if(userBase.getBtnAction() > 0){
				userBaseList.add(userBase);
			}else{
				flowerList.add(userBase);
			}
			
		}
		if(userBaseList.size()>2 && dialHelper != 0){
			Collections.shuffle(userBaseList);
		}
		if(Tools.isNotNull(flowerList)){
			userBaseList.addAll(flowerList);
		}
		onlineDto.setDataList(userBaseList);
		
		long dialingNum = userOrdinaryOnlineListAgent.getAnchorDialingUserNum(userId);
	
		long recvNum = userOrdinaryOnlineListAgent.getAnchorDialingRecvUserTotal(userId);
	
		String info ="今日拨打人数:"+dialingNum+"人,拨通个数:"+recvNum+"个";
		
		onlineDto.setInfo(info);
		onlineDto.setTabType(tabType);
		return ActionResult.success(onlineDto);
	} 
	
	
	
	private List<Long> getUserAllList(AnchorOnlineEntity anchorOnline,long userId,int tabType) throws Exception{
		Double score = Long.valueOf(System.currentTimeMillis()).doubleValue();
		Set<Tuple> onlineUser = null; 
		List<Long> accountList = new ArrayList<>();
		int maxRunTimes = 20;
		List<AnchorRecvUserEntity> disturbList = anchorRecvUserContract.getRecvUserByType(userId,1);
		List<AnchorRecvUserEntity> invisibilityList = anchorRecvUserContract.getRecvUserByType(userId,2);
		do{
			onlineUser = userOrdinaryOnlineListAgent.getOnlineUserWithScores(score,GET_USER_ONLINE_ROWS);
			if(Tools.isNotNull(onlineUser)){
				logger.info("richUserOnline:"+onlineUser.size()+" maxRunTimes:"+maxRunTimes);
				List<Long> userIdList = getUserOnlineList(anchorOnline,onlineUser,userId,false,tabType);
				if(Tools.isNotNull(userIdList)){
					//删除测试号
					userIdList.removeAll(userAutoDialForbiddenAgent.getUserIdList());
					userIdList.removeAll(userSpecialRechargeAgent.getUserIdList());
					if(Tools.isNotNull(disturbList)){
						userIdList.removeAll(disturbList.stream().map(AnchorRecvUserEntity::getUserid).collect(Collectors.toList()));
					}
					if(Tools.isNotNull(invisibilityList)){
						userIdList.removeAll(invisibilityList.stream().map(AnchorRecvUserEntity::getUserid).collect(Collectors.toList()));
					}
					if(Tools.isNotNull(iOSUserSmsAgent.getUserIdList().keySet())){
						userIdList.removeAll(iOSUserSmsAgent.getUserIdList().keySet());
					}
					
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
	public List<Long> getUserOnlineList(AnchorOnlineEntity anchorOnline,Set<Tuple> onlineUserSet ,long userId,boolean maxFlag,int tabType) throws Exception{
		List<Long> userIdOut = new ArrayList<>();
		List<String> onlineUserList = new ArrayList<>();
		for (Tuple tuple : onlineUserSet) {
			if(Tools.isNotNull(tuple)) {
				onlineUserList.add(tuple.getElement());
			}
		}
		RichUserOnlineDto onlineDto = new RichUserOnlineDto();
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
			if(tabType == 1){
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("anchorId", userId));
				pageModel.addQuery(Restrictions.in("userid", idList));
				pageModel.addQuery(Restrictions.eq("video", 1));
				List<AnchorRecvUserEntity> anchorRecvUserList = anchorRecvUserContract.load(pageModel);
				if(Tools.isNull(anchorRecvUserList)){
					return userIdOut;
				}
				idList = anchorRecvUserList.stream().map(v->v.getUserid()).collect(Collectors.toList());
			}
			if(anchorOnline.getDial_helper() == 1 ){
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.in("userid", idList));
				List<UserFirstRechargeLogEntity> firstRechargeList = userFirstRechargeLogContract.load(pageModel);
				
				if(Tools.isNotNull(firstRechargeList)){
					List<Long> firstList = new ArrayList<>();
					firstRechargeList.forEach(v->{
						firstList.add(v.getUserid());
					});
					idList.removeAll(firstList);
				}
				if(Tools.isNull(idList)){
					return userIdOut;
				}
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.in("userid", idList));
				pageModel.addQuery(Restrictions.le("update_time", Tools.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss")));
				List<FreeVideoChatExperienceEntity> freeVideoList = freeVideoChatExperienceContract.load(pageModel);
				
				if(Tools.isNotNull(freeVideoList)){
					freeVideoList.forEach(v->{
						userIdOut.add(v.getUserid());
					});
				}
			}else if(anchorOnline.getDial_helper() == 2 || anchorOnline.getDial_helper() == 0){
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.in("userid", idList));
				List<UserFirstRechargeLogEntity> firstRechargeList = userFirstRechargeLogContract.load(pageModel);
				
				if(Tools.isNotNull(firstRechargeList)){
					idList = firstRechargeList.stream().map(v->v.getUserid()).collect(Collectors.toList());
				}else{
					return userIdOut;
				}
			}
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("user_id", idList));
			pageModel.addQuery(Restrictions.ge("balance",RICH_USER_MIN_DIAMOND ));
			if(maxFlag){
				pageModel.addQuery(Restrictions.le("balance",RICH_USER_MAX_DIAMOND ));
			}
			
			List<UserDiamondAccountEntity> userDiamondList = userDiamondAccountContract.load(pageModel);
			if (Tools.isNotNull(userDiamondList)) {
				userDiamondList.forEach(v -> {
					if(!userIdOut.contains(v.getUser_id())){
						userIdOut.add(v.getUser_id());
					}

				});
			}
			
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("user_id", idList));
			pageModel.addQuery(Restrictions.ge("balance",1 ));
			
			List<RedFlowerAccountEntity> redFlowerList = redFlowerAccountContract.load(pageModel);
			if (Tools.isNotNull(redFlowerList)) {
				redFlowerList.forEach(v -> {
					if(!userIdOut.contains(v.getUser_id())){
						userIdOut.add(v.getUser_id());
					}
				});
			}

		}
		return userIdOut;
		
	}
	
	
	
}
