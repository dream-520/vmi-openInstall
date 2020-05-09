package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Select;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarUserVoteEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  女神之星用户投注表[t_activity_star_user_vote]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Producer(entityType=ActivityStarUserVoteEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ActivityStarUserVoteMapper extends BaseMapper<ActivityStarUserVoteEntity> {
    
	@Select("select count(distinct userid) from t_activity_star_user_vote where issue_id=#{issue}")
	public Integer getIssuePeople(@Param("issue") int issue);
	
	@Select("select sum(issue_vote) from t_activity_star_user_vote where issue_id=#{issue}")
	public Integer getIssueVote(@Param("issue") int issue);
	
	@Select("select sum(issue_vote) from t_activity_star_user_vote where issue_id=#{issue} and issue_anchor=#{anchorid}")
	public Integer getIssuePrizeVote(@Param("issue") int issue, @Param("anchorid") long anchorid);
	
}