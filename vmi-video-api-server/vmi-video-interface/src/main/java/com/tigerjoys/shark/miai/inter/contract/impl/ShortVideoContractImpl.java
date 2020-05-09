package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IShortVideoContract;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ShortVideoMapper;

/**
 * 数据库中  [t_short_video]表 接口实现类
 * @author yangjunming
 * @Date 2019-01-02 15:45:57
 *
 */
@Repository
public class ShortVideoContractImpl extends AbstractBaseContract<ShortVideoEntity , ShortVideoMapper> implements IShortVideoContract {
	
	@Override
	public int addWatchNum(long videoId) throws Exception {
		return mapper.updateByStatement("watch_num=watch_num+1", "id="+videoId);
	}

	@Override
	public int updatePraise(long videoId, int value) throws Exception {
		return mapper.updateByStatement("video_praise=video_praise+("+value+")", "id="+videoId);
	}
}
