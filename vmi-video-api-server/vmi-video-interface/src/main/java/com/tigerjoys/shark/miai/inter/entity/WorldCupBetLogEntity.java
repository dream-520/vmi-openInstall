package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  世界杯比赛竞猜记录[t_world_cup_bet_log] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-06-15 15:49:18
 *
 */
@Table(name="t_world_cup_bet_log")
public class WorldCupBetLogEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Id(increment=false)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="ID")
	private Long id;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long user_id;
	
	/**
	 * 比赛ID
	 */
	@Column(name="game_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="比赛ID")
	private Long game_id;
	
	/**
	 * 投注钻石数
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="投注钻石数")
	private Integer diamond;
	
	/**
	 * 赔率
	 */
	@Column(name="ratio",nullable=false,jdbcType=JdbcType.INTEGER,comment="赔率")
	private Integer ratio;
	
	/**
	 * 选择结果，1-主胜，2-平，3-客胜
	 */
	@Column(name="choice",nullable=false,jdbcType=JdbcType.TINYINT,comment="选择结果，1-主胜，2-平，3-客胜")
	private Integer choice;
	
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
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getGame_id() {
		return game_id;
	}

	public void setGame_id(Long game_id) {
		this.game_id = game_id;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	
	public Integer getChoice() {
		return choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}
	
}