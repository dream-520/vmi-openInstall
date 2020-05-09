package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorEvaluationLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorEvaluationLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorEvaluationLogMapper;

/**
 * 数据库中  主播评价日志[t_anchor_evaluation_log]表 接口实现类
 * @author yangjunming
 * @Date 2020-02-25 15:02:20
 *
 */
@Repository
public class AnchorEvaluationLogContractImpl extends AbstractBaseContract<AnchorEvaluationLogEntity , AnchorEvaluationLogMapper> implements IAnchorEvaluationLogContract {
	
}
