package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.LinkedList;
import java.util.Random;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.ISequenceContract;
import com.tigerjoys.shark.miai.inter.entity.SequenceEntity;
import com.tigerjoys.shark.miai.inter.mapper.SequenceMapper;

/**
 * 数据库中  ID生成器表[b_sequence]表 接口实现类
 * @author chengang
 * @Date 2017-04-14 10:41:53
 *
 */
@Repository
public class SequenceContractImpl extends AbstractBaseContract<SequenceEntity , SequenceMapper> implements ISequenceContract {
	
	private Random random = new Random();
	
	@Transactional(propagation=Propagation.REQUIRES_NEW , rollbackFor=Exception.class)
	@Override
	public LinkedList<Long> getSeqList(Class<?> clazz , int count) throws Exception {
		SequenceEntity sequence = mapper.lockByClassName(clazz.getSimpleName());
		
		LinkedList<Long> idList = new LinkedList<>();
		
		long seq = sequence.getCurrent_value();
		for(int i=0;i<count;i++) {
			//计算随机值
			seq += random.nextInt(sequence.getIncrement().intValue()) + 1;
			
			idList.add(seq);
		}
		
		//将新的值更新到数据库中
		mapper.updateByStatement("current_value="+seq, "id="+sequence.getId());
		
		return idList;
	}
	
}
