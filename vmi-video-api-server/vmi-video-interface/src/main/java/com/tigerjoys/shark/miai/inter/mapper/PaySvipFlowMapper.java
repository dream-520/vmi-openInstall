package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.PaySvipFlowEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  支付高级会员购买流水表[t_pay_svip_flow]表 dao通用操作接口实现类
 * @author chengang
 * @Date 2018-01-26 13:34:41
 *
 */
@Producer(entityType=PaySvipFlowEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface PaySvipFlowMapper extends BaseMapper<PaySvipFlowEntity> {
    
}