package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorShutContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorShutEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorShutMapper;

/**
 * 数据库中  主播下架记录[t_anchor_shut]表 接口实现类
 * @author shiming
<<<<<<< HEAD
 * @Date 2019-08-29 19:59:39
=======
 * @Date 2019-08-29 19:54:23
>>>>>>> vmi0821
 *
 */
@Repository
public class AnchorShutContractImpl extends AbstractBaseContract<AnchorShutEntity , AnchorShutMapper> implements IAnchorShutContract {
	
}
