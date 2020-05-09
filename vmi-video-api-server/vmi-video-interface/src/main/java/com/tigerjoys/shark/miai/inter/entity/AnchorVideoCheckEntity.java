package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  通话监黄业务处理表[t_anchor_video_check] 表对应的实体类
 * @author shiming
 * @Date 2019-08-23 15:12:48
 *
 */
@Table(name="t_anchor_video_check")
public class AnchorVideoCheckEntity extends BaseEntity implements Serializable {

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
	 * 截图的用户id
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="截图的用户id")
	private Long userid;
	
	/**
	 * 通话对方
	 */
	@Column(name="otherid",nullable=true,jdbcType=JdbcType.BIGINT,comment="通话对方")
	private Long otherid;
	
	/**
	 * 房间序列号
	 */
	@Column(name="serialNum",nullable=true,jdbcType=JdbcType.BIGINT,comment="房间序列号")
	private Long serialNum;
	
	/**
	 * 检测图片路径
	 */
	@Column(name="url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="检测图片路径")
	private String url;
	
	/**
	 * 检测类型  0 不进行检测 1 视频检测 2 音频检测 3 全检测
	 */
	@Column(name="checkType",nullable=true,jdbcType=JdbcType.TINYINT,comment="检测类型  0 不进行检测 1 视频检测 2 音频检测 3 全检测")
	private Integer checkType;
	
	/**
	 * 色情等级
	 */
	@Column(name="pornographicLevel",nullable=true,jdbcType=JdbcType.TINYINT,comment="色情等级")
	private Integer pornographicLevel;
	
	/**
	 * 低俗等级
	 */
	@Column(name="lowLevel",nullable=true,jdbcType=JdbcType.TINYINT,comment="低俗等级")
	private Integer lowLevel;
	
	/**
	 * 色情检测比例
	 */
	@Column(name="pornographicRate",nullable=true,jdbcType=JdbcType.DOUBLE,comment="色情检测比例")
	private Double pornographicRate;
	
	/**
	 * 低俗检测比例
	 */
	@Column(name="lowRate",nullable=true,jdbcType=JdbcType.DOUBLE,comment="低俗检测比例")
	private Double lowRate;
	
	/**
	 * 最终确认的检测类型 0 不是色情 1 色情 2 低俗
	 */
	@Column(name="defineType",nullable=true,jdbcType=JdbcType.TINYINT,comment="最终确认的检测类型 0 不是色情 1 色情 2 低俗")
	private Integer defineType;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
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
	
	public Long getOtherid() {
		return otherid;
	}

	public void setOtherid(Long otherid) {
		this.otherid = otherid;
	}
	
	public Long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}
	
	public Integer getPornographicLevel() {
		return pornographicLevel;
	}

	public void setPornographicLevel(Integer pornographicLevel) {
		this.pornographicLevel = pornographicLevel;
	}
	
	public Integer getLowLevel() {
		return lowLevel;
	}

	public void setLowLevel(Integer lowLevel) {
		this.lowLevel = lowLevel;
	}
	
	public Double getPornographicRate() {
		return pornographicRate;
	}

	public void setPornographicRate(Double pornographicRate) {
		this.pornographicRate = pornographicRate;
	}
	
	public Double getLowRate() {
		return lowRate;
	}

	public void setLowRate(Double lowRate) {
		this.lowRate = lowRate;
	}
	
	public Integer getDefineType() {
		return defineType;
	}

	public void setDefineType(Integer defineType) {
		this.defineType = defineType;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}