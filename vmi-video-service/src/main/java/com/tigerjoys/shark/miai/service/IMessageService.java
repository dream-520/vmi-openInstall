package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 各类小红点信息提示服务
 * @author liuman
 *
 */
public interface IMessageService {
    
	/**
	 * 获取各类小红点消息集合
	 * @param userId
	 * @param stamp
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	public ActionResult getMessageUpdateDtos(long userId,String stamp,String packageName) throws Exception;
	
	/**
	 * 获取对应的系统消息小红点的存在性标识
	 * @param userId
	 * @param stamp
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	public ActionResult getMessageCount(long userId) throws Exception;
	
}
