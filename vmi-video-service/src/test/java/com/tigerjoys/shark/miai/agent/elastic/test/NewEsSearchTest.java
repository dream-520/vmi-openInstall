package com.tigerjoys.shark.miai.agent.elastic.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.shark.miai.es.dto.EsMobileAppListReordDto;
import com.tigerjoys.shark.miai.es.dto.EsMobileDeviceReordDto;
import com.tigerjoys.shark.miai.es.dto.EsMobileUserAppListReordDto;
import com.tigerjoys.shark.miai.es.service.IEsMobileAppListRecordService;
import com.tigerjoys.shark.miai.es.service.IEsMobileDeviceRecordService;
import com.tigerjoys.shark.miai.es.service.IEsMobileUserAppRecordService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

public class NewEsSearchTest extends BaseTestConfig {
	
	@Autowired
	private IEsMobileAppListRecordService esMobileAppListRecordService;
	  
	@Autowired
	private IEsMobileUserAppRecordService esMobileUserAppRecordService;
	
	@Autowired
	private IEsMobileDeviceRecordService esMobileDeviceRecordService;
	
	/**
	 * 
	 * 测试用户安装APP列表存储到ES数据库中
	 * <pre>
   curl -X POST -H 'Content-Type: application/json' '192.168.20.17:9200/vmiapplist/mobileapplist/_search?pretty=true' -u elastic:Annal7-Astride -d '{
	    "query":{
	        "bool":{
	            "must":[
	                {
	                    "match":{
	                        "userId":96755292074737920
	                    }
	                }
	            ]
	        }
	    }
	}'
	 * 
	 * <pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void appListRecordSaveAllTest() throws Exception {
		long userId = 123456789L;
		int userType = 0;
		LocalDateTime currentLocal = LocalDateTime.now();
		
		List<String> appList = Lists.newArrayList("com.cto51.student","com.wuba","com.alibaba.aliyun","com.ikang.web","com.qiyi.video","com.baidu.BaiduMap","com.baidu.input_huawei","com.baidu.netdisk","com.zhonglan.polyapp","cn.com.bmac.nfc","com.huawei.KoBackup","tv.danmaku.bili","com.dangdang.buy2","com.sdu.didi.psnger","com.ct.client","com.ss.android.ugc.aweme","com.wm.dmall","com.huawei.phoneservice","com.foxit.mobile.pdf.lite","cn.gov.tax.its","com.codoon.gps","com.huawei.android.hsf","com.huawei.wallet","com.huawei.lives","com.huawei.himovie","com.huawei.hwid","com.huawei.appmarket","com.huawei.parentcontrol","com.ss.android.article.news","com.kingsoft","com.jingdong.app.mall","com.smile.gifmaker","com.huawei.fastapp","com.alpha.lagouapk","com.lolaage.tbulu.tools","com.android.browser","com.topgether.sixfoot","com.sankuai.meituan","com.tjhj.miliao","com.moji.mjweather","cc.quanben.novel","com.huawei.systemmanager","com.hicloud.android.clone","com.taobao.taobao","com.tmall.wireless","iwan.tencent.com","com.tencent.wemeet.app","com.tencent.qqlive","com.tencent.news","com.huawei.skytone","com.huawei.android.totemweather","com.MobileTicket","com.android.gallery3d","com.huawei.android.pushagent","com.tencent.tmgp.sgame","com.tencent.gamehelper.smoba","com.netease.cloudmusic","com.tencent.mm","com.tencent.weread","com.huawei.hidisk","com.xtc.watch","com.tencent.tgclub","com.android.mms","enfc.metro","com.android.mediacenter","com.unionpay.tsmservice","com.huawei.gameassistant","com.huawei.health","com.tencent.tgp","cmb.pb","com.eg.android.AlipayGphone","com.unnoo.quan","com.huawei.hitouch","com.huawei.scanner","com.huawei.search","com.huawei.hwdetectrepair","com.huawei.intelligent","com.greenpoint.android.mc10086.activity","com.huawei.android.thememanager","com.wuba.zhuanzhuan","com.hpbr.bosszhipin","net.csdn.csdnplus","com.wimetro.iafc","com.tencent.mobileqq","com.ydwx.yoyo");
		List<EsMobileAppListReordDto> dto = new ArrayList<>();
		for(String re:appList){
			dto.add(EsMobileAppListReordDto.preDto(userId,userType, re, currentLocal));
		}
		esMobileAppListRecordService.saveAll(dto);
		
		System.out.println("=====");
		Thread.sleep(10000L);
	}
	
	/**
	 * 测试所有用户的安装APP排行版
	 * @throws Exception
	 */
	@Test
	public void appListQueryTopAppTest() throws Exception {
		List<String> list = esMobileAppListRecordService.queryTopApp(0, 10);
		System.out.println(JsonHelper.toJson(list));
	}
	
