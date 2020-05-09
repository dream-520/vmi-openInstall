package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播评价日志[t_anchor_evaluation_log] 表对应的实体类
 * @author yangjunming
 * @Date 2020-02-25 15:02:19
 *
 */
@Table(name="t_anchor_evaluation_log")
public class AnchorEvaluationLogEntity extends BaseEntity implements Serializable {

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
	 * 普通用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="普通用户ID")
	private Long userid;
	
	/**
	 * 主播用户ID
	 */
	@Column(name="anchor_userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="主播用户ID")
	private Long anchor_userid;
	
	/**
	 * 1 音频  2 视频
	 */
	@Column(name="av_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="1 音频  2 视频")
	private Integer av_type;
	
	/**
	 * 订单号
	 */
	@Column(name="orderid",nullable=true,jdbcType=JdbcType.BIGINT,comment="订单号")
	private Long orderid;
	
	/**
	 * 评价标签
	 */
	@Column(name="evaluation_labels",nullable=true,jdbcType=JdbcType.VARCHAR,comment="评价标签")
	private String evaluation_labels;
	
	/**
	 * 文本评论
	 */
	@Column(name="evaluation_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="文本评论")
	private String evaluation_text;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
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
	
	public Long getAnchor_userid() {
		return anchor_userid;
	}

	public void setAnchor_userid(Long anchor_userid) {
		this.anchor_userid = anchor_userid;
	}
	
	public Integer getAv_type() {
		return av_type;
	}

	public void setAv_type(Integer av_type) {
		this.av_type = av_type;
	}
	
	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public String getEvaluation_labels() {
		return evaluation_labels;
	}

	public void setEvaluation_labels(String evaluation_labels) {
		this.evaluation_labels = evaluation_labels;
	}
	
	public String getEvaluation_text() {
		return evaluation_text;
	}

	public void setEvaluation_text(String evaluation_text) {
		this.evaluation_text = evaluation_text;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}