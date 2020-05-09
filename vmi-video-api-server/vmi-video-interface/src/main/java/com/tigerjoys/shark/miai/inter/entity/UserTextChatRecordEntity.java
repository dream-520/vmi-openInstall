package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户图文聊天记录[t_user_text_chat_record] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-08-21 15:03:18
 *
 */
@Table(name="t_user_text_chat_record")
public class UserTextChatRecordEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id(increment=false)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
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
	
}