package com.tigerjoys.shark.miai.agent.dto.transfer;

import java.io.Serializable;
import java.util.Date;

/**
 * 新建用户使用的DTO
 * @author lipeng
 *
 */
/**
 * @author lipeng
 *
 */
public class UserCreatDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2254937776083010503L;
	
	/**
	 * 包名
	 */
	private String packageName;
	
	/**
	 * 昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	/**
	 * 性别，1男，2女
	 */
	private Integer sex;
	
	/**
	 * 国家ID
	 */
	private long countryid;
	
	/**
	 * 省份ID
	 */
	private long provinceid;
	
	/**
	 * 城市名称
	 */
	private long cityid;
	
	/**
	 * 用户等级，默认为1
	 */
	private Integer degreeid;
	
	/**
	 * 个性签名
	 */
	private String signature;
	
	/**
	 * 用户openid，如果是第三方注册的，必传
	 */
	private String openid;
	
	/**
	 * 注册方式,0未知 ，1微信， 2QQ，3手机，4客服注册，9机器人
	 */
	private int fr;
	
	/**
	 * 生日
	 */
	private Date birthday;
	
	/**
	 * 设备ID
	 */
	private String clientid;
	
	/**
	 * 登录的平台,0未知,1And,2IOS
	 */
	private int platform;
	
	/**
	 * 邀请码
	 */
	private String inviteCode;
	
	/**
	 * 经度
	 */
	private Double lng;
	
	/**
	 * 纬度
	 */
	private Double lat;
	
	/**
	 * app版本号，注册或者登录的时候传入
	 */
	private String appVersion;
	
	/**
	 * app编号，注册或者登录的时候传入
	 */
	private Integer versionCode;
	
	/**
	 * 对性的看法
	 */
	private int sexOpinion;
	
	/**
	 * 对另一半的看法
	 */
	private int spouseOpinion;
	
	/**
	 * 交友目地
	 */
	private int makeFriend;
	
	/**
	 * 推送渠道
	 */
	private Integer pushchannel;
	
	/**
	 * 华为推送token
	 * @return
	 */
	private String huaweiToken;
	
	/**
	 * 渠道
	 * @return
	 */
	private String channel;
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public long getCityid() {
		return cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public Integer getDegreeid() {
		return degreeid;
	}

	public void setDegreeid(Integer degreeid) {
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
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

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
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

	public int getSexOpinion() {
		return sexOpinion;
	}

	public void setSexOpinion(int sexOpinion) {
		this.sexOpinion = sexOpinion;
	}

	public int getSpouseOpinion() {
		return spouseOpinion;
	}

	public void setSpouseOpinion(int spouseOpinion) {
		this.spouseOpinion = spouseOpinion;
	}

	public int getMakeFriend() {
		return makeFriend;
	}

	public void setMakeFriend(int makeFriend) {
		this.makeFriend = makeFriend;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
