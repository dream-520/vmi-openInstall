package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAppUserAllowanceAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AppUserAllowanceTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAppAllowanceMoneyUserContract;
import com.tigerjoys.shark.miai.inter.contract.IAppAllowanceUserContract;
import com.tigerjoys.shark.miai.inter.contract.IAppAllowanceUserShowContract;
import com.tigerjoys.shark.miai.inter.contract.IDeviceContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IRechargeCustomMobileContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceUserEntity;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceUserShowEntity;
import com.tigerjoys.shark.miai.inter.entity.DeviceEntity;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomMobileEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;

@Service
public class AppUserAllowanceAgentImpl implements IAppUserAllowanceAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAppAllowanceUserContract appAllowanceUserContract;
	
	@Autowired
	private IAppAllowanceMoneyUserContract appAllowanceMoneyUserContract;
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUserDiamondAccountLogContract userDiamondAccountLogContract;
	
	@Autowired
	private IDeviceContract  deviceContract;
	
	@Autowired
	private IRechargeCustomCategoryContract rechargeCustomCategoryContract;
	
	@Autowired
	private IRechargeCustomMobileContract rechargeCustomMobileContract;
	
	@Autowired
	private IAppAllowanceUserShowContract appAllowanceUserShowContract;
	
	@Autowired
	private IFreeVideoChatExperienceContract freeVideoChatExperienceContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Override
	public int sendAllowance(String clientId, String channel, int versioncode, long userid) {
		//初始化为不进行补助
		int type = AppUserAllowanceTypeEnum.no.getCode();
		try {
			List<Long> checks = null;
			if(!("sms_c_lx_1".equals(channel))) {
				if(versioncode >= 37) {
					SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_USER_ALLOWANCE);
					if(Tools.isNotNull(config)) {
						//获取对应的开关值 和 对应的数量值
						JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
						if(Tools.isNotNull(ctrl)) {
							//获取对应的机型策略
							long category = mobileCategory(clientId);
							String customCategory = ctrl.getString("customCategory");
							if(Tools.isNotNull(customCategory)) {
								checks = Tools.splitToLongList(customCategory);
							}
							if (Tools.isNotNull(checks) && checks.size() > 0) {
								if(checks.contains(category)) {
									//type = AppUserAllowanceTypeEnum.experienceCard.getCode();
									//同时是对应城市访问内的 
									UserBO bo = userAgent.findById(userid);
									if (Tools.isNotNull(bo)) {
										if(!(bo.getCityid() == 4146 || bo.getCityid() == 4149)) {
											type = AppUserAllowanceTypeEnum.experienceCard.getCode();
										}
										logger.error("当前手机的策略为:"+category +" 当前配置的策略为:"+customCategory +" 对应的补助类型为:"+type +" 用户id:"+userid +" 用户所在省份:"+bo.getCityid());
									}

								}
							}
							logger.error("当前手机的策略为:"+category +" 当前配置的策略为:"+customCategory +" 对应的补助类型为:"+type);
						}
					}
				}
			}
			
			//将不送钻石的人数进行入库处理   此处也将送体验卡的放置到里边了
			if(type ==  AppUserAllowanceTypeEnum.no.getCode()) {
				//生成发送日志   注意本库中记录的是不进行补助钻石处理的用户
				AppAllowanceUserEntity t = new AppAllowanceUserEntity();
				t.setUserid(userid);
				t.setAllowance_date(new Date());
				t.setCreate_time(new Date());
				appAllowanceUserContract.insert(t);
			}
		} catch (Exception e) {
			logger.error("检测发送补助出现了错误:"+e.toString());
		}
		return type;
	}
	
	
	/**
	 * 根据设备获取对应的手机机型对应的策略
	 * @param clientId
	 */
	public long mobileCategory(String clientId) throws Exception {
		//默认是其他类型
		long category = 4;
		DeviceEntity device = deviceContract.findByProperty("clientid", clientId);
		if (Tools.isNotNull(device)) {
			RechargeCustomMobileEntity mobile = rechargeCustomMobileContract.findByProperty("name", device.getMobile_model());
			if(Tools.isNotNull(mobile)) {
				RechargeCustomCategoryEntity customCategory = rechargeCustomCategoryContract.findById(mobile.getCategory_id());
				if(Tools.isNotNull(customCategory))
					category = customCategory.getId();
			}
		}
		return category;
	}
	
