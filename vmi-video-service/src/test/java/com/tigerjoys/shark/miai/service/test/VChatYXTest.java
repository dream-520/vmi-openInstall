package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.delayqueue.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.IdGenerator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Bootstrap;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.VchatTurntableConfigDto;
import com.tigerjoys.shark.miai.agent.neteasecheck.INeteaseTextCheck;
import com.tigerjoys.shark.miai.agent.pay.IapHelper;
import com.tigerjoys.shark.miai.agent.service.IVchatTcpMessageService;
import com.tigerjoys.shark.miai.dto.service.BtnData;
import com.tigerjoys.shark.miai.dto.service.DialingCheckNewDto;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPage;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPageNew;
import com.tigerjoys.shark.miai.dto.service.EvaluationDto;
import com.tigerjoys.shark.miai.enums.DlgAndGoPageEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorEvaluationStatisticsContract;
import com.tigerjoys.shark.miai.inter.contract.IAppLabelContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserVchatExperienceIncomeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorEvaluationLogEntity;
import com.tigerjoys.shark.miai.inter.entity.AppLabelEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UserVchatExperienceIncomeLogEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.shark.miai.service.IRechargeGuardVipService;
import com.tigerjoys.shark.miai.service.IShortVideoService;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;
import com.tigerjoys.shark.miai.service.IVChatVideoYXService;
import com.tigerjoys.shark.miai.service.impl.TextChatSendMsgConsumer;
import com.tigerjoys.shark.miai.service.impl.VchatRoomRecvExitSendMsgConsumer;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.tigerjoys.shark.miai.utils.FileUtilTest;

/**
 * 用户代理测试
 * @author chengang
 *
 */
