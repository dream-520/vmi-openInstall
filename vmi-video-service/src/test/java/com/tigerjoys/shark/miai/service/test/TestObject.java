package com.tigerjoys.shark.miai.service.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;
import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.util.TimeTools;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.dto.MessageParamDto;
import com.tigerjoys.shark.miai.agent.dto.PushMessageDto;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.dto.service.UserDto;
import com.tigerjoys.shark.miai.dto.task.JudgeMessageUpdateDto;
import com.tigerjoys.shark.miai.dto.task.RedDotDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.entity.UserEntity;

/**
 * 一些基础代码测试
 * @author liuman
 *
 */
public class TestObject {
	
	@Test
	public void test1() {
		ActionResult result = ActionResult.fail(ErrorCodeEnum.error.getCode(), "每天只能够写三个心愿");
		System.out.println(JsonHelper.toJson(result));
	}
	
	@Test
	public void test2() {
        List<String> aList = new ArrayList<String>();  
        aList.add("aaa");  
        aList.add("bbb");  
        aList.add("ccc");  
        List<String> bList = new ArrayList<String>();  
        bList.add("aaa");  
        bList.add("ddd");  
        bList.add("eee");  
        
        // 并集  
        Collection<String> unionList = CollectionUtils.union(aList, bList);  
        // 交集  
        Collection<String> intersectionList = CollectionUtils.intersection(aList, bList);  
        // 是否存在交集  
        boolean isContained = CollectionUtils.containsAny(aList, bList);  
        // 交集的补集  
        Collection<String> disjunctionList = CollectionUtils.disjunction(aList, bList);  
        // 集合相减  
        Collection<String> subtractList = CollectionUtils.subtract(aList, bList);  
          
        // 排序  
        Collections.sort((List<String>) unionList);  
        Collections.sort((List<String>) intersectionList);  
        Collections.sort((List<String>) disjunctionList);  
        Collections.sort((List<String>) subtractList);  
  
        // 测试  
        System.out.println("A: " + ArrayUtils.toString(aList.toArray()));  
        System.out.println("B: " + ArrayUtils.toString(bList.toArray()));  
        System.out.println("A has one of B? : " + isContained);  
        System.out.println("Union(A, B): "  
                + ArrayUtils.toString(unionList.toArray()));  
        System.out.println("Intersection(A, B): "  
                + ArrayUtils.toString(intersectionList.toArray()));  
        System.out.println("Disjunction(A, B): "  
                + ArrayUtils.toString(disjunctionList.toArray()));  
        System.out.println("Subtract(A, B): "  
                + ArrayUtils.toString(subtractList.toArray()));  
	}
	
	@Test
	public void test3() {
		long currentTime = System.currentTimeMillis();
		long totalTime = TimeTools.getNextMorningTime();
		long remindTime = totalTime - currentTime;
		
		int seconds = new Long(remindTime/1000).intValue();
		System.out.println("到凌晨还剩" + seconds + "秒");
		int minutes = seconds/60;
		System.out.println("到凌晨还剩" + minutes + "分");
		int hour = minutes/60;
		System.out.println("到凌晨还剩" + hour + "小时");
	}
	
	@Test
	public void testLong2Double() {
		long testValue = Long.MAX_VALUE-2;
		System.out.println("testValue:" + testValue);
//		double testDoubleValue = Double.parseDouble(String.valueOf(testValue));
		double testDoubleValue = testValue;
		long testValue1 = (long)testDoubleValue;
		System.out.println("testValue1:" + testValue1);
	}
	
	@Test
	public void testNullCout() {
		String coutStr = null;
		int stealCount = Integer.parseInt(coutStr);
		System.out.println("stealCount:" + stealCount);
	}
	
	@Test
	public void testLingChen() {
		long totalTime = Tools.getDayTime() + Tools.DAY_MILLIS;
		System.out.println("totalTime:" + Tools.getFormatDate(totalTime,TimeTools.YYYY_MM_DD_HH_mm_ss));
	}
	
	@Test
	public void testTodayDateFormat() {
		long userId = 177001L;
		String key = userId + Tools.getCurrentDate(TimeTools.YYYYMMDD);
		System.out.println("key:" + key);
	}
	
	@Test
	public void testStickActionResult() {
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("userStickCount", 2);
		dataMap.put("userInfo", 0);
		ActionResult result = ActionResult.success(dataMap);
		System.out.println("json of result:" + JsonHelper.toJson(result));
	}
	
