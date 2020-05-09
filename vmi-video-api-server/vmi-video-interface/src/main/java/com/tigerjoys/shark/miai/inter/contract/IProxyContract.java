package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.ProxyEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  [t_proxy]表 接口类
 * @author yangjunming
 * @Date 2017-08-18 19:54:41
 *
 */
public interface IProxyContract extends BaseContract<ProxyEntity> {
	/**
	 * 增加收益
	 * @param userid
	 * @param amount    金额
	 * @param times		时长
	 * @param dividedAmount	分成
	 * @return
	 * @throws Exception
	 */
	public int addIncome(long userid, int amount, int times,int dividedAmount) throws Exception;
	/**
	 * 更新状态
	 * @param userid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int updateProxyStatus(long userid, int status) throws Exception;
	/**
	 * 删除代理
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteProxy(long id) throws Exception;
	/**
	 * 根据用户查找对象
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public ProxyEntity findByUserid(long userid) throws Exception;
	
	/**
	 * 累计达人VIP人数
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int addProxyTalentVip(long userid) throws Exception;
	
	/**
	 * 累计邀请人数
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int addProxyUser(long userid) throws Exception;
	
	/**
	 * 代理人收益提现
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int proxyWithdram(long id,int amount) throws Exception;
}
