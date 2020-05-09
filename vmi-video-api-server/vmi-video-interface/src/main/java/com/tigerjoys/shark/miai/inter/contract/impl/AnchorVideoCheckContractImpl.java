package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorVideoCheckContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorVideoCheckEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorVideoCheckMapper;

/**
 * 数据库中  通话监黄业务处理表[t_anchor_video_check]表 接口实现类
 * @author shiming
 * @Date 2019-08-23 15:12:48
 *
 */
@Repository
public class AnchorVideoCheckContractImpl extends AbstractBaseContract<AnchorVideoCheckEntity , AnchorVideoCheckMapper> implements IAnchorVideoCheckContract {
	
}
