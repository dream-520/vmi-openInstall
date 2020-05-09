package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAnchorLevelMonitorContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorLevelMonitorEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AnchorLevelMonitorMapper;

/**
 * 数据库中  主播评级体系[t_anchor_level_monitor]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-27 11:24:01
 *
 */
@Repository
public class AnchorLevelMonitorContractImpl extends AbstractBaseContract<AnchorLevelMonitorEntity , AnchorLevelMonitorMapper> implements IAnchorLevelMonitorContract {

	@Override
	public int updateSyncData(Long anchorId) throws Exception {
		Date current = new Date();
		AnchorLevelMonitorEntity entity = mapper.findByProperty("anchorid", anchorId);
		if(Tools.isNotNull(entity)){
			mapper.updateByStatement("status=2,anchor_update_time=now()", "id="+entity.getId());
		}else{
			entity = new AnchorLevelMonitorEntity();
			entity.setAnchorid(anchorId);
			entity.setCreate_time(current);
			entity.setUpdate_time(current);
			entity.setBegin_time(current);
			entity.setEnd_time(current);
			entity.setStar(1);
			entity.setCheck_times(1);
			entity.setAnchor_update_time(current);
			entity.setStatus(2);
			mapper.insert(entity);
		}
		return 1;
	}

	@Override
	public int setUpdateTime(Long id) throws Exception {
		return mapper.updateByStatement("update_time=now()", "id="+id);
	}
	
	
	
	
}
