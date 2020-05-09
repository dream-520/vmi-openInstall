package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.AmassInfoDto;

/**
 * 信息收集服务接口
 * @author chengang
 *
 */
public interface IInformationCollectService {
	
	/**
	 * 信息手机
	 * @param info - AmassInfoDto
	 * @return ActionResult
	 * @throws Exception
	 */
	public ActionResult amassInfo(AmassInfoDto info) throws Exception;

}
