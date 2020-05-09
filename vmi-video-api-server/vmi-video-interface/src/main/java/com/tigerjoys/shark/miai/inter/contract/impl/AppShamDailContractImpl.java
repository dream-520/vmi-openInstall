package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppShamDailContract;
import com.tigerjoys.shark.miai.inter.entity.AppShamDailEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppShamDailMapper;

/**
 * 数据库中  虚假来电弹窗[t_app_sham_dail]表 接口实现类
 * @author shiming
 * @Date 2019-09-17 17:02:19
 *
 */
@Repository
public class AppShamDailContractImpl extends AbstractBaseContract<AppShamDailEntity , AppShamDailMapper> implements IAppShamDailContract {
	
}
