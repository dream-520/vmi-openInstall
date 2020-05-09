package com.tigerjoys.shark.miai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.IHomeBService;


/**
 * YOYO首页B包
 * @author yangjunming
 *
 */

@Controller
@RequestMapping(value="/api/bpackage" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class HomeBController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(HomeBController.class);
	
	@Autowired
	private IHomeBService homeBService;


	@Login
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public @ResponseBody ActionResult index() throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		int platform = RequestUtils.getCurrent().getHeader().getOs_type();
		return homeBService.index(userId,platform);
	}

}
