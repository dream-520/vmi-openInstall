package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  充值价格列表[t_recharge_price] 表对应的实体类
 * @author yangjunming
 * @Date 2019-09-11 15:01:26
 *
 */
@Table(name="t_recharge_price")
public class RechargePriceEntity extends BaseEntity implements Serializable {

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
	 * 商品标识符
	 */
	@Column(name="price_identifier",nullable=true,jdbcType=JdbcType.VARCHAR,comment="商品标识符")
	private String price_identifier;
	
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
	 * 系统类型，1安卓，2苹果
	 */
	@Column(name="platform",nullable=false,jdbcType=JdbcType.TINYINT,comment="系统类型，1安卓，2苹果")
	private Integer platform;
	
	/**
	 * 0 视频 1 音频 2 IPA视频 3 IPA音频
	 */
	@Column(name="price_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 视频 1 音频 2 IPA视频 3 IPA音频")
	private Integer price_type;
	
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
	
	public String getPrice_identifier() {
		return price_identifier;
	}

	public void setPrice_identifier(String price_identifier) {
		this.price_identifier = price_identifier;
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
	
	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
	public Integer getPrice_type() {
		return price_type;
	}

	public void setPrice_type(Integer price_type) {
		this.price_type = price_type;
	}
	
}