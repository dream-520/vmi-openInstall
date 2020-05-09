package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppPayVipUserContract;
import com.tigerjoys.shark.miai.inter.entity.AppPayVipUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppPayVipUserMapper;

/**
 * 数据库中  用于标识首次购买vip后用户是否发送完对应消息的统计表[t_app_pay_vip_user]表 接口实现类
 * @author shiming
 * @Date 2020-02-14 15:09:15
 *
 */
@Repository
public class AppPayVipUserContractImpl extends AbstractBaseContract<AppPayVipUserEntity , AppPayVipUserMapper> implements IAppPayVipUserContract {
	
}
