package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  活动任务列表[t_user_task] 表对应的实体类
 * @author lipeng
 * @Date 2019-09-07 11:20:28
 *
 */
@Table(name="t_user_task")
public class UserTaskEntity extends BaseEntity implements Serializable {

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
	 * 标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标题")
	private String title;
	
	/**
	 * 唯一字段
	 */
	@Column(name="uni_code",nullable=false,jdbcType=JdbcType.INTEGER,comment="唯一字段")
	private Integer uni_code;
	
	/**
	 * 积分
	 */
	@Column(name="point",nullable=false,jdbcType=JdbcType.INTEGER,comment="积分")
	private Integer point;
	
	/**
	 * 次数
	 */
	@Column(name="count",nullable=false,jdbcType=JdbcType.INTEGER,comment="次数")
	private Integer count;
	
	/**
	 * 参数
	 */
	@Column(name="parameters",nullable=true,jdbcType=JdbcType.VARCHAR,comment="参数")
	private String parameters;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="优先级")
	private Integer priority;
	
	/**
	 * 1正常,0暂停,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常,0暂停,-9删除")
	private Integer status;
	
	/**
	 * app内页的参数,json
	 */
	@Column(name="parame",nullable=true,jdbcType=JdbcType.VARCHAR,comment="app内页的参数,json")
	private String parame;
	
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getUni_code() {
		return uni_code;
	}

	public void setUni_code(Integer uni_code) {
		this.uni_code = uni_code;
	}
	
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getParame() {
		return parame;
	}

	public void setParame(String parame) {
		this.parame = parame;
	}
	
}