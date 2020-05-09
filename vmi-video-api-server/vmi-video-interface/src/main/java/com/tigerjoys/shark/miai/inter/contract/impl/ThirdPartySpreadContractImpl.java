package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IThirdPartySpreadContract;
import com.tigerjoys.shark.miai.inter.entity.ThirdPartySpreadEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ThirdPartySpreadMapper;

/**
 * 数据库中  三方广告推荐注册的设备和用户信息[t_third_party_spread]表 接口实现类
 * @author shiming
 * @Date 2020-01-03 16:31:35
 *
 */
@Repository
public class ThirdPartySpreadContractImpl extends AbstractBaseContract<ThirdPartySpreadEntity , ThirdPartySpreadMapper> implements IThirdPartySpreadContract {
	
}
