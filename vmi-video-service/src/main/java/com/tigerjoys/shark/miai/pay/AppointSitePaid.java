package com.tigerjoys.shark.miai.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.agent.IPayAgent.NotifyCallback;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAppointSiteOrderContract;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteOrderEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;

/**
 * @author yangjunming
 * @since JDK 1.8.0
 */
@Service(PayTypeEnum.SITE)
public class AppointSitePaid extends NotifyCallback {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IAppointSiteOrderContract appointSiteOrderContract;

	@Override
	protected void dealNotifyData(UserPayActionEntity payAction) throws Exception {
		logger.info("场地预订完成支付回调,pay action:" + JsonHelper.toJson(payAction));

		AppointSiteOrderEntity siteOrder = appointSiteOrderContract.findById(payAction.getOrder_id());
		if (siteOrder == null) {
			logger.error("场地预订完成支付回调,支付流水没有找到,siteFlowId=" + payAction.getOrder_id());
			return;
		}
		if (siteOrder.getStatus() != 0) {
			logger.error("场地预订已经支付完毕,支付流水没有找到,siteFlowId=" + payAction.getOrder_id());
			return;
		}
		logger.error("场地预订支付完毕,siteFlowId=" + payAction.getOrder_id());
		appointSiteOrderContract.updateCompeleteStatus(payAction.getOrder_id());
	}
}
