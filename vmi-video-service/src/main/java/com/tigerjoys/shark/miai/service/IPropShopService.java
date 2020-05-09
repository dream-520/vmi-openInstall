package com.tigerjoys.shark.miai.service;


import java.util.Map;

/**
 * 道具商城服务接口类
 * @author chengang
 *
 */
public interface IPropShopService {
	
	/**
	 * 查询商城道具列表和剩余钻石、金币
	 * @return ActionResult
	 * @throws Exception
	 */
	public Map<String ,Object> getShopPropList(long userid) throws Exception;
	
}