	@Test
	public void testRedDots() {
		RedDotDto redDotDto = new RedDotDto();
		int unreadCount = 5;
		int publishCount = 7;
		redDotDto.setUnreadCount(unreadCount);
		List<JudgeMessageUpdateDto> updateDtos = new ArrayList<>();
		JudgeMessageUpdateDto message = new JudgeMessageUpdateDto();
		message.setAppName("你好");
		message.setCreateDate(System.currentTimeMillis());
		message.setInfo("测试内容");
		message.setMsgTag(1);
		message.setMsgType(CommonConst.MSG_TYPE_PAY_DATE_BUY);
		message.setParam("test");
		message.setTitle("测试标题");
		updateDtos.add(message);
		
		redDotDto.setUpdateMessages(updateDtos);
		
		ActionResult result = ActionResult.success(redDotDto, publishCount, false);
		System.out.println("json of result:" + JsonHelper.toJson(result));
	}
	
	@Test
	public void testSubList() {
		List<String> strList = new ArrayList<>(2);
		System.out.println("size of strList:" + strList.size());
		
		strList.add("A");
		strList.add("B");
		strList.add("C");
		strList.add("D");
		System.out.println("size of strList:" + strList.size());
		
		strList.subList(0, 3);
		System.out.println("size of strList:" + strList.size());
	}
	
	@Test
	public void testSetEnumAttribute() {
		String origalAndroidCodeOfOrderTimeout = NewPushAppTagEnum.order_timeout.getAndroidCode();
		String origalIosCodeOfOrderTimeout = NewPushAppTagEnum.order_timeout.getIosCode();
		
		System.out.println("origalAndroidCodeOfOrderTimeout:" + origalAndroidCodeOfOrderTimeout);
		System.out.println("origalIosCodeOfOrderTimeout:" + origalIosCodeOfOrderTimeout);
		
		String newAndroidCodeOfOrderTimeout = PushHelper.getMyPurchaseOfOrderTimeout().getAndroidCode();
		String newIosCodeOfOrderTimeout = PushHelper.getMyPurchaseOfOrderTimeout().getIosCode();
		
		System.out.println("newAndroidCodeOfOrderTimeout:" + newAndroidCodeOfOrderTimeout);
		System.out.println("newIosCodeOfOrderTimeout:" + newIosCodeOfOrderTimeout);
		
	}
	
	@Test
	public void testPushMessageJson() {
		PushMessageDto message = new PushMessageDto();
		message.setAppTag(NewPushAppTagEnum.apply_fail.getIosCode());
		message.setContent("测试消息内容");
		message.setMsgType(PushTypeEnum.type_goto_app.getCode());
		message.setNotifyId(NewPushAppTagEnum.apply_fail.getType());
//		message.addParam(1, "www.baidu.com");
//		message.addParam(2, "百度首页");
//		message.setShowNotif(showNotif);
		message.setTitle("测试消息标题");
		message.setUrl("http://test1.com");
		message.setUser(0);
		System.out.println("推送消息内容:" + JsonHelper.toJson(message));
	}
	
	@Test
	public void testMessageParamDto() {
		MessageParamDto messageParam = new MessageParamDto();
		messageParam.addParam(1, 123);
		messageParam.addParam(2, 456);
		System.out.println("json of result:" + JsonHelper.toJson(messageParam));
	}
	
	@Test
	public void testLongToDateStr() {
		long dateTime = 214502400000L;
		String dateStr = Tools.getDate(dateTime);
		System.out.println("dateStr:" + dateStr);
	}
	
	@Test
	public void testThousandYear() {
		Date date = new Date(Tools.getDayTime(1000L*365));
		String testDate = Tools.getDate(date);
		System.out.println("testDate:" + testDate);
		int age = Tools.getAge(date);
		System.out.println("age:" + age);
	}
	
	@Test
	public void testThousandYear2() {
//		Date date = new Date(Tools.getDayTime(1000L*365));
		Date date = new Date(Tools.getDayTime(60L*365));
		String testDate = Tools.getDate(date);
		System.out.println("testDate:" + testDate);
		
		
		Calendar now = Calendar.getInstance();
		Calendar born = Calendar.getInstance();
		born.setTime(date);

		if (born.after(now)) {
			throw new IllegalArgumentException("Can't be born in the future");
		}

		int age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
		if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
			age -= 1;
		}
		
