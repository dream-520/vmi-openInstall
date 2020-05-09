package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerContract;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerEntity;
import com.tigerjoys.shark.miai.service.IRedFlowerService;

/**
 * @author mouzhanpeng at [2017年12月20日 下午6:19:56]
 * @since JDK 1.8.0
 */
@Service
public class RedFlowerServiceImpl implements IRedFlowerService {

	@Autowired
	private IRedFlowerAgent redFlowerAgent;

	@Autowired
	private IRedFlowerContract redFlowerContract;

	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis; 
	

	@Override
	public ActionResult redFlowerHome() throws Exception {
		BeatContext context = RequestUtils.getCurrent();
		// 保证赠送成功,不会重复赠送
		donorRedFlowers(context.getUserid());

		Map<String, Object> data = new HashMap<>();
		data.put("balance", Tools.parseLong(redFlowerAgent.getTotalFlowers(context.getUserid()).getData()));
		StringBuilder sb = new StringBuilder();
		sb.append("权益：解锁聊天权限\n");
		sb.append("新用户赠送" + Const.RED_FLOWERS_DAILY + "朵小红花\n");
		sb.append("使用说明：系统赠送和奖励的当天有效，购买的长期有效");
		data.put("introductions", sb.toString());
		PageModel pm = PageModel.getPageModel();
		pm.addQuery(Restrictions.eq("status", 1));
		pm.asc("amount");
		List<RedFlowerEntity> list = redFlowerContract.load(pm);
		if (Tools.isNotNull(list)) {
			List<Map<String, Object>> prices = new ArrayList<>(list.size());
			for (RedFlowerEntity entity : list) {
				Map<String, Object> one = new HashMap<>();
				one.put("flowerId", entity.getId());
				one.put("amount", entity.getAmount());
				one.put("diamond", entity.getDiamond());
				prices.add(one);
			}
			data.put("prices", prices);
		}
		return ActionResult.success(data);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult buyRedFlower(long flowerId) throws Exception {
		return null;
	}

	@Override
	public void donorRedFlowers(long userId) throws Exception {
		//redFlowerAgent.increaseDailyFlower(userId, Const.RED_FLOWERS_DAILY);
	}
	
	@Override
	public ActionResult consumeRedFlower(long otherId) throws Exception {
		return null;
	}
}
