package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.RedisCacheContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IBannerContract;
import com.tigerjoys.shark.miai.inter.contract.IBannerGroupContract;
import com.tigerjoys.shark.miai.inter.entity.BannerEntity;
import com.tigerjoys.shark.miai.inter.entity.BannerGroupEntity;
import com.tigerjoys.shark.miai.inter.mapper.BannerMapper;

/**
 * 数据库中  banner推荐表[t_banner]表 接口实现类
 * @author chengang
 * @Date 2017-05-17 11:09:55
 *
 */
@Repository
public class BannerContractImpl extends RedisCacheContract<BannerEntity , BannerMapper> implements IBannerContract {
	
	@Autowired
	private IBannerGroupContract bannerGroupContract;
	
	@Override
	public List<BannerEntity> getBannerByGroupCode(String code, int start, int pagesize) throws Exception {
		if(code == null || code.length() == 0) return null;
		
		BannerGroupEntity group = bannerGroupContract.findByProperty("code", code);
		if(group == null || group.getStatus() != 1) return null;
		
		PageModel pageModel = PageModel.getLimitModel(start, pagesize);
		pageModel.addQuery(Restrictions.eq("groupid", group.getId()));
		pageModel.addQuery(Restrictions.eq("status", 1));
		
		pageModel.asc("priority");
		pageModel.desc("id");
		
		return load(pageModel);
	}
	
	@Override
	public List<BannerEntity> getBannerByGroupCode(String code, int start, int pagesize, int scope) throws Exception {
		if(code == null || code.length() == 0) return null;
		
		BannerGroupEntity group = bannerGroupContract.findByProperty("code", code);
		if(group == null || group.getStatus() != 1) return null;
		
		PageModel pageModel = PageModel.getLimitModel(start, pagesize);
		pageModel.addQuery(Restrictions.eq("groupid", group.getId()));
		pageModel.addQuery(Restrictions.eq("status", 1));
		
		if(scope == 1) {
			pageModel.addQuery(Restrictions.in("scope", 0, 1));
		} else if(scope == 2) {
			pageModel.addQuery(Restrictions.in("scope", 0, 2));
		}
		
		pageModel.asc("priority");
		pageModel.desc("id");
		
		return load(pageModel);
	}
	
	@Override
	public List<BannerEntity> getBannerByGroupCodeTimes(String code, int start, int pagesize) throws Exception {
		if(code == null || code.length() == 0) return null;
		
		BannerGroupEntity group = bannerGroupContract.findByProperty("code", code);
		if(group == null || group.getStatus() != 1) return null;
		
		//获得五分钟前的时间，默认每5分钟缓存失效一次
		Date currDate = new Date(Tools.getMinuteTime(5));
		
		PageModel pageModel = PageModel.getLimitModel(start, pagesize);
		pageModel.addQuery(Restrictions.eq("groupid", group.getId()));
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.le("starttime", currDate));
		pageModel.addQuery(Restrictions.ge("endTime", currDate));
		
		pageModel.asc("priority");
		pageModel.desc("id");
		
		return load(pageModel);
	}

	@Override
	public List<BannerEntity> getBannerByGroupCodeOne(String code, int start, int pagesize) throws Exception {
		if(code == null || code.length() == 0) 
			return null;
		BannerGroupEntity group = bannerGroupContract.findByProperty("code", code);
		if(group == null || group.getStatus() != 1) 
			return null;
		PageModel pageModel = PageModel.getLimitModel(start, pagesize);
		pageModel.addQuery(Restrictions.eq("groupid", group.getId()));
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.desc("priority");
		pageModel.desc("id");
		return load(pageModel);
	}
	
}
