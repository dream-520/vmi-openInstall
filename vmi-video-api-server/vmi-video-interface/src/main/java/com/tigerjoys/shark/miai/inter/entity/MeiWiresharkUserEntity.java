package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  美丽约用户表[t_mei_wireshark_user] 表对应的实体类
 * @author shiming
 * @Date 2018-05-22 18:24:31
 *
 */
@Table(name="t_mei_wireshark_user")
public class MeiWiresharkUserEntity extends BaseEntity implements Serializable {

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
	 * 美丽约平台id值
	 */
	@Column(name="uid",nullable=false,jdbcType=JdbcType.BIGINT,comment="美丽约平台id值")
	private Long uid;
	
	/**
	 * 美丽约昵称
	 */
	@Column(name="nickname",nullable=false,jdbcType=JdbcType.VARCHAR,comment="美丽约昵称")
	private String nickname;
	
	/**
	 * 标示用户的性别
	 */
	@Column(name="sex",nullable=false,jdbcType=JdbcType.VARCHAR,comment="标示用户的性别")
	private String sex;
	
	/**
	 * 用户的年龄
	 */
	@Column(name="age",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用户的年龄")
	private Integer age;
	
	/**
	 * 用于表示本用户是否进行了联系方式认证
	 */
	@Column(name="contact",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用于表示本用户是否进行了联系方式认证")
	private Integer contact;
	
	/**
	 * 用于标示对应用户数据是否需要再次被抓取操作
	 */
	@Column(name="new_flag",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用于标示对应用户数据是否需要再次被抓取操作")
	private Integer new_flag;
	
	/**
	 * 用于标示本用户是否已经进行获取联系方式处理
	 */
	@Column(name="flag",nullable=false,jdbcType=JdbcType.SMALLINT,comment="用于标示本用户是否已经进行获取联系方式处理")
	private Integer flag;
	
	/**
	 * 首次查询到本用户的数据
	 */
	@Column(name="create_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="首次查询到本用户的数据")
	private Date create_time;
	
	/**
	 * 最后一次查询到本用户的时间
	 */
	@Column(name="update_time",nullable=false,jdbcType=JdbcType.TIMESTAMP,comment="最后一次查询到本用户的时间")
	private Date update_time;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getContact() {
		return contact;
	}

	public void setContact(Integer contact) {
		this.contact = contact;
	}
	
	public Integer getNew_flag() {
		return new_flag;
	}

	public void setNew_flag(Integer new_flag) {
		this.new_flag = new_flag;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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
	
}