package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  信用分购买配置信息[t_credit_score_configure] 表对应的实体类
 * @author liuman
 * @Date 2017-08-16 16:20:39
 *
 */
@Table(name="t_credit_score_configure")
public class CreditScoreConfigureEntity extends BaseEntity implements Serializable {

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
	 * 主题
	 */
	@Column(name="theme",nullable=false,jdbcType=JdbcType.VARCHAR,comment="主题")
	private String theme;
	
	/**
	 * 副主题
	 */
	@Column(name="sub_theme",nullable=false,jdbcType=JdbcType.VARCHAR,comment="副主题")
	private String sub_theme;
	
	/**
	 * 信用账户类型:1-普通用户,2-服务者用户
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.SMALLINT,comment="信用账户类型:1-普通用户,2-服务者用户")
	private Integer type;
	
	/**
	 * 信用分
	 */
	@Column(name="score",nullable=false,jdbcType=JdbcType.INTEGER,comment="信用分")
	private Integer score;
	
	/**
	 * 购买信用分需要的钻石数
	 */
	@Column(name="diamonds",nullable=false,jdbcType=JdbcType.INTEGER,comment="购买信用分需要的钻石数")
	private Integer diamonds;
	
	/**
	 * 状态,0暂停,1正常,-9删除
	 */
	@Column(name="status",nullable=false,jdbcType=JdbcType.TINYINT,comment="状态,0暂停,1正常,-9删除")
	private Integer status;
	
	/**
	 * 状态变更日期
	 */
	@Column(name="status_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="状态变更日期")
	private Date status_time;
	
	/**
	 * 状态变更的原因
	 */
	@Column(name="status_reason",nullable=true,jdbcType=JdbcType.VARCHAR,comment="状态变更的原因")
	private String status_reason;
	
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
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getSub_theme() {
		return sub_theme;
	}

	public void setSub_theme(String sub_theme) {
		this.sub_theme = sub_theme;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Integer getDiamonds() {
		return diamonds;
	}

	public void setDiamonds(Integer diamonds) {
		this.diamonds = diamonds;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getStatus_time() {
		return status_time;
	}

	public void setStatus_time(Date status_time) {
		this.status_time = status_time;
	}
	
	public String getStatus_reason() {
		return status_reason;
	}

	public void setStatus_reason(String status_reason) {
		this.status_reason = status_reason;
	}
	
}