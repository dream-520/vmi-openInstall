package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_mei_wireshark_user_detail] 表对应的实体类
 * @author shiming
 * @Date 2018-07-31 17:05:19
 *
 */
@Table(name="t_mei_wireshark_user_detail")
public class MeiWiresharkUserDetailEntity extends BaseEntity implements Serializable {

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
	 * 昵称
	 */
	@Column(name="nickName",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickName;
	
	/**
	 * 年龄
	 */
	@Column(name="age",nullable=false,jdbcType=JdbcType.INTEGER,comment="年龄")
	private Integer age;
	
	/**
	 * 头像
	 */
	@Column(name="photo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="头像")
	private String photo;
	
	/**
	 * 签名
	 */
	@Column(name="signature",nullable=true,jdbcType=JdbcType.VARCHAR,comment="签名")
	private String signature;
	
	/**
	 * 出生日期
	 */
	@Column(name="birthday",nullable=false,jdbcType=JdbcType.DATE,comment="出生日期")
	private Date birthday;
	
	/**
	 * 联系方式
	 */
	@Column(name="concat_text",nullable=false,jdbcType=JdbcType.VARCHAR,comment="联系方式")
	private String concat_text;
	
	/**
	 * 工作
	 */
	@Column(name="job",nullable=false,jdbcType=JdbcType.VARCHAR,comment="工作")
	private String job;
	
	/**
	 * weight
	 */
	@Column(name="weight",nullable=false,jdbcType=JdbcType.INTEGER,comment="weight")
	private Integer weight;
	
	/**
	 * stature
	 */
	@Column(name="stature",nullable=false,jdbcType=JdbcType.INTEGER,comment="stature")
	private Integer stature;
	
	/**
	 * marriage
	 */
	@Column(name="marriage",nullable=false,jdbcType=JdbcType.INTEGER,comment="marriage")
	private Integer marriage;
	
	/**
	 * income
	 */
	@Column(name="income",nullable=false,jdbcType=JdbcType.INTEGER,comment="income")
	private Integer income;
	
	/**
	 * 兴趣爱好
	 */
	@Column(name="points",nullable=false,jdbcType=JdbcType.VARCHAR,comment="兴趣爱好")
	private String points;
	
	/**
	 * 相册
	 */
	@Column(name="photos",nullable=true,jdbcType=JdbcType.VARCHAR,comment="相册")
	private String photos;
	
	/**
	 * 交友目的
	 */
	@Column(name="friend",nullable=false,jdbcType=JdbcType.INTEGER,comment="交友目的")
	private Integer friend;
	
	/**
	 * 个人信息对感情的看法  对性
	 */
	@Column(name="sex",nullable=false,jdbcType=JdbcType.INTEGER,comment="个人信息对感情的看法  对性")
	private Integer sex;
	
	/**
	 * 个人信息对另一半的要求
	 */
	@Column(name="spouse",nullable=true,jdbcType=JdbcType.INTEGER,comment="个人信息对另一半的要求")
	private Integer spouse;
	
	/**
	 * 状态  用于检测本用户是否已经转换成功
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.INTEGER,comment="状态  用于检测本用户是否已经转换成功")
	private Integer state;
	
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
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getConcat_text() {
		return concat_text;
	}

	public void setConcat_text(String concat_text) {
		this.concat_text = concat_text;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getStature() {
		return stature;
	}

	public void setStature(Integer stature) {
		this.stature = stature;
	}
	
	public Integer getMarriage() {
		return marriage;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	
	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
	
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	public Integer getFriend() {
		return friend;
	}

	public void setFriend(Integer friend) {
		this.friend = friend;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getSpouse() {
		return spouse;
	}

	public void setSpouse(Integer spouse) {
		this.spouse = spouse;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}