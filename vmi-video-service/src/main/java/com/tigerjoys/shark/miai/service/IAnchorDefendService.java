package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 获取守护列表数据接口
 * @author shiming
 *
 */
public interface IAnchorDefendService {

	public ActionResult getAnchorDefend(long anchorid);
	
	public ActionResult getUserDefend(long userid);
}
