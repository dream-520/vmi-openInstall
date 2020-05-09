package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserEnergyAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserEnergyAccountLogTypeEnum;
import com.tigerjoys.shark.miai.dto.service.VipCateGoryDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.enums.VIPPayTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IVipContract;
import com.tigerjoys.shark.miai.inter.contract.IVipLogContract;
import com.tigerjoys.shark.miai.inter.contract.IVipSingleContract;
import com.tigerjoys.shark.miai.inter.entity.VipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.VipEntity;
import com.tigerjoys.shark.miai.inter.entity.VipLogEntity;
import com.tigerjoys.shark.miai.inter.entity.VipSingleEntity;
import com.tigerjoys.shark.miai.service.IVipService;

/**
 * VIP服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class VipServiceImpl implements IVipService {

	private final long one_day = 3600L * 1000 * 24;

	private final int months_day = 30;
	@Autowired
	private IVipContract vipContract;

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private IVipCategoryContract vipCateGoryContract;

	@Autowired
	private IVipLogContract vipLogContract;

	@Autowired
	private IVipSingleContract vipSingleContract;

	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserEnergyAgent userEnergyAgent;
	
	

	@Override
	public ActionResult queryVip(long userId) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));

		List<VipSingleEntity> singleList = vipSingleContract.load(pageModel);
		HashMap<String, Long> hsmp = new HashMap<>();
		singleList.forEach(v -> {
			hsmp.put(v.getCategory_id().toString(), v.getCategory_id());
		});
		PageModel pgModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.asc("priority");
		List<VipCategoryEntity> list = vipCateGoryContract.load(pgModel);
		List<VipCateGoryDto> vipCateGoryList = new ArrayList<VipCateGoryDto>();
		if (Tools.isNotNull(list)) {
			for (VipCategoryEntity entity : list) {
				if (entity.getBuy_single() == 1 && hsmp.get(entity.getId().toString()) != null) {
					continue;
				} else {
					vipCateGoryList.add(VipCateGoryDto.pareDto(entity));
				}
			}
		}
		VipEntity vip = vipContract.findById(userId);
		String endtimeText = "";
		boolean isVip = true;
		if (Tools.isNotNull(vip)) {
			if (vip.getExpire_time().getTime() > System.currentTimeMillis()) {
				int n = getDay(new Date(), vip.getExpire_time());
				if (n < 3) {
					endtimeText = "您的VIP已不足" + (n + 1) + "天，请及时续费";
				} else {
					endtimeText = "有效期:" + Tools.getFormatDate(vip.getExpire_time(), "yyyy-MM-dd");
				}
			} else {
				endtimeText = "您的VIP已过期，请及时续费";
			}
		} else {
			endtimeText = "你尚未拥有VIP，请购买";
			isVip = false;
		}

		Long orderId = IdGenerater.generateId();
		HashMap<String, Object> outHsmp = new HashMap<String, Object>();
		outHsmp.put("vipList", vipCateGoryList);
		outHsmp.put("orderId", orderId);
		outHsmp.put("vipFlag", isVip);
		outHsmp.put("remainDiamond", userDiamondAgent.getDiamondBalance(userId));
		outHsmp.put("remainEnergy", userEnergyAgent.getEnergyBalance(userId)); 
		outHsmp.put("endtimeText", endtimeText);
		return ActionResult.success(outHsmp);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public ActionResult buyVip(long userid, String orderId, long vipId, int diamond) throws Exception {
		VipCategoryEntity vipCategory = vipCateGoryContract.findById(vipId);
		if (diamond != vipCategory.getDiamond()) {
			return ActionResult.fail(ErrorCodeEnum.sign_error);
		}

		DiamondResultDto<Long> result = userDiamondAgent.changeDiamondAccount(userid, vipCategory.getDiamond(), null,
				UserDiamondAccountLogTypeEnum.pay_vip.getCode(), 0, null, "" + orderId, vipCategory.getName());
		if (result.getCode() != 0) {
			return ActionResult.fail(result.getCode(), result.getMsg());
		}

		insertVipRecord(vipCategory,userid,orderId,vipId,VIPPayTypeEnum.diamond.getCode(),diamond);
		
		return ActionResult.success();
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ActionResult buyVipUsedEnergy(long userid, String orderId, long vipId, int energy) throws Exception {
		VipCategoryEntity vipCategory = vipCateGoryContract.findById(vipId);
		if (energy != vipCategory.getEnergy()) {
			return ActionResult.fail(ErrorCodeEnum.sign_error);
		}

		AgentResult result = userEnergyAgent.changeEnergyAccount(userid, energy, null, UserEnergyAccountLogTypeEnum.pay_vip.getCode(), 0, null, orderId, vipCategory.getName());
		if(result.getCode() != 0){
			return ActionResult.fail(result.getCode(), result.getCodemsg());
		}
		
		insertVipRecord(vipCategory,userid,orderId,vipId,VIPPayTypeEnum.energy.getCode(),energy);
		
		return ActionResult.success();
	}
	
	
	private void insertVipRecord(VipCategoryEntity vipCategory, long userid, String orderId, long vipId, int payType,
			int money) throws Exception {
		Date current = new Date();
		VipEntity vip = vipContract.findById(userid);
		if (Tools.isNull(vip)) {
			vip = new VipEntity();
			vip.setUserid(userid);
			vip.setCreate_time(current);
			vip.setUpdate_time(current);
			long endTime = current.getTime() + one_day * months_day * vipCategory.getMonths();
			vip.setExpire_time(new Date(endTime));
			vipContract.insert(vip);
		} else {
			vipContract.updateVipTime(userid, vipCategory.getMonths() * months_day);
		}
		vip = vipContract.findById(userid);
		userAgent.updateVip(userid, 1, vip.getExpire_time());
		if (vipCategory.getBuy_single() == 1) {
			VipSingleEntity single = new VipSingleEntity();
			single.setCategory_id(vipId);
			single.setUserid(userid);
			single.setCreate_time(current);
			vipSingleContract.insert(single);
		}
		VipLogEntity vipLog = new VipLogEntity();
		vipLog.setUserid(userid);
		vipLog.setCategory_id(vipId);
		vipLog.setPay_type(payType);
		vipLog.setDiamond(Long.valueOf(money));
		vipLog.setMonths(vipCategory.getMonths());
		vipLog.setCreate_time(current);
		vipLog.setTransaction_flow("" + orderId);
		vipLogContract.insert(vipLog);

		vipCateGoryContract.updateBuyNum(vipId);

	}

	/**
	 * 查询那个日期相差多少天
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private int getDay(Date beginDate, Date endDate) {
		Date newBegin = new Date(Tools.getDayTime(beginDate));
		Date newEnd = new Date(Tools.getDayTime(endDate));
		return (int) Math.abs((newBegin.getTime() - newEnd.getTime()) / one_day);
	}

}
