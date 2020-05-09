package com.tigerjoys.shark.miai.agent;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserChargeDataLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.ICashFlowContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayTimerContract;
import com.tigerjoys.shark.miai.inter.entity.CashFlowEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayTimerEntity;

/**
 * 支付中心
 * 
 * @author mouzhanpeng at [2017年10月9日 下午6:15:08]
 * @since JDK 1.8.0
 */
public interface IPayAgent {

	/**
	 * 支付
	 * 
	 * @param money
	 * @return 签名数据（String）
	 * @throws Exception
	 */
	public AgentResult preparePay(PayAccessDto dto);

	/**
	 * 通过支付宝支付
	 * 
	 * @param money
	 * @return 签名数据（String）
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public AgentResult payWithAli(PayAccessDto dto) throws Exception;
	
	/**
	 * 通过支付宝H5支付
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public AgentResult payWithAliH5(PayAccessDto dto) throws Exception;

	/**
	 * 通过微信支付
	 * 
	 * @param money
	 * @return 签名数据（Map<String,String>）
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public AgentResult payWithWx(PayAccessDto dto) throws Exception;

	/**
	 * 余额支付
	 * 
	 * @param entity
	 * @return 余额（Long）
	 * @throws Exception
	 */
	public AgentResult payWithBal(PayAccessDto dto) throws Exception;

	/**
	 * 苹果支付，只处理消耗性项目</br>
	 * 调用此方法前，请先通过{@link IPayAgent#ensureOrderViaReceipt(String)}方法确定订单是否存在
	 * @param entity
	 * @param receipt     - 收据
	 * @param productId   - 商品ID
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public AgentResult payWithIap(PayAccessDto dto) throws Exception;
	
	/**
	 * 微信H5支付
	 * @param dto
	 * @return
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public AgentResult payWithWxH5(PayAccessDto dto) throws Exception;
	
	/**
	 * 支付中心异步支付通知
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @throws Exception
	 */
	public void notifyPayCenter(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 支付宝异步接收支付通知
	 * 
	 * @param request
	 * @param response
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public void notifyFromAli(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	/**
	 * 支付宝H5异步接收支付通知
	 * 
	 * @param request
	 * @param response
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public void notifyFromAliH5(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 微信异步接收支付通知
	 * 
	 * @param request
	 * @param response
	 * @deprecated 已经被支付中心取代，等支付中心上线正常后，下一个版本删除
	 * @throws Exception
	 */
	public void notifyFromWx(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 支付成功以后的回调操作
	 * 
	 * @author mouzhanpeng at [2017年10月9日 下午6:26:38]
	 * @since JDK 1.8.0
	 */
	abstract class NotifyCallback {
		
		@Autowired
		private ICashFlowContract cashFlowContract;
		
		@Autowired
		private IUserChargeDataAgent userChargeDataAgent;
		
		@Autowired
		private IUserPayTimerContract userPayTimerContract;
		
		@Autowired
		private IPayUserAgent payUserAgent;

		/**
		 * 回调操作
		 * 
		 * @param entity
		 */
		protected abstract void dealNotifyData(UserPayActionEntity entity) throws Exception;

		/**
		 * 传递支付通知数据
		 * 
		 * @param entity
		 * @throws Exception
		 */
		public void notifyData(UserPayActionEntity entity) throws Exception {
			dealNotifyData(entity);
			// 记录现金流
			CashFlowEntity cash = new CashFlowEntity();
			if (PayChannelEnum.balance.getCode() != entity.getPay_channel()) {
				Date date = new Date();
				cash.setCreate_time(date);
				cash.setUpdate_time(date);
				cash.setUser_id(entity.getUser_id());
				cash.setUser_openid(entity.getBuyer_id());
				cash.setUser_account(entity.getBuyer_email());
				cash.setOrder_id(entity.getOrder_id());
				cash.setTransaction_id(entity.getTrade_no());
				cash.setMoney(entity.getMoney());
				cash.setType(PayTypeEnum.getByCode(entity.getType()).getCash().getCode());
				cash.setLogtype(1);
				cash.setStatus(1);
				cashFlowContract.insert(cash);
			}
			
			//新增记录用户的充值金额[只记录微信和支付宝]LOG以及自动SVIP升级
			if(entity.getPay_channel() == PayChannelEnum.ali.getCode() || entity.getPay_channel() == PayChannelEnum.wx.getCode()) {
				userChargeDataAgent.changeChargeAmount(entity.getUser_id(), entity.getMoney(), UserChargeDataLogTypeEnum.charge_diamond, PayTypeEnum.getDescByCode(entity.getType()));
			}
			
			//触发对应的充值提升等级的操作处理
			try {
				UserPayTimerEntity t = new UserPayTimerEntity();
				t.setUserid(entity.getUser_id());
				t.setState(0);
				t.setCreate_time(new Date());
				t.setUpdate_time(new Date());
				userPayTimerContract.insert(t);
			} catch (Exception e) {

			}
			
			//修改用户充值表
			try {
				payUserAgent.updatePay(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 苹果同一订单多次验证时，提取订单号，避免冗余多个重复订单
	 * @param md5Receipt - md5加密后的收据
	 * @return 订单号
	 */
	public Long ensureOrderViaReceipt(String md5Receipt) throws Exception;
}
