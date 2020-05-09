package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.inter.entity.TopHeadInfoEntity;

/**
 * 首页服务接口类
 * @author yangjunming
 *
 */
public interface IHomeBService {
	
	public ActionResult index(long userId,int platform) throws Exception;

}
