package com.tigerjoys.shark.miai.utils;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ApiResult;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.dto.service.WeixinAccessTokenDto;
import com.tigerjoys.shark.miai.dto.service.WeixinUserInfo;
import com.tigerjoys.shark.miai.enums.WeixinErrorCodeEnum;

/**
 * 微信相关的工具类
 * @author chengang
 *
 */
public final class WeixinUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUtils.class);
	
	public static final String WX_API_URL = "https://api.weixin.qq.com";
	
	/**
	 * 授权code调用地址
	 */
	public static final String WX_OAUTH2_ACCESS_TOKEN_URL = WX_API_URL + "/sns/oauth2";
	
	/**
	 * 根据项目不同的环境配置，组合授权URL
	 * @param path - 本项目的相对路径，如/getOpenid
	 * @param scope - 0静默授权,1手动授权[获取用户信息]
	 * @return String
	 * @throws Exception
	 */
	public static String getAuthorizeUrl(String path , int scope) throws Exception {
		return getAuthorizeUrl(Const.WEB_SITE, path , scope);
	}
	
	/**
	 * 组合授权URL
	 * @param domainUrl - 域名地址
	 * @param path - 本项目的相对路径，如/getOpenid
	 * @param scope - 0静默授权,1手动授权[获取用户信息]
	 * @return String
	 * @throws Exception 
	 */
	public static String getAuthorizeUrl(String domainUrl , String path , int scope) throws Exception {
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Const.WEIXIN_HTML_APPID+"&redirect_uri="+URLEncoder.encode(domainUrl+path, ECharset.UTF_8.getName())+"&response_type=code&scope="+(scope==0?"snsapi_base":"snsapi_userinfo")+"&state=STATE#wechat_redirect";
	}
	
	/**
	 * 拼装获取AccessToken的URL地址
	 * @param code - String
	 * @return String
	 */
	private static String getAccessTokenUrl(String code) {
		return WX_OAUTH2_ACCESS_TOKEN_URL + "/access_token?appid="+Const.WEIXIN_HTML_APPID+"&secret="+Const.WEIXIN_HTML_APPSECRET+"&code="+code+"&grant_type=authorization_code";
	}
	
	/**
	 * 根据code获取openid
	 * @param code - String
	 * @return ApiResult<String>
	 */
	public static ApiResult<String> getOpenidByCode(String code) {
		String url = getAccessTokenUrl(code);
		LOGGER.info("accessToken url = {}" , url);
		
		try {
			ApiResult<JSONObject> result = requestUrl(url, JSONObject.class);
			if(result.getCode() == WeixinErrorCodeEnum.SUCCESS.getCode()) {
				return ApiResult.success(result.getData().getString("openid"));
			} else {
				return ApiResult.fail(result);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return ApiResult.fail();
	}
	
	/**
	 * 根据Code获取AccessToken等信息
	 * @param code - String
	 * @return ApiResult<WeixinAccessTokenDto>
	 */
	public static ApiResult<WeixinAccessTokenDto> getAccessTokenByCode(String code){
		String url = getAccessTokenUrl(code);
		LOGGER.info("accessToken url = {}" , url);
		
		try {
			return requestUrl(url, WeixinAccessTokenDto.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return ApiResult.fail();
	}
	
	/**
	 * 通过refreshToken刷新用户的Token有效期
	 * @param refreshToken - String
	 * @return ApiResult<WeixinAccessTokenDto>
	 */
	public static ApiResult<WeixinAccessTokenDto> refreshToken(String refreshToken) {
		String url = WX_OAUTH2_ACCESS_TOKEN_URL + "/refresh_token?appid="+Const.WEIXIN_HTML_APPID+"&grant_type=refresh_token&refresh_token=" + refreshToken;
		LOGGER.info("refreshToken url = {}" , url);
		
		try {
			return requestUrl(url, WeixinAccessTokenDto.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return ApiResult.fail();
	}
	
	/**
	 * 根据openID获取用户的信息
	 * @param openId - String
	 * @return ApiResult<WeixinUserInfo>
	 */
	public static ApiResult<WeixinUserInfo> getUserInfo(String openId){
		String url = WX_API_URL + "/sns/userinfo?access_token=ACCESS_TOKEN&openid="+openId+"&lang=zh_CN";
		LOGGER.info("userInfo url = {}" , url);
		
		try {
			return requestUrl(url, WeixinUserInfo.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return ApiResult.fail();
	}
	
	/**
	 * 请求指定的接口并返回对象信息
	 * @param url - 用户ID
	 * @param clazz - Class
	 * @return ApiResult<T>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static <T> ApiResult<T> requestUrl(String url, Class<T> clazz) throws Exception{
		ResponseStatus response = HttpUtils.get(url);
		LOGGER.info("response status code = {}" , response.getStatusCode());
		if(response.getStatusCode() == HttpStatus.SC_OK) {
			String content = response.getContent();
			LOGGER.info("response content : {}" , content);
			
			JSONObject json = JsonHelper.toJsonObject(content);
			
			int errCode = json.getIntValue("errcode");
			if(errCode > 0) {
				WeixinErrorCodeEnum ec = WeixinErrorCodeEnum.getByCode(errCode);
				if(ec != null) {
					return ApiResult.fail(ec);
				} else {
					return ApiResult.fail(errCode, json.getString("errmsg"));
				}
			} else {
				if(clazz == JSONObject.class) {
					return (ApiResult<T>) ApiResult.success(json);
				} else {
					return ApiResult.success(json.toJavaObject(clazz));
				}
			}
		}
		return ApiResult.fail();
	}
	
	private WeixinUtils() {
		
	}

}
