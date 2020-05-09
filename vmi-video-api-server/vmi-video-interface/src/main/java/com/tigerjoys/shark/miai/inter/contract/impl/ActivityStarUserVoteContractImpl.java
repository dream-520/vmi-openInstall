package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IActivityStarUserVoteContract;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarUserVoteEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ActivityStarUserVoteMapper;

/**
 * 数据库中  女神之星用户投注表[t_activity_star_user_vote]表 接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Repository
public class ActivityStarUserVoteContractImpl extends AbstractBaseContract<ActivityStarUserVoteEntity , ActivityStarUserVoteMapper> implements IActivityStarUserVoteContract {

	@Override
	public Integer getIssuePeople(int issue) {
		return mapper.getIssuePeople(issue);
	}

	@Override
	public Integer getIssueVote(int issue) {
		return mapper.getIssueVote(issue);
	}

	@Override
	public Integer getIssuePrizeVote(int issue, long anchorid) {
		return mapper.getIssuePrizeVote(issue, anchorid);
	}
	
}
