package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.BussinessMessageTypeEnum;
import org.shark.miai.common.enums.PlatformEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IMessageRedDotAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.MessageParamDto;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTopicEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.dto.service.PushRichUserDto;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.inter.contract.IMessageTemplateContract;
import com.tigerjoys.shark.miai.inter.entity.MessageTemplateEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 测试推送业务
 * @author liuman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class PushTest extends BaseTestConfig {
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IMessageTemplateContract messageTemplateContract;
	
	@Autowired
	private IMessageRedDotAgent messageRedDotAgent;
	
	@Test
	public void testPushComplaint() throws Exception {
		long complainant_userid = 25716864076284160L;
		UserBO complainant = userAgent.findById(complainant_userid);
		int talent = 3;
		PushParamDto paramC = PushHelper.getPushParamDto(complainant, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.successful_complaint);
		paramC.setContent("投诉已处理，扣对方"+talent+"信用分！");
		paramC.setContent("投诉已处理！");
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	@Test
	public void testPushRechargeMessage() throws Exception {
		//long userId = 67244811045896448L;
		//long userId = 36775801156337920L;
		//long userId = 66138512797270272L;
		//long userId = 151862465326547200L;
		long userId = 156893542126518528L;
		UserBO user = userAgent.findById(userId);
		System.err.println(user.getClientid());
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.recharge_user);
		paramC.setTitle("我充值了  来和我聊");
		paramC.setContent("一块来聊要");
		paramC.setUserId(userId);
		PushRichUserDto dto = new PushRichUserDto();
		UserBaseInfo otherUserData = new UserBaseInfo();
		otherUserData.setNickName(user.getNickname());
		otherUserData.setBalance("余额: "+20);
		dto.setOtherUserData(otherUserData);
		dto.setInfo("多聊多赚钱");
		paramC.setExtend(JsonHelper.toJson(dto));
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	
	
	
	@Test
	public void testPushWebUrlMessage() throws Exception {
		//long userId = 67244811045896448L;
		//long userId = 36775801156337920L;
		//long userId = 66138512797270272L;
		//long userId = 151862465326547200L;
		long userId = 157831585618526464L;
		UserBO user = userAgent.findById(userId);
		System.err.println(user.getClientid());
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_web_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.wakeup_user);
		paramC.setTitle("我充值了  来和我聊111");
		paramC.setContent("一块来聊要1111");
		paramC.setUserId(userId);
		paramC.setUrl("https://www.baidu.com");
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	@Test
	public void testPushAppWebUrlMessage() throws Exception {
		long userId = 157831585618526464L;
		UserBO user = userAgent.findById(userId);
		System.err.println(user.getClientid());
		PushParamDto param = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment);
		param.setContent("2222");
		param.setTitle("1111");
		param.setUrl("https://www.baidu.com/");
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	@Test
	public void testPushPersonHomePage() throws Exception {
		//long personId = 134832224380977408L;
		//long userId = 134832224380977408L;
		long userId = 140206527603605760L;
		long personId = 140206527603605760L;
		UserBO user = userAgent.findById(userId);
//		user.setPackageName("com.tjhj.miliao");
//		user.setClientid("aa1c60093e9ba0eb63c315c180c2fb07");
		
		//user.setPackageName("com.ydwx.yoyo3");
		//user.setClientid("2e3db5a8a839deb50fb3f8eac437bd45");
		
		user.setClientid("0de5cf79c6af612e1105132672cd23aa");
		user.setPackageName("com.ydwx.yoyo3");
		
		PushParamDto param = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.personal_page);
		param.setTitle("进入个人主页");
		param.setContent("进入个人主页");
		param.setUserId(personId);
		param.setExtend(userId+"");
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	@Test
	public void testPushOnlinePersonHomePage() throws Exception {
		long personId = 36775801156337920L;
		long userId = 134832224380977408L;
		UserBO user = userAgent.findById(userId);
		//user.setClientid("2db19484a00e66bb27062e615dd3028a");
		//user.setPackageName("com.ydwx.yoyo");
		PushParamDto param = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.personal_page);
		param.setTitle("进入个人主页");
		param.setContent("进入个人主页");
		param.setUserId(personId);
		param.setExtend(userId+"");
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	@Test
	public void testPushWakeUpMessage() throws Exception {
		//long userId = 67244811045896448L;
		//long userId = 132458211732160768L;
		//long userId = 36775801156337920L;
		//long userId = 135155314310840576L;
		long userId = 134832224380977408L;
		UserBO user = userAgent.findById(userId);
		//user.setClientid("b78e89681ba4ca1a6d2c9cde2cf88f04");
		//user.setPackageName("com.ydwx.yoyo");
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.wakeup_user);
		paramC.setTitle("唤醒");
		paramC.setContent("唤醒消息");
		paramC.setUserId(userId);
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	
	@Test
	public void testAudioWakeUpMessage() throws Exception {
		//long userId = 67244811045896448L;
		//long userId = 132458211732160768L;
		//long userId = 36775801156337920L;
		//long userId = 135155314310840576L;
		long userId = 134832224380977408L;
		UserBO user = userAgent.findById(userId);
		//user.setClientid("b78e89681ba4ca1a6d2c9cde2cf88f04");
		//user.setPackageName("com.ydwx.yoyo");
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.wakeup_user);
		paramC.setTitle("唤醒");
		paramC.setContent("唤醒消息");
		paramC.setUserId(userId);
		newPushAgent.pushMessageToSingleUser(paramC);
	}
	
	
	@Test
	public void testPushTalentVip() throws Exception {
		long userid = 24067247928574208L;
		UserBO userBO = userAgent.findById(userid);
		PushParamDto param = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.unnecessary, NewPushAppTagEnum.enable_servicer_vip);
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	@Test
	public void testNewPushSystemMessage() throws Exception {
		long id = 129L;
		MessageTemplateEntity mess = messageTemplateContract.findById(id);
		PushParamDto param = PushHelper.getAllPushParamDto(PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment,PlatformEnum.ios);
		param.setContent(mess.getIntro());
		param.setTitle(mess.getTitle());

		if(mess.getType()==0){
//			String url = "https://www.baidu.com";
			String url = Const.getSysMessUrl(mess.getId());
			param.setUrl(url);
		}else if(mess.getType()==1){
			param.setUrl(mess.getOpenurl());
		}
		newPushAgent.pushMessageToAllUser(param);
	}
	
	@Test
	public void testNewPushAllMessage() throws Exception {
		PushParamDto androidParam = PushHelper.getAllPushParamDto(PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment,PlatformEnum.ios);
		androidParam.setContent("赶紧分享给好友吧，体验社交新乐趣！");
		androidParam.setTitle("分享给好友");
		//androidParam.setH5Title(mess.getTitle());
		androidParam.setPackageName("com.yo.miliaoliao");
		newPushAgent.pushMessageToAllUser(androidParam);
	}
	
	
	
	
	@Test
	public void testNewPushChat() throws Exception {
		long id = 129L;
		MessageTemplateEntity mess = messageTemplateContract.findById(id);
		PushParamDto param = PushHelper.getAllPushParamDto(PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment,PlatformEnum.android);
		param.setContent(mess.getIntro());
		param.setTitle(mess.getTitle());

		if(mess.getType()==0){
//			String url = Const.getSysMessUrl(mess.getId());
			String url = "https://www.baidu.com/";
			param.setUrl(url);
		}else if(mess.getType()==1){
			param.setUrl(mess.getOpenurl());
		}
		newPushAgent.pushMessageToAllUser(param);
	}
	
	@Test
	public void testPushHomePage() throws Exception {
		long personId = 24795387357364480L;
		long userId = 25032199065633024L;
		UserBO user = userAgent.findById(userId);
		
		PushParamDto param = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.personal_page);
		param.setUserId(personId);
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	/**
	 * 测试支付订单后通知服务者有人申请你的服务
	 * @throws Exception
	 */
	@Test
	public void testPushApplyService() throws Exception {
		long sellerId = 26081799327252736L;//25739899535687936 25716864076284160 25905916106834176
//		UserBO seller = userAgent.findById(sellerId);
		sendPushMessage(sellerId, BussinessMessageTypeEnum.someone_apply_your_service, CommonConst.myPublish, NewPushAppTagEnum.someone_apply_your_service,this.getParamStr(sellerId));
	}

	private void sendPushMessage(long userId , BussinessMessageTypeEnum messageType , String redDotKey , NewPushAppTagEnum pushAppTag,String paramStr){
		try {
			UserBO sellerUserBO = userAgent.findById(userId);
			PushParamDto param = PushHelper.getPushParamDto(sellerUserBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.unnecessary, pushAppTag);
			newPushAgent.pushMessageToSingleUser(param);
		} catch (Exception e) {
			logger.error(e.getMessage() ,e );
		}
	}
	
	private String getParamStr(long userId) {
		MessageParamDto messageParam = new MessageParamDto();
		messageParam.addParam(1, userId);
		return JsonHelper.toJson(messageParam);
	}
	
	@Test
	public void testPushAuth() throws Exception {
		long userId = 135193478872236288L;
		UserBO userBO = userAgent.findById(userId);
		System.err.println(JsonHelper.toJson(userBO));
		PushParamDto param = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.unnecessary, NewPushAppTagEnum.certification_success);
		System.err.println(JsonHelper.toJson(param));
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	@Test
	public void testSubscribeTopic() throws Exception {
		String topic = PushTopicEnum.perfect_personal_data.getDesc();
		List<String> aliases = new ArrayList<>();
		aliases.add("b36208944e3afd5f60697d8b30344267");
		newPushAgent.subscribeTopicByAlias(PlatformEnum.android.type, 
											topic, 
											aliases);
	}
	
	@Test
	public void testPushMessageByTopic() throws Exception {
		String topic = PushTopicEnum.perfect_personal_data.getDesc();
//		List<String> aliases = new ArrayList<>();
//		aliases.add("9bb14f57ac8fb7b569af918a61fa16c1");
//		aliases.add("24e7eddd0a97937eeac3359427561c08");
//		aliases.add("fb805f9f9f4f96f4460ef9685275be01");
		PushParamDto param = PushHelper.getAllPushParamDto(PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment,PlatformEnum.android);
		param.setContent("活动任务内容");
		param.setTitle("活动任务标题");
		param.setUrl("https://www.baidu.com");
		param.setH5Title("测试标题1");
		newPushAgent.pushMessageToAndroidUserByTopic(param, topic);
	}
	
	@Test
	public void testUbsubscribeTopic() throws Exception {
		String topic = PushTopicEnum.perfect_personal_data.getDesc();
		List<String> aliases = new ArrayList<>();
		aliases.add("b1b761a5bf6d1c21326ed92e3384f0b8");
		newPushAgent.unsubscribeTopicByAlias(PlatformEnum.ios.type, 
											topic, aliases);
	}
	
	@Test
	public void testPushTaskPage() throws Exception {
		//UserBO bo = userAgent.findById(33311928371511040L);
	
		UserBO bo = new UserBO();
		bo.setClientid("aa1c60093e9ba0eb63c315c180c2fb07");
		bo.setPlatform(1);
		bo.setPackageName("com.tjhj.miliao");
		
		long userId = 135193478872236288L;
		UserBO user = userAgent.findById(userId);
		PushParamDto param = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment);
		param.setTitle("任务奖励，拿到手软！");
		param.setContent("完成测试任务1，即可获得10元现金奖励");
		param.setUrl("https://www.baidu.com");
		param.setShowH5TitleFalg(1);
		newPushAgent.pushMessageToSingleUser(param);
	}
	
	@Test
	public void testPushTaskPageByTopic() throws Exception {
		String topic = PushTopicEnum.perfect_personal_data.getDesc();
		UserBO bo = userAgent.findById(135193478872236288L);
		PushParamDto param = PushHelper.getPushParamDto(bo, PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment);
		param.setTitle("任务奖励，拿到手软！");
		param.setContent("完成测试任务1，即可获得10元现金奖励");
		param.setUrl("http://192.168.20.31:20000/shark-miai-service/api/task/home/list");
		param.setShowH5TitleFalg(1);
//		newPushAgent.pushMessageToUserByTopic(param, topic);
		newPushAgent.pushMessageToAndroidUserByTopic(param, topic);
	}
	
	@Test
	public void testPushSystemMessageForB2() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("id", 154));//根据id直接查询
