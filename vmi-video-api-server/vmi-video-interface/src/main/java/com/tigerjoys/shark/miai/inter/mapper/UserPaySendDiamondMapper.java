package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.UserPaySendDiamondEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  累计充值超过500记录送钻记录[t_user_pay_send_diamond]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-03 14:41:44
 *
 */
@Producer(entityType=UserPaySendDiamondEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface UserPaySendDiamondMapper extends BaseMapper<UserPaySendDiamondEntity> {
    
}