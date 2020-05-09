package com.tigerjoys.shark.miai.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorContactLookContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorContactLookEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;

@Login
@Controller
@RequestMapping(value = "/wx/buy", produces=Produce.TEXT_JSON)
public class WeixinBuyAnchorContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinBuyAnchorContactController.class);
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IAnchorContactLookContract anchorContactLookContract;
	
	@Login
	@UserClientService("wx")
	@RequestMapping(value="/anchor/contact")
	@ResponseBody
	public ActionResult buyAnchorContact(@RequestBody String body) throws Exception {
		try {
			long userid = RequestUtils.getCurrent().getUserid();
			JSONObject json = JsonHelper.toJsonObject(body);
			long anchorId = json.getLongValue("anchorId");
			AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", anchorId);
			if(userid <= 0 || anchorId <= 0 || Tools.isNull(anchor))
				return ActionResult.fail();
			//首先检测对应的用户是否买过了本主播的联系方式
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("anchorid", anchorId));
			long count = anchorContactLookContract.count(pageModel);
			int look = 0;
			int price = 0;
			if(count == 0) {
				//进行扣费操作处理
				DiamondResultDto<Long> result = DiamondResultDto.fail(AgentErrorCodeEnum.error.getCode());
				price = anchor.getContact_price();
				//计算主播的收益
				float ratio = anchor.getRatio();
				if(price == 0) {
					if(anchor.getStar() == 3) {
						price = 2000;
					} else if(anchor.getStar() == 4) {
						price = 4000;
					} else if(anchor.getStar() == 5) {
						price = 6000;
					}
				}
				try {
					SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.APP_ROBOT_COFIG);
					JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
					if(Tools.isNotNull(ctrl)) {
						//获取对应的分成和假主播价格
						if(anchor.getFlag() == 4) {
							int anchorPrice = ctrl.getIntValue("anchorPrice");
							if (anchorPrice > 0) {
								price = anchorPrice;
							}
						}
						int anchorIncome = ctrl.getIntValue("anchorIncome");
						if(anchorIncome > 0) {
							ratio = anchorIncome;
						}
					}
				} catch (Exception e) {
					
				}
				
				long logid = IdGenerater.generateId();
				if(AgentErrorCodeEnum.success.getCode() == (result = userDiamondAgent.changeDiamondAccount(userid, price, null, UserDiamondAccountLogTypeEnum.user_look_anchor_contact.getCode(),0, null, String.valueOf(logid), UserDiamondAccountLogTypeEnum.user_look_anchor_contact.getDesc())).getCode()) {
					//使用默认的分成比例
					if(ratio <= 0)
						ratio = 40;
					int amount = Math.round(price * (ratio / 20));
					userIncomeAgent.changeIncomeAccount(anchorId, amount, 1, UserIncomeAccountLogTypeEnum.user_look_anchor_contact, String.valueOf(logid), UserIncomeAccountLogTypeEnum.user_look_anchor_contact.getDesc());
					look = 1;
					AnchorContactLookEntity t = new AnchorContactLookEntity();
					t.setUserid(userid);
					t.setDiamond(price);
					t.setAnchorid(anchorId);
					t.setCreate_time(new Date());
					anchorContactLookContract.insert(t);
				} 
			} else {
				look = 1;
			}
			if(look == 1) {
				//可以进行查看联系方式弹窗
				return ActionResult.success(anchor.getNickname()+"联系方式\n"+anchor.getContact_text());
			} else {
				//费用不足 充值弹窗
				return ActionResult.fail(ErrorCodeEnum.balance_no_enough.getCode(), "充值后即可畅看哦~");
			}
		} catch (Exception e) {
			return ActionResult.fail();
		}
	}
}