//		pageModel.addQuery(Restrictions.eq("status", 1));//正常状态
//		pageModel.addQuery(Restrictions.eq("send", 0));//未发送
//		pageModel.addQuery(Restrictions.le("publish_time", new Date()));//小于当前时间
//		pageModel.desc("publish_time");
		
			List<MessageTemplateEntity> list = messageTemplateContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				Map<String, Integer> dataMap = new HashMap<>();
				for(MessageTemplateEntity mess : list) {
					try {
						String key = mess.getPackagename();
						dataMap.put(key, 1);
						if (dataMap.containsKey(key)) {
							dataMap.put(key, dataMap.get(key)+1);
						} else {
							dataMap.put(key, 1);
						}
						MessageTemplateEntity temp = new MessageTemplateEntity();
						temp.setId(mess.getId());
						temp.setUpdate_time(new Date());
						temp.setSend(1);
						messageTemplateContract.update(temp);
						messageRedDotAgent.sendSystemMessage(mess);
					} catch (Exception e) {
						logger.error("发送消息："+mess.getTitle()+"出错了。", e);
					}
				}
				//根据package区分 将最新加入已经发送了系统公告的redis数据数量 ,这个系统消息数据会一直存在的
//				dataMap.forEach((key,value) -> {
//					systemMessageRedis.incrBy(CommonConst.sys_message + key,value);
//				});
			}
	}
	
	
	
}
