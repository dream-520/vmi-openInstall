package com.tigerjoys.shark.miai.service;

import java.util.List;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.index.GotoDataItem;

/**
 * 遇见首页服务接口定义
 * @author liuman
 *
 */
public interface IIndexService {
	
	/**
	 * 首页活动专区
	 * @param platform
	 * @return
	 * @throws Exception
	 */
	public List<GotoDataItem> getActivities(int platform) throws Exception;
	
}
