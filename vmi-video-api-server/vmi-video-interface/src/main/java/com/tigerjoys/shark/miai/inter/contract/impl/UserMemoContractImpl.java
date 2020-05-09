package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IUserMemoContract;
import com.tigerjoys.shark.miai.inter.entity.UserMemoEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserMemoMapper;

/**
 * 数据库中  用户备注表[t_user_memo]表 接口实现类
 * @author chengang
 * @Date 2017-05-08 11:43:45
 *
 */
@Repository
public class UserMemoContractImpl extends AbstractBaseContract<UserMemoEntity , UserMemoMapper> implements IUserMemoContract {

	@Override
	public UserMemoEntity getUserMemo(long userid, int type) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("type", type));
		
		List<UserMemoEntity> list = mapper.load(pageModel);
		
		if(Tools.isNotNull(list)) return list.get(0);
		return null;
	}
	
}
