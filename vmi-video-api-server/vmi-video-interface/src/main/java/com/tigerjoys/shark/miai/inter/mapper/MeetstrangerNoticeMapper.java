package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.MeetstrangerNoticeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  遇见文字公告表[t_meetstranger_notice]表 dao通用操作接口实现类
 * @author liuman
 * @Date 2017-12-19 16:30:43
 *
 */
@Producer(entityType=MeetstrangerNoticeEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface MeetstrangerNoticeMapper extends BaseMapper<MeetstrangerNoticeEntity> {
    
}