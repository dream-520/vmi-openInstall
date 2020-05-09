package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_app_start_send_msg] 表对应的实体类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
@Table(name="t_app_start_send_msg")
public class AppStartSendMsgEntity extends BaseEntity implements Serializable {

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
	 * 发送消息的用户
	 */
	@Column(name="fromId",nullable=false,jdbcType=JdbcType.BIGINT,comment="发送消息的用户")
	private Long fromId;
	
	/**
	 * 接受消息的用户
	 */
	@Column(name="toId",nullable=false,jdbcType=JdbcType.BIGINT,comment="接受消息的用户")
	private Long toId;
	
	/**
	 * 消息内容
	 */
	@Column(name="msg",nullable=false,jdbcType=JdbcType.VARCHAR,comment="消息内容")
	private String msg;
	
	/**
	 * 发送消息的毫秒值
	 */
	@Column(name="send_time",nullable=false,jdbcType=JdbcType.BIGINT,comment="发送消息的毫秒值")
	private Long send_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	
	public Long getToId() {
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Long getSend_time() {
		return send_time;
	}

	public void setSend_time(Long send_time) {
		this.send_time = send_time;
	}
	
}