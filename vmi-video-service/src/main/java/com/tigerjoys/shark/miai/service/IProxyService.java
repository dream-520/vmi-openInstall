package com.tigerjoys.shark.miai.service;

import java.util.HashMap;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * 代金券服务类
 * @author yangjunming
 *
 */
public interface IProxyService {
	

	/**
	 * 查询代理人收益明细
	 * @param   用户分享映射表id 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> queryProxy(long id) throws Exception;

	/**
	 * 查询代理人分类统计明细
	 * @param userId   代理人ID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> queryProxyInviteMapping(long userId) throws Exception;
	
	/**
	 * 查询代理人员统计
	 * @param userId	代理人ID
	 * @param pageSize	每页行数
	 * @param stamp		分页戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryPersonnelMapping(long userId, int pageSize, long stamp) throws Exception;
	
	/**
	 * 查询代理人员分类明细
	 * @param userId	分类ID
	 * @param pageSize	每页行数
	 * @param stamp		分页戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryPersonnelDesc(long mappingid, int pageSize, long stamp) throws Exception;
	
	/**
	 * 查询代理分成统计
	 * @param userId	代理人ID
	 * @param pageSize	每页行数
	 * @param stamp		分页戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryDividedMapping(long userId, int pageSize, long stamp) throws Exception;

	/**
	 * 查询代理分成分类明细
	 * @param userId	分类ID
	 * @param pageSize	每页行数
	 * @param stamp		分页戳
	 * @return
	 * @throws Exception
	 */
	public ActionResult queryDividedDesc(long mappingid, int pageSize, long stamp) throws Exception;
	
}
