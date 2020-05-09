package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 预约相关的业务操作
 * @author shiming
 *
 */
public interface IUserSubscribeAnchorService {

	/**
	 * 检测是否有预约关系
	 * @param userid
	 * @param anchorid
	 */
	public ActionResult checkSubscribe(long userid, long anchorid) throws Exception ;
	
	/**
	 * 用户端 提交预约业务
	 * @param anchorid
	 * @return
	 */
	public ActionResult commitSubscribe(long userid, long anchorid) throws Exception ;
	
	/**
	 * 获取对应的预约列表数据
	 * @param userid
	 * @return
	 */
	public ActionResult getSubscribes(long userid) throws Exception ;
	
}
