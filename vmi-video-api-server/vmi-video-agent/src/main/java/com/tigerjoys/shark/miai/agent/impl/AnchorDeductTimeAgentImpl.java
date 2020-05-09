package com.tigerjoys.shark.miai.agent.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorDeductTimeAgent;
import com.tigerjoys.shark.miai.inter.contract.IAnchorDeductContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorDeductEntity;

@Service
public class AnchorDeductTimeAgentImpl implements IAnchorDeductTimeAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAnchorDeductContract anchorDeductContract;
	
	@Override
	public int anchorDeductTime(long userid, int second) {
		int deductTime = 0;
		try {
			//首先根据的主播id和秒数在配置表中获取对应的配置信息
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.gt("time_start", second));
			pageModel.addQuery(Restrictions.le("time_end", second));
			pageModel.asc("time_start");
			List<AnchorDeductEntity> deducts = anchorDeductContract.load(pageModel);
			if(Tools.isNotNull(deducts) && deducts.size() > 0) {
				//安装运营配置的参数计算抵扣时间
				AnchorDeductEntity deduct = deducts.get(0);
				if(Tools.isNotNull(deduct)) {
					deductTime = (int) Math.floor(1.0 * second * deduct.getDeduct() / 100);
				}
			} else {
				//没有获取对应的配置信息 走默认的抵扣配置信息
				if(second > 60 && second <= 3 * 60) {
					deductTime = (int) Math.floor(1.0 * second * 5 / 100);
				} else if(second > 3 * 60 && second <= 10 * 60) {
					deductTime = (int) Math.floor(1.0 * second * 10 / 100);
				} else if(second > 10 * 60 && second <= 20 * 60) {
					deductTime = (int) Math.floor(1.0 * second * 12 / 100);
				} else if(second > 20 * 60) {
					deductTime = (int) Math.floor(1.0 * second * 15 / 100);
				}
			}
		} catch (Exception e) {
			logger.error("计算主播抵扣信息出现了错误");
		}
		return deductTime;
	}

}
