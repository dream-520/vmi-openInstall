package com.tigerjoys.shark.miai.controller;

import java.util.List;

import javax.validation.Valid;

import org.shark.miai.common.annotation.ForbidDialAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.dto.service.AnchorCreateDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IVliaoIndexService;

/**
 *  测试版首页数据请求处理接口
 * @author shiming
 *
 */
@Login
@Controller
@RequestMapping(value="/api/v" , method=RequestMethod.POST , produces=Produce.TEXT_ENCODE)
public class VliaoIndexController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(VliaoIndexController.class);
	
	@Autowired
	private IVliaoIndexService vliaoIndexService;
	
	/**
	 * 根据关键词搜索对应的主播
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/anchor/search",method=RequestMethod.POST)
	public @ResponseBody ActionResult searchAnchor(@RequestBody(required = false) String body) throws Exception {
		return vliaoIndexService.searchAnchor(body);
	}
	
	/**
	 * 获取banner列表数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index/homeBanner",method=RequestMethod.POST)
	public @ResponseBody ActionResult getBanner(@RequestBody(required = false) String body) throws Exception {
		return vliaoIndexService.getBanners();
	}
	
	/**
	 * 获取对应的主播列表数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index/anchor",method=RequestMethod.POST)
	public @ResponseBody ActionResult getAnchors(@RequestBody(required = false) String body) throws Exception {
		return vliaoIndexService.getAnchors(body);
	}
	
	@RequestMapping(value="/index/tab/anchor",method=RequestMethod.POST)
	public @ResponseBody ActionResult getTabAnchors(@RequestBody(required = true) String body) throws Exception {
		return vliaoIndexService.getTabAnchors(body);
	}
	
	/**
	 * 获取对应的主播列表数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/anchor/apply",method=RequestMethod.POST)
	public @ResponseBody ActionResult applyAnchor(@Valid @RequestBody AnchorCreateDto createDto, BindingResult result) throws Exception {
		//验证相关条件和逻辑
		if(result.hasFieldErrors()){ //判断验证是否出错.
			List<FieldError> fes = result.getFieldErrors();
			if(fes != null && !fes.isEmpty()) {
				for(FieldError fe : fes){
					logger.info("FieldError : "+fe.getField()+" :"+fe.getDefaultMessage());
				}
				//默认展示一个信息
				return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode() , fes.get(0).getDefaultMessage());
			}
		}
		return vliaoIndexService.applyAnchor(createDto);
	}
	
	/**
	 * 发送绑定验证码
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/anchor/sendAuthCode",method=RequestMethod.POST)
	public @ResponseBody ActionResult sendAuthcode(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		return vliaoIndexService.authSendCode(json.getString("mobile"));
	}
	
	/**
	 * 确认绑定手机号
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@ForbidDialAnnotation
	@RequestMapping(value = "/anchor/authCodeAdd",method=RequestMethod.POST)
	public @ResponseBody ActionResult authcodeAdd(@RequestBody String body) throws Exception {
		return vliaoIndexService.authMobileAdd(JsonHelper.toJsonObject(body));
	}
	
	@RequestMapping(value="/start",method=RequestMethod.POST)
	public  @ResponseBody ActionResult start() throws Exception {
		return vliaoIndexService.start();
	}
	
	@RequestMapping(value="/tempt/start",method=RequestMethod.POST)
	public  @ResponseBody ActionResult temptStart(@RequestBody String body) throws Exception {
		return vliaoIndexService.temptStart(body);
	}
	
	@RequestMapping(value="/chat/start",method=RequestMethod.POST)
	public  @ResponseBody ActionResult chatStart(@RequestBody String body) throws Exception {
		return vliaoIndexService.chatStart(body);
	}
	
	@RequestMapping(value="/chat/video/fail",method=RequestMethod.POST)
	public  @ResponseBody ActionResult videoFail(@RequestBody String body) throws Exception {
		return vliaoIndexService.videoFail(body);
	}
	
	/**
	 * 获取tag对应的主播列表数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index/tag/anchor",method=RequestMethod.POST)
	public @ResponseBody ActionResult getIosTagAnchors(@RequestBody(required = true) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer tag = json.getInteger("tag");   // 0 关注  1 推荐  2 新人
		String stamp = json.getString("stamp");
		return vliaoIndexService.getIosAnchors(tag,stamp);
	}
	
	/**
	 * 获取推荐对应的主播列表数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index/hot/anchor",method=RequestMethod.POST)
	public @ResponseBody ActionResult getIosHotAnchors(@RequestBody(required = false) String body) throws Exception {
		return vliaoIndexService.getIosHotAnchors();
	}
	
	/*
	@RequestMapping(value="/anchor/state",method=RequestMethod.POST)
	public @ResponseBody ActionResult getAnchorState(@RequestBody String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(json)) {
			logger.error("获取到客户端的状态信息:"+json.toJSONString());
		}
		return ActionResult.success();
	}
	*/
	
	@RequestMapping(value="/ios/anchor",method=RequestMethod.POST)
	public @ResponseBody ActionResult getIOSAnchors() throws Exception {
		return vliaoIndexService.getIOSAnchors();
	}
	
	/*
	 * 新主播端调用接口
	 */
	@RequestMapping(value="/new/anchor",method=RequestMethod.POST)
	public @ResponseBody ActionResult getNewAnchors(@RequestBody(required=false) String body) throws Exception {
		JSONObject json = JsonHelper.toJsonObject(body);
		Integer tag = json.getInteger("tag");   //暂时不使用
		Integer type = json.getInteger("type"); //
		String stamp = json.getString("stamp");
		long userid = RequestUtils.getCurrent().getUserid();
		return vliaoIndexService.anchorNewSlideList(userid,tag,type,stamp);
	}
	
}
