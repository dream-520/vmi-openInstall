package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsEntity;
import com.tigerjoys.shark.miai.inter.mapper.AnchorIntimateRankingsMapper;

/**
 * 数据库中  主播亲密排行榜[t_anchor_intimate_rankings]表 接口实现类
 * @author yangjunming
 * @Date 2018-10-30 10:16:48
 *
 */
@Repository
public class AnchorIntimateRankingsContractImpl extends AbstractBaseContract<AnchorIntimateRankingsEntity , AnchorIntimateRankingsMapper> implements IAnchorIntimateRankingsContract {
	
	@Override
	public int addIncome(long anchorUserId, Long uesrid,Integer price) throws Exception {
		int rows = mapper.updateByStatement("total_money=total_money+"+price+",update_time=now()", "anchor_userid="+anchorUserId+" and userid="+uesrid);
		if(rows ==0){
			AnchorIntimateRankingsEntity entity = new AnchorIntimateRankingsEntity();
			entity.setAnchor_userid(anchorUserId);
			entity.setUserid(uesrid);
			entity.setTotal_money(price);
			entity.setUpdate_time(new Date());
			mapper.insert(entity);
			rows = 1;
		}
		return rows;
	}
}
