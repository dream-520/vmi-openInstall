package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  转盘消费记录[t_user_chat_turntable_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-07-27 16:19:53
 *
 */
@Table(name="t_user_chat_turntable_log")
public class UserChatTurntableLogEntity extends BaseEntity implements Serializable {

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
	 * 转盘ID
	 */
	@Column(name="turntable_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="转盘ID")
	private Long turntable_id;
	
	/**
	 * 转盘名称
	 */
	@Column(name="turntable_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="转盘名称")
	private String turntable_name;
	
	/**
	 * 价格（钻）
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="价格（钻）")
	private Integer diamond;
	
	/**
	 * 发送方ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="发送方ID")
	private Long user_id;
	
	/**
	 * 接收方ID
	 */
	@Column(name="other_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="接收方ID")
	private Long other_id;
	
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
	
	public Long getTurntable_id() {
		return turntable_id;
	}

	public void setTurntable_id(Long turntable_id) {
		this.turntable_id = turntable_id;
	}
	
	public String getTurntable_name() {
		return turntable_name;
	}

	public void setTurntable_name(String turntable_name) {
		this.turntable_name = turntable_name;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
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
	
}