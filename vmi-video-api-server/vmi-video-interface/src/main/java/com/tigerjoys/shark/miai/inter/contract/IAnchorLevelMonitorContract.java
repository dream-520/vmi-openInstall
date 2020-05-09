package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AnchorLevelMonitorEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  主播评级体系[t_anchor_level_monitor]表 接口类
 * @author yangjunming
 * @Date 2019-08-27 11:24:01
 *
 */
public interface IAnchorLevelMonitorContract extends BaseContract<AnchorLevelMonitorEntity> {
	
	/**
	 * 更新成同步状态
	 * @param anchorId  更新主播状态
	 * @param type  2  色情  4 低俗
	 * @return
	 * @throws Exception
	 */
	public int updateSyncData(Long anchorId) throws Exception;
	
	
	/**
	 * 更新updateTime
	 * @param anchorId  更新主播状态
	 * @param type  2  色情  4 低俗
	 * @return
	 * @throws Exception
	 */
	public int setUpdateTime(Long id) throws Exception;
}
