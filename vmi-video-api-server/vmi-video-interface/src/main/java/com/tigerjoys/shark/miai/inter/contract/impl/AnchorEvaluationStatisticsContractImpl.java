package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorEvaluationStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorEvaluationStatisticsEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorEvaluationStatisticsMapper;

/**
 * 数据库中 主播评价统计[t_anchor_evaluation_statistics]表 接口实现类
 * 
 * @author yangjunming
 * @Date 2018-11-05 18:14:49
 *
 */
@Repository
public class AnchorEvaluationStatisticsContractImpl extends AbstractBaseContract<AnchorEvaluationStatisticsEntity, AnchorEvaluationStatisticsMapper> implements IAnchorEvaluationStatisticsContract {

	@Override
	public int addEvaluationNum(long userid, Long labelId) throws Exception {
		int rows = mapper.updateByStatement("total_num=total_num+1", "userid=" + userid + " and label_id=" + labelId);
		if (rows == 0) {
			AnchorEvaluationStatisticsEntity entity = new AnchorEvaluationStatisticsEntity();
			entity.setUserid(userid);
			entity.setLabel_id(labelId);
			entity.setTotal_num(1);
			entity.setUpdate_time(new Date());
			mapper.insert(entity);
			rows = 1;
		}
		return rows;
	}
}
