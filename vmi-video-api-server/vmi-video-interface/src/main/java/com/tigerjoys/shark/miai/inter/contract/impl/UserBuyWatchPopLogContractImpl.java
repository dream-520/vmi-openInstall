package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IUserBuyWatchPopLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserBuyWatchPopLogEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.mapper.UserBuyWatchPopLogMapper;

/**
 * 数据库中  用户购买弹窗记录[t_user_buy_watch_pop_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-12-04 20:21:31
 *
 */
@Repository
public class UserBuyWatchPopLogContractImpl extends AbstractBaseContract<UserBuyWatchPopLogEntity , UserBuyWatchPopLogMapper> implements IUserBuyWatchPopLogContract {

	@Override
	public UserBuyWatchPopLogEntity getUserBuyWatchPopLog(long userId, int type) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("type", type));
		List<UserBuyWatchPopLogEntity> list = mapper.load(pageModel);
		if (Tools.isNotNull(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	
}
