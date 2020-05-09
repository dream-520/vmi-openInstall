package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  礼物消费记录[t_copy_user_chat_gift_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-12-17 19:03:27
 *
 */
@Table(name="t_copy_user_chat_gift_log")
public class CopyUserChatGiftLogEntity extends BaseEntity implements Serializable {

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
	 * 礼物ID
	 */
	@Column(name="gift_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="礼物ID")
	private Long gift_id;
	
	/**
	 * 发送类型 UserChatGiftLogTypeEnum类
	 */
	@Column(name="send_type",nullable=true,jdbcType=JdbcType.TINYINT,comment="发送类型 UserChatGiftLogTypeEnum类")
	private Integer send_type;
	
	/**
	 * 价格（钻）
	 */
	@Column(name="diamond",nullable=false,jdbcType=JdbcType.INTEGER,comment="价格（钻）")
	private Integer diamond;
	
	/**
	 * 发送方ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="发送方ID")
	private Long user_id;
	
	/**
	 * 接收方ID
	 */
	@Column(name="other_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="接收方ID")
	private Long other_id;
	
	/**
	 * 礼物盒标记 0 钻发送 1 礼物盒发送 
	 */
	@Column(name="box_flag",nullable=true,jdbcType=JdbcType.TINYINT,comment="礼物盒标记 0 钻发送 1 礼物盒发送 ")
	private Integer box_flag;
	
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
	
	public Long getGift_id() {
		return gift_id;
	}

	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}
	
	public Integer getSend_type() {
		return send_type;
	}

	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	
	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public Long getOther_id() {
		return other_id;
	}

	public void setOther_id(Long other_id) {
		this.other_id = other_id;
	}
	
	public Integer getBox_flag() {
		return box_flag;
	}

	public void setBox_flag(Integer box_flag) {
		this.box_flag = box_flag;
	}
	
}