package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_app_msg_scene_detail] 表对应的实体类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
@Table(name="t_app_msg_scene_detail")
public class AppMsgSceneDetailEntity extends BaseEntity implements Serializable {

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
	 * 发送用户id
	 */
	@Column(name="from_userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="发送用户id")
	private Long from_userid;
	
	/**
	 * 接受用户id
	 */
	@Column(name="to_userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="接受用户id")
	private Long to_userid;
	
	/**
	 * 使用的场景
	 */
	@Column(name="scene",nullable=false,jdbcType=JdbcType.INTEGER,comment="使用的场景")
	private Integer scene;
	
	/**
	 * 当前场景即将发送的条目
	 */
	@Column(name="item",nullable=false,jdbcType=JdbcType.INTEGER,comment="当前场景即将发送的条目")
	private Integer item;
	
	/**
	 * 整个场景对话是否完成 0为完成 1完成
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="整个场景对话是否完成 0为完成 1完成")
	private Integer state;
	
	/**
	 * 发送收条场景对话的时间
	 */
	@Column(name="send_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="发送收条场景对话的时间")
	private Date send_time;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getFrom_userid() {
		return from_userid;
	}

	public void setFrom_userid(Long from_userid) {
		this.from_userid = from_userid;
	}
	
	public Long getTo_userid() {
		return to_userid;
	}

	public void setTo_userid(Long to_userid) {
		this.to_userid = to_userid;
	}
	
	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}
	
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}