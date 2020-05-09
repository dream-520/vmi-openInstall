package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IAppointSiteContract;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.AppointSiteMapper;

/**
 * 数据库中  约定场地[t_appoint_site]表 接口实现类
 * @author yangjunming
 * @Date 2017-12-13 16:14:35
 *
 */
@Repository
public class AppointSiteContractImpl extends AbstractBaseContract<AppointSiteEntity , AppointSiteMapper> implements IAppointSiteContract {

	@Override
	public  List<AppointSiteEntity> loadRandomList(int pageSize) {
		return mapper.loadRandomList(pageSize);
	}
	
}
