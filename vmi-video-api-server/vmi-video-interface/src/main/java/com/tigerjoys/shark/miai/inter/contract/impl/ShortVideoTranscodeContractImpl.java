package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IShortVideoTranscodeContract;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoTranscodeEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ShortVideoTranscodeMapper;

/**
 * 数据库中  [t_short_video_transcode]表 接口实现类
 * @author yangjunming
 * @Date 2019-04-09 18:50:14
 *
 */
@Repository
public class ShortVideoTranscodeContractImpl extends AbstractBaseContract<ShortVideoTranscodeEntity , ShortVideoTranscodeMapper> implements IShortVideoTranscodeContract {

	@Override
	public int updateStatus(long videoId, int status, String description) throws Exception {
		return mapper.updateByStatement("status="+status+",description='"+description+"',update_time=now()", "videoId="+videoId);
	}

	@Override
	public int updateTransTimes(long videoId) throws Exception {
		return mapper.updateByStatement("trans_times=trans_times+1", "videoId="+videoId);
	}
	
	
}
