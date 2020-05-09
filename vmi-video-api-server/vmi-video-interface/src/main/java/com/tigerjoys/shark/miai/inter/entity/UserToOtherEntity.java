package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  聊天历史用户之间映射关系[t_user_to_other] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-05-04 17:03:37
 *
 */
@Table(name="t_user_to_other")
public class UserToOtherEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="主键ID")
	private Long id;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 聊天发起用户Id
	 */
	@Column(name="from_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="聊天发起用户Id")
	private Long from_id;
	
	/**
	 * 聊天接受用户Id
	 */
	@Column(name="to_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="聊天接受用户Id")
	private Long to_id;
	
	/**
	 * 从网易同步历史消息状态:0，未同步； 1，已同步
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="从网易同步历史消息状态:0，未同步； 1，已同步")
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
	
	public Long getFrom_id() {
		return from_id;
	}

	public void setFrom_id(Long from_id) {
		this.from_id = from_id;
	}
	
	public Long getTo_id() {
		return to_id;
	}

	public void setTo_id(Long to_id) {
		this.to_id = to_id;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}