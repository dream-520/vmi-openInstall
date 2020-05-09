package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户视频会话记录流水（每分钟计一次）[t_user_video_chat_record_log] 表对应的实体类
 * @author yangjunming
 * @Date 2018-09-19 10:48:32
 *
 */
@Table(name="t_user_video_chat_record_log")
public class UserVideoChatRecordLogEntity extends BaseEntity implements Serializable {

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
	 * 会话记录ID
	 */
	@Column(name="record_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="会话记录ID")
	private Long record_id;
	
	/**
	 * 消费钻石数
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.BIGINT,comment="消费钻石数")
	private Long diamond;
	
	/**
	 * 渠道类型
	 */
	@Column(name="channel_type",nullable=true,jdbcType=JdbcType.INTEGER,comment="渠道类型")
	private Integer channel_type;
	
	/**
	 * 是否最后一次流水，0-否、1-是
	 */
	@Column(name="last",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否最后一次流水，0-否、1-是")
	private Integer last;
	
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
	
	public Long getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Long record_id) {
		this.record_id = record_id;
	}
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Integer getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(Integer channel_type) {
		this.channel_type = channel_type;
	}
	
	public Integer getLast() {
		return last;
	}

	public void setLast(Integer last) {
		this.last = last;
	}
	
}