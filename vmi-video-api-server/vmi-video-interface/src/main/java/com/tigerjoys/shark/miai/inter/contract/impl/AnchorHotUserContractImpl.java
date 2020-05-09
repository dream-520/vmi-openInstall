package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorHotUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorHotUserEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorHotUserMapper;

/**
 * 数据库中  ios推荐主播类别关系表[t_anchor_hot_user]表 接口实现类
 * @author shiming
 * @Date 2019-03-25 15:25:10
 *
 */
@Repository
public class AnchorHotUserContractImpl extends AbstractBaseContract<AnchorHotUserEntity , AnchorHotUserMapper> implements IAnchorHotUserContract {
	
}
