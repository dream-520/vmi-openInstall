package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_page] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 17:19:42
 *
 */
@Table(name="t_v_user_page")
public class VUserPageEntity extends BaseEntity implements Serializable {

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
	 * 抓取本页数据的机器人
	 */
	@Column(name="visitor",nullable=false,jdbcType=JdbcType.BIGINT,comment="抓取本页数据的机器人")
	private Long visitor;
	
	/**
	 * 抓取的一页数据
	 */
	@Column(name="page",nullable=false,jdbcType=JdbcType.VARCHAR,comment="抓取的一页数据")
	private String page;
	
	/**
	 * 抓取时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="抓取时间")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getVisitor() {
		return visitor;
	}

	public void setVisitor(Long visitor) {
		this.visitor = visitor;
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}