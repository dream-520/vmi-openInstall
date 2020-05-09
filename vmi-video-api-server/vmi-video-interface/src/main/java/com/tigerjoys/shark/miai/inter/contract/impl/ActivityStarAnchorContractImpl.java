package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IActivityStarAnchorContract;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarAnchorEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ActivityStarAnchorMapper;

/**
 * 数据库中  女神之星每期主播信息[t_activity_star_anchor]表 接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Repository
public class ActivityStarAnchorContractImpl extends AbstractBaseContract<ActivityStarAnchorEntity , ActivityStarAnchorMapper> implements IActivityStarAnchorContract {

	@Override
	public void updateAnchorCharm(int issue, long userid, int charm) {
		mapper.updateAnchorCharm(issue, userid, charm);
	}
	
}
