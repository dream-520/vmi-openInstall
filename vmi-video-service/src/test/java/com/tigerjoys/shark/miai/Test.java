package com.tigerjoys.shark.miai;

import java.util.Calendar;
import java.util.List;

import org.shark.miai.common.enums.PlatformEnum;
import org.shark.miai.common.util.AESFieldUtils;

import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.context.RequestHeader;

public class Test {
	
	static class Good {
		private int price;
		private String name;
		
		public Good() {
			
		}
		public Good(String name , int price) {
			this.name = name;
			this.price = price;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	protected static long getTotalSalesAmount(List<Good> goodsList) {
		//return goodsList.stream().mapToInt(v -> v.getPrice()).sum();
		//return goodsList.stream().collect(Collectors.summarizingInt(Good::getPrice)).getSum();
		//return goodsList.stream().mapToInt(Good::getPrice).reduce(0, Integer::sum);
		//return goodsList.stream().reduce(0 , (price , good) -> price + good.getPrice(),(l,r) -> l+r);
		return goodsList.stream().map(Good::getPrice).reduce((p1 , p2) -> p1 + p2).get().intValue();
	}

	public static void main(String[] args) throws Exception {
		//Calendar calendar = Calendar.getInstance();
		//calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY) - 72);
		//System.err.println(calendar.getTime());
		
		String body = AESCipher.aesDecryptString("jd8cblf1nIjppUa9+v5/diceSoSL0f3x3L/1mMLbn5B+QpKBh4/se/B7lRqYlGO7pnSlnPLt8xiTeSGjAn2Q+yt3Evx1sIFUFnjSrETajpgGZ36rGbE747CTKDbzywCrGhARIbXFgqFzCq2gWbRM7o4E9Z5XakFhS83kffyIbkrlB/kiY6GGPt7n8VAjPAf1pZg2fHR/sn7GwvrrrgsR6e4yyzTfBO3sxrqxtjkXGOHpEiR/y1GRIaDZWJUe6gqkGmWfWh4XP6HnDXNWo/8bam5x7kUQHg2xWIH5TlyQ/RTqfNXdmKdJ8M+6rmk/rtkYW9UvZ5WLNC+uxAbmW9IpUI5j/Hi7Wxd8/gLzi8IMNjS6IqqGNEpnyl3WaXY0i9CGr4uyI9fYnGSMtW4Y/5vOwg==");
		RequestHeader header = JsonHelper.toObject(body, RequestHeader.class);
		header.setOs_type(PlatformEnum.H5.type);
		System.err.println(AESCipher.aesEncryptString(JsonHelper.toJson(header)));
		
		//System.err.println(AESFieldUtils.encrypt("18761033254"));
		//ResponseStatus response = HttpUtils.get("https://api.bst.meitu.com/api/outpush");
		//System.err.println(response.getContent());
		//System.err.println(Tools.getFloat(1.0f * 1/2, 1));
		/*int totalPrice = 0;
		List<Good> goodsList = new ArrayList<>();
		for(int i=0;i<100;i++) {
			int price = Helper.RANDOM.nextInt(100) + 1;
			totalPrice += price;
			
			goodsList.add(new Good("商品编号" + i , price));
		}
		
		System.err.println("总价格：" + totalPrice + " ,计算出来的价格：" + getTotalSalesAmount(goodsList));*/
		
		//String[] words = {"a" , "bb" , "ccc" , "ddd" , "eeee"};
		//Arrays.stream(words).filter(c -> {System.out.println(c);return true;}).limit(1).collect(Collectors.toList());
		
		//System.err.println(AESCipher.aesEncryptString("{\"type\":1}"));

		//System.err.println(AESCipher.aesEncryptString("{\"category\":6,\"anchorId\":65418701876231936,\"channel\":1,\"income\":0,\"money\":1,\"priceId\":4}"));
		//System.err.println(AESCipher.aesDecryptString("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"));
		//System.err.println(Tools.getDateTime(1508922000000L));
		
		//System.err.println(23578400943833344L >>> 21);
		//System.err.println(Long.MAX_VALUE);
		//System.err.println(Tools.getDateTime(1508083200000L));
		
		//System.err.println(Tools.getAge(Tools.getDate("1990-09-22")));
		//System.err.println(Tools.getAge(Tools.getDate("1990-09-22")));
		//System.err.println(System.currentTimeMillis());
		
		//String ss = "{\"orderId\":34565173916270848}";
		//System.err.println(AESCipher.aesEncryptString(ss));
		//System.err.println(AESCipher.aesDecryptString(AESCipher.aesEncryptString(ss)));
		
		//Date sss = Tools.getDateTime("2017-06-09 10:41:07");
		//Date bbb = Tools.getDateTime("2017-06-09 10:41:06");
		//System.err.println(sss.getTime() - bbb.getTime());
		//ResponseStatus s = HttpUtils.get("http://www.milier.com");
		//System.err.println(s.getContent());
		
		//ResponseStatus response = HttpUtils.get("http://api.t.sina.com.cn/short_url/shorten.json?source=3821771181&url_long="+URLEncoder.encode("http://a.app.qq.com/o/simple.jsp?pkgname=lhzy.com.bluebee", ECharset.UTF_8.getName()));
		//System.err.println(response.getContent());
		
		/*Thread cc = new Thread(() -> {
			while(!stop) {
				System.out.println("aaaaaaa");
			}
		});
		cc.start();
		//cc.join();
		
		Thread.sleep(2000L);
		System.out.println(stop);
		stop = true;
		System.out.println(stop);
		
		Thread.sleep(5000L);
		
		System.out.println("++++");*/
		
		/*String s = "r0_Cmd=EposLinkSale\nr1_Code=1\nerrorMsg=\nr2_TrxId=\nr3_Amt=0.1\nr4_Cur=RMB\nr_SaleLink=https://www.yeepay.com/app-merchant-proxy/eposMobile.action?l=c619918cd727e3bb02783940c025d4e5";
		
		System.err.println(s);
		System.err.println("=============");
		String[] ss = s.split("\n");
		for(String v : ss) {
			System.err.println(v);
		}*/
		/*String s = ",";
		int i=40;
		for(int j=1;j<=i;j++) {
			s += j+",";
		}
		
		System.err.println(s);
		System.err.println(s.length());*/
		/*String ss = "1,2,3,4";
		StringTokenizer token = new StringTokenizer(ss , ",");
		while(token.hasMoreElements()) {
			System.err.println(token.nextElement());
		}*/
		
		//System.err.println(Tools.getDateTime(new Date()).substring(5 , 16));

		
		//System.err.println(ShieldWordUtils.getSensitiveWord("你妹的，滚，妈逼的，你妈的蛋", ShieldWordUtils.MAX_MATCH_TYPE));
		//System.err.println(String.format("阿萨德阿萨德%s", "ABC"));
		
		/*System.err.println(Tools.getDateTime(1508601600L*1000));
		
		AAA aa = new AAA();
		aa.setAa("我才你阿萨德阿萨德阿萨德你麻痹是煞笔吧");
		aa.setBb(System.currentTimeMillis()+Tools.DAY_MILLIS);
		aa.setCc(1508922000000L);
		
		ValidateResult<AAA> result = ValidateFactory.validate(aa);
		ValidateUtils.printError(result);*/
		//System.err.println(Arrays.asList("我啊,爱的,速度,ad,速度".split(",",1)));
		//System.err.println(Collections.emptyList());
		//System.err.println(Tools.getDateTime("2016-01-01 10:00:00"));
		/*System.err.println(AESFieldUtils.encrypt("A1020kskskskdskA_sssssfkfasl_2123"));
		System.err.println(AESFieldUtils.encrypt("阿萨德阿"));
		System.err.println(AESFieldUtils.decrypt("36ZvDaOCXenbgK/TkQuGFw=="));
		System.err.println(System.currentTimeMillis() + Tools.DAY_MILLIS*30);
		System.err.println(AESFieldUtils.encrypt("13812348888"));
		System.err.println(AESFieldUtils.encrypt("13812349999"));
		System.err.println(AESFieldUtils.encrypt("13812346666"));
		System.err.println(AESFieldUtils.encrypt("13812346234"));
		System.err.println(AESFieldUtils.encrypt("13812348888"));
		System.err.println(AESFieldUtils.encrypt("13812349999"));
		System.err.println(AESFieldUtils.encrypt("13812346666"));
		System.err.println(AESFieldUtils.encrypt("13812346234"));*/
		
		/*String pid ="2088521239958075";
		String app_id="2017081708235619";
		String target_id="kk15665"; 
		boolean rsa2 = false;
		LinkedHashMap<String, String> keyValues = new LinkedHashMap<String, String>();
		// 商户签约拿到的app_id，如：2013081700024223
		keyValues.put("app_id", app_id);
		// 商户签约拿到的pid，如：2088102123816631
		keyValues.put("pid", pid);
		// 服务接口名称， 固定值
		keyValues.put("apiname", "com.alipay.account.auth");
		// 商户类型标识， 固定值
		keyValues.put("app_name", "mc");
		// 业务类型， 固定值
		keyValues.put("biz_type", "openservice");
		// 产品码， 固定值
		keyValues.put("product_id", "APP_FAST_LOGIN");
		// 授权范围， 固定值
		keyValues.put("scope", "auth_user");

		// 商户唯一标识，如：kkkkk091125
		keyValues.put("target_id", target_id);

		// 授权类型， 固定值
		keyValues.put("auth_type", "AUTHACCOUNT");
		// 签名类型
		keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");
		
		
		
		keyValues = AlipayBuilder.buildRequestPara(keyValues);
		
		String code = AlipayHelper.createLinkString(keyValues);
		System.out.println(code);*/
	}

}
