package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.VipEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  用户VIP表[t_vip]表 接口类
 * @author yangjunming
 * @Date 2017-08-18 19:27:26
 *
 */
public interface IVipContract extends BaseContract<VipEntity> {
	
	/**
	 * VIP更新有效时间
	 * @param userid	用户ID
	 * @param days		增加天数
	 * @return   0未使用成功  1 使用成功
	 * @throws Exception
	 */
	 
	public int updateVipTime(long userid, int days) throws Exception;
}
