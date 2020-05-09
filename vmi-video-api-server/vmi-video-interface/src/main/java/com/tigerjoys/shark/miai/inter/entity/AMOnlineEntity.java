package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_a_m_online] 表对应的实体类
 * @author shiming
 * @Date 2018-11-15 10:44:53
 *
 */
@Table(name="t_a_m_online")
public class AMOnlineEntity extends BaseEntity implements Serializable {

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
	 * useridx
	 */
	@Column(name="useridx",nullable=false,jdbcType=JdbcType.BIGINT,comment="useridx")
	private Long useridx;
	
	/**
	 * 昵称
	 */
	@Column(name="myname",nullable=true,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String myname;
	
	/**
	 * 1 在线 4 在聊 5 勿扰 2 活跃
	 */
	@Column(name="onlineState",nullable=false,jdbcType=JdbcType.SMALLINT,comment="1 在线 4 在聊 5 勿扰 2 活跃")
	private Integer onlineState;
	
	/**
	 * 星级
	 */
	@Column(name="starLevel",nullable=true,jdbcType=JdbcType.SMALLINT,comment="星级")
	private Integer starLevel;
	
	/**
	 * 每分钟价格
	 */
	@Column(name="prvideoCallPrice",nullable=true,jdbcType=JdbcType.INTEGER,comment="每分钟价格")
	private Integer prvideoCallPrice;
	
	/**
	 * videocallprice
	 */
	@Column(name="videocallprice",nullable=true,jdbcType=JdbcType.INTEGER,comment="videocallprice")
	private Integer videocallprice;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 类型
	 */
	@Column(name="type",nullable=true,jdbcType=JdbcType.SMALLINT,comment="类型")
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
	
	public Integer getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(Integer onlineState) {
		this.onlineState = onlineState;
	}
	
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	
	public Integer getPrvideoCallPrice() {
		return prvideoCallPrice;
	}

	public void setPrvideoCallPrice(Integer prvideoCallPrice) {
		this.prvideoCallPrice = prvideoCallPrice;
	}
	
	public Integer getVideocallprice() {
		return videocallprice;
	}

	public void setVideocallprice(Integer videocallprice) {
		this.videocallprice = videocallprice;
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