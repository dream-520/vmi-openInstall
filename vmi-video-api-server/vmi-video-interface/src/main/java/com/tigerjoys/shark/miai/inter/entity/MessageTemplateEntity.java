package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  系统消息[t_message_template] 表对应的实体类
 * @author liuman
 * @Date 2018-07-17 15:07:36
 *
 */
@Table(name="t_message_template")
public class MessageTemplateEntity extends BaseEntity implements Serializable {

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
	 * 发布类型，0立即,1定时
	 */
	@Column(name="publish_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="发布类型，0立即,1定时")
	private Integer publish_type;
	
	/**
	 * 发布时间
	 */
	@Column(name="publish_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="发布时间")
	private Date publish_time;
	
	/**
	 * 简介
	 */
	@Column(name="intro",nullable=false,jdbcType=JdbcType.VARCHAR,comment="简介")
	private String intro;
	
	/**
	 * 内容类型,0自制内容,1url
	 */
	@Column(name="content_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="内容类型,0自制内容,1url")
	private Integer content_type;
	
	/**
	 * 打开链接
	 */
	@Column(name="openurl",nullable=true,jdbcType=JdbcType.VARCHAR,comment="打开链接")
	private String openurl;
	
	/**
	 * 0打开网页链接,1打开应用链接,2打开浏览器
	 */
	@Column(name="opentype",nullable=false,jdbcType=JdbcType.TINYINT,comment="0打开网页链接,1打开应用链接,2打开浏览器")
	private Integer opentype;
	
	/**
	 * 自制内容
	 */
	@Column(name="content",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="自制内容")
	private String content;
	
	/**
	 * 消息类型,0系统公告,1通用消息
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="消息类型,0系统公告,1通用消息")
	private Integer type;
	
	/**
	 * 推送人群,1全用户,2指定条件用户群
	 */
	@Column(name="push_crowd",nullable=false,jdbcType=JdbcType.TINYINT,comment="推送人群,1全用户,2指定条件用户群")
	private Integer push_crowd;
	
	/**
	 * 状态,0停用,1正常,-1隐藏,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态,0停用,1正常,-1隐藏,-9删除")
	private Integer status;
	
	/**
	 * 状态变更日期
	 */
	@Column(name="status_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="状态变更日期")
	private Date status_time;
	
	/**
	 * 状态变更的原因
	 */
	@Column(name="status_reason",nullable=true,jdbcType=JdbcType.VARCHAR,comment="状态变更的原因")
	private String status_reason;
	
	/**
	 * 操作人ID
	 */
	@Column(name="status_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="操作人ID")
	private Long status_adminid;
	
	/**
	 * 是否已发送,0未发送，1已发送
	 */
	@Column(name="send",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否已发送,0未发送，1已发送")
	private Integer send;
	
	/**
	 * app包名
	 */
	@Column(name="packagename",nullable=true,jdbcType=JdbcType.VARCHAR,comment="app包名")
	private String packagename;
	
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
	
	public Integer getPublish_type() {
		return publish_type;
	}

	public void setPublish_type(Integer publish_type) {
		this.publish_type = publish_type;
	}
	
	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public Integer getContent_type() {
		return content_type;
	}

	public void setContent_type(Integer content_type) {
		this.content_type = content_type;
	}
	
	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}
	
	public Integer getOpentype() {
		return opentype;
	}

	public void setOpentype(Integer opentype) {
		this.opentype = opentype;
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
	
	public Date getStatus_time() {
		return status_time;
	}

	public void setStatus_time(Date status_time) {
		this.status_time = status_time;
	}
	
	public String getStatus_reason() {
		return status_reason;
	}

	public void setStatus_reason(String status_reason) {
		this.status_reason = status_reason;
	}
	
	public Long getStatus_adminid() {
		return status_adminid;
	}

	public void setStatus_adminid(Long status_adminid) {
		this.status_adminid = status_adminid;
	}
	
	public Integer getSend() {
		return send;
	}

	public void setSend(Integer send) {
		this.send = send;
	}
	
	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	
}