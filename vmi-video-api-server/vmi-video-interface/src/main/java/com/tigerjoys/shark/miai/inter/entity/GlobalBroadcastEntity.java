package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  全局广播[t_global_broadcast] 表对应的实体类
 * @author lipeng
 * @Date 2019-07-06 17:55:36
 *
 */
@Table(name="t_global_broadcast")
public class GlobalBroadcastEntity extends BaseEntity implements Serializable {

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
	 * 用户id
	 */
	@Column(name="userid",nullable=false,jdbcType=JdbcType.BIGINT,comment="用户id")
	private Long userid;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="创建时间")
	private Date create_time;
	
	/**
	 * 头像图片
	 */
	@Column(name="photo",nullable=false,jdbcType=JdbcType.VARCHAR,comment="头像图片")
	private String photo;
	
	/**
	 * 用户昵称
	 */
	@Column(name="nickName",nullable=false,jdbcType=JdbcType.VARCHAR,comment="用户昵称")
	private String nickName;
	
	/**
	 * 广播内容
	 */
	@Column(name="content",nullable=false,jdbcType=JdbcType.VARCHAR,comment="广播内容")
	private String content;
	
	/**
	 * 礼物图片
	 */
	@Column(name="img",nullable=true,jdbcType=JdbcType.VARCHAR,comment="礼物图片")
	private String img;
	
	/**
	 * 播报类型 1.礼物 2.充值 3.上线
	 */
	@Column(name="type",nullable=false,jdbcType=JdbcType.TINYINT,comment="播报类型 1.礼物 2.充值 3.上线")
	private Integer type;
	
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}