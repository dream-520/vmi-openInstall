package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVPageContract;
import com.tigerjoys.shark.miai.inter.entity.VPageEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VPageMapper;

/**
 * 数据库中  [t_v_page]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:06
 *
 */
@Repository
public class VPageContractImpl extends AbstractBaseContract<VPageEntity , VPageMapper> implements IVPageContract {
	
}
