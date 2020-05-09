package com.tigerjoys.shark.miai.agent;

import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;

/**
 * 全局广播服务代理接口
 * @author lipeng
 *
 */
public interface IPayUserAgent {
	
	/**
	 * 修改用户充值统计列表
	 * @param entity
	 * @throws Exception
	 */
	public void updatePay(UserPayActionEntity entity)throws Exception;
	
	
	/**
	 * 修改用户充值统计列表
	 * @param entity
	 * @throws Exception
	 */
	public void updateChat(VchatRoomEntity entity)throws Exception;
	
	
	

}
