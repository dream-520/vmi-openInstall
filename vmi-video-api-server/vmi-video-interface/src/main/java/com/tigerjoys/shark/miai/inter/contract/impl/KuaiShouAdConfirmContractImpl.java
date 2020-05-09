package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IKuaiShouAdConfirmContract;
import com.tigerjoys.shark.miai.inter.entity.KuaiShouAdConfirmEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.KuaiShouAdConfirmMapper;

/**
 * 数据库中  [t_kuai_shou_ad_confirm]表 接口实现类
 * @author shiming
 * @Date 2019-10-07 16:51:03
 *
 */
@Repository
public class KuaiShouAdConfirmContractImpl extends AbstractBaseContract<KuaiShouAdConfirmEntity , KuaiShouAdConfirmMapper> implements IKuaiShouAdConfirmContract {
	
}
