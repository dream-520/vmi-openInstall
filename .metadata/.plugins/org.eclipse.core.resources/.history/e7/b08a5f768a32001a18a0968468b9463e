package com.tigerjoys.shark.miai.agent.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.shark.miai.common.util.AESFieldUtils;

import com.tigerjoys.nbs.common.utils.FileUtil;
import com.tigerjoys.nbs.common.utils.JsonHelper;

public class MD5Test /*extends BaseTestConfig*/ {

	@Test
	public void test1MD5() {
		long currentTimeMillis = 1542007223478L;
		StringBuilder code = new StringBuilder();
		code.append("https://catchatapi.mliao.com.cn/zh-cn/Home/getHomeList".toLowerCase());
		code.append(currentTimeMillis);
		code.append("com.thai.vtalk");
		code.append("HANGZHOUTIANGECHATING");
		//截获app端加密的数据对应结果--------->   userkey: a0bb14ed4d32c740d3f62862b5345cb4 
		System.err.println("userkey"+MD5.md5(code.toString()));
	}
	
	@Test
	public void test2MD5() {
		long currentTimeMillis = 1542007268751L;
		StringBuilder code = new StringBuilder();
		code.append("https://catchatapi.mliao.com.cn/zh-cn/Home/getHomeList".toLowerCase());
		code.append(currentTimeMillis);
		code.append("com.thai.vtalk");
		code.append("HANGZHOUTIANGECHATING");
		//截获app端加密的数据对应结果--------->   userkey: b2863196f0e8f97d98e9fed957b38fcc 
		System.err.println("userkey"+MD5.md5(code.toString()));
	}
	
	
	@Test
	public void testString(){
		String body = "杨柘城sdd顶替123";
		body = body.replaceAll("[a-zA-Z0-9]", "");
		System.out.println(body);
	}
	
	
	@Test
	public void testPattern(){
		String msg = "{mRegistered=YES mTimeStampType=oem_ril mTimeStamp=102090575822043ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=16920331 mPci=51 mTac=4566 mEarfcn=39148 mBandwidth=2147483647 mMcc=460 mMnc=00 mAlphaLong=CMCC mAlphaShort=CMCC} CellSignalStrengthLte: ss=28 rsrp=-86 rsrq=-7 rssnr=2147483647 cqi=2147483647 ta=2147483647}"
						+"{mRegistered=YES mTimeStampType=oem_ril mTimeStamp=101383473212313ns mCellConnectionStatus=0 CellIdentityLte:{ mCi=25387314 mPci=192 mTac=6157 mEarfcn=1825 mBandwidth=2147483647 mMcc=460 mMnc=11 mAlphaLong=460 11 mAlphaShort=460 11} CellSignalStrengthLte: ss=20 rsrp=-107 rsrq=-15 rssnr=2147483647 cqi=2147483647 ta=2147483647}";
		//Pattern p=Pattern.compile("mMcc=\\d*"); 
		Pattern root=Pattern.compile("mRegistered=YES.*ta="); 
		Matcher rootMatcher=root.matcher(msg); 
		while(rootMatcher.find()){
			Pattern p=Pattern.compile("\\w*=[\\w-]*"); 
			Matcher m=p.matcher(rootMatcher.group()); 
			while(m.find()) { 
			     System.out.println("XXXXXX:"+m.group()); 
			} 
		}
		
	}
	
	
	
	@Test
	public void testPattern2() {
		String msg = "\"appList\":[\"com.android.chrome\",\"com.google.android.syncadapters.calendar\",\"com.iqoo.secure\",\"com.android.bbkmusic\",\"com.bbk.theme\",\"com.vivo.assistant\",\"com.kingroot.kinguser\",\"com.tencent.mobileqq\",\"com.vivo.sdkplugin\",\"com.vivo.space\",\"com.vivo.freewificheck\",\"com.vivo.wallet\",\"com.bbk.account\",\"com.ydwx.yoyo\",\"com.android.notes\",\"com.vivo.findphone\",\"com.chaozh.iReader\",\"com.vivo.email\",\"com.vivo.childrenmode\",\"com.vivo.appfilter\",\"com.cmcc.hebao\",\"com.vivo.easyshare\",\"com.vivo.hybrid\",\"com.vivo.browser\",\"com.screeclibinvoke\",\"com.cmcc.cmvideo\",\"cmccwm.mobilemusic\",\"com.ophone.reader.ui\",\"com.tjhj.miliao\",\"com.ydwx.yoyo3\",\"com.kmxs.reader\",\"com.vivo.Tips\",\"com.android.VideoPlayer\",\"com.taobao.taobao\",\"com.vivo.weather\",\"com.vivo.weather.provider\",\"com.vivo.pushservice\",\"com.wandoujia.phoenix2\",\"com.wandoujia.phoenix2.usbproxy\",\"com.bbk.updater\",\"com.android.mms\",\"com.android.bbk.lockscreen3\",\"com.bbk.iqoo.feedback\",\"com.tencent.android.qqdownloader\",\"com.bbk.appstore\",\"com.vivo.game\",\"com.vivo.magazine\",\"com.bbk.cloud\",\"com.eg.android.AlipayGphone\",\"com.vivo.abe\",\"com.vivo.hiboard\"]";
		//Pattern p=Pattern.compile("mMcc=\\d*"); 
		Pattern root=Pattern.compile("\"appList\":\\[.*?\\]"); 
		Matcher rootMatcher=root.matcher(msg); 
		System.out.println("--------------");
		while(rootMatcher.find()){
			System.out.println(rootMatcher.group());
		}
		try {
			System.out.println(AESFieldUtils.encrypt("18711315513"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("mobile:"+AESFieldUtils.decrypt("D9w6zg2uGxeLqsyfKwQZNA=="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testCal() {
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DAY_OF_YEAR, 2);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		cal.add(Calendar.HOUR_OF_DAY, -15);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		System.out.println("hour:"+hour);
		
		System.out.println();
		
	}
	
	
	
	@Test
	public void testJson() {
		Map<String,Object> hsmp = new HashMap<>();
		hsmp.put("aaa", new Date());
		System.out.println(JsonHelper.toJson(hsmp));
		
	}
	@Test
	public void testDate() {
		LocalDate localDate1 = LocalDate.now();
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DAY_OF_YEAR, -1);
		Instant instant = cal.getTime().toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate2 = localDateTime.toLocalDate();
		long days = localDate1.toEpochDay() - localDate2.toEpochDay();
		System.out.println(days);
		Random random = new Random();
		int ss = random.nextInt(1);
		System.out.println(ss);
	}
	
	@Test
	public void testLong() {
		System.out.println(500/100);
		
		
		System.out.println("sdfd_50".endsWith("_"+(500/10)));
		
		
	}
	/**
	 * 读文件
	 */
	@Test
	public void testReader() throws Exception{
		String ssString = FileUtil.getContentByLines("C:/Users/yangjunming.TIGERJOYS/Desktop/aaaa.sql");
		System.out.println(ssString);
		
	}
	
	
}
