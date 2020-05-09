package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 首页弹窗服务接口定义
 * @author liuman
 *
 */
public interface IHomePopService {
	
	/**
	 * 获得单个首页弹窗url地址
	 * @param clientId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ActionResult showPage(String clientId,long userId) throws Exception;
	
	/**
	 * 获得版本首级弹窗url地址
	 * @param clientId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String showVersionUpgradePage(String clientId,long userId,int versionCode) throws Exception;
	
	/**
	 * 新的首页弹出窗口
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ActionResult showNewPage(long userId) throws Exception;
}
