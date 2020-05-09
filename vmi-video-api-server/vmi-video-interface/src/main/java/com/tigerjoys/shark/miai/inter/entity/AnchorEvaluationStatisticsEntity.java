package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播评价统计[t_anchor_evaluation_statistics] 表对应的实体类
 * @author yangjunming
 * @Date 2018-11-05 18:14:49
 *
 */
@Table(name="t_anchor_evaluation_statistics")
public class AnchorEvaluationStatisticsEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 主播用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播用户ID")
	private Long userid;
	
	/**
	 * 标签ID
	 */
	@Column(name="label_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="标签ID")
	private Long label_id;
	
	/**
	 * 评价总数
	 */
	@Column(name="total_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="评价总数")
	private Integer total_num;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
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
	
	public Long getLabel_id() {
		return label_id;
	}

	public void setLabel_id(Long label_id) {
		this.label_id = label_id;
	}
	
	public Integer getTotal_num() {
		return total_num;
	}

	public void setTotal_num(Integer total_num) {
		this.total_num = total_num;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
}