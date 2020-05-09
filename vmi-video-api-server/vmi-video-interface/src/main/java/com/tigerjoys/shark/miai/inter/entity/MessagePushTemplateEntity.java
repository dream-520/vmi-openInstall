package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  系统消息[t_message_push_template] 表对应的实体类
 * @author shiming
 * @Date 2019-10-14 15:44:34
 *
 */
@Table(name="t_message_push_template")
public class MessagePushTemplateEntity extends BaseEntity implements Serializable {

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
	 * 创建人ID
	 */
	@Column(name="create_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="创建人ID")
	private Long create_adminid;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
	private Date update_time;
	
	/**
	 * 修改人ID
	 */
	@Column(name="update_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="修改人ID")
	private Long update_adminid;
	
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
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	@Column(name="opentype",nullable=false,jdbcType=JdbcType.TINYINT,comment="0打开网页链接,1打开应用链接,2打开浏览器")
	private Integer opentype;
	
	/**
	 * 消息类型,0系统公告,1通用消息
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="消息类型,0系统公告,1通用消息")
	private Integer type;
	
	/**
	 * 推送人群,1全用户,2全主播 3 特定用户
	 */
	@Column(name="push_crowd",nullable=false,jdbcType=JdbcType.TINYINT,comment="推送人群,1全用户,2全主播 3 特定用户")
	private Integer push_crowd;
	
	/**
	 * 状态,0停用,1正常,-1隐藏,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态,0停用,1正常,-1隐藏,-9删除")
	private Integer status;
	
	/**
	 * 进入主播页 主播id
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="进入主播页 主播id")
	private Long anchorid;
	
	/**
	 * 给特定用户发送的用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="给特定用户发送的用户id")
	private Long userid;
	
	/**
	 * 标识是否是后台自动发送还是认为发送 0 人工 1 后台定时
	 */
	@Column(name="robot",nullable=true,jdbcType=JdbcType.TINYINT,comment="标识是否是后台自动发送还是认为发送 0 人工 1 后台定时")
	private Integer robot;
	
	/**
	 * 跳转到的url地址
	 */
	@Column(name="url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="跳转到的url地址")
	private String url;
	
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
	
	public Long getCreate_adminid() {
		return create_adminid;
	}

	public void setCreate_adminid(Long create_adminid) {
		this.create_adminid = create_adminid;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUpdate_adminid() {
		return update_adminid;
	}

	public void setUpdate_adminid(Long update_adminid) {
		this.update_adminid = update_adminid;
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
	
	public Integer getOpentype() {
		return opentype;
	}

	public void setOpentype(Integer opentype) {
		this.opentype = opentype;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getPush_crowd() {
		return push_crowd;
	}

	public void setPush_crowd(Integer push_crowd) {
		this.push_crowd = push_crowd;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getRobot() {
		return robot;
	}

	public void setRobot(Integer robot) {
		this.robot = robot;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}