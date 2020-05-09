package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充钻自定义价格列表[t_recharge_custom_price] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-11 15:01:26
 *
 */
@Table(name="t_recharge_custom_price")
public class RechargeCustomPriceEntity extends BaseEntity implements Serializable {

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
	 * 分类ID
	 */
	@Column(name="category_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="分类ID")
	private Long category_id;
	
	/**
	 * 充值金额(分)
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值金额(分)")
	private Integer money;
	
	/**
	 * 充值金额对应钻石数量
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="充值金额对应钻石数量")
	private Integer diamond;
	
	/**
	 * 0 送钻  1 送钱
	 */
	@Column(name="donor_type",nullable=false,jdbcType=JdbcType.TINYINT,comment="0 送钻  1 送钱")
	private Integer donor_type;
	
	/**
	 * 0 首次,1 每次
	 */
	@Column(name="donor_times",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 首次,1 每次")
	private Integer donor_times;
	
	/**
	 * 赠送钻石数量
	 */
	@Column(name="donor",nullable=false,jdbcType=JdbcType.INTEGER,comment="赠送钻石数量")
	private Integer donor;
	
	/**
	 * 主标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="主标题")
	private String title;
	
	/**
	 * 描述
	 */
	@Column(name="description",nullable=false,jdbcType=JdbcType.VARCHAR,comment="描述")
	private String description;
	
	/**
	 * 复充标题 
	 */
	@Column(name="repetition_desc",nullable=true,jdbcType=JdbcType.VARCHAR,comment="复充标题 ")
	private String repetition_desc;
	
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
	
	/**
	 *  0  视频 1 音频
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment=" 0  视频 1 音频")
	private Integer type;
	
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
	
	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Integer getDonor_type() {
		return donor_type;
	}

	public void setDonor_type(Integer donor_type) {
		this.donor_type = donor_type;
	}
	
	public Integer getDonor_times() {
		return donor_times;
	}

	public void setDonor_times(Integer donor_times) {
		this.donor_times = donor_times;
	}
	
	public Integer getDonor() {
		return donor;
	}

	public void setDonor(Integer donor) {
		this.donor = donor;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRepetition_desc() {
		return repetition_desc;
	}

	public void setRepetition_desc(String repetition_desc) {
		this.repetition_desc = repetition_desc;
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
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}