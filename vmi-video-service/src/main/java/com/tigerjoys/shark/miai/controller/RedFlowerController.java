package com.tigerjoys.shark.miai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.shark.miai.service.IRedFlowerService;

/** 
  * @author mouzhanpeng at [2017年12月20日 下午3:39:49] 
  * @since JDK 1.8.0 
  */
@Controller
@RequestMapping(value = "/api/red/flower", produces = Produce.TEXT_ENCODE)
public class RedFlowerController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRedFlowerService redFlowerService;
	
	/**
	 * 小红花首页
	 * @return
	 */
	@RequestMapping("home")
	@ResponseBody
	@Login
	public ActionResult redFlowerList(){
		try {
			return redFlowerService.redFlowerHome();
		} catch (Exception e) {
			LOGGER.warn("小红花首页数据异常", e);
			return ActionResult.fail();
		}
	}
	
	/**
	 * 购买小红花
	 * @return
	 */
	@RequestMapping("buy")
	@ResponseBody
	@Login
	public ActionResult buyRedFlower(@RequestBody String body){
		try {
			long flowerId = JsonHelper.toJsonObject(body).getLongValue("flowerId");
			return redFlowerService.buyRedFlower(flowerId);
		} catch (Exception e) {
			LOGGER.warn("购买小红花数据异常", e);
			return ActionResult.fail();
		}
	}
	
	/**
	 * 消费小红花
	 * @return
	 */
	@RequestMapping("consume")
	@ResponseBody
	@Login
	public ActionResult consumeRedFlower(@RequestBody String body){
		try {
			long otherId = JsonHelper.toJsonObject(body).getLongValue("otherId");
			return redFlowerService.consumeRedFlower(otherId);
		} catch (Exception e) {
			LOGGER.warn("花费小红花数据异常", e);
			return ActionResult.fail();
		}
	}
}
