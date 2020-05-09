package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_app_msg_scene_user] 表对应的实体类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
@Table(name="t_app_msg_scene_user")
public class AppMsgSceneUserEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 对应日期发送消息的次数
	 */
	@Column(name="count",nullable=false,jdbcType=JdbcType.TINYINT,comment="对应日期发送消息的次数")
	private Integer count;
	
	/**
	 * 用户可以使用的场景
	 */
	@Column(name="scene",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户可以使用的场景")
	private String scene;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 当日访问时间
	 */
	@Column(name="vtime",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="当日访问时间")
	private Date vtime;
	
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
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getVtime() {
		return vtime;
	}

	public void setVtime(Date vtime) {
		this.vtime = vtime;
	}
	
}