package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.VUserInfoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_v_user_info]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2018-10-10 14:21:21
 *
 */
@Producer(entityType=VUserInfoEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface VUserInfoMapper extends BaseMapper<VUserInfoEntity> {
    
}