		System.out.println("age:" + age);
	}
	
	@Test
	public void testUserEntity() {
		UserEntity user = new UserEntity();
		System.out.println("birthday:" + user.getBirthday());
		UserDto dto = new UserDto();
//		dto.setBirthday(Tools.getDate(user.getBirthday()));
	}
	
	@Test
	public void testUniqueId() throws Exception {
//		String transactionFlow = String.valueOf(UniqueIDUtils.getUniqueID());
//		System.out.println("transactionFlow:" + transactionFlow);
	}
	
	@Test
	public void testGetRedDotSql () {
		StringBuilder builder = new StringBuilder();
		List<Integer> types = new ArrayList<>();
		types.add(1);
		types.add(2);
		types.add(3);
		types.add(4);
		types.add(5);
		long userId = 13051798829L;
		int i = 0 , j = types.size();
		for (Integer type : types) {
			builder.append("(select * from ");
			builder.append("t_bussiness_message").append(" ");
			builder.append("where userid = ").append(userId).append(" ");
			builder.append("and type = ").append(type).append(" ");
			builder.append("order by create_time desc limit 0,1)").append(" ");
			if (++i < j) {
				builder.append("union all").append(" ");
			}
		}
		
		System.out.println("redDotSql:" + builder.toString());
	}
	
	@Test
	public void testGetRemindStickCout() {
		ActionResult result = ActionResult.success(5);
		System.out.println(JsonHelper.toJson(result));
	}
	
	@Test
	public void testGetTodayTimeRange() {
		String todayDateStr1 = Tools.getDate();
		System.out.println("todayDateStr1:" + todayDateStr1);
		
		String upperLimit = Tools.getFormatDate(Tools.getNextDayTime(1), TimeTools.YYYY_MM_DD_HH_mm_ss);
		System.out.println("upperLimit:" + upperLimit);
		
		String lowerLimit = Tools.getFormatDate(Tools.getDayTime(), TimeTools.YYYY_MM_DD_HH_mm_ss);
		System.out.println("lowerLimit:" + lowerLimit);
	}
	
	@Test
	public void testGetDateLong() {
		Date date = Tools.getDateTime("2018-02-09 14:40", TimeTools.YYYY_MM_DD_HH_mm);
		long appointTime = date.getTime();
		System.out.println("appointTime:" + appointTime);
	}
	
	@Test
	public void testDateLong() {
		long dispatchDateMill = 1519574400000L;
		Date dispatchDate = Tools.dateValue(dispatchDateMill);
		String dateStr = Tools.getFormatDate(dispatchDate, TimeTools.YYYY_MM_DD_HH_mm);
		System.out.println("dateStr:" + dateStr);
	}

	@Test
	public void testTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");// 这里想要只保留分秒可以写成"mm:ss"
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
		String hms = formatter.format(60001);
		System.out.println(hms);

		// 将毫秒转化为时分秒
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		// 减去时区差
		hms = sdf.format(((61000) - TimeZone.getDefault().getRawOffset()));
		System.out.println("timezone:" + TimeZone.getDefault().getRawOffset());
		System.out.println(hms);
		// 没减去时区
		hms = sdf.format(61000);
		System.out.println("没减去时区:" + hms);
		System.out.println("月日:"+Tools.getFormatDate(new Date(),"MM月dd日"));
		System.out.println("时秒:"+Tools.getFormatDate(new Date(),"HH:mm"));

	}
	@Test
	public void testDateTime() {
		String begin = Tools.getDate(Tools.getDayTime()-Tools.DAY_MILLIS*30);
		Date date = new Date();
		date.setTime(Tools.getDayTime()-Tools.DAY_MILLIS*7);
		System.out.println("date:"+Tools.getFormatDate(date, "yyyyMMdd"));
		System.out.println("begin:"+begin);
		System.out.println("-------------------------");
		String field = "ranking_day";
		int day =1;
		Date cal = new Date();
		cal.setTime(Tools.getDayTime()-Tools.DAY_MILLIS*day);
		String start = Tools.getFormatDate(date, "yyyyMMdd");
		String sql1 = "UPDATE t_short_video SET "+field+"=0 WHERE "+field+">0;";
		String sql2 = "UPDATE  t_short_video s SET "+field+"=(SELECT SUM(watch_num) FROM t_short_video_watch_day_log  w WHERE s.id=w.video_id AND w.watch_time>="+start+") WHERE id IN (SELECT DISTINCT video_id FROM t_short_video_watch_day_log WHERE watch_time>="+start+" );";
		System.out.println(sql1);
		System.out.println(sql2);
	}
	
	@Test
	public void testRunnableTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("阻塞");
			e.printStackTrace();
		}
		InterruptedException e1= new InterruptedException();
		
		System.out.println("结束");
		
	}
}
