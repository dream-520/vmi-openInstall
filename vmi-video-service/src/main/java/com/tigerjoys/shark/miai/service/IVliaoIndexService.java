package com.tigerjoys.shark.miai.service;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.AnchorCreateDto;

/**
 * 测试版主页数据处理服务
 * @author shiming
 *
 */
public interface IVliaoIndexService {


	/**
	 * 根据关键词搜索对应的主播
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public ActionResult searchAnchor(String body) throws Exception;
	
	/**
	 * 首页推荐主播
	 * @return
	 * @throws Exception
	 */
	public ActionResult getAnchors(String body) throws Exception;
	
	/**
	 * 根据对应的tab显示对应的主播
	 * @return
	 * @throws Exception
	 */
	public ActionResult getTabAnchors(String body) throws Exception;
	
	/**
	 * 获得首页Banner
	 * @return
	 * @throws Exception
	 */
	public ActionResult getBanners() throws Exception;
	
	/**
	 * 认证大v
	 */
	public ActionResult applyAnchor(AnchorCreateDto createDto) throws Exception;
	
	/**
	 * 认证大v--绑定手机号发送验证码
	 */
	public ActionResult authSendCode(String mobile) throws Exception;
	
	/**
	 * 认证大v--确认绑定手机号
	 */
	public ActionResult authMobileAdd(JSONObject jsonObject) throws Exception;
	
	/**
	 * 应用启动发送假视频来电
	 */
	public ActionResult start() throws Exception;
	
	/**
	 * Ios首页tag主播
	 * @return
	 * @throws Exception
	 */
	public ActionResult getIosAnchors(Integer tag, String stamp) throws Exception;
	
	/**
	 * Ios首页推荐主播
	 * @return
	 * @throws Exception
	 */
	public ActionResult getIosHotAnchors() throws Exception;
	
	public ActionResult getIOSAnchors() throws Exception;
	
	/**
	 * 视频诱惑
	 */
	public ActionResult temptStart(String body) throws Exception;
	
	public ActionResult anchorNewSlideList(long userid, int tag, int type, String stamp) throws Exception;
	
	/**
	 * 触发产品们变态的假消息场景
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public ActionResult chatStart(String body) throws Exception;
	
	/**
	 * 触发假视频充值页面结束
	 * @return
	 * @throws Exception
	 */
	public ActionResult videoFail(String body) throws Exception;
	
}
