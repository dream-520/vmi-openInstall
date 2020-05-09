package com.tigerjoys.shark.miai.agent.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.inter.contract.IAaPContract;
import com.tigerjoys.shark.miai.inter.contract.IAaPdContract;
import com.tigerjoys.shark.miai.inter.entity.AaPEntity;
import com.tigerjoys.shark.miai.inter.entity.AaPdEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.tigerjoys.shark.miai.utils.Helper;

public class SpiderTest extends BaseTestConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpiderTest.class);
	
	@Autowired
	private IAaPContract aaPContract;
	
	@Autowired
	private IAaPdContract aaPdContract;
	
	@Autowired
	private IAppAreaService appAreaService;
	
	/*
	 * 访问获取所有类别的最新图片列表
	 */
	//@Test
	public void testSpiderUrlDataNew() throws Exception {
		Map<Integer, String> types = new HashMap<>();
		types.put(1, "4e4d610cdf714d2966000002");//风景 type=1
		types.put(2, "4fb479f75ba1c65561000027");//视觉 type=2
		types.put(3, "4ef0a35c0569795756000000");//情感 type=3
		types.put(4, "4fb47a195ba1c60ca5000222");//设计 type=4
		types.put(5, "4fb47a465ba1c65561000028");//物语 type=5
		types.put(6, "4ef0a3330569795757000000");//艺术 type=6
		types.put(7, "4fb47a305ba1c60ca5000223");//城市 type=7
		types.put(8, "4ef0a34e0569795757000001");//运动 type=8
		types.put(9, "4fb47a305ba1c60ca5000223");//城市 type=9
		types.put(10,"4e4d610cdf714d2966000004");//卡通 type=10
		types.put(11,"4e4d610cdf714d2966000001");//动物 type=11
		types.put(12,"4e4d610cdf714d2966000000");//美女 type=12
		types.put(13,"4e4d610cdf714d2966000003");//动漫 type=13
		types.put(14,"4e58c2570569791a19000000");//影视 type=14
		types.put(15,"4e4d610cdf714d2966000005");//机械 type=15
		types.put(16,"5109e05248d5b9368bb559dc");//明星 type=16
		types.put(17,"4e4d610cdf714d2966000006");//男人 type=17
		types.put(18,"5109e04e48d5b9364ae9ac45");//文字 type=18
		types.put(19,"4e4d610cdf714d2966000007");//游戏 type=19
		for(int j=1; j<=19; j++) {
			//跳过第九个重复的数据
			if(j==9)
				continue;
			//循环遍历所有的类型
			String type = types.get(j);
			if(Tools.isNotNull(type) && type.length() > 0) {
				//遍历对应的类型数据
		        for(int i = 0; i < 100; i++) {
		        	boolean flag = false;
		        	int index = i*30;
		        	String url = null;
		        	if(i==0) {
		        		url = "http://service.aibizhi.adesk.com/v1/wallpaper/category/"+type+"/wallpaper?limit=30&adult=false&first=1&order=new";
		        	} else {
						url = "http://service.aibizhi.adesk.com/v1/wallpaper/category/"+type+"/wallpaper?limit=30&skip="+index+"&adult=false&first=0&order=new";
					}
		            Map<String , String> headerMap = new HashMap<>();
		            headerMap.put("Host", "service.aibizhi.adesk.com");
		            headerMap.put("Connection", "Keep-Alive");
		            headerMap.put("Accept-Encoding", "gzip");
		            ResponseStatus response = HttpUtils.get(url, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
		            if(Tools.isNotNull(response)) {
		                LOGGER.error(response.getContent());
		                //这里需要将采集到的对应的用户数据插入到数据库中
		                JSONObject json = JsonHelper.toJsonObject(response.getContent());
		                if(Tools.isNotNull(json)) {
		                	int code = json.getIntValue("code");
		                	String msg = json.getString("msg");
		                	if(code == 0 && msg.equals("success")) {
		                		AaPEntity t = new AaPEntity();
		                		t.setNum(i);
		                		t.setType(j);
		                		t.setData(response.getContent());
		    					aaPContract.insert(t);
		    					flag = true;
		                	}
		                }
		            }
		            if(!flag)
		            	break;
		            Thread.sleep(getRandomNumber(4,6)*1000);
		        }
			}
		}
	}
	
	/*
	 * 访问所有类别的最热图片列表
	 */
	//@Test
	public void testSpiderUrlDataHot() throws Exception {
		Map<Integer, String> types = new HashMap<>();
		types.put(1, "4e4d610cdf714d2966000002");//风景 type=1
		types.put(2, "4fb479f75ba1c65561000027");//视觉 type=2
		types.put(3, "4ef0a35c0569795756000000");//情感 type=3
		types.put(4, "4fb47a195ba1c60ca5000222");//设计 type=4
		types.put(5, "4fb47a465ba1c65561000028");//物语 type=5
		types.put(6, "4ef0a3330569795757000000");//艺术 type=6
		types.put(7, "4fb47a305ba1c60ca5000223");//城市 type=7
		types.put(8, "4ef0a34e0569795757000001");//运动 type=8
		types.put(9, "4fb47a305ba1c60ca5000223");//城市 type=9
		types.put(10,"4e4d610cdf714d2966000004");//卡通 type=10
		types.put(11,"4e4d610cdf714d2966000001");//动物 type=11
		types.put(12,"4e4d610cdf714d2966000000");//美女 type=12
		types.put(13,"4e4d610cdf714d2966000003");//动漫 type=13
		types.put(14,"4e58c2570569791a19000000");//影视 type=14
		types.put(15,"4e4d610cdf714d2966000005");//机械 type=15
		types.put(16,"5109e05248d5b9368bb559dc");//明星 type=16
		types.put(17,"4e4d610cdf714d2966000006");//男人 type=17
		types.put(18,"5109e04e48d5b9364ae9ac45");//文字 type=18
		types.put(19,"4e4d610cdf714d2966000007");//游戏 type=19
		for(int j=1; j<=19; j++) {
			//跳过第九个重复的数据
			if(j==9)
				continue;
			//循环遍历所有的类型
			String type = types.get(j);
			if(Tools.isNotNull(type) && type.length() > 0) {
				//遍历对应的类型数据
		        for(int i = 0; i < 100; i++) {
		        	boolean flag = false;
		        	int index = i*30;
		        	String url = null;
		        	if(i==0) {
		        		url = "http://service.aibizhi.adesk.com/v1/wallpaper/category/"+type+"/wallpaper?limit=30&adult=false&first=1&order=hot";
		        	} else {
						url = "http://service.aibizhi.adesk.com/v1/wallpaper/category/"+type+"/wallpaper?limit=30&skip="+index+"&adult=false&first=0&order=hot";
					}
		            Map<String , String> headerMap = new HashMap<>();
		            headerMap.put("Host", "service.aibizhi.adesk.com");
		            headerMap.put("Connection", "Keep-Alive");
		            headerMap.put("Accept-Encoding", "gzip");
		            ResponseStatus response = HttpUtils.get(url, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
		            if(Tools.isNotNull(response)) {
		                LOGGER.error(response.getContent());
		                //这里需要将采集到的对应的用户数据插入到数据库中
		                JSONObject json = JsonHelper.toJsonObject(response.getContent());
		                if(Tools.isNotNull(json)) {
		                	int code = json.getIntValue("code");
		                	String msg = json.getString("msg");
		                	if(code == 0 && msg.equals("success")) {
		                		AaPEntity t = new AaPEntity();
		                		t.setNum(i);
		                		t.setType(j);
		                		t.setData(response.getContent());
		    					aaPContract.insert(t);
		    					flag = true;
		                	}
		                }
		            }
		            if(!flag)
		            	break;
		            Thread.sleep(getRandomNumber(3,5)*1000);
		        }
			}
		}
	}
	
	/*
	 * 分析所有类别的需要进行下载图片对应的图片路径
	 */
	//@Test
	public void testDownDataAnalyse() throws Exception {
		//循环对应的9个类型进行数据解析操作处理
		for(int type = 1; type <= 19; type++) {
			PageModel pageModel = PageModel.getPageModel();
			//增加对应的id值 来控制搜索的数据范围
			pageModel.addQuery(Restrictions.ge("id", 3600));
			pageModel.addQuery(Restrictions.eq("type", type));
			List<AaPEntity> list = aaPContract.load(pageModel);
			if(Tools.isNotNull(list)) {
				//循环进行数据解析操作处理
				for (AaPEntity entity : list) {
					//解析数据
					String data = entity.getData();
					JSONObject json = JsonHelper.toJsonObject(data);
					if(Tools.isNotNull(json)) {
						JSONObject res = json.getJSONObject("res");
						if(Tools.isNotNull(res)) {
							JSONArray array = res.getJSONArray("wallpaper");
							if(Tools.isNotNull(array)) {
								for(int i = 0; i < array.size(); i++) {
									JSONObject o = array.getJSONObject(i);
									String id = o.getString("id");
									String preview = o.getString("preview");
									String rule = o.getString("rule_new");
									int rank = o.getIntValue("rank");
									//查询对应的数据是否已经存储到数据库中  
									AaPdEntity a = aaPdContract.findByProperty("code", id);
									if(Tools.isNull(a) && Tools.isNotNull(rule)) {
										AaPdEntity t = new AaPdEntity();
										t.setCode(id);
										t.setPreview(preview);
										t.setRule(rule);
										t.setRank(rank);
										t.setFalg(0);
										t.setType(entity.getType());
										if(rule.equals("?imageMogr2/thumbnail/!$<Width>x$<Height>r/gravity/Center/crop/$<Width>x$<Height>")) {
											t.setUrl(preview+"?imageMogr2/thumbnail/!2160x1920r/gravity/Center/crop/2160x1920&adult=false");
										} else {
											t.setUrl("11");
										}
										aaPdContract.insert(t);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Test
	public void testDownPicture1() throws Exception {
		Map<Integer, String> names = new HashMap<>();
		names.put(1, "scenery");//风景 type=1
		names.put(2, "vision");//视觉 type=2
		names.put(3, "emotion");//情感 type=3
		names.put(4, "design");//设计 type=4
		names.put(5, "wuyu");//物语 type=5
		names.put(6, "art");//艺术 type=6
		names.put(7, "city");//城市 type=7
		names.put(8, "sport");//运动 type=8
		names.put(9, "city");//城市 type=9
		names.put(10,"cartoon");//卡通 type=10
		names.put(11,"animal");//动物 type=11
		names.put(12,"woman");//美女 type=12
		names.put(13,"manga");//动漫 type=13
		names.put(14,"video");//影视 type=14
		names.put(15,"machine");//机械 type=15
		names.put(16,"star");//明星 type=16
		names.put(17,"man");//男人 type=17
		names.put(18,"character");//文字 type=18
		names.put(19,"game");//游戏 type=19
		//循环对应的19个类型进行数据解析操作处理
		for(int type = 1; type <= 10; type++) {
			//跳过第九个重复的数据
			if(type==9)
				continue;
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("type", type));
			pageModel.addQuery(Restrictions.eq("falg", 0));
			pageModel.addQuery(Restrictions.ge("id", 32193));
			String name = names.get(type);
			if(Tools.isNotNull(name) && name.length() > 0) {
				List<AaPdEntity> list = aaPdContract.load(pageModel);
				if(Tools.isNotNull(list)) {
					//循环进行数据解析操作处理
					for (AaPdEntity entity : list) {
						if(entity.getFalg() == 0 && !entity.getUrl().equals("11")) {
							String fileExt = "jpg";
							String picture = getFileName("wallpaper/"+name+"/picture", fileExt);
							//启动下载头像
							boolean issuccess =  HttpUtils.downloadFile(entity.getUrl(), picture);
							if(issuccess) {
								entity.setFalg(1);
								entity.setName(picture);
								aaPdContract.update(entity);
							}
							//暂停时间
							int sleep = getRandomNumber(3,5);
							Thread.sleep(sleep*1000);
						}
					}
				}
			}
		}
	}
	
	@Test
	public void testDownPicture2() throws Exception {
		Map<Integer, String> names = new HashMap<>();
		names.put(1, "scenery");//风景 type=1
		names.put(2, "vision");//视觉 type=2
		names.put(3, "emotion");//情感 type=3
		names.put(4, "design");//设计 type=4
		names.put(5, "wuyu");//物语 type=5
		names.put(6, "art");//艺术 type=6
		names.put(7, "city");//城市 type=7
		names.put(8, "sport");//运动 type=8
		names.put(9, "city");//城市 type=9
		names.put(10,"cartoon");//卡通 type=10
		names.put(11,"animal");//动物 type=11
		names.put(12,"woman");//美女 type=12
		names.put(13,"manga");//动漫 type=13
		names.put(14,"video");//影视 type=14
		names.put(15,"machine");//机械 type=15
		names.put(16,"star");//明星 type=16
		names.put(17,"man");//男人 type=17
		names.put(18,"character");//文字 type=18
		names.put(19,"game");//游戏 type=19
		//循环对应的19个类型进行数据解析操作处理
		for(int type = 11; type <= 19; type++) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("type", type));
			pageModel.addQuery(Restrictions.eq("falg", 0));
			pageModel.addQuery(Restrictions.ge("id", 32193));
			String name = names.get(type);
			if(Tools.isNotNull(name) && name.length() > 0) {
				List<AaPdEntity> list = aaPdContract.load(pageModel);
				if(Tools.isNotNull(list)) {
					//循环进行数据解析操作处理
					for (AaPdEntity entity : list) {
						if(entity.getFalg() == 0 && !entity.getUrl().equals("11")) {
							String fileExt = "jpg";
							String picture = getFileName("wallpaper/"+name+"/picture", fileExt);
							//启动下载头像
							boolean issuccess =  HttpUtils.downloadFile(entity.getUrl(), picture);
							if(issuccess) {
								entity.setFalg(1);
								entity.setName(picture);
								aaPdContract.update(entity);
							}
							//暂停时间
							int sleep = getRandomNumber(3,5);
							Thread.sleep(sleep*1000);
						}
					}
				}
			}
		}
	}
	
	public int getRandomNumber(int min, int max) {
		Random random = new Random();  
		int randomNumber =  random.nextInt(max)%(max-min+1) + min; 
		return randomNumber;
	}
	
	public static String getFileName(String directory, String fileExt) {
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(fileExt);
		return filePath;
	}
	
	//@Test
	public void testObject() {
		AreaDto[] areas = appAreaService.getAreasByBaiduCode(55);
		System.err.println(areas);
		for (AreaDto areaDto : areas) {
			System.err.println(areaDto.getName());
		}
	}
	
	//@Test
	public void test() {
		String tt="123kg";
		long s = Tools.parseLong(tt);
		System.err.println(s);
	}
	
	@Test
	public void test2() {
		String s1 = "18601111473";
		String s2 = "15666666666";
		System.err.println("11111"+s1.equals(s2));
	}
	
	//@Test
	public void testWxPay() throws HttpException, IOException {
		String url1 = "https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx261613105871362edf5c4aa34275045761&package=744405892";
        Map<String , String> headerMap1 = new HashMap<>();
        headerMap1.put("Host", "wx.tenpay.com");
        headerMap1.put("Connection", "Keep-Alive");
        headerMap1.put("Referer", "https://wxpay.wxutil.com/mch/pay/h5.v2.php");
        headerMap1.put("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36");
        
        ResponseStatus response1 = HttpUtils.get(url1, ECharset.UTF_8, EContentType.APPLICATION_JSON, headerMap1, null);
        if(Tools.isNotNull(response1)){
            LOGGER.error(response1.getContent());
            String content = response1.getContent();
            if(Tools.isNotNull(content)) {
            	//String rgex = "^weixin://\"$";
            	//String rgex = "^weixin://\\w{0,}\"$";
            	String rgex = "weixin://wap/pay.{0,}";
            	Pattern pattern = Pattern.compile(rgex);
            	Matcher m = pattern.matcher(content);
            	while(m.find()){  
            		System.err.println("123");
                    System.err.println(m.group());
                    String data = m.group();
                    data = data.replace(";", "");
                    data = data.replace("\"", "");
                    if(Tools.isNotNull(data)) {
                    	System.err.println(data);
                    }
                }  
            }
        }
	}
	
}
