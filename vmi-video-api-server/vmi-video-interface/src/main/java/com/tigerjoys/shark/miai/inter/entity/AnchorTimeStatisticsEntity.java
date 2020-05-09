package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播每日在线统计表[t_anchor_time_statistics] 表对应的实体类
 * @author shiming
 * @Date 2019-04-16 14:43:20
 *
 */
@Table(name="t_anchor_time_statistics")
public class AnchorTimeStatisticsEntity extends BaseEntity implements Serializable {

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
	 * 主播id值
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播id值")
	private Long userid;
	
	/**
	 * 在线时长
	 */
	@Column(name="minutes",nullable=true,jdbcType=JdbcType.INTEGER,comment="在线时长")
	private Integer minutes;
	
	/**
	 * 所处日期
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.DATE,comment="所处日期")
	private Date create_time;
	
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
	
	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}