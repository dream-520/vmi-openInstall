package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 系统消息服务接口
 * @author liuman
 *
 */
public interface ISystemMessageService {
	
	/**
	 * 
	 * @param userid 用户id
	 * @param stamp 时间戳
	 * @param page 页数
	 * @param pagesize 每页展示的数据量
	 * @param isAddReadCount 是否增加用户读取系统消息数量
	 * @param packageName app包名
	 * @return
	 * @throws Exception
	 */
	public ActionResult messageList(long userid , String stamp , int page , int pagesize,boolean isAddReadCount,String packageName) throws Exception;
	
	/**
	 * 新版获取消息的处理
	 * @param userid
	 * @param stamp
	 * @param page
	 * @param pagesize
	 * @param isAddReadCount
	 * @return
	 * @throws Exception
	 */
	public ActionResult messageListNew(long userid , String stamp , int page , int pagesize,boolean isAddReadCount) throws Exception;
	
}
