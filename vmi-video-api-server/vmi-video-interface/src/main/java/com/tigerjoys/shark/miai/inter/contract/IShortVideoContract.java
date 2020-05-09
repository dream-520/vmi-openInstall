package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_short_video]表 接口类
 * @author yangjunming
 * @Date 2019-01-02 15:45:57
 *
 */
public interface IShortVideoContract extends BaseContract<ShortVideoEntity> {
	
	/**
	 * 观看数累计
	 * @param videoId
	 * @return
	 * @throws Exception
	 */
	public int addWatchNum(long videoId) throws Exception;
	
	/**
	 * 更新点赞数
	 * @param videoId
	 * @param value     增加1或减 1
	 * @return
	 * @throws Exception
	 */
	public int updatePraise(long videoId,int value) throws Exception;
}
