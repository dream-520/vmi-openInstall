package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户视频会话记录[t_user_video_chat_record] 表对应的实体类
 * @author yangjunming
 * @Date 2018-09-19 10:48:31
 *
 */
@Table(name="t_user_video_chat_record")
public class UserVideoChatRecordEntity extends BaseEntity implements Serializable {

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
	 * 聊天开始时间
	 */
	@Column(name="start_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="聊天开始时间")
	private Date start_time;
	
	/**
	 * 聊天结束时间
	 */
	@Column(name="end_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="聊天结束时间")
	private Date end_time;
	
	/**
	 * 主叫用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="主叫用户ID")
	private Long user_id;
	
	/**
	 * 被叫用户ID
	 */
	@Column(name="other_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="被叫用户ID")
	private Long other_id;
	
	/**
	 * 消费钻石数
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.BIGINT,comment="消费钻石数")
	private Long diamond;
	
	/**
	 * 类型 1-音频，2视频
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型 1-音频，2视频")
	private Integer type;
	
	/**
	 * 渠道类型
	 */
	@Column(name="channel_type",nullable=true,jdbcType=JdbcType.INTEGER,comment="渠道类型")
	private Integer channel_type;
	
	/**
	 * 扣费翻转： 1-是，0-否
	 */
	@Column(name="reverse",nullable=false,jdbcType=JdbcType.TINYINT,comment="扣费翻转： 1-是，0-否")
	private Integer reverse;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getOther_id() {
		return other_id;
	}

	public void setOther_id(Long other_id) {
		this.other_id = other_id;
	}
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(Integer channel_type) {
		this.channel_type = channel_type;
	}
	
	public Integer getReverse() {
		return reverse;
	}

	public void setReverse(Integer reverse) {
		this.reverse = reverse;
	}
	
}