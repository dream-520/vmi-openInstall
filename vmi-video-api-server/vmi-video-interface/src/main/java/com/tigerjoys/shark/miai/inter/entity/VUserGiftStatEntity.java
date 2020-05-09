package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_v_user_gift_stat] 表对应的实体类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Table(name="t_v_user_gift_stat")
public class VUserGiftStatEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 礼物总的数量
	 */
	@Column(name="total",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物总的数量")
	private Integer total;
	
	/**
	 * 礼物总的收入
	 */
	@Column(name="money",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物总的收入")
	private Integer money;
	
	/**
	 * 礼物新增数量
	 */
	@Column(name="incN",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物新增数量")
	private Integer incN;
	
	/**
	 * 礼物新增收入
	 */
	@Column(name="incM",nullable=false,jdbcType=JdbcType.INTEGER,comment="礼物新增收入")
	private Integer incM;
	
	/**
	 * 新增礼物的日期
	 */
	@Column(name="visit",nullable=false,jdbcType=JdbcType.DATE,comment="新增礼物的日期")
	private Date visit;
	
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
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
	
	public Integer getIncN() {
		return incN;
	}

	public void setIncN(Integer incN) {
		this.incN = incN;
	}
	
	public Integer getIncM() {
		return incM;
	}

	public void setIncM(Integer incM) {
		this.incM = incM;
	}
	
	public Date getVisit() {
		return visit;
	}

	public void setVisit(Date visit) {
		this.visit = visit;
	}
	
}