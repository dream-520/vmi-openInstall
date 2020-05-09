package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppRankingContract;
import com.tigerjoys.shark.miai.inter.entity.AppRankingEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppRankingMapper;

/**
 * 数据库中  App假排行榜数据[t_app_ranking]表 接口实现类
 * @author shiming
 * @Date 2019-07-16 18:21:38
 *
 */
@Repository
public class AppRankingContractImpl extends AbstractBaseContract<AppRankingEntity , AppRankingMapper> implements IAppRankingContract {
	
}
