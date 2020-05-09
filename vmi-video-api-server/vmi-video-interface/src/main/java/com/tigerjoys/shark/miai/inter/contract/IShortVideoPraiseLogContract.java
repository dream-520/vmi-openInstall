package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ShortVideoPraiseLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_short_video_praise_log]表 接口类
 * @author yangjunming
 * @Date 2018-10-29 17:51:26
 *
 */
public interface IShortVideoPraiseLogContract extends BaseContract<ShortVideoPraiseLogEntity> {
	
	public int  updatePraiseLogStatus(Long videoId,long userid) throws Exception; 
	
}
