package com.tigerjoys.shark.miai.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.IPayAgent.NotifyCallback;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/**
 * 达人预约金交纳回调处理
 * @author shiming
 *
 */
@Service(PayTypeEnum.GOLD)
public class PaidAppointGlod extends NotifyCallback {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Autowired
	//private IPaidAppointGoldContract paidAppointGoldContract;
	
	@Override
	protected void dealNotifyData(UserPayActionEntity payAction) throws Exception {
		logger.info("预约金完成支付回调,pay action:" + JsonHelper.toJson(payAction));

		/*PaidAppointGoldEntity siteOrder = paidAppointGoldContract.findById(payAction.getOrder_id());
		if (siteOrder == null) {
			logger.error("预约金完成支付回调,支付流水没有找到,siteFlowId=" + payAction.getOrder_id());
			return;
		}
		if (siteOrder.getStatus() != 0) {
			logger.error("预约金已经支付完毕,支付流水没有找到,siteFlowId=" + payAction.getOrder_id());
			return;
		}
		logger.error("预约金支付完毕,siteFlowId=" + payAction.getOrder_id());
		paidAppointGoldContract.updateCompeleteStatus(payAction.getOrder_id());*/
	}

}
