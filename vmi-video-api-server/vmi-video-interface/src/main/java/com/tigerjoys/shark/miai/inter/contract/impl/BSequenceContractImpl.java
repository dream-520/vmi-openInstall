package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IBSequenceContract;
import com.tigerjoys.shark.miai.inter.entity.BSequenceEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.BSequenceMapper;

/**
 * 数据库中  [t_b_sequence]表 接口实现类
 * @author yangjunming
 * @Date 2018-09-18 11:24:01
 *
 */
@Repository
public class BSequenceContractImpl extends AbstractBaseContract<BSequenceEntity , BSequenceMapper> implements IBSequenceContract {

	@Override
	public long getGeneraterId(String name) throws Exception {
		int rows = mapper.updateByStatement("current_value=current_value+1", "name='"+name+"' and current_value<max_value");
		if(rows == 0){
			mapper.updateByStatement("current_value=min_value", "name='"+name+"'");
		}
		BSequenceEntity bsSeq = mapper.findByProperty("name", name);
		if(Tools.isNull(bsSeq)){
			return 0;
		}
		return bsSeq.getCurrent_value();
	}

	@Override
	public long getCurrentValue(String name, long increase) throws Exception {
		BSequenceEntity bsSeq = mapper.findByProperty("name", name);
		if(Tools.isNull(bsSeq)){
			return 0;
		}
		if(increase == 0){
			return bsSeq.getCurrent_value();
		}
		int rows = mapper.updateByStatement("current_value=current_value+"+increase, "id="+bsSeq.getId()+" and current_value<max_value");
		if(rows == 0){
			mapper.updateByStatement("current_value=min_value", "id="+bsSeq.getId());
		}
		bsSeq = mapper.findById(bsSeq.getId());
		return bsSeq.getCurrent_value();
	}
	
	
}
