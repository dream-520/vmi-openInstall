package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorLevelManagerEntity;

/**
 * 数据库中  主播升级体系[t_anchor_level_manager]表 接口类
 * @author yangjunming
 * @Date 2019-08-27 11:24:01
 *
 */
public interface IAnchorLevelManagerContract extends BaseContract<AnchorLevelManagerEntity> {
	
	public AnchorLevelManagerEntity getStar(int star) throws Exception;
}
