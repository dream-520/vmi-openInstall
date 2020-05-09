package com.tigerjoys.shark.miai.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoLog;
import com.tigerjoys.nbs.web.logs.LoggerUtil;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

/**
 * 系统相关的接口
 * @author chengang
 *
 */
@FilterHeader
@Controller
public class SystemController extends BaseController {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * 404找不到页面
	 * @return String
	 */
	@NoLog
	@RequestMapping("/404")
	public String error_404(HttpServletRequest request , HttpServletResponse response , Model model){
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return errorModel("找不到页面", model);
	}
	
	/**
	 * 500出错页面
	 * @return String
	 */
	@NoLog
	@RequestMapping("/500")
	public String error_500(HttpServletRequest request , HttpServletResponse response , Model model){
		LOGGER.info(Tools.getReferer(request));
		
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return errorModel("系统异常", model);
	}
	
	/*
	 * 出错页面
	 * @return String
	 */
	/*@NoLog
	@RequestMapping("/error")
	public String error(HttpServletRequest request , HttpServletResponse response , Model model){
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return errorModel(request.getParameter("s"), model);
	}*/
	
	/**
	 * 错误页面模版
	 * @param msg - 错误信息
	 * @param model - Model
	 * @return String
	 */
	private String errorModel(String msg , Model model){
		if(Tools.isNull(msg)) msg = "系统异常";
		
		model.addAttribute("msg", msg);
		return "error_page";
	}
	
	/**
	 * 应用健康检测
	 * @param response - HttpServletResponse
	 * @return ActionResult
	 * @throws Exception 
	 */
	@FilterHeader
	@RequestMapping("/api/monitor")
	public void health(HttpServletResponse response) throws Exception {
		logger.info("===============Monitor OK================");
		
		response.getWriter().print("OK");
	}
	
	/**
	 * 动态修改日志级别
	 * @param name - 日志名称
	 * @param level - 日志级别
	 * @return ActionResult
	 * @throws Exception
	 */
	@RequestMapping(value="/api/dynamic/logger/{name}/{level}")
	public @ResponseBody ActionResult dynamicLoggerLevel(@PathVariable("name") String name , @PathVariable("level") String level) throws Exception {
		if(Tools.isNull(name) || Tools.isNull(level)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_isnull);
		}
		if(name.equals(LoggerUtil.hitLogger) || name.equals(LoggerUtil.exceptLogger)) {
			return ActionResult.fail(ErrorCodeEnum.have_no_permission);
		}
		
		try {
			LoggerContext loggerContext= (LoggerContext)LoggerFactory.getILoggerFactory();
			Logger logger = loggerContext.getLogger(name);
			if(logger == null) {
				return ActionResult.fail(ErrorCodeEnum.db_error);
			}
			
			logger.setLevel(Level.toLevel(level.toUpperCase()));
			
			return ActionResult.success();
		} catch (Exception e) {
			logger.error(e.getMessage() , e);
			
			return ActionResult.fail();
		}
	}

}
