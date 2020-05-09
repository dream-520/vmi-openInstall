package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  [t_user_invite_mapping] 表对应的实体类
 * @author yangjunming
 * @Date 2019-10-16 17:47:10
 *
 */
@Table(name="t_user_invite_mapping")
public class UserInviteMappingEntity extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@Id
	@Column(name="id",nullable=false,jdbcType=JdbcType.BIGINT,comment="id")
	private Long id;
	
	/**
	 * 用户ID
	 */
	@Column(name="userid",nullable=true,jdbcType=JdbcType.BIGINT,comment="用户ID")
	private Long userid;
	
	/**
	 * 名称
	 */
	@Column(name="name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="名称")
	private String name;
	
	/**
	 * 邀请码
	 */
	@Column(name="invitecode",nullable=true,jdbcType=JdbcType.VARCHAR,comment="邀请码")
	private String invitecode;
	
	/**
	 * 邀请人数
	 */
	@Column(name="invite_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="邀请人数")
	private Integer invite_num;
	
	/**
	 * 包名
	 */
	@Column(name="package_name",nullable=true,jdbcType=JdbcType.VARCHAR,comment="包名")
	private String package_name;
	
	/**
	 * 累计达人数
	 */
	@Column(name="talentvip_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="累计达人数")
	private Integer talentvip_num;
	
	/**
	 * 交易笔数
	 */
	@Column(name="trade_num",nullable=true,jdbcType=JdbcType.INTEGER,comment="交易笔数")
	private Integer trade_num;
	
	/**
	 * 交易总金额
	 */
	@Column(name="trade_amount",nullable=true,jdbcType=JdbcType.INTEGER,comment="交易总金额")
	private Integer trade_amount;
	
	/**
	 * 生成时间
	 */
	@Column(name="create_time",nullable=true,jdbcType=JdbcType.TIMESTAMP,comment="生成时间")
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	
	public Integer getInvite_num() {
		return invite_num;
	}

	public void setInvite_num(Integer invite_num) {
		this.invite_num = invite_num;
	}
	
	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	
	public Integer getTalentvip_num() {
		return talentvip_num;
	}

	public void setTalentvip_num(Integer talentvip_num) {
		this.talentvip_num = talentvip_num;
	}
	
	public Integer getTrade_num() {
		return trade_num;
	}

	public void setTrade_num(Integer trade_num) {
		this.trade_num = trade_num;
	}
	
	public Integer getTrade_amount() {
		return trade_amount;
	}

	public void setTrade_amount(Integer trade_amount) {
		this.trade_amount = trade_amount;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}