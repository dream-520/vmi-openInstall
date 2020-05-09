package com.tigerjoys.shark.miai.agent.test;

import java.io.Serializable;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.enums.EContentType;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.inter.contract.IAVContract;
import com.tigerjoys.shark.miai.inter.entity.AVEntity;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class VSpiderTest extends BaseTestConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(VSpiderTest.class);
	
	@Autowired
	private IAVContract aVContract;
	
	@Test
	public void testSpiderData() throws Exception {
		VRobotInfo robot = new VRobotInfo();
		robot.setInterfaceVersionCode("44");
		robot.setInterfacesystemversion("8.0.0");
		robot.setInterfaceversion("4.2");
		robot.setInterfacesystemtype("Xiaomi MI 6 8.0.0");
		robot.setInterfaceChannel("xiaomi");
		robot.setInterfacesource("android");
		
		robot.setAppmarket("xiaomi");
		robot.setAppversion("4.2");
		robot.setPassword("s1234567");
		robot.setPhonenumber("18500317816");
		robot.setPhonebrand("Xiaomi");
		robot.setPhonemodel("MI 6");
		robot.setPhoneos("8.0.0");
		LoginVResult login = null;
		login = login(robot);
		if(Tools.isNotNull(login) && login.getUserId() > 0) {
			//登录之后进行暂时等待一小段时间
			Thread.sleep(1500);
			//登录成功后  直接拉取对应的 三星  四星  五星的列表数据
            for(int tag = 3; tag <= 5; tag++) {
            	 //初始化抓取的分页结构
                PageVQuery query = new PageVQuery();
                query.setPage(1);
                query.setTagId(tag);
                boolean flag = true;
                int errors = 0;
                do {
                    LOGGER.info("当前抓取的tag:"+query.getTagId() +" 当前需要抓取的页码:"+query.getPage());
                    PageVResult pageData = spider(robot, login, query);
                    LOGGER.info("当前抓取到的数据结构信息:"+ JsonHelper.toJson(pageData));
                    if(Tools.isNotNull(pageData) && pageData.isResult()) {
                    	//获取对列表数据的解析 同时启动抓取对应的用户数据处理
                    	String data = pageData.getData();
                    	if(Tools.isNotNull(data) && data.length() > 0) {
                    		//解析列表  同时出发请求个人主页数据
                    		//data部分{"result":true,"maxPage":8,"currPage":"1","data":[{"id":1338229,"nickname":"\u9648\u4f73\u6a31","topic":"\u8bf7\u5c3d\u60c5\u5429\u5490\u59b2\u5df1\uff5e","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1926581,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/BtHjGeXFHc.jpg"},"isOneFree":0},{"id":1688977,"nickname":"\u96e8\u6b23","topic":"\u5317\u4eac\u6210\u90fd\u7518\u8083","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1930871,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/J7ZeQQXAQz.jpg"},"isOneFree":0},{"id":1366649,"nickname":"AMY\u6851","topic":"\u5c31\u7231\u5403\u5927\u767d\u5154\u7cd6\ud83d\udc30","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1929657,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/SamyJibF2a.jpg"},"isOneFree":0},{"id":1751421,"nickname":"\u56fe\u56fe","topic":"\u9760\u8fd1\u6211\uff0c\u6e29\u6696\u4f60","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1933021,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1751421_1536825849000_ps1guig2cg2z17mx0wh2"},"isOneFree":0},{"id":1672652,"nickname":"\u8c28\u6c90","topic":"\u591c\u6df1\u4eba\u9759\u7684\u65f6\u5019","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1919116,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1672652_1533544891000_nvqvnnzs06jr3yoo101q"},"isOneFree":0},{"id":2129449,"nickname":"\u5c0f\u5c0f","topic":"\u6211\u53ef\u4e0d\u53ef\u4ee5\u505a\u4f60\u7684\u5b9d\u8d1d","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":2118648,"width":500,"height":503,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/2129449_1541066559_12841418"},"isOneFree":0},{"id":758760,"nickname":"\u4e0a\u5b98\u7075\u513f","topic":"\u9700\u8981\u4f60\u966a","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":2035718,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/758760_1539689668000_zqzdw6vkbb1trzadtpwq"},"isOneFree":0},{"id":1131354,"nickname":"\u949f\u9716","topic":"*\u4e1d\u52ff\u6270","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1909031,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1131354_1531912400000_uutn9x4c2lvva0kczrit"},"isOneFree":0},{"id":1290546,"nickname":"nina","topic":"\u5355\u8eab\u5f88\u4e45\u4e86\u60f3\u627e\u4e2a\u7537\u670b\u53cb\uff5e","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1917293,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1290546_1533196765000_vhutqoflwr8s7x0xlqrn"},"isOneFree":0},{"id":1778141,"nickname":"\u8bb8\u51b0\u513f","topic":"\u6211\u559c\u6b22\u6d77\uff0c\u4f60\u559c\u6b22\u6d6a\ud83c\udf0a\u5417\uff1f","vcoinPerMinute":8,"level":"3","online":1,"avatar":{"id":1930027,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1778141_1536129719000_37mvfv2we5q1wonx8szd"},"isOneFree":0},{"id":1436637,"nickname":"\u5c0fG\u5a1c","topic":"\u7ea6\u8d77\u6765\u2026\u2026\u4e0d\u770b\u6253\u5b57","vcoinPerMinute":8,"level":"3","online":2,"avatar":{"id":2023309,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1436637_1539434988000_9jrc1f527tdytydv76wf"},"isOneFree":0},{"id":280732,"nickname":"\u4efb\u83b9\u6a31","topic":"\u8bf7\u6587\u660e\u89c2\u7403","vcoinPerMinute":8,"level":"3","online":2,"avatar":{"id":1906311,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/280732_1531443173000_t4bntfxibxcb1u8yxbii"},"isOneFree":0},{"id":1252330,"nickname":"Sherry","topic":"\u6211\u559c\u6b22\u6162\u6162\u6df1\u5165\u7684\uff0c\u4f60\u559c\u6b22\u5417","vcoinPerMinute":8,"level":"3","online":2,"avatar":{"id":1886527,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1252330_1528330411000_yqemlk54pt66ve7k4rpc"},"isOneFree":0},{"id":2062232,"nickname":"\u9759\u9759lucky","topic":"\u4f60\u6562\u6765\u64a9\u4e48","vcoinPerMinute":8,"level":"3","online":2,"avatar":{"id":2078008,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/NC7z6SrGQK.jpg"},"isOneFree":0},{"id":236638,"nickname":"Yumi\u5189\u5927\u5927","topic":"\u559c\u6b22\u6211\u5c31\u70b9\u6211\u5427","vcoinPerMinute":8,"level":"3","online":3,"avatar":{"id":2146459,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/236638_1541453917000_7lrikxdz6563ixl62o3u"},"isOneFree":0},{"id":228589,"nickname":"\u767d\u5c0f\u5c0f","topic":"\u4e2a\u6027\u59b9\u5b50\u4e5f\u53ef\u4ee5\u6e29\u67d4\u53ef\u4eba","vcoinPerMinute":8,"level":"3","online":3,"avatar":{"id":2139839,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/228589_1541350720000_kf8yar0xi676zyb9y9kd"},"isOneFree":0},{"id":1059127,"nickname":"\u871c\u5416","topic":"\u6e29\u67d4\u53c8\u4f53\u8d34","vcoinPerMinute":8,"level":"3","online":3,"avatar":{"id":2077176,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1059127_1540377112000_1r7wjs0koort8heuf7ag"},"isOneFree":0},{"id":2063536,"nickname":"\u7433\u7433","topic":"\u65b0\u4eba\u4e00\u679a","vcoinPerMinute":8,"level":"3","online":3,"avatar":{"id":2071075,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/2063536_1540281545000_wenxysjla0xghvzb0dm9"},"isOneFree":0},{"id":342348,"nickname":"\u7b71\u8d1d","topic":"\u5404\u79cd\u59ff\u52bf\u5404\u79cd\u62db \u5404\u79cd\u6f8e\u6e43\u5404\u79cd\u98d8","vcoinPerMinute":8,"level":"3","online":3,"avatar":{"id":1888087,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/342348_1528537451000_hm5qukbsim0za34tbg9l"},"isOneFree":0},{"id":1813049,"nickname":"\u5f20\u70b9\u5fc3","topic":"\u4e00\u5f20\u767d\u7eb8\uff0c\u8981\u4f60tiao\u2006jia","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1929322,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1813049_1535958221000_51cyjiar7nlwbznc9g71"},"isOneFree":0},{"id":370913,"nickname":"\u5c39\u6ce1\u8299","topic":"\u4f60\u559c\u6b22\u7684\u6211\u90fd\u53ef\u4ee5\u968f\u610f\u5207\u6362","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1834148,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/370913_1518248453000_l69u7n630wbaqsb9fvv7"},"isOneFree":0},{"id":60705,"nickname":"\u5a49\u745c","topic":"\u53ef\u7231\u53ea\u56e0\u6709\u4f60\u624d\u663e\u7684\u683c\u5916\u73cd\u8d35","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1930626,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/dKiDAhp56W.jpg"},"isOneFree":0},{"id":1781109,"nickname":"\u91d1\u5b9d\u62c9","topic":"\u8c08\u8c08\u4eba\u751f\u804a\u804a\u7406\u60f3","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1930446,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/YfbCkSSGmc.jpg"},"isOneFree":0},{"id":1115573,"nickname":"\u7ae5\u4e9a\u840d","topic":"I watch for yo\uff01","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1929593,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/YbD4bG8a5b.jpg"},"isOneFree":0},{"id":1226723,"nickname":"\u6881\u5f81","topic":"\u4f1a\u804a\u5929\uff0c\u4f1a\u7b97\u547d\uff0c\u8fd8\u80fd\u65fa\u4f60","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1921892,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1226723_1534154614000_cfj3dp0rx4oqglq8ixkp"},"isOneFree":0}]}
                    		JSONArray array = JsonHelper.toJsonArray(data);
                    		if(Tools.isNotNull(array) && array.size() > 0) {
                    			for (int i=0; i < array.size(); i++) {
                    				JSONObject anchor = array.getJSONObject(i);
                    				long id = anchor.getLongValue("id");
                    				String nickname = anchor.getString("nickname");
                    				//循环获取对应的主播的相关信息
                    				if(id > 0 && Tools.isNotNull(nickname)) {
                    					peopleBase(robot, login, id, nickname);
                    					Thread.sleep(800);
                    					peopleData(robot, login, id, nickname);
                    					Thread.sleep(800);
                    					peopleVideo(robot, login, id, nickname);
                    					Thread.sleep(1500);
                    				}
								}
                    		}
                    	}
                        //成功一次将原先的错误清除
                        errors = 0;
                        //设置进行抓取下一页数据
                        query.setPage(query.getPage()+1);
                        //如果抓取的页码大于了最大的页码 就进行退出处理
                        if(query.getPage() > pageData.getMaxPage()) {
                            flag = false;
                            break;
                        }
                        //进行暂时等待一小段时间
                        try {
                            Thread.sleep(getRandom(2,6)*1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        errors++;
                        //错误3此这个地方才进行退出本次tag的操作处理
                        if(errors > 3)
                            flag = false;
                    }
                } while(flag);
                
            }
		}
	}
	
	
	/**
     * 进行V聊用户登录处理的接口
     */
    /**
     *  POST http://sp40xiaomi.vliao12.com/auth/phone-number-login HTTP/1.1
     InterfaceSystemVersion: 8.0.0
     InterfaceSystemType: Xiaomi MI 6 8.0.0
     interfaceVersion: 4.0
     interfaceSource: android
     Content-Type: application/x-www-form-urlencoded
     Content-Length: 123
     Host: sp40xiaomi.vliao12.com
     Connection: Keep-Alive
     Accept-Encoding: gzip
     User-Agent: okhttp/3.8.0

     phoneNumber=15652533152&password=ss123456&phoneBrand=Xiaomi&phoneOs=8.0.0&phoneModel=MI%206&appMarket=xiaomi&appVersion=4.0

     HTTP/1.1 200 OK
     Date: Fri, 13 Jul 2018 02:43:21 GMT
     Content-Type: application/json
     Connection: keep-alive
     X-Powered-By: PHP/7.1.7
     Cache-Control: no-cache
     Set-Cookie: SERVERID=e94c39e198b1a4d88a06ab91cc779866|1531449801|1531449801;Path=/
     Content-Length: 486

     {"result":true,"user":{"id":1491609,"nickname":"\u4f60\u597d","avatar":{"id":1,"width":512,"height":512,"sn":-1,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/default_avatar.jpg"},"regTime":"2018-07-12 17:43:11","userKey":"70fb04d9e2eaa7da449007090df48953","phoneNumber":"15652533152","categoryId":1,"isCelebrityReviewing":0,"publicAccountList":[],"isDisabled":0,"disabledContent":"","vcoinAmount":0,"isNoDisturbing":0,"accid":"vliour_1491609","token":"d5bf0ca48898dc389ea554f72a4fc929"}}
     
     phoneNumber=18500317816&password=s1234567&phoneBrand=Xiaomi&phoneOs=8.0.0&phoneModel=MI%206&appMarket=xiaomi&appVersion=4.2
     */
    private LoginVResult login(VRobotInfo robot){
        String url = "http://sp40xiaomi.vliao12.com/auth/phone-number-login";
        Map<String , String> headerMap = new HashMap<>();
        headerMap.put("InterfaceSystemVersion", robot.getInterfacesystemversion());
        headerMap.put("InterfaceSystemType", robot.getInterfacesystemtype());
        headerMap.put("interfaceVersion", robot.getInterfaceversion());
        headerMap.put("interfaceSource", robot.getInterfacesource());
        headerMap.put("InterfaceChannel", robot.getInterfaceChannel());
        headerMap.put("InterfaceVersionCode", robot.getInterfaceVersionCode());
        headerMap.put("AppName", "vchat");
        headerMap.put("User-Agent", "okhttp/3.8.0");
        headerMap.put("Host", "sp40xiaomi.vliao12.com");
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Accept-Encoding", "gzip");
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("phoneNumber", robot.getPhonenumber());
        parameterMap.put("password", robot.getPassword());
        parameterMap.put("phoneBrand", robot.getPhonebrand());
        parameterMap.put("phoneOs", robot.getPhoneos());
        parameterMap.put("phoneModel", robot.getPhonemodel());
        parameterMap.put("appMarket", robot.getAppmarket());
        parameterMap.put("appVersion", robot.getAppversion());
        ResponseStatus response;
        LoginVResult result = new LoginVResult();
        try {
            response = HttpUtils.post(url, parameterMap, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
            if(Tools.isNotNull(response)){
                //这个地方需要解析列表数据 然后进入对应的个人主页获取对应的用户数据
                LOGGER.error(response.getContent());
                JSONObject json = JsonHelper.toJsonObject(response.getContent());
                if (Tools.isNotNull(json)){
                    LOGGER.error(json.toJSONString());
                    //解析对应的json数据
                    boolean code = json.getBooleanValue("result");
                    long id = 0;
                    String userKey = "";
                    String token = "";
                    JSONObject user = json.getJSONObject("user");
                    if(Tools.isNotNull(user)) {
                        id = user.getLongValue("id");
                        userKey = user.getString("userKey");
                        token = user.getString("token");
                    }
                    result.setResult(code);
                    result.setUserId(id);
                    result.setUserKey(userKey);
                    result.setToken(token);
                }
            }
            return result;
        } catch (NoRouteToHostException | ConnectTimeoutException | SocketTimeoutException | HttpHostConnectException e){
            LOGGER.error("代理出现了问题");
            LOGGER.error(e.toString());
            result.setError(1);
            return result;
        } catch (JSONException e){
            //处理代理的Bad网关错误
            LOGGER.error("返回的内容是 网关出现了错误");
            LOGGER.error(e.toString());
            result.setError(1);
            return result;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return result;
        }
    }
    
    /**
     * 拉取星级列表数据
     */
	/*
	 *  POST http://sp40xiaomi.vliao12.com/v31/homepage HTTP/1.1
		InterfaceSystemVersion: 8.0.0
		InterfaceSystemType: Xiaomi MI 6 8.0.0
		interfaceVersion: 4.0
		interfaceSource: android
		Content-Type: application/x-www-form-urlencoded
		Content-Length: 70
		Host: sp40xiaomi.vliao12.com
		Connection: Keep-Alive
		Accept-Encoding: gzip
		User-Agent: okhttp/3.8.0

		userId=1491609&userKey=70fb04d9e2eaa7da449007090df48953&tagId=3&page=1

		HTTP/1.1 200 OK
		Date: Fri, 13 Jul 2018 02:46:02 GMT
		Content-Type: application/json
		Connection: keep-alive
		X-Powered-By: PHP/7.1.7
		Cache-Control: no-cache
		Set-Cookie: SERVERID=e94c39e198b1a4d88a06ab91cc779866|1531449962|1531449962;Path=/
		Content-Length: 7636

		{"result":true,"maxPage":7,"currPage":"1","data":[{"id":929917,"nickname":"Zoey","topic":"\u6765\u627e\u6211\u89c6\u9891\u5427\uff0c\u672c\u4eba\u6bd4\u7167\u7247\u597d\u770b\u54e6","vcoinPerMinute":8,"level":"3","online":"1","avatar":{"id":1899393,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/YbnkAipQHp.jpg"},"isOneFree":0},{"id":941971,"nickname":"\u67f3\u7f8e\u598d","topic":"\u559c\u6b22\u6211\u7684\u4f60 \u773c\u5149\u771f\u597d","vcoinPerMinute":8,"level":"3","online":"1","avatar":{"id":1905303,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/941971_1531280464000_luaf3lf754p0s0rk0trg"},"isOneFree":"1"},{"id":1426901,"nickname":"\u5154\u7259\u513f","topic":"\u968f\u9047\u800c\u5b89","vcoinPerMinute":8,"level":"3","online":"1","avatar":{"id":1905561,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/hPzctFWjmC.jpg"},"isOneFree":"1"},{"id":844985,"nickname":"\u8a00\u6653\u989c","topic":"\u672c\u4eba\u66f4\u597d\u770b\uff0c\u4e56\u4e56\u7b49\u4f60\u2661","vcoinPerMinute":8,"level":"3","online":"1","avatar":{"id":1897483,"width":900,"height":900,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/844985_1529933595161_7oecwm5nqugp81d1qott"},"isOneFree":1},{"id":538993,"nickname":"\u827a\u7eaf","topic":"\u6211\u5728\u5317\u4eac\u4f60\u5728\u54ea","vcoinPerMinute":8,"level":"3","online":"1","avatar":{"id":1852006,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/538993_1522315905000_unkxrw1v9enaynzscr15"},"isOneFree":1},{"id":280732,"nickname":"\u4efb\u83b9\u6a31","topic":"\u5fa1\u59d0\u5916\u8868\u841d\u8389\u5185\u5fc3\u6027\u683c\u6587\u827a\u7231\u5065\u8eab","vcoinPerMinute":8,"level":"3","online":"2","avatar":{"id":1906311,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/280732_1531443173000_t4bntfxibxcb1u8yxbii"},"isOneFree":0},{"id":869729,"nickname":"\u97e9\u68a6\u5189","topic":"\u4f60\u60f3\u8981\u7684\u4f60\u559c\u6b22\u7684\u6211\u90fd\u7ed9\u4f60\u54e6~","vcoinPerMinute":8,"level":"3","online":"2","avatar":{"id":1857407,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/869729_1523289005000_7b1g4t7b52kk3vaa5ujk"},"isOneFree":0},{"id":587711,"nickname":"debbie\u591c\u672a\u592e","topic":"\u804a\u5230\u4f60\u4f1a\u559c\u6b22\u6211","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1831781,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/587711_1517417152000_yo3qqj82g7wpdj5q4fel"},"isOneFree":0},{"id":247827,"nickname":"\u8ff7\u4eba\u7684\u6df7\u86cb","topic":"\u8d50\u4f60\u4e00\u9189\u7684\u6c14\u52bf\u4e0d\u77e5\u9053\u8bb0\u51e0\u662f\u8c01","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1894651,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/247827_1529496965000_bj18kuhhvljir6ae466a"},"isOneFree":0},{"id":1173640,"nickname":"lemon v","topic":"\u65b0\u4eba\u5f00\u64ad\uff0c\u7b49\u4f60\u6765\u64a9","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1880789,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/1173640_1527504881000_t5t4d9fupnry107p8fyo"},"isOneFree":1},{"id":232480,"nickname":"\u51b7\u7738","topic":"\u770b\u5fc3\u60c5","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1777106,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/232480_1508334374000_ok6rxh35vawjycjwjpwy"},"isOneFree":0},{"id":150927,"nickname":"CC","topic":"\u5065\u8eab \u5a31\u4e50 \u60c5\u611f \u7f8e\u98df","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1830004,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/150927_1517066086000_n25crjlu6lvxsa62mzft"},"isOneFree":0},{"id":60705,"nickname":"\u5a49\u745c","topic":"\u542c\u8bdd\u60f9\u4eba\u559c\u6b22\u7684\u5c0f\u840c\u59b9","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1778128,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/60705_1508490412000_jndjcez628e54au98rfw"},"isOneFree":0},{"id":719915,"nickname":"\u53ee\u53ee","topic":"\u4e0d\u5b9a\u65f6\u5728\u7ebf \u6709\u65f6\u63a5\u4e0d\u5230\u53ef\u9884\u7ea6\u5594","vcoinPerMinute":8,"level":"3","online":"0","avatar":{"id":1843250,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/719915_1520605211000_j2qpqhqie8wgu9x67aem"},"isOneFree":0},{"id":274448,"nickname":"Beata\u8431\u5bb8","topic":"\u4f60\u613f\u7231 \u6211\u613f\u7231","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1849719,"width":1000,"height":1000,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/KnQWJKF2th.jpg"},"isOneFree":0},{"id":330128,"nickname":"\u7f8e\u9633\u9633","topic":"\u5168\u7f51\u7b2c\u4e00\u6e29\u67d4\u7684\u7f8e\u5c11\u5973","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1797900,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/330128_1511865716000_n4y5n3qh2lzluexmaev1"},"isOneFree":0},{"id":69964,"nickname":"\u738b\u6069\u6069","topic":"\u968f\u610f","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":476464,"width":600,"height":600,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/ByeGp7mT6S.jpg"},"isOneFree":0},{"id":544456,"nickname":"\u7ef5\u7ef5","topic":"\u613f\u6211\u4e00\u751f\u52aa\u529b \u4e00\u76f4\u62e5\u6709\u7231\u7684\u80fd\u529b","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1826837,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/544456_1516538492000_nvun2h0q3y6gs18b9dnc"},"isOneFree":0},{"id":329625,"nickname":"\u5c0f\u5341\u4e03","topic":"\u5148\u8c0b\u751f \u518d\u8c0b\u7231","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1823861,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/329625_1516056790000_692pelr9rj54fzip91eb"},"isOneFree":0},{"id":101259,"nickname":"\u8bb8\u6587\u5a77Regina","topic":"????\u9001\u4f60\u4e00\u6735\u5c0f\u82b1\u82b1","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1798471,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/101259_1511946759000_bo3f2bjuy2lcjkz9ctnv"},"isOneFree":0},{"id":129624,"nickname":"\u55b5","topic":"\u767e\u53d8\u5973\u738b\u3002\u603b\u6709\u4f60\u559c\u6b22\u7684\u4e00\u6b3e","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1353823,"width":720,"height":755,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/129624_1505544780000_gkpa0dpqumnfbttc3ub5"},"isOneFree":0},{"id":146985,"nickname":"\u8fc7\u671f\u7c73\u7ebf\u7ebf\u55b5","topic":"\u968f\u610f","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":1512289,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/146985_1505896695000_1nukxw1k9kybydlcu165"},"isOneFree":0},{"id":59660,"nickname":"\u8f9b\u59ae","topic":"\u5f00\u5fc3\u5c31\u597d","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":316025,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/59660_1502018123000_lru3sl64bozqxm83yubz"},"isOneFree":0},{"id":102540,"nickname":"\u5468\u4f73\u6c5d","topic":"\u968f\u610f","vcoinPerMinute":8,"level":"3","online":0,"avatar":{"id":959739,"width":720,"height":720,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/102540_1504255376000_xfq4gyrdk6b1wdjquo9a"},"isOneFree":0},{"id":1325434,"nickname":"\u6c49\u5e93\u514b","topic":"\u7eff\u64ad \u62d2\u52a0v\u3002\u6709\u9700\u6c42\u7684\u52ff\u6270","vcoinPerMinute":8,"level":"3","online":"4","avatar":{"id":1905453,"width":750,"height":750,"sn":0,"url":"http:\/\/cdn.vliao1.xyz\/prod\/image\/ka5b7Wx5dE.jpg"},"isOneFree":0}]}
	 */
    private PageVResult spider(VRobotInfo robot, LoginVResult login, PageVQuery query){
        String url = "http://sp40xiaomi.vliao12.com/v31/homepage";
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("InterfaceSystemVersion", robot.getInterfacesystemversion());
        headerMap.put("InterfaceSystemType", robot.getInterfacesystemtype());
        headerMap.put("interfaceVersion", robot.getInterfaceversion());
        headerMap.put("interfaceSource", robot.getInterfacesource());
        headerMap.put("InterfaceChannel", robot.getInterfaceChannel());
        headerMap.put("InterfaceVersionCode", robot.getInterfaceVersionCode());
        headerMap.put("AppName", "vchat");
        headerMap.put("User-Agent", "okhttp/3.8.0");
        headerMap.put("Host", "sp40xiaomi.vliao12.com");
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Accept-Encoding", "gzip");
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("userId", login.getUserId()+"");
        parameterMap.put("userKey", login.getUserKey());
        //设置抓取的页数和列表项
        parameterMap.put("tagId", query.getTagId()+"");
        parameterMap.put("page", query.getPage()+"");
        ResponseStatus response;
        PageVResult result = new PageVResult();
        try {
            response = HttpUtils.post(url, parameterMap, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
            if(Tools.isNotNull(response)){
                //这个地方需要解析列表数据 然后进入对应的个人主页获取对应的用户数据
                LOGGER.error(response.getContent());
                JSONObject json = JsonHelper.toJsonObject(response.getContent());
                if (Tools.isNotNull(json)){
                    LOGGER.error(json.toJSONString());
                    //解析对应的json数据
                    boolean code = json.getBooleanValue("result");
                    int maxPage = json.getIntValue("maxPage");
                    int currPage = json.getIntValue("currPage");
                    JSONArray datas = json.getJSONArray("data");
                    String data = "";
                    if(Tools.isNotNull(datas))
                        data = datas.toJSONString();
                    result.setResult(code);
                    result.setMaxPage(maxPage);
                    result.setCurrPage(currPage);
                    result.setData(data);
                }
            }
            return result;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return result;
        }
    }
    
    /**
     * 拉取个人主页基础数据
     */
    /*
 		POST http://sp42xiaomi.vliao12.com/v31/bigv/detail-base HTTP/1.1
		InterfaceVersionCode: 44
		InterfaceSystemVersion: 8.0.0
		InterfaceVersion: 4.2
		InterfaceSystemType: Xiaomi MI 6 8.0.0
		AppTime: Tue Nov 06 14:33:32 GMT+08:00 2018
		InterfaceChannel: xiaomi
		InterfaceSource: android
		AppName: vchat
		Content-Type: application/x-www-form-urlencoded
		Content-Length: 67
		Host: sp42xiaomi.vliao12.com
		Connection: Keep-Alive
		Accept-Encoding: gzip
		User-Agent: okhttp/3.8.0

		userId=1491574&userKey=895ade40d2a46e500b8b5a6d801c80dd&vid=1338229
     */
    private int peopleBase(VRobotInfo robot, LoginVResult login, long id, String nickname){
    	String url = "http://sp42xiaomi.vliao12.com/v31/bigv/detail-base";
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("InterfaceSystemVersion", robot.getInterfacesystemversion());
        headerMap.put("InterfaceSystemType", robot.getInterfacesystemtype());
        headerMap.put("interfaceVersion", robot.getInterfaceversion());
        headerMap.put("interfaceSource", robot.getInterfacesource());
        headerMap.put("InterfaceChannel", robot.getInterfaceChannel());
        headerMap.put("InterfaceVersionCode", robot.getInterfaceVersionCode());
        headerMap.put("AppName", "vchat");
        headerMap.put("User-Agent", "okhttp/3.8.0");
        headerMap.put("Host", "sp40xiaomi.vliao12.com");
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Accept-Encoding", "gzip");
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("userId", login.getUserId()+"");
        parameterMap.put("userKey", login.getUserKey());
        //设置抓取的页数和列表项
        parameterMap.put("vid", id+"");
        ResponseStatus response;
        try {
            response = HttpUtils.post(url, parameterMap, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
            if(Tools.isNotNull(response)){
                //这个地方需要解析列表数据 然后进入对应的个人主页获取对应的用户数据
                LOGGER.error(response.getContent());
                JSONObject json = JsonHelper.toJsonObject(response.getContent());
                if (Tools.isNotNull(json)){
                    LOGGER.error(json.toJSONString());
                    AVEntity t = new AVEntity();
                    t.setUserid(id);
                    t.setNickname(nickname);
                    t.setType(1);
                    t.setData(json.toJSONString());
					aVContract.insert(t);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    	return 0;
    }
    
    /**
     * 拉取个人主页基础数据
     */
    /*
     * POST http://sp42xiaomi.vliao12.com/v31/bigv/detail-data HTTP/1.1
		InterfaceVersionCode: 44
		InterfaceSystemVersion: 8.0.0
		InterfaceVersion: 4.2
		InterfaceSystemType: Xiaomi MI 6 8.0.0
		AppTime: Tue Nov 06 14:33:32 GMT+08:00 2018
		InterfaceChannel: xiaomi
		InterfaceSource: android
		AppName: vchat
		Content-Type: application/x-www-form-urlencoded
		Content-Length: 67
		Host: sp42xiaomi.vliao12.com
		Connection: Keep-Alive
		Accept-Encoding: gzip
		User-Agent: okhttp/3.8.0

		userId=1491574&userKey=895ade40d2a46e500b8b5a6d801c80dd&vid=1338229
     */
    private int peopleData(VRobotInfo robot, LoginVResult login, long id, String nickname){
    	String url = "http://sp42xiaomi.vliao12.com/v31/bigv/detail-data";
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("InterfaceSystemVersion", robot.getInterfacesystemversion());
        headerMap.put("InterfaceSystemType", robot.getInterfacesystemtype());
        headerMap.put("interfaceVersion", robot.getInterfaceversion());
        headerMap.put("interfaceSource", robot.getInterfacesource());
        headerMap.put("InterfaceChannel", robot.getInterfaceChannel());
        headerMap.put("InterfaceVersionCode", robot.getInterfaceVersionCode());
        headerMap.put("AppName", "vchat");
        headerMap.put("User-Agent", "okhttp/3.8.0");
        headerMap.put("Host", "sp40xiaomi.vliao12.com");
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Accept-Encoding", "gzip");
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("userId", login.getUserId()+"");
        parameterMap.put("userKey", login.getUserKey());
        //设置抓取的页数和列表项
        parameterMap.put("vid", id+"");
        ResponseStatus response;
        try {
            response = HttpUtils.post(url, parameterMap, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
            if(Tools.isNotNull(response)){
                //这个地方需要解析列表数据 然后进入对应的个人主页获取对应的用户数据
                LOGGER.error(response.getContent());
                JSONObject json = JsonHelper.toJsonObject(response.getContent());
                if (Tools.isNotNull(json)){
                    LOGGER.error(json.toJSONString());
                    AVEntity t = new AVEntity();
                    t.setUserid(id);
                    t.setNickname(nickname);
                    t.setType(2);
                    t.setData(json.toJSONString());
					aVContract.insert(t);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    	return 0;
    }
    
    /**
     * 拉取个人主页基础数据
     */
    /*
     * POST http://sp42xiaomi.vliao12.com/v32/smallvideo/bigv-list HTTP/1.1
		InterfaceVersionCode: 44
		InterfaceSystemVersion: 8.0.0
		InterfaceVersion: 4.2
		InterfaceSystemType: Xiaomi MI 6 8.0.0
		AppTime: Tue Nov 06 14:33:32 GMT+08:00 2018
		InterfaceChannel: xiaomi
		InterfaceSource: android
		AppName: vchat
		Content-Type: application/x-www-form-urlencoded
		Content-Length: 74
		Host: sp42xiaomi.vliao12.com
		Connection: Keep-Alive
		Accept-Encoding: gzip
		User-Agent: okhttp/3.8.0

		userId=1491574&userKey=895ade40d2a46e500b8b5a6d801c80dd&page=1&vid=1338229
     */
    private int peopleVideo(VRobotInfo robot, LoginVResult login, long id, String nickname){
    	String url = "http://sp42xiaomi.vliao12.com/v32/smallvideo/bigv-list";
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("InterfaceSystemVersion", robot.getInterfacesystemversion());
        headerMap.put("InterfaceSystemType", robot.getInterfacesystemtype());
        headerMap.put("interfaceVersion", robot.getInterfaceversion());
        headerMap.put("interfaceSource", robot.getInterfacesource());
        headerMap.put("InterfaceChannel", robot.getInterfaceChannel());
        headerMap.put("InterfaceVersionCode", robot.getInterfaceVersionCode());
        headerMap.put("AppName", "vchat");
        headerMap.put("User-Agent", "okhttp/3.8.0");
        headerMap.put("Host", "sp40xiaomi.vliao12.com");
        headerMap.put("Connection", "Keep-Alive");
        headerMap.put("Accept-Encoding", "gzip");
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("userId", login.getUserId()+"");
        parameterMap.put("userKey", login.getUserKey());
        //设置抓取的页数和列表项
        parameterMap.put("vid", id+"");
        parameterMap.put("page", 1+"");
        ResponseStatus response;
        try {
            response = HttpUtils.post(url, parameterMap, ECharset.UTF_8, EContentType.APPLICATION_FORM_URLENCODED, headerMap, null);
            if(Tools.isNotNull(response)){
                //这个地方需要解析列表数据 然后进入对应的个人主页获取对应的用户数据
                LOGGER.error(response.getContent());
                JSONObject json = JsonHelper.toJsonObject(response.getContent());
                if (Tools.isNotNull(json)){
                    LOGGER.error(json.toJSONString());
                    AVEntity t = new AVEntity();
                    t.setUserid(id);
                    t.setNickname(nickname);
                    t.setType(3);
                    t.setData(json.toJSONString());
					aVContract.insert(t);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    	return 0;
    }
    
    public int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }
    
    public class VRobotInfo implements Serializable {
    	
    	/**
    	 * 
    	 */
    	private static final long serialVersionUID = 1L;
    	
    	/**
    	 * V聊平台的用户id值
    	 */
    	private Long userid;
    	
    	/**
    	 * 注册的手机号码
    	 */
    	private String phonenumber;
    	
    	/**
    	 * 机器人用户对应的密码
    	 */
    	private String password;
    	
    	/**
    	 * 手机类型
    	 */
    	private String phonebrand;
    	
    	/**
    	 * 手机操作系统
    	 */
    	private String phoneos;
    	
    	/**
    	 * 手机型号
    	 */
    	private String phonemodel;
    	
    	/**
    	 * 下载来源
    	 */
    	private String appmarket;
    	
    	/**
    	 * app版本
    	 */
    	private String appversion;
    	
    	/**
    	 * 服务器下发的秘钥
    	 */
    	private String userkey;
    	
    	/**
    	 * 请求头中的系统版本
    	 */
    	private String interfacesystemversion;
    	
    	/**
    	 * 请求头中的系统类型
    	 */
    	private String interfacesystemtype;
    	
    	/**
    	 * 请求头中的app版本
    	 */
    	private String interfaceversion;
    	
    	/**
    	 * 请求头中的手机操作系统类型
    	 */
    	private String interfacesource;
    	
    	private String interfaceChannel;
    	
    	private String interfaceVersionCode;
    	
    	public Long getUserid() {
    		return userid;
    	}

    	public void setUserid(Long userid) {
    		this.userid = userid;
    	}
    	
    	public String getPhonenumber() {
    		return phonenumber;
    	}

    	public void setPhonenumber(String phonenumber) {
    		this.phonenumber = phonenumber;
    	}
    	
    	public String getPassword() {
    		return password;
    	}

    	public void setPassword(String password) {
    		this.password = password;
    	}
    	
    	public String getPhonebrand() {
    		return phonebrand;
    	}

    	public void setPhonebrand(String phonebrand) {
    		this.phonebrand = phonebrand;
    	}
    	
    	public String getPhoneos() {
    		return phoneos;
    	}

    	public void setPhoneos(String phoneos) {
    		this.phoneos = phoneos;
    	}
    	
    	public String getPhonemodel() {
    		return phonemodel;
    	}

    	public void setPhonemodel(String phonemodel) {
    		this.phonemodel = phonemodel;
    	}
    	
    	public String getAppmarket() {
    		return appmarket;
    	}

    	public void setAppmarket(String appmarket) {
    		this.appmarket = appmarket;
    	}
    	
    	public String getAppversion() {
    		return appversion;
    	}

    	public void setAppversion(String appversion) {
    		this.appversion = appversion;
    	}
    	
    	public String getUserkey() {
    		return userkey;
    	}

    	public void setUserkey(String userkey) {
    		this.userkey = userkey;
    	}
    	
    	public String getInterfacesystemversion() {
    		return interfacesystemversion;
    	}

    	public void setInterfacesystemversion(String interfacesystemversion) {
    		this.interfacesystemversion = interfacesystemversion;
    	}
    	
    	public String getInterfacesystemtype() {
    		return interfacesystemtype;
    	}

    	public void setInterfacesystemtype(String interfacesystemtype) {
    		this.interfacesystemtype = interfacesystemtype;
    	}
    	
    	public String getInterfaceversion() {
    		return interfaceversion;
    	}

    	public void setInterfaceversion(String interfaceversion) {
    		this.interfaceversion = interfaceversion;
    	}
    	
    	public String getInterfacesource() {
    		return interfacesource;
    	}

    	public void setInterfacesource(String interfacesource) {
    		this.interfacesource = interfacesource;
    	}

		public String getInterfaceChannel() {
			return interfaceChannel;
		}

		public void setInterfaceChannel(String interfaceChannel) {
			this.interfaceChannel = interfaceChannel;
		}

		public String getInterfaceVersionCode() {
			return interfaceVersionCode;
		}

		public void setInterfaceVersionCode(String interfaceVersionCode) {
			this.interfaceVersionCode = interfaceVersionCode;
		}
    	
    }
    
    public class LoginVResult {

    	private boolean result;
    	
    	private long userId;
    	
    	private String userKey;
    	
    	private String token;

    	//用于标示对应的代理是否出现了错误 1标示代理出错了
    	private int error;

    	public boolean isResult() {
    		return result;
    	}

    	public void setResult(boolean result) {
    		this.result = result;
    	}

    	public long getUserId() {
    		return userId;
    	}

    	public void setUserId(long userId) {
    		this.userId = userId;
    	}

    	public String getUserKey() {
    		return userKey;
    	}

    	public void setUserKey(String userKey) {
    		this.userKey = userKey;
    	}

    	public String getToken() {
    		return token;
    	}

    	public void setToken(String token) {
    		this.token = token;
    	}

    	public int getError() {
    		return error;
    	}

    	public void setError(int error) {
    		this.error = error;
    	}
    }
    
    public class PageVQuery {

    	private int tagId;
    	
    	private int page;

    	public int getTagId() {
    		return tagId;
    	}

    	public void setTagId(int tagId) {
    		this.tagId = tagId;
    	}

    	public int getPage() {
    		return page;
    	}

    	public void setPage(int page) {
    		this.page = page;
    	}
    	
    }
    
    public class PageVResult {

    	private boolean result;
    	
    	private int maxPage;
    	
    	private int currPage;
    	
    	private String data;

    	public boolean isResult() {
    		return result;
    	}

    	public void setResult(boolean result) {
    		this.result = result;
    	}

    	public int getMaxPage() {
    		return maxPage;
    	}

    	public void setMaxPage(int maxPage) {
    		this.maxPage = maxPage;
    	}

    	public int getCurrPage() {
    		return currPage;
    	}

    	public void setCurrPage(int currPage) {
    		this.currPage = currPage;
    	}

    	public String getData() {
    		return data;
    	}

    	public void setData(String data) {
    		this.data = data;
    	}
    	
    }
    
}
