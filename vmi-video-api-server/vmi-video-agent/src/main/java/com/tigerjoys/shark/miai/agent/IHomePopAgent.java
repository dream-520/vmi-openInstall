package com.tigerjoys.shark.miai.agent;

import java.util.Date;
import java.util.List;

import com.tigerjoys.shark.miai.inter.entity.HomePopEntity;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;

/**
 * 首页弹窗业务接口定义
 * @author liuman
 *
 */
public interface IHomePopAgent {
	
	/**
	 * 查询首页弹窗数据
	 * @return
	 * @throws Exception
	 */
	public List<HomePopEntity> getHomePopList(int platform) throws Exception;
	
	/**
	 * 查询今天是否首页是否有弹出窗
	 * @param clientId
	 * @param popId
	 * @return
	 * @throws Exception
	 */
	public List<HomePopLogEntity> getHomePopLogList(String clientId,long popId) throws Exception;
	
	/**
	 * 增加日志
	 * @param clientId
	 * @param popId
	 * @param userId
	 * @throws Exception
	 */
	public void addHomePopLog(String clientId,long popId,long userId) throws Exception;
	
	/**
	 * 更新日志
	 * @param clientId
	 * @param popLogId
	 * @param count
	 * @param currDate 当前更新时间
	 * @throws Exception
	 */
	public void updateHomePopLog(String clientId,long popLogId, int count,Date currDate) throws Exception;
	
	/**
	 * 获取当天日期弹窗的记录
	 * @param clientId
	 * @param popId
	 * @return
	 * @throws Exception
	 */
	public List<HomePopLogEntity> getHomePopLogByToday(String clientId, long popId) throws Exception ;
}
