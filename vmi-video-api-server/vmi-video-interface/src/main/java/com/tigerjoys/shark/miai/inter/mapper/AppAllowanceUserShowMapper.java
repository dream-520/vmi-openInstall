package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceUserShowEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户消费免费机会弹窗记录[t_app_allowance_user_show]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-18 16:19:01
 *
 */
@Producer(entityType=AppAllowanceUserShowEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppAllowanceUserShowMapper extends BaseMapper<AppAllowanceUserShowEntity> {
    
}