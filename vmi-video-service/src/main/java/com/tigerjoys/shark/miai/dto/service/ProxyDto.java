package com.tigerjoys.shark.miai.dto.service;

import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;

/**
 * 代金券dto
 * 
 * @author yangjunming
 *
 */
public class ProxyDto {

	/**
	 * id
	 */
	private long id;
	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 头像
	 */
	private String photo;

	/**
	 * 性别，1男，2女
	 */
	private int sex;

	/**
	 * 时长
	 */
	private Integer times;

	/**
	 * 分成金额
	 */
	private String amount;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 是否VIP
	 */
	private boolean vip;

	/**
	 * 结束时间
	 */
	private String createTime;

	public static ProxyDto pareDto(ProxyTransEntity entity, UserBO user) {
		if (user == null) {
			user = new UserBO();
			user.setVip(0);
			user.setVipExpire(new Date(Tools.getDayTime()));
			user.setDegreeid(1);
		}
		ProxyDto dto = new ProxyDto();
		dto.setId(entity.getId());
		dto.setNickName(user.getNickname());
		dto.setSex(user.getSex());
		dto.setPhoto(Const.getCdn(user.getPhoto()));
		dto.setTimes(entity.getTimes());
		dto.setAmount("" + entity.getDivided_amount() / 100.0);
		dto.setVip(user.vipValue() == 0 ? false : true);
		dto.setAge(Tools.getAge(user.getBirthday()));
		dto.setCreateTime(Tools.getFormatDate(entity.getCreate_time(), "yyyy-MM-dd HH:mm"));
		return dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

}
