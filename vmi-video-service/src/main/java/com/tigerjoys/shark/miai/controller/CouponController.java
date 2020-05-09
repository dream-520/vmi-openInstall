package com.tigerjoys.shark.miai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.service.ICouponService;

/**
 * 代金券
 * 
 * @author yangjunming
 *
 */
@Login
@Controller
@RequestMapping(value = "/api/paid/coupon", method = RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class CouponController extends BaseController {


	@Autowired
	private ICouponService couponService;

	@RequestMapping(value = "/allCoupon", method = RequestMethod.POST)
	@Login
	public @ResponseBody ActionResult queryAllCoupon() throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		Long userId = context.getUserid();

		return couponService.queryAllCoupon(userId);

	}

}
