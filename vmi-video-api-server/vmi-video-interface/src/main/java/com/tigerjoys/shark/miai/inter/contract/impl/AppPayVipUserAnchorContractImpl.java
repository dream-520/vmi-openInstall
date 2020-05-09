package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppPayVipUserAnchorContract;
import com.tigerjoys.shark.miai.inter.entity.AppPayVipUserAnchorEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppPayVipUserAnchorMapper;

/**
 * 数据库中  用户购买vip后对应主播发送联系方式的记录表[t_app_pay_vip_user_anchor]表 接口实现类
 * @author shiming
 * @Date 2020-02-14 15:09:15
 *
 */
@Repository
public class AppPayVipUserAnchorContractImpl extends AbstractBaseContract<AppPayVipUserAnchorEntity , AppPayVipUserAnchorMapper> implements IAppPayVipUserAnchorContract {
	
}
