package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;
import org.shark.miai.common.util.BaiduUtils;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;

public class BaiduApiTest {
	@Test
	public void testGetCityInfoByLatLng() throws Exception {
		String ak = "X3xHYXFPgFal1wXerNwgomxLHZLCSSwW";
		String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" 
	                +"39.934,116.329" 
					+ "&output=json&pois=1&ak=" + ak;
//		JSONObject json = HttpUtils.get(url).getJsonObjectContent();
//		int cityId = (int)json.get("cityCode");
//		System.out.println("cityId:" + cityId);
		
		String content = HttpUtils.get(url).getContent();
		String jsonContent = content.substring(content.indexOf("(")+1, content.lastIndexOf(")"));
		JSONObject json = JsonHelper.toJsonObject(jsonContent);
		String resultString = json.getString("result");
		System.out.println("jsonString of result:" + json.getString("result"));
		JSONObject resultJson = JsonHelper.toJsonObject(resultString);
		System.out.println("cityId:" + resultJson.getString("cityCode"));
		
	}
	
	@Test
	public void testGetCityInfoByLatLng2() throws Exception {
		double lat = 39.934D;
		double lng = 116.329D;
		int cityId = BaiduUtils.getCityIdByLatLng(lng, lat);
		System.out.println("cityId:" + cityId);
	}
}
