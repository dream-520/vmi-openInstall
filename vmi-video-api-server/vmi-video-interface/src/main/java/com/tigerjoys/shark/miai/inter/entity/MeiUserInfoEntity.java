package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_mei_user_info] 表对应的实体类
 * @author chengang
 * @Date 2017-12-28 10:47:40
 *
 */
@Table(name="t_mei_user_info")
public class MeiUserInfoEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标示")
	private Long id;
	
	/**
	 * 美丽约平台id
	 */
	@Column(name="uid",nullable=false,jdbcType=JdbcType.BIGINT,comment="美丽约平台id")
	private Long uid;
	
	/**
	 * 自己平台id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="自己平台id")
	private Long userid;
	
	/**
	 * 美丽约用户昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="美丽约用户昵称")
	private String nickname;
	
	/**
	 * 头像路径
	 */
	@Column(name="photo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="头像路径")
	private String photo;
	
	/**
	 * 个性签名
	 */
	@Column(name="signature",nullable=true,jdbcType=JdbcType.VARCHAR,comment="个性签名")
	private String signature;
	
	/**
	 * qq
	 */
	@Column(name="qq",nullable=true,jdbcType=JdbcType.VARCHAR,comment="qq")
	private String qq;
	
	/**
	 * weixin
	 */
	@Column(name="weixin",nullable=true,jdbcType=JdbcType.VARCHAR,comment="weixin")
	private String weixin;
	
	/**
	 * mobile
	 */
	@Column(name="mobile",nullable=true,jdbcType=JdbcType.VARCHAR,comment="mobile")
	private String mobile;
	
	/**
	 * 星座
	 */
	@Column(name="zodiac",nullable=true,jdbcType=JdbcType.CHAR,comment="星座")
	private String zodiac;
	
	/**
	 * 感情状态
	 */
	@Column(name="marriage",nullable=true,jdbcType=JdbcType.TINYINT,comment="感情状态")
	private Integer marriage;
	
	/**
	 * 职位
	 */
	@Column(name="job",nullable=true,jdbcType=JdbcType.VARCHAR,comment="职位")
	private String job;
	
	/**
	 * '收入
	 */
	@Column(name="income",nullable=true,jdbcType=JdbcType.TINYINT,comment="'收入")
	private Integer income;
	
	/**
	 * 身高(CM)
	 */
	@Column(name="stature",nullable=true,jdbcType=JdbcType.SMALLINT,comment="身高(CM)")
	private Integer stature;
	
	/**
	 * 体重(KG)
	 */
	@Column(name="weight",nullable=true,jdbcType=JdbcType.SMALLINT,comment="体重(KG)")
	private Integer weight;
	
	/**
	 * 对性的看法
	 */
	@Column(name="sex_opinion",nullable=true,jdbcType=JdbcType.TINYINT,comment="对性的看法")
	private Integer sex_opinion;
	
	/**
	 * 对另一半的看法
	 */
	@Column(name="spouse_opinion",nullable=true,jdbcType=JdbcType.TINYINT,comment="对另一半的看法")
	private Integer spouse_opinion;
	
	/**
	 * 交友目地
	 */
	@Column(name="make_friend",nullable=true,jdbcType=JdbcType.TINYINT,comment="交友目地")
	private Integer make_friend;
	
	/**
	 * trait_point
	 */
	@Column(name="trait_point",nullable=true,jdbcType=JdbcType.VARCHAR,comment="trait_point")
	private String trait_point;
	
	/**
	 * 扩展JSON字段
	 */
	@Column(name="extra",nullable=true,jdbcType=JdbcType.VARCHAR,comment="扩展JSON字段")
	private String extra;
	
	/**
	 * 性别
	 */
	@Column(name="sex",nullable=false,jdbcType=JdbcType.TINYINT,comment="性别")
	private Integer sex;
	
	/**
	 * 形象视频路径
	 */
	@Column(name="video",nullable=true,jdbcType=JdbcType.VARCHAR,comment="形象视频路径")
	private String video;
	
	/**
	 * 出生日期
	 */
	@Column(name="age",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="出生日期")
	private Date age;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getZodiac() {
		return zodiac;
	}

	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	
	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}
	
	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getSex_opinion() {
		return sex_opinion;
	}

	public void setSex_opinion(Integer sex_opinion) {
		this.sex_opinion = sex_opinion;
	}
	
	public Integer getSpouse_opinion() {
		return spouse_opinion;
	}

	public void setSpouse_opinion(Integer spouse_opinion) {
		this.spouse_opinion = spouse_opinion;
	}
	
	public Integer getMake_friend() {
		return make_friend;
	}

	public void setMake_friend(Integer make_friend) {
		this.make_friend = make_friend;
	}
	
	public String getTrait_point() {
		return trait_point;
	}

	public void setTrait_point(String trait_point) {
		this.trait_point = trait_point;
	}
	
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}
	
}