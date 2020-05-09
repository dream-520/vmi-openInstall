package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 系统消息服务接口
 * @author liuman
 *
 */
public interface IBussinessMessageService {
	
	/**
	 * 
	 * @param userid 用户id
	 * @param stamp 时间戳
	 * @param page 页数
	 * @param pagesize 每页展示的数据量
	 * @return
	 * @throws Exception
	 */
	public ActionResult messageList(long userid , String stamp , int page , int pagesize) throws Exception;
}
