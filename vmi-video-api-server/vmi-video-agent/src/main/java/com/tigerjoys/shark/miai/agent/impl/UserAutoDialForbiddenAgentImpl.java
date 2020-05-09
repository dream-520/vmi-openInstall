package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAutoDialForbiddenAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserAutoDialForbiddenContract;
import com.tigerjoys.shark.miai.inter.entity.UserAutoDialForbiddenEntity;

@Service
public class UserAutoDialForbiddenAgentImpl implements IUserAutoDialForbiddenAgent {

	@Autowired
	private IUserAutoDialForbiddenContract userAutoDialForbiddenContract;
	
	@Override
	public Set<Long> getUserIdList() throws Exception {
		Set<Long> userIdSet = new HashSet<>();
		HashMap<Long,String> outMap = new HashMap<Long, String>();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<UserAutoDialForbiddenEntity> userSmsList = userAutoDialForbiddenContract.load(pageModel);
		if(Tools.isNotNull(userSmsList)){
			userSmsList.forEach(v->{
				if(Tools.isNotNull(v.getUserid())){
					userIdSet.add(v.getUserid());
				}
				
			});
		}
		return userIdSet;
	}

}
