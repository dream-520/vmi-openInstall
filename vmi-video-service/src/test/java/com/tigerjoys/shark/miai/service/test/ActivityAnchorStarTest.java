package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.controller.WebActivityAnchorStar;
import com.tigerjoys.shark.miai.inter.contract.IActivityStarAnchorContract;
import com.tigerjoys.shark.miai.inter.contract.IActivityStarIssueContract;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarAnchorEntity;
import com.tigerjoys.shark.miai.inter.entity.ActivityStarIssueEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class ActivityAnchorStarTest extends BaseTestConfig {

	@Autowired
	private WebActivityAnchorStar webActivityAnchorStar;
	
	@Autowired
	private IActivityStarIssueContract activityStarIssueContract;
	
	@Autowired
	private IActivityStarAnchorContract activityStarAnchorContract;
	
	@Test
	public void testWebActivityAnchorStar() throws Exception {
		//ActionResult data = webActivityAnchorStar.getIndexData();
		//System.err.println(JsonHelper.toJson(data));
	}
	
	@Test
	public void testCommitAnchorStar() throws Exception {
		/*
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("issue", 5);
		obj.put("flower", 2);
		obj.put("userid", 142409136550772992L);
		String body = JsonHelper.toJson(obj);
		System.err.println(body);
		ActionResult data = webActivityAnchorStar.commit(body);
		System.err.println(JsonHelper.toJson(data));
		*/
	}
	
	//@Test
	public void createWebActivityAnchorStar() throws Exception {
		for (int j = 1; j < 30; j++) {
			String time = Tools.getDate(Tools.getDayTime(-j));
			String begin = time + " 12:00:00";
			Date begin_time = Tools.getDateTime(begin);
			Date end_time = Tools.getdate(begin_time, 1);
			
			ActivityStarIssueEntity t = new ActivityStarIssueEntity();
			t.setIssue_number(4+j);
			t.setBegin_time(begin_time);
			t.setEnd_time(end_time);
			t.setCreate_time(new Date());
			activityStarIssueContract.insert(t);
			System.err.println("time:"+time +"begin_time:" + begin_time +"end_time:"+end_time);
		}
	}
	
	@Test
	public void createWebActivityAnchor() throws Exception {
		for (int j = 1; j < 29; j++) {
			//处理获取随机10个数据的处理
			List<Long> ids = new ArrayList<>(40);
			ids.add(139300336738304256L);
			ids.add(132463373943111936L);
			ids.add(132484736267387136L);
			ids.add(134819997018030336L);
			ids.add(135746091768414464L);
			ids.add(139895313287676160L);
			ids.add(140596694176825600L);
			ids.add(133709344461095168L);
			ids.add(132502929635606784L);
			ids.add(141779275492688128L);
			ids.add(141142156510298368L);
			ids.add(139735307573592320L);
			ids.add(141651505301225728L);
			ids.add(135759007125143808L);
			ids.add(135730787866706176L);
			ids.add(140622993547133184L);
			ids.add(143855966188798208L);
			ids.add(143697638542344448L);
			ids.add(144773438031069440L);
			ids.add(138236813792837888L);
			ids.add(137224557351207168L);
			ids.add(139099284963393792L);
			ids.add(137670354794512640L);
			ids.add(141307976152580352L);
			ids.add(137695432814952704L);
			ids.add(145118417446306048L);
			ids.add(142450991168553216L);
			ids.add(101394776322015488L);
			ids.add(135406655255937280L);
			ids.add(145310816942489856L);
			ids.add(145132116653441280L);
			ids.add(135116485508006144L);
			ids.add(139898299455963392L);

			Collections.shuffle(ids);
			for(int i=0; i<=9;i++) {
				ActivityStarAnchorEntity t = new ActivityStarAnchorEntity();
				t.setIssue_number(5+j);
				int charm = getRandomNumber(2000,8000);
				t.setCharm_init(charm);
				t.setCharm_current(charm);
				t.setAnchor_id(ids.get(i));
				t.setCreate_time(new Date());
				activityStarAnchorContract.insert(t);
			}
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
}
