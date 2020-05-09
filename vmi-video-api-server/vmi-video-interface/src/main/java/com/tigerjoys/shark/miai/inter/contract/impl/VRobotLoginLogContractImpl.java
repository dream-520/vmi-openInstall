package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVRobotLoginLogContract;
import com.tigerjoys.shark.miai.inter.entity.VRobotLoginLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VRobotLoginLogMapper;

/**
 * 数据库中  [t_v_robot_login_log]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Repository
public class VRobotLoginLogContractImpl extends AbstractBaseContract<VRobotLoginLogEntity , VRobotLoginLogMapper> implements IVRobotLoginLogContract {
	
}
