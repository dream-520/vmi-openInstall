package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.inter.contract.ISlotMachineLogContract;
import com.tigerjoys.shark.miai.service.IWebLotteryService;

@Controller
//@TestEncrypt("pUOj7GGbnHNF3q72/4D9sUl6azlJzO/JJO32FZQzJZUO2rSJKcoaTCM9JlW134P+1p/+R4gnDTtLFJyQKCZxNiTnnwpPrnukNMvH2pKrrfJsf3ozkVuBPVJVFiJX4G7CU4Vfi3MyRvs6ukSc4ZSn9LC02GFwxKenqq96A/mxcCMWr3i9QyRqZ5tA+tGBZa/YsfCY8+adEfvc3Rh9fp5RsighdE0760yXT+L2P75izTFpIVhw14s9/fhT7U1yuMGgSXihFuyPv7DqBaPt81MvF0dtfBVLsTEUhSRDPycMtkE7Z1GVllVw9Y59qFppGc3MCUHG5jHPMFQCGJ0XPVwUKJIK9801m5EAbJgPBbYPdU/ZsyM/A5j5LWm/krO5scI070Cdp6lrLWAffeCoCubeqgYZkpcvx8kiCcCVJJ0gxUxo2nGpyHg4r+LImrocx+IBVfKZL82+06/FFApANjoevmWLrOAqQHiyIVj33AD2qsjeIbnUoFAftwnmyjHEmDnO")
@RequestMapping(value = "/web/lottery")
public class WebLotteryController {

	//private final List<String> items = Stream.of(RouletteWheel.NewV.values()).map(one -> one.getDesc()).collect(Collectors.toList());
	
	@Autowired
	private IWebLotteryService webLotteryService;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	@Autowired
	private IUserDiamondAgent userDiamondAgent;

	@Autowired
	private ISlotMachineLogContract slotMachineLogContract;
	
	/*
	@ModelAttribute("encrypt")
	public String addEncrypt() {
		return RequestUtils.getCurrent().getHeaderEncrypt();
	}
	*/
	
	@NoSign
	@RequestMapping(value = "/roulette/index", produces = Produce.TEXT_HTML)
	public String rouletteHome(Model model) throws Exception {
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		//model.addAttribute("wheels", JsonHelper.toJson(items));
		//return "activity/lottery/roulette";
		//设置40条随机的滚动条数据
		model.addAttribute("prizes", webLotteryService.randPrizes());
		return "activity/lottery/LuckyTurntable";
	}
	
	/**
	 * 提交轮盘结果
	 */
	@Login
	@NoSign
	@RequestMapping(value = "/roulette/commit", method = RequestMethod.POST)
	@ResponseBody
	public ActionResult commitRoulette() throws Exception {
		try {
			return webLotteryService.roulette();
		} catch (Exception e) {
			return ActionResult.fail();
		}
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++老虎机幸运抽奖活动++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 轮盘页面
	 * @return - String
	 * @throws Exception
	 */
	@NoSign
	@Login
	@RequestMapping(value = "/slot/index", produces = Produce.TEXT_HTML)
	public String slotHome(Model model) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		model.addAttribute("times", Tools.isNull(cacheRedis.hget("slot_free_daily", String.valueOf(userId))) ? 1 : 0);
		model.addAttribute("diamonds", userDiamondAgent.getDiamondBalance(userId));
		model.addAttribute("price", Const.SLOT_DIAMONDS_EACH_TIME);
		model.addAttribute("lucky", slotMachineLogContract.load(PageModel.getLimitModel(0,100).addQuery(Restrictions.gt("award", 0)).desc("create_time")));
		return "activity/lottery/slotmachine";
	}

	/**
	 * 提交轮盘结果
	 *
	 * @throws Exception
	 */
	@Login
	@NoSign
	@RequestMapping(value = "/slot/commit", method = RequestMethod.POST)
	@ResponseBody
	public ActionResult commitSlot() throws Exception {
		try {
			return webLotteryService.slot();
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.fail();
		}
	}
	
}
