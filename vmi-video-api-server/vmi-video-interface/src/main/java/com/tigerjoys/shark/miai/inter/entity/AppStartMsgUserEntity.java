package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户发送消息的情况[t_app_start_msg_user] 表对应的实体类
 * @author shiming
 * @Date 2019-01-04 16:33:58
 *
 */
@Table(name="t_app_start_msg_user")
public class AppStartMsgUserEntity extends BaseEntity implements Serializable {

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
	 * 对应的日期
	 */
	@Column(name="time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="对应的日期")
	private Date time;
	
	/**
	 * 可以发送的消息的索引
	 */
	@Column(name="indexs",nullable=false,jdbcType=JdbcType.INTEGER,comment="可以发送的消息的索引")
	private Integer indexs;
	
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
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public Integer getIndexs() {
		return indexs;
	}

	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
	}
	
}