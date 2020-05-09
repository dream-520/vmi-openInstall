package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  购买守户和VIP[t_guard_vip_category] 表对应的实体类
 * @author yangjunming
 * @Date 2020-02-25 16:18:20
 *
 */
@Table(name="t_guard_vip_category")
public class GuardVipCategoryEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 类别ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="类别ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 类型 1 守护 2 VIP
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型 1 守护 2 VIP")
	private Integer type;
	
	/**
	 * 名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="名称")
	private String name;
	
	/**
	 * 小红花副标题
	 */
	@Column(name="description",nullable=true,jdbcType=JdbcType.VARCHAR,comment="小红花副标题")
	private String description;
	
	/**
	 * 等级
	 */
	@Column(name="level",nullable=false,jdbcType=JdbcType.INTEGER,comment="等级")
	private Integer level;
	
	/**
	 * 图片地址
	 */
	@Column(name="icon",nullable=true,jdbcType=JdbcType.VARCHAR,comment="图片地址")
	private String icon;
	
	/**
	 * 背景图片
	 */
	@Column(name="icon_back",nullable=true,jdbcType=JdbcType.VARCHAR,comment="背景图片")
	private String icon_back;
	
	/**
	 * 小图标
	 */
	@Column(name="icon_small",nullable=true,jdbcType=JdbcType.VARCHAR,comment="小图标")
	private String icon_small;
	
	/**
	 * 赠送小红花
	 */
	@Column(name="donor",nullable=false,jdbcType=JdbcType.INTEGER,comment="赠送小红花")
	private Integer donor;
	
	/**
	 * 增送钻石
	 */
	@Column(name="diamond",nullable=true,jdbcType=JdbcType.BIGINT,comment="增送钻石")
	private Long diamond;
	
	/**
	 * 天数
	 */
	@Column(name="days",nullable=false,jdbcType=JdbcType.INTEGER,comment="天数")
	private Integer days;
	
	/**
	 * 金额(分)
	 */
	@Column(name="money",nullable=true,jdbcType=JdbcType.INTEGER,comment="金额(分)")
	private Integer money;
	
	/**
	 * IAP标识
	 */
	@Column(name="identifier",nullable=true,jdbcType=JdbcType.VARCHAR,comment="IAP标识")
	private String identifier;
	
	/**
	 * 每日分钟数
	 */
	@Column(name="buy_num",nullable=false,jdbcType=JdbcType.BIGINT,comment="每日分钟数")
	private Long buy_num;
	
	/**
	 * 显示顺序
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="显示顺序")
	private Integer priority;
	
	/**
	 * 状态  0下架  1上架
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态  0下架  1上架")
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon_back() {
		return icon_back;
	}

	public void setIcon_back(String icon_back) {
		this.icon_back = icon_back;
	}
	
	public String getIcon_small() {
		return icon_small;
	}

	public void setIcon_small(String icon_small) {
		this.icon_small = icon_small;
	}
	
	public Integer getDonor() {
		return donor;
	}

	public void setDonor(Integer donor) {
		this.donor = donor;
	}
	
	public Long getDiamond() {
		return diamond;
	}

	public void setDiamond(Long diamond) {
		this.diamond = diamond;
	}
	
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public Long getBuy_num() {
		return buy_num;
	}

	public void setBuy_num(Long buy_num) {
		this.buy_num = buy_num;
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