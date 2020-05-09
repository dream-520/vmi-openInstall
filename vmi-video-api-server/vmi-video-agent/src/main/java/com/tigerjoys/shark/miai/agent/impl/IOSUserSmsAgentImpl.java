package com.tigerjoys.shark.miai.agent.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IIOSUserSmsAgent;
import com.tigerjoys.shark.miai.inter.contract.IIosUserLoginSmsContract;
import com.tigerjoys.shark.miai.inter.entity.IosUserLoginSmsEntity;

@Service
public class IOSUserSmsAgentImpl implements IIOSUserSmsAgent {
	
	@Autowired
	private IIosUserLoginSmsContract iosUserLoginSmsContract;
	

	@Override
	public Map<String, String> getUserSmsList() throws Exception {
		HashMap<String,String> outMap = new HashMap<String, String>();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<IosUserLoginSmsEntity> userSmsList = iosUserLoginSmsContract.load(pageModel);
		if(Tools.isNotNull(userSmsList)){
			userSmsList.forEach(v->{
				outMap.put(v.getMobile()+"", v.getSms_text());
			});
		}
		
		return outMap;
	}


	@Override
	public Map<Long, String> getUserIdList() throws Exception {
		HashMap<Long,String> outMap = new HashMap<Long, String>();
		PageModel pageModel = new PageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<IosUserLoginSmsEntity> userSmsList = iosUserLoginSmsContract.load(pageModel);
		if(Tools.isNotNull(userSmsList)){
			userSmsList.forEach(v->{
				if(Tools.isNotNull(v.getUserid())){
					outMap.put(v.getUserid(), v.getMobile()+"");
				}
				
			});
		}
		
		return outMap;
	}
	
	

}
