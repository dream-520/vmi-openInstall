package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorAppointContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorAppointEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorAppointMapper;

/**
 * 数据库中  主播约会表[t_anchor_appoint]表 接口实现类
 * @author shiming
 * @Date 2020-01-07 20:00:05
 *
 */
@Repository
public class AnchorAppointContractImpl extends AbstractBaseContract<AnchorAppointEntity , AnchorAppointMapper> implements IAnchorAppointContract {
	
}
