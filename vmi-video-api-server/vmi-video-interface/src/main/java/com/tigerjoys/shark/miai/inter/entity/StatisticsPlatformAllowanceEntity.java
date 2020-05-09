package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  平台渠道每日补助统计[t_statistics_platform_allowance] 表对应的实体类
 * @author shiming
 * @Date 2019-07-01 16:22:15
 *
 */
@Table(name="t_statistics_platform_allowance")
public class StatisticsPlatformAllowanceEntity extends BaseEntity implements Serializable {

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
	 * 审查时间
	 */
	@Column(name="check_date",nullable=true,jdbcType=JdbcType.DATE,comment="审查时间")
	private Date check_date;
	
	/**
	 * 补助钻石值
	 */
	@Column(name="diamond",nullable=true,jdbcType=JdbcType.INTEGER,comment="补助钻石值")
	private Integer diamond;
	
	/**
	 * 补助渠道
	 */
	@Column(name="channel",nullable=true,jdbcType=JdbcType.VARCHAR,comment="补助渠道")
	private String channel;
	
	/**
	 * 补助人数
	 */
	@Column(name="peoples",nullable=true,jdbcType=JdbcType.INTEGER,comment="补助人数")
	private Integer peoples;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Integer getPeoples() {
		return peoples;
	}

	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}