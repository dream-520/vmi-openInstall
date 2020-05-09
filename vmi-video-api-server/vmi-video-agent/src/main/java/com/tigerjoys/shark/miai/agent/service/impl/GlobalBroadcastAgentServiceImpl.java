package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.GlobalBroadcastTypeEnum;
import com.tigerjoys.shark.miai.agent.service.IGlobalBroadcastAgentService;
import com.tigerjoys.shark.miai.inter.contract.IGlobalBroadcastContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftContract;
import com.tigerjoys.shark.miai.inter.entity.GlobalBroadcastEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;

/**
 * 全局广播服务接口实现类
 * 
 * @author lipeng
 *
 */
@Service
public class GlobalBroadcastAgentServiceImpl implements IGlobalBroadcastAgentService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 空对象
	 */
	private static final GlobalBroadcastEntity EMPTY_DTO;

	static {
		EMPTY_DTO = new GlobalBroadcastEntity();
		EMPTY_DTO.setId(0L);
	}
	
	@Autowired
	private IGlobalBroadcastContract globalBroadcastContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserChatGiftContract userChatGiftContract;


	@Autowired
	@Qualifier(AgentRedisCacheConst.GLOBAL_BROADCAST_CACHE)
	private CacheRedis globalBroadcastCacheRedis;
	
	private final Random random = new Random();

	@Override
	public AgentResult recordGlobalBroadcast(long userid) {
		if ( userid <= 0) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		String key = AgentRedisCacheConst.GLOBAL_BROADCAST_CACHE_KEY + Tools.getDate();
		globalBroadcastCacheRedis.hincrBy(key, String.valueOf(userid), 1);
		globalBroadcastCacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
		return AgentResult.success();
	}
	
	@Override
	public int getRecordCount(long userid) {
		int count = 0;
		String key = AgentRedisCacheConst.GLOBAL_BROADCAST_CACHE_KEY + Tools.getDate();
		String value = globalBroadcastCacheRedis.hget(key, String.valueOf(userid));
		if (value!=null) {
			count = Tools.parseInt(value);
		}
		return count;
	}
	
	@Override
	public void insert(Long userid, long anchorid, long money, int type, long giftId) throws Exception {
		UserBO user = userAgent.findById(userid);
		if (user!=null) {
			try {
				GlobalBroadcastEntity entity = new  GlobalBroadcastEntity();
				entity.setUserid(user.getUserid());
				entity.setCreate_time(new Date());
				if (Tools.isNull(user.getPhoto())) {
					entity.setPhoto("");
				}else{
					entity.setPhoto(user.getPhoto());
				}
				entity.setNickName(user.getNickname());
				if (type == GlobalBroadcastTypeEnum.gift.getCode()) {
					UserBO anchor = userAgent.findById(anchorid);
					UserChatGiftEntity gift = userChatGiftContract.findById(giftId);
					if (anchor!=null && gift != null) {
						entity.setContent("送了"+(anchor.getNickname().length()> (8 - gift.getName().length()) ? anchor.getNickname().substring(0, 8-gift.getName().length()-1)+"...":anchor.getNickname())+gift.getName());
						entity.setImg(gift.getIcon());
					}
				}else if (type == GlobalBroadcastTypeEnum.recharge.getCode()) {
					if (money<=20 && idList.contains(userid)) {
						money = getFakeGlobalBroadcastContent();
					}
					if (money>0) {
						entity.setContent("成功充值"+money+"元");
					}
				}else{
					entity.setContent("上线了！");
				}
				entity.setType(type);
				if (type == GlobalBroadcastTypeEnum.online.getCode()) {
					PageModel pageModel = new PageModel();
					pageModel.addQuery(Restrictions.eq("nickName", user.getNickname()));
					pageModel.addQuery(Restrictions.gt("create_time", Tools.getDateTime(Tools.getDayTime())));
					pageModel.addQuery(Restrictions.eq("type", type));
					if (Tools.isNull(globalBroadcastContract.load(pageModel))) {
						globalBroadcastContract.insert(entity);
					}
				}else{
					globalBroadcastContract.insert(entity);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	public static List<Long> idList = new ArrayList<Long>(); 
	static{
		idList.add(130114190751891712L);
		idList.add(32396088795267328L);
		idList.add(32470659676307712L);
		idList.add(145896550965510400L);
		idList.add(142626186703470848L);
		idList.add(132100703287050496L);
		idList.add(78111993137004800L);
		idList.add(91682454307406080L);
	}
	
	/**
	 * 随机生成假数据广播内容
	 * @return
	 */
	private int getFakeGlobalBroadcastContent() {
		int select = random.nextInt(100);
		int money = 200;
		if (30>=select && select>10) {
			money = 500;
		}else if(10 >= select) {
			money = 1000;
		}
		return money;
	}
	
}
