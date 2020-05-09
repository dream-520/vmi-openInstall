package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ShortVideoWatchDayLogEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  短视频每日观看数[t_short_video_watch_day_log]表 接口类
 * @author yangjunming
 * @Date 2019-01-02 15:45:58
 *
 */
public interface IShortVideoWatchDayLogContract extends BaseContract<ShortVideoWatchDayLogEntity> {
	
	/**
	 * 添加当天观看次数
	 * @param videoId
	 * @return
	 * @throws Exception
	 */
	public int addWatchNum(long videoId) throws Exception;
}
