package com.tigerjoys.shark.miai.agent.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;

import com.tigerjoys.nbs.common.utils.RSA;

/* *
 *类名：AlipaySubmit
 *功能：支付宝各接口请求提交类
 *详细：构造支付宝各接口表单HTML文本，获取远程HTTP数据
 *版本：3.3
 *日期：2012-08-13
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayBuilder {
    
	
    /**
     * 生成签名结果(请求支付宝)
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(LinkedHashMap<String, String> sPara) {
    	String prestr = AlipayHelper.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(AlipayConfig.SIGN_TYPE.equals("RSA") ){
        	try {
				mysign = URLEncoder.encode(RSA.sign(prestr, AlipayConfig.PRIVATE_KEY, AlipayConfig.INPUT_CHARSET),AlipayConfig.INPUT_CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    public static LinkedHashMap<String, String> buildRequestPara(LinkedHashMap<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
    	LinkedHashMap<String, String> sPara = AlipayHelper.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);
        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", AlipayConfig.SIGN_TYPE);
        return sPara;
    }
}
