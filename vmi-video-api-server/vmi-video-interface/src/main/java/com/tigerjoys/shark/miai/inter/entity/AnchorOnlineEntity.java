package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  大v信息表[t_anchor_online] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-20 18:04:09
 *
 */
@Table(name="t_anchor_online")
public class AnchorOnlineEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标识
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标识")
	private Long id;
	
	/**
	 * 用户id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 每分钟价格
	 */
	@Column(name="price",nullable=false,jdbcType=JdbcType.INTEGER,comment="每分钟价格")
	private Integer price;
	
	/**
	 * 用户所处星级
	 */
	@Column(name="star",nullable=true,jdbcType=JdbcType.SMALLINT,comment="用户所处星级")
	private Integer star;
	
	/**
	 * 用户在线状态
	 */
	@Column(name="online",nullable=true,jdbcType=JdbcType.SMALLINT,comment="用户在线状态")
	private Integer online;
	
	/**
	 * 用户可用状态
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用户可用状态")
	private Integer state;
	
	/**
	 * 用户申请成为主播的时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="用户申请成为主播的时间")
	private Date create_time;
	
	/**
	 * 用户状态更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="用户状态更新时间")
	private Date update_time;
	
	/**
	 * 用户审核通过的时间
	 */
	@Column(name="audit_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="用户审核通过的时间")
	private Date audit_time;
	
	/**
	 * 审核评论内容
	 */
	@Column(name="audit_memo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="审核评论内容")
	private String audit_memo;
	
	/**
	 * 用于标识当前主播是否是机器人
	 */
	@Column(name="flag",nullable=true,jdbcType=JdbcType.SMALLINT,comment="用于标识当前主播是否是机器人")
	private Integer flag;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=true,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 手机号码
	 */
	@Column(name="mobile",nullable=true,jdbcType=JdbcType.VARCHAR,comment="手机号码")
	private String mobile;
	
	/**
	 * 所在城市ID
	 */
	@Column(name="cityid",nullable=true,jdbcType=JdbcType.BIGINT,comment="所在城市ID")
	private Long cityid;
	
	/**
	 * 用户头像
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="用户头像")
	private String photo;
	
	/**
	 * 个性签名
	 */
	@Column(name="signature",nullable=true,jdbcType=JdbcType.VARCHAR,comment="个性签名")
	private String signature;
	
	/**
	 * 个人介绍
	 */
	@Column(name="intro",nullable=true,jdbcType=JdbcType.VARCHAR,comment="个人介绍")
	private String intro;
	
	/**
	 * 身高
	 */
	@Column(name="stature",nullable=true,jdbcType=JdbcType.VARCHAR,comment="身高")
	private String stature;
	
	/**
	 * 体重
	 */
	@Column(name="weight",nullable=true,jdbcType=JdbcType.VARCHAR,comment="体重")
	private String weight;
	
	/**
	 * 星座
	 */
	@Column(name="zodiac",nullable=true,jdbcType=JdbcType.VARCHAR,comment="星座")
	private String zodiac;
	
	/**
	 * 形象标签
	 */
	@Column(name="label",nullable=true,jdbcType=JdbcType.VARCHAR,comment="形象标签")
	private String label;
	
	/**
	 * 转盘限制
	 */
	@Column(name="turntable",nullable=true,jdbcType=JdbcType.VARCHAR,comment="转盘限制")
	private String turntable;
	
	/**
	 * 用户拨打本大v总次数
	 */
	@Column(name="dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨打本大v总次数")
	private Integer dial_num;
	
	/**
	 * 用户拨打本大v总次数
	 */
	@Column(name="user_dial_controll",nullable=true,jdbcType=JdbcType.INTEGER,comment="0 全用户 1 非充值用户 2充值用户")
	private Integer user_dial_controll;
	
	/**
	 * 用户成功拨打本大v总次数
	 */
	@Column(name="recv_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户成功拨打本大v总次数")
	private Integer recv_num;
	
	/**
	 * 通话结束用户评论本大v总次数
	 */
	@Column(name="evaluation_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="通话结束用户评论本大v总次数")
	private Integer evaluation_num;
	
	/**
	 * 用户拨打音频总次数
	 */
	@Column(name="audio_dial_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户拨打音频总次数")
	private Integer audio_dial_num;
	
	/**
	 * 用户成功拨打音频总次数
	 */
	@Column(name="audio_recv_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户成功拨打音频总次数")
	private Integer audio_recv_num;
	
	/**
	 * 通话结束用户评论音频总次数
	 */
	@Column(name="audio_evaluation_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="通话结束用户评论音频总次数")
	private Integer audio_evaluation_num;
	
	/**
	 * 昨天接听率
	 */
	@Column(name="avg_yesterday_recv",nullable=true,jdbcType=JdbcType.DECIMAL,comment="昨天接听率")
	private Double avg_yesterday_recv;
	
	/**
	 * 主播分成比例
	 */
	@Column(name="ratio",nullable=true,jdbcType=JdbcType.FLOAT,comment="主播分成比例")
	private Float ratio;
	
	/**
	 * 主用户ID，非0为假主播
	 */
	@Column(name="parent_userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主用户ID，非0为假主播")
	private Long parent_userid;
	
	/**
	 * 单独添加的勿扰状态位(0未开启勿扰 1开启勿扰)
	 */
	@Column(name="disturb",nullable=true,jdbcType=JdbcType.SMALLINT,comment="单独添加的勿扰状态位(0未开启勿扰 1开启勿扰)")
	private Integer disturb;
	
	/**
	 * 大V助手限制 0 新老用户 1 新用户 2 老用户
	 */
	@Column(name="dial_helper",nullable=true,jdbcType=JdbcType.TINYINT,comment="大V助手限制 0 新老用户 1 新用户 2 老用户")
	private Integer dial_helper;
	
	/**
	 * 大V助手限制拨打次数
	 */
	@Column(name="dial_helper_num",nullable=true,jdbcType=JdbcType.SMALLINT,comment="大V助手限制拨打次数")
	private Integer dial_helper_num;
	
	/**
	 * 大V助手限制音频拨打次数
	 */
	@Column(name="dial_helper_audio_num",nullable=true,jdbcType=JdbcType.SMALLINT,comment="大V助手限制音频拨打次数")
	private Integer dial_helper_audio_num;
	
	/**
	 * 新人标识 1新人
	 */
	@Column(name="freshman",nullable=true,jdbcType=JdbcType.SMALLINT,comment="新人标识 1新人")
	private Integer freshman;
	
	/**
	 * 推荐标识 1推荐
	 */
	@Column(name="nominate",nullable=true,jdbcType=JdbcType.SMALLINT,comment="推荐标识 1推荐")
	private Integer nominate;
	
	/**
	 * 主播排序优先级
	 */
	@Column(name="priority",nullable=true,jdbcType=JdbcType.SMALLINT,comment="主播排序优先级")
	private Integer priority;
	
	/**
	 * 是否是免费主播
	 */
	@Column(name="free",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否是免费主播")
	private Integer free;
	
	/**
	 * 主播城市隔离
	 */
	@Column(name="segregation",nullable=true,jdbcType=JdbcType.VARCHAR,comment="主播城市隔离")
	private String segregation;
	
	/**
	 * 音频价格
	 */
	@Column(name="audio_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="音频价格")
	private Integer audio_price;
	
	/**
	 * 音频分成
	 */
	@Column(name="audio_ratio",nullable=true,jdbcType=JdbcType.FLOAT,comment="音频分成")
	private Float audio_ratio;
	
	/**
	 * 礼物分成
	 */
	@Column(name="gift_ratio",nullable=true,jdbcType=JdbcType.FLOAT,comment="礼物分成")
	private Float gift_ratio;
	
	/**
	 * 短信分成
	 */
	@Column(name="msg_ratio",nullable=true,jdbcType=JdbcType.INTEGER,comment="短信分成")
	private Integer msg_ratio;
	
	/**
	 * 服务者类型 0 视频 1 音频 2 全类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="服务者类型 0 视频 1 音频 2 全类型")
	private Integer type;
	
	/**
	 * 录制音频的时间
	 */
	@Column(name="audio_time",nullable=true,jdbcType=JdbcType.INTEGER,comment="录制音频的时间")
	private Integer audio_time;
	
	/**
	 * 录制音频的路径
	 */
	@Column(name="audio_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="录制音频的路径")
	private String audio_path;
	
	/**
	 * 视频主播标识
	 */
	@Column(name="video_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="视频主播标识")
	private Integer video_type;
	
	/**
	 * 音频主播标识
	 */
	@Column(name="audio_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="音频主播标识")
	private Integer audio_type;
	
	/**
	 * 根据包名显示字段 0 全部 1 密聊 2其他
	 */
	@Column(name="app",nullable=true,jdbcType=JdbcType.TINYINT,comment="根据包名显示字段 0 全部 1 密聊 2其他")
	private Integer app;
	
	/**
	 * 主播禁言标识 0 不禁言 1 禁言
	 */
	@Column(name="silent",nullable=true,jdbcType=JdbcType.TINYINT,comment="主播禁言标识 0 不禁言 1 禁言")
	private Integer silent;
	
	/**
	 * 主播新老用户首页展示标识 0 都展示 1新用户展示 2 老用户展示
	 */
	@Column(name="index_show",nullable=true,jdbcType=JdbcType.TINYINT,comment="主播新老用户首页展示标识 0 都展示 1新用户展示 2 老用户展示")
	private Integer index_show;
	
	/**
	 * 新老用户拨打控制 0 都可拨打 1 新用户 2老用户
	 */
	@Column(name="dial_controll",nullable=true,jdbcType=JdbcType.TINYINT,comment="新老用户拨打控制 0 都可拨打 1 新用户 2老用户")
	private Integer dial_controll;
	
	/**
	 * 用户充值等级限制 
	 */
	@Column(name="dial_charge_user_level",nullable=true,jdbcType=JdbcType.INTEGER,comment="用户充值等级限制 ")
	private Integer dial_charge_user_level;
	
	/**
	 * 标签标识
	 */
	@Column(name="tag",nullable=true,jdbcType=JdbcType.INTEGER,comment="标签标识")
	private Integer tag;
	
	/**
	 * 颜值专栏条目
	 */
	@Column(name="appearance",nullable=true,jdbcType=JdbcType.TINYINT,comment="颜值专栏条目")
	private Integer appearance;
	
	/**
	 * 是否显示提现与收益  0 不显示 1 显示
	 */
	@Column(name="show_income",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否显示提现与收益  0 不显示 1 显示")
	private Integer show_income;
	
	/**
	 * 是否进行抵扣 0 不抵扣 1 抵扣
	 */
	@Column(name="deduct",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否进行抵扣 0 不抵扣 1 抵扣")
	private Integer deduct;
	
	/**
	 * 奖励方案 0 原始奖励方案  1 新奖励方案  2 不奖励
	 */
	@Column(name="award",nullable=true,jdbcType=JdbcType.TINYINT,comment="奖励方案 0 原始奖励方案  1 新奖励方案  2 不奖励")
	private Integer award;
	
	/**
	 * 是否10s 结算  0 不进行  1进行
	 */
	@Column(name="settlement",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否10s 结算  0 不进行  1进行")
	private Integer settlement;
	
	/**
	 * 工会ID 
	 */
	@Column(name="labor_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="工会ID ")
	private Long labor_id;
	
	/**
	 * 主播每日魅力值记录字段
	 */
	@Column(name="charm",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播每日魅力值记录字段")
	private Integer charm;
	
	/**

	 * 主播是否安全 0 安全 1 不安全
	 */
	@Column(name="anchor_safe",nullable=true,jdbcType=JdbcType.TINYINT,comment="主播是否安全 0 安全 1 不安全")
	private Integer anchor_safe;
	/**
	 * 主播实际视频扣费价格
	 */
	@Column(name="anchor_video_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播实际视频扣费价格")
	private Integer anchor_video_price;
	
	/**
	 * 主播实际音频扣费价格
	 */
	@Column(name="anchor_audio_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="主播实际音频扣费价格")
	private Integer anchor_audio_price;
	
	/**
	 * 原始视频展示价格
	 */
	@Column(name="original_video_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="原始视频展示价格")
	private Integer original_video_price;
	
	/**
	 * 原始音频展示价格
	 */
	@Column(name="original_audio_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="原始音频展示价格")
	private Integer original_audio_price;
	
	/**
	 * original_anchor_video_price
	 */
	@Column(name="original_anchor_video_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="original_anchor_video_price")
	private Integer original_anchor_video_price;
	
	/**
	 * original_anchor_audio_price
	 */
	@Column(name="original_anchor_audio_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="original_anchor_audio_price")
	private Integer original_anchor_audio_price;
	
	/**
	 * 主播性别标识 1 男 2 女
	 */
	@Column(name="sex",nullable=true,jdbcType=JdbcType.TINYINT,comment="主播性别标识 1 男 2 女")
	private Integer sex;
	
	/**
	 * 联系方式开关
	 */
	@Column(name="contact_on",nullable=true,jdbcType=JdbcType.TINYINT,comment="联系方式开关")
	private Integer contact_on;
	
	/**
	 * 联系方式
	 */
	@Column(name="contact_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="联系方式")
	private String contact_text;
	
	/**
	 * 联系方式价格
	 */
	@Column(name="contact_price",nullable=true,jdbcType=JdbcType.INTEGER,comment="联系方式价格")
	private Integer contact_price;
	
	/**
	 * 年龄
	 */
	@Column(name="age",nullable=true,jdbcType=JdbcType.INTEGER,comment="年龄")
	private Integer age;
	
	/**
	 * 距离
	 */
	@Column(name="distance",nullable=true,jdbcType=JdbcType.FLOAT,comment="距离")
	private Float distance;
	
	/**
	 * 首页主播展示特殊图
	 */
	@Column(name="headPhoto",nullable=true,jdbcType=JdbcType.VARCHAR,comment="首页主播展示特殊图")
	private String headPhoto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
	
	public Date getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	
	public String getAudit_memo() {
		return audit_memo;
	}

	public void setAudit_memo(String audit_memo) {
		this.audit_memo = audit_memo;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getTurntable() {
		return turntable;
	}

	public void setTurntable(String turntable) {
		this.turntable = turntable;
	}
	
	public Integer getDial_num() {
		return dial_num;
	}

	public void setDial_num(Integer dial_num) {
		this.dial_num = dial_num;
	}
	
	public Integer getRecv_num() {
		return recv_num;
	}

	public void setRecv_num(Integer recv_num) {
		this.recv_num = recv_num;
	}
	
	public Integer getEvaluation_num() {
		return evaluation_num;
	}

	public void setEvaluation_num(Integer evaluation_num) {
		this.evaluation_num = evaluation_num;
	}
	
	public Integer getAudio_dial_num() {
		return audio_dial_num;
	}

	public void setAudio_dial_num(Integer audio_dial_num) {
		this.audio_dial_num = audio_dial_num;
	}
	
	public Integer getAudio_recv_num() {
		return audio_recv_num;
	}

	public void setAudio_recv_num(Integer audio_recv_num) {
		this.audio_recv_num = audio_recv_num;
	}
	
	public Integer getAudio_evaluation_num() {
		return audio_evaluation_num;
	}

	public void setAudio_evaluation_num(Integer audio_evaluation_num) {
		this.audio_evaluation_num = audio_evaluation_num;
	}
	
	public Double getAvg_yesterday_recv() {
		return avg_yesterday_recv;
	}

	public void setAvg_yesterday_recv(Double avg_yesterday_recv) {
		this.avg_yesterday_recv = avg_yesterday_recv;
	}
	
	public Float getRatio() {
		return ratio;
	}

	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}
	
	public Long getParent_userid() {
		return parent_userid;
	}

	public void setParent_userid(Long parent_userid) {
		this.parent_userid = parent_userid;
	}
	
	public Integer getDisturb() {
		return disturb;
	}

	public void setDisturb(Integer disturb) {
		this.disturb = disturb;
	}
	
	public Integer getDial_helper() {
		return dial_helper;
	}

	public void setDial_helper(Integer dial_helper) {
		this.dial_helper = dial_helper;
	}
	
	public Integer getDial_helper_num() {
		return dial_helper_num;
	}

	public void setDial_helper_num(Integer dial_helper_num) {
		this.dial_helper_num = dial_helper_num;
	}
	
	public Integer getDial_helper_audio_num() {
		return dial_helper_audio_num;
	}

	public void setDial_helper_audio_num(Integer dial_helper_audio_num) {
		this.dial_helper_audio_num = dial_helper_audio_num;
	}
	
	public Integer getFreshman() {
		return freshman;
	}

	public void setFreshman(Integer freshman) {
		this.freshman = freshman;
	}
	
	public Integer getNominate() {
		return nominate;
	}

	public void setNominate(Integer nominate) {
		this.nominate = nominate;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}
	
	public String getSegregation() {
		return segregation;
	}

	public void setSegregation(String segregation) {
		this.segregation = segregation;
	}
	
	public Integer getAudio_price() {
		return audio_price;
	}

	public void setAudio_price(Integer audio_price) {
		this.audio_price = audio_price;
	}
	
	public Float getAudio_ratio() {
		return audio_ratio;
	}

	public void setAudio_ratio(Float audio_ratio) {
		this.audio_ratio = audio_ratio;
	}
	
	public Float getGift_ratio() {
		return gift_ratio;
	}

	public void setGift_ratio(Float gift_ratio) {
		this.gift_ratio = gift_ratio;
	}
	
	public Integer getMsg_ratio() {
		return msg_ratio;
	}

	public void setMsg_ratio(Integer msg_ratio) {
		this.msg_ratio = msg_ratio;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getAudio_time() {
		return audio_time;
	}

	public void setAudio_time(Integer audio_time) {
		this.audio_time = audio_time;
	}
	
	public String getAudio_path() {
		return audio_path;
	}

	public void setAudio_path(String audio_path) {
		this.audio_path = audio_path;
	}
	
	public Integer getVideo_type() {
		return video_type;
	}

	public void setVideo_type(Integer video_type) {
		this.video_type = video_type;
	}
	
	public Integer getAudio_type() {
		return audio_type;
	}

	public void setAudio_type(Integer audio_type) {
		this.audio_type = audio_type;
	}
	
	public Integer getApp() {
		return app;
	}

	public void setApp(Integer app) {
		this.app = app;
	}
	
	public Integer getSilent() {
		return silent;
	}

	public void setSilent(Integer silent) {
		this.silent = silent;
	}
	
	public Integer getIndex_show() {
		return index_show;
	}

	public void setIndex_show(Integer index_show) {
		this.index_show = index_show;
	}
	
	public Integer getDial_controll() {
		return dial_controll;
	}

	public void setDial_controll(Integer dial_controll) {
		this.dial_controll = dial_controll;
	}
	
	public Integer getDial_charge_user_level() {
		return dial_charge_user_level;
	}

	public void setDial_charge_user_level(Integer dial_charge_user_level) {
		this.dial_charge_user_level = dial_charge_user_level;
	}
	
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
	public Integer getAppearance() {
		return appearance;
	}

	public void setAppearance(Integer appearance) {
		this.appearance = appearance;
	}
	
	public Integer getShow_income() {
		return show_income;
	}

	public void setShow_income(Integer show_income) {
		this.show_income = show_income;
	}
	
	public Integer getDeduct() {
		return deduct;
	}

	public void setDeduct(Integer deduct) {
		this.deduct = deduct;
	}
	
	public Integer getAward() {
		return award;
	}

	public void setAward(Integer award) {
		this.award = award;
	}
	
	public Integer getSettlement() {
		return settlement;
	}

	public void setSettlement(Integer settlement) {
		this.settlement = settlement;
	}
	
	public Long getLabor_id() {
		return labor_id;
	}

	public void setLabor_id(Long labor_id) {
		this.labor_id = labor_id;
	}
	
	public Integer getCharm() {
		return charm;
	}

	public void setCharm(Integer charm) {
		this.charm = charm;
	}
	
	public Integer getAnchor_safe() {
		return anchor_safe;
	}

	public void setAnchor_safe(Integer anchor_safe) {
		this.anchor_safe = anchor_safe;
	}

	public Integer getUser_dial_controll() {
		return user_dial_controll;
	}

	public void setUser_dial_controll(Integer user_dial_controll) {
		this.user_dial_controll = user_dial_controll;
	}
	
	public Integer getAnchor_video_price() {
		return anchor_video_price;
	}

	public void setAnchor_video_price(Integer anchor_video_price) {
		this.anchor_video_price = anchor_video_price;
	}
	
	public Integer getAnchor_audio_price() {
		return anchor_audio_price;
	}

	public void setAnchor_audio_price(Integer anchor_audio_price) {
		this.anchor_audio_price = anchor_audio_price;
	}
	
	public Integer getOriginal_video_price() {
		return original_video_price;
	}

	public void setOriginal_video_price(Integer original_video_price) {
		this.original_video_price = original_video_price;
	}
	
	public Integer getOriginal_audio_price() {
		return original_audio_price;
	}

	public void setOriginal_audio_price(Integer original_audio_price) {
		this.original_audio_price = original_audio_price;
	}
	
	public Integer getOriginal_anchor_video_price() {
		return original_anchor_video_price;
	}

	public void setOriginal_anchor_video_price(Integer original_anchor_video_price) {
		this.original_anchor_video_price = original_anchor_video_price;
	}
	
	public Integer getOriginal_anchor_audio_price() {
		return original_anchor_audio_price;
	}

	public void setOriginal_anchor_audio_price(Integer original_anchor_audio_price) {
		this.original_anchor_audio_price = original_anchor_audio_price;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getContact_on() {
		return contact_on;
	}

	public void setContact_on(Integer contact_on) {
		this.contact_on = contact_on;
	}
	
	public String getContact_text() {
		return contact_text;
	}

	public void setContact_text(String contact_text) {
		this.contact_text = contact_text;
	}
	
	public Integer getContact_price() {
		return contact_price;
	}

	public void setContact_price(Integer contact_price) {
		this.contact_price = contact_price;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}
	
	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
}