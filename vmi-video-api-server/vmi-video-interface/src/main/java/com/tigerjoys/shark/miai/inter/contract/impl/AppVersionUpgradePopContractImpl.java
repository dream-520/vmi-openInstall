package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppVersionUpgradePopContract;
import com.tigerjoys.shark.miai.inter.entity.AppVersionUpgradePopEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppVersionUpgradePopMapper;

/**
 * 数据库中  版本升级弹窗表[t_app_version_upgrade_pop]表 接口实现类
 * @author yangjunming
 * @Date 2019-09-03 13:21:47
 *
 */
@Repository
public class AppVersionUpgradePopContractImpl extends AbstractBaseContract<AppVersionUpgradePopEntity , AppVersionUpgradePopMapper> implements IAppVersionUpgradePopContract {
	
}
