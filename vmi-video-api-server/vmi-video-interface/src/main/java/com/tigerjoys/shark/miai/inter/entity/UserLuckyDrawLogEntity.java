package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户参与幸运抽奖日志表[t_user_lucky_draw_log] 表对应的实体类
 * @author lipeng
 * @Date 2018-01-29 16:31:32
 *
 */
@Table(name="t_user_lucky_draw_log")
public class UserLuckyDrawLogEntity extends BaseEntity implements Serializable {

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
	 * 用户ID
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 中奖结果
	 */
	@Column(name="reserved_int",nullable=true,jdbcType=JdbcType.INTEGER,comment="中奖结果")
	private Integer reserved_int;
	
	/**
	 * 奖品类型
	 */
	@Column(name="prize_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="奖品类型")
	private Integer prize_type;
	
	/**
	 * 描述信息
	 */
	@Column(name="remark",nullable=true,jdbcType=JdbcType.VARCHAR,comment="描述信息")
	private String remark;
	
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
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Integer getReserved_int() {
		return reserved_int;
	}

	public void setReserved_int(Integer reserved_int) {
		this.reserved_int = reserved_int;
	}
	
	public Integer getPrize_type() {
		return prize_type;
	}

	public void setPrize_type(Integer prize_type) {
		this.prize_type = prize_type;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}