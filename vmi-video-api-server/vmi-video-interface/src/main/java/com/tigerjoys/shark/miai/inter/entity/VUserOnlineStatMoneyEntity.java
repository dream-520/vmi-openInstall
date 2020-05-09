package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_online_stat_money] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Table(name="t_v_user_online_stat_money")
public class VUserOnlineStatMoneyEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id标志
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id标志")
	private Long id;
	
	/**
	 * 用户id信息
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id信息")
	private Long userid;
	
	/**
	 * 昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="昵称")
	private String nickname;
	
	/**
	 * 用户等级
	 */
	@Column(name="level",nullable=false,jdbcType=JdbcType.TINYINT,comment="用户等级")
	private Integer level;
	
	/**
	 * 用户当前时段的收入值
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="用户当前时段的收入值")
	private Integer money;
	
	/**
	 * 记录总的分钟数
	 */
	@Column(name="minute",nullable=false,jdbcType=JdbcType.INTEGER,comment="记录总的分钟数")
	private Integer minute;
	
	/**
	 * 每分钟币值
	 */
	@Column(name="price",nullable=false,jdbcType=JdbcType.INTEGER,comment="每分钟币值")
	private Integer price;
	
	/**
	 * 礼物收入
	 */
	@Column(name="gift_money",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物收入")
	private Integer gift_money;
	
	/**
	 * 礼物数据
	 */
	@Column(name="gift_num",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物数据")
	private Integer gift_num;
	
	/**
	 * 礼物当日增加收入
	 */
	@Column(name="gift_imoney",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物当日增加收入")
	private Integer gift_imoney;
	
	/**
	 * 礼物当日增加数量
	 */
	@Column(name="gift_inum",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物当日增加数量")
	private Integer gift_inum;
	
	/**
	 * 收入对应的当天日期
	 */
	@Column(name="createTime",nullable=false,jdbcType=JdbcType.DATE,comment="收入对应的当天日期")
	private Date createTime;
	
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
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getGift_money() {
		return gift_money;
	}

	public void setGift_money(Integer gift_money) {
		this.gift_money = gift_money;
	}
	
	public Integer getGift_num() {
		return gift_num;
	}

	public void setGift_num(Integer gift_num) {
		this.gift_num = gift_num;
	}
	
	public Integer getGift_imoney() {
		return gift_imoney;
	}

	public void setGift_imoney(Integer gift_imoney) {
		this.gift_imoney = gift_imoney;
	}
	
	public Integer getGift_inum() {
		return gift_inum;
	}

	public void setGift_inum(Integer gift_inum) {
		this.gift_inum = gift_inum;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}