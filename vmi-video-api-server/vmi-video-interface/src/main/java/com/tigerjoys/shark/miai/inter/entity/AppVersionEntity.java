package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  版本信息表[t_app_version] 表对应的实体类
 * @author lipeng
 * @Date 2017-11-30 10:14:00
 *
 */
@Table(name="t_app_version")
public class AppVersionEntity extends BaseEntity implements Serializable {

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
	 * 创建人ID
	 */
	@Column(name="create_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="创建人ID")
	private Long create_adminid;
	
	/**
	 * 修改时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="修改时间")
	private Date update_time;
	
	/**
	 * 修改人ID
	 */
	@Column(name="update_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="修改人ID")
	private Long update_adminid;
	
	/**
	 * 渠道ID,0代表不限制
	 */
	@Column(name="channel_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="渠道ID,0代表不限制")
	private Long channel_id;
	
	/**
	 * 更新标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="更新标题")
	private String title;
	
	/**
	 * 更新内容
	 */
	@Column(name="content",nullable=false,jdbcType=JdbcType.VARCHAR,comment="更新内容")
	private String content;
	
	/**
	 * 0未知,1And,2IOS
	 */
	@Column(name="platform",nullable=false,jdbcType=JdbcType.TINYINT,comment="0未知,1And,2IOS")
	private Integer platform;
	
	/**
	 * 更新版本
	 */
	@Column(name="version",nullable=false,jdbcType=JdbcType.VARCHAR,comment="更新版本")
	private String version;
	
	/**
	 * 版本序列号
	 */
	@Column(name="code",nullable=false,jdbcType=JdbcType.INTEGER,comment="版本序列号")
	private Integer code;
	
	/**
	 * 0不提示升级,1提示升级,2强制升级
	 */
	@Column(name="flag",nullable=false,jdbcType=JdbcType.TINYINT,comment="0不提示升级,1提示升级,2强制升级")
	private Integer flag;
	
	/**
	 * 更新下载地址
	 */
	@Column(name="url",nullable=false,jdbcType=JdbcType.VARCHAR,comment="更新下载地址")
	private String url;
	
	/**
	 * 0停用,1正常
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="0停用,1正常")
	private Integer status;
	
	/**
	 * 用户数
	 */
	@Column(name="usercount",nullable=false,jdbcType=JdbcType.INTEGER,comment="用户数")
	private Integer usercount;
	
	/**
	 * IOS专属是否展示真实应用 0否 1是
	 */
	@Column(name="ios_status",nullable=false,jdbcType=JdbcType.TINYINT,comment="IOS专属是否展示真实应用 0否 1是")
	private Integer ios_status;
	
	/**
	 * IOS专属屏蔽苹果支付 0否 1是
	 */
	@Column(name="block_apple_pay",nullable=false,jdbcType=JdbcType.TINYINT,comment="IOS专属是否展示真实应用 0否 1是")
	private Integer block_apple_pay;
	
	/**
	 * 是否显示收益状态 0否 1是
	 */
	@Column(name="income_status",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否显示收益状态 0否 1是")
	private Integer income_status;
	
	/**
	 * 是否显示游客登录 0否 1是
	 */
	@Column(name="tourist_show",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否显示游客登录 0否 1是")
	private Integer tourist_show;
	
	/**
	 * 是否显示第三方登录 0否 1是
	 */
	@Column(name="third_show",nullable=false,jdbcType=JdbcType.TINYINT,comment="是否显示第三方登录 0否 1是")
	private Integer third_show;
	
	/**
	 * 渠道ID,0代表不限制
	 */
	@Column(name="package_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="包名ID,0代表不限制")
	private Long package_id;
	
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
	
	public Long getCreate_adminid() {
		return create_adminid;
	}

	public void setCreate_adminid(Long create_adminid) {
		this.create_adminid = create_adminid;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUpdate_adminid() {
		return update_adminid;
	}

	public void setUpdate_adminid(Long update_adminid) {
		this.update_adminid = update_adminid;
	}
	
	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getUsercount() {
		return usercount;
	}

	public void setUsercount(Integer usercount) {
		this.usercount = usercount;
	}
	
	public Integer getIos_status() {
		return ios_status;
	}

	public void setIos_status(Integer ios_status) {
		this.ios_status = ios_status;
	}

	public Integer getBlock_apple_pay() {
		return block_apple_pay;
	}

	public void setBlock_apple_pay(Integer block_apple_pay) {
		this.block_apple_pay = block_apple_pay;
	}

	public Integer getIncome_status() {
		return income_status;
	}

	public void setIncome_status(Integer income_status) {
		this.income_status = income_status;
	}

	public Integer getTourist_show() {
		return tourist_show;
	}

	public void setTourist_show(Integer tourist_show) {
		this.tourist_show = tourist_show;
	}

	public Integer getThird_show() {
		return third_show;
	}

	public void setThird_show(Integer third_show) {
		this.third_show = third_show;
	}

	public Long getPackage_id() {
		return package_id;
	}

	public void setPackage_id(Long package_id) {
		this.package_id = package_id;
	}
	
}