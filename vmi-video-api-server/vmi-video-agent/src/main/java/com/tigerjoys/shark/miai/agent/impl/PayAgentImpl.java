package com.tigerjoys.shark.miai.agent.impl;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.google.common.collect.Maps;
import com.tigerjoys.nbs.common.ApiResult;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.BeanUtils;
import com.tigerjoys.nbs.common.utils.IOUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.mybatis.core.utils.SpringBeanApplicationContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IIOSUserSmsAgent;
import com.tigerjoys.shark.miai.agent.ILoggerAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IUserBalanceAccountAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.dto.PayPrepayResultDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.pay.AlipayBuilder;
import com.tigerjoys.shark.miai.agent.pay.AlipayConfig;
import com.tigerjoys.shark.miai.agent.pay.AlipayH5Config;
import com.tigerjoys.shark.miai.agent.pay.AlipayHelper;
import com.tigerjoys.shark.miai.agent.pay.AlipayNotify;
import com.tigerjoys.shark.miai.agent.pay.IapConfig;
import com.tigerjoys.shark.miai.agent.pay.IapHelper;
import com.tigerjoys.shark.miai.agent.pay.PayCenterConfig;
import com.tigerjoys.shark.miai.agent.pay.WxpayBuilder;
import com.tigerjoys.shark.miai.agent.pay.WxpayConfig;
import com.tigerjoys.shark.miai.agent.pay.WxpayHelper;
import com.tigerjoys.shark.miai.agent.pay.WxpayUtil;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserPayActionContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.CopyUserPayActionEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.pay.client.IPayCenterClient;
import com.tigerjoys.shark.pay.client.PayCenterSignHelper;
import com.tigerjoys.shark.pay.client.dto.PayCenterIapResultDto;
import com.tigerjoys.shark.pay.client.dto.PayCenterSubmitOrderDto;
import com.tigerjoys.shark.pay.client.dto.PayCenterSubmitOrderResultDto;
import com.tigerjoys.shark.pay.client.enums.PayCenterCodeEnums;

/**
 * @author mouzhanpeng at [2017年10月9日 下午6:28:21]
 * @since JDK 1.8.0
 */
