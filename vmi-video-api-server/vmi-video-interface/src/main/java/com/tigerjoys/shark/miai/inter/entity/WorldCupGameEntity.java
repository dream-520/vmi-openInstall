package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  世界杯比赛[t_world_cup_game] 表对应的实体类
 * @author mouzhanpeng
 * @Date 2018-06-15 13:44:51
 *
 */
@Table(name="t_world_cup_game")
public class WorldCupGameEntity extends BaseEntity implements Serializable {

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
	 * 创建人ID
	 */
	@Column(name="create_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="创建人ID")
	private Long create_adminid;
	
	/**
	 * 最后更新时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后更新时间")
	private Date update_time;
	
	/**
	 * 修改人ID
	 */
	@Column(name="update_adminid",nullable=false,jdbcType=JdbcType.BIGINT,comment="修改人ID")
	private Long update_adminid;
	
	/**
	 * 比赛开始时间时间
	 */
	@Column(name="game_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="比赛开始时间时间")
	private Date game_time;
	
	/**
	 * 主场国家名
	 */
	@Column(name="home",nullable=false,jdbcType=JdbcType.VARCHAR,comment="主场国家名")
	private String home;
	
	/**
	 * 主场国旗
	 */
	@Column(name="home_flag",nullable=false,jdbcType=JdbcType.VARCHAR,comment="主场国旗")
	private String home_flag;
	
	/**
	 * 客场国家名
	 */
	@Column(name="guest",nullable=false,jdbcType=JdbcType.VARCHAR,comment="客场国家名")
	private String guest;
	
	/**
	 * 客场国旗
	 */
	@Column(name="guest_flag",nullable=false,jdbcType=JdbcType.VARCHAR,comment="客场国旗")
	private String guest_flag;
	
	/**
	 * 主胜赔率
	 */
	@Column(name="home_win",nullable=false,jdbcType=JdbcType.INTEGER,comment="主胜赔率")
	private Integer home_win;
	
	/**
	 * 客胜赔率
	 */
	@Column(name="guest_win",nullable=false,jdbcType=JdbcType.INTEGER,comment="客胜赔率")
	private Integer guest_win;
	
	/**
	 * 平局赔率
	 */
	@Column(name="dogfall",nullable=false,jdbcType=JdbcType.INTEGER,comment="平局赔率")
	private Integer dogfall;
	
	/**
	 * 0未开奖,1-主胜，2-平，3-客胜
	 */
	@Column(name="result",nullable=false,jdbcType=JdbcType.TINYINT,comment="0未开奖,1-主胜，2-平，3-客胜")
	private Integer result;
	
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
	
	public Long getCreate_adminid() {
		return create_adminid;
	}

	public void setCreate_adminid(Long create_adminid) {
		this.create_adminid = create_adminid;
	}
	
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public Long getUpdate_adminid() {
		return update_adminid;
	}

	public void setUpdate_adminid(Long update_adminid) {
		this.update_adminid = update_adminid;
	}
	
	public Date getGame_time() {
		return game_time;
	}

	public void setGame_time(Date game_time) {
		this.game_time = game_time;
	}
	
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
	
	public String getHome_flag() {
		return home_flag;
	}

	public void setHome_flag(String home_flag) {
		this.home_flag = home_flag;
	}
	
	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}
	
	public String getGuest_flag() {
		return guest_flag;
	}

	public void setGuest_flag(String guest_flag) {
		this.guest_flag = guest_flag;
	}
	
	public Integer getHome_win() {
		return home_win;
	}

	public void setHome_win(Integer home_win) {
		this.home_win = home_win;
	}
	
	public Integer getGuest_win() {
		return guest_win;
	}

	public void setGuest_win(Integer guest_win) {
		this.guest_win = guest_win;
	}
	
	public Integer getDogfall() {
		return dogfall;
	}

	public void setDogfall(Integer dogfall) {
		this.dogfall = dogfall;
	}
	
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
}