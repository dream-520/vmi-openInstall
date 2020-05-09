package com.tigerjoys.shark.miai.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.shark.miai.common.enums.AppNameEnum;
import org.shark.miai.common.enums.PlatformEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.context.UserDetails;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IIOSUserSmsAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserEnergyAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserScvcAgent;
import com.tigerjoys.shark.miai.agent.IUserSpecialRechargeAgent;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeConfigDto;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeRedFlowerConfigDto;
import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.FirstPayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeCategoryPriceEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeOrderDonorTimesEnum;
import com.tigerjoys.shark.miai.agent.enums.RechargeOrderDonorTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserEnergyAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.dto.service.RechargePriceDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppVersionContract;
import com.tigerjoys.shark.miai.inter.contract.IEnergyCustomCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IEnergyCustomMobileContract;
import com.tigerjoys.shark.miai.inter.contract.IEnergyCustomPriceContract;
import com.tigerjoys.shark.miai.inter.contract.IEnergyOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IPayUserRecordContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomMobileContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomPriceContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargePriceContract;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerOrderContract;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerPriceContract;
import com.tigerjoys.shark.miai.inter.contract.IUserFirstRechargeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeWithdrawalContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppVersionEntity;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomMobileEntity;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomPriceEntity;
import com.tigerjoys.shark.miai.inter.entity.EnergyOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomMobileEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomPriceEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargePriceEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerPriceEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeWithdrawalEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.service.IRechargeWithdrawalService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * @author mouzhanpeng at [2017年10月11日 下午4:05:56]
 * @since JDK 1.8.0
 */
