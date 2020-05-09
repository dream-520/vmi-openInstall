package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.javatuples.Pair;
import org.shark.miai.common.enums.CouponStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.inter.contract.ICouponContract;
import com.tigerjoys.shark.miai.inter.contract.ICouponTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.CouponEntity;
import com.tigerjoys.shark.miai.inter.entity.CouponTemplateEntity;

/**
 * 代金券代理接口实现类
 * 
 * @author yangjunming
 *
 */
@Service
public class CouponAgentImpl implements ICouponAgent {


	@Autowired
	private ICouponContract couponContract;

	@Autowired
	private ICouponTemplateContract couponTemplateContract;

	@Override
	public CouponEntity findById(long id) throws Exception {
		return couponContract.findById(id);
	}

	@Override
	public List<CouponEntity> findByUserId(long userId) throws Exception {
		Date current = new Date();
		PageModel pageModel = PageModel.getPageModel(0, 100);
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.le("start_time", current));
		pageModel.addQuery(Restrictions.ge("end_time", current));
		pageModel.addQuery(Restrictions.eq("status", CouponStatusEnum.available.getCode()));
		pageModel.desc("id");
		return couponContract.load(pageModel);
	}

	@Override
	public List<CouponEntity> findByUserId(long userId, int type, long lastAmount) throws Exception {
		Date current = new Date();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.le("start_time", current));
		pageModel.addQuery(Restrictions.ge("end_time", current));
		pageModel.addQuery(Restrictions.le("use_amount", lastAmount));
		pageModel.addQuery(Restrictions.eq("type", type));
		pageModel.addQuery(Restrictions.eq("status", CouponStatusEnum.available.getCode()));
		pageModel.addLimitField(0, 50);
		pageModel.desc("id");
		return couponContract.load(pageModel);
	}

	@Override
	public void addUserCoupon(long userId, int groupid) throws Exception {
		Date current = new Date();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("group_id", groupid));
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.desc("amount");
		List<CouponTemplateEntity> list = couponTemplateContract.load(pageModel);
		List<CouponEntity> couponList = new ArrayList<>();
		list.forEach(v -> {
			CouponEntity entity = new CouponEntity();
			entity.setUserid(userId);
			entity.setName(v.getName());
			entity.setTemplate_id(v.getId());
			entity.setAmount(v.getAmount());
			entity.setCreate_time(current);
			entity.setStart_time(new Date(Tools.getDayTime(current)));
			long endMillis = Tools.getdate(entity.getStart_time(), v.getValidity()).getTime() - 1000;
			entity.setEnd_time(new Date(endMillis));
			entity.setGroup_id(v.getGroup_id());
			entity.setType(v.getType());
			entity.setStatus(CouponStatusEnum.available.getCode());
			entity.setUse_amount(v.getUse_amount());
			couponList.add(entity);
		});
		if (!couponList.isEmpty()) {
			insertAll(couponList);
		}
	}

	@Override
	public void insert(CouponEntity t) throws Exception {
		couponContract.insert(t);
	}

	@Override
	public void insertAll(List<CouponEntity> list) throws Exception {
		couponContract.insertAll(list);

	}

	@Override
	public int useCoupon(long id, long orderId) throws Exception {
		return couponContract.useCoupon(id, orderId);
	}

	@Override
	public void recoverCoupon(long id) throws Exception {
		couponContract.recoverCoupon(id);
	}

	@Override
	public int deleteCoupon(long id) throws Exception {
		return couponContract.deleteCoupon(id);
	}

	@Override
	public Pair<Boolean, CouponEntity> checkCoupon(long userId, long couponId, int type, long amount) throws Exception {
		boolean result = true;
		CouponEntity coupon = findById(couponId);
		long currtime = System.currentTimeMillis();
		if (coupon == null) {
			result = false;
		} else if (coupon.getUserid() != userId) {
			result = false;
		} else if (coupon.getType() != type) {
			result = false;
		} else if (amount < coupon.getAmount()) {
			result = false;
		} else if (coupon.getStatus() != CouponStatusEnum.available.getCode()) {
			result = false;
		} else if (currtime < coupon.getStart_time().getTime() || currtime > coupon.getEnd_time().getTime()) {
			result = false;
		}

		return new Pair<Boolean, CouponEntity>(result, coupon);
	}

	@Override
	public long queryUserCouponNum(long userId, Integer groupid, Integer status) throws Exception {
		Date current = new Date();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		if (Tools.isNotNull(groupid)) {
			pageModel.addQuery(Restrictions.eq("group_id", groupid));
		}
		if (Tools.isNotNull(status)) {
			if (status == CouponStatusEnum.available.getCode()) {
				pageModel.addQuery(Restrictions.eq("status", status));
				pageModel.addQuery(Restrictions.le("start_time", current));
				pageModel.addQuery(Restrictions.ge("end_time", current));
			} else if (status == CouponStatusEnum.Used.getCode()) {
				pageModel.addQuery(Restrictions.eq("status", status));
			} else if (status == CouponStatusEnum.out_of_date.getCode()) {
				pageModel.addQuery(Restrictions.eq("status", CouponStatusEnum.available.getCode()));
				pageModel.addQuery(Restrictions.lt("end_time", current));
			}
		}
		return couponContract.count(pageModel);
	}

}
