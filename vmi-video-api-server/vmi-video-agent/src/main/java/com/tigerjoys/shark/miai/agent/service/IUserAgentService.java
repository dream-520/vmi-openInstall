package com.tigerjoys.shark.miai.agent.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.UserFullBO;
import com.tigerjoys.shark.miai.agent.dto.UserLoginBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserModifyDto;
import com.tigerjoys.shark.miai.agent.enums.BadgeTypeEnum;
import com.tigerjoys.shark.miai.agent.event.ICreateUserCallback;

/**
 * 用户服务接口
 * @author chengang
 *
 */
public interface IUserAgentService {
	
	/**
	 * 根据ID获得用户对象，此方法走缓存
	 * @param userid - 用户ID
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findById(long userid) throws Exception;
	
	/**
	 * 根据ID获得用户全量信息对象，<b>此方法不走缓存，仅限需要权限信息的地方使用</b>
	 * @param userid - 用户ID
	 * @return UserFullBO
	 * @throws Exception
	 */
	public UserFullBO findFullById(long userid) throws Exception;
	
	/**
	 * 根据用户ID集合获得用户集合
	 * @param userIds - 用户ID集合
	 * @return Map<Long , UserBO>
	 * @throws Exception
	 */
	public Map<Long , UserBO> findById(List<Long> userIds) throws Exception;
	
	/**
	 * 根据ID获得用户对象并且刷新缓存
	 * @param userid - 用户ID
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findByIdRefreshCache(long userid) throws Exception;
	
	/**
	 * 根据用户名获得用户对象
	 * @param username - 用户名
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findByUsername(String username) throws Exception;
	
	/**
	 * 根据用户昵称获得用户对象
	 * @param nickname - 用户昵称
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findByNickname(String nickname) throws Exception;
	
	/**
	 * 根据用户的idcard获得用户对象
	 * @param idcard
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findByIdcard(int idcard) throws Exception;
	
	/**
	 * 根据唯一key获得用户对象
	 * @param uniqueKey - 唯一key
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findByUniqueKey(String uniqueKey) throws Exception;
	
	/**
	 * 找出当前设备绑定的用户信息
	 * @param clientid - 设备ID
	 * @return UserBo
	 * @throws Exception
	 */
	public UserBO findByClientid(String clientid) throws Exception;
	
	/**
	 * 根据唯一key获得用户信息全面的对象,<b>仅限需要权限信息的地方使用</b>
	 * @param uniqueKey - 唯一key
	 * @return UserFullBO
	 * @throws Exception
	 */
	public UserFullBO findFullByUniqueKey(String uniqueKey) throws Exception;
	
	/**
	 * 根据手机号码获得用户对象
	 * @param mobile - 手机号码
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBO findByMobile(String mobile) throws Exception;
	
	/**
	 * 创建新用户
	 * @param callback - 创建用户的回调函数
	 * @return UserFullBO
	 * @throws Exception
	 */
	public UserFullBO createUser(UserCreatDto dto,ICreateUserCallback callback) throws Exception;
	
	/**
	 * 修改用户名
	 * @param userid - 用户ID
	 * @param newUsername - 新的用户名
	 * @return AgentResult
	 * @throws Exception
	 */
	public AgentResult updateUsername(long userid , String newUsername) throws Exception;
	
	/**
	 * 修改用户标签
	 * @param userid - 用户ID
	 * @param labels - 要添加的标签
	 * @return AgentResult
	 * @throws Exception
	 */
	public void updateUserLabels(long userid , String labels) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param dto - 修改的实体
	 * @throws Exception
	 */
	public void updateUser(UserModifyDto dto) throws Exception;
	
	/**
	 * 修改用户等级
	 * @param userid - 用户ID
	 * @param levelId - 等级ID
	 * @throws Exception
	 */
	public void updateUserLevel(long userid , int levelId) throws Exception;
	
	/**
	 * 升级用户VIP等级
	 * @param userid - 用户ID
	 * @param vip - vip级别,0则取消vip
	 * @param expireDate - 过期时间
	 * @throws Exception
	 */
	public void updateVip(long userid , int vip , Date expireDate) throws Exception;

