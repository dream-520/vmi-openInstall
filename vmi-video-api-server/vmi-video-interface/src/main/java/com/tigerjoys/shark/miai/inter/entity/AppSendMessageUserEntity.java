package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  客服消息历史表[t_app_send_message_user] 表对应的实体类
 * @author yangjunming
 * @Date 2020-01-14 18:17:54
 *
 */
@Table(name="t_app_send_message_user")
public class AppSendMessageUserEntity extends BaseEntity implements Serializable {

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
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 管理ID
	 */
	@Column(name="admin_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="管理ID")
	private Long admin_id;
	
	/**
	 * 送钻石数量
	 */
	@Column(name="diamond",nullable=true,jdbcType=JdbcType.INTEGER,comment="送钻石数量")
	private Integer diamond;
	
	/**
	 * 送小红花数量
	 */
	@Column(name="flower",nullable=true,jdbcType=JdbcType.INTEGER,comment="送小红花数量")
	private Integer flower;
	
	/**
	 * 送余额数量
	 */
	@Column(name="balance",nullable=true,jdbcType=JdbcType.DECIMAL,comment="送余额数量")
	private Double balance;
	
	/**
	 * 发送的消息内容
	 */
	@Column(name="body",nullable=true,jdbcType=JdbcType.VARCHAR,comment="发送的消息内容")
	private String body;
	
	/**
	 * 收益补差类型
	 */
	@Column(name="log_type",nullable=true,jdbcType=JdbcType.INTEGER,comment="收益补差类型")
	private Integer log_type;
	
	/**
	 * 发送消息的时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="发送消息的时间")
	private Date create_time;
	
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
	
	public Long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Integer getFlower() {
		return flower;
	}

	public void setFlower(Integer flower) {
		this.flower = flower;
	}
	
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public Integer getLog_type() {
		return log_type;
	}

	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}