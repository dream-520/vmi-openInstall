package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AnchorEvaluationStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  主播评价统计[t_anchor_evaluation_statistics]表 接口类
 * @author yangjunming
 * @Date 2018-11-05 18:14:49
 *
 */
public interface IAnchorEvaluationStatisticsContract extends BaseContract<AnchorEvaluationStatisticsEntity> {
	
	// 增加大V总计
	public int addEvaluationNum(long userid,Long labelId) throws Exception;
}
