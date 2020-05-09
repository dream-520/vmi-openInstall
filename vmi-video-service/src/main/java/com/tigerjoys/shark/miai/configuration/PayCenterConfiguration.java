package com.tigerjoys.shark.miai.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tigerjoys.nbs.common.http.HttpClientProperties;
import com.tigerjoys.shark.pay.client.IPayCenterClient;
import com.tigerjoys.shark.pay.client.PayCenterClientFactoryBean;

/**
 * 支付中心配置
 * @author chengang
 *
 */
@Configuration
public class PayCenterConfiguration {
	
	/**
	 * APP ID
	 */
	@Value("${pay.center.appid}")
	private String appid;
	
	/**
	 * 密钥
	 */
	@Value("${pay.center.secret}")
	private String secret;
	
	/**
	 * 接口请求地址
	 */
	@Value("${pay.center.url}")
	private String url;
	
	/**
	 * HttpClient 属性设置
	 * @return HttpClientProperties
	 */
	@Bean
	public HttpClientProperties httpClientProperties() {
		return new HttpClientProperties();
	}
	
	/**
	 * 支付中心客户端工厂
	 * @return PayCenterClientFactoryBean
	 */
	@Bean
	public PayCenterClientFactoryBean payCenterClientFactoryBean() {
		PayCenterClientFactoryBean factoryBean = new PayCenterClientFactoryBean();
		factoryBean.setAppid(appid);
		factoryBean.setSecretKey(secret);
		factoryBean.setPayCenterURL(url);
		factoryBean.setHttpClientProperties(httpClientProperties());
		
		return factoryBean;
	}
	
	/**
	 * 支付中心客户端
	 * @return IPayCenterClient
	 * @throws Exception 
	 */
	@Bean
	public IPayCenterClient payCenterClient() throws Exception {
		PayCenterClientFactoryBean factoryBean = payCenterClientFactoryBean();
		
		return factoryBean.getObject();
	}
	
}
