package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  系统配置表[t_sys_config] 表对应的实体类
 * @author yangjunming
 * @Date 2017-05-06 16:58:50
 *
 */
@Table(name="t_sys_config")
public class SysConfigEntity extends BaseEntity implements Serializable {

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
	 * 配置名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="配置名称")
	private String name;
	
	/**
	 * 配置的内容
	 */
	@Column(name="value",nullable=false,jdbcType=JdbcType.VARCHAR,comment="配置的内容")
	private String value;
	
	/**
	 * 开始时间
	 */
	@Column(name="begintime",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="开始时间")
	private Date begintime;
	
	/**
	 * 结束时间
	 */
	@Column(name="endtime",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="结束时间")
	private Date endtime;
	
	/**
	 * 状态，1正常，-9删除
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态，1正常，-9删除")
	private Integer state;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}