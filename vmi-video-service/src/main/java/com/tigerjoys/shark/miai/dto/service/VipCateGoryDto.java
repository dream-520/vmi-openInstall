package com.tigerjoys.shark.miai.dto.service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.inter.entity.VipCategoryEntity;

/**
 * 代金券dto
 * @author yangjunming
 *
 */
public class VipCateGoryDto  {
	
	/**
	 * id
	 */
	private long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 图片
	 */
	private String photo;

	/**
	 * 金额
	 */
	private Integer diamond;
	
	/**
	 * 能量
	 */
	private Integer energy;

	/**
	 * 有效月数
	 */
	private Integer months;


	
	public  static VipCateGoryDto pareDto(VipCategoryEntity entity){
		VipCateGoryDto dto=new VipCateGoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPhoto(Const.getCdn(entity.getIcon()));
		dto.setDiamond(Tools.intValue(entity.getDiamond()));
		dto.setEnergy(Tools.intValue(entity.getEnergy()));
		dto.setMonths(entity.getMonths());
		return dto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getDiamond() {
		return diamond;
	}

	public void setDiamond(Integer diamond) {
		this.diamond = diamond;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	
	
}