//	@Override
//	public int sendAllowance(String clientId, String channel, int versioncode, long userid) {
//		//初始化为不进行补助
//		int type = AppUserAllowanceTypeEnum.no.getCode();
//		try {
//			if(!("sms_c_lx_1".equals(channel))) {
//				if(Tools.isNotNull(channel) && channel.equals("Huawei_yoyo3") && versioncode >= 37) {
//					type = AppUserAllowanceTypeEnum.experienceCard.getCode();
//				} else {
//					SysConfigEntity diamond = sysConfigContract.findByProperty("name", Const.APP_USER_ALLOWANCE);
//					if(Tools.isNotNull(diamond)) {
//						//获取对应的开关值 和 对应的数量值
//						JSONObject ctrl = JsonHelper.toJsonObject(diamond.getValue());
//						if(Tools.isNotNull(ctrl)) {
//							int on = ctrl.getIntValue("on");
//							//检测是否开启开关     
//							if(on == 1) {
//								//获取配置的手机价格  如果配置的是0  那么就触发全部赠送钱的逻辑
//								int price = ctrl.getIntValue("price");
//								if(price > 0) {
//									//获取配置的每日额度
//									//int num = ctrl.getIntValue("num");
//									//获取今日已经发送的人数
//									//PageModel pageModel = PageModel.getPageModel();
//									//pageModel.addQuery(Restrictions.eq("allowance_date", Tools.getDate(Tools.getDayTime())));
//									//long count = appAllowanceUserContract.count(pageModel);
//									//检测是否已经超过了限制额度
//									//if(num > count) {
//										//再次检测一个机型信息  如果机型大于2000 才进行不送钻石处理
//										if(!ifMobileModelMoreThanTwoThousand(clientId, price)) {
//											//手机机型处于送钻石的逻辑
//											type = AppUserAllowanceTypeEnum.diamond.getCode();
//										} else {
//											//手机大于2000 需要检测是否送体验卡或者不送钻石处理
//											int card = ctrl.getIntValue("type");
//											if(card == 1 && versioncode >= 37)
//												type = AppUserAllowanceTypeEnum.experienceCard.getCode();
//										}
//									//}
//								} else if(price == 0){
//									//处理特殊的价格为0  即触发 不送钻 或者全送体验机会的处理
//									int card = ctrl.getIntValue("type");
//									if(card == 1 && versioncode >= 37)
//										type = AppUserAllowanceTypeEnum.experienceCard.getCode();
//								}
//							} else {
//								//如果开关关闭  那么就全部启动送钻石的逻辑    开关关闭  统一触发钻石逻辑
//								type = AppUserAllowanceTypeEnum.diamond.getCode();
//							}
//						}
//					}
//				}
//			}
//			
//			/*  暂时取消送钱的业务
//			//检测对应的用户是否获取了钻石资格  没有资格就获取对应的送收益资格
//			if(type == AppUserAllowanceTypeEnum.no.getCode()) {
//				SysConfigEntity money = sysConfigContract.findByProperty("name", Const.APP_USER_MONEY_ALLOWANCE);
//				if(Tools.isNotNull(money)) {
//					//获取对应的开关值 和 对应的数量值
//					JSONObject ctrl = JsonHelper.toJsonObject(money.getValue());
//					if(Tools.isNotNull(ctrl)) {
//						int on = ctrl.getIntValue("on");
//						//检测是否开启开关
//						if(on == 1) {
//							//获取配置的每日额度
//							int num = ctrl.getIntValue("num");
//							//获取今日已经发送的人数
//							PageModel pageModel = PageModel.getPageModel();
//							pageModel.addQuery(Restrictions.eq("allowance_date", Tools.getDate(Tools.getDayTime())));
//							long count = appAllowanceMoneyUserContract.count(pageModel);
//							if(num > count) {
//								type = AppUserAllowanceTypeEnum.money.getCode();
//								//生成发送日志   注意本库中记录的是进行赠送收益的用户
//								AppAllowanceMoneyUserEntity t = new AppAllowanceMoneyUserEntity();
//								t.setUserid(userid);
//								t.setAllowance_date(new Date());
//								t.setCreate_time(new Date());
//								appAllowanceMoneyUserContract.insert(t);
//							}
//						}
//					}
//				}
//			}
//			*/
//			
//			//将不送钻石的人数进行入库处理   此处也将送体验卡的放置到里边了
//			if(type ==  AppUserAllowanceTypeEnum.no.getCode() || type == AppUserAllowanceTypeEnum.experienceCard.getCode()) {
//				//生成发送日志   注意本库中记录的是不进行补助钻石处理的用户
//				AppAllowanceUserEntity t = new AppAllowanceUserEntity();
//				t.setUserid(userid);
//				t.setAllowance_date(new Date());
//				t.setCreate_time(new Date());
//				appAllowanceUserContract.insert(t);
//			}
//		} catch (Exception e) {
//			logger.error("检测发送补助钻石出现了错误:"+e.toString());
//		}
//		return type;
//	}

	@Override
	public int showAllowanceWindow(long userid) {
		int type = AppUserAllowanceTypeEnum.no.getCode();
		try {
			//首先分析是否触发了 送收益抵扣
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			long count = appAllowanceMoneyUserContract.count(pageModel);
			if (count > 0) {
				type = AppUserAllowanceTypeEnum.money.getCode();
			}
			if(type == AppUserAllowanceTypeEnum.no.getCode()) {
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("user_id", userid));
				pageModel.addQuery(Restrictions.eq("type", 31));
				long num = userDiamondAccountLogContract.count(pageModel);
				if(num > 0) {
					type = AppUserAllowanceTypeEnum.diamond.getCode();
				}
			}
		} catch (Exception e) {
			logger.error("检测是否弹出抵扣窗体错误:"+e.toString());
		}
		return type;
	}
	
	
	public boolean ifMobileModelMoreThanTwoThousand(String clientId, int price) throws Exception {
		boolean b = false;
		SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_USER_ALLOWANCE);
		if(Tools.isNotNull(clientId) && Tools.isNotNull(config)) {
			DeviceEntity device = deviceContract.findByProperty("clientid", clientId);
			if (Tools.isNotNull(device)) {
				RechargeCustomMobileEntity mobile = rechargeCustomMobileContract.findByProperty("name", device.getMobile_model());
				if(Tools.isNotNull(mobile)) {
					RechargeCustomCategoryEntity category = rechargeCustomCategoryContract.findById(mobile.getCategory_id());
					//这个地方进行赔死了  大于3000的手机  修改字段后 手机档位大于配置的手机价格 就不进行送钻石处理
					if (Tools.isNotNull(category) && price > 0 && category.getPrice() >= price) {
						b = true;
					}
				}
			}
		}
		return b;
	}

	@Override
	public int showAllowanceNewWindow(long userid) {
		int type = AppUserAllowanceTypeEnum.no.getCode();
		//首先检测对应的用户是否已经进行了弹窗处理
		try {
			AppAllowanceUserShowEntity show = appAllowanceUserShowContract.findByProperty("userid", userid);
			if(Tools.isNull(show)) {
				//没有进行过弹窗处理  检测需要显示的弹窗类型
				FreeVideoChatExperienceEntity free = freeVideoChatExperienceContract.findByProperty("userid", userid);
				if(Tools.isNotNull(free)) {
					type = AppUserAllowanceTypeEnum.experienceCard.getCode();
				} else {
					//检测是否进行赠送钻石处理
					PageModel pageModel = PageModel.getPageModel();
					pageModel.addQuery(Restrictions.eq("user_id", userid));
					pageModel.addQuery(Restrictions.eq("type", 31));
					long num = userDiamondAccountLogContract.count(pageModel);
					if(num > 0) {
						type = AppUserAllowanceTypeEnum.diamond.getCode();
					}
				}
			}
			//处理只显示一次弹窗处理
			if(type != AppUserAllowanceTypeEnum.no.getCode()) {
				AppAllowanceUserShowEntity t = new AppAllowanceUserShowEntity();
				t.setUserid(userid);
				t.setCreate_time(new Date());
				appAllowanceUserShowContract.insert(t );
			}
		} catch (Exception e) {
			logger.error("检测是否弹出抵扣窗体错误:"+e.toString());
		}
		return type;
	}

}
