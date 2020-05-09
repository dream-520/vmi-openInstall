package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  V聊机器人用户信息表[t_v_robot_info] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Table(name="t_v_robot_info")
public class VRobotInfoEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 注册的V聊机器人用户
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="注册的V聊机器人用户")
	private Long id;
	
	/**
	 * V聊平台的用户id值
	 */
	@Column(name="userId",nullable=false,jdbcType=JdbcType.BIGINT,comment="V聊平台的用户id值")
	private Long userId;
	
	/**
	 * 注册的手机号码
	 */
	@Column(name="phoneNumber",nullable=false,jdbcType=JdbcType.VARCHAR,comment="注册的手机号码")
	private String phoneNumber;
	
	/**
	 * 机器人用户对应的密码
	 */
	@Column(name="password",nullable=false,jdbcType=JdbcType.VARCHAR,comment="机器人用户对应的密码")
	private String password;
	
	/**
	 * 手机类型
	 */
	@Column(name="appMarket",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机类型")
	private String appMarket;
	
	/**
	 * 手机操作系统
	 */
	@Column(name="apiVersion",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机操作系统")
	private String apiVersion;
	
	/**
	 * 手机型号
	 */
	@Column(name="phoneSystem",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机型号")
	private String phoneSystem;
	
	/**
	 * 下载来源
	 */
	@Column(name="phoneBrand",nullable=false,jdbcType=JdbcType.VARCHAR,comment="下载来源")
	private String phoneBrand;
	
	/**
	 * app版本
	 */
	@Column(name="packageName",nullable=false,jdbcType=JdbcType.VARCHAR,comment="app版本")
	private String packageName;
	
	/**
	 * 请求头中的系统版本
	 */
	@Column(name="appVersionName",nullable=true,jdbcType=JdbcType.VARCHAR,comment="请求头中的系统版本")
	private String appVersionName;
	
	/**
	 * 请求头中的系统类型
	 */
	@Column(name="appVersionCode",nullable=true,jdbcType=JdbcType.VARCHAR,comment="请求头中的系统类型")
	private String appVersionCode;
	
	/**
	 * 请求头中的app版本
	 */
	@Column(name="phoneModels",nullable=true,jdbcType=JdbcType.VARCHAR,comment="请求头中的app版本")
	private String phoneModels;
	
	/**
	 * 服务器下发的秘钥
	 */
	@Column(name="userKey",nullable=true,jdbcType=JdbcType.VARCHAR,comment="服务器下发的秘钥")
	private String userKey;
	
	/**
	 * 当前机器人的状态信息 1在线 0状态异常
	 */
	@Column(name="state",nullable=false,jdbcType=JdbcType.TINYINT,comment="当前机器人的状态信息 1在线 0状态异常")
	private Integer state;
	
	/**
	 * 登录时间
	 */
	@Column(name="login",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="登录时间")
	private Date login;
	
	/**
	 * 机器人是否处于使用中 0 不在线 1 在线
	 */
	@Column(name="online",nullable=true,jdbcType=JdbcType.TINYINT,comment="机器人是否处于使用中 0 不在线 1 在线")
	private Integer online;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAppMarket() {
		return appMarket;
	}

	public void setAppMarket(String appMarket) {
		this.appMarket = appMarket;
	}
	
	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	public String getPhoneSystem() {
		return phoneSystem;
	}

	public void setPhoneSystem(String phoneSystem) {
		this.phoneSystem = phoneSystem;
	}
	
	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}
	
	public String getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}
	
	public String getPhoneModels() {
		return phoneModels;
	}

	public void setPhoneModels(String phoneModels) {
		this.phoneModels = phoneModels;
	}
	
	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Date getLogin() {
		return login;
	}

	public void setLogin(Date login) {
		this.login = login;
	}
	
	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}
	
}