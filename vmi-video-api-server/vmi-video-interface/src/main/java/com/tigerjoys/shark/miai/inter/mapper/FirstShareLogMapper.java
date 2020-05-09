package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.FirstShareLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户每日首次登录日志表[t_first_share_log]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-06-07 15:49:06
 *
 */
@Producer(entityType=FirstShareLogEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface FirstShareLogMapper extends BaseMapper<FirstShareLogEntity> {
    
}