package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IForbiddenRechargeAgent;
import com.tigerjoys.shark.miai.inter.contract.IForbiddenRechargeContract;
import com.tigerjoys.shark.miai.inter.entity.ForbiddenRechargeEntity;

@Service
public class ForbiddenRechargeAgentImpl implements IForbiddenRechargeAgent {
	
	@Autowired
	private IForbiddenRechargeContract forbiddenRechargeContract;
	
	@Override
	public Map<String, String> getForbiddenRechargeList() throws Exception {
		HashMap<String,String> outMap = new HashMap<String, String>();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<ForbiddenRechargeEntity> forbiddenList = forbiddenRechargeContract.load(pageModel);
		if(Tools.isNotNull(forbiddenList)){
			forbiddenList.forEach(v->{
				outMap.put(v.getUserid()+""+"", v.getUserid()+"");
				if (Tools.isNotNull(v.getForbidden_text())){
					String[]array =  Tools.split(v.getForbidden_text(), "\n");
					for(String re:array){
						outMap.put(Tools.trim(re), v.getUserid()+"");
					}
				}
			});
		}
		
		return outMap;
	}


	
	

}
