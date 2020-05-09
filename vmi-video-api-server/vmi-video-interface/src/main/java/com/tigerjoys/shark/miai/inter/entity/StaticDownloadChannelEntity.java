package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  遍历日志获取到的渠道信息[t_static_download_channel] 表对应的实体类
 * @author yangjunming
 * @Date 2018-05-09 10:53:59
 *
 */
@Table(name="t_static_download_channel")
public class StaticDownloadChannelEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 对应的渠道标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="对应的渠道标示")
	private Long id;
	
	/**
	 * 对应的渠道名称
	 */
	@Column(name="channel",nullable=false,jdbcType=JdbcType.VARCHAR,comment="对应的渠道名称")
	private String channel;
	
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
	
}