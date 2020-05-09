package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ShortVideoTranscodeEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_short_video_transcode]表 接口类
 * @author yangjunming
 * @Date 2019-04-09 18:50:14
 *
 */
public interface IShortVideoTranscodeContract extends BaseContract<ShortVideoTranscodeEntity> {
	
	public int updateStatus(long videoId,int status,String description) throws Exception;
	
	public int updateTransTimes(long videoId) throws Exception;
}
