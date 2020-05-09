package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVUserGiftListContract;
import com.tigerjoys.shark.miai.inter.entity.VUserGiftListEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VUserGiftListMapper;

/**
 * 数据库中  [t_v_user_gift_list]表 接口实现类
 * @author shiming
 * @Date 2019-03-06 10:41:07
 *
 */
@Repository
public class VUserGiftListContractImpl extends AbstractBaseContract<VUserGiftListEntity , VUserGiftListMapper> implements IVUserGiftListContract {
	
}
