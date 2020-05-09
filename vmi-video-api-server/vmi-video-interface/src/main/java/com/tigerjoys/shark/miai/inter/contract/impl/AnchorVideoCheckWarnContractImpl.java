package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorVideoCheckWarnContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorVideoCheckWarnEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorVideoCheckWarnMapper;

/**
 * 数据库中  监黄业务警告表[t_anchor_video_check_warn]表 接口实现类
 * @author shiming
 * @Date 2019-08-23 17:57:18
 *
 */
@Repository
public class AnchorVideoCheckWarnContractImpl extends AbstractBaseContract<AnchorVideoCheckWarnEntity , AnchorVideoCheckWarnMapper> implements IAnchorVideoCheckWarnContract {
	
}
