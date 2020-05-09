package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  约定场地[t_appoint_site] 表对应的实体类
 * @author yangjunming
 * @Date 2017-12-14 17:56:54
 *
 */
@Table(name="t_appoint_site")
public class AppointSiteEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 场地ID
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="场地ID")
	private Long id;
	
	/**
	 * 场地类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.INTEGER,comment="场地类型")
	private Integer type;
	
	/**
	 * 场地名称
	 */
	@Column(name="title",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地名称")
	private String title;
	
	/**
	 * 场地图片地址
	 */
	@Column(name="picture",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地图片地址")
	private String picture;
	
	/**
	 * 星级
	 */
	@Column(name="rating",nullable=false,jdbcType=JdbcType.INTEGER,comment="星级")
	private Integer rating;
	
	/**
	 * 场地使用事项
	 */
	@Column(name="matters",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地使用事项")
	private String matters;
	
	/**
	 * 场地地址
	 */
	@Column(name="address",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地地址")
	private String address;
	
	/**
	 * 场地信息
	 */
	@Column(name="info",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地信息")
	private String info;
	
	/**
	 * 场地营业时间
	 */
	@Column(name="business_time",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地营业时间")
	private String business_time;
	
	/**
	 * 场地设施
	 */
	@Column(name="facilities",nullable=true,jdbcType=JdbcType.VARCHAR,comment="场地设施")
	private String facilities;
	
	/**
	 * 场地费用
	 */
	@Column(name="cost",nullable=true,jdbcType=JdbcType.INTEGER,comment="场地费用")
	private Integer cost;
	
	/**
	 * 客服咨询
	 */
	@Column(name="telephone",nullable=true,jdbcType=JdbcType.VARCHAR,comment="客服咨询")
	private String telephone;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public String getMatters() {
		return matters;
	}

	public void setMatters(String matters) {
		this.matters = matters;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getBusiness_time() {
		return business_time;
	}

	public void setBusiness_time(String business_time) {
		this.business_time = business_time;
	}
	
	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}