package com.tigerjoys.shark.miai.dto.service;

import org.shark.miai.common.enums.CouponGroupEnum;
import org.shark.miai.common.enums.CouponTypeEnum;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;

/**
 * 代金券dto
 * @author yangjunming
 *
 */
public class CouponDto  {
	
	/**
	 * id
	 */
	private long id;
	
	/**
	 * 名称
	 */
	private String name;

	/**
	 * 来源组
	 */
	private String group;
	
	/**
	 * 类弄
	 */
	private String type;
	
	
	/**
	 * 金额
	 */
	private Integer amount;

	/**
	 * 限额使用
	 */
	private Integer useAmount;

	/**
	 * 结束时间
	 */
	private String endtime;

	
	public  static CouponDto pareDto(CouponEntity entity){
		CouponDto dto=new CouponDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setGroup(CouponGroupEnum.getDescByCode(entity.getGroup_id()));
		dto.setType(CouponTypeEnum.getDescByCode(entity.getType()));
		dto.setAmount(entity.getAmount()/100);
		dto.setUseAmount(entity.getUse_amount()/100);
		dto.setEndtime(Tools.getDate(entity.getEnd_time()));
		return dto;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(Integer useAmount) {
		this.useAmount = useAmount;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
