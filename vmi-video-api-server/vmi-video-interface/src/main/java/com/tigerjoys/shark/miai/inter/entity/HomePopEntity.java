package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  首页弹窗功能表[t_home_pop] 表对应的实体类
 * @author yangjunming
 * @Date 2019-07-15 14:33:17
 *
 */
@Table(name="t_home_pop")
public class HomePopEntity extends BaseEntity implements Serializable {

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
	 * 弹窗标题
	 */
	@Column(name="title",nullable=false,jdbcType=JdbcType.VARCHAR,comment="弹窗标题")
	private String title;
	
	/**
	 * 弹窗链接
	 */
	@Column(name="url",nullable=false,jdbcType=JdbcType.VARCHAR,comment="弹窗链接")
	private String url;
	
	/**
	 * 文字说明
	 */
	@Column(name="intro",nullable=false,jdbcType=JdbcType.VARCHAR,comment="文字说明")
	private String intro;
	
	/**
	 * 1正常，-1下架
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="1正常，-1下架")
	private Integer status;
	
	/**
	 * 备注
	 */
	@Column(name="memo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="备注")
	private String memo;
	
	/**
	 * 0只弹一次，1每天一次
	 */
	@Column(name="showtype",nullable=false,jdbcType=JdbcType.TINYINT,comment="0只弹一次，1每天一次")
	private Integer showtype;
	
	/**
	 * 1 增钻 2 增钱  
	 */
	@Column(name="pop_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="1 增钻 2 增钱  ")
	private Integer pop_type;
	
	/**
	 * 用户类型
	 */
	@Column(name="usertype",nullable=false,jdbcType=JdbcType.TINYINT,comment="用户类型")
	private Integer usertype;
	
	/**
	 * 手机类型
	 */
	@Column(name="platform",nullable=false,jdbcType=JdbcType.TINYINT,comment="手机类型")
	private Integer platform;
	
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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
	
	public Integer getShowtype() {
		return showtype;
	}

	public void setShowtype(Integer showtype) {
		this.showtype = showtype;
	}
	
	public Integer getPop_type() {
		return pop_type;
	}

	public void setPop_type(Integer pop_type) {
		this.pop_type = pop_type;
	}
	
	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	
	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	
}