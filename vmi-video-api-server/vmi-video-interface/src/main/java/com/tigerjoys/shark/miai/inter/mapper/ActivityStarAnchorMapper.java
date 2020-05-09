package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Producer;
import org.apache.ibatis.annotations.Update;

import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarAnchorEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  女神之星每期主播信息[t_activity_star_anchor]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
@Producer(entityType=ActivityStarAnchorEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface ActivityStarAnchorMapper extends BaseMapper<ActivityStarAnchorEntity> {
    
	@Update("update t_activity_star_anchor set charm_current=charm_current + #{charm} where anchor_id=#{userid} and issue_number=#{issue}")
	public void updateAnchorCharm(@Param("issue") int issue,@Param("userid") long userid, @Param("charm") int charm);
}