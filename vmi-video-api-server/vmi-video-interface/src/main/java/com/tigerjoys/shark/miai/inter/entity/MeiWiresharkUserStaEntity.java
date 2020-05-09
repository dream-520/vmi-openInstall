package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  美丽约用户数据清洗表[t_mei_wireshark_user_sta] 表对应的实体类
 * @author shiming
 * @Date 2018-05-30 15:58:20
 *
 */
@Table(name="t_mei_wireshark_user_sta")
public class MeiWiresharkUserStaEntity extends BaseEntity implements Serializable {

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
	 * 美丽约id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="美丽约id")
	private Long userid;
	
	/**
	 * 联系方式
	 */
	@Column(name="msg",nullable=true,jdbcType=JdbcType.VARCHAR,comment="联系方式")
	private String msg;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=true,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 头像
	 */
	@Column(name="url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="头像")
	private String url;
	
	/**
	 * 城市
	 */
	@Column(name="area",nullable=true,jdbcType=JdbcType.VARCHAR,comment="城市")
	private String area;
	
	/**
	 * 基本信息
	 */
	@Column(name="about",nullable=true,jdbcType=JdbcType.VARCHAR,comment="基本信息")
	private String about;
	
	/**
	 * 性别
	 */
	@Column(name="sex",nullable=true,jdbcType=JdbcType.TINYINT,comment="性别")
	private Integer sex;
	
	/**
	 * 年龄
	 */
	@Column(name="age",nullable=true,jdbcType=JdbcType.INTEGER,comment="年龄")
	private Integer age;
	
	/**
	 * 相册数量
	 */
	@Column(name="photos",nullable=true,jdbcType=JdbcType.INTEGER,comment="相册数量")
	private Integer photos;
	
	/**
	 * 邀约数量
	 */
	@Column(name="events",nullable=true,jdbcType=JdbcType.INTEGER,comment="邀约数量")
	private Integer events;
	
	/**
	 * 动态数量
	 */
	@Column(name="attentions",nullable=true,jdbcType=JdbcType.INTEGER,comment="动态数量")
	private Integer attentions;
	
	/**
	 * 音乐数量
	 */
	@Column(name="musics",nullable=true,jdbcType=JdbcType.INTEGER,comment="音乐数量")
	private Integer musics;
	
	/**
	 * 礼物数量
	 */
	@Column(name="gifts",nullable=true,jdbcType=JdbcType.INTEGER,comment="礼物数量")
	private Integer gifts;
	
	/**
	 * 主编访问数量
	 */
	@Column(name="interviews",nullable=true,jdbcType=JdbcType.INTEGER,comment="主编访问数量")
	private Integer interviews;
	
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
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getPhotos() {
		return photos;
	}

	public void setPhotos(Integer photos) {
		this.photos = photos;
	}
	
	public Integer getEvents() {
		return events;
	}

	public void setEvents(Integer events) {
		this.events = events;
	}
	
	public Integer getAttentions() {
		return attentions;
	}

	public void setAttentions(Integer attentions) {
		this.attentions = attentions;
	}
	
	public Integer getMusics() {
		return musics;
	}

	public void setMusics(Integer musics) {
		this.musics = musics;
	}
	
	public Integer getGifts() {
		return gifts;
	}

	public void setGifts(Integer gifts) {
		this.gifts = gifts;
	}
	
	public Integer getInterviews() {
		return interviews;
	}

	public void setInterviews(Integer interviews) {
		this.interviews = interviews;
	}
	
}