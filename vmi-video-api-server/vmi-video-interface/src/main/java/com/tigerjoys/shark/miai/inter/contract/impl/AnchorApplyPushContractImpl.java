package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorApplyPushContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorApplyPushEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorApplyPushMapper;

/**
 * 数据库中  记录对应的主播审核通过发送通知的处理[t_anchor_apply_push]表 接口实现类
 * @author shiming
 * @Date 2019-07-06 17:36:11
 *
 */
@Repository
public class AnchorApplyPushContractImpl extends AbstractBaseContract<AnchorApplyPushEntity , AnchorApplyPushMapper> implements IAnchorApplyPushContract {
	
}
