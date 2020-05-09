package com.tigerjoys.shark.miai.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.IPayAgent.NotifyCallback;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.service.IUserSvipFlowService;

/**
 * 购买高级会员的返回回调
 * @author chengang at [2018年1月26日 13:40:27]
 * @since JDK 1.8.0
 */
@Service(PayTypeEnum.SVIP)
public class BuySvipFlowPaid extends NotifyCallback {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserSvipFlowService userSvipService;
	
	@Override
	protected void dealNotifyData(UserPayActionEntity payAction) throws Exception {
		logger.info("购买高级会员支付回调,pay action:" + JsonHelper.toJson(payAction));
		
		ActionResult result = userSvipService.paySuccessSvip(payAction.getOrder_id(), payAction.getMoney());
		if(result.getCode() != ErrorCodeEnum.success.getCode()) {
			logger.error("校验高级会员支付失败,code=" + result.getCode()+",msg="+result.getCodemsg());
		}
	}

}
