package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  监黄业务警告表[t_anchor_video_check_warn] 表对应的实体类
 * @author shiming
 * @Date 2019-08-23 20:42:31
 *
 */
@Table(name="t_anchor_video_check_warn")
public class AnchorVideoCheckWarnEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标识
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标识")
	private Long id;
	
	/**
	 * 检测点id
	 */
	@Column(name="checkId",nullable=true,jdbcType=JdbcType.BIGINT,comment="检测点id")
	private Long checkId;
	
	/**
	 * 用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 对方id
	 */
	@Column(name="otherid",nullable=true,jdbcType=JdbcType.BIGINT,comment="对方id")
	private Long otherid;
	
	/**
	 * 通话标识
	 */
	@Column(name="serialNum",nullable=true,jdbcType=JdbcType.BIGINT,comment="通话标识")
	private Long serialNum;
	
	/**
	 * 警告类型 1 色情 2 低俗
	 */
	@Column(name="warn",nullable=true,jdbcType=JdbcType.TINYINT,comment="警告类型 1 色情 2 低俗")
	private Integer warn;
	
	/**
	 * 警告类型  0 不发送   1 发送色情警告 2 发送色情切断警告 3 发送低俗警告  4 发送低俗切断警告
	 */
	@Column(name="warnType",nullable=true,jdbcType=JdbcType.TINYINT,comment="警告类型  0 不发送   1 发送色情警告 2 发送色情切断警告 3 发送低俗警告  4 发送低俗切断警告")
	private Integer warnType;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getOtherid() {
		return otherid;
	}

	public void setOtherid(Long otherid) {
		this.otherid = otherid;
	}
	
	public Long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}
	
	public Integer getWarn() {
		return warn;
	}

	public void setWarn(Integer warn) {
		this.warn = warn;
	}
	
	public Integer getWarnType() {
		return warnType;
	}

	public void setWarnType(Integer warnType) {
		this.warnType = warnType;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}