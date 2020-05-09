package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  任务信息表[t_task] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-01-24 17:57:24
 *
 */
@Table(name="t_task")
public class TaskEntity extends BaseEntity implements Serializable {

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
	 * 修改时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
	private Date update_time;
	
	/**
	 * 任务体系编号，参考TaskCategoryEnum
	 */
	@Column(name="code",nullable=false,jdbcType=JdbcType.INTEGER,comment="任务体系编号，参考TaskCategoryEnum")
	private Integer code;
	
	/**
	 * 达人等级，0-普通用户;参考TalentLevelEnum
	 */
	@Column(name="level",nullable=false,jdbcType=JdbcType.INTEGER,comment="达人等级，0-普通用户;参考TalentLevelEnum")
	private Integer level;
	
	/**
	 * 任务标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="任务标题")
	private String title;
	
	/**
	 * 任务描述
	 */
	@Column(name="description",nullable=false,jdbcType=JdbcType.VARCHAR,comment="任务描述")
	private String description;
	
	/**
	 * 任务图标
	 */
	@Column(name="image_url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="任务图标")
	private String image_url;
	
	/**
	 * 开始时间
	 */
	@Column(name="start",nullable=false,jdbcType=JdbcType.TIME,comment="开始时间")
	private Time start;
	
	/**
	 * 结束时间
	 */
	@Column(name="end",nullable=false,jdbcType=JdbcType.TIME,comment="结束时间")
	private Time end;
	
	/**
	 * 每天任务领取次数1-10
	 */
	@Column(name="day_times",nullable=true,jdbcType=JdbcType.SMALLINT,comment="每天任务领取次数1-10")
	private Integer day_times;
	
	/**
	 * 奖励（分）或者订单额外分成（%）
	 */
	@Column(name="award",nullable=false,jdbcType=JdbcType.INTEGER,comment="奖励（分）或者订单额外分成（%）")
	private Integer award;
	
	/**
	 * scvc奖励
	 */
	@Column(name="scvc",nullable=false,jdbcType=JdbcType.INTEGER,comment="scvc奖励")
	private Integer scvc;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="优先级")
	private Integer priority;
	
	/**
	 * 钱奖励流向，1-收益、2-单独提现、3-奖励金
	 */
	@Column(name="direction",nullable=false,jdbcType=JdbcType.TINYINT,comment="钱奖励流向，1-收益、2-单独提现、3-奖励金")
	private Integer direction;
	
	/**
	 * 状态,0停用,1正常
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态,0停用,1正常")
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
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
	
	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}
	
	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}
	
	public Integer getDay_times() {
		return day_times;
	}

	public void setDay_times(Integer day_times) {
		this.day_times = day_times;
	}
	
	public Integer getAward() {
		return award;
	}

	public void setAward(Integer award) {
		this.award = award;
	}
	
	public Integer getScvc() {
		return scvc;
	}

	public void setScvc(Integer scvc) {
		this.scvc = scvc;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}