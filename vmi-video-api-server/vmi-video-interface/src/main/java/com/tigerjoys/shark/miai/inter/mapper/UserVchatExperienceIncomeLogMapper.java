package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserVchatExperienceIncomeLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户音视频体验收益[t_user_vchat_experience_income_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-09-10 12:12:46
 *
 */
@Producer(entityType=UserVchatExperienceIncomeLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserVchatExperienceIncomeLogMapper extends BaseMapper<UserVchatExperienceIncomeLogEntity> {
    
}