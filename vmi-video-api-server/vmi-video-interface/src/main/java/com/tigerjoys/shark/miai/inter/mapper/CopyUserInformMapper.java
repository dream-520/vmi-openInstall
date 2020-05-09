package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.CopyUserInformEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户举报表[t_copy_user_inform]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-12-18 15:45:36
 *
 */
@Producer(entityType=CopyUserInformEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface CopyUserInformMapper extends BaseMapper<CopyUserInformEntity> {
    
}