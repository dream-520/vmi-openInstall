package com.tigerjoys.shark.miai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.annotations.Login;

@Login
@Controller
@RequestMapping(value="/web/creditscore",produces=Produce.TEXT_HTML)
public class WebCreditScoreController {
	
	/**
	 * 用户信用分记录页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/records",produces=Produce.TEXT_HTML)
	public String getGameList(Model model) throws Exception{
		return "task/miai-help";
	}
}
