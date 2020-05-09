package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  红包现金账户统计[t_static_user_remain_cash] 表对应的实体类
 * @author yangjunming
 * @Date 2017-05-22 15:35:37
 *
 */
@Table(name="t_static_user_remain_cash")
public class StaticUserRemainCashEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id(increment=false)
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 类型 1剩余现金 2累计现金
	 */
	@Id(increment=false)
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="类型 1剩余现金 2累计现金")
	private Integer type;
	
	/**
	 * 累计现金
	 */
	@Column(name="total_cash",nullable=true,jdbcType=JdbcType.DECIMAL,comment="累计现金")
	private Double total_cash;
	
	/**
	 * 平均现金
	 */
	@Column(name="avg_cash",nullable=true,jdbcType=JdbcType.DECIMAL,comment="平均现金")
	private Double avg_cash;
	
	/**
	 * 总人数
	 */
	@Column(name="people_num",nullable=true,jdbcType=JdbcType.BIGINT,comment="总人数")
	private Long people_num;
	
	/**
	 * 更新时间
	 */
	@Column(name="update_Time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="更新时间")
	private Date update_Time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Double getTotal_cash() {
		return total_cash;
	}

	public void setTotal_cash(Double total_cash) {
		this.total_cash = total_cash;
	}
	
	public Double getAvg_cash() {
		return avg_cash;
	}

	public void setAvg_cash(Double avg_cash) {
		this.avg_cash = avg_cash;
	}
	
	public Long getPeople_num() {
		return people_num;
	}

	public void setPeople_num(Long people_num) {
		this.people_num = people_num;
	}
	
	public Date getUpdate_Time() {
		return update_Time;
	}

	public void setUpdate_Time(Date update_Time) {
		this.update_Time = update_Time;
	}
	
}