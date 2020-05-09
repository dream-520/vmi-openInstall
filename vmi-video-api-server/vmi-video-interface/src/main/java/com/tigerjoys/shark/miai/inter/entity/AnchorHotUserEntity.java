package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  ios推荐主播类别关系表[t_anchor_hot_user] 表对应的实体类
 * @author shiming
 * @Date 2019-03-25 15:25:10
 *
 */
@Table(name="t_anchor_hot_user")
public class AnchorHotUserEntity extends BaseEntity implements Serializable {

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
	 * 所属标识
	 */
	@Column(name="tab",nullable=false,jdbcType=JdbcType.BIGINT,comment="所属标识")
	private Long tab;
	
	/**
	 * userid
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="userid")
	private Long userid;
	
	/**
	 * 添加时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="添加时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTab() {
		return tab;
	}

	public void setTab(Long tab) {
		this.tab = tab;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}