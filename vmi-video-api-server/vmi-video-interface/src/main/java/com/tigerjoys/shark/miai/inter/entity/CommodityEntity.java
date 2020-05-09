package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  领取商品表[t_commodity] 表对应的实体类
 * @author lipeng
 * @Date 2018-12-07 15:16:38
 *
 */
@Table(name="t_commodity")
public class CommodityEntity extends BaseEntity implements Serializable {

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
	 * 商品图片
	 */
	@Column(name="photo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="商品图片")
	private String photo;
	
	/**
	 * 商品标题
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="商品标题")
	private String name;
	
	/**
	 * 淘宝链接
	 */
	@Column(name="tb_url",nullable=false,jdbcType=JdbcType.VARCHAR,comment="淘宝链接")
	private String tb_url;
	
	/**
	 * 分组ID
	 */
	@Column(name="group_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="分组ID")
	private Long group_id;
	
	/**
	 * 状态1正常 0下架 -9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态1正常 0下架 -9删除")
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTb_url() {
		return tb_url;
	}

	public void setTb_url(String tb_url) {
		this.tb_url = tb_url;
	}
	
	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}