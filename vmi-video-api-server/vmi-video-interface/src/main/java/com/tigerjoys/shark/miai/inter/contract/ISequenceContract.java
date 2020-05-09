package com.tigerjoys.shark.miai.inter.contract;

import java.util.LinkedList;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.SequenceEntity;

/**
 * 数据库中  ID生成器表[b_sequence]表 接口类
 * @author chengang
 * @Date 2017-04-14 10:41:53
 *
 */
public interface ISequenceContract extends BaseContract<SequenceEntity> {
	
	/**
	 * 开启新事务，并获得一组ID序列
	 * @param clazz - entity对象的Class
	 * @param count - 每次生成数量
	 * @return LinkedList<Long>
	 * @throws Exception
	 */
	public LinkedList<Long> getSeqList(Class<?> clazz , int count) throws Exception;
	
}
