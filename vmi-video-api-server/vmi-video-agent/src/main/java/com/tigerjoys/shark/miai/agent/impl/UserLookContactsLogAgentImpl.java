package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserLookContactsLogAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserLookContactsLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserLookContactsLogEntity;

/**
 * 用户查看联系方式记录实现类
 * @author lipeng
 *
 */
@Service
public class UserLookContactsLogAgentImpl implements IUserLookContactsLogAgent {
	
	@Autowired
	private IUserLookContactsLogContract userLookContactsLogContract;

	@Override
	public int getCount(long userid) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("create_time", Tools.getDate()));
		return (int) userLookContactsLogContract.count(pageModel);
	}

	@Override
	public boolean ifLooked(long userid, long otherid) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("otherid", otherid));
		pageModel.addQuery(Restrictions.eq("create_time", Tools.getDate()));
		if (userLookContactsLogContract.count(pageModel)>0) {
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void creat(long userid, long otherid) throws Exception {
		UserLookContactsLogEntity entity = new UserLookContactsLogEntity();
		entity.setUserid(userid);
		entity.setOtherid(otherid);
		entity.setCreate_time(new Date());
		userLookContactsLogContract.insert(entity);
	}

	
}
