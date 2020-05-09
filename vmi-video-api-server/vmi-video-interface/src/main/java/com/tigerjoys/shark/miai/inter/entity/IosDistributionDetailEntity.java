package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  IOS分发表明细[t_ios_distribution_detail] 表对应的实体类
 * @author yangjunming
 * @Date 2019-04-23 14:27:59
 *
 */
@Table(name="t_ios_distribution_detail")
public class IosDistributionDetailEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 平台ID
	 */
	@Column(name="platform_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="平台ID")
	private Long platform_id;
	
	/**
	 * 渠道
	 */
	@Column(name="channel_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="渠道")
	private Long channel_id;
	
	/**
	 * 操作平台(1 android 2 IOS)
	 */
	@Column(name="os_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="操作平台(1 android 2 IOS)")
	private Integer os_type;
	
	/**
	 * 版本
	 */
	@Column(name="version_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="版本")
	private String version_text;
	
	/**
	 * IOS包地址
	 */
	@Column(name="ios_app_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="IOS包地址")
	private String ios_app_path;
	
	/**
	 * IOS的plist地址
	 */
	@Column(name="plist_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="IOS的plist地址")
	private String plist_path;
	
	/**
	 * 分发链接
	 */
	@Column(name="distribution_path",nullable=true,jdbcType=JdbcType.VARCHAR,comment="分发链接")
	private String distribution_path;
	
	/**
	 * 备注
	 */
	@Column(name="comment_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String comment_text;
	
	/**
	 * 状态
	 */
	@Column(name="status",nullable=true,jdbcType=JdbcType.TINYINT,comment="状态")
	private Integer status;
	
	/**
	 * 关联ID
	 */
	@Column(name="union_id",nullable=true,jdbcType=JdbcType.BIGINT,comment="关联ID")
	private Long union_id;
	
	/**
	 * 文件前缀
	 */
	@Column(name="file_prefix",nullable=true,jdbcType=JdbcType.VARCHAR,comment="文件前缀")
	private String file_prefix;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(Long platform_id) {
		this.platform_id = platform_id;
	}
	
	public Long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Long channel_id) {
		this.channel_id = channel_id;
	}
	
	public Integer getOs_type() {
		return os_type;
	}

	public void setOs_type(Integer os_type) {
		this.os_type = os_type;
	}
	
	public String getVersion_text() {
		return version_text;
	}

	public void setVersion_text(String version_text) {
		this.version_text = version_text;
	}
	
	public String getIos_app_path() {
		return ios_app_path;
	}

	public void setIos_app_path(String ios_app_path) {
		this.ios_app_path = ios_app_path;
	}
	
	public String getPlist_path() {
		return plist_path;
	}

	public void setPlist_path(String plist_path) {
		this.plist_path = plist_path;
	}
	
	public String getDistribution_path() {
		return distribution_path;
	}

	public void setDistribution_path(String distribution_path) {
		this.distribution_path = distribution_path;
	}
	
	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getUnion_id() {
		return union_id;
	}

	public void setUnion_id(Long union_id) {
		this.union_id = union_id;
	}
	
	public String getFile_prefix() {
		return file_prefix;
	}

	public void setFile_prefix(String file_prefix) {
		this.file_prefix = file_prefix;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}