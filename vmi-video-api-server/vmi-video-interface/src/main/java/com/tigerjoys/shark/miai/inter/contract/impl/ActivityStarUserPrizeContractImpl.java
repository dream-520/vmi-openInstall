package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IActivityStarUserPrizeContract;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarUserPrizeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ActivityStarUserPrizeMapper;

/**
 * 数据库中  [t_activity_star_user_prize]表 接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Repository
public class ActivityStarUserPrizeContractImpl extends AbstractBaseContract<ActivityStarUserPrizeEntity , ActivityStarUserPrizeMapper> implements IActivityStarUserPrizeContract {
	
}
