package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  美丽约自动抓包机器人账号[t_mei_wireshark_robot] 表对应的实体类
 * @author shiming
 * @Date 2018-05-23 16:48:10
 *
 */
@Table(name="t_mei_wireshark_robot")
public class MeiWiresharkRobotEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 账号id标示
	 */
	@Id(increment=false)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="账号id标示")
	private Long id;
	
	/**
	 * 登录手机号
	 */
	@Column(name="account",nullable=false,jdbcType=JdbcType.VARCHAR,comment="登录手机号")
	private String account;
	
	/**
	 * 登录密码 明文
	 */
	@Column(name="password",nullable=false,jdbcType=JdbcType.VARCHAR,comment="登录密码 明文")
	private String password;
	
	/**
	 * 标示是否是小米手机
	 */
	@Column(name="isMiui",nullable=false,jdbcType=JdbcType.SMALLINT,comment="标示是否是小米手机")
	private Integer isMiui;
	
	/**
	 * 手机相关配置参数
	 */
	@Column(name="ua",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机相关配置参数")
	private String ua;
	
	/**
	 * 请求头中的User-Agent参数
	 */
	@Column(name="user_agent",nullable=false,jdbcType=JdbcType.VARCHAR,comment="请求头中的User-Agent参数")
	private String user_agent;
	
	/**
	 * 创建时间
	 */
	@Column(name="creat_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date creat_time;
	
	/**
	 * 最后一次登录时间
	 */
	@Column(name="login_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后一次登录时间")
	private Date login_time;
	
	/**
	 * 标示用户当前状态 1正常 0可能出现了问题  -1被封杀了
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.SMALLINT,comment="标示用户当前状态 1正常 0可能出现了问题  -1被封杀了")
	private Integer status;
	
	/**
	 * 登录失败对应的描述信息
	 */
	@Column(name="msg",nullable=true,jdbcType=JdbcType.VARCHAR,comment="登录失败对应的描述信息")
	private String msg;
	
	/**
	 * 用于标示是否是vip账号
	 */
	@Column(name="vip",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用于标示是否是vip账号")
	private Integer vip;
	
	/**
	 * 手机mac值
	 */
	@Column(name="mac",nullable=false,jdbcType=JdbcType.VARCHAR,comment="手机mac值")
	private String mac;
	
	/**
	 * vip用户权限拥有的钥匙数量
	 */
	@Column(name="haveKey",nullable=false,jdbcType=JdbcType.INTEGER,comment="vip用户权限拥有的钥匙数量")
	private Integer haveKey;
	
	/**
	 * 当天消耗的钥匙数量
	 */
	@Column(name="consumeKey",nullable=false,jdbcType=JdbcType.INTEGER,comment="当天消耗的钥匙数量")
	private Integer consumeKey;
	
	/**
	 * 连续登录错误的次数
	 */
	@Column(name="errors",nullable=false,jdbcType=JdbcType.INTEGER,comment="连续登录错误的次数")
	private Integer errors;
	
	/**
	 * 第一次错误失败的时间
	 */
	@Column(name="errors_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="第一次错误失败的时间")
	private Date errors_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getIsMiui() {
		return isMiui;
	}

	public void setIsMiui(Integer isMiui) {
		this.isMiui = isMiui;
	}
	
	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}
	
	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	
	public Date getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}
	
	public Date getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public Integer getHaveKey() {
		return haveKey;
	}

	public void setHaveKey(Integer haveKey) {
		this.haveKey = haveKey;
	}
	
	public Integer getConsumeKey() {
		return consumeKey;
	}

	public void setConsumeKey(Integer consumeKey) {
		this.consumeKey = consumeKey;
	}
	
	public Integer getErrors() {
		return errors;
	}

	public void setErrors(Integer errors) {
		this.errors = errors;
	}
	
	public Date getErrors_time() {
		return errors_time;
	}

	public void setErrors_time(Date errors_time) {
		this.errors_time = errors_time;
	}
	
}