package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorToUserEvaluationLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorToUserEvaluationLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorToUserEvaluationLogMapper;

/**
 * 数据库中  主播对用户评价日志[t_anchor_to_user_evaluation_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-06-26 20:24:04
 *
 */
@Repository
public class AnchorToUserEvaluationLogContractImpl extends AbstractBaseContract<AnchorToUserEvaluationLogEntity , AnchorToUserEvaluationLogMapper> implements IAnchorToUserEvaluationLogContract {
	
}
