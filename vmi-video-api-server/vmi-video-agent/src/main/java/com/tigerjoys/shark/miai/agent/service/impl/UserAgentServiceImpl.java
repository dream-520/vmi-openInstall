package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.CouponGroupEnum;
import org.shark.miai.common.enums.ProxyInletEnum;
import org.shark.miai.common.enums.UserTypeEnum;
import org.shark.miai.common.util.AESFieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.UserIdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.agent.IDeviceAgent;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IProxyAgent;
import com.tigerjoys.shark.miai.agent.IUserCreditScoreAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserGeoAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineListAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.DeviceBO;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserFullBO;
import com.tigerjoys.shark.miai.agent.dto.UserLoginBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.transfer.DeviceModifyDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserModifyDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.BadgeTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.NeteaseErrorEnum;
import com.tigerjoys.shark.miai.agent.enums.UserFrEnum;
import com.tigerjoys.shark.miai.agent.enums.UserLoginLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserMemoEnum;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserSVIPLevelEnum;
import com.tigerjoys.shark.miai.agent.event.ICreateUserCallback;
import com.tigerjoys.shark.miai.agent.service.IUserAgentService;
import com.tigerjoys.shark.miai.inter.contract.IAppLoginContract;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.contract.IProxyContract;
import com.tigerjoys.shark.miai.inter.contract.ISequenceContract;
import com.tigerjoys.shark.miai.inter.contract.IShareVipCardContract;
import com.tigerjoys.shark.miai.inter.contract.ITalentVipContract;
import com.tigerjoys.shark.miai.inter.contract.IUserCanCallLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChargeDataLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserCharmContract;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserExtensionContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteMappingContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInvitecodeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserLoginLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserMemoContract;
import com.tigerjoys.shark.miai.inter.contract.IUserSvipContract;
import com.tigerjoys.shark.miai.inter.entity.AppLoginEntity;
import com.tigerjoys.shark.miai.inter.entity.CopyUserEntity;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.shark.miai.inter.entity.ProxyEntity;
import com.tigerjoys.shark.miai.inter.entity.TalentVipEntity;
import com.tigerjoys.shark.miai.inter.entity.UserCanCallLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserCharmEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;
import com.tigerjoys.shark.miai.inter.entity.UserExtensionEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInvitecodeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserLoginLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserMemoEntity;
import com.tigerjoys.shark.miai.inter.entity.UserSvipEntity;

import net.rubyeye.xmemcached.utils.ByteUtils;

/**
 * 用户服务接口实现类
 * 
 * @author chengang
 *
 */
