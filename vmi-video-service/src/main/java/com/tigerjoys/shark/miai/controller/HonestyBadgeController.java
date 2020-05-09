package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserHonestyBadgeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IBadgePriceContract;
import com.tigerjoys.shark.miai.inter.entity.BadgePriceEntity;

@Controller
@RequestMapping(value = "/api/honesty/badge", produces = Produce.TEXT_ENCODE)
public class HonestyBadgeController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserHonestyBadgeAgent userHonestyBadgeAgent;

	@Autowired
	private IBadgePriceContract badgePriceContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;

	/**
	 * 徽章列表
	 * 
	 * @return ActionResult
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ActionResult getBadgeList() throws Exception {
		try {
			PageModel pm = PageModel.getPageModel();
			pm.addQuery(Restrictions.eq("status", 1));
			List<BadgePriceEntity> list = badgePriceContract.load(pm);
			List<Map<String, Object>> data = new ArrayList<>(3);
			if (Tools.isNotNull(list)) {
				for (BadgePriceEntity entity : list) {
					Map<String, Object> one = new HashMap<>();
					one.put("badgeId", entity.getId());
					one.put("name", entity.getName());
					one.put("image", Const.getCdn(entity.getBig_image()));
					one.put("diamond", entity.getDiamond());
					one.put("duration", entity.getDuration());
					data.add(one);
				}
			}
			Map<String,Object> result = new HashMap<>();
			result.put("balance", userDiamondAgent.getDiamondBalance(RequestUtils.getCurrent().getUserid()));
			result.put("badges", data);
			return ActionResult.success(result);
		} catch (Exception e) {
			logger.error("getting badge list error!",e);
			return ActionResult.fail();
		}
	}

	/**
	 * 用户购买徽章
	 * 
	 * @param body
	 *            - 参数体
	 * @return ActionResult
	 * @throws Exception
	 */
	@Login
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	@ResponseBody
	public ActionResult buyHonestyBadge(@RequestBody String body) throws Exception {
		try {
			JSONObject json = JsonHelper.toJsonObject(body);
			long badgeId = json.getLongValue("badgeId");
			UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
			if(0 < user.honestyBadgeValue()){
				return ActionResult.fail(ErrorCodeEnum.honesty_badge_not_expire);
			}
			AgentResult result = userHonestyBadgeAgent.buyHonestyBadge(user.getUserid(), badgeId);
			if(0 != result.getCode()){
				return ActionResult.fail(result.getCode(), result.getCodemsg());
			}
			return ActionResult.success(result.getData());
		} catch (Exception e) {
			logger.error("buy honesty badge error!",e);
			return ActionResult.fail();
		}
	}

}
