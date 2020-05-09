package com.tigerjoys.shark.miai.configuration;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.MediaTypes;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.WebConst;
import com.tigerjoys.nbs.web.WebErrorCodeEnum;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.logs.HitLogger;
import com.tigerjoys.nbs.web.logs.LoggerUtil;
import com.tigerjoys.nbs.web.utils.ControllerValidHelper;

/**
 * 系统异常类
 * @author chengang
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	private final static Logger EXCEPT_LOGGER = LoggerUtil.getLogger(LoggerUtil.exceptLogger);

	@ExceptionHandler(Exception.class)
	public ModelAndView globalException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Throwable ex) {
		Method method = null;
		
		if(handlerMethod != null) {
			method = handlerMethod.getMethod();
		}
		
		if(ex != null) {
			StringBuilder buf = new StringBuilder("拦截器拦截到异常，class:");
			buf.append(handlerMethod!=null?handlerMethod.getBeanType():null);
			buf.append(",method:").append(method!=null?method.getName():null);
			buf.append(",url:").append(request.getRequestURI());
			String request_header_encrypt = request.getHeader(WebConst.request_header_encrypt);
			if(Tools.isNotNull(request_header_encrypt)){
				buf.append(",header_encrypt:").append(request_header_encrypt);
			}
			//记录request日志
			if(handlerMethod != null) EXCEPT_LOGGER.info(HitLogger.makeRequestLog().toString());
			EXCEPT_LOGGER.info(buf.toString() , ex);
		}
		
		try {
			if(handlerMethod == null) {
				return ActionResult.errorModel(response , WebErrorCodeEnum.error.getDesc());
			}
			
			//此处判断异常是接口的异常还是普通的页面异常
			
			boolean isSign = false;
			if(AnnotationUtils.findAnnotation(method, FilterHeader.class) == null &&
				AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), FilterHeader.class) == null) {
				isSign = ControllerValidHelper.validSign(method, handlerMethod.getBeanType());
			}
			
			Class<?> returnType = handlerMethod.getMethod().getReturnType();
			if(returnType != null && returnType.equals(ActionResult.class)) {//json
				return writerError(response, isSign, WebErrorCodeEnum.error.getCode());
			}
			return ActionResult.errorModel(response , WebErrorCodeEnum.error.getDesc());
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return ActionResult.errorModel(response , WebErrorCodeEnum.error.getDesc());
	}
	
	/**
     * 捕捉404异常,这个方法只在配置
     * spring.mvc.throw-exception-if-no-handler-found=true来后起作用
     * 
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNotFound(HttpServletRequest request , HttpServletResponse response , NoHandlerFoundException e) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("forward:/404");
        return mav;
    }
    
    /**
	 * 输出错误信息
	 * @param response - HttpServletResponse
	 * @param isSign - 是否加密
	 * @param code - 错误码
	 * @param ModelAndView
	 * @throws IOException
	 */
	private ModelAndView writerError(HttpServletResponse response , boolean isSign , int code) throws Exception {
		String err = JsonHelper.toJson(ActionResult.fail());
		
		if(isSign) response.setContentType(MediaTypes.TEXT_ENCODE_VALUE);
		else response.setContentType(MediaTypes.APPLICATION_JSON_VALUE);
		
		return ActionResult.msgModel(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR , isSign?AESCipher.aesEncryptString(err):err);
	}

}
