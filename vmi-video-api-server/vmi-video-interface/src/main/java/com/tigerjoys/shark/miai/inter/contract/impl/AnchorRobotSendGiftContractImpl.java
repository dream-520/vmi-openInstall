package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorRobotSendGiftContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorRobotSendGiftEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorRobotSendGiftMapper;

/**
 * 数据库中  接收机器人礼物的用户[t_anchor_robot_send_gift]表 接口实现类
 * @author shiming
 * @Date 2019-09-12 14:14:08
 *
 */
@Repository
public class AnchorRobotSendGiftContractImpl extends AbstractBaseContract<AnchorRobotSendGiftEntity , AnchorRobotSendGiftMapper> implements IAnchorRobotSendGiftContract {
	
}
