package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.shark.miai.inter.contract.IBannerGroupContract;
import com.tigerjoys.shark.miai.inter.entity.BannerGroupEntity;
import com.tigerjoys.shark.miai.inter.mapper.BannerGroupMapper;

/**
 * 数据库中  banner推荐组[t_banner_group]表 接口实现类
 * @author chengang
 * @Date 2017-05-17 11:09:55
 *
 */
@Repository
public class BannerGroupContractImpl extends RedisCacheContract<BannerGroupEntity , BannerGroupMapper> implements IBannerGroupContract {
	
}
