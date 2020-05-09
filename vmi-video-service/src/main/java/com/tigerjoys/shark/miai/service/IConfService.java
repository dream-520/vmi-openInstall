package com.tigerjoys.shark.miai.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;

/**
 * APP更新服务接口类
 * @author lipeng
 *
 */
public interface IConfService {
	
	/**
	 * 检查更新
	 * @param channelName
	 * @param platform
	 * @return
	 * @throws Exception
	 */
	public ActionResult checkVersion(String channelName, int platform) throws Exception;
	
	/**
	 * IOS检查更新
	 * @param platform
	 * @return
	 * @throws Exception
	 */
	//public ActionResult iosCheckVersion(int platform) throws Exception;

	/**
	 * 黑名单列表
	 * @param userid
	 * @param stamp
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public ActionResult blackList(long userid, long stamp)throws Exception;
	
	/**
	 * 获得客户端兼容的城市列表对象
	 * @return Map<String , Object>
	 * @throws Exception
	 */
	public Map<String , Object> getRegionCityMap() throws Exception;
	
	/**
	 * 同步数据
	 * @param version - 客户端传递的版本号
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult syncData(int version,int packageType) throws Exception;

	/**
	 * 获得ios端真实展示状态
	 * @return
	 */
	public ActionResult getIosStatus() throws Exception;

	/**
	 * 获得用户隐私设置页面数据
	 * @return
	 * @throws Exception
	 */
	public ActionResult getPrivacySet()throws Exception;

	/**
	 * 设置/验证密码
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public ActionResult verifyPassword(JSONObject json)throws Exception;

	/**
	 * 达人开关
	 * @return
	 * @throws Exception
	 */
	public ActionResult talentOnOff()throws Exception;
	
	/**
	 * 测试IOS是否真测试
	 * @return
	 * @throws Exception
	 */
	public boolean testIOS() throws Exception;

	/**
	 * IOS企业包升级检测
	 * @return
	 * @throws Exception
	 */
	public ActionResult checkIosVersion() throws Exception;


}
