package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

public interface IAnchorHelperAutoDialService {
	
	
	
	/**
	 * 自动拨打获取用户接口
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public ActionResult autoDial(long userId) throws Exception;
	

}
