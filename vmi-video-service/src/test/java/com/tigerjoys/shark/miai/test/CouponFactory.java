package com.tigerjoys.shark.miai.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.shark.miai.inter.entity.CouponTemplateEntity;
import com.tigerjoys.shark.miai.inter.entity.VipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.VipEntity;
import com.tigerjoys.shark.miai.inter.entity.VipSingleEntity;

public class CouponFactory {

	
	
	public static CouponEntity createCoupon(long id,long userid,int status ,int type,int amount,Date start,Date end){
		CouponEntity coupon=new CouponEntity();
		coupon.setId(id);
		coupon.setUserid(userid);
		coupon.setType(type);
		coupon.setAmount(amount);
		coupon.setStatus(status);
		coupon.setStart_time(start);
		coupon.setEnd_time(end);
		return coupon;
	}
	
	public static List<CouponTemplateEntity> createCouponTemplateList(){
		ArrayList<CouponTemplateEntity>couponList=new ArrayList<CouponTemplateEntity>();
		CouponTemplateEntity coupon=new CouponTemplateEntity();
		coupon.setAmount(5000);
		coupon.setCreate_time(new Date());
		coupon.setGroup_id(0);
		coupon.setId(10001L);
		coupon.setName("代金券50元");
		coupon.setStatus(1);
		coupon.setType(0);
		coupon.setUse_amount(9900);
		coupon.setValidity(30);
		
		CouponTemplateEntity coupon2=new CouponTemplateEntity();
		coupon2.setAmount(10000);
		coupon2.setCreate_time(new Date());
		coupon2.setGroup_id(0);
		coupon2.setId(10002L);
		coupon2.setName("代金券100元");
		coupon2.setStatus(1);
		coupon2.setType(0);
		coupon2.setUse_amount(19900);
		coupon2.setValidity(30);
		couponList.add(coupon);
		couponList.add(coupon2);
		return couponList;
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
