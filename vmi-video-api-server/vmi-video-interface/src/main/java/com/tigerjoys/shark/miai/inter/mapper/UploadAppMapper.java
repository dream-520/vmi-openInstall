package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UploadAppEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  App上传信息表[t_upload_app]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2018-04-26 15:28:04
 *
 */
@Producer(entityType=UploadAppEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UploadAppMapper extends BaseMapper<UploadAppEntity> {
    
}