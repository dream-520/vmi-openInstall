package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserSpecialRechargeAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserSpecialRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.UserSpecialRechargeEntity;

@Service
public class UserSpecialRechargeAgentImpl implements IUserSpecialRechargeAgent {
	
	@Autowired
	private IUserSpecialRechargeContract userSpecialRechargeContract;

	@Override
	public Set<Long> getUserIdList() throws Exception {
		Set<Long> outMap = new HashSet<Long>();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<UserSpecialRechargeEntity> userSpecialList = userSpecialRechargeContract.load(pageModel);
		if(Tools.isNotNull(userSpecialList)){
			userSpecialList.forEach(v->{
				if(Tools.isNotNull(v.getUserid())){
					outMap.add(v.getUserid());
				}
				
			});
		}
		return outMap;
	}
	
	

}
