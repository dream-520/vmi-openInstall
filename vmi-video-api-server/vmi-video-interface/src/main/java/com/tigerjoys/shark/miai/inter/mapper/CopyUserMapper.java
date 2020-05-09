package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CopyUserEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户基础表[t_copy_user]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-12-17 15:58:26
 *
 */
@Producer(entityType=CopyUserEntity.class,providerType=DefaultSqlProvider.class,increment=false)
@Mapper
public interface CopyUserMapper extends BaseMapper<CopyUserEntity> {
    
}