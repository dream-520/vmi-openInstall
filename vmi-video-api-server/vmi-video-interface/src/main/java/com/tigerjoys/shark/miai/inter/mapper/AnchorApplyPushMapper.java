package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorApplyPushEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  记录对应的主播审核通过发送通知的处理[t_anchor_apply_push]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-07-06 17:36:11
 *
 */
@Producer(entityType=AnchorApplyPushEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorApplyPushMapper extends BaseMapper<AnchorApplyPushEntity> {
    
}