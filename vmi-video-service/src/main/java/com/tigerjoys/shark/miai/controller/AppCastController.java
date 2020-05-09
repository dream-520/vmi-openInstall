/**
 * 
 */
package com.tigerjoys.shark.miai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IBannerContract;
import com.tigerjoys.shark.miai.inter.entity.BannerEntity;

/** 
 * ClassName: AppCastController <br/> 
 * date: 2017年5月17日 下午4:28:50 <br/> 
 * description:应用定制化接口
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
@Controller
@RequestMapping(value="/api/cast", produces=Produce.TEXT_ENCODE)
public class AppCastController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRedFlowerAgent redFlowerAgent;
	
	@Autowired
	private IBannerContract bannerContract;
	
	/**
	 * 获得app启动画面
	 */
	@RequestMapping(value="/home/start",method=RequestMethod.POST)
	public @ResponseBody ActionResult getAppHomeBanner(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		int versionId = json.getIntValue("versionId");
		try {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<BannerEntity> list = bannerContract.getBannerByGroupCode(Const.APP_AD_BANNER, 0, 1);
			if(Tools.isNotNull(list)) {
				BannerEntity banner = list.get(0);
				if(banner.getId() != versionId) {
					dataMap.put("versionId", banner.getId());
					dataMap.put("picUrl", Const.getCdn(banner.getImageurl()));
				} else {
					return ActionResult.fail(ErrorCodeEnum.error.getCode() , "版本一致，不需要拉取");
				}
			} else {
				dataMap.put("versionId", 0);
				dataMap.put("picUrl", "");
			}
			return ActionResult.success(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionResult.fail();
	}
	
	/**
	 * 城市数据升级，返回城市信息ZIP下载包
	 * @param body - 参数体
	 * @return ActionResult
	 * @throws Exception
	 */
	@RequestMapping(value="/area/upgrade")
	public @ResponseBody ActionResult getCityListToJson(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		if(!json.containsKey("version")) {
			return ActionResult.fail();
		}
		
		if(Const.CITY_CODE_VERSION <= json.getIntValue("version")) {
			return ActionResult.fail(ErrorCodeEnum.error.getCode() , "没有新版本");
		}
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("version", Const.CITY_CODE_VERSION);
		dataMap.put("url", Const.getCdn(Const.CITY_DOWNLOAD_FILE_URL));
		return ActionResult.success(dataMap);
	}
	
	/**
	 * 日常赠送小红花
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "home/toast" ,method=RequestMethod.POST)
	@ResponseBody
	public ActionResult homeToast() throws Exception{
		/*AgentResult result = redFlowerAgent.increaseDailyFlower(RequestUtils.getCurrent().getUserid(), Const.RED_FLOWERS_DAILY);
		if(AgentErrorCodeEnum.success.getCode() == result.getCode()){
			return ActionResult.success("赠送给你"+ Const.RED_FLOWERS_DAILY + "朵小红花，可以和别人聊天哦~");
		}else{
			return ActionResult.success();
		}*/
		return ActionResult.success();
	}
	
	/**
	 * 支撑其他平台身份证信息验证
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping(value = "verify/idcard" ,method=RequestMethod.GET ,produces = Produce.TEXT_JSON)
	@FilterHeader
	public void thirdSupport(HttpServletRequest req, HttpServletResponse res) throws Exception{
		res.setContentType("text/plain");
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);
		res.setHeader("Access-Control-Allow-Origin", "*");//添加跨域访问

	    String jsonpCallback = req.getParameter("callback");//客户端请求参数
	    PrintWriter out = res.getWriter();
	    try {
	        String idcard = req.getParameter("idcard");
	        String realname = req.getParameter("realname");
	        final String appKey = "15ca6c5895754833cc5196e21b4da3a1";
	        final String api = "http://op.juhe.cn/idcard/query";

	        Map<String, String> params = new HashMap<>();
	        params.put("key", appKey);
	        params.put("idcard", Tools.formatString(idcard));
	        params.put("realname", Tools.formatString(realname).trim());
	        JSONObject json = HttpUtils.post(api, params).getJsonObjectContent();
	        LOGGER.info(json.toString());
	        if(0 == json.getIntValue("error_code")){//成功
	 	        out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.success(json.getJSONObject("result"))) +")");//返回jsonp格式数据  
	        }else{
	        	 out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail(json.getIntValue("error_code"), json.getString("reason"))) +")");
	        }
	    } catch (IOException e) {
	       LOGGER.error("验证身份证出错！",e);
	       out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail()) +")");//返回jsonp格式数据
	    } finally {
	    	out.flush();
	    	out.close();
		}
	}
}
