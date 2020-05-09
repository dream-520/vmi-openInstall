package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播亲密排行榜假数据[t_anchor_intimate_rankings_fake] 表对应的实体类
 * @author lipeng
 * @Date 2019-07-18 17:17:56
 *
 */
@Table(name="t_anchor_intimate_rankings_fake")
public class AnchorIntimateRankingsFakeEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 主播用户ID
	 */
	@Column(name="anchor_userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播用户ID")
	private Long anchor_userid;
	
	/**
	 * 普通用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="普通用户ID")
	private Long userid;
	
	/**
	 * 总收入
	 */
	@Column(name="total_money",nullable=true,jdbcType=JdbcType.INTEGER,comment="总收入")
	private Integer total_money;
	
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getAnchor_userid() {
		return anchor_userid;
	}

	public void setAnchor_userid(Long anchor_userid) {
		this.anchor_userid = anchor_userid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Integer total_money) {
		this.total_money = total_money;
	}
	
}