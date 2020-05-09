package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_a_m_day_income] 表对应的实体类
 * @author shiming
 * @Date 2018-11-15 17:19:33
 *
 */
@Table(name="t_a_m_day_income")
public class AMDayIncomeEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="useridx",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long useridx;
	
	/**
	 * 昵称
	 */
	@Column(name="myname",nullable=true,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String myname;
	
	/**
	 * 星级
	 */
	@Column(name="starLevel",nullable=true,jdbcType=JdbcType.SMALLINT,comment="星级")
	private Integer starLevel;
	
	/**
	 * 价格
	 */
	@Column(name="videocallprice",nullable=true,jdbcType=JdbcType.INTEGER,comment="价格")
	private Integer videocallprice;
	
	/**
	 * 在聊时长
	 */
	@Column(name="talking_minute",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊时长")
	private Integer talking_minute;
	
	/**
	 * 在聊收益
	 */
	@Column(name="talking_total",nullable=true,jdbcType=JdbcType.INTEGER,comment="在聊收益")
	private Integer talking_total;
	
	/**
	 * 收益日期
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.DATE,comment="收益日期")
	private Date create_time;
	
	/**
	 * 抓取的网页类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.SMALLINT,comment="抓取的网页类型")
	private Integer type;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUseridx() {
		return useridx;
	}

	public void setUseridx(Long useridx) {
		this.useridx = useridx;
	}
	
	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}
	
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	
	public Integer getVideocallprice() {
		return videocallprice;
	}

	public void setVideocallprice(Integer videocallprice) {
		this.videocallprice = videocallprice;
	}
	
	public Integer getTalking_minute() {
		return talking_minute;
	}

	public void setTalking_minute(Integer talking_minute) {
		this.talking_minute = talking_minute;
	}
	
	public Integer getTalking_total() {
		return talking_total;
	}

	public void setTalking_total(Integer talking_total) {
		this.talking_total = talking_total;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}