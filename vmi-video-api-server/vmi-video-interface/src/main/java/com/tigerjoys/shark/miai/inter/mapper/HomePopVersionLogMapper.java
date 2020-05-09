package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.HomePopVersionLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  升级弹窗log表[t_home_pop_version_log]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2019-09-03 13:21:47
 *
 */
@Producer(entityType=HomePopVersionLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface HomePopVersionLogMapper extends BaseMapper<HomePopVersionLogEntity> {
    
}