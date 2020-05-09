package com.tigerjoys.shark.miai.inter.mapper;

import org.apache.ibatis.annotations.Producer;
import com.tigerjoys.nbs.mybatis.core.provider.DefaultSqlProvider;
import com.tigerjoys.shark.miai.inter.entity.AnchorRobotSendGiftEntity;
import com.tigerjoys.nbs.mybatis.core.BaseMapper;
import com.tigerjoys.nbs.mybatis.core.annotation.Mapper;

/**
 * 数据库  接收机器人礼物的用户[t_anchor_robot_send_gift]表 dao通用操作接口实现类
 * @author shiming
 * @Date 2019-09-12 14:14:08
 *
 */
@Producer(entityType=AnchorRobotSendGiftEntity.class,providerType=DefaultSqlProvider.class)
@Mapper
public interface AnchorRobotSendGiftMapper extends BaseMapper<AnchorRobotSendGiftEntity> {
    
}