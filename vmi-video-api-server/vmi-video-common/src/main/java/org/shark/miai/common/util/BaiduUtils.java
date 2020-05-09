package org.shark.miai.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.shark.miai.common.constant.CommonConst;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.enums.ECharset;
import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.nbs.common.http.ResponseStatus;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 百度地图工具类
 *
 */
public class BaiduUtils {
	
	private static final Log logger = LogFactory.getLog(BaiduUtils.class);
	
	/**
	 * 根据地址获得经纬度
	 * @param address - 查询地址
	 * @return BmapPoint
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static BmapPoint getByAddress(String address) throws HttpException, IOException {
		return getByAddress(address , null);
	}
	
	/**
	 * 根据地址获得经纬度
	 * @param address - 查询地址
	 * @param city - 查询城市,选填
	 * @return BmapPoint
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static BmapPoint getByAddress(String address , String city) throws HttpException, IOException {
		String url = "http://api.map.baidu.com/cloudgc/v1?ak="+CommonConst.baidu_map_ak+"&geotable_id=172408&address="+Tools.encoder(address, ECharset.UTF_8.getName())+(Tools.isNotNull(city)?"&city="+city:"");
		logger.info(url);
		
		for(int i=0;i<3;i++) {
			ResponseStatus response = HttpUtils.get(url);
			if(response.getStatusCode() != HttpStatus.SC_OK) {
				logger.info(response.getContent());
				continue;
			}
			String ret = response.getContent();
			if(ret != null && ret.length() > 0) {
				JSONObject json = JsonHelper.toJsonObject(ret);
				if(json.getIntValue("status") == 0) {
					if(json.containsKey("result")) {
						JSONArray result = json.getJSONArray("result");
						if(result.size() > 0) {
							JSONObject ojson = result.getJSONObject(0);
							if(ojson.containsKey("location")) {
								JSONObject lbs = ojson.getJSONObject("location");
								if(lbs != null && !lbs.isEmpty()) {
									return new BmapPoint(lbs.getDoubleValue("lng"),lbs.getDoubleValue("lat"));
								}
							}
						}
					}
				} else {
					logger.warn(json.getString("message"));
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 根据经纬度查询地址
	 * @param lng - 经度
	 * @param lat - 纬度
	 * @return BmapAddress
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static BmapAddress getByLocation(double lng , double lat) throws HttpException, IOException {
		String url = "http://api.map.baidu.com/cloudrgc/v1?ak="+CommonConst.baidu_map_ak+"&geotable_id=172408&location="+lat+","+lng+"&pois=0";
		logger.info(url);
		
		ResponseStatus response = HttpUtils.get(url);
		if(response.getStatusCode() != HttpStatus.SC_OK) {
			logger.info(response.getContent());
			return null;
		}
		
		String ret = response.getContent();
		if(ret != null && ret.length() > 0) {
			JSONObject json = JsonHelper.toJsonObject(ret);
			if(json.getIntValue("status") == 0) {
				BmapAddress add = new BmapAddress();
				add.setLng(lng);
				add.setLat(lat);
				if(json.containsKey("address_component")) {
					JSONObject address = json.getJSONObject("address_component");

					add.setCountry(address.getString("country"));
					add.setProvince(address.getString("province"));
					add.setCity(address.getString("city"));
					add.setDistrict(address.getString("district"));
					add.setStreet(address.getString("street"));
					add.setStreet_number(address.getString("street_number"));
					add.setAdmin_area_code(address.getString("admin_area_code"));
					add.setCountry_code(address.getString("country_code"));
				}

				add.setFormatted_address(json.getString("formatted_address"));
				add.setRecommended_location_description("recommended_location_description");
				
				return add;
			} else {
				logger.warn(json.getString("message"));
			}
		}
		
		return null;
	}
	
	/**
	 * 根据经纬度获得cityId
	 * @param lng 经度
	 * @param lat 纬度
	 * @throws IOException 
	 * @throws HttpException 
	 * @throws UnsupportedEncodingException 
	 */
	public static int getCityIdByLatLng(double lng , double lat) throws UnsupportedEncodingException, HttpException, IOException {
		String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" 
                + lat + "," +  lng
				+ "&output=json&pois=1&ak=" + CommonConst.ak;
		String content = HttpUtils.get(url).getContent();
		String jsonContent = content.substring(content.indexOf("(")+1, content.lastIndexOf(")"));
		JSONObject json = JsonHelper.toJsonObject(jsonContent);
		String resultString = json.getString("result");
		JSONObject resultJson = JsonHelper.toJsonObject(resultString);
		return resultJson.getIntValue("cityCode");
	}
	
	public static void main(String[] args) throws HttpException, IOException {
		BmapPoint point = getByAddress("北京" , "北京");
		System.err.println(point);
		//BmapAddress address = getByLocation(116.21645635689414d, 40.2217235498323d);
		//System.err.println(address);
	}

}
