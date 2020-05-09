package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  礼物列表[t_user_chat_gift] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-07-24 12:22:00
 *
 */
@Table(name="t_user_chat_gift")
public class UserChatGiftEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 礼物名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="礼物名称")
	private String name;
	
	/**
	 * 价格（钻）
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="价格（钻）")
	private Integer diamond;
	
	/**
	 * 图标
	 */
	@Column(name="icon",nullable=false,jdbcType=JdbcType.VARCHAR,comment="图标")
	private String icon;
	
	/**
	 * 类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="类型  0普通  1 奢侈 2 vip")
	private Integer type;
	
	/**
	 * 动画标记  0 无 1 动画
	 */
	@Column(name="animation_flag",nullable=true,jdbcType=JdbcType.TINYINT,comment="动画标记  0 无 1 动画")
	private Integer animation_flag;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="优先级")
	private Integer priority;
	
	/**
	 * 状态，1正常，0停用
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态，1正常，0停用")
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getAnimation_flag() {
		return animation_flag;
	}

	public void setAnimation_flag(Integer animation_flag) {
		this.animation_flag = animation_flag;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}