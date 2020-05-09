package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户任务完成记录[t_task_user_complete] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-01-25 10:22:43
 *
 */
@Table(name="t_task_user_complete")
public class TaskUserCompleteEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 任务ID
	 */
	@Column(name="task_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="任务ID")
	private Long task_id;
	
	/**
	 * 任务体系编号，参考TaskCategoryEnum
	 */
	@Column(name="code",nullable=false,jdbcType=JdbcType.INTEGER,comment="任务体系编号，参考TaskCategoryEnum")
	private Integer code;
	
	/**
	 * 奖励（分）
	 */
	@Column(name="award",nullable=true,jdbcType=JdbcType.INTEGER,comment="奖励（分）")
	private Integer award;
	
	/**
	 * 订单额外分成（%）
	 */
	@Column(name="vacuate",nullable=true,jdbcType=JdbcType.INTEGER,comment="订单额外分成（%）")
	private Integer vacuate;
	
	/**
	 * scvc奖励
	 */
	@Column(name="scvc",nullable=false,jdbcType=JdbcType.INTEGER,comment="scvc奖励")
	private Integer scvc;
	
	/**
	 * 钱奖励流向，1-收益、2-单独提现、3-奖励金
	 */
	@Column(name="direction",nullable=false,jdbcType=JdbcType.TINYINT,comment="钱奖励流向，1-收益、2-单独提现、3-奖励金")
	private Integer direction;
	
	/**
	 * 是否完成任务，0-否，1-是
	 */
	@Column(name="completed",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否完成任务，0-否，1-是")
	private Integer completed;
	
	/**
	 * 扩展字段（达人约订单号）
	 */
	@Column(name="extension",nullable=true,jdbcType=JdbcType.VARCHAR,comment="扩展字段（达人约订单号）")
	private String extension;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getAward() {
		return award;
	}

	public void setAward(Integer award) {
		this.award = award;
	}
	
	public Integer getVacuate() {
		return vacuate;
	}

	public void setVacuate(Integer vacuate) {
		this.vacuate = vacuate;
	}
	
	public Integer getScvc() {
		return scvc;
	}

	public void setScvc(Integer scvc) {
		this.scvc = scvc;
	}
	
	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	
	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}
	
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
}