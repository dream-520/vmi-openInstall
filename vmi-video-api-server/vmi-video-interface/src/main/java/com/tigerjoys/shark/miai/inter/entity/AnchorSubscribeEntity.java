package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_anchor_subscribe] 表对应的实体类
 * @author shiming
 * @Date 2019-11-11 14:01:46
 *
 */
@Table(name="t_anchor_subscribe")
public class AnchorSubscribeEntity extends BaseEntity implements Serializable {

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
	 * 预约的主播
	 */
	@Column(name="anchorid",nullable=true,jdbcType=JdbcType.BIGINT,comment="预约的主播")
	private Long anchorid;
	
	/**
	 * 预约用户
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="预约用户")
	private Long userid;
	
	/**
	 * 预约消费的钻石值
	 */
	@Column(name="diamond",nullable=true,jdbcType=JdbcType.INTEGER,comment="预约消费的钻石值")
	private Integer diamond;
	
	/**
	 * 预约状态  0 预约中  1 预约失败 2 预约成功
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="预约状态  0 预约中  1 预约失败 2 预约成功")
	private Integer state;
	
	/**
	 * 预约类型 0 视频 1 音频
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="预约类型 0 视频 1 音频")
	private Integer type;
	
	/**
	 * 预约创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="预约创建时间")
	private Date create_time;
	
	/**
	 * 预约结束时间
	 */
	@Column(name="end_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="预约结束时间")
	private Date end_time;
	
	/**
	 * 条目更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="条目更新时间")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}