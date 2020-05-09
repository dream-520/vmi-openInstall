package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorImageAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.inter.contract.IAnchorImageContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorImageEntity;

@Service
public class AnchorImageAgentImpl implements IAnchorImageAgent {


	@Autowired
	private IAnchorImageContract anchorImageContract;
	
	@Override
	public List<String> getAnchorImage(long userid) throws Exception {
		List<String> paths = null;
		if(userid > 0) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("state", 1));
			List<AnchorImageEntity> images = anchorImageContract.load(pageModel);
			if(Tools.isNotNull(images) && images.size() > 0) {
				paths = new ArrayList<>();
				for (AnchorImageEntity image : images) {
					paths.add(Const.getCdn(image.getPath()));
				}
			}
		}
		return paths;
	}
}
