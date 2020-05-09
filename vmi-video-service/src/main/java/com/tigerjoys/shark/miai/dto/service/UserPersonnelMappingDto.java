package com.tigerjoys.shark.miai.dto.service;

import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;

/**
 * 代理分类人员明细统计dto
 * 
 * @author yangjunming
 *
 */
public class UserPersonnelMappingDto {

	/**
	 * id
	 */
	private long id;
	/**
	 * 分类名称
	 */
	private String mappingName;

	/**
	 * 邀请人数
	 */
	private Integer inviteNum;

	/**
	 * 达人数
	 */
	private Integer talentNum;
	


	public static UserPersonnelMappingDto pareDto(UserInviteMappingEntity entity) {
		UserPersonnelMappingDto dto = new UserPersonnelMappingDto();
		dto.setId(entity.getId());
		dto.setMappingName(entity.getName());
		dto.setInviteNum(entity.getInvite_num());
		dto.setTalentNum(entity.getTalentvip_num());
		return dto;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getMappingName() {
		return mappingName;
	}



	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}



	public Integer getInviteNum() {
		return inviteNum;
	}



	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}



	public Integer getTalentNum() {
		return talentNum;
	}



	public void setTalentNum(Integer talentNum) {
		this.talentNum = talentNum;
	}



}
