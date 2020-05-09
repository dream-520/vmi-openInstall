package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  1分钟兔费聊体验日志[t_free_video_chat_experience_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-12-06 16:14:10
 *
 */
@Table(name="t_free_video_chat_experience_log")
public class FreeVideoChatExperienceLogEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 *  0 免费  1 购买
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment=" 0 免费  1 购买")
	private Integer type;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
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
	
}