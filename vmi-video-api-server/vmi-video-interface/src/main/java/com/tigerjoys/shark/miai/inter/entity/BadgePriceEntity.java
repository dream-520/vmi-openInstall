package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  诚信徽章价格列表[t_badge_price] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2017-11-21 18:58:49
 *
 */
@Table(name="t_badge_price")
public class BadgePriceEntity extends BaseEntity implements Serializable {

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
	 * 唯一标识符（参考BadgeTypeEnum）
	 */
	@Column(name="code",nullable=true,jdbcType=JdbcType.TINYINT,comment="唯一标识符（参考BadgeTypeEnum）")
	private Integer code;
	
	/**
	 * 钻石数量
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="钻石数量")
	private Integer diamond;
	
	/**
	 * 徽章名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="徽章名称")
	private String name;
	
	/**
	 * 徽章小图标
	 */
	@Column(name="small_image",nullable=false,jdbcType=JdbcType.VARCHAR,comment="徽章小图标")
	private String small_image;
	
	/**
	 * 徽章大图标
	 */
	@Column(name="big_image",nullable=false,jdbcType=JdbcType.VARCHAR,comment="徽章大图标")
	private String big_image;
	
	/**
	 * 有效时长（月）
	 */
	@Column(name="duration",nullable=false,jdbcType=JdbcType.TINYINT,comment="有效时长（月）")
	private Integer duration;
	
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
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSmall_image() {
		return small_image;
	}

	public void setSmall_image(String small_image) {
		this.small_image = small_image;
	}
	
	public String getBig_image() {
		return big_image;
	}

	public void setBig_image(String big_image) {
		this.big_image = big_image;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
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