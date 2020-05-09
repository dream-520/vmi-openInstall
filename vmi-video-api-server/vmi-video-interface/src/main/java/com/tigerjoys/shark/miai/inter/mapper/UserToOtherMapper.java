package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserToOtherEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  聊天历史用户之间映射关系[t_user_to_other]表 dao通用操作接口实现类
 * @author mouzhanpeng
 * @Date 2017-05-04 17:03:38
 *
 */
@Producer(entityType=UserToOtherEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserToOtherMapper extends BaseMapper<UserToOtherEntity> {
    
}