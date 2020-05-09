/**
 * 
 */
package com.tigerjoys.shark.miai.controller;


import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shark.miai.common.cloud.upyun.Result;
import org.shark.miai.common.enums.ShortVideoStatusEnum;
import org.shark.miai.common.enums.ShortVideoTranscodeStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.common.collect.Maps;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoLog;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.ILoggerAgent;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserTariffeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.agent.pay.AlipayH5Config;
import com.tigerjoys.shark.miai.agent.pay.PayCenterConfig;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoTranscodeContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatChannelContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoTranscodeEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatChannelEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.netease.AttachMessageObservable;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;
import com.tigerjoys.shark.pay.client.PayCenterSignHelper;
import com.tigerjoys.shark.pay.client.enums.PayCenterCodeEnums;

/**
 * ClassName: NotifyDataController <br/>
 * date: 2017年8月14日 下午8:29:43 <br/>
 * 第三方异步通知接口
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */

@Controller
@FilterHeader
@RequestMapping("third/party")
public class NotifyDataController {
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IPayAgent payAgent;

	@Autowired
	private INeteaseAgent neteaseAgent;

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private ILoggerAgent loggerAgent;
	
	@Autowired
	private AttachMessageObservable attachMessageObservable;
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	@Autowired
	private IShortVideoTranscodeContract shortVideoTranscodeContract;
	
	
	@Autowired
	private IShortVideoContract shortVideoContract;
	
	@Autowired
	private IVchatChannelContract vchatChannelContract;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private IVChatTextYXService vChatTextYXService;
	
	@Autowired
	private IUserTariffeAgent userTariffeAgent;
	
	
	

	/**
	 * 支付中心异步通知
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	@UserClientService("pay")
	@RequestMapping(value = "/paycenter/notify")
	public void notifyFromPayCenter(HttpServletRequest request, HttpServletResponse response) {
		try {
			payAgent.notifyPayCenter(request, response);
		} catch (Exception e) {
			logger.warn("支付中心异步通知出错", e);
		}
	}
	
	/**
	 * 支付中心同步通知
	 * @param request - HttpServletRequest
	 * @param model - Model
	 * @return String
	 * @throws Exception
	 */
	@UserClientService("pay")
	@RequestMapping(value = "/paycenter/returnNotify")
	public String returnFromPayCenter(HttpServletRequest request, Model model) throws Exception {
		Map<String , String> params = Maps.newHashMap();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			
			params.put(name, value);
		}
		
		logger.info("PayCenter return_param:"+JsonHelper.toJson(params));
		String sign = PayCenterSignHelper.sign(params, PayCenterConfig.SECRET);
		boolean verify_result = sign.equals(params.get("sign"));
		logger.info("PayCenter return_verify:"+JsonHelper.toJson(params)+";verify:"+verify_result);
		UserPayActionEntity payAction = null;
		boolean falg = false;
		if(params.containsKey("result_code")) {
			int return_code = Tools.parseInt(params.get("result_code"));
			if(return_code == PayCenterCodeEnums.SUCCESS.getCode()) {
				payAction = userPayActionContract.findById(Long.parseLong(params.get("out_order_no")));
				if(Tools.isNotNull(payAction)){
					falg =true;
				}
			}
		}
		
		model.addAttribute("verifyResult",falg);
	
		if (falg && Tools.isNotNull(payAction)) {
			if (PayTypeEnum.recharge_vip.getCode() == payAction.getType()) {
				UserBO userBO = userAgent.findById(payAction.getUser_id());
				if (Tools.isNull(userBO.getMobile())){
					model.addAttribute("skipPath",Const.WEB_SITE+"/api/userSafe/gotoMobilePage");
					model.addAttribute("skipTitle","绑定手机号");
				} else {
					if (userTariffeAgent.getTariffeBalance(payAction.getUser_id()) >= Const.GET_PHONE_MONEY) {
						model.addAttribute("skipPath",Const.WEB_SITE+"/api/web/getTelephoneCharge");
						model.addAttribute("skipTitle","领取话费");
					} else {
						return "wallet/payFinish";
					}
					
				}
				return "wallet/payFinishBack";
			}
		}
		
		
		
