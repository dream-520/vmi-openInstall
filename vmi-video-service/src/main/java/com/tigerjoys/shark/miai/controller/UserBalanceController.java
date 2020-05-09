package com.tigerjoys.shark.miai.controller;

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
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserBalanceAccountAgent;

/**
 * 用户余额相关的接口
 * @author chengang
 *
 */
@Login
@Controller
@RequestMapping(value="/api/user/balance" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class UserBalanceController extends BaseController {
	
	@Autowired
	private IUserBalanceAccountAgent userBalanceAccountAgent;
	
	/**
	 * 查询用户余额
	 * @return ActionResult
	 * @throws Exception
	 */
	@UserClientService("user_balance")
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public @ResponseBody ActionResult appointList() throws Exception {
		return ActionResult.success(Tools.formatDouble2Percent(userBalanceAccountAgent.getBalanceByUserId(RequestUtils.getCurrent().getUserid())));
	}

}
