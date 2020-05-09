package com.tigerjoys.shark.miai.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.enums.RouletteWheel;
import com.tigerjoys.shark.miai.service.IRouletteService;

/**
 * 幸运抽奖页面控制类
 * 
 * @author lipeng
 *
 */
@Controller
//@TestEncrypt("5KdAVyLCYWMpEV/67SjhMbv69yCD3xE0Jep4y5zNC+mqFGREZlehEWlDMO2HnM1Lp12sEbst2smBpZhfnGtVniTJTSw3IeZ1cylR6VCFM+GQlMcaJQwnfvu2OVQJe4Uvne3CELMYTSc5XPFjD2CD6Bb74CZJMqm4DjTp/3+39T6t0/KIKCRlHjtRGlyNFScRYgHWoFWra/dsKcD0zmHwSbMZPmrbMtPRJ3PpsdBIAi0cnN3miW0nSb/TTuEx4EyZqO+lxjoULEUVENA1OlHbbEIjpspxjsNcJUSNOX6VmFPRURMFHd+5ESX3vVq64RTh7wSjsUp7kPtiD/HUj0F+gM6CBsu3K2WMOe2DhY5D4S9FA/ivugr4RNwgFRe9JtOS5TDqoXe0K8HaRyeLzAd4BK9o/GK626RV1R2lZeV2yZYxX+N8XTQIzic3WonAKxxDuZtZFlcCs3x7Qq6aJtc2eQ==")
@RequestMapping(value = "/api")
public class LuckyDrawController extends BaseController {

	private final List<String> items = Stream.of(RouletteWheel.Low.values()).map(one -> one.getDesc()).collect(Collectors.toList());

	@Autowired
	private IRouletteService rouletteService;

	/**
	 * 每个web请求都会自带encrypt到页面上
	 * @return String
	 */
	@ModelAttribute("encrypt")
	public String addEncrypt() {
		return RequestUtils.getCurrent().getHeaderEncrypt();
	}
	
	/**
	 * 轮盘页面
	 * @return - String
	 * @throws Exception
	 */
	@UserClientService("roulette")
	@NoSign
	@RequestMapping(value = "/roulette/index", produces = Produce.TEXT_HTML)
	public String rouletteHome(Model model) throws Exception {
		model.addAttribute("wheels", JsonHelper.toJson(items));
		return "activity/roulette";
	}

	/**
	 * 提交轮盘结果
	 *
	 * @throws Exception
	 */
	@UserClientService("roulette")
	@Login
	@NoSign
	@RequestMapping(value = "/roulette/commit", method = RequestMethod.POST)
	@ResponseBody
	public ActionResult commitRoulette() throws Exception {
		try {
			return rouletteService.roulette();
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.fail();
		}
	}

	/**
	 * 轮盘总奖励
	 *
	 * @throws Exception
	 */
	@UserClientService("roulette")
	@Login
	@NoSign
	@RequestMapping(value = "/roulette/total", method = RequestMethod.POST)
	@ResponseBody
	public ActionResult getRouletteIncome() throws Exception {
		try {
			return rouletteService.getTotalIncome();
		} catch (Exception e) {
			e.printStackTrace();
			return ActionResult.fail();
		}
	}
	
}
