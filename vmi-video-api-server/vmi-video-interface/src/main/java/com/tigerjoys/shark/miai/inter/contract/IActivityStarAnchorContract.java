package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ActivityStarAnchorEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  女神之星每期主播信息[t_activity_star_anchor]表 接口类
 * @author shiming
 * @Date 2019-08-02 15:14:40
 *
 */
public interface IActivityStarAnchorContract extends BaseContract<ActivityStarAnchorEntity> {
	
	public void updateAnchorCharm(int issue, long userid, int charm);
}
