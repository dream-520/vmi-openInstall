package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  短信发送记录表[t_send_sms] 表对应的实体类
 * @author yangjunming
 * @Date 2017-10-19 19:01:08
 *
 */
@Table(name="t_send_sms")
public class SendSmsEntity extends BaseEntity implements Serializable {

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
	 * 手机号码
	 */
	@Column(name="mobile",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机号码")
	private String mobile;
	
	/**
	 * 发送内容
	 */
	@Column(name="content",nullable=false,jdbcType=JdbcType.VARCHAR,comment="发送内容")
	private String content;
	
	/**
	 * 发送渠道
	 */
	@Column(name="send_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="发送渠道")
	private String send_name;
	
	/**
	 * 发送结果
	 */
	@Column(name="return_mes",nullable=false,jdbcType=JdbcType.VARCHAR,comment="发送结果")
	private String return_mes;
	
	/**
	 * 返送结果code
	 */
	@Column(name="send_state",nullable=false,jdbcType=JdbcType.INTEGER,comment="返送结果code")
	private Integer send_state;
	
	/**
	 * 0失败,1成功
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="0失败,1成功")
	private Integer status;
	
	/**
	 * 短信发送类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.INTEGER,comment="短信发送类型")
	private Integer type;
	
	/**
	 * 返回结果信息
	 */
	@Column(name="return_msg",nullable=true,jdbcType=JdbcType.VARCHAR,comment="返回结果信息")
	private String return_msg;
	
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
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	
	public String getReturn_mes() {
		return return_mes;
	}

	public void setReturn_mes(String return_mes) {
		this.return_mes = return_mes;
	}
	
	public Integer getSend_state() {
		return send_state;
	}

	public void setSend_state(Integer send_state) {
		this.send_state = send_state;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}