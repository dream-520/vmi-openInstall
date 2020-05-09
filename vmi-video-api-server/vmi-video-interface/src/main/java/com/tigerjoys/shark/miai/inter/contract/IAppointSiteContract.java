package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.AppointSiteEntity;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  约定场地[t_appoint_site]表 接口类
 * @author yangjunming
 * @Date 2017-12-13 16:14:35
 *
 */
public interface IAppointSiteContract extends BaseContract<AppointSiteEntity> {
	
	/**
	 * 随机取出pageSize条记录
	 * @param pageSize - 订单ID
	 * @return AppointSiteEntity
	 */
	public  List<AppointSiteEntity> loadRandomList(int pageSize);
	
}
