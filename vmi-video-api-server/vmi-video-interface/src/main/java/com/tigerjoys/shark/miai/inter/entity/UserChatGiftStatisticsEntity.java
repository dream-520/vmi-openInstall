package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户聊天送礼物数量统计表[t_user_chat_gift_statistics] 表对应的实体类
 * @author lipeng
 * @Date 2019-07-09 13:53:46
 *
 */
@Table(name="t_user_chat_gift_statistics")
public class UserChatGiftStatisticsEntity extends BaseEntity implements Serializable {

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
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 主播ID
	 */
	@Column(name="anchorid",nullable=false,jdbcType=JdbcType.BIGINT,comment="主播ID")
	private Long anchorid;
	
	/**
	 * 礼物数量
	 */
	@Column(name="gift_count",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物数量")
	private Integer gift_count;
	
	/**
	 * 礼物贡献
	 */
	@Column(name="gift_contribution",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物贡献")
	private Integer gift_contribution;
	
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
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getAnchorid() {
		return anchorid;
	}

	public void setAnchorid(Long anchorid) {
		this.anchorid = anchorid;
	}
	
	public Integer getGift_count() {
		return gift_count;
	}

	public void setGift_count(Integer gift_count) {
		this.gift_count = gift_count;
	}
	
	public Integer getGift_contribution() {
		return gift_contribution;
	}

	public void setGift_contribution(Integer gift_contribution) {
		this.gift_contribution = gift_contribution;
	}
	
}