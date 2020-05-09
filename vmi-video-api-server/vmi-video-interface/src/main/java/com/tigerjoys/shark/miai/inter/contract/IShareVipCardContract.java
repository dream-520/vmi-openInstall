package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ShareVipCardEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_share_vip_card]表 接口类
 * @author yangjunming
 * @Date 2018-05-25 10:26:31
 *
 */
public interface IShareVipCardContract extends BaseContract<ShareVipCardEntity> {
	/**
	 * 已使用
	 * @param mobile
	 * @param orderId
	 * @return   0未使用  1 使用成功
	 * @throws Exception
	 */
	 
	public int usedCard(String mobile) throws Exception;
	
	
	/**
	 * 已使用
	 * @param id
	 * @param orderId
	 * @return   0未使用  1 使用成功
	 * @throws Exception
	 */
	 
	public boolean queryCardStatus(String mobile) throws Exception;
}
