package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.StaticDownloadChannelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  遍历日志获取到的渠道信息[t_static_download_channel]表 dao通用操作接口实现类
 * @author yangjunming
 * @Date 2018-05-09 10:53:59
 *
 */
@Producer(entityType=StaticDownloadChannelEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface StaticDownloadChannelMapper extends BaseMapper<StaticDownloadChannelEntity> {
    
}