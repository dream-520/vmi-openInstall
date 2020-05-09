package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserFirstRechargeLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserFirstRechargeLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.mapper.UserFirstRechargeLogMapper;

/**
 * 数据库中  用户首次充值记录[t_user_first_recharge_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-31 15:13:37
 *
 */
@Repository
public class UserFirstRechargeLogContractImpl extends AbstractBaseContract<UserFirstRechargeLogEntity , UserFirstRechargeLogMapper> implements IUserFirstRechargeLogContract {

	@Override
	public boolean checkFirstRecharge(long userid, int type) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		if(type > 0) {
			pageModel.addQuery(Restrictions.eq("type", type));
		}
		long count = mapper.count(pageModel);
		if(count > 0) {
			return false;
		} 
		return true;
		//UserFirstRechargeLogEntity entity = mapper.findByProperty("userid", userid);
		//return Tools.isNull(entity);
	}
	
}
