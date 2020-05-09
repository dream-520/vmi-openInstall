package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVchatChannelContract;
import com.tigerjoys.shark.miai.inter.entity.VchatChannelEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VchatChannelMapper;

/**
 * 数据库中  网易通话信息记录表[t_vchat_channel]表 接口实现类
 * @author shiming
 * @Date 2019-06-21 14:39:20
 *
 */
@Repository
public class VchatChannelContractImpl extends AbstractBaseContract<VchatChannelEntity , VchatChannelMapper> implements IVchatChannelContract {
	
}
