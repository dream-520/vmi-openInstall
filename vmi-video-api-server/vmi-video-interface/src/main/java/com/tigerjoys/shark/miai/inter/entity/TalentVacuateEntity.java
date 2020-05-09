package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  达人分成比例表[t_talent_vacuate] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-11-15 15:01:09
 *
 */
@Table(name="t_talent_vacuate")
public class TalentVacuateEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 区间下限约会订单数量
	 */
	@Column(name="down_amount",nullable=false,jdbcType=JdbcType.INTEGER,comment="区间下限约会订单数量")
	private Integer down_amount;
	
	/**
	 * 区间上限限约会订单数量
	 */
	@Column(name="up_amount",nullable=false,jdbcType=JdbcType.INTEGER,comment="区间上限限约会订单数量")
	private Integer up_amount;
	
	/**
	 * 达人分成比例（%）
	 */
	@Column(name="talent_ratio",nullable=false,jdbcType=JdbcType.SMALLINT,comment="达人分成比例（%）")
	private Integer talent_ratio;
	
	/**
	 * 代理人分成比例（%）
	 */
	@Column(name="proxy_ratio",nullable=false,jdbcType=JdbcType.SMALLINT,comment="代理人分成比例（%）")
	private Integer proxy_ratio;
	
	/**
	 * 状态；-9，删除；0，正常
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态；-9，删除；0，正常")
	private Integer status;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Integer getDown_amount() {
		return down_amount;
	}

	public void setDown_amount(Integer down_amount) {
		this.down_amount = down_amount;
	}
	
	public Integer getUp_amount() {
		return up_amount;
	}

	public void setUp_amount(Integer up_amount) {
		this.up_amount = up_amount;
	}
	
	public Integer getTalent_ratio() {
		return talent_ratio;
	}

	public void setTalent_ratio(Integer talent_ratio) {
		this.talent_ratio = talent_ratio;
	}
	
	public Integer getProxy_ratio() {
		return proxy_ratio;
	}

	public void setProxy_ratio(Integer proxy_ratio) {
		this.proxy_ratio = proxy_ratio;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}