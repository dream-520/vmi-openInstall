package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserBgPictureEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  用户背景图片表[t_user_bg_picture]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2017-12-21 16:51:37
 *
 */
@Producer(entityType=UserBgPictureEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserBgPictureMapper extends BaseMapper<UserBgPictureEntity> {
    
}