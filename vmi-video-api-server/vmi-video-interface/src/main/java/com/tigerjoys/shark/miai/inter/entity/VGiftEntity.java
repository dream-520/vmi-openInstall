package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_gift] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Table(name="t_v_gift")
public class VGiftEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标示")
	private Long id;
	
	/**
	 * 类型
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型")
	private Integer type;
	
	/**
	 * 礼物名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="礼物名称")
	private String name;
	
	/**
	 * 消耗的V币数量
	 */
	@Column(name="vcoin",nullable=false,jdbcType=JdbcType.INTEGER,comment="消耗的V币数量")
	private Integer vcoin;
	
	/**
	 * interactionType
	 */
	@Column(name="interactionType",nullable=false,jdbcType=JdbcType.TINYINT,comment="interactionType")
	private Integer interactionType;
	
	/**
	 * specialEffect
	 */
	@Column(name="specialEffect",nullable=false,jdbcType=JdbcType.TINYINT,comment="specialEffect")
	private Integer specialEffect;
	
	/**
	 * 图片
	 */
	@Column(name="picture",nullable=false,jdbcType=JdbcType.VARCHAR,comment="图片")
	private String picture;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getVcoin() {
		return vcoin;
	}

	public void setVcoin(Integer vcoin) {
		this.vcoin = vcoin;
	}
	
	public Integer getInteractionType() {
		return interactionType;
	}

	public void setInteractionType(Integer interactionType) {
		this.interactionType = interactionType;
	}
	
	public Integer getSpecialEffect() {
		return specialEffect;
	}

	public void setSpecialEffect(Integer specialEffect) {
		this.specialEffect = specialEffect;
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}