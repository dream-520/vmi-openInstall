package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 用户高级VIP支付处理等操作接口
 * @author chengang
 *
 */
public interface IUserSvipFlowService {
	
	/**
	 * 创建开通高级会员流水订单和支付密钥
	 * @param svip - 购买的高级会员等级
	 * @param payType - 支付渠道
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult createPaySvipFlow(int svip , int payType) throws Exception;
	
	/**
	 * 开通高级会员充值成功处理
	 * @param flowId - 流水ID
	 * @param money - 充值金额
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult paySuccessSvip(long flowId , long money) throws Exception;

}
