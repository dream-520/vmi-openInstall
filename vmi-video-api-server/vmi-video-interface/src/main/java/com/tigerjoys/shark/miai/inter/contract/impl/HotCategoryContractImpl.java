package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IHotCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.HotCategoryEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.HotCategoryMapper;

/**
 * 数据库中  首页热门管理[t_hot_category]表 接口实现类
 * @author liuman
 * @Date 2018-01-25 17:50:29
 *
 */
@Repository
public class HotCategoryContractImpl extends AbstractBaseContract<HotCategoryEntity , HotCategoryMapper> implements IHotCategoryContract {
	
}
