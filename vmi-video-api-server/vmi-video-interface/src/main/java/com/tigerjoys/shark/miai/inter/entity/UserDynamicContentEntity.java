package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  动态内容表[t_user_dynamic_content] 表对应的实体类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
@Table(name="t_user_dynamic_content")
public class UserDynamicContentEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 动态id
	 */
	@Id(increment=false)
	@Column(name="dynamicid",nullable=false,jdbcType=JdbcType.BIGINT,comment="动态id")
	private Long dynamicid;
	
	/**
	 * 动态内容
	 */
	@Column(name="content",nullable=false,jdbcType=JdbcType.VARCHAR,comment="动态内容")
	private String content;
	
	/**
	 * 资源json
	 */
	@Column(name="path",nullable=false,jdbcType=JdbcType.VARCHAR,comment="资源json")
	private String path;
	
	public Long getDynamicid() {
		return dynamicid;
	}

	public void setDynamicid(Long dynamicid) {
		this.dynamicid = dynamicid;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public Long getId() {
		return dynamicid;
	}
}