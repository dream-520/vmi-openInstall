package com.tigerjoys.shark.miai.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IHomePopAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.IHomePopService;
import com.tigerjoys.shark.miai.service.IRechargeDialExperienceService;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;

/**
 * 首页弹窗
 * @author liuman
 *
 */
@Login
@Controller
@RequestMapping(value="/api/homePop" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class HomePopController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomePopController.class);
	
	@Autowired
	private IHomePopService homePopService;
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IVChatTextYXService vChatTextYXService;
	
	@Autowired
	private IRechargeDialExperienceService rechargeDialExperienceService;
	
	@Autowired
	private IHomePopAgent homePopAgent;
	
	
	/**
	 * 获得弹窗信息
	 */
	@RequestMapping(value="/showPage",method=RequestMethod.POST)
	public @ResponseBody ActionResult showPage() {
		long userId = RequestUtils.getCurrent().getUserid();
		String clientId = RequestUtils.getCurrent().getHeader().getClientId();
		int versionCode = RequestUtils.getCurrent().getHeader().getVersioncode();
		
		//提审期间不显示弹出窗
		boolean check = channelCheckService.checkChannel();
		if(check) {
			return ActionResult.success();
		}
		String packageName = RequestUtils.getCurrent().getHeader().getPackageName(); 
		if("com.tjhj.miliao".equals(packageName) || "com.tjhj.dvzs".equals(packageName))
			return ActionResult.success();
		try {
			String url = homePopService.showVersionUpgradePage(clientId, userId, versionCode);
			if(Tools.isNotNull(url)){
				return ActionResult.success(url);
			}
			/*
			if(Tools.isNotNull(rechargeDialExperienceService.getPriceList())){
				return ActionResult.success(Const.WEB_SITE+"/api/buy/dialExperience");
			}
			*/
			
			if(vChatTextYXService.checkShowVIPFragment(userId)){
				List<HomePopLogEntity> logList = homePopAgent.getHomePopLogByToday(clientId, 19L);
				if(Tools.isNull(logList)){
					UserBO userBO = userAgent.findById(userId);
					if(Tools.isNotNull(userBO)){
						long currentTime = Long.valueOf(Tools.getFormatDate(new Date(), "yyyyMMdd"));
						long userReqTime = Long.valueOf(Tools.getFormatDate(userBO.getCreateTime(), "yyyyMMdd"));
						if(currentTime >userReqTime){
							homePopAgent.addHomePopLog(clientId,19L, userId);
							return ActionResult.success(Const.WEB_SITE+"/web/charge/tovipPop");
						}
					}
				}
			
			}
			
			
			return homePopService.showPage(clientId, userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ActionResult.fail();
		}
	}
	
	@RequestMapping(value="/showNewPage",method=RequestMethod.POST)
	public @ResponseBody ActionResult showNewPage() {
		long userId = RequestUtils.getCurrent().getUserid();
		try {
			//提审期间不显示弹出窗
			boolean check = channelCheckService.checkChannel();
			if(check) {
				return ActionResult.success();
			}
			String packageName = RequestUtils.getCurrent().getHeader().getPackageName(); 
			if("com.tjhj.miliao".equals(packageName) || "com.tjhj.dvzs".equals(packageName)){
				return ActionResult.success();
			}                                                                                                                                                                                                                                                                                                                                                                                  
			return homePopService.showNewPage(userId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return ActionResult.fail();
		}
	}

}
