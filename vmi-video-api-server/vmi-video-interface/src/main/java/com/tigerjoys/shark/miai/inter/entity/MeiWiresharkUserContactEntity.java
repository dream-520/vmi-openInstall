package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  抓取的用户联系方式存储表[t_mei_wireshark_user_contact] 表对应的实体类
 * @author shiming
 * @Date 2018-05-22 18:24:32
 *
 */
@Table(name="t_mei_wireshark_user_contact")
public class MeiWiresharkUserContactEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标示
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标示")
	private Long id;
	
	/**
	 * 抓取用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="抓取用户id")
	private Long userid;
	
	/**
	 * 被抓取用户id
	 */
	@Column(name="vuserid",nullable=true,jdbcType=JdbcType.BIGINT,comment="被抓取用户id")
	private Long vuserid;
	
	/**
	 * 抓取的总的联系方式数据
	 */
	@Column(name="data",nullable=true,jdbcType=JdbcType.LONGVARCHAR,comment="抓取的总的联系方式数据")
	private String data;
	
	/**
	 * 联系方式
	 */
	@Column(name="msg",nullable=true,jdbcType=JdbcType.VARCHAR,comment="联系方式")
	private String msg;
	
	/**
	 * 用于标示是否已经再次抓取了
	 */
	@Column(name="flag",nullable=true,jdbcType=JdbcType.INTEGER,comment="用于标示是否已经再次抓取了")
	private Integer flag;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getVuserid() {
		return vuserid;
	}

	public void setVuserid(Long vuserid) {
		this.vuserid = vuserid;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}