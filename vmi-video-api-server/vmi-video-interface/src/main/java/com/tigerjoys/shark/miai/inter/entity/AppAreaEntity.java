package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  城市字典表[t_app_area] 表对应的实体类
 * @author chengang
 * @Date 2017-05-08 16:12:55
 *
 */
@Table(name="t_app_area")
public class AppAreaEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id(increment=false)
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
	 * 名称
	 */
	@Column(name="name",nullable=false,jdbcType=JdbcType.VARCHAR,comment="名称")
	private String name;
	
	/**
	 * 城市简称
	 */
	@Column(name="ename",nullable=false,jdbcType=JdbcType.VARCHAR,comment="城市简称")
	private String ename;
	
	/**
	 * 拼音
	 */
	@Column(name="spell",nullable=false,jdbcType=JdbcType.VARCHAR,comment="拼音")
	private String spell;
	
	/**
	 * 首字母
	 */
	@Column(name="initial",nullable=false,jdbcType=JdbcType.CHAR,comment="首字母")
	private String initial;
	
	/**
	 * 是否热门城市，0否，1是
	 */
	@Column(name="hot",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否热门城市")
	private Integer hot;
	
	/**
	 * 分级
	 */
	@Column(name="depth",nullable=false,jdbcType=JdbcType.TINYINT,comment="分级")
	private Integer depth;
	
	/**
	 * 父级ID
	 */
	@Column(name="pid",nullable=false,jdbcType=JdbcType.BIGINT,comment="父级ID")
	private Long pid;
	
	/**
	 * 是否有子级,0否,1是
	 */
	@Column(name="has_children",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否有子级")
	private Integer has_children;
	
	/**
	 * 优先级
	 */
	@Column(name="priority",nullable=false,jdbcType=JdbcType.INTEGER,comment="优先级")
	private Integer priority;
	
	/**
	 * 状态，1正常，0隐藏
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态")
	private Integer status;

	/**
	 * 电话区号
	 */
	@Column(name="phone_code",nullable=true,jdbcType=JdbcType.VARCHAR,comment="电话区号")
	private String phone_code;

	/**
	 * 默认经度
	 */
	@Column(name="lng",nullable=false,jdbcType=JdbcType.DOUBLE,comment="默认经度")
	private Double lng;

	/**
	 * 默认纬度
	 */
	@Column(name="lat",nullable=false,jdbcType=JdbcType.DOUBLE,comment="默认纬度")
	private Double lat;
	
	/**
	 * 百度城市编号
	 */
	@Column(name="baidu_code",nullable=false,jdbcType=JdbcType.INTEGER,comment="百度城市编号")
	private Integer baidu_code;

	/**
	 * 是否直辖市
	 */
	@Column(name="iszhi",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否直辖市")
	private Integer iszhi;
	
	/**
	 * 城市等级,1级,2级，0级(无等级，默认)
	 */
	@Column(name="level",nullable=false,jdbcType=JdbcType.TINYINT,comment="城市等级")
	private Integer level;
	
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
	
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}
	
	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	public Integer getHas_children() {
		return has_children;
	}

	public void setHas_children(Integer has_children) {
		this.has_children = has_children;
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

	public String getPhone_code() {
		return phone_code;
	}

	public void setPhone_code(String phone_code) {
		this.phone_code = phone_code;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getBaidu_code() {
		return baidu_code;
	}

	public void setBaidu_code(Integer baidu_code) {
		this.baidu_code = baidu_code;
	}

	public Integer getIszhi() {
		return iszhi;
	}

	public void setIszhi(Integer iszhi) {
		this.iszhi = iszhi;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}