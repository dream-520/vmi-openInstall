package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVGiftContract;
import com.tigerjoys.shark.miai.inter.entity.VGiftEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VGiftMapper;

/**
 * 数据库中  [t_v_gift]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Repository
public class VGiftContractImpl extends AbstractBaseContract<VGiftEntity , VGiftMapper> implements IVGiftContract {
	
}
