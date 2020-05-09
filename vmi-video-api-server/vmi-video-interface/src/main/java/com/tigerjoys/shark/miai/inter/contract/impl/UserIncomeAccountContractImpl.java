package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountContract;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.mapper.UserIncomeAccountMapper;

/**
 * 数据库中  用户收益账户[t_user_income_account]表 接口实现类
 * @author mouzhanpeng
 * @Date 2017-08-16 15:37:02
 *
 */
@Repository
public class UserIncomeAccountContractImpl extends AbstractBaseContract<UserIncomeAccountEntity , UserIncomeAccountMapper> implements IUserIncomeAccountContract {
	
	@Override
	public UserIncomeAccountEntity lockByUserId(long userId, int type){
		return mapper.lockByUserId(userId, type);
	}

	@Override
	public UserIncomeAccountEntity findByUserIdAndType(long userId, int type) {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		pageModel.addQuery(Restrictions.eq("type",type));
		List<UserIncomeAccountEntity> list = mapper.load(pageModel);
		if(Tools.isNotNull(list)){
			return list.get(0);
		}
		return null;
	}
	
	
}
