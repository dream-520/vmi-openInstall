package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppAllowanceUserShowContract;
import com.tigerjoys.shark.miai.inter.entity.AppAllowanceUserShowEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppAllowanceUserShowMapper;

/**
 * 数据库中  用户消费免费机会弹窗记录[t_app_allowance_user_show]表 接口实现类
 * @author shiming
 * @Date 2019-07-18 16:19:01
 *
 */
@Repository
public class AppAllowanceUserShowContractImpl extends AbstractBaseContract<AppAllowanceUserShowEntity , AppAllowanceUserShowMapper> implements IAppAllowanceUserShowContract {
	
}
