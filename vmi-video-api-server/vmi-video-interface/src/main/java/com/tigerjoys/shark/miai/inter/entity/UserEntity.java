package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户基础表[t_user] 表对应的实体类
 * @author lipeng
 * @Date 2019-06-26 18:26:25
 *
 */
@Table(name="t_user")
public class UserEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id(increment=false)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	/**
	 * 用户名
	 */
	@Column(name="username",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户名")
	private String username;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * idcard
	 */
	@Column(name="idcard",nullable=false,jdbcType=JdbcType.INTEGER,comment="idcard")
	private Integer idcard;
	
	/**
	 * 密码
	 */
	@Column(name="password",nullable=false,jdbcType=JdbcType.CHAR,comment="密码")
	private String password;
	
	/**
	 * 手机号码
	 */
	@Column(name="mobile",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机号码")
	private String mobile;
	
	/**
	 * QQ
	 */
	@Column(name="qq",nullable=true,jdbcType=JdbcType.VARCHAR,comment="QQ")
	private String qq;
	
	/**
	 * 微信
	 */
	@Column(name="weixin",nullable=true,jdbcType=JdbcType.VARCHAR,comment="微信")
	private String weixin;
	
	/**
	 * 联系方式状态：1显示,2不显示
	 */
	@Column(name="contact_status",nullable=false,jdbcType=JdbcType.TINYINT,comment="联系方式状态：1显示,2不显示")
	private Integer contact_status;
	
	/**
	 * 联系方式文本
	 */
	@Column(name="concat_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="联系方式文本")
	private String concat_text;
	
	/**
	 * 用户头像
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户头像")
	private String photo;
	
	/**
	 * 唯一标识
	 */
	@Column(name="unique_key",nullable=false,jdbcType=JdbcType.CHAR,comment="唯一标识")
	private String unique_key;
	
	/**
	 * 1:男   2：女
	 */
	@Column(name="sex",nullable=false,jdbcType=JdbcType.TINYINT,comment="1:男   2：女")
	private Integer sex;
	
	/**
	 * 1=正常;0=停用;（默认启用）
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1=正常;0=停用;（默认启用）")
	private Integer status;
	
	/**
	 * 注册方式
	 */
	@Column(name="fr",nullable=false,jdbcType=JdbcType.TINYINT,comment="注册方式")
	private Integer fr;
	
	/**
	 * 最后活动时间
	 */
	@Column(name="refresh_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后活动时间")
	private Date refresh_time;
	
	/**
	 * 所在国家ID
	 */
	@Column(name="countryid",nullable=false,jdbcType=JdbcType.BIGINT,comment="所在国家ID")
	private Long countryid;
	
	/**
	 * 所在省份ID
	 */
	@Column(name="provinceid",nullable=false,jdbcType=JdbcType.BIGINT,comment="所在省份ID")
	private Long provinceid;
	
	/**
	 * 所在城市ID
	 */
	@Column(name="cityid",nullable=false,jdbcType=JdbcType.BIGINT,comment="所在城市ID")
	private Long cityid;
	
	/**
	 * 用户等级ID
	 */
	@Column(name="degreeid",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用户等级ID")
	private Integer degreeid;
	
	/**
	 * 生日
	 */
	@Column(name="birthday",nullable=true,jdbcType=JdbcType.DATE,comment="生日")
	private Date birthday;
	
	/**
	 * 个性签名
	 */
	@Column(name="signature",nullable=true,jdbcType=JdbcType.VARCHAR,comment="个性签名")
	private String signature;
	
	/**
	 * 个人介绍
	 */
	@Column(name="introduce",nullable=true,jdbcType=JdbcType.VARCHAR,comment="个人介绍")
	private String introduce;
	
	/**
	 * NETEASE聊天注册授权
	 */
	@Column(name="im_token",nullable=true,jdbcType=JdbcType.VARCHAR,comment="NETEASE聊天注册授权")
	private String im_token;
	
	/**
	 * 最后一次登录的设备ID
	 */
	@Column(name="clientid",nullable=true,jdbcType=JdbcType.CHAR,comment="最后一次登录的设备ID")
	private String clientid;
	
	/**
	 * 最后一次登录的平台,0未知,1And,2IOS
	 */
	@Column(name="platform",nullable=false,jdbcType=JdbcType.TINYINT,comment="最后一次登录的平台,0未知,1And,2IOS")
	private Integer platform;
	
	/**
	 * VIP等级,默认0
	 */
	@Column(name="vip",nullable=false,jdbcType=JdbcType.TINYINT,comment="VIP等级,默认0")
	private Integer vip;
	
	/**
	 * vip到期时间
	 */
	@Column(name="vip_expire",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="vip到期时间")
	private Date vip_expire;
	
	/**
	 * 高级会员等级,默认0
	 */
	@Column(name="svip",nullable=false,jdbcType=JdbcType.TINYINT,comment="高级会员等级,默认0")
	private Integer svip;
	
	/**
	 * 达人VIP
	 */
	@Column(name="talent_vip",nullable=false,jdbcType=JdbcType.TINYINT,comment="达人VIP")
	private Integer talent_vip;
	
	/**
	 * 达人VIP到期时间
	 */
	@Column(name="talent_vip_expire",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="达人VIP到期时间")
	private Date talent_vip_expire;
	
	/**
	 * 诚信徽章
	 */
	@Column(name="honesty_badge",nullable=false,jdbcType=JdbcType.TINYINT,comment="诚信徽章")
	private Integer honesty_badge;
	
	/**
	 * 诚信徽章到期时间
	 */
	@Column(name="honesty_badge_expire",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="诚信徽章到期时间")
	private Date honesty_badge_expire;
	
	/**
	 * 认证视频
	 */
	@Column(name="video_auth",nullable=true,jdbcType=JdbcType.VARCHAR,comment="认证视频")
	private String video_auth;
	
	/**
	 * 认证视频展示图片
	 */
	@Column(name="video_auth_pic",nullable=true,jdbcType=JdbcType.VARCHAR,comment="认证视频展示图片")
	private String video_auth_pic;
	
	/**
	 * 是否代理人 0 不是代理人 1是代理人
	 */
	@Column(name="proxy",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否代理人 0 不是代理人 1是代理人")
	private Integer proxy;
	
	/**
	 * 服务者等级,0普通用户
	 */
	@Column(name="waiter",nullable=false,jdbcType=JdbcType.TINYINT,comment="服务者等级,0普通用户")
	private Integer waiter;
	
	/**
	 * 用户置顶时间
	 */
	@Column(name="stick_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="用户置顶时间")
	private Date stick_time;
	
	/**
	 * 用户背景图片
	 */
	@Column(name="bg_picture",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户背景图片")
	private String bg_picture;
	
	/**
	 * 视频聊天配置开关，0-关闭、1-打开
	 */
	@Column(name="allow_video_chat",nullable=false,jdbcType=JdbcType.TINYINT,comment="视频聊天配置开关，0-关闭、1-打开")
	private Integer allow_video_chat;
	
	/**
	 * 评分
	 */
	@Column(name="star",nullable=true,jdbcType=JdbcType.TINYINT,comment="评分")
	private Integer star;
	
	/**
	 * 小红花弹窗，只弹一次
	 */
	@Column(name="flower_toasted",nullable=true,jdbcType=JdbcType.TINYINT,comment="小红花弹窗，只弹一次")
	private Integer flower_toasted;
	
	/**
	 * 用户是否具备机会 0 有机会 1 无机会 2 以充值
	 */
	@Column(name="opportunity",nullable=true,jdbcType=JdbcType.TINYINT,comment="用户是否具备机会 0 有机会 1 无机会 2 以充值")
	private Integer opportunity;
	
	/**
	 * 推送渠道标识 0默认小米  1华为 2 vivo
	 */
	@Column(name="push_channel",nullable=true,jdbcType=JdbcType.TINYINT,comment="推送渠道标识 0默认小米  1华为 2 vivo")
	private Integer push_channel;
	
	/**
	 * 华为渠道的推送标识
	 */
	@Column(name="huawei_token",nullable=true,jdbcType=JdbcType.VARCHAR,comment="华为渠道的推送标识")
	private String huawei_token;
	
	/**
	 * 用户勿扰标识 0 不启动勿扰 1 启动全局勿扰
	 */
	@Column(name="disturb",nullable=true,jdbcType=JdbcType.TINYINT,comment="用户勿扰标识 0 不启动勿扰 1 启动全局勿扰")
	private Integer disturb;
	
	/**
	 * 华为渠道的推送标识
	 */
	@Column(name="labels",nullable=true,jdbcType=JdbcType.VARCHAR,comment="标签")
	private String labels;
	
	/**
	 * 华为渠道的推送标识
	 */
	@Column(name="channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="渠道名称")
	private String channel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
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
	
	public Integer getIdcard() {
		return idcard;
	}

	public void setIdcard(Integer idcard) {
		this.idcard = idcard;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public String getConcat_text() {
		return concat_text;
	}

	public void setConcat_text(String concat_text) {
		this.concat_text = concat_text;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getUnique_key() {
		return unique_key;
	}

	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getFr() {
		return fr;
	}

	public void setFr(Integer fr) {
		this.fr = fr;
	}
	
	public Date getRefresh_time() {
		return refresh_time;
	}

	public void setRefresh_time(Date refresh_time) {
		this.refresh_time = refresh_time;
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
	
	public Integer getDegreeid() {
		return degreeid;
	}

	public void setDegreeid(Integer degreeid) {
		this.degreeid = degreeid;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getIm_token() {
		return im_token;
	}

	public void setIm_token(String im_token) {
		this.im_token = im_token;
	}
	
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	
	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}
	
	public Date getVip_expire() {
		return vip_expire;
	}

	public void setVip_expire(Date vip_expire) {
		this.vip_expire = vip_expire;
	}
	
	public Integer getSvip() {
		return svip;
	}

	public void setSvip(Integer svip) {
		this.svip = svip;
	}
	
	public Integer getTalent_vip() {
		return talent_vip;
	}

	public void setTalent_vip(Integer talent_vip) {
		this.talent_vip = talent_vip;
	}
	
	public Date getTalent_vip_expire() {
		return talent_vip_expire;
	}

	public void setTalent_vip_expire(Date talent_vip_expire) {
		this.talent_vip_expire = talent_vip_expire;
	}
	
	public Integer getHonesty_badge() {
		return honesty_badge;
	}

	public void setHonesty_badge(Integer honesty_badge) {
		this.honesty_badge = honesty_badge;
	}
	
	public Date getHonesty_badge_expire() {
		return honesty_badge_expire;
	}

	public void setHonesty_badge_expire(Date honesty_badge_expire) {
		this.honesty_badge_expire = honesty_badge_expire;
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
	
	public Integer getProxy() {
		return proxy;
	}

	public void setProxy(Integer proxy) {
		this.proxy = proxy;
	}
	
	public Integer getWaiter() {
		return waiter;
	}

	public void setWaiter(Integer waiter) {
		this.waiter = waiter;
	}
	
	public Date getStick_time() {
		return stick_time;
	}

	public void setStick_time(Date stick_time) {
		this.stick_time = stick_time;
	}
	
	public String getBg_picture() {
		return bg_picture;
	}

	public void setBg_picture(String bg_picture) {
		this.bg_picture = bg_picture;
	}
	
	public Integer getAllow_video_chat() {
		return allow_video_chat;
	}

	public void setAllow_video_chat(Integer allow_video_chat) {
		this.allow_video_chat = allow_video_chat;
	}
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	public Integer getFlower_toasted() {
		return flower_toasted;
	}

	public void setFlower_toasted(Integer flower_toasted) {
		this.flower_toasted = flower_toasted;
	}
	
	public Integer getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Integer opportunity) {
		this.opportunity = opportunity;
	}
	
	public Integer getPush_channel() {
		return push_channel;
	}

	public void setPush_channel(Integer push_channel) {
		this.push_channel = push_channel;
	}
	
	public String getHuawei_token() {
		return huawei_token;
	}

	public void setHuawei_token(String huawei_token) {
		this.huawei_token = huawei_token;
	}
	
	public Integer getDisturb() {
		return disturb;
	}

	public void setDisturb(Integer disturb) {
		this.disturb = disturb;
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