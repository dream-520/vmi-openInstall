package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserNoWithdrawalBoundsEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.mapper.UserNoWithdrawalBoundsMapper;

/**
 * 数据库中  用户不可提现收益限额[t_user_no_withdrawal_bounds]表 接口实现类
 * @author yangjunming
 * @Date 2019-08-05 14:15:28
 *
 */
@Repository
public class UserNoWithdrawalBoundsContractImpl extends AbstractBaseContract<UserNoWithdrawalBoundsEntity , UserNoWithdrawalBoundsMapper> implements IUserNoWithdrawalBoundsContract {
	@Override
	public UserNoWithdrawalBoundsEntity lockByUserId(long userId, int type){
		return mapper.lockByUserId(userId, type);
	}

	@Override
	public UserNoWithdrawalBoundsEntity findByUserIdAndType(long userId, int type) {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		pageModel.addQuery(Restrictions.eq("type",type));
		List<UserNoWithdrawalBoundsEntity> list = mapper.load(pageModel);
		if(Tools.isNotNull(list)){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int updateBalance(long userId, int type,int io,long amount){
		UserNoWithdrawalBoundsEntity entity = findByUserIdAndType(userId, type);
		Date current = new Date();
		int rows = 0;
		if(Tools.isNull(entity)){
			entity = new UserNoWithdrawalBoundsEntity();
			entity.setUser_id(userId);
			entity.setType(type);
			if(io == 1){
				entity.setBalance(amount);
			}else{
				entity.setBalance(0L);
			}
			entity.setCreate_time(current);
			entity.setUpdate_time(current);
			mapper.insert(entity);
			rows = 1;
		}else{
			StringBuilder sb = new StringBuilder();
			if (1 == io) {// 收入
				sb.append("balance=balance+").append(amount);
			} else {// 提取
				sb.append("balance=CASE WHEN balance<"+amount+" THEN  0 else balance-"+amount+"  END ");
			}
			rows = mapper.updateByStatement(sb.toString(), "id=" + entity.getId());
		}
		return rows;
	}
	
}