	/**
	 * 保存用户的安装APP列表
	 * 
	 * <pre>
	 * curl -X POST -H 'Content-Type: application/json' '192.168.20.17:9200/userapptest/userapplist/_search?pretty=true' -u elastic:Annal7-Astride -d '{
		    "query":{
		        "bool":{
		            "must":[
		                {
		                    "match":{
		                        "userId":123456789
		                    }
		                }
		            ]
		        }
		    }
		}'
	 * <pre>
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void userAppListSaveAllTest() throws Exception {
		long userId = 123456789L;
		int userType = 0;
		LocalDateTime currentLocal = LocalDateTime.now();
		
		List<String> appList = Lists.newArrayList("com.cto51.student","com.wuba","com.alibaba.aliyun","com.ikang.web","com.qiyi.video","com.baidu.BaiduMap","com.baidu.input_huawei","com.baidu.netdisk","com.zhonglan.polyapp","cn.com.bmac.nfc","com.huawei.KoBackup","tv.danmaku.bili","com.dangdang.buy2","com.sdu.didi.psnger","com.ct.client","com.ss.android.ugc.aweme","com.wm.dmall","com.huawei.phoneservice","com.foxit.mobile.pdf.lite","cn.gov.tax.its","com.codoon.gps","com.huawei.android.hsf","com.huawei.wallet","com.huawei.lives","com.huawei.himovie","com.huawei.hwid","com.huawei.appmarket","com.huawei.parentcontrol","com.ss.android.article.news","com.kingsoft","com.jingdong.app.mall","com.smile.gifmaker","com.huawei.fastapp","com.alpha.lagouapk","com.lolaage.tbulu.tools","com.android.browser","com.topgether.sixfoot","com.sankuai.meituan","com.tjhj.miliao","com.moji.mjweather","cc.quanben.novel","com.huawei.systemmanager","com.hicloud.android.clone","com.taobao.taobao","com.tmall.wireless","iwan.tencent.com","com.tencent.wemeet.app","com.tencent.qqlive","com.tencent.news","com.huawei.skytone","com.huawei.android.totemweather","com.MobileTicket","com.android.gallery3d","com.huawei.android.pushagent","com.tencent.tmgp.sgame","com.tencent.gamehelper.smoba","com.netease.cloudmusic","com.tencent.mm","com.tencent.weread","com.huawei.hidisk","com.xtc.watch","com.tencent.tgclub","com.android.mms","enfc.metro","com.android.mediacenter","com.unionpay.tsmservice","com.huawei.gameassistant","com.huawei.health","com.tencent.tgp","cmb.pb","com.eg.android.AlipayGphone","com.unnoo.quan","com.huawei.hitouch","com.huawei.scanner","com.huawei.search","com.huawei.hwdetectrepair","com.huawei.intelligent","com.greenpoint.android.mc10086.activity","com.huawei.android.thememanager","com.wuba.zhuanzhuan","com.hpbr.bosszhipin","net.csdn.csdnplus","com.wimetro.iafc","com.tencent.mobileqq","com.ydwx.yoyo");
		List<String> curAppList = Lists.newArrayList("com.tencent.mobileqq,com.alibaba.aliyun");
		
		EsMobileUserAppListReordDto userAppListDto = EsMobileUserAppListReordDto.preDto(userId, userType, appList, curAppList, currentLocal);
		
		esMobileUserAppRecordService.saveEntity(userAppListDto);
		
		System.out.println("===完成===");
		Thread.sleep(10000L);
	}
	
	/**
	 * 查看用户最近安装的APP列表
	 * @throws Exception
	 */
	@Test
	public void queryLastLoginAppListTest() throws Exception {
		long userId = 123456789L;
		List<String> list = esMobileUserAppRecordService.queryLastLoginAppList(userId);
		System.out.println(JsonHelper.toJson(list));
	}
	
	/**
	 * 测试手机设备记录
	 * <pre>
	 * 
	 * curl -X POST -H 'Content-Type: application/json' '192.168.20.17:9200/userapptest/mobiletest/_search?pretty=true' -u elastic:Annal7-Astride -d '{
		    "query":{
		        "bool":{
		            "must":[
		                {
		                    "match":{
		                        "clientid":"abc123456789"
		                    }
		                }
		            ]
		        }
		    }
		}'
	 * 
	 * </pre>
	 * @throws Exception
	 */
	@Test
	public void mobileDeviceTest() throws Exception {
		String clientID = "abc123456789";
		Date current = new Date();
		
		List<EsMobileDeviceReordDto> list = Lists.newArrayList();
		String[] appList = new String[] {"com.tencent.androidqqmail","com.ydwx.yoyo","com.snda.wifilocating"};
		for(String appName : appList) {
			list.add(EsMobileDeviceReordDto.preDto(clientID, appName, current));
		}
		
		esMobileDeviceRecordService.saveAll(list);
	}
	
	/**
	 * 查询设备排行
	 * @throws Exception
	 */
	@Test
	public void queryTopAppTest() throws Exception {
		List<String> list = esMobileDeviceRecordService.queryTopApp(10);
		System.out.println(JsonHelper.toJson(list));
	}

}
