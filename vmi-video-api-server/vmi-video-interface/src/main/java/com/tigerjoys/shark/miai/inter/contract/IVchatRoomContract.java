package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_vchat_room]表 接口类
 * @author yangjunming
 * @Date 2019-07-18 15:18:56
 *
 */
public interface IVchatRoomContract extends BaseContract<VchatRoomEntity> {
	/**
	 * 普通用户进入房间
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int enterRoomUserId(long orderId,long userid) throws Exception;
	
	
	/**
	 * 主播进入房间
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int enterRoomAnchor(long orderId,long anchorId) throws Exception;
	
	
	/**
	 * 返回结果大于0 表示可以扣一分钟的费用
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int pay(long orderId) throws Exception;
	
	
	/**
	 * 返回结果大于0 表示可以扣一分钟的费用
	 * @param userid
	 * @param firstEntry    是否第一次计费
	 * @return    返回 大于0 可以扣费  返回2为第一次扣费      
	 * @throws Exception
	 */
	public int payYX(long id,boolean firstEntry) throws Exception;
	
	/**
	 * 退出的时候核查扣费请况
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int payYXRecvExit(long id) throws Exception;
	
	
	/**
	 * 更新时长和余额
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateTotalPrice(long id,int price) throws Exception;
	
	/**
	 * 更新免费聊时长
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateFreeVchatDuration(long id) throws Exception;
	
	
	/**
	 * 更新周卡时长
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateWeekCardDuration(long id) throws Exception;
	
	
	/**
	 * 更新免费聊时长
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateFreeVchartFalg(long id,int freeVchartFalg) throws Exception;
	
	/**
	 * 更新通话结束时间
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateFinishTime(long id) throws Exception;
	
	/**
	 * 更新通话结束时间
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateIncomeFalg(long id,int status) throws Exception;
	
	
	/**
	 * 更新拨打标记（15秒内不计算拨打率）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateDialingFalg(long id) throws Exception;
	
	/**
	 * 更新网易通话标识
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateWyChatId(long id,long wyChatId) throws Exception;
	
	/**
	 * 用户退出原因
	 * @param id
	 * @param userid
	 * @param shutInfo
	 * @return
	 * @throws Exception
	 */
	public int userExit(long id,Long userid,String shutInfo) throws Exception;
	
	
	/**
	 * 监黄
	 * @param orderid  订单号
	 * @param type  2  色情  4 低俗
	 * @return
	 * @throws Exception
	 */
	public int checkPorn(long orderid,Long userid, int type) throws Exception;
	
	
	/**
	 * 更新网易通话标识
	 * @param id
	 * @param incomeFalg  收益入账 0 未入账  1 已入账 2 无收益
	 * @return
	 * @throws Exception
	 */
	public int updateFreeIncomeFalg(long id,int incomeFalg) throws Exception;
	
	
	/**
	 * 更新通话结束时间
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateSysOffsetDuration(long id,int second) throws Exception;
	
	/**
	 * 系统异常关闭服务
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int closeExceptionVchatRoom(long id) throws Exception;
	
}
