package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播约会表[t_anchor_appoint] 表对应的实体类
 * @author shiming
 * @Date 2020-01-07 20:00:05
 *
 */
@Table(name="t_anchor_appoint")
public class AnchorAppointEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 约会id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="约会id")
	private Long id;
	
	/**
	 * 主播id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播id")
	private Long userid;
	
	/**
	 * 约会描述
	 */
	@Column(name="content",nullable=true,jdbcType=JdbcType.VARCHAR,comment="约会描述")
	private String content;
	
	/**
	 * 约会类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="约会类型")
	private Integer type;
	
	/**
	 * 约会状态 0 约会中 -1 约会过期了
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="约会状态 0 约会中 -1 约会过期了")
	private Integer state;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 结束时间
	 */
	@Column(name="end_time",nullable=true,jdbcType=JdbcType.DATE,comment="结束时间")
	private Date end_time;
	
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
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
}