@Service
public class PayAgentImpl implements IPayAgent , InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 线上环境内置苹果支付的沙盒用户
	private static final Set<Long> DEFAULT_IAP_MIXURE_USERS = new HashSet<>(Arrays.asList(32394473073869056L, 33697911852302592L,156227137842512384L,163069912242258176L,164485333663154688L,171239105804435968L,171976577914044928L,173585561538330880L));
	
	@Autowired
	private IUserPayActionContract userPayActionContract;

	@Autowired
	private IUserBalanceAccountAgent userBalanceAccountAgent;
	
	@Autowired
	private ICopyUserPayActionContract copyUserPayActionContract;
	
	@Autowired
	private IIOSUserSmsAgent iOSUserSmsAgent;
	
	@Autowired
	private ILoggerAgent loggerAgent;
	
	@Autowired(required=false)
	private IPayCenterClient payCenterClient;
	
	/**
	 * 支付中心上线后，下一个版本删除
	 */
	@Deprecated
	public String ip;
	
	@PostConstruct
	public void init() {
		//处理获取服务器的真实出口ip地址
		Map<String , String> headerMap1 = new HashMap<>();
        headerMap1.put("Host", "ip.didiman.com");
        headerMap1.put("Connection", "Keep-Alive");
        headerMap1.put("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36");
        
        ResponseStatus response;
		try {
			response = HttpUtils.get("http://ip.didiman.com/", ECharset.UTF_8, EContentType.APPLICATION_JSON, headerMap1, null);
			if(Tools.isNotNull(response)){
	            String content = response.getContent();
	            if(Tools.isNotNull(content)) {
	            	content = content.replaceAll("\r|\n", "");
	            	ip = content;
	            }
	        }
		}  catch (Exception e) {
			
		}
		//logger.info("获取的ip地址为:"+ip);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		/*if(SpringBeanApplicationContext.containsBean("payCenterClient")) {
			payCenterClient = SpringBeanApplicationContext.getBean("payCenterClient", IPayCenterClient.class);
		}*/
	}

	@Override
	public AgentResult preparePay(PayAccessDto dto) {
		try {
			switch (dto.getPay_channel()) {
				case balance : {//余额支付
					return payWithBal(dto);
				}
				case iap : {//IAP
					PageModel pm = PageModel.getLimitModel(0, 1);
					pm.addQuery(Restrictions.eq("order_id", dto.getOrder_id()));
					pm.addQuery(Restrictions.eq("type", dto.getType().getCode()));
					pm.addQuery(Restrictions.eq("status", 1));
					long rows = userPayActionContract.count(pm);
					if(rows>0){
						return AgentResult.success();
					}
				}
				default : {//其他一律走支付中心(注意：IAP支付上面没有return，只是做了一个校验)
					UserPayActionEntity entity = payAction(dto);
					
					PayCenterSubmitOrderDto submitOrder = new PayCenterSubmitOrderDto();
					submitOrder.setApp_channel(dto.getApp_channel());
					submitOrder.setApp_package(dto.getPackage_name());
					submitOrder.setApp_version(dto.getApp_version());
					submitOrder.setDescription(dto.getDescription());
					submitOrder.setNotify_url(PayCenterConfig.NOTIFY_URL);
					submitOrder.setReturn_url(PayCenterConfig.RETURN_URL);//只有阿里H5才会回调
					submitOrder.setOs_type(dto.getApp_type());//这里跟支付中心的完全对应上
					submitOrder.setOut_order_no(String.valueOf(entity.getId()));
					submitOrder.setPay_channel(dto.getPay_channel().getCode());//这里跟支付中心的完全对应上
					submitOrder.setProduct_id(dto.getType().name()+"_"+dto.getMoney());
					submitOrder.setSpbill_create_ip(Tools.getIP(RequestUtils.getCurrent().getRequest()));
					//submitOrder.setSpbill_create_ip("118.187.28.42");
					submitOrder.setSubject(dto.getSubject());
					submitOrder.setTotal_amount(dto.getMoney());
					submitOrder.setIos_receipt(dto.getReceipt());
					submitOrder.setIos_transaction_id(dto.getIpaTransactionId());
					//如果是微信JS支付，需要设置openid
					if(dto.getPay_channel() == PayChannelEnum.wxJS) {
						submitOrder.setOpenid(dto.getOpenId());
					}
					
					loggerAgent.payParamsLogger(entity.getUser_id(), "PayCenter-"+dto.getPay_channel().name(), true , submitOrder);
					ApiResult<PayCenterSubmitOrderResultDto> result = payCenterClient.submitOrder(submitOrder);
					loggerAgent.payParamsLogger(entity.getUser_id(), "PayCenter-"+dto.getPay_channel().name(), false , result);
					
					if(result.getCode() == PayCenterCodeEnums.SUCCESS.getCode()) {
						if(dto.getPay_channel() != PayChannelEnum.iap) {
							return AgentResult.success(PayPrepayResultDto.create(result.getData().getSign_data(), entity.getId()));
						} else {
							//IAP支付还需要做一些校验处理
							return verifyIap(JsonHelper.toObject(result.getData().getSign_data(), PayCenterIapResultDto.class), entity, dto.getProduct_id());
						}
					} else {
						return AgentResult.fail(result.getCode() , result.getCodemsg());
					}
				}
			}
		} catch (Exception e) {
			logger.error("pay action occur error ！", e);
			return AgentResult.fail();
		}
		
		/*try {
			switch (dto.getPay_channel()) {
				case ali:
					return payWithAli(dto);
				case wx:
					return payWithWx(dto);
				case iap:
					return payWithIap(dto);
				case balance:
					return payWithBal(dto);
				case wxH5:
					return payWithWxH5(dto);
				case aliH5:
					return payWithAliH5(dto);
				default:
					return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
			}
		} catch (Exception e) {
			logger.error("pay action occur error ！", e);
			return AgentResult.fail();
		}*/
	}

	@Override
	public AgentResult payWithAli(PayAccessDto dto) throws Exception {
		UserPayActionEntity entity = payAction(dto);
		
		// 构建请求参数,顺序不能改变
		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
		params.put("partner", AlipayConfig.PARTNER);
		params.put("out_trade_no", String.valueOf(entity.getId()));
		params.put("seller_id", AlipayConfig.SELLER_ID);
		params.put("subject", entity.getSubject());
		params.put("body", entity.getDescription());
		params.put("total_fee", Tools.formatDouble2PercentToString(entity.getMoney()));
		params.put("notify_url", AlipayConfig.NOTIFY_URL);
		params.put("service", AlipayConfig.SERVICE);
		params.put("payment_type", AlipayConfig.PAYMENT_TYPE);
		params.put("_input_charset", AlipayConfig.INPUT_CHARSET);
		// 签名
		params = AlipayBuilder.buildRequestPara(params);
		loggerAgent.payParamsLogger(entity.getUser_id(), "ALI-CLI", true, params);
		// 将post接收到的数组所有元素，按照[参数="参数值"]的模式用[&]字符拼接成字符串。需要排序。[value加双引号]
		return AgentResult.success(AlipayHelper.createLinkString(params));
	}
	
	@Override
	public AgentResult payWithAliH5(PayAccessDto dto) throws Exception {
		UserPayActionEntity entity = payAction(dto);
		
		// 将post接收到的数组所有元素，按照[参数="参数值"]的模式用[&]字符拼接成字符串。需要排序。[value加双引号]
		AlipayClient client = new DefaultAlipayClient(AlipayH5Config.URL, AlipayH5Config.APPID, AlipayH5Config.RSA_PRIVATE_KEY, AlipayH5Config.FORMAT, AlipayH5Config.CHARSET, AlipayH5Config.ALIPAY_PUBLIC_KEY,AlipayH5Config.SIGNTYPE);
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel payModel=new AlipayTradeWapPayModel();
	    payModel.setOutTradeNo(String.valueOf(entity.getId()));
	    payModel.setSubject(entity.getSubject());
	    payModel.setTotalAmount(Tools.formatDouble2PercentToString(entity.getMoney()));
	    payModel.setBody(entity.getDescription());
	    payModel.setTimeoutExpress("1c");
	    payModel.setProductCode("QUICK_WAP_WAY");
	    alipay_request.setBizModel(payModel);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(AlipayH5Config.notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(AlipayH5Config.return_url);  
	    // 构建请求参数,顺序不能改变
 		LinkedHashMap<String, String> params = new LinkedHashMap<String, String>();
 		params.put("partner", AlipayH5Config.APPID);
 		params.put("out_trade_no",payModel.getOutTradeNo());
 		params.put("subject", payModel.getSubject());
 		params.put("body", payModel.getBody());
 		params.put("total_fee",payModel.getTotalAmount());
 		params.put("notify_url", AlipayH5Config.notify_url);
 		params.put("return_url", AlipayH5Config.return_url);
 		params.put("service", AlipayConfig.SERVICE);
 		//params.put("payment_type", AlipayConfig.PAYMENT_TYPE);
 		params.put("_input_charset", AlipayH5Config.CHARSET);
	 	// 签名
		loggerAgent.payParamsLogger(entity.getUser_id(), "ALIH5-CLI", true, params);
	    // form表单生产
	    String form = "";
		// 调用SDK生成表单
		form = client.pageExecute(alipay_request).getBody();
		return AgentResult.success(form);
	}

	@Override
	public AgentResult payWithWx(PayAccessDto dto) throws Exception {
		UserPayActionEntity entity = payAction(dto);
		Pair<String, String> info = WxpayConfig.getAppInfo(dto.getApp_type(), dto.getApp_channel(), dto.getPackage_name());
		// 构建请求参数
		Map<String, String> params = new HashMap<>();
		params.put("appid", info.getValue0());
		params.put("mch_id", info.getValue1());
		params.put("nonce_str", WxpayUtil.getNonceStr());
		params.put("body", entity.getSubject());
		params.put("detail", entity.getDescription());
		params.put("out_trade_no", String.valueOf(entity.getId()));
		params.put("total_fee", String.valueOf(entity.getMoney()));
		params.put("spbill_create_ip", RequestUtils.getCurrent().getRequest().getRemoteAddr());
		params.put("notify_url", WxpayConfig.NOTIFY_URL);
		params.put("trade_type", WxpayConfig.TRADE_TYPE);
		// 签名
		params = WxpayBuilder.buildRequestPara(params);
		loggerAgent.payParamsLogger(entity.getUser_id(), "WX-PREPAY", true, params);
		// 预支付请求
		String prepay = WxpayUtil.postXmlClient(WxpayConfig.PREPAY_URL, WxpayUtil.parseToXml(params));
		// 预支付响应
		Map<String, String> fromWx = WxpayUtil.parseToMap(prepay);
		loggerAgent.payParamsLogger(entity.getUser_id(), "WX-PREPAY", false, fromWx);
		String prepay_id = null;
		if (Tools.isNull(fromWx)) {
			logger.info("wx_return params is null!");
			return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), AgentErrorCodeEnum.wechat_prepay_return_fail.getDesc());
		} else {
			String return_code = fromWx.get("return_code");
			if (Tools.isNull(return_code)) {
				logger.info("wx_return [return_code] is null!");
				return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), AgentErrorCodeEnum.wechat_prepay_return_fail.getDesc());
			} else {
				if (WxpayConfig.SUCCESS.equalsIgnoreCase(return_code)) {
					// 验证签名
					if (WxpayHelper.verify(fromWx)) {
						String result_code = fromWx.get("result_code");
						if (Tools.isNull(result_code)) {
							logger.info("wx_return [result_code] is null!");
							return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), AgentErrorCodeEnum.wechat_prepay_return_fail.getDesc());
						} else {
							if (WxpayConfig.SUCCESS.equalsIgnoreCase(result_code)) {
								prepay_id = fromWx.get("prepay_id");
							} else {
								logger.info(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode() + ":" + fromWx.get("err_code_des"));
								return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), fromWx.get("err_code_des"));
							}
						}
					} else {
						logger.info(AgentErrorCodeEnum.pay_sign_check_fail.getDesc());
						return AgentResult.fail(AgentErrorCodeEnum.pay_sign_check_fail.getCode(), AgentErrorCodeEnum.pay_sign_check_fail.getDesc());
					}
				} else {
					logger.info(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode() + ":" + fromWx.get("return_msg"));
					return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), fromWx.get("return_msg"));
				}
			}
		}
		Map<String, String> data = new HashMap<>();
		data.put("appid", info.getValue0());
		data.put("partnerid", info.getValue1());
		data.put("prepayid", prepay_id);
		data.put("package", WxpayConfig.PACKAGE);
		data.put("noncestr", WxpayUtil.getNonceStr());
		data.put("timestamp", WxpayUtil.getTimeStamp());
		// 签名
		data = WxpayBuilder.buildRequestPara(data);
		loggerAgent.payParamsLogger(entity.getUser_id(), "WX-CLI", true, data);
		return AgentResult.success(data);
	}
	
	@Override
	public AgentResult payWithWxH5(PayAccessDto dto) throws Exception {
		UserPayActionEntity entity = payAction(dto);
		//临时使用固定的支付渠道--------->目前暂时用的是android的
		Pair<String, String> info = WxpayConfig.getAppInfo(dto.getApp_type(), dto.getApp_channel(), "android.pay");
		
		//测试获取真实ip
		/*
		String ip = RequestUtils.getCurrent().getRequest().getHeader("x-forwarded-for");
		if(Tools.isNull(ip)) {
			ip = RequestUtils.getCurrent().getRequest().getHeader("x-real-ip");
			if(Tools.isNull(ip)) {
				ip = RequestUtils.getCurrent().getRequest().getRemoteAddr();
			}
		}
		*/
		
		/*
		Enumeration<String> headers = RequestUtils.getCurrent().getRequest().getHeaderNames();
		if(Tools.isNotNull(headers)) {
			while (headers.hasMoreElements()) {
				String name = headers.nextElement();
				logger.error("name:"+name+" value:"+RequestUtils.getCurrent().getRequest().getHeader(name));
	        }
		}
		
		*/
		
		if(Tools.isNull(ip)) {
			logger.info("获取服务器ip出现了问题");
			return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), AgentErrorCodeEnum.wechat_prepay_return_fail.getDesc());
		}
		// 构建请求参数
		Map<String, String> params = new HashMap<>();
		params.put("appid", info.getValue0());
		params.put("mch_id", info.getValue1());
		params.put("nonce_str", WxpayUtil.getNonceStr());
		params.put("body", entity.getSubject());
		params.put("detail", entity.getDescription());
		params.put("out_trade_no", String.valueOf(entity.getId()));
		params.put("total_fee", String.valueOf(entity.getMoney()));
		//params.put("spbill_create_ip", "118.187.28.42");
		if(Const.is_test) {
			params.put("spbill_create_ip", "118.187.28.42");
		} else {
			params.put("spbill_create_ip", ip);
		}
		params.put("notify_url", WxpayConfig.NOTIFY_URL);
		params.put("trade_type", "MWEB");

		// 签名
		params = WxpayBuilder.buildRequestPara(params);
		loggerAgent.payParamsLogger(entity.getUser_id(), "WX-PREPAY", true, params);
		// 预支付请求
		String prepay = WxpayUtil.postXmlClient(WxpayConfig.PREPAY_URL, WxpayUtil.parseToXml(params));
		// 预支付响应
		Map<String, String> fromWx = WxpayUtil.parseToMap(prepay);
		loggerAgent.payParamsLogger(entity.getUser_id(), "WX-PREPAY", false, fromWx);
		if (Tools.isNull(fromWx)) {
			logger.info("wx_return params is null!");
			return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), AgentErrorCodeEnum.wechat_prepay_return_fail.getDesc());
		} else {
			String return_code = fromWx.get("return_code");
			if (Tools.isNull(return_code)) {
				logger.info("wx_return [return_code] is null!");
				return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), AgentErrorCodeEnum.wechat_prepay_return_fail.getDesc());
			} else {
				if (WxpayConfig.SUCCESS.equalsIgnoreCase(return_code)) {
					//拼接对应的微信链接地址和对应的支付情况跳转地址
//					String redirect_url=WxpayConfig.REDIRECT_URL+"?out_trade_no="+entity.getId();
//					//对回跳的url的网址进行URLEncode
//			        String urlString = URLEncoder.encode(redirect_url, "utf-8");
					String mweb_url = fromWx.get("mweb_url");
//					mweb_url+="&redirect_url=";
//					mweb_url+=urlString;
					Map<String, String> data = new HashMap<String, String>();
					data.put("mweb_url", mweb_url);
					data.put("orderId", entity.getId()+"");
					return AgentResult.success(data);
				} else {
					logger.info(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode() + ":" + fromWx.get("return_msg"));
					return AgentResult.fail(AgentErrorCodeEnum.wechat_prepay_return_fail.getCode(), fromWx.get("return_msg"));
				}
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult payWithIap(PayAccessDto dto) throws Exception {
		PageModel pm = PageModel.getLimitModel(0, 1);
		pm.addQuery(Restrictions.eq("order_id", dto.getOrder_id()));
		pm.addQuery(Restrictions.eq("type", dto.getType().getCode()));
		pm.addQuery(Restrictions.eq("status", 1));
		long rows = userPayActionContract.count(pm);
		if(rows>0){
			return AgentResult.success();
		}
		UserPayActionEntity entity = payAction(dto);
		// 校验
		String payStr = IapHelper.getPayData(dto.getReceipt());
		logger.info("verifyReceipt_IAP:payStr="+payStr);
		loggerAgent.payParamsLogger(entity.getUser_id(), "IAP-VERIFY", false, JsonHelper.toMap(payStr));
		JSONObject payData = JsonHelper.toJsonObject(payStr);
		int status = payData.getIntValue("status");
		if (0 == status) {
			entity.setIap_sandbox(0);
			return verifyReceipt(payData, entity, dto.getProduct_id());
		} else if(21007 == status){// 沙盒测试数据标识
			if(!iOSUserSmsAgent.getUserIdList().containsKey(entity.getUser_id())){
				return AgentResult.fail(AgentErrorCodeEnum.sign_error.getCode(), "非法用户！");
			}
			entity.setIap_sandbox(1);
			payStr = IapHelper.getPayData(dto.getReceipt(),IapConfig.DEV_VERIFY_URL);
			loggerAgent.payParamsLogger(entity.getUser_id(), "IAP-VERIFY", false, JsonHelper.toMap(payStr));
			payData = JsonHelper.toJsonObject(payStr);
			if(0 == payData.getIntValue("status")){
				return verifyReceipt(payData, entity, dto.getProduct_id()); 
			}else{
				return AgentResult.fail(status, "[沙盒苹果服务器]未响应数据！");
			}
		}else{
			return AgentResult.fail(status, "[线上苹果服务器]未响应数据！");
		}
	}
	
	/**
	 * IAP支付返回校验
	 * @param iapResult - 支付中心返回的IAP支付结果对象
	 * @param entity - 用户支付记录对象
	 * @param productId - 商品ID
	 * @return AgentResult
	 * @throws Exception
	 */
	private AgentResult verifyIap(PayCenterIapResultDto iapResult, UserPayActionEntity entity, String productId) throws Exception {
		int status = iapResult.getStatus();
		if(iapResult.getSanbox() == 1) {
			if(0 == status){
				return verifyIapResult(iapResult , entity , productId); 
			}else{
				return AgentResult.fail(status, "[沙盒苹果服务器]未响应数据！");
			}
		} else {
			if (0 == status) {
				return verifyIapResult(iapResult , entity , productId);
			} else {
				return AgentResult.fail(status, "[线上苹果服务器]未响应数据！");
			}
		}
	}
	
	/**
	 * IAP支付返回信息校验
	 * @param iapResult - 支付中心返回的IAP支付结果对象
	 * @param entity - 用户支付记录对象
	 * @param productId - 商品ID
	 * @return AgentResult
	 * @throws Exception
	 */
	private AgentResult verifyIapResult(PayCenterIapResultDto iapResult, UserPayActionEntity entity, String productId) throws Exception {
		long initialPrice = entity.getInitial_price()/100;
		if (!iapResult.getProduct_id().endsWith(initialPrice+"")) {// 校验商品标识
			logger.info("product_id_error:payid:"+entity.getId()+";product_Id="+iapResult.getProduct_id()+";initialPrice="+initialPrice);
			return AgentResult.fail(AgentErrorCodeEnum.IPA_product_id_error);
		}
		
		entity.setBuyer_email("未知");
		entity.setBuyer_id("未知");
		entity.setPay_time(iapResult.getPurchase_date_ms()>0?new Date(iapResult.getPurchase_date_ms()):null);
		//entity.setTrade_no();
		entity.setStatus(1);
		entity.setIap_sandbox(iapResult.getSanbox());
		entity.setTrade_status("success");
		// 调用回调事务
		try {
			payCallback(entity);
		} catch (Exception e) {
			entity.setStatus(-1);
			entity.setTrade_status("[苹果]调用接口出错");
			logger.info("[苹果]调用接口出错", e);
			userPayActionContract.update(entity);
			return AgentResult.fail();
		}
		
		entity.setTimes(getTimes(entity.getUser_id()));
		userPayActionContract.update(entity);
		return AgentResult.success();
	}

	/**
	 * 苹果服务器返回数据验证
	 * @param payData
	 * @param entity
	 * @param productId
	 * @return
	 * @deprecated 支付中心上线后，下一个版本将作废
	 * @throws Exception
	 */
	private AgentResult verifyReceipt(JSONObject payData, UserPayActionEntity entity, String productId) throws Exception{
		payData = payData.getJSONObject("receipt");
		if (!IapConfig.BUNDLE_ID.equals(payData.getString("bundle_id"))) {// 校验注册标识
			return AgentResult.fail(AgentErrorCodeEnum.sign_error);
		}
		JSONArray array = payData.getJSONArray("in_app");
		if (Tools.isNull(array)) {
			return AgentResult.fail(AgentErrorCodeEnum.iap_in_app_blank);
		}
		// 获取数组中最后一个商品，避免客户端发送的移除请求失败(http://www.jianshu.com/p/ebdeea271352)
		//payData = array.getJSONObject(array.size() - 1);
		/*
		if (!Tools.formatString(productId).equals(payData.getString("product_id"))) {// 校验商品标识
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		*/
		//动态配值产品号前缀，只比对后缀
		/*
		String product_Id = payData.getString("product_id");
		product_Id = Tools.isNotNull(product_Id)?product_Id:"";
		long initialPrice = entity.getInitial_price()/100;
		if (!product_Id.endsWith(initialPrice+"")) {// 校验商品标识
			logger.info("product_id_error:payid:"+entity.getId()+";product_Id="+product_Id+";initialPrice="+initialPrice);
			return AgentResult.fail(AgentErrorCodeEnum.IPA_product_id_error);
		}
		*/
		boolean tradeFlag = false;
		String product_Id = "";
		long purchase_date_ms = 0;
		if(Tools.isNotNull(entity.getTrade_no())){
			for(int i=0;i<array.size();i++){
				JSONObject re = array.getJSONObject(i);
				if(re.getString("transaction_id").equals(entity.getTrade_no())){
					product_Id = re.getString("product_id");
					purchase_date_ms = re.getLongValue("purchase_date_ms");
					tradeFlag = true;
				}
			}
			if(Tools.isNotNull(entity.getTrade_no()) && !tradeFlag){
				return AgentResult.fail(AgentErrorCodeEnum.IPA_transaction_id_error);
			}
			long initialPrice = entity.getInitial_price()/100;
			if (!product_Id.endsWith("_"+initialPrice)) {// 校验商品标识
				logger.info("product_id_error:payid:"+entity.getId()+";product_Id="+product_Id+";initialPrice="+initialPrice);
				return AgentResult.fail(AgentErrorCodeEnum.IPA_product_id_error);
			}
		}
		entity.setBuyer_email("未知");
		entity.setBuyer_id("未知");
		entity.setPay_time(purchase_date_ms>0?new Date(purchase_date_ms):null);
		//entity.setTrade_no();
		entity.setStatus(1);
		entity.setTrade_status("success");
		// 调用回调事务
		try {
			payCallback(entity);
		} catch (Exception e) {
			entity.setStatus(-1);
			entity.setTrade_status("[苹果]调用接口出错");
			logger.info("[苹果]调用接口出错", e);
			userPayActionContract.update(entity);
			return AgentResult.fail();
		}
		
		entity.setTimes(getTimes(entity.getUser_id()));
		userPayActionContract.update(entity);
		return AgentResult.success();
	}
	
	private Integer getTimes(Long user_id) throws Exception{
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("user_id", user_id));
		pageModel.addQuery(Restrictions.eq("status", 1));
		long times= userPayActionContract.count(pageModel);
		return (int) (times+1);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult payWithBal(PayAccessDto dto) throws Exception {
		UserPayActionEntity entity = payAction(dto);
		AgentResult result = userBalanceAccountAgent.changeAccount(entity.getUser_id(), entity.getMoney(), UserBalanceAccountLogTypeEnum.create_order,
				String.valueOf(entity.getId()), entity.getDescription());
		if (0 == result.getCode()) {
			payCallback(entity);
			entity.setPay_time(new Date());
			entity.setStatus(1);
			entity.setTimes(getTimes(entity.getUser_id()));
			userPayActionContract.update(entity);
			
			return AgentResult.success(PayPrepayResultDto.create(String.valueOf(result.getData()), entity.getId()));
		}
		return result;
	}
	
	@Override
	public void notifyPayCenter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		
		Map<String , String> params = Maps.newHashMap();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			
			params.put(name, value);
		}
		
		loggerAgent.payParamsLogger(null, "PayCenter-NOTIFY", true, params);
		String sign = PayCenterSignHelper.sign(params, PayCenterConfig.SECRET);
		logger.info("PayCenter sign = {} , receive sign = {}" , sign , params.get("sign"));
		
		if(!sign.equals(params.get("sign"))) {
			logger.info("签名验签错误！");
			out.write("success");
			return;
		}
		ApiResult<?> verifyResult = payCenterClient.verifyOrder(params.get("notify_id"));
		if(verifyResult.getCode() != PayCenterCodeEnums.SUCCESS.getCode()) {
			logger.info("校验未通过，原因："+verifyResult.getCodemsg());
			out.write("success");
			return;
		}
		if(!params.containsKey("result_code")) {
			logger.info("未获取到返回码信息！");
			out.write("success");
			return;
		}
		int return_code = Tools.parseInt(params.get("result_code"));
		if(return_code != PayCenterCodeEnums.SUCCESS.getCode()) {
			logger.info(params.get("result_code_des"));
			out.write("success");
			return;
		}
		String out_trade_no = params.get("out_order_no");
		UserPayActionEntity entity = userPayActionContract.findById(Tools.parseLong(out_trade_no));
		if(entity == null) {
			logger.info("订单号[" + out_trade_no + "]不存在");
			out.write("success");
			return;
		}

		entity.setUpdate_time(new Date());
		if (entity.getMoney().longValue() != Tools.parseDouble(params.get("total_amount"))) {
			logger.info("支付金额不一致！");
			entity.setStatus(-1);
			entity.setTrade_status("支付金额不一致！");
			userPayActionContract.update(entity);
			out.write("success");
			return;
		}

		entity.setBuyer_email(Tools.EMPTY_STRING);
		entity.setBuyer_id("PayCenter");
		entity.setPay_time(Tools.getDateTime(params.get("pay_time")));
		entity.setTrade_no(params.get("order_no"));
		if (0 == entity.getStatus()) {// 避免重复支付
			entity.setStatus(1);
			entity.setTrade_status("支付成功");
			// 调用回调事务
			try {
				payCallback(entity);
			} catch (Exception e) {
				entity.setStatus(-1);
				entity.setTrade_status("[支付宝]调用接口出错");
				logger.info("[支付宝]调用接口出错", e);
			}
			if (entity.getStatus()==1) {
				entity.setTimes(getTimes(entity.getUser_id()));
			}
			userPayActionContract.update(entity);
		}
		out.write("success");
	}

	@Override
	public void notifyFromAli(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		PrintWriter out = response.getWriter();
		Map<String, String[]> requestParams = request.getParameterMap();
		if (Tools.isNotNull(requestParams)) {
			for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
				String name = entry.getKey();
				String[] values = entry.getValue();
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
		}
		loggerAgent.payParamsLogger(null, "ALI-NOTIFY", true, params);
		if (AlipayNotify.verify(params)) {
			String out_trade_no = params.get("out_trade_no");
			UserPayActionEntity entity = userPayActionContract.findById(Tools.parseLong(out_trade_no));
			if (Tools.isNotNull(entity)) {
				entity.setUpdate_time(new Date());
				entity.setBuyer_email(params.get("buyer_email"));
				entity.setBuyer_id(params.get("buyer_id"));
				entity.setPay_time(Tools.getDateTime(params.get("gmt_payment")));
				entity.setTrade_no(params.get("trade_no"));
				if (entity.getMoney() / 100.0 != Tools.parseDouble(params.get("total_fee"))) {
					logger.info("支付金额不一致！");
					entity.setStatus(-1);
					entity.setTrade_status("支付金额不一致！");
					userPayActionContract.update(entity);
					out.write("success");
					return;
				}
				if (!AlipayConfig.SELLER_ID.equalsIgnoreCase(params.get("seller_id"))) {
					logger.info("受理商户不一致！");
					entity.setStatus(-1);
					entity.setTrade_status("受理商户不一致！");
					userPayActionContract.update(entity);
					out.write("success");
					return;
				}
				String trade_status = params.get("trade_status");
				if (AlipayConfig.TRADE_SUCCESS.equalsIgnoreCase(trade_status) || AlipayConfig.TRADE_FINISHED.equalsIgnoreCase(trade_status)) {
					if (0 == entity.getStatus()) {// 避免重复支付
						entity.setStatus(1);
						entity.setTrade_status(trade_status);
						// 调用回调事务
						try {
							payCallback(entity);
						} catch (Exception e) {
							entity.setStatus(-1);
							entity.setTrade_status("[支付宝]调用接口出错");
							logger.info("[支付宝]调用接口出错", e);
						}
						if (entity.getStatus()==1) {
							entity.setTimes(getTimes(entity.getUser_id()));
						}
						userPayActionContract.update(entity);
					}
					out.write("success");
				}
			} else {
				logger.info("订单号[" + out_trade_no + "]不存在");
				out.write("success");
			}
		} else {
			logger.info("签名验签错误！");
			out.write("success");
		}
	}

	
	@Override
	public void notifyFromAliH5(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		PrintWriter out = response.getWriter();
		Map<String, String[]> requestParams = request.getParameterMap();
		if (Tools.isNotNull(requestParams)) {
			for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
				String name = entry.getKey();
				String[] values = entry.getValue();
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
		}
		loggerAgent.payParamsLogger(null, "ALIH5-NOTIFY", true, params);
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayH5Config.ALIPAY_PUBLIC_KEY, AlipayH5Config.CHARSET, "RSA2");
		logger.info("ALIH5-NOTIFY:parames="+JsonHelper.toJson(params)+";verify_result="+verify_result);
		if (verify_result) {
			String out_trade_no = params.get("out_trade_no");
			UserPayActionEntity entity = userPayActionContract.findById(Tools.parseLong(out_trade_no));
			if (Tools.isNotNull(entity)) {
				entity.setUpdate_time(new Date());
				entity.setBuyer_email(params.get("buyer_logon_id"));
				entity.setBuyer_id(params.get("buyer_id"));
				entity.setPay_time(Tools.getDateTime(params.get("gmt_payment")));
				entity.setTrade_no(params.get("trade_no"));
				if (entity.getMoney() / 100.0 != Tools.parseDouble(params.get("total_amount"))) {
					logger.info("支付金额不一致！");
					entity.setStatus(-1);
					entity.setTrade_status("支付金额不一致！");
					userPayActionContract.update(entity);
					out.write("success");
					return;
				}
				if (!AlipayConfig.SELLER_ID.equalsIgnoreCase(params.get("seller_id"))) {
					logger.info("受理商户不一致！");
					entity.setStatus(-1);
					entity.setTrade_status("受理商户不一致！");
					userPayActionContract.update(entity);
					out.write("success");
					return;
				}
				String trade_status = params.get("trade_status");
				if (AlipayH5Config.TRADE_SUCCESS.equalsIgnoreCase(trade_status) || AlipayH5Config.TRADE_FINISHED.equalsIgnoreCase(trade_status)) {
					if (0 == entity.getStatus()) {// 避免重复支付
						entity.setStatus(1);
						entity.setTrade_status(trade_status);
						// 调用回调事务
						try {
							payCallback(entity);
						} catch (Exception e) {
							entity.setStatus(-1);
							entity.setTrade_status("[支付宝H5]调用接口出错");
							logger.info("[支付宝H5]调用接口出错", e);
						}
						if (entity.getStatus()==1) {
							entity.setTimes(getTimes(entity.getUser_id()));
						}
						userPayActionContract.update(entity);
					}
					out.write("success");
				}
			} else {
				logger.info("订单号[" + out_trade_no + "]不存在");
				out.write("success");
			}
		} else {
			logger.info("H5签名验签错误！"+JsonHelper.toJson(params));
			out.write("success");
		}
	}
	
	
	@Override
	public void notifyFromWx(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> data = WxpayUtil.parseToMap(IOUtils.read(request.getReader()));
		loggerAgent.payParamsLogger(null, "WX-NOTIFY", true, data);
		PrintWriter out = response.getWriter();
		if (Tools.isNull(data)) {
			logger.info("wx notify params is null");
			out.write(WxpayHelper.no(loggerAgent, "异步通知参数为空"));
		} else {
			String return_code = data.get("return_code");
			if (Tools.isNull(return_code)) {
				logger.info("wx notify [return_code] is null");
				out.write(WxpayHelper.no(loggerAgent, "异步通知参数[return_code]为空"));
			} else {
				if (WxpayConfig.SUCCESS.equalsIgnoreCase(return_code)) {
					if (WxpayHelper.verify(data)) {
						String out_trade_no = data.get("out_trade_no");
						UserPayActionEntity entity = userPayActionContract.findById(Tools.parseLong(out_trade_no));
						if (Tools.isNotNull(entity)) {
							entity.setUpdate_time(new Date());
							entity.setBuyer_email("未知");
							entity.setBuyer_id(data.get("openid"));
							entity.setPay_time(Tools.getDateTime(data.get("time_end"), "yyyyMMddHHmmss"));
							entity.setTrade_no(data.get("transaction_id"));
							if (0 == entity.getStatus()) {// 避免重复支付
								if (WxpayConfig.SUCCESS.equalsIgnoreCase(data.get("result_code"))) {
									entity.setStatus(1);
									entity.setTrade_status(WxpayConfig.SUCCESS);
									// 调用回调事务
									try {
										payCallback(entity);
									} catch (Exception e) {
										entity.setStatus(-1);
										entity.setTrade_status("[微信]调用接口出错");
										logger.info("[微信]调用接口出错", e);
									}
								} else {
									entity.setStatus(-1);
									entity.setTrade_status(WxpayConfig.FAIL);
								}
								if (entity.getStatus()==1) {
									entity.setTimes(getTimes(entity.getUser_id()));
								}
								userPayActionContract.update(entity);
							}
							out.write(WxpayHelper.yes(loggerAgent));
						} else {
							logger.info("订单号[" + out_trade_no + "]不存在");
							out.write(WxpayHelper.no(loggerAgent, "订单号[" + out_trade_no + "]不存在"));
						}
					} else {
						logger.info(AgentErrorCodeEnum.pay_sign_check_fail.getDesc());
						out.write(WxpayHelper.no(loggerAgent, AgentErrorCodeEnum.pay_sign_check_fail.getDesc()));
					}
				}
			}
		}
	}

	/**
	 * 支付回调
	 * 
	 * @param entity
	 * @throws Exception
	 */
	private void payCallback(UserPayActionEntity entity) throws Exception {
		try {
			if (entity.getApp_channel().equals("Huawei_yoyo3")||entity.getApp_channel().equals("shenghe_wxb")) {
				CopyUserPayActionEntity CopyUserPayAction = new CopyUserPayActionEntity();
				BeanUtils.copyBean(entity,CopyUserPayAction);
				CopyUserPayAction.setStatus(1);
				copyUserPayActionContract.insert(CopyUserPayAction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringBeanApplicationContext.getBean(PayTypeEnum.getByCode(entity.getType()).name(), NotifyCallback.class).notifyData(entity);
	}

	/**
	 * 支付请求记录
	 * 
	 * @return
	 * @throws Exception
	 */
	private UserPayActionEntity payAction(PayAccessDto dto) throws Exception {
		Date date = new Date();
		UserPayActionEntity entity = null;
		PageModel pm = PageModel.getLimitModel(0, 1);
		pm.addQuery(Restrictions.eq("order_id", dto.getOrder_id()));
		pm.addQuery(Restrictions.eq("type", dto.getType().getCode()));
		List<UserPayActionEntity> list = userPayActionContract.load(pm);
		if(Tools.isNotNull(list)){ 
			entity = list.get(0);
			if(0 != entity.getStatus()){
				throw new Exception("pay action status error !");
			}
			entity.setUpdate_time(date);
			entity.setNickname(Tools.formatString(dto.getNickname()));
			entity.setMobile(Tools.formatString(dto.getMobile()));
			entity.setApp_type(dto.getApp_type());
			entity.setApp_version(dto.getApp_version());
			entity.setPay_channel(dto.getPay_channel().getCode());
			userPayActionContract.update(entity);
		}else{
			entity = new UserPayActionEntity();
			entity.setCreate_time(date);
			entity.setUpdate_time(date);
			entity.setUser_id(dto.getUser_id());
			entity.setNickname(Tools.formatString(dto.getNickname()));
			entity.setMobile(Tools.formatString(dto.getMobile()));
			entity.setApp_type(dto.getApp_type());
			entity.setApp_channel(dto.getApp_channel());
			entity.setApp_version(dto.getApp_version());
			entity.setOrder_id(dto.getOrder_id());
			entity.setInitial_price(dto.getInitialPrice());
			entity.setSubject(dto.getSubject());
			entity.setDescription(dto.getDescription());
			entity.setMoney(dto.getMoney());
			entity.setPay_channel(dto.getPay_channel().getCode());
			entity.setType(dto.getType().getCode());
			entity.setStatus(0);
			// 苹果支付此值必须设置，防止重复重复提交//
			entity.setIap_receipt_md5(MD5.encode(dto.getReceipt()));
			entity.setApp_package(dto.getPackage_name());
			if(PayChannelEnum.iap.getCode() == dto.getPay_channel().getCode()){
				entity.setTrade_no(dto.getIpaTransactionId());
			}
			
			userPayActionContract.insert(entity);
		}
		return entity;
	}

	@Override
	public Long ensureOrderViaReceipt(String md5Receipt) throws Exception {
		if(null == md5Receipt){
			return null;
		}
		UserPayActionEntity entity = userPayActionContract.findByProperty("iap_receipt_md5", md5Receipt);
		return Tools.isNull(entity) ? null : entity.getOrder_id();
	}
}
