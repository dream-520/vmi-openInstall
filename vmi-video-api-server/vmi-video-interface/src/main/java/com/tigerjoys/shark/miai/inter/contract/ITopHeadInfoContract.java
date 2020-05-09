package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.TopHeadInfoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中 [t_top_head_info]表 接口类
 * 
 * @author yangjunming
 * @Date 2018-03-09 18:19:37
 *
 */
public interface ITopHeadInfoContract extends BaseContract<TopHeadInfoEntity> {
	/**
	 * 添加头条信息
	 * 
	 * @param name
	 * @param info
	 * @return
	 * @throws Exception
	 */
	public void addTopHeadInfo(String name, String info) throws Exception;
}
