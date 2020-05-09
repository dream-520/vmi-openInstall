package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVRobotInfoContract;
import com.tigerjoys.shark.miai.inter.entity.VRobotInfoEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VRobotInfoMapper;

/**
 * 数据库中  V聊机器人用户信息表[t_v_robot_info]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Repository
public class VRobotInfoContractImpl extends AbstractBaseContract<VRobotInfoEntity , VRobotInfoMapper> implements IVRobotInfoContract {
	
}