		return "wallet/payFinish";
	}
	
	
	@UserClientService("pay")
	@RequestMapping(value = "/payWx/returnNotify/{orderId}" , produces = Produce.TEXT_HTML)
	public String returnPayVip(@PathVariable long orderId, Model model) throws Exception {
		
		UserPayActionEntity payAction = userPayActionContract.findById(orderId);	
		if (PayTypeEnum.recharge_vip.getCode() == payAction.getType()) {
				model.addAttribute("verifyResult",true);
				UserBO userBO = userAgent.findById(payAction.getUser_id());
				if (Tools.isNull(userBO.getMobile())){
					model.addAttribute("skipPath",Const.WEB_SITE+"/api/userSafe/gotoMobilePage");
					model.addAttribute("skipTitle","绑定手机号");
				} else {
					if (userTariffeAgent.getTariffeBalance(payAction.getUser_id()) >= Const.GET_PHONE_MONEY) {
						model.addAttribute("skipPath",Const.WEB_SITE+"/api/web/getTelephoneCharge");
						model.addAttribute("skipTitle","领取话费");
					} else {
						return "wallet/payFinish_wx";
					}
				}
				
				return "wallet/payFinishBack";
			}
		
		return "wallet/payFinish_wx";
		
	}
	
	
	/**
	 * 支付宝异步通知
	 * 
	 * @deprecated 已被支付中心替代，下一个版本删除掉
	 * @return ActionResult
	 */
	@UserClientService("pay")
	@RequestMapping(value = "/ali/notify")
	public void notifyFromAli(HttpServletRequest request, HttpServletResponse response) {
		try {
			payAgent.notifyFromAli(request, response);
		} catch (Exception e) {
			logger.warn("支付宝异步通知出错", e);
		}
	}
	
	/**
	 * 支付宝H5异步通知
	 * 
	 * @deprecated 已被支付中心替代，下一个版本删除掉
	 * @return ActionResult
	 */
	@UserClientService("pay")
	@RequestMapping(value = "/aliH5/notify")
	public void notifyFromAliH5(HttpServletRequest request, HttpServletResponse response) {
		try {
			payAgent.notifyFromAliH5(request, response);
		} catch (Exception e) {
			logger.warn("支付宝H5异步通知出错", e);
		}
	}
	
	/**
	 * 支付宝H5同步通知
	 * 
	 * @deprecated 已被支付中心替代，下一个版本删除掉
	 * @return ActionResult
	 */
	@UserClientService("pay")
	@RequestMapping(value = "/aliH5/returnNotify")
	public String returnFromAliH5(HttpServletRequest request, Model model) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		logger.info("eturnNotify_param:"+JsonHelper.toJson(params));
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayH5Config.ALIPAY_PUBLIC_KEY, AlipayH5Config.CHARSET, "RSA2");
		logger.info("eturnNotify_verify:"+JsonHelper.toJson(params)+";verify:"+verify_result);
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		UserPayActionEntity payAction = userPayActionContract.findById(Long.parseLong(out_trade_no));
		boolean falg =false;
		if(Tools.isNotNull(payAction)){
			if(payAction.getStatus() == 1){
				falg =true;
			}
		}
		model.addAttribute("verifyResult",falg);
		return "wallet/payFinish";
	}

	/**
	 * 微信支付异步通知
	 * 
	 * @deprecated 已被支付中心替代，下一个版本删除掉
	 * @return ActionResult
	 */
	@UserClientService("pay")
	@RequestMapping(value = "/wx/notify")
	public void notifyFromWx(HttpServletRequest request, HttpServletResponse response) {
		try {
			payAgent.notifyFromWx(request, response);
		} catch (Exception e) {
			logger.warn("微信支付异步通知出错", e);
		}
	}

	/**
	 * 网易聊天消息抄送
	 * 
	 * @param request
	 * @return
	 */
	@NoLog
	@RequestMapping(value = "msg/attach", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> attachNeteaseMsg(HttpServletRequest request) {
		return neteaseAgent.attachNeteaseMsg(request, json -> {
			try {
				System.err.println(json.toString());
				// *同步记录聊天消息* //
				loggerAgent.chatMessageLogger(json.toString());
				//处理视频录制  统计信息
				if (5 == json.getIntValue("eventType") && ("VEDIO".equals(json.getString("type")) || "AUDIO".equals(json.getString("type")))) {
					//{"duration":"4","ext":"extra_data","createtime":"1565174410630","members":"[{\"duration\":2,\"accid\":\"131879189602173184\",\"userType\":1},{\"duration\":2,\"caller\":true,\"accid\":\"135182078311465216\",\"userType\":1}]","eventType":"5","type":"AUDIO","channelId":"51287635068891","live":"0","status":"SUCCESS"}
					//{"duration":"28","ext":"extra_data","createtime":"1561099680720","members":"[{\"duration\":14,\"caller\":true,\"accid\":\"67244811045896448\",\"userType\":1},{\"duration\":14,\"accid\":\"134831734503047424\",\"userType\":1}]","eventType":"5","type":"VEDIO","channelId":"51154114316248","live":"0","status":"SUCCESS"}
					int duration = json.getIntValue("duration");    
					if(duration > 0) {
						long channelId = json.getLongValue("channelId");
						if(channelId > 0) {
							JSONArray members = json.getJSONArray("members");
							if(Tools.isNotNull(members) && members.size() > 0) {
								long callerId = 0;
								long userId = 0;
								for(int i=0; i<members.size(); i++) {
									JSONObject member = members.getJSONObject(i);
									if(Tools.isNotNull(member)) {
										boolean caller = member.getBooleanValue("caller");
										long accid = member.getLongValue("accid");
										if(caller) {
											callerId = accid;
										} else {
											userId = accid;
										}
									}
								}
								//进行数据插入操作处理
								VchatChannelEntity entity = new VchatChannelEntity();
								entity.setCallerid(callerId);
								entity.setDuration(duration);
								entity.setUserid(userId);
								entity.setChannelId(channelId);
								entity.setCreate_time(new Date());
								entity.setUpdate_time(new Date());
								if("AUDIO".equals(json.getString("type"))) {
									entity.setType(1);
								} else {
									entity.setType(0);
								}
								vchatChannelContract.insert(entity);
								
								try {
									//处理根据对应的通话渠道获取对应的  视频聊中的数据
									PageModel pageModel = PageModel.getPageModel();
									pageModel.addQuery(Restrictions.eq("wy_chatid", channelId));
									List<VchatRoomEntity> list = vchatRoomContract.load(pageModel);
									if(Tools.isNotNull(list) && list.size() > 0) {
										VchatRoomEntity room = list.get(0);
										room.setDuration(duration);
										vchatRoomContract.update(room);
									}
								} catch (Exception e) {
									
								}
							}
						}
						
					}
				}
				//处理视频录制  地址信息
				if (6 == json.getIntValue("eventType")) {
					//{"fileinfo":"[{\"vid\":\"2557063713\",\"filename\":\"0-51153883301845-mix.mp4\",\"pieceindex\":\"0\",\"size\":\"2431573\",\"type\":\"mp4\",\"user\":\"0\",\"mix\":true,\"url\":\"http://jdvodr6xgogfm.vod.126.net/jdvodr6xgogfm/0-51153883301845-0-mix.mp4\",\"channelid\":\"51153883301845\",\"timestamp\":\"1561092673\",\"md5\":\"03726e847b9bbc8b31f96037b93a39c8\"}]","eventType":"6"}
					JSONArray fileinfos = json.getJSONArray("fileinfo");
					if(Tools.isNotNull(fileinfos) && fileinfos.size() > 0) {
						JSONObject fileinfo = fileinfos.getJSONObject(0);
						if(Tools.isNotNull(fileinfo)) {
							long channelid = fileinfo.getLongValue("channelid");
							if(channelid > 0) {
								String url = fileinfo.getString("url");
								VchatChannelEntity entity = vchatChannelContract.findByProperty("channelId", channelid);
								if(Tools.isNotNull(entity)) {
									entity.setUrl(url);
									entity.setUpdate_time(new Date());
									vchatChannelContract.update(entity);
								}
							}
						}
					}
				}
				
				if (1 == json.getIntValue("eventType") && "PERSON".equals(json.getString("convType"))) {// P2P会话类型的消息
					if (0 == json.getIntValue("resendFlag")) {// 排重
						// 分离业务
						UserBO from = userAgent.findById(json.getLongValue("fromAccount"));
						UserBO to = userAgent.findById(json.getLongValue("to"));
						attachMessageObservable.notifyObservers(from, to, json);
					}
				}

			} catch (Exception e) {
				logger.warn("处理抄送消息出错", e);
			}
		});
	}
	
	
	/**
	 *  又拍云转码
	 * 
	 * @param request
	 * @return
	 */
	@NoLog
	@RequestMapping(value = "/upYunCloud/videoTranscode/{videoId}", method = RequestMethod.POST, produces = Produce.TEXT_JSON)
	@ResponseBody
	public Result  upYunCloudVideoTranscode(@RequestBody(required=false) String body,@PathVariable("videoId") long videoId) throws Exception {
		logger.info("videoTranscode_body:"+body);
		JSONObject json = JsonHelper.toJsonObject(body);
		String taskId = json.getString("task_id");
		Integer status = json.getInteger("status_code");
		String description = json.getString("description");
		JSONArray jsonArray = json.getJSONArray("path");
		
		if(status == 200){
			ShortVideoEntity shortVideo = shortVideoContract.findById(videoId);
			if(Tools.isNotNull(shortVideo)){
				if(shortVideo.getStatus() == ShortVideoStatusEnum.transcoding.getCode()){
					shortVideo.setStatus(ShortVideoStatusEnum.online.getCode());
					String saveAs = shortVideo.getVideo_path().substring(0, shortVideo.getVideo_path().lastIndexOf("."))+".mp4";
					shortVideo.setVideo_path(saveAs);
					shortVideoContract.update(shortVideo);
				}
			}
		
		}
		ShortVideoTranscodeEntity entity = shortVideoTranscodeContract.findByProperty("videoId", videoId);
		if(Tools.isNotNull(entity)){
			entity.setStatus(status==200?ShortVideoTranscodeStatusEnum.trans_success.getCode():ShortVideoTranscodeStatusEnum.trans_fail.getCode());
			entity.setResult_status(status);
			if(Tools.isNotNull(jsonArray)){
				entity.setPath(jsonArray.getString(0));
			}
			entity.setDescription(description);
			entity.setUpdate_time(new Date());
			shortVideoTranscodeContract.update(entity);
		}
		
		Result result = new Result();
		result.setCode(200);
		result.setSucceed(true);
		result.setMsg("success");
		return result;
	}
	
	
	
	/**
	 * 网易聊天消息反垃圾回调
	 * 
	 * @param request
	 * @return
	 */
	@NoLog
	@RequestMapping(value = "msg/receiveMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> attachReceiveMsg(HttpServletRequest request) {
		return neteaseAgent.receiveMsgNeteaseMsg(request, json -> {
			try {
				// *同步记录聊天消息* //
				logger.info("neteaseReciveMsg:"+json.toString());
				
			} catch (Exception e) {
				logger.warn("处理反垃圾消息出错", e);
			}
		});
	}
	
	
	/**
	 * 网易语音消息回调结果
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "netease/msg/audioResult/{orderId}",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> audioResult(@PathVariable("orderId") long orderId, HttpServletRequest request) throws Exception {
		Enumeration<?> en = request.getParameterNames();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("orderId", orderId);
		while(en.hasMoreElements()){
			String k = (String)en.nextElement();
			hashMap.put(k, request.getParameter(k));
		}
		vChatTextYXService.audioCallBackRecv(orderId ,request);
		logger.info("audioResult:"+JsonHelper.toJson(hashMap));
		return null;
	}
	
}
