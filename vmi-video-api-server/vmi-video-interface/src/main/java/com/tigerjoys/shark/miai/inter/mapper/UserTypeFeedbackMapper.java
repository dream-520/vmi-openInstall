package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserTypeFeedbackEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户分类反馈表[t_user_type_feedback]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-01-29 16:31:32
 *
 */
@Producer(entityType=UserTypeFeedbackEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserTypeFeedbackMapper extends BaseMapper<UserTypeFeedbackEntity> {
    
}