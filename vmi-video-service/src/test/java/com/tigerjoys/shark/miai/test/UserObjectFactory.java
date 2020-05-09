package com.tigerjoys.shark.miai.test;

import java.util.Date;

import org.apache.log4j.chainsaw.Main;
import org.shark.miai.common.enums.IncomeEnum;
import org.shark.miai.common.enums.MakeFriendEnum;
import org.shark.miai.common.enums.MarriageEnum;
import org.shark.miai.common.enums.SexOpinionEnum;
import org.shark.miai.common.enums.SpouseOpinionEnum;
import org.shark.miai.common.util.AESFieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserExtensionBO;
import com.tigerjoys.shark.miai.dto.service.UserBasicVO;

/**
 * 创建用户对象辅助类
 * @author chengang
 *
 */
public final class UserObjectFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserObjectFactory.class);
	
	/**
	 * 创建一个ID为10001的普通用户对象
	 * @return UserBO
	 */
	public static UserBO createNormalUser(){
		return createNormalUser(10001 , "1988-12-18" , "小橙子在东京" , true , true);
	}
	
	/**
	 * 
	 * 创建一个指定ID的用户对象
	 * @param userid - 用户ID
	 * @param birthday - 生日
	 * @param nickname - 昵称
	 * @param isvip - 是否VIP
	 * @param isTalentVip - 是否达人VIP
	 * @return UserBO
	 */
	public static UserBO createNormalUser(long userid , String birthday , String nickname , boolean isvip , boolean isTalentVip) {
		Date currDate = new Date();

		try {
			UserBO user = new UserBO();
			user.setBirthday(Tools.getDate(birthday));
			user.setCityid(4146L);
			user.setClientid("fb805f9f9f4f96f4460ef9685275be01");
			user.setContactStatus(1);
			user.setCountryid(3592L);
			user.setCreateTime(currDate);
			user.setDegreeid(0);
			user.setFr(0);
			user.setIdcard(10050753);
			user.setImToken("4a6a43a92d01b20c2abe3c3473a1b0fc");
			user.setLastLoginDate(currDate);
			user.setMobile(AESFieldUtils.encrypt("18911344762"));
			user.setNickname(nickname);
			user.setPhoto("/user/1.jpg");
			user.setPlatform(1);
			user.setProvinceid(3593L);
			user.setQq(AESFieldUtils.encrypt("172577882"));
			user.setSex(1);
			user.setSignature("我来过，我很乖");
			user.setStatus(1);
			user.setStickTime(currDate);
			user.setTalentVip(isTalentVip?1:0);
			user.setTalentVipExpire(Tools.getDate(isTalentVip?"2022-09-09":"2000-12-12"));
			user.setUniqueKey("8f32c6c61cf87b80efd849e12c36c16e");
			user.setUpdateTime(currDate);
			user.setUserid(userid);
			user.setUsername("小橙子");
			user.setVideoAuth("/user/aaaa.jpg");
			user.setVip(isvip?1:0);
			user.setVipExpire(Tools.getDate(isvip?"2022-09-09":"2000-12-12"));
			user.setWaiter(true);
			user.setWeixin(AESFieldUtils.encrypt("qq172577882"));
			
			return user;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 创建正常的UserBasicVO对象
	 * @return UserBasicVO
	 */
	public static UserBasicVO createNormalUserBasicVO(){
		UserBasicVO userBasicVO = new UserBasicVO();
		userBasicVO.setAge(18);
		userBasicVO.setBigHead("");
		userBasicVO.setBirthday("1988-08-08");
		userBasicVO.setCityid(3601);
		userBasicVO.setCityName("北京");
		userBasicVO.setGender(1);
		userBasicVO.setHead("");
		userBasicVO.setNickname("小橙子");
		userBasicVO.setSignature("原得一情侣，白首这一生。");
		userBasicVO.setTalentVip(1);
		userBasicVO.setUserid(10001L);
		userBasicVO.setVideoAuth("/user/aaa.mp4");
		userBasicVO.setVip(1);
		
		return userBasicVO;
	}
	
	/**
	 * 创建通用的用户扩展信息对象
	 * @return UserExtensionBO
	 */
	public static UserExtensionBO createNormalUserExtensionBO(){
		UserExtensionBO userExtensionBO = new UserExtensionBO();
		userExtensionBO.setId(1L);
		userExtensionBO.setIncome(IncomeEnum.income1.getCode());
		userExtensionBO.setJob("程序员");
		userExtensionBO.setMakeFriend(MakeFriendEnum.find_friends.getCode());
		userExtensionBO.setMarriage(MarriageEnum.divorced.getCode());
		userExtensionBO.setSexOpinion(SexOpinionEnum.conservative.getCode());
		userExtensionBO.setSpouseOpinion(SpouseOpinionEnum.can_swim.getCode());
		userExtensionBO.setStature(190);
		userExtensionBO.setTraitPoint("热情 爱好");
		userExtensionBO.setUserid(10001L);
		userExtensionBO.setWeight(56);
		userExtensionBO.setZodiac("狮子座");
		
		return userExtensionBO;
	}

	
	
}
