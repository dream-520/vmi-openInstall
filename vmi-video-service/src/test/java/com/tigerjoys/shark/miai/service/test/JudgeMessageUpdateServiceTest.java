package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.BussinessMessageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IMessageRedDotAgent;
import com.tigerjoys.shark.miai.agent.IUserMypageActivityAgent;
import com.tigerjoys.shark.miai.agent.dto.MessageParamDto;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.inter.contract.IBussinessMessageContract;
import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.BussinessMessageEntity;
import com.tigerjoys.shark.miai.service.IMessageService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class JudgeMessageUpdateServiceTest extends BaseTestConfig {
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IUserMypageActivityAgent userMypageActivityAgent;
	
	@Autowired
	private IMessageRedDotAgent messageRedDotAgent;
	
	@Autowired
	private IBussinessMessageContract bussinessMessageContract;
	
	@Autowired
	private IMessageTemplateContract messageTemplateContract;
	
	/**
	 * 测试小红点接口
	 * @throws Exception 
	 */
	@Test
	public void testGetDataList() throws Exception {
//		long userId = 13059718849L;
		long userId = 13051798829L;
//		String stamp = "1";
		String stamp = null;
		ActionResult result = messageService.getMessageUpdateDtos(userId,stamp,"yoyo2");
		System.out.println("json of result:" + JsonHelper.toJson(result));
	}
	
	/**
	 * 测试set衰减
	 * @throws Exception 
	 */
	@Test
	public void testRedisSet() throws Exception {
		long userId = 10023066L;
		String count = cacheRedis.hget(CommonConst.sys_message, String.valueOf(userId));
		System.out.println("count1:" + count);
		
		cacheRedis.hincrBy(CommonConst.sys_message, String.valueOf(userId), -1);
		
		String count2 = cacheRedis.hget(CommonConst.sys_message, String.valueOf(userId));
		System.out.println("count2:" + count2);
	}
	
	@Test
	public void testGetEveryDayTaskFinishCount() {
		long userId = 3957344845234432L;
		String everyDayCount = cacheRedis.hget(CommonConst.task_been_finished_message,String.valueOf(userId));
		System.out.println("everyDayCount:" + everyDayCount);
	}
	
	@Test
	public void testGetNoviceTaskFinishCount() {
		long userId = 3957344845234432L;
		String noviceCount = cacheRedis.hget(CommonConst.novice_task_been_finished_message,String.valueOf(userId));
		System.out.println("noviceCount:" + noviceCount);
	}
	
	@Test
	public void testGetTemp() throws Exception {
		long userId = 20295198334583040L;//20295198334583040;22860589651001600
		List<Long> tempActivities = userMypageActivityAgent.getIndexCode(userId);
		System.out.println("tempActivities:" + tempActivities.size());
	}
	
	@Test
	public void testMyPublishFree() throws Exception {
		long userId = 19565292852150528L;//19565292852150528
		sendPushMessage(userId, BussinessMessageTypeEnum.someone_apply_your_service_free,
				CommonConst.myPublishFree, NewPushAppTagEnum.someone_apply_your_service_free,
				this.getParamStr(userId));
	}
	
	private String getParamStr(long userId) {
		MessageParamDto messageParam = new MessageParamDto();
		messageParam.addParam(1, userId);
		return JsonHelper.toJson(messageParam);
	}
	
	/**
	 * 给单方用户发送推送消息
	 * 
	 * @param userId
	 *            - 用户ID
	 * @param messageType
	 *            - 消息类型
	 * @param redDotKey
	 *            - 小红点key名称
	 * @param pushAppTag
	 *            - push tag
	 * @param paramStr
	 *            - 页面跳转参数
	 */
	private void sendPushMessage(long userId, BussinessMessageTypeEnum messageType, String redDotKey,
			NewPushAppTagEnum pushAppTag, String paramStr) {
		try {
			messageRedDotAgent.addBussinessMessage(userId, messageType, paramStr);
			messageRedDotAgent.addRedDot(redDotKey, userId);

//			UserBO sellerUserBO = userAgent.findById(userId);
//			PushParamDto param = PushHelper.getPushParamDto(sellerUserBO, PushTypeEnum.type_goto_app,
//					PushContentTypeEnum.unnecessary, pushAppTag);
//			newPushAgent.pushMessageToSingleUser(param);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 测试小红点接口
	 * @throws Exception 
	 */
	@Test
	public void testGetDataList2() throws Exception {
		long userId = 13051798829L;
		List<BussinessMessageEntity> messages = bussinessMessageContract.loadByUseridAndType(BussinessMessageTypeEnum.getTypes(), userId);
		logger.info("json of result:{}", JsonHelper.toJson(messages));
	}
	
	/**
	 * 测试小红点接口
	 * @throws Exception 
	 */
	@Test
	public void testGetDataList3() throws Exception {
		long userId = 13051798829L;
		List<BussinessMessageEntity> messages = bussinessMessageContract.loadByUseridAndType(BussinessMessageTypeEnum.getTypes(), userId);
		Map<Integer,BussinessMessageEntity> dataMap = new HashMap<>();
		for (BussinessMessageEntity message : messages) {
			dataMap.put(message.getType(), message);
		}
		logger.info("json of result:{}", JsonHelper.toJson(dataMap.get(2)));
	}
	
	/**
	 * 测试小红点接口
	 * @throws Exception 
	 */
	@Test
	public void testGetDataList4() throws Exception {
		long userId = 13051798829L;
		List<BussinessMessageEntity> messages = bussinessMessageContract.loadByUseridAndType(BussinessMessageTypeEnum.getTypes(), userId);
		//根据创建时间倒序排序
		Collections.sort(messages, new Comparator<BussinessMessageEntity>() {

			@Override
			public int compare(BussinessMessageEntity o1, BussinessMessageEntity o2) {
//				int flag = o1.getCreate_time().compareTo(o2.getCreate_time());
				int flag = o2.getCreate_time().compareTo(o1.getCreate_time());
				System.out.println("flag:" + flag);
				return flag;
			}
		});
		logger.info("json of result:{}", JsonHelper.toJson(messages));
	}
	
	@Test
	public void testGroupBy() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addProjection(Projections.groupProperty("packagename"));
		List<Map<String , Object>> datas = messageTemplateContract.loadGroupBy(pageModel);
//		logger.info("json of result:{}", JsonHelper.toJson(datas));
		List<String> keys = new ArrayList<>();
		for (Map<String , Object> data : datas) {
			keys.add(data.get("packagename").toString());
		}
		
		logger.info("json of result:{}", JsonHelper.toJson(keys));
	}
}