@Service
public class UserAgentServiceImpl implements IUserAgentService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 空对象
	 */
	private static final UserBO EMPTY_DTO;

	static {
		EMPTY_DTO = new UserBO();
		EMPTY_DTO.setUserid(0L);
	}

	private LinkedList<Long> idList = null;

	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private ICopyUserContract copyUserContract;

	@Autowired
	private IProxyContract proxyContract;

	@Autowired
	private ISequenceContract sequenceContract;

	@Autowired
	private IUserExtensionContract userExtensionContract;

	@Autowired
	private IAppLoginContract appLoginContract;

	@Autowired
	private IUserCharmContract userCharmContract;
	
	@Autowired
	private IFreeVideoChatExperienceContract  freeVideoChatExperienceContract;

	@Autowired
	private IUserMemoContract userMemoContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserPointAgent userPointAgent;

	@Autowired
	private INeteaseAgent neteaseAgent;

	@Autowired
	private ICouponAgent couponAgent;

	@Autowired
	private IProxyAgent proxyAgent;

	@Autowired
	private IUserLoginLogContract userLoginLogContract;
	
	@Autowired
	private IShareVipCardContract  shareVipCardContract;

	@Autowired
	private IUserOnlineListAgent userOnlineListAgent;

	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_CACHE)
	private CacheRedis userCacheRedis;

	@Autowired
	private IUserCreditScoreAgent userCreditScoreAgent;
	
	@Autowired
	private IUserGeoAgent userGeoAgent;

	@Autowired
	private IDeviceAgent deviceAgent;

	@Autowired
	private IUserInviteMappingContract userInviteMappingContract;

	@Autowired
	private IUserInvitecodeContract userInvitecodeContract;

	@Autowired
	private ITalentVipContract talentVipContract;

	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	private IUserChargeDataLogContract userChargeDataLogContract;
	
	@Autowired
	private IUserSvipContract userSvipContract;
	
	@Autowired
	private IUserCanCallLogContract userCanCallLogContract;
	
	private final Random random = new Random();

	@Override
	public UserBO findById(long userid) throws Exception {
		if (userid <= 0)
			return null;

		String key = AgentRedisCacheConst.USER_CACHE_KEY + userid;
		UserBO dto = (UserBO) userCacheRedis.getObject(key);
		if (dto != null) {
			if (dto.getUserid() == 0) {
				return null;
			} else {
				return dto;
			}
		}

		dto = transferUser(userContract.findById(userid));
		if (dto == null) {
			dto = EMPTY_DTO;
		}

		userCacheRedis.setObject(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);

		return dto.getUserid() == 0 ? null : dto;
	}

	@Override
	public UserFullBO findFullById(long userid) throws Exception {
		if (userid <= 0)
			return null;

		return transferFullUser(userContract.findById(userid));
	}

	@Override
	public Map<Long, UserBO> findById(List<Long> userIds) throws Exception {
		if (Tools.isNull(userIds))
			return null;

		Map<Long, UserBO> dataMap = new HashMap<>();
		List<Object> returnList = userCacheRedis.pipelinedAndReturnAll(pipeline -> {
			for (Long userId : userIds) {
				pipeline.get(ByteUtils.getBytes(AgentRedisCacheConst.USER_CACHE_KEY + userId));
			}
		});

		Set<Long> emptyDataSet = new HashSet<>();
		// 批量返回的数据不为空，则将数据转换为对象存储到dataMap中
		if (Tools.isNotNull(returnList)) {
			for (int i = 0, size = userIds.size(); i < size; i++) {
				byte[] b = (byte[]) returnList.get(i);
				if (b != null) {
					UserBO uf = (UserBO) userCacheRedis.decode(b);
					if (uf.getUserid() != 0)
						dataMap.put(userIds.get(i), uf);
				} else {
					emptyDataSet.add(userIds.get(i));
				}
			}
		}

		// 如果emptyDataSet不为空，则代表还有数据不在缓存中，则需要批量从数据库中获取，并且将其放入到缓存中
		if (Tools.isNotNull(emptyDataSet)) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.in("id", emptyDataSet));
			List<UserEntity> list = userContract.load(pageModel);
			if (Tools.isNotNull(list)) {
				List<UserBO> boList = new ArrayList<>(list.size());
				for (UserEntity ug : list) {
					UserBO bo = transferUser(ug);

					dataMap.put(bo.getUserid(), bo);
					boList.add(bo);

					// 将为查找的数据删除掉
					emptyDataSet.remove(bo.getUserid());
				}

				// 顺便将数据放入到缓存中
				userCacheRedis.pipelined(pipeline -> {
					boList.forEach(e -> {
						String cacheKey = AgentRedisCacheConst.USER_CACHE_KEY + e.getUserid();
						pipeline.set(ByteUtils.getBytes(cacheKey), userCacheRedis.encode(e));
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
					});
				});
			}

			// 如果emptyDataSet还有空的，则创建空对象存放到redis中
			if (Tools.isNotNull(emptyDataSet)) {
				byte[] empty = userCacheRedis.encode(EMPTY_DTO);

				userCacheRedis.pipelined(pipeline -> {
					emptyDataSet.forEach(userId -> {
						String cacheKey = AgentRedisCacheConst.USER_CACHE_KEY + userId;
						pipeline.set(ByteUtils.getBytes(cacheKey), empty);
						pipeline.expire(cacheKey, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE);
					});
				});
			}
		}
		return dataMap;
	}

	@Override
	public UserBO findByIdRefreshCache(long userid) throws Exception {
		if (userid <= 0)
			return null;

		UserBO dto = transferUser(userContract.findById(userid));
		if (dto == null) {
			dto = EMPTY_DTO;
		}

		// 获得数据并且刷新缓存
		userCacheRedis.setObject(AgentRedisCacheConst.USER_CACHE_KEY + userid, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, dto);

		return dto.getUserid() == 0 ? null : dto;
	}

	@Override
	public UserBO findByUsername(String username) throws Exception {
		if (Tools.isNull(username))
			return null;
		UserEntity user = userContract.findByUsername(username);
		return transferUser(user);
	}

	@Override
	public UserBO findByNickname(String nickname) throws Exception {
		if (Tools.isNull(nickname))
			return null;
		UserEntity user = userContract.findByNickname(nickname);
		return transferUser(user);
	}

	@Override
	public UserBO findByIdcard(int idcard) throws Exception {
		if (idcard <= 0)
			return null;
		UserEntity user = userContract.findByProperty("idcard", idcard);
		return transferUser(user);
	}

	@Override
	public UserBO findByUniqueKey(String uniqueKey) throws Exception {
		if (Tools.isNull(uniqueKey))
			return null;
		UserEntity user = userContract.findByUniqueKey(uniqueKey);
		return transferUser(user);
	}

	@Override
	public UserBO findByClientid(String clientid) throws Exception {
		if (Tools.isNull(clientid)) {
			return null;
		}
		UserEntity user = userContract.findByProperty("clientid", clientid);
		return transferUser(user);
	}

	@Override
	public UserFullBO findFullByUniqueKey(String uniqueKey) throws Exception {
		if (Tools.isNull(uniqueKey))
			return null;
		UserEntity user = userContract.findByUniqueKey(uniqueKey);

		return transferFullUser(user);
	}

	@Override
	public UserBO findByMobile(String mobile) throws Exception {
		if (!Tools.isMobile(mobile))
			return null;
		UserEntity user = userContract.findByMobile(AESFieldUtils.encrypt(mobile));
		return transferUser(user);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public UserFullBO createUser(UserCreatDto dto, ICreateUserCallback callback) throws Exception {
		// 此处生成idcard
		long idcard = getNextId();
		Date currDate = new Date();

		String nickname = dto.getNickname();
		if (Tools.isNotNull(nickname)) {
			while (findByNickname(nickname)!=null) {
				nickname = dto.getNickname().length()>6?dto.getNickname().substring(0,6)+random.nextInt(9999):dto.getNickname()+random.nextInt(9999);
			}
		}
		if (Tools.isNull(nickname)) {
			nickname = "yoyo" + Integer.toHexString((int) idcard);
		}
		
		// 如果clientid有相同的，则将之前的用户的clientid抹掉
		Integer oldPlatform = null;
		String oldTopic = null;
		UserEntity clientUser = userContract.findByProperty("clientid", dto.getClientid());
		if (clientUser != null) {
			try {
				Map<String, Object> updateStatement = new HashMap<>();
				updateStatement.put("update_time", currDate);
				updateStatement.put("clientid", null);
				
				//清除原始的推送方式
				updateStatement.put("push_channel", -1);
				updateStatement.put("huawei_token", null);
				
				userContract.updateById(updateStatement, clientUser.getId());

				oldPlatform = clientUser.getPlatform();
				oldTopic = Const.TASK_PUSH_TOPIC_PREFIX + oldPlatform + clientUser.getWaiter();
			} finally {
				clearUserCache(clientUser.getId());
			}
		}

		long userid = UserIdGenerater.generateId();
		// 创建用户
		UserEntity user = new UserEntity();
		user.setId(userid);// 自定义主键
		user.setBirthday(dto.getBirthday());
		user.setCreate_time(currDate);
		user.setPackage_name(dto.getPackageName());
		user.setFr(dto.getFr());
		user.setUsername(dto.getOpenid());
		if (dto.getFr()==UserFrEnum.mobile.getCode()&&Tools.isMobile(dto.getOpenid())) {
			user.setMobile(AESFieldUtils.encrypt(dto.getOpenid()));
			//绑定手机号送积分
			userPointAgent.changePointAccount(userid, UserPointAccountLogTypeEnum.bind_mobile.getCode(), 1, dto.getOpenid(), UserPointAccountLogTypeEnum.bind_mobile.getDesc());
		}
		user.setNickname(nickname);
		user.setPassword(MD5.encode("#@$!!23dddr44332"));
		user.setSex(dto.getSex());
		user.setStatus(1);
		user.setUnique_key(MD5.encode(System.currentTimeMillis() + "#" + new Random().nextInt(10000)));
		user.setUpdate_time(currDate);
		user.setIdcard((int) idcard);
		user.setCountryid(dto.getCountryid());
		user.setProvinceid(dto.getProvinceid());
		user.setCityid(dto.getCityid());
		user.setDegreeid(1);// 默认1级
		user.setRefresh_time(currDate);
		user.setClientid(dto.getClientid());
		user.setVip(0);
		user.setSvip(UserSVIPLevelEnum.NORMAL_LEVEL.getLevel());
		user.setTalent_vip(0);
		user.setHonesty_badge(BadgeTypeEnum.NO_PROMISE.getCode());
		user.setPhoto(dto.getPhoto());
		user.setSignature(dto.getSignature());
		user.setIntroduce("");
		user.setProxy(0);
		user.setContact_status(1);
		user.setWaiter(0);
		user.setPlatform(dto.getPlatform());
		user.setIm_token(registerAndGetToken(userid, nickname, Const.getCdn(dto.getPhoto())));
		user.setAllow_video_chat(1);
		user.setFlower_toasted(0);
		user.setDisturb(0);
		user.setPush_channel(dto.getPushchannel());
		user.setHuawei_token(dto.getHuaweiToken());
		user.setChannel(dto.getChannel());
		userContract.insert(user);

		//把城市定位信息更新到设备表
		DeviceBO device = deviceAgent.findByClientId(dto.getClientid());
		if(device != null) {
			DeviceModifyDto modifyDeviceDto = new DeviceModifyDto();
			modifyDeviceDto.setDeviceId(device.getDeviceId());
			if(dto.getCountryid() != 0) {
				modifyDeviceDto.setCountry_id(dto.getCountryid());
			}
			if(dto.getProvinceid() != 0) {
				modifyDeviceDto.setProvince_id(dto.getProvinceid());
			}
			if(dto.getCityid() != 0) {
				modifyDeviceDto.setCity_id(dto.getCityid());
			}
			deviceAgent.modifyDevice(modifyDeviceDto);
		}
		
		
		UserInvitecodeEntity inviteCode = userInvitecodeContract.getInviteCode();
		if (Tools.isNotNull(inviteCode)) {
			UserInviteMappingEntity inviteMapping = new UserInviteMappingEntity();
			inviteMapping.setUserid(user.getId());
			inviteMapping.setName("默认邀请码");
			inviteMapping.setInvitecode(inviteCode.getInviteCode());
			inviteMapping.setInvite_num(0);
			inviteMapping.setCreate_time(currDate);
			userInviteMappingContract.insert(inviteMapping);
			userInvitecodeContract.updateUsed(inviteCode.getId());
		}
		if (Tools.isNotNull(dto.getInviteCode())) {
			proxyAgent.addInvitation(user.getId(), dto.getInviteCode());
		}

		if (dto.getOpenid() != null && (dto.getFr() == UserFrEnum.weixin.getCode() || dto.getFr() == UserFrEnum.qq.getCode())) {
			// 添加第三方信息登录表
			AppLoginEntity appLogin = new AppLoginEntity();
			appLogin.setCreate_time(currDate);
			appLogin.setUpdate_time(currDate);
			appLogin.setOpenid(dto.getOpenid());
			appLogin.setAccess_date(currDate);
			appLogin.setAccess_token("");
			appLogin.setExpires_in(0);
			appLogin.setRefresh_token("");
			appLogin.setUserid(userid);
			appLoginContract.insert(appLogin);
		}

		// 添加用户拓展表
		UserExtensionEntity userExtention = new UserExtensionEntity();
		userExtention.setCreate_time(currDate);
		userExtention.setUserid(userid);
		userExtention.setZodiac("");
		userExtention.setMarriage(0);
		userExtention.setJob("");
		userExtention.setIncome(0);
		userExtention.setStature(0);
		userExtention.setWeight(0);
		userExtention.setSex_opinion(dto.getSexOpinion());
		userExtention.setSpouse_opinion(dto.getSpouseOpinion());
		userExtention.setMake_friend(dto.getMakeFriend());
		userExtention.setWeight(0);
		userExtensionContract.insert(userExtention);

		// 添加用户魅力值表
		UserCharmEntity uc = new UserCharmEntity();
		uc.setCreate_time(currDate);
		uc.setUserid(userid);
		uc.setCharm(0);
		userCharmContract.insert(uc);

		if (callback != null)
			callback.doExecute(user);
		userGeoAgent.modifyUserGeo(userid, Tools.isNotNull(dto.getLng()) ? dto.getLng() : 0, Tools.isNotNull(dto.getLat()) ? dto.getLat() : 0, null, null);
		
		if (Tools.isMobile(user.getUsername())&&shareVipCardContract.queryCardStatus(user.getUsername())) {
			couponAgent.addUserCoupon(userid, CouponGroupEnum.offline_ktv.getGroup());
			shareVipCardContract.usedCard(user.getUsername());
		}
		
		// 初始化信用分账户
		userCreditScoreAgent.initUserCreaditAccount(userid, (user.getWaiter() == 1) ? UserTypeEnum.servicer.getCode() : UserTypeEnum.ordinary.getCode());

		// 设置用户与设备关联信息
		if (dto.getVersionCode() != null && dto.getVersionCode() != null && Tools.isNotNull(dto.getClientid())) {
			// 启动线程池更新设备与用户关联信息
			ExecutorServiceHelper.executor.execute(() -> {
				try {
					deviceAgent.relatedUserAndDevice(user.getId(), user.getClientid(), dto.getAppVersion(), dto.getVersionCode());
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			});
		}

		// 解绑老用户和绑定新注册用户
		this.subscribePushTopicByAlias(oldPlatform, oldTopic, dto.getPlatform(), Const.TASK_PUSH_TOPIC_PREFIX + dto.getPlatform() + user.getWaiter(),
				Lists.newArrayList(user.getClientid()));

		// 初始化该用户的系统消息数量
		userCacheRedis.hset(CommonConst.sys_user_message, String.valueOf(userid), Tools.formatString(userCacheRedis.get(CommonConst.sys_message), "0"));
		return transferFullUser(user);
	}

	@Override
	public AgentResult updateUsername(long userid, String newUsername) throws Exception {
		if (newUsername != null) {
			newUsername = newUsername.trim();
		}
		if (Tools.isNull(newUsername) || userid <= 0) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}

		UserEntity user = userContract.findByUsername(newUsername);
		if (user != null) {
			if (user.getId().longValue() != userid) {
				return AgentResult.fail(AgentErrorCodeEnum.user_username_isused);
			} else {
				return AgentResult.success();
			}
		} else {
			try {
				UserEntity temp = new UserEntity();
				temp.setId(userid);
				temp.setUpdate_time(new Date());
				temp.setUsername(newUsername);
				userContract.update(temp);

				return AgentResult.success();
			} finally {
				clearUserCache(userid);
			}
		}
	}
	
	@Override
	public void updateUserLabels(long userid, String label) throws Exception {
		if (userid <= 0 || label == null)
			throw new IllegalArgumentException("parameter is error");
		if (label != null) {
			label = label.trim();
		}
		UserEntity user = userContract.findById(userid);
		if (user != null) {
			if (user.getLabels()!=null) {
				if (!user.getLabels().contains(label)) {
					try {
						UserEntity temp = new UserEntity();
						temp.setId(userid);
						temp.setLabels(user.getLabels()+label);
						temp.setUpdate_time(new Date());
						userContract.update(temp);
					} finally {
						clearUserCache(userid);
					}
				}
			}else{
				try {
					UserEntity temp = new UserEntity();
					temp.setId(userid);
					temp.setLabels(label);
					temp.setUpdate_time(new Date());
					userContract.update(temp);
				} finally {
					clearUserCache(userid);
				}
			}
		}
	}

	@Override
	public void updateUser(UserModifyDto dto) throws Exception {
		if (dto.getUserid() <= 0)
			throw new IllegalArgumentException("parameter userid is error");

		UserBO userBO = this.findById(dto.getUserid());
		Date currDate = new Date();

		try {
			if (Tools.isNotNull(dto.getClientid())) {
				if (Tools.isNull(userBO.getClientid()) || !userBO.getClientid().equals(dto.getClientid())) {
					// 如果clientid有相同的，则将之前的用户的clientid抹掉
					UserEntity clientUser = userContract.findByProperty("clientid", dto.getClientid());
					if (clientUser != null) {
						try {
							Map<String, Object> updateStatement = new HashMap<>();
							updateStatement.put("update_time", currDate);
							updateStatement.put("clientid", null);
							
							//清除原始的推送方式
							updateStatement.put("push_channel", -1);
							updateStatement.put("huawei_token", null);
							
							userContract.updateById(updateStatement, clientUser.getId());

							// 解除原有的绑定用户
							newPushAgent.unsubscribeTopicByAlias(clientUser.getPlatform(), Const.TASK_PUSH_TOPIC_PREFIX + clientUser.getPlatform() + clientUser.getWaiter(),
									Lists.newArrayList(clientUser.getClientid()));

							//unbind = true;
						} finally {
							clearUserCache(clientUser.getId());
						}
					}
				}
			}

			UserEntity temp = new UserEntity();
			temp.setId(dto.getUserid());
			temp.setUsername(dto.getUserName());
			temp.setBirthday(dto.getBirthday());
			temp.setCountryid(dto.getCountryid());
			temp.setProvinceid(dto.getProvinceid());
			temp.setCityid(dto.getCityid());
			temp.setNickname(dto.getNickname());
			temp.setPhoto(dto.getPhoto());
			temp.setBg_picture(dto.getBgPicture());
			temp.setSex(dto.getSex());
			temp.setSignature(dto.getSignature());
			temp.setIntroduce(dto.getIntroduce());
			temp.setUpdate_time(currDate);
			temp.setClientid(dto.getClientid());
			temp.setPlatform(dto.getPlatform());
			temp.setRefresh_time(dto.getRefresh_time());
			temp.setUnique_key(dto.getUnique_key());
			temp.setMobile(AESFieldUtils.encrypt(dto.getMobile()));
			temp.setQq(AESFieldUtils.encrypt(dto.getQq()));
			temp.setWeixin(AESFieldUtils.encrypt(dto.getWeixin()));
			temp.setContact_status(dto.getContact_status());
			temp.setVideo_auth(dto.getVideo_auth());
			temp.setVideo_auth_pic(dto.getVideo_auth_pic());
			temp.setStick_time(dto.getStickTime());
			temp.setPackage_name(dto.getPackageName());
			temp.setPush_channel(dto.getPushchannel());
			temp.setLabels(dto.getLabels());
			temp.setChannel(dto.getChannel());
			temp.setStatus(dto.getStatus());
			if(Tools.isNotNull(dto.getHuaweiToken()) && dto.getHuaweiToken().length() > 0)
				temp.setHuawei_token(dto.getHuaweiToken());
			userContract.update(temp);

			// 设置用户与设备关联信息
			if (dto.getVersionCode() != null && dto.getVersionCode() != null && Tools.isNotNull(dto.getClientid())) {
				// 启动线程池更新设备与用户关联信息
				ExecutorServiceHelper.executor.execute(() -> {
					try {
						deviceAgent.relatedUserAndDevice(dto.getUserid(), dto.getClientid(), dto.getAppVersion(), dto.getVersionCode());
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				});
			}
		} finally {
			clearUserCache(dto.getUserid());
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateUserLevel(long userid, int levelId) throws Exception {
		if (userid <= 0 || levelId < 1)
			throw new IllegalArgumentException("parameter is error");

		UserBO user = findById(userid);
		if (user == null)
			throw new NullPointerException("user is null");

		logger.info("修改用户等级,userid:" + userid + ", levelId:" + levelId);

		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setDegreeid(levelId);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}

	@Override
	public void updateVip(long userid, int vip, Date expireDate) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("userid is error");

		logger.info("升级VIP用户,userid:" + userid + ", vip:" + vip);

		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setVip(vip);
			temp.setUpdate_time(new Date());
			if (vip > 0) {
				temp.setVip_expire(expireDate);
			}
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}

	@Override
	public void updateFlowerToasted(long userid) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("userid is error");
		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setFlower_toasted(1);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}
	
	@Override
	public void updateSvip(long userid) throws Exception {
		if(userid <= 0 ) {
			throw new IllegalArgumentException("userid is error");
		}
		
		//首先获取一年内的总充值金额,需要加上本次的
		long totalAmount = userChargeDataLogContract.getSumAmount(userid, new Date(Tools.getCurrDayYearTimes(-1)));
		//计算此金额对应的SVIP等级
		UserSVIPLevelEnum svipLevel = UserSVIPLevelEnum.checkSvipLevel(totalAmount);
		UserSvipEntity svip = userSvipContract.findById(userid);
		//当前用户应该的等级
		int currLevel = svipLevel.getLevel();
		
		logger.info("userid="+userid+",oldSvip="+(svip!=null?svip.getLevel():0)+",currsvipLevel="+currLevel);
		
		//如果是大于普通会员，则判断是否需要升级，
		if(currLevel > 0) {
			if(svip == null) {
				svip = initSVIP(userid , currLevel);
			} else {
				//需要升级
				if(svip.getLevel() < currLevel) {
					upgradeLevel(svip, currLevel);
				} else if(svip.getExpire_time().getTime() < System.currentTimeMillis()) {
					//如果已过期，匹配到的用户等级>=当前用户的等级，则需要更新新的等级以及过期时间
					//可能是降级，也可能是升级
					upgradeLevel(svip, currLevel);
				}
			}
		} else {
			//如果用户当前等级>0级，并且已经过期了
			if(svip != null && svip.getExpire_time().getTime() < System.currentTimeMillis()) {
				//用户需要降级为0
				upgradeLevel(svip, UserSVIPLevelEnum.NORMAL_LEVEL.getLevel());
			}
		}
	}
	
	/**
	 * 修改用户的svip等级
	 * @param userid - 用户ID
	 * @param svip - svip等级
	 * @throws Exception
	 */
	private void updateUserSvip(long userid , int svip) throws Exception {
		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setSvip(svip);
			temp.setUpdate_time(new Date());	
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}
	
	/**
	 * 用户的SVIP升级
	 * @param svip - SVIP
	 * @param level - 等级
	 * @throws Exception
	 */
	private void upgradeLevel(UserSvipEntity svip , int level) throws Exception {
		logger.info("用户SVIP升级，userid="+svip.getUserid()+",svip="+svip.getLevel()+",upgradeLevel="+level);
		
		Date currDate = new Date();
		
		svip.setExpire_time(new Date(Tools.getCurrDayYearTimes(1) - 1));
		svip.setLevel(level);
		svip.setUpgrade_time(currDate);
		svip.setUpdate_time(currDate);
		userSvipContract.update(svip);
		
		updateUserSvip(svip.getUserid(), level);
	}
	
	/**
	 * 初始化用户SVIP等级
	 * @param userid - 用户ID
	 * @param level - 等级ID
	 * @return UserSvipEntity
	 * @throws Exception
	 */
	private UserSvipEntity initSVIP(long userid , int level) throws Exception {
		logger.info("用户SVIP升级，userid="+userid+",svip="+0+",upgradeLevel="+level);
		
		Date currDate = new Date();
		
		UserSvipEntity svip = new UserSvipEntity();
		svip.setCreate_time(currDate);
		svip.setExpire_time(new Date(Tools.getCurrDayYearTimes(1) - 1));
		svip.setLevel(level);
		svip.setUpdate_time(currDate);
		svip.setUpgrade_time(currDate);
		svip.setUserid(userid);
		userSvipContract.insert(svip);
		
		updateUserSvip(userid, level);
		
		return svip;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Date updateTalentVip(long userid, int vip, int day) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("userid is error");

		logger.info("升级VIP用户,userid:" + userid + ", vip:" + vip);

		Date currDate = new Date();
		Date expireDate = null;
		try {
			// 顺便延长VIP时间
			TalentVipEntity talentVip = talentVipContract.findById(userid);
			if (talentVip == null) {
				expireDate = new Date(currDate.getTime() + Tools.DAY_MILLIS * day);

				talentVip = new TalentVipEntity();
				talentVip.setCreate_time(currDate);
				talentVip.setUserid(userid);
				talentVip.setExpire_time(expireDate);
				talentVip.setHandler(0);
				talentVipContract.insert(talentVip);
			} else {
				TalentVipEntity temp = new TalentVipEntity();
				temp.setUserid(userid);
				if (talentVip.getExpire_time().getTime() > currDate.getTime()) {
					expireDate = new Date(talentVip.getExpire_time().getTime() + Tools.DAY_MILLIS * day);

					temp.setExpire_time(expireDate);
				} else {
					expireDate = new Date(currDate.getTime() + Tools.DAY_MILLIS * day);

					temp.setExpire_time(expireDate);
				}
				temp.setHandler(0);
				talentVipContract.update(temp);
			}

			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setTalent_vip(vip);
			temp.setUpdate_time(new Date());
			if (vip > 0) {
				temp.setTalent_vip_expire(expireDate);
			}
			userContract.update(temp);

			return expireDate;
		} finally {
			clearUserCache(userid);
		}
	}

	@Override
	public void updateHonestyBadge(long userId, BadgeTypeEnum type, Date expireDate) throws Exception {
		if (userId <= 0) {
			throw new IllegalArgumentException("userId is error !");
		}
		logger.info("更新用户诚信徽章状态,userId:" + userId + ", type:" + type);

		try {
			Map<String, Object> data = new HashMap<>();
			data.put("update_time", new Date());
			data.put("honesty_badge", type.getCode());
			data.put("honesty_badge_expire", expireDate);
			userContract.updateById(data, userId);
		} finally {
			clearUserCache(userId);
		}
	}

	@Override
	public void updateVideoAuth(long userid, String video_auth) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("userid is error");

		logger.info("视频认证用户,userid:" + userid + ", video_auth:" + video_auth);

		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setVideo_auth(video_auth);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}

	@Override
	public void updateWaiter(long userid, int levelId) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("userid is error");

		logger.info("修改用户是否为服务者[传入参数],userid:" + userid + ", levelId:" + levelId);
		UserBO user = findById(userid);
		if (user == null) {
			throw new NullPointerException("user is null");
		}
		if (user.getWaiterLevelId() == levelId) {
			return;
		}

		logger.info("修改用户是否为服务者[进入修改],userid:" + userid + ", levelId:" + levelId);
		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setWaiter(levelId);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
		
		// 重新订阅推送主题
		int platform = user.getPlatform();
		String oldTopic = Const.TASK_PUSH_TOPIC_PREFIX + platform + user.getWaiterLevelId();
		String newTopic = Const.TASK_PUSH_TOPIC_PREFIX + platform + levelId;

		this.subscribePushTopicByAlias(platform, oldTopic, platform, newTopic, Lists.newArrayList(user.getClientid()));
	}

	@Override
	public void updateVideoChat(long userid, int allow) throws Exception {
		if (userid <= 0){
			throw new IllegalArgumentException("userid is error");
		}
		logger.info("视频聊天配置开关,userid:" + userid + ", allow:" + allow);

		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setAllow_video_chat(allow);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}

	}
	
	@Override
	public void updateUserDisturb(long userid, int disturb) throws Exception {
		if (userid <= 0){
			throw new IllegalArgumentException("userid is error");
		}
		logger.info("用户勿扰配置开关,userid:" + userid + ", disturb:" + disturb);

		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setDisturb(disturb);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
		
	}
	
	@Override
	public void updateUserHuaweiToken(long userid, String token) throws Exception {
		if (userid <= 0){
			throw new IllegalArgumentException("userid is error");
		}
		logger.info("更新用户的华为token值,userid:" + userid + ", token:" + token);
		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setHuawei_token(token);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateUserPushChannel(long userid, int pushChannel) throws Exception {
		if (userid <= 0){
			throw new IllegalArgumentException("userid is error");
		}
		logger.info("更新用户的推送pushChannel值,userid:" + userid + ", pushChannel:" + pushChannel);
		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setPush_channel(pushChannel);
			temp.setUpdate_time(new Date());
			userContract.update(temp);
		} finally {
			clearUserCache(userid);
		}
	}

	@Override
	public void changeUserStatus(long userid, int status, String memo) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("parameter is error");

		if (memo == null)
			memo = "";

		Date currDate = new Date();
		int type = (status == 1 ? UserMemoEnum.user_deblock.getCode() : UserMemoEnum.user_block.getCode());
		try {
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setUpdate_time(currDate);
			temp.setStatus(status);
			userContract.update(temp);

			// 添加查封和解封的原因
			UserMemoEntity um = userMemoContract.getUserMemo(userid, type);
			if (um != null) {
				UserMemoEntity tempMemo = new UserMemoEntity();
				tempMemo.setUpdate_time(currDate);
				tempMemo.setMemo(memo);
				userMemoContract.update(tempMemo);
			} else {
				um = new UserMemoEntity();
				um.setMemo(memo);
				um.setType(type);
				um.setUpdate_time(currDate);
				um.setUserid(userid);
				userMemoContract.insert(um);
			}
		} finally {
			clearUserCache(userid);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void changeUserProxy(long userid, Integer inlet, int status, String memo) throws Exception {
		if (userid <= 0)
			throw new IllegalArgumentException("parameter is error");

		if (memo == null)
			memo = "";

		Date currDate = new Date();
		int type = (status == 1 ? UserMemoEnum.proxy_deblock.getCode() : UserMemoEnum.proxy_block.getCode());
		try {
			// ProxyEntity
			ProxyEntity proxy = proxyContract.findByProperty("userid", userid);
			if (proxy == null) {
				proxy = new ProxyEntity();
				proxy.setUserid(userid);
				proxy.setInvitation(0);
				proxy.setTalent_vip_num(0);
				proxy.setTrade_amount(0);
				proxy.setTimes(0L);
				proxy.setTrade_num(0);
				proxy.setDivided_amount(0);
				proxy.setSettlement_amount(0);
				proxy.setStatus(status);
				if (Tools.isNotNull(inlet)) {
					proxy.setInlet(inlet);
				} else {
					proxy.setInlet(ProxyInletEnum.offline.getCode());
				}
				proxy.setCreate_time(new Date());
				proxyContract.insert(proxy);
			} else {
				proxyContract.updateProxyStatus(userid, status);
			}
			UserEntity temp = new UserEntity();
			temp.setId(userid);
			temp.setUpdate_time(currDate);
			temp.setProxy(status);
			userContract.update(temp);

			// 添加查封和解封的原因
			UserMemoEntity um = userMemoContract.getUserMemo(userid, type);
			if (um != null) {
				UserMemoEntity tempMemo = new UserMemoEntity();
				tempMemo.setUpdate_time(currDate);
				tempMemo.setMemo(memo);
				userMemoContract.update(tempMemo);
			} else {
				um = new UserMemoEntity();
				um.setMemo(memo);
				um.setType(type);
				um.setUpdate_time(currDate);
				um.setUserid(userid);
				userMemoContract.insert(um);
			}
		} finally {
			clearUserCache(userid);
		}
	}

	/**
	 * 清除用户的缓存的基本信息
	 * 
	 * @param userid
	 *            - 用户ID
	 */
	public void clearUserCache(long userid) {
		userCacheRedis.del(AgentRedisCacheConst.USER_CACHE_KEY + userid);
	}

	/**
	 * 将数据库用户对象转换为UserBO对象
	 * 
	 * @param user
	 *            - UserEntity
	 * @return UserBO
	 * @throws Exception
	 */
	private UserBO transferUser(UserEntity user) throws Exception {
		if (user == null)
			return null;
		UserBO dto = new UserBO();
		initUserBasicBO(dto, user);
		return dto;
	}

	/**
	 * 将数据库用户对象转换为UserFullBO对象
	 * 
	 * @param user
	 *            - UserEntity
	 * @return UserFullBO
	 * @throws Exception
	 */
	private UserFullBO transferFullUser(UserEntity user) throws Exception {
		if (user == null)
			return null;

		UserFullBO dto = new UserFullBO();
		initUserBasicBO(dto, user);
		dto.setCreateTime(user.getCreate_time());
		dto.setUpdateTime(user.getUpdate_time());

		return dto;
	}

	/**
	 * 初始化用户的基本信息
	 * 
	 * @param dto
	 * @param user
	 * @throws Exception
	 */
	private void initUserBasicBO(UserBO dto, UserEntity user) throws Exception {
		String photo = user.getPhoto();

		dto.setFr(user.getFr());
		dto.setNickname(user.getNickname());
		dto.setPhoto(photo);
		dto.setMobile(AESFieldUtils.decrypt(user.getMobile()));
		dto.setQq(AESFieldUtils.decrypt(user.getQq()));
		dto.setWeixin(AESFieldUtils.decrypt(user.getWeixin()));
		dto.setContactStatus(user.getContact_status());
		dto.setContactText(user.getConcat_text());
		dto.setSex(user.getSex());
		dto.setStatus(user.getStatus());
		dto.setUserid(user.getId());
		dto.setUsername(user.getUsername());
		dto.setCountryid(Tools.longValue(user.getCountryid()));
		dto.setProvinceid(Tools.intValue(user.getProvinceid()));
		dto.setCityid(Tools.longValue(user.getCityid()));
		dto.setDegreeid(Tools.intValue(user.getDegreeid(), 1));
		dto.setSignature(user.getSignature());
		dto.setIntroduce(user.getIntroduce());
		dto.setBirthday(user.getBirthday());
		dto.setIdcard(user.getIdcard());
		dto.setImToken(user.getIm_token());
		dto.setClientid(user.getClientid());
		dto.setPlatform(Tools.intValue(user.getPlatform()));
		dto.setUniqueKey(user.getUnique_key());
		dto.setLastLoginDate(user.getRefresh_time());
		dto.setVip(user.getVip());
		dto.setVipExpire(user.getVip_expire());
		dto.setVideoAuth(user.getVideo_auth());
		dto.setVideoAuthPic(user.getVideo_auth_pic());
		dto.setTalentVip(Tools.intValue(user.getTalent_vip()));
		dto.setTalentVipExpire(user.getTalent_vip_expire());
		dto.setHonestyBadge(user.getHonesty_badge());
		dto.setHonestyBadgeExpire(user.getHonesty_badge_expire());
		dto.setWaiter(user.getWaiter() > 0);// 是否是服务者
		dto.setWaiterLevelId(user.getWaiter());
		dto.setCreateTime(user.getCreate_time());
		dto.setUpdateTime(user.getUpdate_time());
		dto.setStickTime(user.getStick_time());
		dto.setBgPicture(user.getBg_picture());
		dto.setAllowVideoChat(user.getAllow_video_chat());
		dto.setSvip(user.getSvip());
		dto.setPackageName(user.getPackage_name());
		dto.setFlowerToasted(user.getFlower_toasted());
		dto.setDisturb(user.getDisturb());
		dto.setLabels(user.getLabels());
		
		//设置推送方式
		dto.setPushchannel(user.getPush_channel());
		dto.setHuaweiToken(user.getHuawei_token());
	}

	/**
	 * 返回自定义userId
	 * 
	 * @return long
	 * @throws Exception
	 */
	private long getNextId() throws Exception {
		synchronized (getClass()) {
			if (idList == null || idList.isEmpty()) {
				idList = sequenceContract.getSeqList(UserEntity.class, 100);
			}
			return idList.pop();
		}
	}

	/**
	 * 网易注册用户
	 * 
	 * @return
	 * @throws Exception
	 */
	private String registerAndGetToken(long userId, String nickName, String icon) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("accid", String.valueOf(userId));
		params.put("name", nickName);
		params.put("icon", icon);
		JSONObject data = neteaseAgent.createUser(params);
		int code = data.getIntValue("code");
		if (NeteaseErrorEnum.success.getCode() == code) {
			return data.getJSONObject("info").getString("token");
		} else {
			throw new Exception("register to Netease occured error!");
		}
	}

	@Override
	public void loginOut(long userid, UserLoginBO loginbo) throws Exception {
		if (userid <= 0)
			return;
		// 将用户从在线列表中删除
		try {
			userOnlineListAgent.removeOnlineUser(userid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// 登出
		try {
			Map<String, Object> updateStatement = new HashMap<>();
			updateStatement.put("update_time", new Date());
			updateStatement.put("clientid", null);
			//清除原始的推送方式
			updateStatement.put("push_channel", -1);
			updateStatement.put("huawei_token", null);
			userContract.updateById(updateStatement, userid);
			
			CopyUserEntity  copyUser =  copyUserContract.findById(userid);
			if (copyUser!=null) {
				Map<String, Object> copyUpdateStatement = new HashMap<>();
				copyUpdateStatement.put("update_time", new Date());
				copyUpdateStatement.put("clientid", null);
				//清除原始的推送方式
				copyUpdateStatement.put("push_channel", -1);
				copyUpdateStatement.put("huawei_token", null);
				copyUserContract.updateById(copyUpdateStatement, userid);
			}
		} finally {
			clearUserCache(userid);
		}

		// 记录退出登录位置
		UserLoginLogEntity log = new UserLoginLogEntity();
		log.setCity_name(loginbo.getCity_name());
		log.setClient_id(loginbo.getClient_id());
		log.setCreate_time(new Date());
		log.setIp(loginbo.getIp());
		log.setLat(Tools.doubleValue(loginbo.getLat()));
		log.setLng(Tools.doubleValue(loginbo.getLng()));
		log.setType(UserLoginLogTypeEnum.login_out.getCode());
		log.setUser_id(userid);
		log.setApptype(loginbo.getApptype());
		log.setAppversion(loginbo.getAppversion());
		log.setChannel(Tools.formatString(loginbo.getChannel()));
		log.setPackage_name(loginbo.getPackage_name());
		userLoginLogContract.insert(log);
	}

	@Override
	public void subscribePushTopicByAlias(Integer oldPlatform, String oldTopic, int newPlatform, String newTopic, List<String> clientList) {
		if (Tools.isNull(clientList)) {
			return;
		}

		logger.info("订阅推送主题,oldPlatform : " + oldPlatform + ",oldTopic:" + oldTopic + ",newPlatform:" + newPlatform + ",newTopic:" + newTopic + ",clientList:" + clientList);

		try {
			if (clientList.size() > 20) {
				List<List<String>> partitionList = Lists.partition(clientList, 20);
				for (List<String> aliasList : partitionList) {
					// 此处解除订阅和重新订阅
					if (oldPlatform != null && oldTopic != null) {
						newPushAgent.unsubscribeTopicByAlias(oldPlatform, oldTopic, aliasList);
					}
					newPushAgent.subscribeTopicByAlias(newPlatform, newTopic, aliasList);
				}
			} else {
				// 此处解除订阅和重新订阅
				if (oldPlatform != null && oldTopic != null) {
					newPushAgent.unsubscribeTopicByAlias(oldPlatform, oldTopic, clientList);
				}
				newPushAgent.subscribeTopicByAlias(newPlatform, newTopic, clientList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void saveUserCanCallLog(long userid) throws Exception {
		FreeVideoChatExperienceEntity f = freeVideoChatExperienceContract.findByProperty("userid", userid);
		if (f != null) {
			insertUserCanCallLog(userid);
		}else{
			if (userDiamondAgent.getDiamondBalance(userid)>=3) {
				insertUserCanCallLog(userid);
			}
		}
	}

	/**
	 * 保存可拨打用户
	 * @param userid
	 */
	private void insertUserCanCallLog(long userid) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addLimitField(0, 1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("create_time", Tools.getFormatDate(new Date(), "yyyy-MM-dd HH:30:00")));
		List<UserCanCallLogEntity> list = userCanCallLogContract.load(pageModel);
		if (Tools.isNull(list)) {
			UserCanCallLogEntity entity = new UserCanCallLogEntity();
			entity.setCreate_time(Tools.getDateTime(Tools.getFormatDate(new Date(), "yyyy-MM-dd HH:30:00")));
			entity.setUserid(userid);
			userCanCallLogContract.insert(entity);
		}
	}
}
