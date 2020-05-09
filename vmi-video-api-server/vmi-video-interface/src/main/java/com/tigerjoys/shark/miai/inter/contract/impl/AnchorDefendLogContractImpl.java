package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorDefendLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorDefendLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorDefendLogMapper;

/**
 * 数据库中  用户守护变更记录表[t_anchor_defend_log]表 接口实现类
 * @author shiming
 * @Date 2019-10-04 20:37:29
 *
 */
@Repository
public class AnchorDefendLogContractImpl extends AbstractBaseContract<AnchorDefendLogEntity , AnchorDefendLogMapper> implements IAnchorDefendLogContract {
	
}
