package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  渠道连接访问量统计表[t_static_download_visit] 表对应的实体类
 * @author yangjunming
 * @Date 2018-05-09 10:53:59
 *
 */
@Table(name="t_static_download_visit")
public class StaticDownloadVisitEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 统计数据唯一标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="统计数据唯一标示")
	private Long id;
	
	/**
	 * 下载来源的渠道标示
	 */
	@Column(name="channel",nullable=false,jdbcType=JdbcType.VARCHAR,comment="下载来源的渠道标示")
	private String channel;
	
	/**
	 * 统计类型标示 1 访问量 2 独立ip数
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="统计类型标示 1 访问量 2 独立ip数")
	private Integer type;
	
	/**
	 * 统计数量
	 */
	@Column(name="num",nullable=false,jdbcType=JdbcType.BIGINT,comment="统计数量")
	private Long num;
	
	/**
	 * 插入统计数据的时间
	 */
	@Column(name="createTime",nullable=false,jdbcType=JdbcType.DATE,comment="插入统计数据的时间")
	private Date createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}