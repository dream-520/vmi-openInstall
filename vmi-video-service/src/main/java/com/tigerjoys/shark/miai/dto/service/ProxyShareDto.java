package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;

/**
 * 代金券dto
 * 
 * @author yangjunming
 *
 */
public class ProxyShareDto {

	/**
	 * id
	 */
	private long id;

	/**
	 * 名称
	 */
	private String title;

	/**
	 * 时间
	 */
	private String time;

	/**
	 * 达人累计人数
	 */
	private Integer talentvip;

	public static ProxyShareDto pareDto(UserInviteMappingEntity entity) {
		ProxyShareDto dto = new ProxyShareDto();
		if (Tools.isNull(entity)) {
			return dto;
		}
		dto.setId(entity.getId());
		dto.setTitle(entity.getName());
		dto.setTalentvip(entity.getTalentvip_num());
		dto.setTime(Tools.getFormatDate(entity.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
		return dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getTalentvip() {
		return talentvip;
	}

	public void setTalentvip(Integer talentvip) {
		this.talentvip = talentvip;
	}

}
