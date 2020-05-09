package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserDiamondPriceWatchLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondPriceWatchLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.UserDiamondPriceWatchLogMapper;

/**
 * 数据库中  用户查看钻石价格列表初始记录[t_user_diamond_price_watch_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-26 15:24:32
 *
 */
@Repository
public class UserDiamondPriceWatchLogContractImpl extends AbstractBaseContract<UserDiamondPriceWatchLogEntity , UserDiamondPriceWatchLogMapper> implements IUserDiamondPriceWatchLogContract {
	
}
