package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户修改传递的DTO
 * @author chengang
 *
 */
public class UserModifyDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2845656347301643752L;

	/**
	 * 用户ID
	 */
	private long userid;
	
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	/**
	 * 用户头像
	 */
	private String bgPicture;
	
	/**
	 * 性别，1男，2女
	 */
	private Integer sex;
	
	/**
	 * 性别，1男，2女
	 */
	private Integer Status;
	
	/**
	 * 国家ID
	 */
	private Long countryid;
	
	/**
	 * 省份ID
	 */
	private Long provinceid;
	
	/**
	 * 城市ID
	 */
	private Long cityid;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 个人介绍
	 */
	private String introduce;
	
	/**
	 * 生日
	 */
	private Date birthday;
	
	/**
	 * 最后登录的设备ID
	 */
	private String clientid;
	
	/**
	 * 登录的平台,0未知,1And,2IOS
	 */
	private Integer platform;
	
	/**
	 * 最后活动时间
	 */
	private Date refresh_time;
	
	/**
	 * 唯一key修改，用户登录的时候刷新key
	 */
	private String unique_key;
	
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
	private Integer contact_status;
	
	/**
	 * 认证视频
	 */
	private String video_auth;
	
	/**
	 * 认证视频图片地址
	 */
	private String video_auth_pic;
	
	/**
	 * app版本号，注册或者登录的时候传入
	 */
	private String appVersion;
	
	/**
	 * app编号，注册或者登录的时候传入
	 */
	private Integer versionCode;
	
	/**
	 * 更新置顶排序时间
	 */
	private Date stickTime;
	
	/**
	 * 联系方式文本，如果设置此值，则不会将qq,weixin,mobile自动加入此字段中
	 */
	private String concat_text;
	
	/**
	 * 包名
	 */
	private String packageName;
	
	/**
	 * 推送渠道
	 */
	private Integer pushchannel;
	
	/**
	 * 华为推送
	 * @return
	 */
	private String huaweiToken;
	
	/**
	 * 用户标签
	 * @return
	 */
	private String labels;
	
	/**
	 * 渠道
	 */
	private String channel;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getCountryid() {
		return countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	public Long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Long provinceid) {
		this.provinceid = provinceid;
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
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

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public Date getRefresh_time() {
		return refresh_time;
	}

	public void setRefresh_time(Date refresh_time) {
		this.refresh_time = refresh_time;
	}

	public String getUnique_key() {
		return unique_key;
	}

	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Integer getContact_status() {
		return contact_status;
	}

	public void setContact_status(Integer contact_status) {
		this.contact_status = contact_status;
	}

	public String getVideo_auth() {
		return video_auth;
	}

	public void setVideo_auth(String video_auth) {
		this.video_auth = video_auth;
	}

	public String getVideo_auth_pic() {
		return video_auth_pic;
	}

	public void setVideo_auth_pic(String video_auth_pic) {
		this.video_auth_pic = video_auth_pic;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
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

	public String getConcat_text() {
		return concat_text;
	}

	public void setConcat_text(String concat_text) {
		this.concat_text = concat_text;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}
	
}
