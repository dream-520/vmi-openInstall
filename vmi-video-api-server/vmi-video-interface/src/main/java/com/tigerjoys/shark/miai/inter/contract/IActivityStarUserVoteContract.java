package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ActivityStarUserVoteEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  女神之星用户投注表[t_activity_star_user_vote]表 接口类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
public interface IActivityStarUserVoteContract extends BaseContract<ActivityStarUserVoteEntity> {
	
	public Integer getIssuePeople(int issue);
	
	public Integer getIssueVote(int issue);
	
	public Integer getIssuePrizeVote(int issue, long anchorid);
}
