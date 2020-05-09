package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IBussinessMessageContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.BussinessMessageMapper;

/**
 * 数据库中  业务消息记录表[t_bussiness_message]表 接口实现类
 * @author liuman
 * @Date 2017-05-18 11:38:44
 *
 */
@Repository
public class BussinessMessageContractImpl extends AbstractBaseContract<BussinessMessageEntity , BussinessMessageMapper> implements IBussinessMessageContract {

	@Override
	public List<BussinessMessageEntity> loadByUseridAndType(List<Integer> types, long userId) {
		return mapper.loadByUseridAndType(types, userId);
	}
	
}
