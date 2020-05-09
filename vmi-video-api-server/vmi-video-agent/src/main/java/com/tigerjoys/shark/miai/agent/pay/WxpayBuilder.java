package com.tigerjoys.shark.miai.agent.pay;

import java.util.Map;

import com.tigerjoys.nbs.common.utils.MD5;

/** 
 * @Description: TODO
 * @author mouzhanpeng
 * @date 2016年8月11日 下午5:28:37
 * @version 
 * @since 1.8.0
 */
public class WxpayBuilder {
	 /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = WxpayHelper.createLinkString(sPara)+"&key="+WxpayConfig.PARTNER_KEY; 
        return MD5.encode(prestr).toUpperCase();
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = WxpayHelper.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);
        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        return sPara;
    }

}
