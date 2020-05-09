package com.tigerjoys.shark.miai.service;

import java.util.List;
import java.util.Map;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.AppointSiteDto;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteEntity;

/**
 * 场地服务类
 * @author yangjunming
 *
 */
public interface IAppointSiteService {
	

	/**
	 * 场地首面数据
	 */
	public Map<String,Object> siteIndex() throws Exception;
	
	/**
	 * 场地分类数据
	 */
	public List<AppointSiteEntity> siteTypeList(int type) throws Exception;
	
	/**
	 * 场地详请
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AppointSiteDto siteDesc(long id) throws Exception;
	
	
	/**
	 * 支付场地费用
	 * @param id
	 * @param channelCode
	 * @param money(元)
	 * @param receipt
	 * @return
	 * @throws Exception
	 */
	public ActionResult recharge(long id, int channelCode, long money, String receipt) throws Exception;
	
}
