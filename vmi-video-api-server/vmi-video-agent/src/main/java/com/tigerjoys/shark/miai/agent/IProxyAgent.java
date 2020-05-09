package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.agent.dto.ProxyInletDto;
import com.tigerjoys.shark.miai.agent.dto.UserInviteCpsDto;
import com.tigerjoys.shark.miai.inter.entity.ProxyEntity;

/**
 * 付费代理人
 * @author yangjunming
 *
 */
public interface IProxyAgent {
	
	/**
	 * 根据ID查找代理人对象
	 * @param id - ID
	 * @return PaidAppiontAgentEntity
	 * @throws Exception
	 */
	public ProxyEntity findById(long id) throws Exception;
	

	/**
	 * 根据用户ID查询代理人对象
	 * @param userId - 用户ID
	 * @return ProxyEntity
	 * @throws Exception
	 */
	public ProxyEntity findByUserId(long userId) throws Exception;
	

	
	/**
	 * 新增代理人
	 * @param t - PaidAppointCouponEntity
	 * @throws Exception
	 */
	public void insert(ProxyEntity t) throws Exception;
	
	/**
	 * 批量新增代理人
	 * @param list - List<ProxyEntity>
	 * @throws Exception
	 */
	public void insertAll(List<ProxyEntity> list) throws Exception;
	
	/**
	 * 新增收益
	 * @param userid	邀请者ID
	 * @param inComeid	被邀请者ID
	 * @param orderid	订单ID
	 * @param amount	收入金额
	 * @param times		时长
	 * @param percent	百分比
	 * @return
	 * @throws Exception
	 */
	public int addIncome(long userid,long inComeid,long orderid,Integer amount,Map<String,Object>map,long percent ) throws Exception;
	
	/**
	 * 累计达人VIP人数
	 * @param userid 达人
	 * @return
	 * @throws Exception
	 */
	public int addProxyTalentVip(long userid) throws Exception;
	
	/**
	 *设置用户邀请码
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ActionResult addInvitation(long userid,String inviteCode) throws Exception;
	
	/**
	 * 删除代理人
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(long id) throws Exception;
	
	/**
	 * 根据用户ID查询是否被代理人邀情，并返回线上代理人，还是线下代理人
	 * @param userId - 用户ID
	 * @return ProxyEntity
	 * @throws Exception
	 */
	public ProxyInletDto queryInletByUserId(long userId) throws Exception;
	
	/**
	 * 获取用户邀请等级收益记录
	 * @param userId
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public List<UserInviteCpsDto> getUserInviteCpsIncomeList(long userId,int level) throws Exception;
	
	/**
	 * 添加CPS邀请用户收益
	 * @param userId
	 * @param amount
	 * @throws Exception
	 */
	public void addCpsIncome(long userId,long amount) throws Exception;
}
