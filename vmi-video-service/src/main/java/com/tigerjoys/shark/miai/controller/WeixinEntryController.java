package com.tigerjoys.shark.miai.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shark.miai.common.enums.PlatformEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tigerjoys.nbs.common.ApiResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.WebConst;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.utils.WebHelper;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.UserStatusEnum;
import com.tigerjoys.shark.miai.dto.service.RegLoginDto;
import com.tigerjoys.shark.miai.enums.WeixinErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IRegLoginService;
import com.tigerjoys.shark.miai.utils.Helper;
import com.tigerjoys.shark.miai.utils.WeixinUtils;

/**
 * 微信入口
 * @author chengang
 *
 */
@Controller
@RequestMapping(value = "/wx/entry", produces=Produce.TEXT_HTML)
public class WeixinEntryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinEntryController.class);
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IRegLoginService regLoginService;

	/**
	 * 微信入口地址
	 * @param request - HttpServletRequest
	 * @param model - Model
	 * @return String
	 * @throws Exception 
	 */
	@FilterHeader
	@RequestMapping(value = "/index", produces=Produce.TEXT_HTML)
	public String entryIndex(HttpServletRequest request, Model model) throws Exception {
		//获取头信息
		String encrypt = WebHelper.getCookie(request, WebConst.web_login_cookie);
		if(Tools.isNotNull(encrypt)) {
			try {
				LOGGER.info(request.getRequestURI()+",encrypt:"+encrypt);
				encrypt = URLDecoder.decode(encrypt, ECharset.UTF_8.getName());
				String decodeValues = AESCipher.aesDecryptString(encrypt);
				LOGGER.info(request.getRequestURI()+",decode:"+decodeValues);
				RequestHeader header = JsonHelper.toObject(decodeValues, RequestHeader.class);
				if(header != null) {
					//用户ID
					long userid = header.getUserid();
					if(userid > 0) {
						//此处验证用户信息
						UserBO user = userAgent.findById(userid);
						if(user != null) {
							if(user.getStatus() == UserStatusEnum.disable.getCode()) {
								model.addAttribute("msg", "该用户已被查封");
								return "error_h5";
							}
							//此处要验证token是否一致，并且token没有过期
							//这里跳转到首页
							if(user.getUniqueKey().equals(header.getToken()) && header.getOs_type() == PlatformEnum.H5.type && System.currentTimeMillis() - user.getLastLoginDate().getTime() <= Const.USER_TOKEN_EXPIRE_MILLIS) {
								//强制设置头信息
								RequestUtils.getCurrent().setHeader(header);
								RegLoginDto loginDto = regLoginService.autoLogin(header.getToken(),Integer.valueOf(0));
								if(loginDto != null && loginDto.getToken().equals(header.getToken())) {
									return "redirect:/wx/index/anchor";
								}
							}
						}
					}
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		//此处生成URL跳转微信
		return "redirect:" + WeixinUtils.getAuthorizeUrl("/wx/entry/getopenid", 0);
	}
	
	/**
	 * 微信获取openid回调地址
	 * @param code - 微信code码
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @param model - Model
	 * @return String
	 * @throws Exception 
	 */
	@FilterHeader
	@RequestMapping(value = "/getopenid", produces=Produce.TEXT_HTML)
	public String getOpenid(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//根据授权码获取页面
		ApiResult<String> result = WeixinUtils.getOpenidByCode(code);
		if(result.getCode() != WeixinErrorCodeEnum.SUCCESS.getCode()) {
			model.addAttribute("msg", result.getCodemsg());
			return "error_h5";
		}
		
		String openid = result.getData();
		LOGGER.info("openid = {}" , openid);
		
		//此处需要判断用户是否已注册，如果用户未注册，则注册一个用户，否则执行登录操作
		UserBO user = regLoginService.WeixinH5Login(openid);
		
		String encrypt = Helper.createWeixinH5RequestHeaderEncrypt(user, openid);

		//写入cookie
		Cookie userIdCookie = new Cookie(WebConst.web_login_cookie, URLEncoder.encode(encrypt, ECharset.UTF_8.getName()));
		userIdCookie.setPath("/");
		userIdCookie.setMaxAge((int)(Tools.MONTH_MILLIS/1000));
		response.addCookie(userIdCookie);
		
		//跳转到首页
		return "redirect:/wx/index/anchor";
	}

}