@Service
public class RechargeWithdrawalServiceImpl implements IRechargeWithdrawalService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRechargePriceContract rechargePriceContract;

	@Autowired
	private IRechargeCustomCategoryContract rechargeCustomCategoryContract;

	@Autowired
	private IRechargeCustomPriceContract rechargeCustomPriceContract;

	@Autowired
	private IRechargeCustomMobileContract rechargeCustomMobileContract;

	@Autowired
	private IRechargeOrderContract rechargeOrderContract;

	@Autowired
	private IUserDiamondAgent userDiamondAgent;

	@Autowired
	private IPayAgent payAgent;

	@Autowired
	private IUserIncomeAccountContract userIncomeAccountContract;

	@Autowired
	private IUserIncomeAccountLogContract userIncomeAccountLogContract;

	@Autowired
	private IUserIncomeWithdrawalContract userIncomeWithdrawalContract;

	@Autowired
	private ISysConfigAgent sysConfigAgent;

	@Autowired
	private IAppVersionContract appVersionContract;
	
	@Autowired
	private IUserScvcAgent userScvcAgent;

	@Autowired
	private IUserIncomeAgent userIncomeAgent;

	@Autowired
	private IEnergyCustomCategoryContract energyCustomCategoryContract;

	@Autowired
	private IEnergyCustomMobileContract energyCustomMobileContract;

	@Autowired
	private IEnergyCustomPriceContract energyCustomPriceContract;

	@Autowired
	private IUserEnergyAgent userEnergyAgent;

	@Autowired
	private IEnergyOrderContract energyOrderContract;

	@Autowired
	private IRedFlowerPriceContract redFlowerPriceContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IPayUserRecordContract payUserRecordContract;
	
	@Autowired
	private IUserFirstRechargeLogContract userFirstRechargeLogContract;
	
	@Autowired
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	
	@Autowired
	private IRedFlowerOrderContract redFlowerOrderContract;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	
	
	@Autowired
	private IIOSUserSmsAgent iOSUserSmsAgent;
	
	@Autowired
	private IUserSpecialRechargeAgent userSpecialRechargeAgent;
	
	

	@Override
	public ActionResult getNativePriceList() throws Exception {
		return ActionResult.success(getPriceList(1,0));
	}
	
	
	public Map<String, Object> getPriceList(int type,int priceType) throws Exception {
		BeatContext bc = RequestUtils.getCurrent();
		long userId = bc.getUserid();
		String channel = bc.getHeader().getChannel();
		Map<String, Object> map = new HashMap<>();
		map.put("balance", userDiamondAgent.getDiamondBalance(userId));
		map.put("income", Tools.formatDouble2Percent(userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL)));
		UserBO userBo = userAgent.findById(userId);
		
		int os = bc.getHeader().getOs_type();
		String packageName = bc.getHeader().getPackageName();
		if(PlatformEnum.android.type == os){
			List<RechargeCustomPriceEntity> list = getPricesByMobile(bc.getHeader().getMobile_model(), packageName, userId,priceType);
			if (Tools.isNotNull(list)) {
				String cityCode = RequestUtils.getCurrent().getHeader().getCityCode();
				
				if(AppNameEnum.andriod_com_tjhj_miliao.getPackageName().equalsIgnoreCase(packageName)){
					//不在处理
				} else {
					if("332".equals(cityCode) || userBo.getProvinceid()==3958L ){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if( ("131".equals(cityCode) || userBo.getProvinceid()==3593L )){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if(type == 1){
						RechargeCustomCategoryEntity category = getRechargeCustomCategory(bc.getHeader().getMobile_model());
						if(priceType == RechargeCategoryPriceEnum.video.getCode() || priceType == RechargeCategoryPriceEnum.audio.getCode()){
						    if(Tools.isNotNull(category)){
						    	if(category.getId()<=5){
						    		list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
						    	}else if(category.getId()<=8){
						    		list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
						    	}else {
						    		list = list.stream().filter(v->v.getMoney()>=20000).collect(Collectors.toList());
						    	}
						    }
						}
					}
					
					
					if("User_Share_i".equalsIgnoreCase(channel) || "User_Share_a".equalsIgnoreCase(channel) ){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
				
					if(checkChargeLimit(userId)){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if(checkChargeLimit200(userId)){
						list = list.stream().filter(v->v.getMoney()>=20000).collect(Collectors.toList());
					}
					
					if(checkUserAPPLimit(userId)){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
				
					
					long day = (Tools.getDayTime()-Tools.getDayTime(userBo.getCreateTime()))/(24 * 60 * 60 * 1000);
					if(!Const.IS_TEST && day>=30){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
				}
				
				if(checkUserAccountLimit(userId)){
					list = list.stream().filter(v->v.getMoney()>=50000).collect(Collectors.toList());
				}
				
				
				if(AppNameEnum.andriod_com_tjhj_miliao.getPackageName().equalsIgnoreCase(packageName)){
					list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
				}
				
				
				if(userSpecialRechargeAgent.getUserIdList().contains(userId)){
					list.add(0,ServiceHelper.FirstRechargePrice.getDynamicPrice(Long.MAX_VALUE-3));
				}
				
				List<Map<String, Object>> prices = new ArrayList<>();
				boolean firstCharge = checkFirstRecharge(userId);
				for (RechargeCustomPriceEntity price : list) {
					Map<String, Object> item = new HashMap<>();
					item.put("priceId", price.getId());
					item.put("title", price.getTitle());
					if(firstCharge || price.getDonor_times().equals(RechargeOrderDonorTimesEnum.every.getCode())){
						item.put("description", Tools.isNull(price.getDescription()) ? null : price.getDescription());
					}else {
						item.put("description",Tools.isNull(price.getRepetition_desc()) ? null : price.getRepetition_desc());
					}
					item.put("money", price.getMoney() / 100);
					item.put("diamond", price.getDiamond());
					item.put("actionUrl", Const.WEB_SITE+"/api/product/all/"+1+"/"+price.getId());
					prices.add(item);
				}
				map.put("prices", prices);
			}
		}else{
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("status", 1));
			pageModel.addQuery(Restrictions.eq("platform", os));
			pageModel.addQuery(Restrictions.eq("price_type", priceType));
			pageModel.asc("priority");
			List<RechargePriceEntity> list = rechargePriceContract.load(pageModel);
			if (Tools.isNotNull(list)) {
				String cityCode = RequestUtils.getCurrent().getHeader().getCityCode();
				if(AppNameEnum.ios_com_tjhj_miyou.getPackageName().equalsIgnoreCase(packageName)	
						|| AppNameEnum.ios_com_duidui_duijiaoyou.getPackageName().equalsIgnoreCase(packageName)	
						|| AppNameEnum.ios_com_xqjy_milian.getPackageName().equalsIgnoreCase(packageName)	
						|| AppNameEnum.ios_com_xq_yuanyuan.getPackageName().equalsIgnoreCase(packageName)	
						|| AppNameEnum.ios_com_jiaoyou_quliao.getPackageName().equalsIgnoreCase(packageName)	
						){
						//不在处理
				} else {
					if("332".equals(cityCode) || userBo.getProvinceid()==3958L ){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if(  "131".equals(cityCode) || userBo.getProvinceid()==3593L){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if( type ==1 ){
						if(priceType == RechargeCategoryPriceEnum.video.getCode()){
							list = list.stream().filter(v->v.getMoney()>=20000).collect(Collectors.toList());
						}
						if(priceType == RechargeCategoryPriceEnum.audio.getCode()){
							list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
						}
					}
					if("User_Share_i".equalsIgnoreCase(channel) || "User_Share_a".equalsIgnoreCase(channel) ){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if(checkChargeLimit(userId)){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if(checkChargeLimit200(userId)){
						list = list.stream().filter(v->v.getMoney()>=20000).collect(Collectors.toList());
					}
					
					if(checkUserAPPLimit(userId)){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
					if(checkUserAccountLimit(userId)){
						list = list.stream().filter(v->v.getMoney()>=50000).collect(Collectors.toList());
					}
					
					long day = (Tools.getDayTime()-Tools.getDayTime(userBo.getCreateTime()))/(24 * 60 * 60 * 1000);
					if(!Const.IS_TEST && day>=30){
						list = list.stream().filter(v->v.getMoney()>=10000).collect(Collectors.toList());
					}
					
				}
				/*
				if(checkUserAccountLimit(userId)){
					list = list.stream().filter(v->v.getMoney()>=50000).collect(Collectors.toList());
				}
				*/
				if(com.tigerjoys.shark.miai.agent.constant.Const.USER_ID_CHARGE_PRICE_LIST.contains(userId)){
					list.add(0,ServiceHelper.FirstRechargePrice.getIosDynamicPrice(Long.MAX_VALUE-3));
				}
				
				List<Map<String, Object>> prices = new ArrayList<>();
				
				boolean firstCharge = checkFirstRecharge(userId);
				for (RechargePriceEntity price : list) {
					Map<String, Object> item = new HashMap<>();
					item.put("priceId", price.getId());
					item.put("identifier", getIpaPricePrefix(packageName)+Math.round(price.getMoney()/100.0));
					item.put("title", price.getTitle());
					
					if(firstCharge || price.getDonor_times().equals(RechargeOrderDonorTimesEnum.every.getCode())){
						item.put("description", Tools.isNull(price.getDescription()) ? null : price.getDescription());
					}else {
						item.put("description",Tools.isNull(price.getRepetition_desc()) ? null : price.getRepetition_desc());
					}
					
					item.put("money", price.getMoney() / 100);
					item.put("diamond", price.getDiamond());
					if (Const.IOS_TEST_CHARGE_DIAMOND.containsKey(userId)) {
						item.put("diamond", price.getMoney() / 100);
					}
					item.put("actionUrl", Const.WEB_SITE+"/api/product/all/"+1+"/"+price.getId());
					prices.add(item);
				}
				pageModel.clearAll().addLimitField(0, 1).addQuery(Restrictions.eq("platform", os)).addQuery(Restrictions.eq("code", bc.getHeader().getVersioncode()));
				List<AppVersionEntity> v = appVersionContract.load(pageModel);
				
				if (Tools.isNotNull(v)) {
					String userName = RequestUtils.getCurrent().getUser().getUsername();
					if (iOSUserSmsAgent.getUserSmsList().containsKey(userName)) {
						map.put("shield", 1);
					} else {
						map.put("shield", v.get(0).getBlock_apple_pay());
					}
				} else {
					map.put("shield", 1);
				}
				map.put("prices", prices);
			}
		}
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("code", bc.getHeader().getVersioncode()));
		pageModel.addQuery(Restrictions.eq("platform", os));
		List<AppVersionEntity> versionList = appVersionContract.load(pageModel);
		int incomeStatus = 0;
		if (Tools.isNotNull(versionList)) {
			incomeStatus = versionList.get(0).getIncome_status();
		}
		map.put("incomeStatus", incomeStatus);
	
		return map;
	}
	
	private String getIpaPricePrefix(String packageName){
		Map<String,String> hsmp = new HashMap<>();
		hsmp.put(AppNameEnum.ios_com_tjhj_miyou.getPackageName(), "CT_P_");
		String prefix = hsmp.get(packageName);
		return Tools.isNotNull(prefix)?prefix:"";
	}

	/**
	 * 根据手机型号获取价格列表
	 * @param model
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	private List<RechargeCustomPriceEntity> getPricesByMobile(String model, String packageName,long userId ,int priceType) throws Exception {
		List<RechargeCustomPriceEntity> list = new ArrayList<>();
		UserBO userBO = userAgent.findById(userId);
		RechargeCustomCategoryEntity category = null;
		if(Tools.isNotNull(userBO)){
			long userReqTime = Long.valueOf(Tools.getFormatDate(userBO.getCreateTime(), "yyyyMMdd"));
			PayUserRecordEntity payUserRecord = payUserRecordContract.findByProperty("userid", userId);
			if(20191126>userReqTime && Tools.isNotNull(payUserRecord) && payUserRecord.getPay_money()>=10000 ){
				category = rechargeCustomCategoryContract.findById(Const.OLD_USER_RECHARGE_CUSTOM_CATEGORY);
			}else{
				category = getRechargeCustomCategory(model);
			}
		}
	    if(Tools.isNull(category)){
	    	return list;
	    }
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("category_id", category.getId()));
		pageModel.addQuery(Restrictions.eq("status",1));
		//if(Tools.isNotNull(packageName) && packageName.equals("com.tjhj.miliao")) {
			//pageModel.addQuery(Restrictions.eq("type",1));
		//} else {
			pageModel.addQuery(Restrictions.eq("type",priceType));
		//}
		pageModel.asc("priority");
		List<RechargeCustomPriceEntity> prices = rechargeCustomPriceContract.load(pageModel);
		if(Tools.isNotNull(prices)){
			if(category.getFirst() == 1 && checkFirstRecharge(userId) ){
				//prices.add(0,ServiceHelper.FirstRechargePrice.getPrice(sysConfigAgent.firstCharge()));
				FirstChargeConfigDto firstPrice = new FirstChargeConfigDto();
				firstPrice.setTitle(category.getFirst_title());
				firstPrice.setDescription(category.getFirst_disc());
				firstPrice.setMoney(category.getFirst_money());
				firstPrice.setDiamond(category.getFirst_diamond());
				prices.add(0,ServiceHelper.FirstRechargePrice.getPrice(firstPrice));
			}
			list.addAll(prices);
		}
		return list;
	}
	
	public RechargeCustomCategoryEntity getRechargeCustomCategory(String model) throws Exception{
		RechargeCustomMobileEntity mobile = rechargeCustomMobileContract.findByProperty("name", Tools.formatString(model));
		RechargeCustomCategoryEntity category = null;
		if(Tools.isNull(mobile)){
			category = rechargeCustomCategoryContract.findByProperty("name", "其他");
			if(Tools.isNull(category)){
				return null;
			}
			try {
				RechargeCustomMobileEntity entity = new RechargeCustomMobileEntity();
				entity.setCreate_time(new Date());
				entity.setUpdate_time(new Date());
				entity.setCategory_id(category.getId());
				entity.setName(Tools.formatString(model));
				entity.setStatus(1);
				rechargeCustomMobileContract.insert(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			category = rechargeCustomCategoryContract.findById(mobile.getCategory_id());
		}
		return category;
	}
	
	/**
	 * 根据手机型号获取首充列表
	 * @param model
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private FirstChargeConfigDto getFirstCharge() throws Exception {
		BeatContext bc = RequestUtils.getCurrent();
		String model = bc.getHeader().getMobile_model();
		RechargeCustomCategoryEntity category = getRechargeCustomCategory(model);
	    if(Tools.isNull(category)){
	    	return null;
	    }
		
		if (Tools.isNotNull(category)) {
			FirstChargeConfigDto firstPrice = new FirstChargeConfigDto();
			firstPrice.setTitle(category.getFirst_title());
			firstPrice.setDescription(category.getFirst_disc());
			firstPrice.setMoney(category.getFirst_money());
			firstPrice.setDiamond(category.getFirst_diamond());
			return firstPrice;
		}
		return null;
	}
	
	/**
	 * 根据手机型号获取首充列表
	 * @param model
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public FirstChargeRedFlowerConfigDto getFirstRedFlowerCategoryCharge() throws Exception {
		BeatContext bc = RequestUtils.getCurrent();
		String model = bc.getHeader().getMobile_model();
		RechargeCustomCategoryEntity category = getRechargeCustomCategory(model);
	    if(Tools.isNull(category)){
	    	return null;
	    }
		if (Tools.isNotNull(category)) {
			FirstChargeRedFlowerConfigDto firstPrice = new FirstChargeRedFlowerConfigDto();
			firstPrice.setTitle(category.getFirst_flower_title());
			firstPrice.setDescription(category.getFirst_flower_disc());
			firstPrice.setMoney(category.getFirst_flower_money());
			firstPrice.setFlower(category.getFirst_flower_num());
			return firstPrice;
		}
		return null;
	}
	

	@Override
	public RechargePriceDto getRechargePriceByPriceId(int osType, long category, Long priceId) throws Exception {
		RechargePriceDto dto = new RechargePriceDto();
		dto.setPriceId(priceId);
		//dto.set
		if(category == RechargeCategoryPriceEnum.video.getCode() 
			|| category == RechargeCategoryPriceEnum.audio.getCode()
			|| category == RechargeCategoryPriceEnum.video_ipa.getCode()
			|| category == RechargeCategoryPriceEnum.audio_ipa.getCode()
				){
			if(osType == PlatformEnum.android.type){
				//RechargeCustomPriceEntity customPrice = Long.MAX_VALUE == priceId ? ServiceHelper.FirstRechargePrice.getPrice(getFirstCharge()):rechargeCustomPriceContract.findById(priceId);
				RechargeCustomPriceEntity customPrice = null ;
				if( Long.MAX_VALUE == priceId){
					customPrice = ServiceHelper.FirstRechargePrice.getPrice(getFirstCharge());
				}else if((Long.MAX_VALUE-1) == priceId || (Long.MAX_VALUE-2) == priceId || (Long.MAX_VALUE-3) == priceId){
					customPrice = ServiceHelper.FirstRechargePrice.getDynamicPrice(priceId);
				}else{
					customPrice = rechargeCustomPriceContract.findById(priceId);
				}
				dto.setDiamond(customPrice.getDiamond());
				dto.setMoney(customPrice.getMoney());
				dto.setBuyText("钻");
			}else{
				RechargePriceEntity rechargePrice = null;
				if((Long.MAX_VALUE-1) == priceId || (Long.MAX_VALUE-2) == priceId || (Long.MAX_VALUE-3) == priceId){
					rechargePrice = ServiceHelper.FirstRechargePrice.getIosDynamicPrice(priceId);
				}else{
					rechargePrice = rechargePriceContract.findById(priceId);  //IOS没有1元首充
					
				}
				dto.setDiamond(rechargePrice.getDiamond());
				dto.setMoney(rechargePrice.getMoney());
				dto.setBuyText("钻");
			}
		
		}else if(category ==  RechargeCategoryPriceEnum.red_flower.getCode()){
			RedFlowerPriceEntity flowerPrice = null;
			if((Long.MAX_VALUE-100) == priceId){
				if( osType == PlatformEnum.android.type){
					flowerPrice = ServiceHelper.FirstRechargePrice.getRedFlowerPrice(getFirstRedFlowerCategoryCharge());
				}else{
					flowerPrice = ServiceHelper.FirstRechargePrice.getRedFlowerPrice(sysConfigAgent.firstChargeRedFlower());
				}
				
			}else if((Long.MAX_VALUE-1) == priceId){
				flowerPrice = ServiceHelper.FirstRechargePrice.getRedFlowerDynamicPrice(Long.MAX_VALUE-1);
			}else {
				flowerPrice =  redFlowerPriceContract.findById(priceId);
			}
			dto.setDiamond(flowerPrice.getFlower());
			dto.setMoney(flowerPrice.getMoney());
			dto.setBuyText("朵");
		}else if(category ==  RechargeCategoryPriceEnum.guard.getCode() || category ==  RechargeCategoryPriceEnum.VIP.getCode()){
			GuardVipCategoryEntity guardVip = null;
			if (category == RechargeCategoryPriceEnum.VIP.getCode() && Const.vipBuyExperienceDto.getPriceId() == priceId ){
				guardVip = new GuardVipCategoryEntity();
				guardVip.setDays((int)Const.vipBuyExperienceDto.getDays());
				guardVip.setMoney((int)Const.vipBuyExperienceDto.getMoney()*100);
				guardVip.setName("VIP体验");
			} else {
				guardVip = guardVipCategoryContract.findById(priceId);
			}
			String channel = RequestUtils.getCurrent().getHeader().getChannel();
			if( (guardVip.getType()==2 || guardVip.getType()==10 || guardVip.getType()==12  ) && "FBL_20200318_1".equals(channel) && guardVip.getDays() ==30 ){
				guardVip.setMoney(1800); //渠道 FBL_20200318_1 的VIP 其它、<1500 1500~2500这3个档 1月为18元
			}
			
			
			long userId = RequestUtils.getCurrent().getUserid();
			if(userSpecialRechargeAgent.getUserIdList().contains(userId)){
				guardVip.setMoney(100);
			}
			dto.setDiamond(guardVip.getDays());
			dto.setMoney(guardVip.getMoney());
			dto.setBuyText("天 "+guardVip.getName());
		}else if(category ==  RechargeCategoryPriceEnum.weeks_card.getCode()) {
			GuardVipCategoryEntity guardVip = guardVipCategoryContract.findById(priceId);
			long userId = RequestUtils.getCurrent().getUserid();
			if(userSpecialRechargeAgent.getUserIdList().contains(userId)){
				guardVip.setMoney(100);
			}
			dto.setDiamond(guardVip.getDays());
			dto.setMoney(guardVip.getMoney());
			dto.setBuyText("天 "+guardVip.getName());
		}else if(category ==  RechargeCategoryPriceEnum.dial_experience.getCode()) {
			//long userId = RequestUtils.getCurrent().getUserid();
			//dto.setDiamond();
			dto.setMoney(priceId.intValue()*100);
			dto.setBuyText(RechargeCategoryPriceEnum.dial_experience.getDesc());
		}
		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult recharge(long priceId, int channelCode, double money, double income, Map<String,String> ipaParam) throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		RequestHeader header = context.getHeader();
		UserDetails user = context.getUser();
		Long userId = user.getUserid();
		String nickname = Tools.isNull(user.getNickname()) ? "" : user.getNickname();
		String mobile = Tools.isNull(user.getMobile()) ? "" : user.getMobile();
		Date date = new Date();
		int mon = new BigDecimal(String.valueOf(money)).multiply(new BigDecimal("100")).intValue(), inc = new BigDecimal(String.valueOf(income)).multiply(new BigDecimal("100")).intValue();
		if(0 < inc && inc > userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL)){
			return ActionResult.fail(ErrorCodeEnum.income_pay_not_enough);
		}
		if(PlatformEnum.android.type == header.getOs_type()){
			// 一金币首冲价格id 取Long 最大值
			//RechargeCustomPriceEntity price = Long.MAX_VALUE == priceId ? ServiceHelper.FirstRechargePrice.getPrice(getFirstCharge()) : rechargeCustomPriceContract.findById(priceId);
			RechargeCustomPriceEntity price = null;
			if(Long.MAX_VALUE == priceId){
				price = ServiceHelper.FirstRechargePrice.getPrice(getFirstCharge());
			}else if((Long.MAX_VALUE-1) == priceId || (Long.MAX_VALUE-2) == priceId || (Long.MAX_VALUE-3) == priceId){
				price = ServiceHelper.FirstRechargePrice.getDynamicPrice(priceId);
			}else{
				price = rechargeCustomPriceContract.findById(priceId);
			}
			
			if (Tools.isNotNull(price)) {
				if (mon + inc != price.getMoney() || sysConfigAgent.checkIncomeRadioNoPass(price.getMoney(),inc)) {
					return ActionResult.fail(ErrorCodeEnum.parameter_error);
				}
			} else {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			RechargeOrderEntity order = new RechargeOrderEntity();
			order.setCreate_time(date);
			order.setUpdate_time(date);
			order.setUser_id(userId);
			order.setMobile(mobile);
			order.setNickname(nickname);
			order.setPrice_id(price.getId());
			order.setPrice(price.getMoney());
			order.setDiamond(price.getDiamond());
			order.setDonor_type(price.getDonor_type());
			order.setDonor_times(price.getDonor_times());
			order.setDonor(price.getDonor());
			order.setMoney(mon);
			order.setIncome(inc);
			order.setStatus(0);
			order.setPackage_name(header.getPackageName());
			rechargeOrderContract.insert(order);
			// 收益冲钻
			if(0 == money){
				IncomeResultDto<Long> result = userIncomeAgent.changeIncomeAccount(userId, inc, 0, UserIncomeAccountLogTypeEnum.buy_diamond, String.valueOf(order.getId()),
						UserIncomeAccountLogTypeEnum.buy_diamond.getDesc());
				if(0 == result.getCode()){
					userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 0, inc);
					// 冲钻
					long chargeDiamond = price.getDiamond();
					if(price.getDonor_type() == RechargeOrderDonorTypeEnum.diamond.getCode()){
						if( price.getDonor()>0){
							if(price.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(userId)){
								chargeDiamond = price.getDiamond() + price.getDonor();
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
								chargeDiamond = price.getDiamond() + price.getDonor();
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
								chargeDiamond = price.getDiamond() ;
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(userId) ){
								chargeDiamond = price.getDiamond() + price.getDonor();
							}
						}
					}
					userDiamondAgent.changeDiamondAccount(userId, chargeDiamond ,
							(long)mon, UserDiamondAccountLogTypeEnum.recharge_account.getCode(), 1, channelCode,
							String.valueOf(order.getId()), UserDiamondAccountLogTypeEnum.recharge_account.getDesc());
					
					if(price.getDonor_type() == RechargeOrderDonorTypeEnum.money.getCode()){
						if(price.getDonor() > 0){
							if(price.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(userId)){
								userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, price.getDonor()*100);
								userIncomeAgent.changeIncomeAccount(userId, price.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(order.getId()),
										UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
								userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, price.getDonor()*100);
								userIncomeAgent.changeIncomeAccount(userId, price.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(order.getId()),
										UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
								
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(userId) ){
								userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, price.getDonor()*100);
								userIncomeAgent.changeIncomeAccount(userId, price.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(order.getId()),
										UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
							}
						}
					}
					
					
					RechargeOrderEntity o = new RechargeOrderEntity();
					o.setOrder_id(order.getId());
					o.setUpdate_time(date);
					o.setStatus(1);
					rechargeOrderContract.update(o);
					Map<String, Object> data = new HashMap<>();
					data.put("channel", channelCode);
					data.put("signData", result.getData());
					return ActionResult.success(data);
				}else{
					return  ActionResult.fail(result.getCode(), result.getMsg());
				}
			}

			PayAccessDto access = new PayAccessDto();
			access.setUser_id(user.getUserid());
			access.setNickname(user.getNickname());
			access.setMobile(user.getMobile());
			access.setOrder_id(order.getId());
			access.setInitialPrice((long)price.getMoney());
			String fm = Tools.formatDouble2PercentToString(mon);
			access.setSubject("[钻石充值：" + fm + "元]");
			access.setDescription("用户账户钻石充值花费：" + fm + "元");
			access.setMoney((long) mon);
			access.setPay_channel(PayChannelEnum.getByCode(channelCode));
			access.setType(PayTypeEnum.recharge_diamond);
			access.setApp_type(header.getOs_type());
			access.setApp_channel(header.getChannel());
			access.setApp_version(header.getVersion());
			access.setPackage_name(header.getPackageName());
			return ServiceHelper.dealPayData(payAgent.preparePay(access), PayChannelEnum.getByCode(channelCode));
		}else{
			
			RechargePriceEntity price = null;
			if((Long.MAX_VALUE-1) == priceId || (Long.MAX_VALUE-2) == priceId || (Long.MAX_VALUE-3) == priceId){
				price = ServiceHelper.FirstRechargePrice.getIosDynamicPrice(priceId);
			}else{
				price = rechargePriceContract.findById(priceId);
			}
			
			if (Tools.isNotNull(price)) {
				if (mon + inc != price.getMoney() || sysConfigAgent.checkIncomeRadioNoPass(price.getMoney(),inc)) {
					return ActionResult.fail(ErrorCodeEnum.parameter_error);
				}
			} else {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			String transactionId = null;
			String receipt = null;
			if(Tools.isNotNull(ipaParam)){
				transactionId = ipaParam.get("transactionId");
				receipt = ipaParam.get("receipt");
			}
			if (PayChannelEnum.iap.getCode() == channelCode && Tools.isNull(receipt) && Tools.isNull(transactionId)) {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			// 同一订单可能多次验证
			Long orderId = payAgent.ensureOrderViaReceipt(MD5.encode(receipt));
			if (null == orderId || null == rechargeOrderContract.findById(orderId)) {
				RechargeOrderEntity order = new RechargeOrderEntity();
				order.setCreate_time(date);
				order.setUpdate_time(date);
				order.setUser_id(userId);
				order.setMobile(mobile);
				order.setNickname(nickname);
				order.setPrice_id(price.getId());
				order.setPrice(price.getMoney());
				order.setDiamond(price.getDiamond());
				order.setDonor_type(price.getDonor_type());
				order.setDonor_times(price.getDonor_times());
				order.setDonor(price.getDonor());
				order.setMoney(mon);
				order.setIncome(inc);
				order.setStatus(0);
				rechargeOrderContract.insert(order);
				orderId = order.getOrder_id();
			}
			// 收益冲钻
			if(0 == money){
				IncomeResultDto<Long> result = userIncomeAgent.changeIncomeAccount(userId, inc, 0, UserIncomeAccountLogTypeEnum.buy_diamond, String.valueOf(orderId),
						UserIncomeAccountLogTypeEnum.buy_diamond.getDesc());
				if(0 == result.getCode()){
					userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 0, inc);
					// 冲钻
					long chargeDiamond = price.getDiamond();
					if(price.getDonor_type() == RechargeOrderDonorTypeEnum.diamond.getCode()){
						if(price.getDonor() > 0){
							if(price.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(userId)){
								chargeDiamond = price.getDiamond() + price.getDonor();
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
								chargeDiamond = price.getDiamond() + price.getDonor();
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
								chargeDiamond = price.getDiamond() ;
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(userId) ){
								chargeDiamond = price.getDiamond() + price.getDonor();
							}
						}
					}
					userDiamondAgent.changeDiamondAccount(userId, chargeDiamond,
							(long)mon, UserDiamondAccountLogTypeEnum.recharge_account.getCode(), 1, channelCode,
							String.valueOf(orderId), UserDiamondAccountLogTypeEnum.recharge_account.getDesc());
					/*
					 * 充钻返收益
					 */
					if(price.getDonor_type() == RechargeOrderDonorTypeEnum.money.getCode()){
						if(price.getDonor() > 0){
							if(price.getDonor_times() == RechargeOrderDonorTimesEnum.first.getCode() && checkFirstRecharge(userId)){
								userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, price.getDonor()*100);
								userIncomeAgent.changeIncomeAccount(userId, price.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(orderId),
										UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.every.getCode()){
								userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, price.getDonor()*100);
								userIncomeAgent.changeIncomeAccount(userId, price.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(orderId),
										UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.nothing.getCode()){
								
							}else if(price.getDonor_times() ==  RechargeOrderDonorTimesEnum.repetition_desc.getCode() && !checkFirstRecharge(userId) ){
								userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, price.getDonor()*100);
								userIncomeAgent.changeIncomeAccount(userId, price.getDonor()*100, 1, UserIncomeAccountLogTypeEnum.recharge_back_income, String.valueOf(orderId),
										UserIncomeAccountLogTypeEnum.recharge_back_income.getDesc());
								
							}
						}
					}
					
					RechargeOrderEntity order = new RechargeOrderEntity();
					order.setOrder_id(orderId);
					order.setStatus(1);
					rechargeOrderContract.update(order);
					Map<String, Object> data = new HashMap<>();
					data.put("channel", channelCode);
					data.put("signData", result.getData());
					return ActionResult.success(data);
				}else{
					return  ActionResult.fail(result.getCode(), result.getMsg());
				}
			}

			PayAccessDto access = new PayAccessDto();
			access.setUser_id(user.getUserid());
			access.setNickname(user.getNickname());
			access.setMobile(user.getMobile());
			access.setOrder_id(orderId);
			String fm = Tools.formatDouble2PercentToString(mon);
			access.setSubject("[钻石充值：" + fm + "元]");
			access.setDescription("用户账户钻石充值花费：" + fm + "元");
			access.setMoney((long) mon);
			access.setInitialPrice((long)price.getMoney());
			access.setPay_channel(PayChannelEnum.getByCode(channelCode));
			access.setType(PayTypeEnum.recharge_diamond);
			access.setApp_type(header.getOs_type());
			access.setApp_channel(header.getChannel());
			access.setApp_version(header.getVersion());
			access.setPackage_name(header.getPackageName());
			// iap 字段
			access.setReceipt(receipt);
			access.setProduct_id(price.getPrice_identifier());
			access.setIpaTransactionId(transactionId);
			return ServiceHelper.dealPayData(payAgent.preparePay(access), PayChannelEnum.getByCode(channelCode));
		}
	}

	@Override
	public ActionResult getIncomePage() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		Map<String, Object> data = new HashMap<>();
		UserIncomeAccountEntity account = userIncomeAccountContract.findByUserIdAndType(userId, AccountType.GENERAL.getCode());
		if (null != account) {
			data.put("deposit", account.getDeposit() / 100.0);
			data.put("balance", account.getBalance() / 100.0);
		}
		data.put("limit", Const.WITHDRAWAL_LIMIT);
		StringBuilder sb = new StringBuilder();
		sb.append("1、每次不低于" + Const.WITHDRAWAL_LIMIT + "金币（1金币=1元）；\n");
		sb.append("2、用户必须经过认证才可提现到支付宝；\n");
		sb.append("3、按照国家法律法规，提现金额将收取" + sysConfigAgent.vacuate().getTaxRatio() + "%的劳务所得税；\n");
		sb.append("4、提现金额将在5个工作日内转到支付宝账户中；\n");
		sb.append("5、因填写信息错误导致转账失败或错误，本平台概不负责；\n");
		sb.append("6、最终解释权归官方所有。");
		data.put("note", sb.toString());
		return ActionResult.success(data);
	}

	@Override
	public ActionResult getBonusPage() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		Map<String, Object> data = new HashMap<>();
		UserIncomeAccountEntity account = userIncomeAccountContract.findByUserIdAndType(userId, AccountType.BONUS.getCode());
		if (null != account) {
			data.put("deposit", account.getDeposit() / 100.0);
			data.put("balance", account.getBalance() / 100.0);
		}
		data.put("scvc", userScvcAgent.getScvcDeposit(userId));
		StringBuilder sb = new StringBuilder();
		sb.append("1、奖励金是平台奖励用户，可以通过系统赠送、做任务等形式获得；\n");
		sb.append("2、奖励金不能够单独提现，需要在收益里面一起提现，并满足每" + Const.WITHDRAWAL_LIMIT +"金币收益，可提现100金币奖励金；\n");
		sb.append("3、最终解释权归官方所有。");
		data.put("note", sb.toString());
		return ActionResult.success(data);
	}

	@Override
	public ActionResult withdrawalCashLog() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		pageModel.addQuery(Restrictions.eq("type", AccountType.GENERAL.getCode()));
		pageModel.desc("id");
		List<UserIncomeWithdrawalEntity> list = userIncomeWithdrawalContract.load(pageModel);
		if (Tools.isNotNull(list)) {
			return ActionResult.success(list.stream().map(entity -> {
				Map<String, Object> data = new HashMap<>();
				data.put("title", (entity.getApply_money() + entity.getBonus()) / 100 + "金币");
				data.put("detail", "收益：" + entity.getApply_money() / 100 + "金币,奖励金：" + entity.getBonus() / 100 + "金币");
				data.put("description", "扣除" + JsonHelper.toObject(entity.getVacuate(), VacuateConfigDto.class).getTaxRatio() + "%税,实得"
						+ (entity.getApply_money() + entity.getBonus() - entity.getTax() - entity.getBonux_tax()) / 100 + "金币");
				data.put("date", Tools.getDateTime(entity.getCreate_time()));
				data.put("status", entity.getStatus());
				data.put("via", 1 == entity.getVia() ? "现金余额" : "支付宝");
				return data;
			}).collect(Collectors.toList()));
		}
		return ActionResult.success();
	}

	@Override
	public ActionResult incomeCashLog(Integer stamp) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		
		int pageSize = 200;
		
		PageModel pageModel = PageModel.getLimitModel(0, pageSize+1);
		pageModel.addQuery(Restrictions.eq("io", 1));
		pageModel.addQuery(Restrictions
				.in("type", UserIncomeAccountLogTypeEnum.chat_diamond.getLogType(), UserIncomeAccountLogTypeEnum.general_award.getLogType(),UserIncomeAccountLogTypeEnum.sign_award.getLogType(),
						UserIncomeAccountLogTypeEnum.gift_diamond.getLogType(), UserIncomeAccountLogTypeEnum.roulette_wheel.getLogType(),UserIncomeAccountLogTypeEnum.recharge_back_invitation_income.getLogType(),
						UserIncomeAccountLogTypeEnum.recharge_back_income.getLogType(),UserIncomeAccountLogTypeEnum.text_diamond.getLogType(),UserIncomeAccountLogTypeEnum.audio_diamond.getLogType(),
						UserIncomeAccountLogTypeEnum.video_diamond.getLogType(),UserIncomeAccountLogTypeEnum.transfer_income.getLogType(),UserIncomeAccountLogTypeEnum.anchor_trans_pay_user.getLogType(),
						UserIncomeAccountLogTypeEnum.turntable_diamond.getLogType(),UserIncomeAccountLogTypeEnum.anchor_trans_call_user.getLogType(),UserIncomeAccountLogTypeEnum.recharge_flower_back_income.getLogType(),
						UserIncomeAccountLogTypeEnum.text_chat_flower.getLogType(),UserIncomeAccountLogTypeEnum.recharge_for_deduction.getLogType(),UserIncomeAccountLogTypeEnum.free_income_video_lt_30s.getLogType(),
						UserIncomeAccountLogTypeEnum.free_income_video_ge_30s.getLogType(),UserIncomeAccountLogTypeEnum.point_transform_income.getLogType(),UserIncomeAccountLogTypeEnum.free_income_video_ge_30s_action2.getLogType(),
						UserIncomeAccountLogTypeEnum.anchor_settle_back_inviter_income.getLogType(),UserIncomeAccountLogTypeEnum.buy_guard_give_anchor_income.getLogType(),UserIncomeAccountLogTypeEnum.user_inivite_cps_back_Income.getLogType(),
						UserIncomeAccountLogTypeEnum.income_increase.getLogType(),UserIncomeAccountLogTypeEnum.recharge_vip_back_invitation_income.getLogType()
						));
		pageModel.addQuery(Restrictions.or(Restrictions.eq("service_id", userId), Restrictions.eq("inviter_id", userId)));
		if(Tools.isNotNull(stamp) && stamp>0){
			pageModel.addQuery(Restrictions.lt("id", stamp));
		}
		pageModel.desc("id");
		List<UserIncomeAccountLogEntity> list = userIncomeAccountLogContract.load(pageModel);
		
		List<Map<String, Object>> datas = new ArrayList<>();
		int rows = 0;
		boolean next = false;
		long stampInt = 0; 
		if (Tools.isNotNull(list)) {
	
		   boolean transFlag = false;
			AnchorOnlineEntity anchor =  anchorOnlineContract.getAnchorOnlineEntity(userId);
			if(Tools.isNotNull(anchor)){
				if(anchor.getShow_income()==0){
					transFlag = true;
				}
			}
			for (UserIncomeAccountLogEntity entity : list.stream().sorted(Comparator.comparingLong(o -> -o.getCreate_time().getTime())).collect(Collectors.toList())){
				Map<String, Object> data = new HashMap<>();
				long serviceAmount = entity.getService_amount();
				if(transFlag){
					serviceAmount = entity.getAmount();
				}
				if (userId == entity.getService_id()) {
					switch (UserIncomeAccountLogTypeEnum.getByCode(entity.getType())) {
						case chat_diamond:
						case video_diamond:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "视频聊天");
							break;
						case audio_diamond:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "音频聊天");
							break;
						case text_diamond:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "图文聊天");
							break;
						case gift_diamond:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", entity.getMemo());
							break;
						case general_award:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "任务奖励");
							break;
						case roulette_wheel:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "大转盘获奖");
							break;
						case recharge_back_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "充值返现");
							break;
						case recharge_back_invitation_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "充值返邀请者收益");
							break;
						case sign_award:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "签到奖励");
							break;
						case transfer_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "子账户收益转入");
							break;
						case anchor_trans_pay_user:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "转化提成转入");
							break;
						case turntable_diamond:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "转盘收益");
							break;
						case anchor_trans_call_user:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "通话奖励");
							break;
						case recharge_flower_back_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "小红花充值返现");
							break;
						case text_chat_flower:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "聊天收益");
							break;
						case recharge_for_deduction:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "充值抵扣金");
							break;
						case free_income_video_lt_30s:
						case free_income_video_ge_30s:
						case free_income_video_ge_30s_action2:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "通话激励");
							break;
						case point_transform_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "积分换收益");
							break;
						case anchor_settle_back_inviter_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "邀请大V分成");
							break;
						case buy_guard_give_anchor_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "守护分成");
							break;
						case user_inivite_cps_back_Income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "邀请分成");
							break;
						case income_increase:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "收益人工增加");
							break;
						case recharge_vip_back_invitation_income:
							data.put("title", Tools.formatDouble2PercentToString(serviceAmount) + "金币");
							data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
							data.put("date", Tools.getDateTime(entity.getCreate_time()));
							data.put("via", "购买VIP返邀请者收益");
							break;
						default:
							break;
					}
				} else {
					if(entity.getInviter_amount() > 0){
						data.put("title", Tools.formatDouble2PercentToString(entity.getInviter_amount()) + "金币");
						data.put("description", "共" + Tools.formatDouble2PercentToString(entity.getAmount()) + "金币");
						data.put("date", Tools.getDateTime(entity.getCreate_time()));
						data.put("via", "邀请分成");
					}else{
						continue;
					}
				}
				rows++;
				if(rows>pageSize){
					next = true;
					break;
				}
				//清空信息
				data.put("description","");
				stampInt = entity.getId();
				datas.add(data);
			}
		}
		return ActionResult.success(datas,stampInt,next);
	}

	//###################################################################################################################################################
	@Override
	public ActionResult getEnergyPriceList() throws Exception {
		BeatContext bc = RequestUtils.getCurrent();
		long userId = bc.getUserid();
		Map<String, Object> map = new HashMap<>();
		map.put("balance", userEnergyAgent.getEnergyBalance(userId));
		map.put("income", Tools.formatDouble2Percent(userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL)));
		map.put("ratio", Const.RECHARGE_DIAMONDS_INCOME_RATIO);
		int os = bc.getHeader().getOs_type();
		List<EnergyCustomPriceEntity> list = getPricesByMobile(bc.getHeader().getMobile_model(), userId, os);
		if (Tools.isNotNull(list)) {
			List<Map<String, Object>> prices = new ArrayList<>();
			for (EnergyCustomPriceEntity price : list) {
				Map<String, Object> item = new HashMap<>();
				item.put("priceId", price.getId());
				item.put("identifier", price.getPrice_identifier());
				item.put("title", price.getTitle());
				item.put("description", Tools.isNull(price.getDescription()) ? null : price.getDescription());
				item.put("money", price.getMoney() / 100.0);
				item.put("energy", price.getEnergy());
				prices.add(item);
			}
			map.put("prices", prices);
		}
		if(PlatformEnum.ios.type == os){
			PageModel pageModel = PageModel.getLimitModel(0, 1).addQuery(Restrictions.eq("platform", os)).addQuery(Restrictions.eq("code", bc.getHeader().getVersioncode()));
			List<AppVersionEntity> v = appVersionContract.load(pageModel);
			if (Tools.isNotNull(v)) {
				map.put("shield", v.get(0).getBlock_apple_pay());
			} else {
				map.put("shield", 1);
			}
		}
		return ActionResult.success(map);
	}

	/**
	 * 根据手机型号获取价格列表
	 * @param model
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private List<EnergyCustomPriceEntity> getPricesByMobile(String model,long userId,int platform) throws Exception {
		List<EnergyCustomPriceEntity> list = new ArrayList<>();
		EnergyCustomCategoryEntity category = null;
		if(PlatformEnum.ios.type == platform){
			category = energyCustomCategoryContract.findByProperty("name", "IOS");
			if(Tools.isNotNull(category)){
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("category_id", category.getId()));
				pageModel.addQuery(Restrictions.eq("status",1));
				pageModel.asc("priority");
				List<EnergyCustomPriceEntity> prices = energyCustomPriceContract.load(pageModel);
				if(Tools.isNotNull(prices)){
					list.addAll(prices);
				}
			}
			return list;
		}
		EnergyCustomMobileEntity mobile = energyCustomMobileContract.findByProperty("name", Tools.formatString(model));
		if(Tools.isNull(mobile)){
			category = energyCustomCategoryContract.findByProperty("name", "其他");
			if(Tools.isNull(category)){
				return list;
			}
			try {
				EnergyCustomMobileEntity entity = new EnergyCustomMobileEntity();
				entity.setCreate_time(new Date());
				entity.setUpdate_time(new Date());
				entity.setCategory_id(category.getId());
				entity.setName(Tools.formatString(model));
				entity.setStatus(1);
				energyCustomMobileContract.insert(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			category = energyCustomCategoryContract.findById(mobile.getCategory_id());
		}
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("category_id", category.getId()));
		pageModel.addQuery(Restrictions.eq("status",1));
		pageModel.asc("priority");
		List<EnergyCustomPriceEntity> prices = energyCustomPriceContract.load(pageModel);
		if(Tools.isNotNull(prices)){
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.eq("user_id", userId));
			pageModel.addQuery(Restrictions.eq("status",1));
			if(category.getFirst() == 1 && energyOrderContract.count(pageModel) == 0 ){
				prices.set(0, ServiceHelper.FirstRechargePrice.getEnergyPrice());
			}
			list.addAll(prices);
		}
		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult rechargeEnergy(long priceId, int channelCode, double money, double income, String receipt) throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		RequestHeader header = context.getHeader();
		UserDetails user = context.getUser();
		Long userId = user.getUserid();
		String nickname = Tools.isNull(user.getNickname()) ? "" : user.getNickname();
		String mobile = Tools.isNull(user.getMobile()) ? "" : user.getMobile();
		Date date = new Date();
		int mon = new BigDecimal(String.valueOf(money)).multiply(new BigDecimal("100")).intValue(), inc = new BigDecimal(String.valueOf(income))
				.multiply(new BigDecimal("100")).intValue();
		if (0 < inc && inc > userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL)) {
			return ActionResult.fail(ErrorCodeEnum.income_pay_not_enough);
		}
		// 一金币首冲价格id 取Long 最大值
		EnergyCustomPriceEntity price =
				Long.MAX_VALUE == priceId ? ServiceHelper.FirstRechargePrice.getEnergyPrice() : energyCustomPriceContract.findById(priceId);
		if (Tools.isNotNull(price)) {
			if (mon + inc != price.getMoney()) {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
		} else {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		if (PayChannelEnum.iap.getCode() == channelCode && Tools.isNull(receipt)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		// 同一订单可能多次验证
		Long orderId = payAgent.ensureOrderViaReceipt(MD5.encode(receipt));
		if (null == orderId || null == energyOrderContract.findById(orderId)) {
			EnergyOrderEntity order = new EnergyOrderEntity();
			order.setCreate_time(date);
			order.setUpdate_time(date);
			order.setUser_id(userId);
			order.setMobile(mobile);
			order.setNickname(nickname);
			order.setPrice_id(price.getId());
			order.setPrice(price.getMoney());
			order.setEnergy(price.getEnergy());
			order.setDonor(price.getDonor());
			order.setMoney(mon);
			order.setIncome(inc);
			order.setStatus(0);
			energyOrderContract.insert(order);
			orderId = order.getOrder_id();
		}
		// 收益冲钻
		if (0 == money) {
			IncomeResultDto<Long> result = userIncomeAgent
					.changeIncomeAccount(userId, inc, 0, UserIncomeAccountLogTypeEnum.buy_diamond, String.valueOf(orderId),
							UserIncomeAccountLogTypeEnum.buy_diamond.getDesc());
			if (0 == result.getCode()) {
				// 冲钻
				userEnergyAgent
						.changeEnergyAccount(userId, price.getEnergy() + price.getDonor(), (long) mon, UserEnergyAccountLogTypeEnum.recharge_account.getCode(), 1,
								channelCode, String.valueOf(orderId), UserEnergyAccountLogTypeEnum.recharge_account.getDesc());
				EnergyOrderEntity o = new EnergyOrderEntity();
				o.setOrder_id(orderId);
				o.setUpdate_time(date);
				o.setStatus(1);
				energyOrderContract.update(o);
				Map<String, Object> data = new HashMap<>();
				data.put("channel", channelCode);
				data.put("signData", result.getData());
				return ActionResult.success(data);
			} else {
				return ActionResult.fail(result.getCode(), result.getMsg());
			}
		}
		PayAccessDto access = new PayAccessDto();
		access.setUser_id(user.getUserid());
		access.setNickname(user.getNickname());
		access.setMobile(user.getMobile());
		access.setOrder_id(orderId);
		String fm = Tools.formatDouble2PercentToString(mon);
		access.setSubject("[能量充值：" + fm + "元]");
		access.setDescription("用户账户能量充值花费：" + fm + "元");
		access.setMoney((long) mon);
		access.setPay_channel(PayChannelEnum.getByCode(channelCode));
		access.setType(PayTypeEnum.recharge_energy);
		access.setApp_type(header.getOs_type());
		access.setApp_channel(header.getChannel());
		access.setApp_version(header.getVersion());
		access.setPackage_name(header.getPackageName());
		// iap 字段
		access.setReceipt(receipt);
		access.setProduct_id(price.getPrice_identifier());
		return ServiceHelper.dealPayData(payAgent.preparePay(access), PayChannelEnum.getByCode(channelCode));
	}

	
	public RechargePriceEntity getRechargeCustomPrice(Long priceId) throws Exception{
		return rechargePriceContract.findById(priceId);
	}
	
	@Override
	public ActionResult rechargeH5(long priceId, int channelCode, double money, double income, String receipt) throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		RequestHeader header = context.getHeader();
		UserDetails user = context.getUser();
		Long userId = user.getUserid();
		String nickname = Tools.isNull(user.getNickname()) ? "" : user.getNickname();
		String mobile = Tools.isNull(user.getMobile()) ? "" : user.getMobile();
		Date date = new Date();
		//int mon = new BigDecimal(String.valueOf(money)).multiply(new BigDecimal("100")).intValue(), inc = new BigDecimal(String.valueOf(income)).multiply(new BigDecimal("100")).intValue();

//		if(0 < inc && inc > userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL)){
//			return ActionResult.fail(ErrorCodeEnum.income_pay_not_enough);
//		}
		// 一金币首冲价格id 取Long 最大值
		RechargePriceEntity price = rechargePriceContract.findById(priceId);
		if (Tools.isNotNull(price)) {
//			if (mon + inc != price.getMoney()) {
//				return ActionResult.fail(ErrorCodeEnum.parameter_error);
//			}
		} else {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		RechargeOrderEntity order = new RechargeOrderEntity();
		order.setCreate_time(date);
		order.setUpdate_time(date);
		order.setUser_id(userId);
		order.setMobile(mobile);
		order.setNickname(nickname);
		order.setPrice_id(price.getId());
		order.setPrice(price.getMoney());
		order.setDiamond(price.getDiamond());
		order.setDonor(price.getDonor());
		order.setMoney(price.getMoney());
		order.setIncome(0);
		order.setStatus(0);
		order.setPackage_name(header.getPackageName());
		rechargeOrderContract.insert(order);

		PayAccessDto access = new PayAccessDto();
		access.setUser_id(user.getUserid());
		access.setNickname(user.getNickname());
		access.setMobile(user.getMobile());
		access.setOrder_id(order.getId());
		String fm = Tools.formatDouble2PercentToString(price.getMoney());
		access.setSubject("[钻石充值：" + fm + "元]");
		access.setDescription("用户账户钻石充值花费：" + fm + "元");
		access.setMoney(price.getMoney().longValue());
		access.setPay_channel(PayChannelEnum.getByCode(channelCode));
		access.setType(PayTypeEnum.recharge_diamond);
		access.setApp_type(header.getOs_type());
		access.setApp_channel(header.getChannel());
		access.setApp_version(header.getVersion());
		access.setPackage_name(header.getPackageName());
		
		if(PayChannelEnum.wxH5.getCode()== channelCode) {
			AgentResult result = payAgent.preparePay(access);
			Map<String, String> data = new HashMap<String, String>();;
			//检测调用微信H5支付信息是否正确
			if (result.getCode() == 0) {
				//拿到对应的回调地址来请求对应的微信信息
				Map<String, String> map = (Map<String, String>) result.getData();
				if(Tools.isNotNull(map)) {
					String url = map.get("mweb_url");
					String orderId = map.get("orderId");
					if(Tools.isNotNull(url)) {
						Map<String , String> headerMap1 = new HashMap<>();
				        headerMap1.put("Host", "wx.tenpay.com");
				        headerMap1.put("Connection", "Keep-Alive");
				        headerMap1.put("Referer", "http://vapi.yoyo.liaomeivideo.com/");
				        headerMap1.put("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36");
				        
				        ResponseStatus response = HttpUtils.get(url, ECharset.UTF_8, EContentType.APPLICATION_JSON, headerMap1, null);
				        if(Tools.isNotNull(response)){
				            //logger.error(response.getContent());
				            String content = response.getContent();
				            if(Tools.isNotNull(content)) {
				            	String rgex = "weixin://wap/pay.{0,}";
				            	Pattern pattern = Pattern.compile(rgex);
				            	Matcher m = pattern.matcher(content);
				            	while(m.find()){  
				                    String web = m.group();
				                    web = web.replace(";", "");
				                    web = web.replace("\"", "");
				                    //System.err.println(web);
				                    data.put("url", web);
				                    data.put("orderId", orderId);
				                    break;
				                }  
				            }
				        }
					}
				}
			}
			if(Tools.isNotNull(data) && data.size() > 0) {
				return ActionResult.success(data);
			} else {
				return ActionResult.fail(result.getCode(), result.getCodemsg());
			}
		} else if(PayChannelEnum.aliH5.getCode()== channelCode){
			AgentResult result = payAgent.preparePay(access);
			return  ActionResult.success(result.getData());
		} else {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
	}
	
	private boolean checkFirstRecharge(long userid) throws Exception{
		UserBO userBo = userAgent.findById(userid);
		boolean currentFlag = false;
		if(Tools.isNotNull(userBo)){
			currentFlag = Tools.getDayTime(userBo.getCreateTime())== Tools.getDayTime(new Date())? true:false;
		}	
		return currentFlag && userFirstRechargeLogContract.checkFirstRecharge(userid, FirstPayTypeEnum.diamond.getCode());
	}
	
	
	/**
	 * 检查充值额度是否超过指定值
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkChargeLimit(long userid) throws Exception{
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		/*
		pageModel.addQuery(Restrictions.eq("status",1));
		pageModel.addQuery(Restrictions.eq("user_id",userid));
		pageModel.asc("order_id");
		List<RechargeOrderEntity> orderList = rechargeOrderContract.load(pageModel);
		if(Tools.isNotNull(orderList)){
			if(orderList.get(0).getMoney()>=10000L){
				return true;
			}
		}
		*/
		
		pageModel.addQuery(Restrictions.eq("status",1));
		pageModel.addQuery(Restrictions.eq("user_id",userid));
		pageModel.addQuery(Restrictions.ge("price",10000));
		List<RechargeOrderEntity> orderList = rechargeOrderContract.load(pageModel);
		if(Tools.isNotNull(orderList)){
				return true;
		}
		List<RedFlowerOrderEntity> flowerList =redFlowerOrderContract.load(pageModel);
		if(Tools.isNotNull(flowerList)){
				return true;
		}
		
		pageModel.clearAll();
		pageModel.addLimitField(0, 50);
		pageModel.addProjection(Projections.sum("money").as("totalMoney"));
		pageModel.addQuery(Restrictions.eq("status",1));
		pageModel.addQuery(Restrictions.eq("user_id",userid));
		long limit = 50000L;
		boolean chargeLimitFalg = false;
		List<Map<String, Object>> maps = userPayActionContract.loadGroupBy(pageModel);
		if (Tools.isNotNull(maps)) {
			for(Map<String, Object> map:maps){
				if (Tools.isNotNull(map)) {
					long totalMoney = Tools.parseLong(map.get("totalMoney"));
					if(totalMoney>=limit){
						chargeLimitFalg = true;
					}
				}
			}
		}
		
		
		return chargeLimitFalg;
	}
	
	
	/**
	 * 检查充值额度是否超过指定值
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean checkChargeLimit200(long userid) throws Exception{
		boolean chargeLimitFalg = false;
		try{
			PageModel pageModel = PageModel.getLimitModel(0, 1);
			pageModel.addQuery(Restrictions.eq("status",1));
			pageModel.addQuery(Restrictions.eq("user_id",userid));
			pageModel.asc("create_time");
			List<UserPayActionEntity> orderList = userPayActionContract.load(pageModel);
			if(Tools.isNotNull(orderList)){
				Long initialPrice = orderList.get(0).getInitial_price();
				if(Tools.isNotNull(initialPrice)){
					if(initialPrice>=20000){
						chargeLimitFalg = true;
					}
				}
				
			}
		}catch(Exception e){
			logger.info("查询出错 userId:"+userid,e);
		}
		return chargeLimitFalg;
	}
	
	public boolean checkUserAPPLimit(long userid) throws Exception{
		boolean chargeLimitFalg = false;
		/*
		try{
			UserBO userBO = userAgent.findById(userid);
			if(Tools.isNotNull(userBO)){
				String label = userBO.getLabels();
				if(Tools.isNotNull(label)){
					if(label.contains("Q")){
						return true;
					}
				}
			}
			List<String> appList = esMobileUserAppRecordService.queryLastLoginAppList(userid);
			if(Tools.isNotNull(appList)){
				if(appList.contains("cn.xuexi.android")){
					userAgent.updateUserLabels(userid, "Q");
					chargeLimitFalg = true;
				}
			}
		}catch(Exception e){
			logger.info("查询出错 userId:"+userid,e);
		}
		*/
		return chargeLimitFalg;
	}
	
	public boolean checkUserAccountLimit(long userid) throws Exception{
		boolean chargeLimitFalg = false;
		try{
			if(Const.USER_LIMIT_STAET_RECHARGE_MONEY_ACCOUNT_MAP.containsKey(userid)){
				return true;
			}
			String clientId = RequestUtils.getCurrent().getHeader().getClientId();
			if(Const.USER_LIMIT_STAET_RECHARGE_MONEY_CLIENT_MAP.containsKey(clientId)){
				return true;
			}
			HttpServletRequest request = RequestUtils.getCurrent().getRequest();
			if(Const.USER_LIMIT_STAET_RECHARGE_MONEY_IP_MAP.containsKey(Tools.getIP(request))){
				return true;
			}
		}catch(Exception e){
			logger.info("查询出错 userId:"+userid,e);
		}
		return chargeLimitFalg;
	}
	
}
