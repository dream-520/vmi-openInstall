package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IUserBlacklistContract;
import com.tigerjoys.shark.miai.inter.entity.UserBlacklistEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserBlacklistMapper;

/**
 * 数据库中  用户黑名单表[t_user_blacklist]表 接口实现类
 * @author lipeng
 * @Date 2017-08-17 11:01:38
 *
 */
@Repository
public class UserBlacklistContractImpl extends AbstractBaseContract<UserBlacklistEntity , UserBlacklistMapper> implements IUserBlacklistContract {

	@Override
	public UserBlacklistEntity getUserBlackList(long userid, long blackid) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("blackid", blackid));
		
		List<UserBlacklistEntity> list = mapper.load(pageModel);
		return Tools.isNull(list)?null:list.get(0);
	}
	
}
