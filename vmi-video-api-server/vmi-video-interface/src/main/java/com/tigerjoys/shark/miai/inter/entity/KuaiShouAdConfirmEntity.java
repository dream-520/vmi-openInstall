package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_kuai_shou_ad_confirm] 表对应的实体类
 * @author shiming
 * @Date 2019-10-07 16:51:03
 *
 */
@Table(name="t_kuai_shou_ad_confirm")
public class KuaiShouAdConfirmEntity extends BaseEntity implements Serializable {

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
	 * 设备标识
	 */
	@Column(name="clientId",nullable=true,jdbcType=JdbcType.VARCHAR,comment="设备标识")
	private String clientId;
	
	/**
	 * 广告类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.TINYINT,comment="广告类型")
	private Integer type;
	
	/**
	 * 确认状态信息 0 待确认 1 确认 2 重复注册类型
	 */
	@Column(name="state",nullable=true,jdbcType=JdbcType.TINYINT,comment="确认状态信息 0 待确认 1 确认 2 重复注册类型")
	private Integer state;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
	
}