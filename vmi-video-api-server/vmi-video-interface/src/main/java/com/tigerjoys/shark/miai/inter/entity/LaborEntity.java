package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  工会列表[t_labor] 表对应的实体类
 * @author lipeng
 * @Date 2019-09-21 11:27:53
 *
 */
@Table(name="t_labor")
public class LaborEntity extends BaseEntity implements Serializable {

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
	 * 管理员ID
	 */
	@Column(name="adminId",nullable=false,jdbcType=JdbcType.BIGINT,comment="管理员ID")
	private Long adminId;
	
	/**
	 * 工会名称
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="工会名称")
	private String title;
	
	/**
	 * 主播数
	 */
	@Column(name="anchor_count",nullable=false,jdbcType=JdbcType.INTEGER,comment="主播数")
	private Integer anchor_count;
	
	/**
	 * 用户名
	 */
	@Column(name="username",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户名")
	private String username;
	
	/**
	 * 密码
	 */
	@Column(name="password",nullable=false,jdbcType=JdbcType.CHAR,comment="密码")
	private String password;
	
	/**
	 * 权限ID
	 */
	@Column(name="roleId",nullable=false,jdbcType=JdbcType.BIGINT,comment="权限ID")
	private Long roleId;
	
	/**
	 * 附件图片list
	 */
	@Column(name="accessory_list",nullable=true,jdbcType=JdbcType.VARCHAR,comment="附件图片list")
	private String accessory_list;
	
	/**
	 * 工会类型 1个人,2公司
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="工会类型 1个人,2公司")
	private Integer type;
	
	/**
	 * 1正常,0暂停,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常,0暂停,-9删除")
	private Integer status;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=true,jdbcType=JdbcType.VARCHAR,comment="备注")
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
	
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getAnchor_count() {
		return anchor_count;
	}

	public void setAnchor_count(Integer anchor_count) {
		this.anchor_count = anchor_count;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public String getAccessory_list() {
		return accessory_list;
	}

	public void setAccessory_list(String accessory_list) {
		this.accessory_list = accessory_list;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}