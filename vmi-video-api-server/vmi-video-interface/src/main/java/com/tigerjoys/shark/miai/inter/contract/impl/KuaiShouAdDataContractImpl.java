package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IKuaiShouAdDataContract;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdDataEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.KuaiShouAdDataMapper;

/**
 * 数据库中  快手下发的广告数据[t_kuai_shou_ad_data]表 接口实现类
 * @author shiming
 * @Date 2019-10-06 17:35:46
 *
 */
@Repository
public class KuaiShouAdDataContractImpl extends AbstractBaseContract<KuaiShouAdDataEntity , KuaiShouAdDataMapper> implements IKuaiShouAdDataContract {
	
}
