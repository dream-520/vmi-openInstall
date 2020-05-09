package com.tigerjoys.shark.miai.service.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.inter.contract.IAppShamDailContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.AppShamDailEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPayActionEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class UserVDailTest extends BaseTestConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAppShamDailContract appShamDailContract;
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	//@Test
	public void testShamDail() throws Exception {
		FileReader fr = new FileReader("E:\\userid\\userid.text2");
		BufferedReader bf = new BufferedReader(fr);
		String str;
		while ((str = bf.readLine()) != null) {
			String[] array = Tools.split(str, " ");
			if(Tools.isNotNull(array)) {
				AppShamDailEntity t = new AppShamDailEntity();
				t.setUserid(Long.parseLong(array[2]));
				t.setCreate_time(Tools.getDateTime(array[0] + " " + array[1]));
				appShamDailContract.insert(t);
			}
		}
		bf.close();
		fr.close();
	}
	
	@Test
	public void testShamDail1() throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("HH");
		//分析获取用户的首次充值
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("status", 1));
		//处理钻石的数据
		pageModel.addQuery(Restrictions.eq("type", 1));
		pageModel.addQuery(Restrictions.ge("create_time", "2019-09-22 00:00:00"));
		pageModel.addQuery(Restrictions.lt("create_time", "2019-09-23 00:00:00"));
		pageModel.addProjection(Projections.min("id").as("id"));
		pageModel.addProjection(Projections.groupProperty("user_id"));
		List<Map<String, Object>> maps = userPayActionContract.loadGroupBy(pageModel);
		//查找是否有对应的通话记录
		List<Long> ids = new ArrayList<Long>();
		if (Tools.isNotNull(maps)) {
			maps.stream().forEach(map -> {
				if (Tools.isNotNull(map)) {
					long id = Tools.parseLong(map.get("id"));
					long userid =  Tools.parseLong(map.get("user_id"));
					ids.add(id);
					logger.error("支付id" + id +" 用户id:" + userid);
				}
			});
		}
		Map<String, Long> datas = new HashMap<String, Long>();
		//分析是否是新用户首充
		if(Tools.isNotNull(ids) && ids.size() > 0) {
			for (Long id : ids) {
				UserPayActionEntity pay = userPayActionContract.findById(id);
				if(Tools.isNotNull(pay)) {
					long userid = pay.getUser_id();
					UserBO bo = userAgent.findById(userid);
					if(Tools.isNotNull(bo)) {
						//当天注册的用户
						if(Tools.getDate(pay.getCreate_time()).equals(Tools.getDate(bo.getCreateTime()))){
							//检测是否有对应的聊天记录
							pageModel.clearAll();
							pageModel.addQuery(Restrictions.eq("userid", userid));
							//pageModel.addQuery(Restrictions.gt("vchat_duration", 0));
							pageModel.addQuery(Restrictions.lt("create_time", pay.getCreate_time()));
							long count = vchatRoomContract.count(pageModel);
							if(count == 0) {
								//处理记录   分时段进行统计
								String hour = f.format(pay.getCreate_time());
								Long value = datas.get(hour);
								if(Tools.isNotNull(value)) {
									datas.put(hour, value+1);
								} else {
									datas.put(hour, 1L);
								}
							}
						}
					}
				}
			}
		}
		//输出对应的记录
		if(Tools.isNotNull(datas)) {
			for(Map.Entry<String, Long> entry : datas.entrySet()){
			    String key = entry.getKey();
			    Long value = entry.getValue();
			    System.err.println(key+":"+value);
			}
		}
	}
	
	@Test
	public void testShamDail2() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.ge("create_time", "2019-09-02 00:00:00"));
		pageModel.addQuery(Restrictions.lt("create_time", "2019-09-18 00:00:00"));
		
		appShamDailContract.loadGroupBy(pageModel );
	}
	
}
