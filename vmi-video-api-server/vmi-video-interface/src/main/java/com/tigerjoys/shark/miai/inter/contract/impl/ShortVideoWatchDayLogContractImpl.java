package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IShortVideoWatchDayLogContract;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoWatchDayLogEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.ShortVideoWatchDayLogMapper;

/**
 * 数据库中  短视频每日观看数[t_short_video_watch_day_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-01-02 15:45:58
 *
 */
@Repository
public class ShortVideoWatchDayLogContractImpl extends AbstractBaseContract<ShortVideoWatchDayLogEntity , ShortVideoWatchDayLogMapper> implements IShortVideoWatchDayLogContract {

	@Override
	public int addWatchNum(long videoId) throws Exception {
		Date current = new Date();
		int currentDate = Integer.parseInt(Tools.getFormatDate(current, "yyyMMdd"));
		int rows = mapper.updateByStatement("watch_num=watch_num+1", "video_id="+videoId+" and watch_time="+currentDate);
		if(rows == 0){
			ShortVideoWatchDayLogEntity log = new ShortVideoWatchDayLogEntity();
			log.setVideo_id(videoId);
			log.setWatch_time(currentDate);
			log.setWatch_num(1);
			log.setCreate_time(current);
			mapper.insert(log);
		}
		return rows;
	}


	
	
}
