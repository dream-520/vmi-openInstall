package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorRecvUserAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserFriendAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserFriendBO;
import com.tigerjoys.shark.miai.agent.enums.AnchorOnlineStateEnum;
import com.tigerjoys.shark.miai.dto.service.FriendListResultDto;
import com.tigerjoys.shark.miai.enums.UserFriendStatusEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFriendsAddLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.UserFriendsAddLogEntity;
import com.tigerjoys.shark.miai.service.IFriendsService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;
import com.tigerjoys.shark.miai.utils.ShowDateStringUtil;

/**
 * 好友关系服务类
 * @author shiming
 *
 */
@Service
public class FriendsServiceImpl implements IFriendsService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserFriendAgent userFriendAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserFriendsAddLogContract userFriendsAddLogContract;
	
	@Autowired
	private IUserOnlineStateAgent userOnlineStateAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IAnchorRecvUserAgent anchorRecvUserAgent;
	
	/**
	 * 关注与取消关注处理
	 */
	@Override
	public Map<String, Object> attentionUser(long userid, long friendid,int state) throws Exception {
		boolean boo = false;
		Map<String,Object> map = new HashMap<>();
		if(state == 0) {
			//取消关注的处理
			boo = userFriendAgent.removeFriend(userid, friendid);
		} else {
			//添加关注的处理
			boo = userFriendAgent.addFriend(userid, friendid);
		}
		if(boo){
			//重新获取对应的操作状态
			map.put("code", 1);
			map.put("id", friendid);
			map.put("status", relationStatus(userid,friendid));
			map.put("fansCount", userFriendAgent.findFriendUserCount(friendid));
//			if(state != 0) {
//				//在进行加关注的情况下  检测是否已经进行发送了好友消息的处理   在没有发送好友消息的情况下进行发送好友消息的处理
//				PageModel pageModel = new PageModel();
//				pageModel.addQuery(Restrictions.eq("userid", userid));
//				pageModel.addQuery(Restrictions.eq("friendid", friendid));
//				long count = userFriendsAddLogContract.count(pageModel);
//				if(count == 0) {
//					//在没有添加过好友关系的前提下进行   首先添加记录 然后进行发送消息
//					UserFriendsAddLogEntity entity = new UserFriendsAddLogEntity();
//					entity.setUserid(userid);
//					entity.setFriendid(friendid);
//					entity.setCreate_time(new Date());
//					userFriendsAddLogContract.insert(entity);
////					try {
////						neteaseAgent.pushOneMessage(userid, friendid, "hi，交个朋友呗！", false);
////					} catch (Exception e) {
////						logger.warn("sending attention msg fail!", e);
////					}
//				}
//			}
			return map;
		}
		map.put("code", 2);
		return map;
	}
	
	/**
	 * 我和好友的关系状态
	 *   0  无关系
	 *   1 他是我的粉丝
	 *   2 我是他的粉丝
	 *   3 我们是好友 
	 * @return ActionResult
	 * @throws Exception
	 */
	@Override
	public int relationStatus(long userid, long friendid) throws Exception {
		int resultInt = 0;
		try {
			UserFriendBO userFriend = userFriendAgent.findUserFriend(userid, friendid);
			if(Tools.isNotNull(userFriend)) {
				if(userFriend.getEachFans() == 0) {
					resultInt = 2;
				} else if(userFriend.getEachFans() == 1){
					resultInt = 3;
				}
			} else {
				UserFriendBO friendUser = userFriendAgent.findUserFriend(friendid,userid);
				if(Tools.isNotNull(friendUser)){
					resultInt = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultInt;
	}
	
	/**
	 * 好友列表
	 */
	@Override
	public List<FriendListResultDto> getFriendList(long userid, long lastUserFrId, int pagesize,int type) throws Exception {
		List<FriendListResultDto> resultDtoList = null;
		List<UserFriendBO> userFriendList = null;
		if(type==1){
			//1好友
			userFriendList = userFriendAgent.findUserFriendPowderList(userid, lastUserFrId, pagesize+1);
		}else if(type==2){
			//2关注
			userFriendList = userFriendAgent.findUserFriendList(userid, lastUserFrId, pagesize+1);
		}else{
			//3粉丝
			userFriendList = userFriendAgent.findFriendUserList(userid, lastUserFrId, pagesize+1);
		}
		//单独处理粉丝问题
		int video_type = 0;
		int audio_type = 0;
		//是否是主播标识
		int waiter = 0;
		AnchorOnlineEntity anchorUser = anchorOnlineContract.findByProperty("userid", userid);
		if(Tools.isNotNull(anchorUser)) {
			video_type = anchorUser.getVideo_type().intValue();
			audio_type = anchorUser.getAudio_type().intValue();
			waiter = 1;
		}
		
		if(Tools.isNotNull(userFriendList)) {
			resultDtoList = new ArrayList<>();
			for(UserFriendBO userFriendBo:userFriendList) {
				FriendListResultDto dto = new FriendListResultDto();
				long userboid = userFriendBo.getUserid();
				if(type != 3) {
					userboid = userFriendBo.getFriendid();
				}
				UserBO user = userAgent.findById(userboid);
				if(Tools.isNull(user)){
					logger.info("getFriendList==查询无此用户=="+userboid);
					continue;
				}
				dto.setId(userFriendBo.getId());
				dto.setAge(ShowDateStringUtil.getAgeForCompareDate(user.getBirthday()));
				dto.setDeclaration(user.getSignature());
				dto.setFaceImg(ServiceHelper.getUserPhoto(user.getPhoto()));
				dto.setNickName(user.getNickname());
				dto.setSex(user.getSex());
				//暂时整体都不需要传递vip标识
//				dto.setVip(user.getVip());
				dto.setVip(0);
				if(type!=UserFriendStatusEnum.FRIEND.getCode()){
					dto.setUserId(userFriendBo.getFriendid());
				} else {
					dto.setUserId(userFriendBo.getUserid());
				}
				
				//默认初始全是好友关系
				int status = 3; 
				if(type == 3) {
					//3粉丝 --是否互粉  0否，1是
					if(userFriendBo.getEachFans()==0) {
						status = 1;
					}
				} else if(type==2) {
					//2关注
					if(userFriendBo.getEachFans()==0){
						status = 2;
					} 
				}
				dto.setStatus(status);
				long balance = userDiamondAgent.getDiamondBalance(dto.getUserId());
				dto.setBalance(balance>0?"有钻":"");
				
				if(waiter == 1) {
					//检测是否处于隐身设置状态
					boolean invisibility = anchorRecvUserAgent.checkInvisibility(dto.getUserId(), userid);
					if(!invisibility)
						dto.setStateStr(userOnlineStateAgent.userOnlineState(dto.getUserId())==AnchorOnlineStateEnum.online.getCode()?AnchorOnlineStateEnum.online.getDesc():"");
				} else {
					dto.setStateStr(userOnlineStateAgent.userOnlineState(dto.getUserId())==AnchorOnlineStateEnum.online.getCode()?AnchorOnlineStateEnum.online.getDesc():"");
				}
				dto.setIsWaiter(userAgent.findById(dto.getUserId()).isWaiter()?1:0);
				
				//单独处理服务类型
				if(type == 3) {
					//粉丝类型 以自己的服务类型为主
					dto.setAnchorTypeAudio(audio_type);
					dto.setAnchorTypeVideo(video_type);
				} else {
					//关注类型 以关注人的类型为准
					AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userFriendBo.getFriendid());
					if(Tools.isNotNull(anchor)) {
						dto.setAnchorTypeAudio(anchor.getAudio_type().intValue());
						dto.setAnchorTypeVideo(anchor.getVideo_type().intValue());
					} else {
						dto.setAnchorTypeAudio(audio_type);
						dto.setAnchorTypeVideo(video_type);
					}
				}
				resultDtoList.add(dto);
			}
		}
		return resultDtoList;
	}

}
