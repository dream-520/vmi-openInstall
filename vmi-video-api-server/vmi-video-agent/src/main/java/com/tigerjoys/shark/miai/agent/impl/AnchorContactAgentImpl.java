package com.tigerjoys.shark.miai.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorContactAgent;
import com.tigerjoys.shark.miai.inter.contract.IAnchorContactLookContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;

@Service
public class AnchorContactAgentImpl implements IAnchorContactAgent {

	@Autowired
	private IAnchorContactLookContract anchorContactLookContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Override
	public int showAnchorContact(long userid, long anchorid) {
		int status = 0;
		try {
			//首先检测对应的用户是否买过了本主播的联系方式
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
			long count = anchorContactLookContract.count(pageModel);
			if(count == 0) {
				//然后检测对应的主播的联系方式开关是否开启
				AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", anchorid);
				if(Tools.isNotNull(anchor)) {
					if(anchor.getContact_on() == 1 && Tools.isNotNull(anchor.getContact_text()) && anchor.getContact_text().length() > 0) {
						status = 1;
					}
				}
			} else {
				status = 1;
			}
		} catch (Exception e) {
			
		}
		return status;
	}

}
