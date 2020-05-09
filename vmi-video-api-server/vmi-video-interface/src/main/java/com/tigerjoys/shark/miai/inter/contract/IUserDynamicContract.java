package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.UserDynamicEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  动态表[t_user_dynamic]表 接口类
 * @author shiming
 * @Date 2017-08-14 14:42:23
 *
 */
public interface IUserDynamicContract extends BaseContract<UserDynamicEntity> {
	
	/**
	 * 获取关注人的动态
	 * @param userid
	 * @param stamp
	 * @param limit
	 * @return
	 */
	List<UserDynamicEntity> findAttentionDynamic(long userid, String stamp, long limit);
}
