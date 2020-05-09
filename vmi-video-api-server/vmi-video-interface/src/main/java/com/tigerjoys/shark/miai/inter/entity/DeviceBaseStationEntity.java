package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  设备基站信息[t_device_base_station] 表对应的实体类
 * @author yangjunming
 * @Date 2019-08-03 10:08:31
 *
 */
@Table(name="t_device_base_station")
public class DeviceBaseStationEntity extends BaseEntity implements Serializable {

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
	 * 设备ID
	 */
	@Column(name="clientid",nullable=true,jdbcType=JdbcType.VARCHAR,comment="设备ID")
	private String clientid;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 基站MCC
	 */
	@Column(name="station_mcc",nullable=true,jdbcType=JdbcType.VARCHAR,comment="基站MCC")
	private String station_mcc;
	
	/**
	 * 基站MNC
	 */
	@Column(name="station_mnc",nullable=true,jdbcType=JdbcType.VARCHAR,comment="基站MNC")
	private String station_mnc;
	
	/**
	 * 基站LAC
	 */
	@Column(name="station_lac",nullable=true,jdbcType=JdbcType.VARCHAR,comment="基站LAC")
	private String station_lac;
	
	/**
	 * 基站CI
	 */
	@Column(name="station_ci",nullable=true,jdbcType=JdbcType.VARCHAR,comment="基站CI")
	private String station_ci;
	
	/**
	 * 基站rsrq
	 */
	@Column(name="station_rsrq",nullable=true,jdbcType=JdbcType.VARCHAR,comment="基站rsrq")
	private String station_rsrq;
	
	/**
	 * 状态 0 未查  1 查询过
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态 0 未查  1 查询过")
	private Integer status;
	
	/**
	 * 附近基站信息
	 */
	@Column(name="base_station_info",nullable=true,jdbcType=JdbcType.VARCHAR,comment="附近基站信息")
	private String base_station_info;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
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
	
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public String getStation_mcc() {
		return station_mcc;
	}

	public void setStation_mcc(String station_mcc) {
		this.station_mcc = station_mcc;
	}
	
	public String getStation_mnc() {
		return station_mnc;
	}

	public void setStation_mnc(String station_mnc) {
		this.station_mnc = station_mnc;
	}
	
	public String getStation_lac() {
		return station_lac;
	}

	public void setStation_lac(String station_lac) {
		this.station_lac = station_lac;
	}
	
	public String getStation_ci() {
		return station_ci;
	}

	public void setStation_ci(String station_ci) {
		this.station_ci = station_ci;
	}
	
	public String getStation_rsrq() {
		return station_rsrq;
	}

	public void setStation_rsrq(String station_rsrq) {
		this.station_rsrq = station_rsrq;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getBase_station_info() {
		return base_station_info;
	}

	public void setBase_station_info(String base_station_info) {
		this.base_station_info = base_station_info;
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
	
}