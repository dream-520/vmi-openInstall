package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.PayUserLabelEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  付费用户标签表[t_pay_user_label]表 dao通用操作接口实现类
 * @author lipeng
 * @Date 2019-10-22 16:36:28
 *
 */
@Producer(entityType=PayUserLabelEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface PayUserLabelMapper extends BaseMapper<PayUserLabelEntity> {
    
}