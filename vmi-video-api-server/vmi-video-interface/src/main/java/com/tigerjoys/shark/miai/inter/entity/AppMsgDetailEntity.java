package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  全网消息详情表[t_app_msg_detail] 表对应的实体类
 * @author shiming
 * @Date 2019-07-23 10:40:19
 *
 */
@Table(name="t_app_msg_detail")
public class AppMsgDetailEntity extends BaseEntity implements Serializable {

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
	 * 真实用户id
	 */
	@Column(name="fromid",nullable=false,jdbcType=JdbcType.BIGINT,comment="真实用户id")
	private Long fromid;
	
	/**
	 * 虚拟用户id
	 */
	@Column(name="toid",nullable=false,jdbcType=JdbcType.BIGINT,comment="虚拟用户id")
	private Long toid;
	
	/**
	 * 发送的消息内容
	 */
	@Column(name="body",nullable=true,jdbcType=JdbcType.VARCHAR,comment="发送的消息内容")
	private String body;
	
	/**
	 * 存储图片数据
	 */
	@Column(name="attach",nullable=true,jdbcType=JdbcType.VARCHAR,comment="存储图片数据")
	private String attach;
	
	/**
	 * 消息类型
	 */
	@Column(name="convType",nullable=false,jdbcType=JdbcType.VARCHAR,comment="消息类型")
	private String convType;
	
	/**
	 * 消息类型
	 */
	@Column(name="msgType",nullable=false,jdbcType=JdbcType.VARCHAR,comment="消息类型")
	private String msgType;
	
	/**
	 * 消息类型 1 文本消息 2 图片消息 3 自定义消息
	 */
	@Column(name="tag",nullable=false,jdbcType=JdbcType.TINYINT,comment="消息类型 1 文本消息 2 图片消息 3 自定义消息")
	private Integer tag;
	
	/**
	 * 消息进入数据库的时间值
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="消息进入数据库的时间值")
	private Date create_time;
	
	/**
	 * 发送消息的时间戳
	 */
	@Column(name="msgTimestamp",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="发送消息的时间戳")
	private Date msgTimestamp;
	
	/**
	 * 聊天记录状态 1正常 -9 删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="聊天记录状态 1正常 -9 删除")
	private Integer status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getFromid() {
		return fromid;
	}

	public void setFromid(Long fromid) {
		this.fromid = fromid;
	}
	
	public Long getToid() {
		return toid;
	}

	public void setToid(Long toid) {
		this.toid = toid;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	public String getConvType() {
		return convType;
	}

	public void setConvType(String convType) {
		this.convType = convType;
	}
	
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getMsgTimestamp() {
		return msgTimestamp;
	}

	public void setMsgTimestamp(Date msgTimestamp) {
		this.msgTimestamp = msgTimestamp;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}