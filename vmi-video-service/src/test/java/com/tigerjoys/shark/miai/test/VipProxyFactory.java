package com.tigerjoys.shark.miai.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.VipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.VipEntity;
import com.tigerjoys.shark.miai.inter.entity.VipSingleEntity;

public class VipProxyFactory {
	/**
	 * 获的当天之后days日期，负数为当天之前的日期  
	 * @param days
	 * @return
	 */
	public static VipEntity createVip(int days){
		VipEntity vip=new VipEntity();
		vip.setExpire_time(Tools.getdate(new Date(), days));
		return vip;
	}
	
	public static List<VipCategoryEntity> createVipCateGory(){
		ArrayList<VipCategoryEntity>cateList=new ArrayList<VipCategoryEntity>();
		VipCategoryEntity vip=new VipCategoryEntity();
		vip.setId(10000L);
		vip.setBuy_num(500L);
		vip.setBuy_single(1);
		vip.setCreate_time(new Date());
		vip.setDiamond(500L);
		vip.setName("1个月");
		
		VipCategoryEntity vip2=new VipCategoryEntity();
		vip2.setId(20000L);
		vip2.setBuy_num(1000L);
		vip2.setBuy_single(0);
		vip2.setCreate_time(new Date());
		vip2.setDiamond(800L);
		vip2.setName("2个月");
		cateList.add(vip);
		cateList.add(vip2);
		
		return cateList;
	}
	
	public static VipCategoryEntity createVipCateGoryOne(){
		VipCategoryEntity vip2=new VipCategoryEntity();
		vip2.setId(20000L);
		vip2.setBuy_num(1000L);
		vip2.setBuy_single(0);
		vip2.setCreate_time(new Date());
		vip2.setDiamond(800L);
		vip2.setName("2个月");
		vip2.setMonths(1);
		return vip2;
	}
	
	public static List<VipSingleEntity> createSingleList(Long categroyId){
		List<VipSingleEntity> vipSingleList=new ArrayList<VipSingleEntity>();
		VipSingleEntity entity=new VipSingleEntity();
		entity.setCategory_id(categroyId);
		vipSingleList.add(entity);
		
		return vipSingleList;
	}
	
	
	
	
}
