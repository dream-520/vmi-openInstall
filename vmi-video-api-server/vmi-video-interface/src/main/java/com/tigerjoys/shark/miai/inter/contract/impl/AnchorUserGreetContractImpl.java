package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorUserGreetContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorUserGreetEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorUserGreetMapper;

/**
 * 数据库中  记录用户打过招呼的主播数据[t_anchor_user_greet]表 接口实现类
 * @author shiming
 * @Date 2020-01-08 15:15:13
 *
 */
@Repository
public class AnchorUserGreetContractImpl extends AbstractBaseContract<AnchorUserGreetEntity , AnchorUserGreetMapper> implements IAnchorUserGreetContract {
	
}
