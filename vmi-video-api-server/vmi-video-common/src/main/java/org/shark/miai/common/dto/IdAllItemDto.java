package org.shark.miai.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 技能一级条目 DTO
 * @author shiming
 *
 */
public class IdAllItemDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2188852106020263707L;
	
	/**
	 * 一级分类信息
	 */
	private IdItemDto skill;
	
	/**
	 * 下级分类信息
	 */
	private List<IdItemDto> subSkills;

	public IdItemDto getSkill() {
		return skill;
	}

	public void setSkill(IdItemDto skill) {
		this.skill = skill;
	}

	public List<IdItemDto> getSubSkills() {
		return subSkills;
	}

	public void setSubSkills(List<IdItemDto> subSkills) {
		this.subSkills = subSkills;
	}
	
	
}
