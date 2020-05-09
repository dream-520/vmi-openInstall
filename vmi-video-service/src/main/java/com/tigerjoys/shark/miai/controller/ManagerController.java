package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

@Controller
@RequestMapping(value="/manager/service",produces=Produce.TEXT_JSON)
public class ManagerController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserContract userContract;
	
	/**
	 * 刷新配置服务缓存
	 * @return ActionResult
	 */
	@FilterHeader
	@RequestMapping(value = "/clearConfigCache" , method=RequestMethod.GET)
	public @ResponseBody ActionResult clearConfigCache() {
		try {
			sysConfigAgent.clearCache();
			
			return ActionResult.success();
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
			
			return ActionResult.fail();
		}
	}

	/**
	 * 设置用户的推送分组
	 * @return ActionResult
	 * @throws Exception
	 */
	@FilterHeader
	@RequestMapping(value="/push/usertopic")
	public @ResponseBody ActionResult userPushTopic() throws Exception {
		List<UserEntity> list = userContract.load(PageModel.getPageModel());
		
		int totalSize = 0;
		if(Tools.isNotNull(list)) {
			Map<String , List<String>> waiterLevelMap = new HashMap<>();
			for(UserEntity user : list) {
				String clientId = user.getClientid();
				if(Tools.isNull(clientId)) {
					continue;
				}
				
				String key = user.getWaiter() + "#" + user.getPlatform();
				
				List<String> clientList = waiterLevelMap.get(key);
				if(clientList == null) {
 					clientList = new ArrayList<>();
					waiterLevelMap.put(key, clientList);
				}
				clientList.add(user.getClientid());
			}
			
			for(Map.Entry<String, List<String>> me : waiterLevelMap.entrySet()) {
				String[] vs = me.getKey().split("#");

				int platform = Integer.parseInt(vs[1]);
				String topic = com.tigerjoys.shark.miai.agent.constant.Const.TASK_PUSH_TOPIC_PREFIX + platform + vs[0];
				
				List<List<String>> clientsList = Lists.partition(me.getValue(), 20);
				if(Tools.isNotNull(clientsList)) {
					for(List<String> aliases : clientsList) {
						userAgent.subscribePushTopicByAlias(null,null,platform, topic, aliases);
						
						totalSize += aliases.size();
					}
				}
			}
		}
		
		return ActionResult.success("总用户数：" + list.size()+",初始化数：" + totalSize);
	}

}
