package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  商品发货表[t_commodity_shipments] 表对应的实体类
 * @author lipeng
 * @Date 2018-12-07 15:21:43
 *
 */
@Table(name="t_commodity_shipments")
public class CommodityShipmentsEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 商品ID
	 */
	@Column(name="commodity_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="商品ID")
	private Long commodity_id;
	
	/**
	 * 商品标题
	 */
	@Column(name="commodity_name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="商品标题")
	private String commodity_name;
	
	/**
	 * 商品图片
	 */
	@Column(name="commodity_photo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="商品标题")
	private String commodity_photo;
	
	/**
	 * 淘宝链接
	 */
	@Column(name="commodity_tb_url",nullable=false,jdbcType=JdbcType.VARCHAR,comment="淘宝链接")
	private String commodity_tb_url;
	
	/**
	 * 收货人姓名
	 */
	@Column(name="consignee_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="收货人姓名")
	private String consignee_name;
	
	/**
	 * 收货人电话
	 */
	@Column(name="consignee_phone",nullable=true,jdbcType=JdbcType.VARCHAR,comment="收货人电话")
	private String consignee_phone;
	
	/**
	 * 收货人地址
	 */
	@Column(name="consignee_site",nullable=true,jdbcType=JdbcType.VARCHAR,comment="收货人地址")
	private String consignee_site;
	
	/**
	 * 发货时间
	 */
	@Column(name="delivery_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="发货时间")
	private Date delivery_time;
	
	/**
	 * 发货状态 1发货 0未发
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="发货状态 1发货 0未发")
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
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Long getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(Long commodity_id) {
		this.commodity_id = commodity_id;
	}
	
	public String getCommodity_name() {
		return commodity_name;
	}

	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}
	
	public String getCommodity_photo() {
		return commodity_photo;
	}

	public void setCommodity_photo(String commodity_photo) {
		this.commodity_photo = commodity_photo;
	}

	public String getCommodity_tb_url() {
		return commodity_tb_url;
	}

	public void setCommodity_tb_url(String commodity_tb_url) {
		this.commodity_tb_url = commodity_tb_url;
	}
	
	public String getConsignee_name() {
		return consignee_name;
	}

	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	
	public String getConsignee_phone() {
		return consignee_phone;
	}

	public void setConsignee_phone(String consignee_phone) {
		this.consignee_phone = consignee_phone;
	}
	
	public String getConsignee_site() {
		return consignee_site;
	}

	public void setConsignee_site(String consignee_site) {
		this.consignee_site = consignee_site;
	}
	
	public Date getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}