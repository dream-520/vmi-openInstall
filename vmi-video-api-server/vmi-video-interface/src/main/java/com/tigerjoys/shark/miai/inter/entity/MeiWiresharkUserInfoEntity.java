package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  美丽约用户详细信息表[t_mei_wireshark_user_info] 表对应的实体类
 * @author shiming
 * @Date 2018-05-22 18:24:32
 *
 */
@Table(name="t_mei_wireshark_user_info")
public class MeiWiresharkUserInfoEntity extends BaseEntity implements Serializable {

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
	 * 美丽约平台id值
	 */
	@Column(name="uid",nullable=false,jdbcType=JdbcType.BIGINT,comment="美丽约平台id值")
	private Long uid;
	
	/**
	 * 用户的完整信息
	 */
	@Column(name="info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="用户的完整信息")
	private String info;
	
	/**
	 * 右侧顶部状态
	 */
	@Column(name="top_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="右侧顶部状态")
	private String top_info;
	
	/**
	 * 用户基本信息
	 */
	@Column(name="user_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="用户基本信息")
	private String user_info;
	
	/**
	 * 相册信息
	 */
	@Column(name="photo_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="相册信息")
	private String photo_info;
	
	/**
	 * 邀约
	 */
	@Column(name="event_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="邀约")
	private String event_info;
	
	/**
	 * 动态
	 */
	@Column(name="attention_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="动态")
	private String attention_info;
	
	/**
	 * 音乐
	 */
	@Column(name="music_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="音乐")
	private String music_info;
	
	/**
	 * svip
	 */
	@Column(name="svip_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="svip")
	private String svip_info;
	
	/**
	 * 用户扩展信息
	 */
	@Column(name="about_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="用户扩展信息")
	private String about_info;
	
	/**
	 * 用户认证信息
	 */
	@Column(name="auth_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="用户认证信息")
	private String auth_info;
	
	/**
	 * 礼物信息
	 */
	@Column(name="gift_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="礼物信息")
	private String gift_info;
	
	/**
	 * 小编专访
	 */
	@Column(name="interview_info",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="小编专访")
	private String interview_info;
	
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
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getTop_info() {
		return top_info;
	}

	public void setTop_info(String top_info) {
		this.top_info = top_info;
	}
	
	public String getUser_info() {
		return user_info;
	}

	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}
	
	public String getPhoto_info() {
		return photo_info;
	}

	public void setPhoto_info(String photo_info) {
		this.photo_info = photo_info;
	}
	
	public String getEvent_info() {
		return event_info;
	}

	public void setEvent_info(String event_info) {
		this.event_info = event_info;
	}
	
	public String getAttention_info() {
		return attention_info;
	}

	public void setAttention_info(String attention_info) {
		this.attention_info = attention_info;
	}
	
	public String getMusic_info() {
		return music_info;
	}

	public void setMusic_info(String music_info) {
		this.music_info = music_info;
	}
	
	public String getSvip_info() {
		return svip_info;
	}

	public void setSvip_info(String svip_info) {
		this.svip_info = svip_info;
	}
	
	public String getAbout_info() {
		return about_info;
	}

	public void setAbout_info(String about_info) {
		this.about_info = about_info;
	}
	
	public String getAuth_info() {
		return auth_info;
	}

	public void setAuth_info(String auth_info) {
		this.auth_info = auth_info;
	}
	
	public String getGift_info() {
		return gift_info;
	}

	public void setGift_info(String gift_info) {
		this.gift_info = gift_info;
	}
	
	public String getInterview_info() {
		return interview_info;
	}

	public void setInterview_info(String interview_info) {
		this.interview_info = interview_info;
	}
	
}