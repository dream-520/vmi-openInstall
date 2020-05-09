package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  主播接听用户[t_anchor_recv_user] 表对应的实体类
 * @author shiming
 * @Date 2019-07-05 16:00:33
 *
 */
@Table(name="t_anchor_recv_user")
public class AnchorRecvUserEntity extends BaseEntity implements Serializable {

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
	 * 主播id
	 */
	@Column(name="anchorId",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播id")
	private Long anchorId;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 是否视频过
	 */
	@Column(name="video",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否视频过")
	private Integer video;
	
	/**
	 * 是否发送过消息
	 */
	@Column(name="msg",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否发送过消息")
	private Integer msg;
	
	/**
	 * 是否发送过礼物
	 */
	@Column(name="gift",nullable=true,jdbcType=JdbcType.TINYINT,comment="是否发送过礼物")
	private Integer gift;
	
	/**
	 * 用户对主播的勿扰状态 0 未启动勿扰 1 启动勿扰
	 */
	@Column(name="disturb",nullable=true,jdbcType=JdbcType.TINYINT,comment="用户对主播的勿扰状态 0 未启动勿扰 1 启动勿扰")
	private Integer disturb;
	
	/**
	 * 用户对主播的隐身状态 0 未启动隐身1 启动隐身
	 */
	@Column(name="invisibility",nullable=true,jdbcType=JdbcType.TINYINT,comment="用户对主播的隐身状态 0 未启动隐身1 启动隐身")
	private Integer invisibility;
	
	/**
	 * create_time
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="create_time")
	private Date create_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getAnchorId() {
		return anchorId;
	}

	public void setAnchorId(Long anchorId) {
		this.anchorId = anchorId;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Integer getVideo() {
		return video;
	}

	public void setVideo(Integer video) {
		this.video = video;
	}
	
	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}
	
	public Integer getGift() {
		return gift;
	}

	public void setGift(Integer gift) {
		this.gift = gift;
	}
	
	public Integer getDisturb() {
		return disturb;
	}

	public void setDisturb(Integer disturb) {
		this.disturb = disturb;
	}
	
	public Integer getInvisibility() {
		return invisibility;
	}

	public void setInvisibility(Integer invisibility) {
		this.invisibility = invisibility;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}