public class VChatYXTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IVChatVideoYXService vChatVideoYXService;
	@Autowired
	private IAnchorEvaluationStatisticsContract anchorEvaluationStatisticsContract;
	
	@Autowired
	private IShortVideoService shortVideoService;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private INeteaseTextCheck neteaseTextCheck;
	
	@Autowired
	private TextChatSendMsgConsumer textChatSendMsgConsumer;
	
	@Autowired
	private IVChatTextYXService vChatTextYXService;
	
	@Autowired
	private IRechargeGuardVipService rechargeGuardVipService;
	
	
	
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IUserVchatExperienceIncomeLogContract userVchatExperienceIncomeLogContract;
	
	
	@Autowired
	private IVchatTcpMessageService vchatTcpMessageService;
	
	@Autowired
	private IAppLabelContract appLabelContract;
	
	
	/**
	 * 主播视频通话结束后评价列表
	 * @throws Exception
	 */
	@Test
	public void anchorEvaluationList() throws Exception{
		ActionResult result = vChatVideoYXService.anchorEvaluationList(0);
		
		System.out.println(JsonHelper.toJson(result));
	}
	
	/**
	 * 评价投注
	 * @throws Exception
	 */
	@Test
	public void testAnchorEvaluation() throws Exception{
		
		HashMap< String, Object> hsmp = new HashMap<>();
		hsmp.put("anchorId", 36775801156337920L);
		hsmp.put("evaluationIdList", new Long[]{25L,26L});
		
		JSONObject json = JsonHelper.toJsonObject(JsonHelper.toJson(hsmp));
		Long anchorId = json.getLong("anchorId");
		JSONArray idArray = json.getJSONArray("evaluationIdList");
		List<Long> list = new ArrayList<Long>();
		idArray.forEach(v->{
			list.add(Long.valueOf(""+ v));
		});
		
//		AnchorEvaluationStatisticsEntity entity = new AnchorEvaluationStatisticsEntity();
//		entity.setUserid(36775801156337920L);
//		entity.setLabel_id(25L);
//		entity.setTotal_num(1);
//		entity.setUpdate_time(new Date());
//		anchorEvaluationStatisticsContract.insert(entity);
	
		
		System.out.println("xxxxx:"+JsonHelper.toJson(list));		
		ActionResult result = vChatVideoYXService.anchorEvaluation(32392052257915136L, anchorId,2, list,"");
		
		System.err.println(JsonHelper.toJson(result));
	}
	
	
	@Test
	public void testVideoDesc() throws Exception{
		ActionResult result = shortVideoService.videoDesc(4L, 65418691671490304L);
		System.out.println(JsonHelper.toJson(result));
	}

	
	@Test
	public void testVideoList() throws Exception{
		ActionResult result = shortVideoService.videoHome("");
		System.out.println(JsonHelper.toJson(result));
	}
	
	@Test
	public void testDialingCheck() throws Exception{
		//ActionResult result = vChatVideoYXService.dialingCheck(65418691671490304L, 65418693535858432L,0);
		//System.out.println(JsonHelper.toJson(result));
	}
	
	@Test
	public void testEvaluation() throws Exception{
		EvaluationDto dto = new EvaluationDto();
		dto.setBgColor("#FFFFF");
		//dto.setText("充值");
		System.err.println(JsonHelper.toJson(dto));
	}
	
	@Test
	public void testVideoPraise() throws Exception{
		ActionResult result = shortVideoService.videoPraise(9L, 65418693535858432L);
		System.out.println(JsonHelper.toJson(result));
	}
	
	@Test
	public void testHome() throws Exception{
		ActionResult result = shortVideoService.anchorSlideList(0L, 0, 0, null, 65418691671490304L);
		System.err.println("0:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorSlideList(0L, 1, 0, null, 65418691671490304L);
		System.err.println("1:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorSlideList(0L, 2, 0, null, 65418691671490304L);
		System.err.println("2:"+JsonHelper.toJson(result));
	}
	
	@Test
	public void testNewHome() throws Exception{
		ActionResult result = shortVideoService.anchorNewSlideList(0L, 0, 0, null, 65418691671490304L);
		System.err.println("关注数据:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(0L, 0, 1, null, 65418691671490304L);
		System.err.println("推荐数据:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(0L, 0, 2, null, 65418691671490304L);
		System.err.println("新人数据:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(0L, 0, 3, null, 65418691671490304L);
		System.err.println("三星数据:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(0L, 0, 4, null, 65418691671490304L);
		System.err.println("四星数据:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(0L, 0, 5, null, 65418691671490304L);
		System.err.println("五星数据:"+JsonHelper.toJson(result));
	}
	
	@Test
	public void testIAP() throws Exception{
	String receipt = "";
	 String payStr = IapHelper.getPayData(receipt);
	 System.err.println(payStr);
	}
	
	@Test
	public void testShortVideo() throws Exception{
		ActionResult result = shortVideoService.anchorNewSlideList(null, 1, 0, "", null);
		System.err.println("type0:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(null, 1, 1, "", null);
		System.err.println("type1:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(null, 1, 2, "", null);
		System.err.println("type2:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(null, 1, 3, "", null);
		System.err.println("type3:"+JsonHelper.toJson(result));
		result = shortVideoService.anchorNewSlideList(104743390576443648L, 1, 4, "", null);
		System.err.println("type4:"+JsonHelper.toJson(result));
	}
	
	@Test
	public void testMyPhone() throws Exception{
		ActionResult result = vChatVideoYXService.getMyPhone(65418712749965056L, "");
		System.err.println("myPhone:"+JsonHelper.toJson(result));
	}
	
	
	@Test
	public void testDistinct() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, -15);
		PageModel pageModel =PageModel.getPageModel();
		pageModel.addProjection(Projections.distinct(Projections.property("anchorid")));
		pageModel.addQuery(Restrictions.eq("userid", 135182012418949376L));
		pageModel.addQuery(Restrictions.gt("create_time", Tools.getFormatDate(cal.getTime(), "yyyy-MM-dd HH:mm:ss")));
	
		
		 List<Map<String, Object>> maps = vchatRoomContract.loadGroupBy(pageModel);
		if (Tools.isNotNull(maps)) {
			maps.stream().forEach(map -> {
				if (Tools.isNotNull(map)) {
					Long anchorid = Tools.parseLong(map.get("anchorid"));
					System.out.println("=========="+anchorid);
				}
			});
		}
		
	}
	@Test
	public void testTurntable() throws Exception{
	SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.VCHAT_TURNTABLE_INFO);
	VchatTurntableConfigDto turntableConfig = JsonHelper.toObject(config.getValue(), VchatTurntableConfigDto.class);
	String []ss = Tools.split(turntableConfig.getRuleText(),"\\n");
	for(String re:ss){
		System.out.println("xxxxx:"+re);
	}
	System.out.println("aaaa"+turntableConfig.getRuleText());
	}
	
	
	@Test
	public void testDekatQueue() throws Exception{
		 // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();
        // 添加延时消息,m1 延时3s
        Message m1 = new Message("1", "world", 3000);
        // 添加延时消息,m2 延时10s
        Message m2 = new Message("2", "hello", 5000);
        //将延时消息放到延时队列中
        
        queue.offer(m2);
        queue.offer(m1);
      
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
       // exec.execute(new VchatRoomRecvExitSendMsgConsumer.VchatRoomRecvExitSendMsgConsumerThread(queue));
        //exec.shutdown();
        
        Thread.sleep(10000);
		
	}
	
	@Test
	public void testVchatText() throws Exception{
		String msg = "你是 龟孙儿 ,微信456123455";
		//String msg = "你是我的爱";
		String result = neteaseTextCheck.check(IdGenerater.generateId(), msg);
		System.out.println("########:"+result);
		
		
		UserVchatExperienceIncomeLogEntity incomeLog = userVchatExperienceIncomeLogContract.findById(149661811411583232L);
		System.out.println("UserVchatExperienceIncomeLog:"+JsonHelper.toJson(incomeLog));
	}
	
	@Test
	public void textChatSendMsgConsumer() throws Exception{
		
		long userId = 152744030258659584L;
		long toUserId = 152709450457743616L;
		
		Map<String,Object> hsmp = new HashMap<>();
		hsmp.put("userId", userId);
		hsmp.put("toUserId", toUserId);
		hsmp.put("msg", "嘿嘿");
		
		//Message consumerMsg = new Message(IdGenerater.generateId()+"",JsonHelper.toJson(hsmp),3000);
		//textChatSendMsgConsumer.put(consumerMsg);
		
		 vChatTextYXService.payChat(userId, toUserId,"微信");
	}
	
	@Test
	public void textChatDialing() throws Exception{
	DialingCheckNewDto checkDto = new DialingCheckNewDto();
	checkDto.setStatus(0);
	
	DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
	String hintInfo = "您不是VIP用户，请开通VIP才可能通话";
	BtnData chargebtn = new BtnData();
	chargebtn.setBtnName("开通VIP");
	chargebtn.setAndroidPageTag("VIPFragment");
	chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(0));
	BtnData cancelBtn = new BtnData();
	cancelBtn.setBtnName("取消");
	dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn,cancelBtn));
	dlgAndGoPage.setHintInfo(hintInfo);
	
	checkDto.setDlgAndGoPage(dlgAndGoPage);
    System.out.println("json:"+JsonHelper.toJson(checkDto));
	}
	
	
	@Test
	public void textTCP() throws Exception{
		
		vchatTcpMessageService.sendShortVideoOpenStatus(67244811045896448L,  0);
	}
	
	@Test
	public void textRechargeGuardVIp() throws Exception{
		
		Map<String, Object> outMap = rechargeGuardVipService. getPriceList(0, 2);
		System.out.println(JsonHelper.toJson(outMap));
	}
	
	@Test
	public void textAnchorEvaluation() throws Exception{
		
		  List<String> anchorIdList = FileUtilTest.readBylineTolist("D:\\aaaa\\ll_userid.csv");
		  List<String> userIdList = FileUtilTest.readBylineTolist("D:\\aaaa\\userId.csv");
	
		long userId = 0L;
		  List<String> sqlList = new ArrayList<>();
		for (String anchorStr:anchorIdList) {
			for (int i=0;i<10;i++){
				List<Long> evaluationIdList = new ArrayList<>();
				// 评论 21 到  42
				long id1 = (long)new Random().nextInt(22)+21;
				evaluationIdList.add(id1);
				long id2 = (long)new Random().nextInt(22)+21;
				if(id1 != id2){
					evaluationIdList.add(id2);
				}
				
				long anchorId = Tools.parseLong(anchorStr);
				String userIdStr = userIdList.get(new Random().nextInt(userIdList.size()-1));
				Map<Long,AppLabelEntity> labelList = appLabelContract.findById(evaluationIdList);
				AnchorEvaluationLogEntity logEntity = new AnchorEvaluationLogEntity();
				logEntity.setUserid(userId);
				logEntity.setAnchor_userid(anchorId);
				List<HashMap<String, String>> labelsList = new ArrayList<>();
				if(Tools.isNotNull(labelList)){
					for(Long re:evaluationIdList){
						AppLabelEntity label = labelList.get(re);
						if(Tools.isNotNull(label)){
							HashMap<String, String> hsmp = new HashMap<>();
							hsmp.put("desc", label.getName());
							hsmp.put("color", label.getColor());
							labelsList.add(hsmp);
						}
					
					}
				}
				int avType = 2;
				logEntity.setOrderid(0L);
				logEntity.setAv_type(avType);
				logEntity.setEvaluation_labels(JsonHelper.toJson(labelsList));
				logEntity.setCreate_time(new Date());
				
				String sql = "insert into `t_anchor_evaluation_log` ( `userid`, `anchor_userid`, `av_type`, `orderid`, `evaluation_labels`, `evaluation_text`, `create_time`) values('"+userIdStr+"','"+anchorId+"','2','0','"+JsonHelper.toJson(labelsList)+"',NULL,'2020-03-12 07:59:59');";
				sqlList.add(sql);
			}
		}
		FileUtilTest.createFile("D:\\aaaa\\SQL.sql",sqlList );
		
	}

}
