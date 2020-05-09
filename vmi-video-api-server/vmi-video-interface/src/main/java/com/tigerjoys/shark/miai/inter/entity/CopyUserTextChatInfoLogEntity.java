package com.tigerjoys.shark.miai.inter.entity;

import java.io.Serializable;
import java.util.Date;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.annotations.Column;
import org.apache.ibatis.annotations.Id;
import org.apache.ibatis.annotations.Table;
import com.tigerjoys.nbs.mybatis.core.BaseEntity;

/**
 * 数据库中  用户文字聊天详细记录[t_copy_user_text_chat_info_log] 表对应的实体类
 * @author yangjunming
 * @Date 2019-12-17 17:04:13
 *
 */
@Table(name="t_copy_user_text_chat_info_log")
public class CopyUserTextChatInfoLogEntity extends BaseEntity implements Serializable {

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
	 * 主叫用户ID
	 */
	@Column(name="user_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="主叫用户ID")
	private Long user_id;
	
	/**
	 * 被叫用户ID
	 */
	@Column(name="other_id",nullable=false,jdbcType=JdbcType.BIGINT,comment="被叫用户ID")
	private Long other_id;
	
	/**
	 * 0 文本 1 语音
	 */
	@Column(name="audio_falg",nullable=true,jdbcType=JdbcType.TINYINT,comment="0 文本 1 语音")
	private Integer audio_falg;
	
	/**
	 * 检查ID
	 */
	@Column(name="check_taskid",nullable=true,jdbcType=JdbcType.VARCHAR,comment="检查ID")
	private String check_taskid;
	
	/**
	 * 检查状态
	 */
	@Column(name="check_status",nullable=true,jdbcType=JdbcType.INTEGER,comment="检查状态")
	private Integer check_status;
	
	/**
	 * 返回分类 code
	 */
	@Column(name="check_type_code",nullable=true,jdbcType=JdbcType.INTEGER,comment="返回分类 code")
	private Integer check_type_code;
	
	/**
	 * 检查分类 NeteaseTextCheckLabelEnum
	 */
	@Column(name="check_type",nullable=true,jdbcType=JdbcType.VARCHAR,comment="检查分类 NeteaseTextCheckLabelEnum")
	private String check_type;
	
	/**
	 * 消费小红花
	 */
	@Column(name="flower",nullable=false,jdbcType=JdbcType.BIGINT,comment="消费小红花")
	private Long flower;
	
	/**
	 * 文本消息
	 */
	@Column(name="chat_text",nullable=true,jdbcType=JdbcType.VARCHAR,comment="文本消息")
	private String chat_text;
	
	/**
	 * 音频地址
	 */
	@Column(name="audio_url",nullable=true,jdbcType=JdbcType.VARCHAR,comment="音频地址")
	private String audio_url;
	
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
	
	public Long getOther_id() {
		return other_id;
	}

	public void setOther_id(Long other_id) {
		this.other_id = other_id;
	}
	
	public Integer getAudio_falg() {
		return audio_falg;
	}

	public void setAudio_falg(Integer audio_falg) {
		this.audio_falg = audio_falg;
	}
	
	public String getCheck_taskid() {
		return check_taskid;
	}

	public void setCheck_taskid(String check_taskid) {
		this.check_taskid = check_taskid;
	}
	
	public Integer getCheck_status() {
		return check_status;
	}

	public void setCheck_status(Integer check_status) {
		this.check_status = check_status;
	}
	
	public Integer getCheck_type_code() {
		return check_type_code;
	}

	public void setCheck_type_code(Integer check_type_code) {
		this.check_type_code = check_type_code;
	}
	
	public String getCheck_type() {
		return check_type;
	}

	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	
	public Long getFlower() {
		return flower;
	}

	public void setFlower(Long flower) {
		this.flower = flower;
	}
	
	public String getChat_text() {
		return chat_text;
	}

	public void setChat_text(String chat_text) {
		this.chat_text = chat_text;
	}
	
	public String getAudio_url() {
		return audio_url;
	}

	public void setAudio_url(String audio_url) {
		this.audio_url = audio_url;
	}
	
}