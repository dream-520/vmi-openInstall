package com.tigerjoys.shark.miai.service.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.enums.UserCreditRecordEnum;
import org.shark.miai.common.enums.UserCreditScoreAccountResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.UniqueIDUtils;
import com.tigerjoys.shark.miai.agent.IUserCreditScoreAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.dto.CreditScoreConfigureBO;
import com.tigerjoys.shark.miai.agent.dto.PurchaseCreditScorePageDto;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserCreditScoreLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreLogEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


/**
 * 信用分相关业务测试
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class CreditScoreTest extends BaseTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditScoreTest.class);
	
	@Autowired
	private IUserCreditScoreAgent userCreditScoreAgent;
	
	@Autowired
	private IUserCreditScoreLogContract userCreditScoreLogContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	/**
	 * 信用分购买页数据测试
	 * @throws Exception 
	 */
	@Test
	public void testCrediScorePurchase() throws Exception {
		long userId = 10001L;
		PurchaseCreditScorePageDto purchasePage = new PurchaseCreditScorePageDto();
		purchasePage.setBalanceScore("10");
		List<CreditScoreConfigureBO> configures = userCreditScoreAgent.findPurchaseCreditScoreList(userId);
		purchasePage.setConfigures(configures);
		ActionResult result = ActionResult.success(purchasePage);
		logger.info("result of json:{}", JsonHelper.toJson(result));
	}
	
	/**
	 * 批量加入用户信用分数据
	 * @throws Exception 
	 */
	@Test
	public void testBatchAddCrediScore() throws Exception {
		for (int i=0; i<= 25; i++) {
			this.addCreditScore();
		}
	}
	
	private void addCreditScore() throws Exception {
		long userId = 123L;
		String transactionFlow = String.valueOf(UniqueIDUtils.getUniqueID());
		Date currentDate = new Date();
		//记录日志
		UserCreditScoreLogEntity log = new UserCreditScoreLogEntity();
		log.setAmount(10L);
		log.setBalance(10L);
		log.setCreate_time(currentDate);
		log.setLogtype(1);
		log.setMemo(UserCreditRecordEnum.purchaseAdd.getDesc());
		log.setType(1);
		log.setUserid(userId);
		log.setTransaction_flow(transactionFlow);
		userCreditScoreLogContract.insert(log);
	}
	
	@Test
	public void testPurchaseCreditScore() throws Exception {
		ActionResult actionResult = null;
		long userId = 22839886300119296L;
		int creditScore = 100;
		int diamonds = 100;
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
				actionResult = ActionResult.success(purchaseCreditScorePageDto);
			} else {
				logger.error("购买信用分，添加信用分失败！");
				
				actionResult = ActionResult.fail();
			}
		} else {
			logger.error("购买信用分，扣除钻石失败，失败code："+result.getCode()+",失败原因：" + result.getMsg());
			
//			actionResult = ActionResult.success(result.getCode() , result.getMsg());
			actionResult = ActionResult.fail(result.getCode(), result.getMsg());
		}
		
		logger.info("json of result:{}",JsonHelper.toJson(actionResult));
	}
}
