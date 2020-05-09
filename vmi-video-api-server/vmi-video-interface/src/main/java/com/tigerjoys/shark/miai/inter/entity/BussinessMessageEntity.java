package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  业务消息记录表[t_bussiness_message] 表对应的实体类
 * @author liuman
 * @Date 2017-10-13 15:30:14
 *
 */
@Table(name="t_bussiness_message")
public class BussinessMessageEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 消息标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="消息标题")
	private String title;
	
	/**
	 * 简介
	 */
	@Column(name="intro",nullable=false,jdbcType=JdbcType.VARCHAR,comment="简介")
	private String intro;
	
	/**
	 * 业务消息内容
	 */
	@Column(name="content",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="业务消息内容")
	private String content;
	
	/**
	 * 业务消息类型(对应BussinessMessageTypeEnum的type)
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.SMALLINT,comment="业务消息类型(对应BussinessMessageTypeEnum的type)")
	private Integer type;
	
	/**
	 * 点击类型:0-不可点击,1-可点击
	 */
	@Column(name="hitType",nullable=true,jdbcType=JdbcType.SMALLINT,comment="点击类型:0-不可点击,1-可点击")
	private Integer hitType;
	
	/**
	 * 安卓页面名称(对应BussinessMessageTypeEnum的androidAppPage)
	 */
	@Column(name="androidAppPage",nullable=true,jdbcType=JdbcType.VARCHAR,comment="安卓页面名称(对应BussinessMessageTypeEnum的androidAppPage)")
	private String androidAppPage;
	
	/**
	 * ios页面名称(对应BussinessMessageTypeEnum的iosAppPage)
	 */
	@Column(name="iosAppPage",nullable=true,jdbcType=JdbcType.VARCHAR,comment="ios页面名称(对应BussinessMessageTypeEnum的iosAppPage)")
	private String iosAppPage;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	@Column(name="opentype",nullable=true,jdbcType=JdbcType.SMALLINT,comment="0打开网页链接,1打开应用链接,2打开浏览器")
	private Integer opentype;
	
	/**
	 * 打开链接地址
	 */
	@Column(name="openurl",nullable=true,jdbcType=JdbcType.VARCHAR,comment="打开链接地址")
	private String openurl;
	
	/**
	 * app内页的参数,json
	 */
	@Column(name="param",nullable=true,jdbcType=JdbcType.VARCHAR,comment="app内页的参数,json")
	private String param;
	
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getHitType() {
		return hitType;
	}

	public void setHitType(Integer hitType) {
		this.hitType = hitType;
	}
	
	public String getAndroidAppPage() {
		return androidAppPage;
	}

	public void setAndroidAppPage(String androidAppPage) {
		this.androidAppPage = androidAppPage;
	}
	
	public String getIosAppPage() {
		return iosAppPage;
	}

	public void setIosAppPage(String iosAppPage) {
		this.iosAppPage = iosAppPage;
	}
	
	public Integer getOpentype() {
		return opentype;
	}

	public void setOpentype(Integer opentype) {
		this.opentype = opentype;
	}
	
	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}