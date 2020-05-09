package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IHomePopContract;
import com.tigerjoys.shark.miai.inter.entity.HomePopEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.HomePopMapper;

/**
 * 数据库中  首页弹窗功能表[t_home_pop]表 接口实现类
 * @author liuman
 * @Date 2017-12-20 14:12:24
 *
 */
@Repository
public class HomePopContractImpl extends AbstractBaseContract<HomePopEntity , HomePopMapper> implements IHomePopContract {
	
}
