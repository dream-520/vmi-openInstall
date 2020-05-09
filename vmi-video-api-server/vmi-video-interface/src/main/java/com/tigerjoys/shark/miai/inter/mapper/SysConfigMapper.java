package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  系统配置表[t_sys_config]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2017-05-06 16:58:50
 *
 */
@Producer(entityType=SysConfigEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfigEntity> {
    
}