package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AppMsgSceneDetailEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  [t_app_msg_scene_detail]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-11 15:01:54
 *
 */
@Producer(entityType=AppMsgSceneDetailEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AppMsgSceneDetailMapper extends BaseMapper<AppMsgSceneDetailEntity> {
    
}