	/**
	 * 红花弹窗记录
	 * @param userid
	 * @throws Exception
	 */
	public void updateFlowerToasted(long userid) throws Exception;
	/**
	 * 升级用户SVIP等级
	 * @param userid - 用户ID
	 * @throws Exception
	 */
	public void updateSvip(long userid) throws Exception;
	
	/**
	 * 升级用户达人VIP等级
	 * @param userid - 用户ID
	 * @param vip - vip级别,0则取消vip
	 * @param day - 延长时间(天)
	 * @return 达人VIP过期时间
	 * @throws Exception
	 */
	public Date updateTalentVip(long userid , int vip , int day) throws Exception;
	
	/**
	 * 更新用户诚信徽章
	 * @param userId - 用户ID
	 * @param type - 徽章类别
	 * @param expireDate - 过期时间
	 * @throws Exception
	 */
	public void updateHonestyBadge(long userId, BadgeTypeEnum type, Date expireDate) throws Exception;
	
	/**
	 * 用户的视频认证通过保存到视频列表
	 * @param userid - 用户ID
	 * @param video_auth - 认证视频地址
	 * @throws Exception
	 */
	public void updateVideoAuth(long userid , String video_auth) throws Exception;
	
	/**
	 * 修改用户为指定的达人等级
	 * @param userid - 用户ID
	 * @param levelId - 设置为达人等级Id
	 * @throws Exception
	 */
	public void updateWaiter(long userid , int levelId) throws Exception;
	
	/**
	 * 修改用户视频聊天配置开关
	 * @param userid - 用户ID
	 * @param allow - 0-不允许，1-允许
	 * @throws Exception
	 */
	public void updateVideoChat(long userid , int allow) throws Exception;
	
	/**
	 * 修改用户勿扰状态
	 * @param userid - 用户ID
	 * @param  0-不开启勿扰，1-开启全局勿扰
	 * @throws Exception
	 */
	public void updateUserDisturb(long userid , int disturb) throws Exception;
	
	/**
	 * 查封/解封用户
	 * @param userid - 用户ID
	 * @param status - 状态，1正常,0查封
	 * @param memo - 备注
	 * @throws Exception
	 */
	public void changeUserStatus(long userid , int status , String memo) throws Exception;
	
	/**
	 * 查封/解封代理人
	 * @param userid - 用户ID
	 * @param inlet - 代理申请入口  参考ProxyInletEnum 类
	 * @param status - 状态，1正常,0查封
	 * @param memo - 备注
	 * @throws Exception
	 */
	public void changeUserProxy(long userid , Integer inlet, int status , String memo) throws Exception;
	
	/**
	 * 更新用户的华为推送的token值
	 * @param userid
	 * @param token
	 * @throws Exception
	 */
	public void updateUserHuaweiToken(long userid, String token) throws Exception;
	
	/**
	 * 更新用户的华为推送的pushChannel值
	 * @param userid
	 * @param token
	 * @throws Exception
	 */
	public void updateUserPushChannel(long userid, int pushChannel) throws Exception;
	
	/**
	 * 注销用户
	 * @param userid
	 * @param loginbo
	 * @throws Exception
	 */
	public void loginOut(long userid, UserLoginBO loginbo) throws Exception;
	
	/**
	 * 清除指定用户的实例缓存
	 * @param userid - 用户ID
	 */
	public void clearUserCache(long userid);
	
	/**
	 * 订阅push主题
	 * @param oldPlatform - 老的系统平台，如果为空则不解绑
	 * @param oldTopic - 老的主题，如果为空则不解绑
	 * @param newPlatform - 新的系统平台
	 * @param newTopic - 新的主题
	 * @param clientList - 用户client集合
	 */
	public void subscribePushTopicByAlias(Integer oldPlatform , String oldTopic , int newPlatform , String newTopic , List<String> clientList);

	/**
	 * 记录可拨打用户
	 * @param userId
	 * @throws Exception
	 */
	public void saveUserCanCallLog(long userId) throws Exception;

}
