package com.tigerjoys.shark.miai.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.dto.service.AppointSiteDto;
import com.tigerjoys.shark.miai.service.IAppointSiteService;

/**
 * 场地相关接口
 * 
 * @author yangjunming
 *
 */
@Controller
@RequestMapping(value = "/web/appointsite", produces = Produce.TEXT_HTML)
public class AppointSiteController {

	@Autowired
	private IAppointSiteService appointSiteService;

	/**
	 * 预定场地首页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/home", produces = Produce.TEXT_HTML)
	public String home(Model model) throws Exception {
		Map<String, Object> outHsmp = appointSiteService.siteIndex();
		model.addAllAttributes(outHsmp);
		return "appointsite/appointsite";

	}

	/**
	 * 预定场地首页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/site/{siteid}", produces = Produce.TEXT_HTML)
	public String siteById(@PathVariable long siteid, Model model) throws Exception {
		AppointSiteDto dto = appointSiteService.siteDesc(siteid);
		model.addAttribute("dto", dto);

		model.addAttribute("deliver", "{\"id\":" + siteid + ",\"money\":" + dto.getCost() + ",\"type\":"
				+ PayTypeEnum.pay_appoint_site.getCode() + "}");
		model.addAttribute("describe", PayTypeEnum.pay_appoint_site.getDesc());
		model.addAttribute("money", dto.getCost()+"元");

		return "appointsite/appointsiteDetail";

	}

	/**
	 * 预定场地首页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/treasureChest", produces = Produce.TEXT_HTML)
	public String treasureChest() throws Exception {
		return "appointsite/baibaoBox";

	}

}
