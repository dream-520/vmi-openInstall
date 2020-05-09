package com.tigerjoys.shark.miai.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.shark.miai.agent.pay.AlipayH5Config;


@Controller
@FilterHeader
@RequestMapping(value="/web/pay",produces=Produce.TEXT_HTML)
public class AliPayH5Controller {
	
	/**
	 * 用户信用分记录页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/aliPayH5",produces=Produce.TEXT_HTML)
	public String getGameList(Model model) throws Exception{
		//   http://127.0.0.1:8090/web/pay/aliPayH5
		//https://openclub.alipay.com/read.php?tid=1909&fid=25
		 // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	    //调用RSA签名方式
	    AlipayClient client = new DefaultAlipayClient(AlipayH5Config.URL, AlipayH5Config.APPID, AlipayH5Config.RSA_PRIVATE_KEY, AlipayH5Config.FORMAT, AlipayH5Config.CHARSET, AlipayH5Config.ALIPAY_PUBLIC_KEY,AlipayH5Config.SIGNTYPE);
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    Random random = new Random();
	    // 封装请求支付信息
	    AlipayTradeWapPayModel payModel=new AlipayTradeWapPayModel();
	    payModel.setOutTradeNo("test_no1001"+random.nextInt(100));
	    payModel.setSubject("H5测试");
	    payModel.setTotalAmount("0.01");
	    payModel.setBody("充钻");
	    payModel.setTimeoutExpress("1c");
	    payModel.setProductCode("QUICK_WAP_WAY");
	    alipay_request.setBizModel(payModel);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(AlipayH5Config.notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(AlipayH5Config.return_url);   
	    
	    // form表单生产
	    String form = "";
		try {
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		model.addAttribute("content",form);
		return "wallet/aliPayH5Test";
		//return "wallet/zfbPay";
	}
	
	public static void main(String[] args) {
		  Random random = new Random();
		 AlipayClient client = new DefaultAlipayClient(AlipayH5Config.URL, AlipayH5Config.APPID, AlipayH5Config.RSA_PRIVATE_KEY, AlipayH5Config.FORMAT, AlipayH5Config.CHARSET, AlipayH5Config.ALIPAY_PUBLIC_KEY,AlipayH5Config.SIGNTYPE);
		    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
		    
		    // 封装请求支付信息
		    AlipayTradeWapPayModel payModel=new AlipayTradeWapPayModel();
		    payModel.setOutTradeNo("test_no1001"+random.nextInt(100));
		    payModel.setSubject("H5测试");
		    payModel.setTotalAmount("0.01");
		    payModel.setBody("充钻");
		    payModel.setTimeoutExpress("1c");
		    payModel.setProductCode("QUICK_WAP_WAY");
		    alipay_request.setBizModel(payModel);
		    // 设置异步通知地址
		    alipay_request.setNotifyUrl(AlipayH5Config.notify_url);
		    // 设置同步地址
		    alipay_request.setReturnUrl(AlipayH5Config.notify_url);   
		    
		    // form表单生产
		    String form = "";
			try {
				// 调用SDK生成表单
				form = client.pageExecute(alipay_request).getBody();
				
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println("from\n"+form);
	}
}
