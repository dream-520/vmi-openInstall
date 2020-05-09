package com.tigerjoys.shark.miai.agent.dto;

import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.context.UserDetails;
import com.tigerjoys.shark.miai.agent.enums.BadgeTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserSVIPLevelEnum;

/**
 * 用户信息DTO
 * @author chengang
 *
 */
public class UserBO implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5757834390785677285L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * idcard
	 */
	private int idcard;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * QQ
	 */
	private String qq;
	
	/**
	 * 微信
	 */
	private String weixin;
	
	/**
	 * 联系方式状态：1显示,2不显示
	 */
	private Integer contactStatus;
	
	/**
	 * 联系方式文本
	 */
	private String contactText;
	
	/**
	 * 用户头像(大图)
	 */
	private String photo;
	
	/**
	 * 性别，1男，2女
	 */
	private int sex;
	
	/**
	 * 状态，1正常,0查封,-1注销
	 */
	private int status;
	
	/**
	 * 注册方式,0系统注册
	 */
	private int fr;
	
	/**
	 * 国家ID
	 */
	private long countryid;
	
	/**
	 * 省份ID
	 */
	private long provinceid;
	
	/**
	 * 城市ID
	 */
	private long cityid;
	
	/**
	 * 用户等级，默认为1
	 */
	private int degreeid;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 个性签名
	 */
	private String introduce;
	
	/**
	 * 生日
	 */
	private Date birthday;
	
   /**
    * 网易注册token
    */
	private String imToken;
	
	/**
	 * 用户最后登录设备号
	 */
	private String clientid;
	
	/**
	 * 最后登录的平台，0未知,1And,2IOS
	 */
	private int platform;
	
	/**
	 * 用户的unique token值
	 */
	private String uniqueKey;
	
	/**
	 * 用户最后登录时间
	 */
	private Date lastLoginDate;
	
	/**
	 * VIP等级，默认0，判断方法查看 vipValue
	 */
	private int vip;
	
	/**
	 * VIP到期时间
	 */
	private Date vipExpire;
	
	/**
	 * 高级VIP等级
	 * @see UserSVIPLevelEnum
	 */
	private int svip;
	
	/**
	 * 形象视频地址
	 */
	private String videoAuth;
	
	/**
	 * 形象视频图片地址
	 */
	private String videoAuthPic;
	
	/**
	 * 是否达人VIP，判断方法查看 talentVipValue
	 */
	private int talentVip;
	
	/**
	 * 达人VIP到期时间
	 */
	private Date talentVipExpire;
	
	/**
	 * 诚信徽章类别，0-表示没有，其他参考@see {@link BadgeTypeEnum}
	 */
	private int honestyBadge;
	
	/**
	 * 诚信徽章到期时间
	 */
	private Date honestyBadgeExpire;
	
	/**
	 * 是否是服务者
	 */
	private boolean waiter;
	
	/**
	 * 达人等级/服务者等级ID
	 */
	private int waiterLevelId;
	
	/**
	 * 用户注册日期
	 */
	private Date createTime;
	
	/**
	 * 用户信息更新信息
	 */
	private Date updateTime;
	
	/**
	 * 用户置顶时间
	 */
	private Date stickTime;
	
	/**
	 * 用户背景图片
	 */
	private String bgPicture;
	
	/**
	 * 小红花弹窗，只弹一次
	 */
	private int flowerToasted;
	
	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 视频聊天配置开关，0-关闭、1-打开
	 */
	private int allowVideoChat;
	
	/**
	 * 用户勿扰状态 0-不开启勿扰，1-开启全局勿扰
	 */
	private int disturb;
	
	/**
	 * 推送渠道
	 */
	private Integer pushchannel;
	
	/**
	 * 华为推送
	 */
	private String huaweiToken;
	
	/**
	 * 标签
	 */
	private String labels;

	/**
	 * 包名
	 */
	private String channel;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
	}

	public long getCityid() {
		return cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public String getImToken() {
		return imToken;
	}

	public void setImToken(String imToken) {
		this.imToken = imToken;
	}

	public int getDegreeid() {
		return degreeid;
	}

	public void setDegreeid(int degreeid) {
		this.degreeid = degreeid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String getMobile() {
		return mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(Integer contactStatus) {
		this.contactStatus = contactStatus;
	}

	@Override
	public boolean isEnabled() {
		return status == 1;
	}

	public int getIdcard() {
		return idcard;
	}

	public void setIdcard(int idcard) {
		this.idcard = idcard;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public long getCountryid() {
		return countryid;
	}

	public void setCountryid(long countryid) {
		this.countryid = countryid;
	}

	public long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(long provinceid) {
		this.provinceid = provinceid;
	}

	public String getVideoAuth() {
		return videoAuth;
	}

	public void setVideoAuth(String videoAuth) {
		this.videoAuth = videoAuth;
	}

	public String getVideoAuthPic() {
		return videoAuthPic;
	}

	public void setVideoAuthPic(String videoAuthPic) {
		this.videoAuthPic = videoAuthPic;
	}

	public int getTalentVip() {
		return talentVip;
	}

	public void setTalentVip(int talentVip) {
		this.talentVip = talentVip;
	}

	public Date getVipExpire() {
		return vipExpire;
	}

	public void setVipExpire(Date vipExpire) {
		this.vipExpire = vipExpire;
	}

	public Date getTalentVipExpire() {
		return talentVipExpire;
	}

	public void setTalentVipExpire(Date talentVipExpire) {
		this.talentVipExpire = talentVipExpire;
	}
	
	public int getHonestyBadge() {
		return honestyBadge;
	}

	public void setHonestyBadge(int honestyBadge) {
		this.honestyBadge = honestyBadge;
	}

	public Date getHonestyBadgeExpire() {
		return honestyBadgeExpire;
	}

	public void setHonestyBadgeExpire(Date honestyBadgeExpire) {
		this.honestyBadgeExpire = honestyBadgeExpire;
	}

	/**
	 * 判断用户是否是VIP
	 * @return int
	 */
	public int vipValue(){
		if(vip <= 0) {
			return 0;
		} else {
			long t = Tools.dateValue(vipExpire);
			if(t < System.currentTimeMillis()) {
				return 0;
			}
			/*
			if(t < Tools.getDayTime()) {
				return 0;
			}
			*/
		}
		
		return vip;
	}
	
	/**
	 * 判断用户是否是达人VIP
	 * @return boolean
	 */
	public int talentVipValue(){
		if(talentVip <= 0) {
			return 0;
		} else {
			long t = Tools.dateValue(talentVipExpire);
			if(t < System.currentTimeMillis()) {
				return 0;
			}
		}
		
		return talentVip;
	}
	
	/**
	 * 判断用户是否有诚信徽章
	 * @return int
	 */
	public int honestyBadgeValue(){
		if(honestyBadge <= BadgeTypeEnum.NO_PROMISE.getCode()) {
			return 0;
		} else {
			long t = Tools.dateValue(honestyBadgeExpire);
			if(t < System.currentTimeMillis()) {
				return 0;
			}
		}
		
		return honestyBadge;
	}

	public boolean isWaiter() {
		return waiter;
	}

	public void setWaiter(boolean waiter) {
		this.waiter = waiter;
	}

	public int getWaiterLevelId() {
		return waiterLevelId;
	}

	public void setWaiterLevelId(int waiterLevelId) {
		this.waiterLevelId = waiterLevelId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getStickTime() {
		return stickTime;
	}

	public void setStickTime(Date stickTime) {
		this.stickTime = stickTime;
	}

	public String getBgPicture() {
		return bgPicture;
	}

	public void setBgPicture(String bgPicture) {
		this.bgPicture = bgPicture;
	}

	public int getAllowVideoChat() {
		return allowVideoChat;
	}

	public void setAllowVideoChat(int allowVideoChat) {
		this.allowVideoChat = allowVideoChat;
	}

	public int getSvip() {
		return svip;
	}

	public void setSvip(int svip) {
		this.svip = svip;
	}

	public String getContactText() {
		return contactText;
	}

	public void setContactText(String contactText) {
		this.contactText = contactText;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getFlowerToasted() {
		return flowerToasted;
	}

	public void setFlowerToasted(int flowerToasted) {
		this.flowerToasted = flowerToasted;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getDisturb() {
		return disturb;
	}

	public void setDisturb(int disturb) {
		this.disturb = disturb;
	}

	public Integer getPushchannel() {
		return pushchannel;
	}

	public void setPushchannel(Integer pushchannel) {
		this.pushchannel = pushchannel;
	}

	public String getHuaweiToken() {
		return huaweiToken;
	}

	public void setHuaweiToken(String huaweiToken) {
		this.huaweiToken = huaweiToken;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
