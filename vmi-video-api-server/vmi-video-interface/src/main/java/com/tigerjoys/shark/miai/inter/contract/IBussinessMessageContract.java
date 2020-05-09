package com.tigerjoys.shark.miai.inter.contract;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;

/**
 * 数据库中  业务消息记录表[t_bussiness_message]表 接口类
 * @author liuman
 * @Date 2017-05-18 11:38:44
 *
 */
public interface IBussinessMessageContract extends BaseContract<BussinessMessageEntity> {
	
	public abstract List<BussinessMessageEntity> loadByUseridAndType(List<Integer> types, long userId);
}
