package com.tigerjoys.shark.miai.dto.service;

import java.sql.Date;
import java.util.List;

import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;

/**
 * 客户端需要的动态列表数据
 * @author shiming
 */
public class DynamicDto {
	
	public static DynamicDto parse(UserDynamicEntity entity) {
		DynamicDto dto = new DynamicDto();
		dto.setId(entity.getId());
		dto.setAudience_count(entity.getAudience_num());
		dto.setFavor_count(entity.getFavor_num());
		dto.setType(entity.getType());
		return dto;
	}

	/**
	 * ID
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private String userid;
	
	/**
	 * 用户昵称
	 */
	private String nickname;
	
	/**
	 * 用户头像
	 */
	private String photo;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 是否是vip
	 */
	private Integer vip;
	
	/**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 用户签名
	 */
	private String signature;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 图片列表
	 */
	private List<String> paths;
	
	/**
	 * 大图片列表
	 */
	private List<String> bigPaths;
	
	/**
	 * 视频路径
	 */
	private String video;
	
	/**
	 * 视频前景图
	 */
	private String foreground;
	
	/**
	 * 用户评论数
	 */
	private Long audience_count;
	
	/**
	 * 点赞数量
	 */
	private Long favor_count;
	
	/**
	 * 是否被当前设备点赞
	 */
	private Boolean favor_status;
	
	/**
	 * 动态类型  1图片 2 视频
	 */
	private Integer type;
	
	/**
	 * 动态时间
	 */
	private String date;
	
	/**
	 * 创建时间
	 */
	private String create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}
	
	public List<String> getBigPaths() {
		return bigPaths;
	}

	public void setBigPaths(List<String> bigPaths) {
		this.bigPaths = bigPaths;
	}

	public Long getAudience_count() {
		return audience_count;
	}

	public void setAudience_count(Long audience_count) {
		this.audience_count = audience_count;
	}

	public Long getFavor_count() {
		return favor_count;
	}

	public void setFavor_count(Long favor_count) {
		this.favor_count = favor_count;
	}

	public Boolean getFavor_status() {
		return favor_status;
	}

	public void setFavor_status(Boolean favor_status) {
		this.favor_status = favor_status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getForeground() {
		return foreground;
	}

	public void setForeground(String foreground) {
		this.foreground = foreground;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
