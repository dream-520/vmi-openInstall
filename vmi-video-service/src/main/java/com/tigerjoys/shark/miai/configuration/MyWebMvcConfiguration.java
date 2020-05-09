package com.tigerjoys.shark.miai.configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.utils.validate.ValidateUtils;
import com.tigerjoys.nbs.web.utils.MyObjectMapper;
import com.tigerjoys.shark.miai.interceptors.WebControllerInterceptor;

/**
 * MVC配置类
 * 
 * @author chengang
 *
 */
@EnableWebMvc
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {
	
	/**
	 * 前台拦截器
	 * @return WebControllerInterceptor
	 */
	@Bean
	WebControllerInterceptor getInterceptor() {
		return new WebControllerInterceptor();
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/favicon.ico","/res/**","/html/**");
    }
	
	/**
	 * 设置springboot 的 方法参数注解验证
	 * @return MethodValidationPostProcessor
	 */
	@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        /**设置validator模式为快速失败返回*/
        postProcessor.setValidator(ValidateUtils.getFailFastValidator());
        return postProcessor;
    }
	
	/**
	 * 设置spring mvc默认的validator
	 * @return Validator
	 */
	@Override
	public Validator getValidator() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages","classpath:org/hibernate/validator/ValidationMessages");
		messageSource.setUseCodeAsDefaultMessage(false);
		messageSource.setDefaultEncoding(ECharset.UTF_8.getName());
		//此值代表是否定时刷新资源文件，-1不刷新，单位秒
		messageSource.setCacheSeconds(-1);
		
		CustomValidatorBean validator = new CustomValidatorBean();
		validator.setValidatorFactory(ValidateUtils.getDefaultValidatorFactory());
		
		validator.setMessageInterpolator(new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource)));
		
		return validator;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/res/favicon.ico");
		registry.addResourceHandler("/res/**").addResourceLocations("classpath:/res/");
		registry.addResourceHandler("/html/**").addResourceLocations("classpath:/html/").setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic());
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.clear();
		
		MyObjectMapper objectMapper = new MyObjectMapper();
		
		//放到第一个，编解码器
		converters.add(new AESJackson2HttpMessageConverter(objectMapper));
        //初始化jackson2
        converters.add(new MappingJackson2HttpMessageLogConverter(objectMapper));
		//普通的字符串
        converters.add(new StringHttpMessageConverter());
	}
	
}
