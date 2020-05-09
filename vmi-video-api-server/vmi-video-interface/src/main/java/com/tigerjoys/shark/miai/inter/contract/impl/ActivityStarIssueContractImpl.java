package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IActivityStarIssueContract;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarIssueEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ActivityStarIssueMapper;

/**
 * 数据库中  女神之星活动期数管理表[t_activity_star_issue]表 接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Repository
public class ActivityStarIssueContractImpl extends AbstractBaseContract<ActivityStarIssueEntity , ActivityStarIssueMapper> implements IActivityStarIssueContract {
	
}
