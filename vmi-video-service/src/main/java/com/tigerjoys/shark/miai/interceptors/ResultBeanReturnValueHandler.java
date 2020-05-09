package com.tigerjoys.shark.miai.interceptors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;

/**
 * 结果封装类
 */
public class ResultBeanReturnValueHandler implements HandlerMethodReturnValueHandler  {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 类似ResponseBody，仅有添加ResponseBody注解的method才会触发
	 */
	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return (AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null
				|| returnType.getMethodAnnotation(ResponseBody.class) != null);
	}

	/**
	 * 使用统一的结果封装类ResultInfo，并序列化成json
	 */
	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		ActionResult result = (ActionResult) returnValue;
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		response.addHeader("Content-Type", "application/json;charset=UTF-8");
		response.getWriter().append(JsonHelper.toJson(result));
		logger.info("handleReturnValue:"+JsonHelper.toJson(result));
	}

}
