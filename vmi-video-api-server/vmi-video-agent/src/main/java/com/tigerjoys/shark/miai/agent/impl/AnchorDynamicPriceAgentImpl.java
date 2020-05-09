package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorDynamicPriceAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IPayUserLabelContract;
import com.tigerjoys.shark.miai.inter.entity.PayUserLabelEntity;

/**
 * 根据用户身份获取主播的动态价格
 * @author shiming
 *
 */
@Service
public class AnchorDynamicPriceAgentImpl implements IAnchorDynamicPriceAgent {

	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IPayUserLabelContract payUserLabelContract;
	
	@Override
	public int getUserScale(long userid) {
		int scale = 0;
		try {
			UserBO bo = userAgent.findById(userid);
			if(Tools.isNotNull(bo)) {
				if((!bo.isWaiter()) && Tools.isNotNull(bo.getLabels())) {
					char[] labels = bo.getLabels().toCharArray();
					if(Tools.isNotNull(labels)) {
						List<String> query = new ArrayList<>();
						//循环处理字符串转换操作
						for (char c : labels) {
							query.add( String.valueOf(c));
						}
						PageModel pageModel = PageModel.getLimitModel(0, 1);
						pageModel.addQuery(Restrictions.in("name", query));
						pageModel.desc("ratio");
						List<PayUserLabelEntity> list = payUserLabelContract.load(pageModel);
						if(Tools.isNotNull(list)) {
							PayUserLabelEntity entity = list.get(0);
							if(Tools.isNotNull(entity)) {
								scale = entity.getRatio();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		return scale;
	}

	@Override
	public int getAnchorDynamicPrice(long userid, int price) {
		int dynamicPrice = price;
		int scale = getUserScale(userid);
		if(scale < 100)
			dynamicPrice = price + (int)(1.0 * scale / 100 * price);
		return dynamicPrice;
	}

}
