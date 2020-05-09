package com.tigerjoys.shark.miai.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.shark.miai.common.enums.UserCreditRecordEnum;
import org.shark.miai.common.enums.UserCreditScoreAccountResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.UniqueIDUtils;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserCreditScoreAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.dto.CreditRecordBO;
import com.tigerjoys.shark.miai.agent.dto.CreditScoreConfigureBO;
import com.tigerjoys.shark.miai.agent.dto.PurchaseCreditScorePageDto;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;

/**
 * 用户信用分接口
 * @author liuman
 *
 */
@Login
@Controller
@RequestMapping(value="/api/creditscore",produces=Produce.TEXT_ENCODE)
//@RequestMapping(value="/api/creditscore")
public class CreditScoreController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditScoreController.class);
	
	@Autowired
	private IUserCreditScoreAgent userCreditScoreAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	/**
	 * 信用分购买页面接口
	 */
	@RequestMapping(value="/purchase",method=RequestMethod.POST)
	public @ResponseBody ActionResult purchasePage() throws Exception {
		long userid = RequestUtils.getCurrent().getUserid();
		List<CreditScoreConfigureBO> configures = null;
		try {
			PurchaseCreditScorePageDto purchaseCreditScorePageDto = new PurchaseCreditScorePageDto();
			purchaseCreditScorePageDto.setBalanceScore(userCreditScoreAgent.getUserCreditBalance(userid) + "");
			configures = userCreditScoreAgent.findPurchaseCreditScoreList(userid);
			purchaseCreditScorePageDto.setConfigures(configures);
			//获得钻石账户余额
			purchaseCreditScorePageDto.setBalanceDiamonds(userDiamondAgent.getDiamondBalance(userid) + "");
			return ActionResult.success(purchaseCreditScorePageDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return ActionResult.fail();
	}
	
	/**
	 * 信用分记录接口(暂时不用,变成H5页面了)
	 */
	@RequestMapping(value="/getRecords",method=RequestMethod.POST)
	public @ResponseBody ActionResult getRecords(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		
		long userid = RequestUtils.getCurrent().getUserid();
		long recordId = json.getLongValue("stamp");
		List<CreditRecordBO> creditRecords = null;
		boolean isNextPage = false;
		String stamp = "";
		try {
			creditRecords = userCreditScoreAgent.findCreditScoreRecordListByPaging(userid,recordId , Const.CREDIT_RECORD_PAGESIZE);
			//判断有没有下一页
			if (CollectionUtils.isNotEmpty(creditRecords)) {
				int size = creditRecords.size();
				if (size > Const.CREDIT_RECORD_PAGESIZE) {
					isNextPage = true;
					stamp = creditRecords.get(size - 1).getId() + "";
				}
			}
			
			return ActionResult.success(creditRecords, stamp, isNextPage);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return ActionResult.fail();
	}
	
	/**
	 * 购买信用分接口
	 */
	@RequestMapping(value="/purchase_creditscore",method=RequestMethod.POST)
	public @ResponseBody ActionResult purchaseCreditScore(@RequestBody String body) throws Exception {
		//调用消费钻石接口,购买信用分
		JSONObject json = JsonHelper.toJsonObject(body);
		
		long userId = RequestUtils.getCurrent().getUserid();
		int creditScore = json.getIntValue("creditScore");
		int diamonds = json.getIntValue("diamonds");
		String transactionFlow = String.valueOf(UniqueIDUtils.getUniqueID());
		DiamondResultDto<Long> result = userDiamondAgent.changeDiamondAccount(userId, diamonds, null, UserDiamondAccountLogTypeEnum.purchase_credit_score.getCode(),
							                  0, null, transactionFlow, UserDiamondAccountLogTypeEnum.purchase_credit_score.getDesc());
		
		//如果钻石扣除成功，才会要求增加信用值
		if(result.getCode() == AgentErrorCodeEnum.success.getCode()) {
			Map<String , Object> resultMap = userCreditScoreAgent.changeCreditScore(userId, true, creditScore, transactionFlow, 
				       UserCreditRecordEnum.purchaseAdd.getCode(), 
				       UserCreditRecordEnum.purchaseAdd.getDesc());
			
			//添加信用值成功
			if(resultMap != null && Tools.intValue(resultMap.get(UserCreditScoreAccountResultEnum.success.getKey())) == UserCreditScoreAccountResultEnum.success.getCode()) {
				PurchaseCreditScorePageDto purchaseCreditScorePageDto = new PurchaseCreditScorePageDto();
				purchaseCreditScorePageDto.setBalanceScore(userCreditScoreAgent.getUserCreditBalance(userId) + "");
				//获得钻石账户余额
				purchaseCreditScorePageDto.setBalanceDiamonds(userDiamondAgent.getDiamondBalance(userId) + "");
				return ActionResult.success(purchaseCreditScorePageDto);
			} else {
				logger.error("购买信用分，添加信用分失败！");
				
				return ActionResult.fail();
			}
		} else {
			logger.error("购买信用分，扣除钻石失败，失败code："+result.getCode()+",失败原因：" + result.getMsg());
			
			return ActionResult.fail(result.getCode(), result.getMsg());
		}
	}
	
	/**
	 * 个人信用分记录
	 * @param userId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@NoSign
	@RequestMapping(value="/records_page/{userId}",method=RequestMethod.GET,produces=Produce.TEXT_HTML)
	public String recordsPage(@PathVariable("userId") long userId,Model model) throws Exception {
		List<CreditRecordBO> creditRecords = null;
		creditRecords = userCreditScoreAgent.findCreditScoreRecordList(userId);
		//有信用分记录
		if (CollectionUtils.isNotEmpty(creditRecords)) {
			model.addAttribute("creditRecords", creditRecords);
			return "credit/credit_record";
		} else {//无信用分记录
			return "credit/no_record";
		}
			
	}
	
	/**
	 * 个人信用分记录分页
	 * @param userId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/records_page_bypaging/{userId}",method=RequestMethod.GET,produces=Produce.TEXT_HTML)
	public String recordsPageByPaging(@PathVariable("userId") long userId,Model model) throws Exception {
		List<CreditRecordBO> creditRecords = null;
		boolean isNextPage = false;
		String stamp = "";
		long recordId = 0L;
		creditRecords = userCreditScoreAgent.findCreditScoreRecordListByPaging(userId,recordId , Const.CREDIT_RECORD_PAGESIZE);
		//有信用分记录
		if (CollectionUtils.isNotEmpty(creditRecords)) {
			int size = creditRecords.size();
			if (size > Const.CREDIT_RECORD_PAGESIZE) {
				isNextPage = true;
				stamp = creditRecords.get(size - 1).getId() + "";
			}
			//获取视频相关评论后,要将stamp返回给页面
			model.addAttribute("stamp", stamp);
			//刚刚进入页面后要设置成1不管有没有数据  1-有下一页, 0-没有下一页
			model.addAttribute("nextPage", isNextPage ? 1 : 0);
			model.addAttribute("creditRecords", creditRecords);
//			model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
			return "credit/credit_record";
		} else {//无信用分记录
			return "credit/no_record";
		}
			
	}
	
}
