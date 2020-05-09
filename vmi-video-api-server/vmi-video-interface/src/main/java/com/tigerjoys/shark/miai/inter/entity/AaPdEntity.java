package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_aa_pd] 表对应的实体类
 * @author shiming
 * @Date 2018-11-02 16:48:27
 *
 */
@Table(name="t_aa_pd")
public class AaPdEntity extends BaseEntity implements Serializable {

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
	 * code
	 */
	@Column(name="code",nullable=false,jdbcType=JdbcType.VARCHAR,comment="code")
	private String code;
	
	/**
	 * preview
	 */
	@Column(name="preview",nullable=true,jdbcType=JdbcType.VARCHAR,comment="preview")
	private String preview;
	
	/**
	 * rule
	 */
	@Column(name="rule",nullable=true,jdbcType=JdbcType.VARCHAR,comment="rule")
	private String rule;
	
	/**
	 * rank
	 */
	@Column(name="rank",nullable=true,jdbcType=JdbcType.INTEGER,comment="rank")
	private Integer rank;
	
	/**
	 * url
	 */
	@Column(name="url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="url")
	private String url;
	
	/**
	 * falg
	 */
	@Column(name="falg",nullable=true,jdbcType=JdbcType.INTEGER,comment="falg")
	private Integer falg;
	
	/**
	 * 类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.INTEGER,comment="类型")
	private Integer type;
	
	/**
	 * name
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="图片存储路径")
	private String name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}
	
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getFalg() {
		return falg;
	}

	public void setFalg(Integer falg) {
		this.falg = falg;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}