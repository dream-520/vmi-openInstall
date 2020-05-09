package com.tigerjoys.shark.miai.dto.service;

import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTalentVipStatisticsEntity;

/**
 * 代金券dto
 * 
 * @author yangjunming
 *
 */
public class ProxyPersonnelDto {

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
	 * 是否达人
	 */
	private boolean talent;

	/**
	 * 结束时间
	 */
	private String createTime;

	public static ProxyPersonnelDto pareDto(UserInviteEntity entity, UserBO user, UserTalentVipStatisticsEntity statistics) {
		if (user == null) {
			user = new UserBO();
			user.setVip(0);
			user.setVipExpire(new Date(Tools.getDayTime()));
			user.setDegreeid(1);
			user.setTalentVip(0);
			user.setTalentVipExpire(new Date(Tools.getDayTime()));
		}
		ProxyPersonnelDto dto = new ProxyPersonnelDto();
		dto.setId(entity.getId());
		dto.setNickName(user.getNickname());
		dto.setSex(user.getSex());
		dto.setPhoto(Const.getCdn(user.getPhoto()));
		if (Tools.isNotNull(statistics)) {
			dto.setTimes(statistics.getTrade_num());
			dto.setAmount("" + statistics.getTrade_amount() / 100.0);
		} else {
			dto.setTimes(0);
			dto.setAmount("" + 0);
		}
		dto.setVip(user.vipValue() == 0 ? false : true);
		dto.setTalent(user.isWaiter());
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

	public boolean isTalent() {
		return talent;
	}

	public void setTalent(boolean talent) {
		this.talent = talent;
	}

}
