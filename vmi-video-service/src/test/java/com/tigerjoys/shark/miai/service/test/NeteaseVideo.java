package com.tigerjoys.shark.miai.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.constant.NeteaseApiConst;
import com.tigerjoys.shark.miai.agent.utils.CheckSumBuilder;
import com.tigerjoys.shark.miai.inter.contract.IUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.tigerjoys.shark.miai.utils.Helper;

/**
 * 处理网易视频相关的测试
 * @author shiming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class NeteaseVideo extends BaseTestConfig {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IUserContract userContract;
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	//@Test
	public void testgetVideos() throws Exception {
		String api = "https://vcloud.163.com/app/vod/video/list";
		Map<String, String> params = new HashMap<String, String>();
		params.put("currentPage", 1+"");
		params.put("pageSize", 100+"");
		params.put("status", 0+"");
		params.put("type", 0+"");
		//params.put("sortStr", "asc");
		JSONObject json = neteaseAgent.acquireData(api, params);
		if(Tools.isNotNull(json)) {
			logger.error(json.toJSONString());
		}
	}
	
	@Test
	public void testgetVideos2() throws Exception {
		for(int j = 0; j<100; j++) {
			List<Long> vids = new ArrayList<>();
			//获取对应的视频列表
			String api = "https://vcloud.163.com/app/vod/video/list";
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(api);
			
			String appKey = NeteaseApiConst.APP_KEY;
	        String appSecret = NeteaseApiConst.APP_SECRET;
	        String nonce = NeteaseApiConst.NONCE;
	        String curTime = String.valueOf((new Date()).getTime() / 1000L);
	        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

	        // 设置请求的header
	        httpPost.addHeader("AppKey", appKey);
	        httpPost.addHeader("Nonce", nonce);
	        httpPost.addHeader("CurTime", curTime);
	        httpPost.addHeader("CheckSum", checkSum);
	        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

	        Map<String, String> params1 = new HashMap<String, String>();
			params1.put("currentPage", 1+"");
			params1.put("pageSize", 50+"");
			params1.put("status", 0+"");
			params1.put("type", 0+"");
			params1.put("sortStr", "asc");
			
	        //设置请求的参数
	        StringEntity params = new StringEntity(JsonHelper.toJson(params1),Consts.UTF_8);
	        httpPost.setEntity(params);
	        //执行请求
	        HttpResponse response = httpClient.execute(httpPost);
	        if(Tools.isNotNull(response) && response.getStatusLine().getStatusCode() == 200) {
	        	JSONObject object = JsonHelper.toJsonObject(EntityUtils.toString(response.getEntity(), "utf-8"));
	        	if(Tools.isNotNull(object)) {
	        		JSONObject ret = object.getJSONObject("ret");
	        		if(Tools.isNotNull(ret)) {
	        			logger.error("当前视频文件的个数为:"+ret.getIntValue("totalRecords") +"   当前文件的页数为:"+ret.getIntValue("pageSize"));
	        			
	        			JSONArray array = ret.getJSONArray("list");
	        			if(Tools.isNotNull(array) && array.size() > 0) {
	        				for(int i = 0; i < array.size(); i++) {
	        					JSONObject item = array.getJSONObject(i);
	        					if(Tools.isNotNull(item)) {
	        						long vid = item.getLongValue("vid");
	        						long createTime = item.getLongValue("createTime");
	        						logger.error("vid:"+vid+"  createTime:"+createTime);
	        						//检测时间信息  30分钟之内的不进行删除处理
	        						long current = new Date().getTime();
	        						long s = (current - createTime)/1000/60 - 20;
	        						logger.error("获取的录制视频的时间值:"+Tools.getDateTime(createTime)+"  剩余的时间值："+s);
	        						if(s > 0 && vid > 0) {
	        							vids.add(vid);
	        						}
	        					}
	        				}
	        			}
	        		}
	        	}
	        }
	        
	        if(Tools.isNotNull(vids) && vids.size() > 0) {
	        	//进行删除对应的视频操作处理
	            String api2 = "https://vcloud.163.com/app/vod/video/delete";
	            HttpPost httpPost2 = new HttpPost(api2);
	            httpPost2.addHeader("AppKey", appKey);
	            httpPost2.addHeader("Nonce", nonce);
	            httpPost2.addHeader("CurTime", curTime);
	            httpPost2.addHeader("CheckSum", checkSum);
	            httpPost2.addHeader("Content-Type", "application/json;charset=utf-8");
	            Map<String, List<Long>> params2 = new HashMap<String, List<Long>>();
	            params2.put("vids", vids);
	            StringEntity params3 = new StringEntity(JsonHelper.toJson(params2),Consts.UTF_8);
	            httpPost2.setEntity(params3);
	            //执行请求
	            HttpResponse response2 = httpClient.execute(httpPost2);
	            if(Tools.isNotNull(response2) && response2.getStatusLine().getStatusCode() == 200) {
	            	JSONObject object = JsonHelper.toJsonObject(EntityUtils.toString(response2.getEntity(), "utf-8"));
	            	logger.error(object.toJSONString());
	            }
	        }
			Thread.sleep(4000);
		}
	}
	
	/*@Test
	public void testgetAnchorData() throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.gt("fr", 5));
		pageModel.addQuery(Restrictions.gt("id", 65418720958217984L));
		List<UserEntity> users = userContract.load(pageModel);
		if(Tools.isNotNull(users)) {
			for (UserEntity user : users) {
				//现在对应的头像数据和视频数据
				if(Tools.isNotNull(user.getPhoto())) {
					String path = getFileName(user.getNickname(), "jpg");
					boolean suc =  HttpUtils.downloadFile("http://cdn.yoyo.liaomeivideo.com" + user.getPhoto(), path);
					if(suc) {
						
					} else {
						System.err.println("下载失败");
					}
					Thread.sleep(5);
					System.err.println(path);
				}
				
				if(Tools.isNotNull(user.getVideo_auth())) {
					String path = getFileName(user.getNickname(), "mp4");
					boolean suc =  HttpUtils.downloadFile("http://oss.yoyo.liaomeivideo.com" + user.getVideo_auth(), path);
					Thread.sleep(5);
					System.err.println(path);
				}
				
				pageModel.clearAll();
				pageModel.addQuery(Restrictions.eq("userid", user.getId()));
				List<UserPhotoResourceEntity> photos = userPhotoResourceContract.load(pageModel);
				if(Tools.isNotNull(photos)) {
					for (UserPhotoResourceEntity photo : photos) {
						//现在对应的相册数据
						String path = getFileName(user.getNickname(), "jpg");
						boolean suc =  HttpUtils.downloadFile("http://cdn.yoyo.liaomeivideo.com" + photo.getPath(), path);
						Thread.sleep(5);
						System.err.println(path);
					}
				}
			}
		}
	}*/
	
	public static String getFileName(String directory, String fileExt) {
		String filePath = "/upload/"+directory+"/"+IdGenerater.generateId()+Helper.getUploadFileName(fileExt);
		return filePath;
	}
}
