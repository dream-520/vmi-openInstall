package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_gift] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Table(name="t_v_user_gift")
public class VUserGiftEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 当前礼物对应的数量
	 */
	@Column(name="nums",nullable=false,jdbcType=JdbcType.INTEGER,comment="当前礼物对应的数量")
	private Integer nums;
	
	/**
	 * 礼物的id值
	 */
	@Column(name="giftId",nullable=false,jdbcType=JdbcType.BIGINT,comment="礼物的id值")
	private Long giftId;
	
	/**
	 * 礼物对应的消耗币
	 */
	@Column(name="vcoin",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物对应的消耗币")
	private Integer vcoin;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
	
	public Long getGiftId() {
		return giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}
	
	public Integer getVcoin() {
		return vcoin;
	}

	public void setVcoin(Integer vcoin) {
		this.vcoin = vcoin;
	}
	
}