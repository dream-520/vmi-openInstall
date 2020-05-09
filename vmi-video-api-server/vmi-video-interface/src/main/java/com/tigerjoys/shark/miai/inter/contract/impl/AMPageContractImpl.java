package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAMPageContract;
import com.tigerjoys.shark.miai.inter.entity.AMPageEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AMPageMapper;

/**
 * 数据库中  [t_a_m_page]表 接口实现类
 * @author shiming
 * @Date 2018-11-14 16:14:24
 *
 */
@Repository
public class AMPageContractImpl extends AbstractBaseContract<AMPageEntity , AMPageMapper> implements IAMPageContract {
	
}
