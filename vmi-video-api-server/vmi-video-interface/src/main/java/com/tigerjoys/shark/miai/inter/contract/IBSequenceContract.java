package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.BSequenceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_b_sequence]表 接口类
 * @author yangjunming
 * @Date 2018-09-18 11:24:01
 *
 */
public interface IBSequenceContract extends BaseContract<BSequenceEntity> {
	
	/**
	 * 获取生成的ID
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public long getGeneraterId(String name) throws Exception;
	
	
	/**
	 * 获取当前CurrentValue值
	 * @param name
	 * @param increase
	 * @return
	 * @throws Exception
	 */
	public long getCurrentValue(String name,long increase ) throws Exception;
	
}
