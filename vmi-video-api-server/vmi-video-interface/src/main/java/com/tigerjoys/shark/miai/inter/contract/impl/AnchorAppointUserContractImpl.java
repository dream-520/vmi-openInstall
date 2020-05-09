package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorAppointUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorAppointUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorAppointUserMapper;

/**
 * 数据库中  用户约会信息记录表[t_anchor_appoint_user]表 接口实现类
 * @author shiming
 * @Date 2020-01-07 20:00:05
 *
 */
@Repository
public class AnchorAppointUserContractImpl extends AbstractBaseContract<AnchorAppointUserEntity , AnchorAppointUserMapper> implements IAnchorAppointUserContract {
	
}
