package com.tigerjoys.shark.miai.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeConfigDto;
import com.tigerjoys.shark.miai.agent.dto.FirstChargeRedFlowerConfigDto;
import com.tigerjoys.shark.miai.agent.dto.PayPrepayResultDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.inter.entity.EnergyCustomPriceEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargeCustomPriceEntity;
import com.tigerjoys.shark.miai.inter.entity.RechargePriceEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerPriceEntity;
import com.tigerjoys.shark.pay.client.dto.PayCenterWeixinJSResultDto;

/**
 * 服务工具类
 * @author chengang
 *
 */
public final class ServiceHelper {
	
	/**
	 * 通用的100x100图片
	 */
	public static final String COMMON_100_TAG = "com100";
	
	/**
	 * 通用的150x150图片
	 */
	public static final String COMMON_150_TAG = "com150";
	
	/**
	 * 通用的200x200图片
	 */
	public static final String COMMON_200_TAG = "com200";
	
	/**
	 * 通用的300x300图片
	 */
	public static final String COMMON_300_TAG = "com300";
	
	/**
	 * 获得用户的150x150小头像
	 * @param relativePhoto - 用户头像
	 * @return String
	 */
	public static String getUserSmallPhoto(String relativePhoto) {
		if(relativePhoto == null || relativePhoto.length() == 0) {
			relativePhoto = Const.DEFAULT_USER_ICON;
		}
		
		return getPhoto(Const.HTTP_PIC_URL, relativePhoto, COMMON_100_TAG);
	}
	
	/**
	 * 获得用户的300x300小头像
	 * @param relativePhoto - 用户头像
	 * @return String
	 */
	public static String getUserBigPhoto(String relativePhoto) {
		if(relativePhoto == null || relativePhoto.length() == 0) {
			relativePhoto = Const.DEFAULT_USER_ICON;
		}
		
		return getPhoto(Const.HTTP_PIC_URL, relativePhoto, COMMON_300_TAG);
	}
	
	/**
	 * 获得用户的原始头像
	 * @param photo - 用户头像
	 * @return String
	 */
	public static String getUserPhoto(String relativePhoto) {
		if(relativePhoto == null || relativePhoto.length() == 0) {
			relativePhoto = Const.DEFAULT_USER_ICON;
		}
		
		return getPhoto(Const.HTTP_PIC_URL, relativePhoto, null);
	}
	
	/**
	 * 获得CDN的图片地址
	 * @param relativePhoto - String
	 * @return String
	 */
	public static String getCdnPhoto(String relativePhoto) {
		return getPhoto(Const.HTTP_PIC_URL , relativePhoto , null);
	}
	
	/**
	 * 获得CDN的视频地址
	 * @param relativePhoto - String
	 * @param tag - 图片标签名称
	 * @return String
	 */
	public static String getCdnVideo(String relativePhoto , String tag) {
		return getPhoto(Const.HTTP_VIDEO_URL, relativePhoto, tag);
	}
	
	/**
	 * 获得语音消息的音频地址
	 * @param relativePhoto - String
	 * @param tag - 图片标签名称
	 * @return String
	 */
	public static String getVchatAudio(String relativePhoto ) {
		return getPhoto(Const.HTTP_VCHAT_AUDIO_URL, relativePhoto, null);
	}
	
	
	/**
	 * 获得IOS的Plist地址
	 * @param relativePhoto - String
	 * @return String
	 */
	public static String getIOSPlist(String relativePhoto) {
		return getPhoto(Const.PLIST_URL_HTTPS , relativePhoto , null);
	}
	
	
	/**
	 * 获得CDN的视频地址
	 * @param relativePhoto - String
	 * @return String
	 */
	public static String getCdnVideo(String relativePhoto) {
		return getPhoto(Const.HTTP_VIDEO_URL , relativePhoto , null);
	}
	
	/**
	 * 获得CDN的图片地址
	 * @param relativePhoto - String
	 * @param tag - 图片标签名称
	 * @return String
	 */
	public static String getCdnPhoto(String relativePhoto , String tag) {
		return getPhoto(Const.HTTP_PIC_URL, relativePhoto, tag);
	}
	
	/**
	 * 获得CDN图片地址
	 * @param website - 域名前缀
	 * @param relativePhoto - 相对路径
	 * @param tag - 标签
	 * @return String
	 */
	public static String getPhoto(String website , String relativePhoto , String tag){
		if(relativePhoto == null || relativePhoto.length() == 0) {
			return Tools.EMPTY_STRING;
		}
		
		if(relativePhoto.charAt(0) == 'h') {
			return relativePhoto;
		}
		
		//组装
		StringBuilder buf = new StringBuilder(128);
		buf.append(website).append(relativePhoto);
		if(tag != null && tag.trim().length() > 0) {
			if(Const.IS_TEST) {
				buf.append("?t=");
			} else {
				buf.append("!");
			}
			buf.append(tag);
		}
		
		return buf.toString();
	}
	
	/**
	 * 支付签名数据
	 * @param result - AgentResult
	 * @param channel - PayChannelEnum
	 * @return ActionResult
	 */
	public static ActionResult dealPayData(AgentResult result, PayChannelEnum channel) {
		if (AgentErrorCodeEnum.success.getCode() == result.getCode()) {
			PayPrepayResultDto prepayResult = (PayPrepayResultDto)result.getData();
			
			Map<String, Object> data = Maps.newHashMap();
			data.put("channel", channel.getCode());
			switch (channel) {
				case ali: // 支付宝
					data.put("signData", prepayResult.getData());
					return ActionResult.success(data);
				case wx: // 微信支付
					data.put("signData", prepayResult.getData());
					return ActionResult.success(data);
				case balance:
					data.put("signData", "(剩余" + Tools.formatDouble2PercentToString(Tools.parseLong(prepayResult.getData())) + "元)");
					return ActionResult.success(data);
				case iap: // 苹果支付
					return ActionResult.success();
				case wxH5: 
					//处理微信h5支付
					data.put("url", prepayResult.getData());
					data.put("orderId", String.valueOf(prepayResult.getPay_action_id()));
					
					return ActionResult.success(data);
				case aliH5: // 支付宝H5支付
					return ActionResult.success(prepayResult.getData());
				case wxJS: //微信JS支付
					return ActionResult.success(JsonHelper.toObject(prepayResult.getData(), PayCenterWeixinJSResultDto.class));
				default:
					return ActionResult.fail();
			}
		} else {
			return ActionResult.fail(result.getCode(), result.getCodemsg());
		}
	}
	
	/*
	 * 支付签名数据
	 * @param result
	 * @param channel
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public static ActionResult dealPayData(AgentResult result, PayChannelEnum channel) {
		if (AgentErrorCodeEnum.success.getCode() == result.getCode()) {
			Map<String, Object> data = new HashMap<>();
			data.put("channel", channel.getCode());
			switch (channel) {
			case ali: // 支付宝
				data.put("signData", String.valueOf(result.getData()));
				return ActionResult.success(data);
			case wx: // 微信支付
				Map<String, String> sd = (Map<String, String>) result.getData();
				sd.put("extension", sd.remove("package"));// 屏蔽package关键字
				data.put("signData", JsonHelper.toJson(sd));
				return ActionResult.success(data);
			case balance:
				data.put("signData", "(剩余" + Tools.formatDouble2PercentToString(Tools.parseLong(result.getData())) + "元)");
				return ActionResult.success(data);
			case iap: // 苹果支付
				return ActionResult.success();
			case wxH5: 
				//处理微信h5支付
				Map<String, String> data1 = new HashMap<String, String>();
				//检测调用微信H5支付信息是否正确
				if (result.getCode() == 0) {
					//拿到对应的回调地址来请求对应的微信信息
					Map<String, String> map = (Map<String, String>) result.getData();
					if(Tools.isNotNull(map)) {
						String url = map.get("mweb_url");
						String orderId = map.get("orderId");
						if(Tools.isNotNull(url)) {
							Map<String , String> headerMap1 = new HashMap<>();
					        headerMap1.put("Host", "wx.tenpay.com");
					        headerMap1.put("Connection", "Keep-Alive");
					        headerMap1.put("Referer", "http://bapi.yoyo.liaomeivideo.com/");
					        headerMap1.put("User-Agent", "Mozilla/5.0 (Linux; Android 8.0.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/62.0.3202.84 Mobile Safari/537.36");
					        ResponseStatus response;
							try {
								response = HttpUtils.get(url, ECharset.UTF_8, EContentType.APPLICATION_JSON, headerMap1, null);
						        if(Tools.isNotNull(response)){
						        	//System.err.println(response.getContent());
						            String content = response.getContent();
						            if(Tools.isNotNull(content)) {
						            	String rgex = "weixin://wap/pay.{0,}";
						            	Pattern pattern = Pattern.compile(rgex);
						            	Matcher m = pattern.matcher(content);
						            	while(m.find()){  
						                    String web = m.group();
						                    web = web.replace(";", "");
						                    web = web.replace("\"", "");
						                    //System.err.println(web);
						                    data1.put("url", web);
						                    data1.put("orderId", orderId);
						                    break;
						                }  
						            }
						        }
							} catch (HttpException | IOException e) {

							}
						}
					}
				} 
				if(Tools.isNotNull(data1) && data1.size() > 0) {
					return ActionResult.success(data1);
				} else {
					return ActionResult.fail();
				}	
			case aliH5: // 支付宝H5支付
				return ActionResult.success(result.getData());
			default:
				return ActionResult.fail();
			}
		} else {
			return ActionResult.fail(result.getCode(), result.getCodemsg());
		}
	}*/
	
	/**
	 * 根据IP地址获取城市名称
	 * @param ip - IP
	 * @return String
	 */
	public static String getCityNameByIP(String ip) {
		if(Tools.isNull(ip)) {
			return null;
		}
		
		String[] area = IP.findArea(ip);
		if(area != null && Tools.isNotNull(area[2])) {
			return area[2];
		}
		return null;
	}
	
	/**
	 * 根据IP地址获省份名称
	 * @param ip - IP
	 * @return String
	 */
	public static String getProvinceNameByIP(String ip) {
		if(Tools.isNull(ip)) {
			return null;
		}
		
		String[] area = IP.findArea(ip);
		if(area != null && Tools.isNotNull(area[1])) {
			return area[1];
		}
		return null;
	}
	
	private ServiceHelper(){}


	public static class FirstRechargePrice{
		private static final RechargeCustomPriceEntity price = new RechargeCustomPriceEntity();
		private static final EnergyCustomPriceEntity energyPrice = new EnergyCustomPriceEntity();
		private static final RedFlowerPriceEntity redFlowerPrice = new RedFlowerPriceEntity();
		static{
			price.setId(Long.MAX_VALUE);
			price.setTitle("960钻");
			price.setDescription("首充48元,每天100份,先到先得");
			price.setMoney(4800);
			price.setDiamond(960);
			price.setDonor(0);

			energyPrice.setId(Long.MAX_VALUE);
			energyPrice.setTitle("10能量");
			energyPrice.setDescription("10元首充");
			energyPrice.setMoney(1000);
			energyPrice.setEnergy(10);
			energyPrice.setDonor(0);
		}

		/**
		 * 首冲价格
		 * @return
		 */
		public static RechargeCustomPriceEntity getPrice(){
			return price;
		}
		
		/**
		 * 动态价格
		 * @return
		 */
		public static <T> RechargeCustomPriceEntity getPrice(FirstChargeConfigDto config){
			
			RechargeCustomPriceEntity price = new RechargeCustomPriceEntity();
			price.setId(Long.MAX_VALUE);
			price.setTitle(config.getTitle());
			price.setDescription(config.getDescription());
			price.setMoney(Double.valueOf(config.getMoney()*100).intValue());
			price.setDiamond(config.getDiamond());
			price.setDonor_type(0);
			price.setDonor(0);
			return price;
		}
		
		
		/**
		 * 动态价格
		 * @return
		 */
		public static RedFlowerPriceEntity getRedFlowerPrice(FirstChargeRedFlowerConfigDto config){
			RedFlowerPriceEntity price = new RedFlowerPriceEntity();
			price.setId(Long.MAX_VALUE-100);
			price.setTitle(config.getTitle());
			price.setDescription(config.getDescription());
			price.setMoney(Double.valueOf(config.getMoney()*100).intValue());
			price.setFlower(config.getFlower());
			price.setDonor_type(0);
			price.setDonor_times(0);
			price.setDonor(0);
			return price;
		}
		
		/**
		 * 小红花动态价格
		 * @return
		 */
		public static RedFlowerPriceEntity getRedFlowerDynamicPrice(long randomPriceid){
			
			RedFlowerPriceEntity price = new RedFlowerPriceEntity();
			price.setId(randomPriceid);
			if((Long.MAX_VALUE-1) == randomPriceid){
				price.setId(Long.MAX_VALUE-1);
				price.setTitle("12朵");
				price.setDescription("仅需5元");
				price.setMoney(500);
				price.setFlower(12);
				price.setDonor_type(0);
				price.setDonor_times(0);
				price.setDonor(0);
			}
			return price;
		}
		
		
	
		
		
		/**
		 * 30元和20元充值送8钻动态价格
		 * @return
		 */
		public static RechargeCustomPriceEntity getDynamicPrice(long randomPriceid){
			
			RechargeCustomPriceEntity price = new RechargeCustomPriceEntity();
			price.setId(randomPriceid);
			if((Long.MAX_VALUE-1) == randomPriceid){
				price.setTitle("280钻");
				price.setDescription("仅需28元");
				price.setMoney(2800);
				price.setDiamond(280);
				price.setDonor_type(0);
				price.setDonor(0);
				price.setDonor_times(0);
			}else if((Long.MAX_VALUE-2) == randomPriceid){
				price.setTitle("100钻");
				price.setDescription("仅需10元");
				price.setMoney(1000);
				price.setDiamond(100);
				price.setDonor_type(0);
				price.setDonor(0);
				price.setDonor_times(0);
			}else if((Long.MAX_VALUE-3) == randomPriceid){
				price.setTitle("100钻");
				price.setDescription("仅需5元");
				price.setMoney(500);
				price.setDiamond(100);
				price.setDonor_type(0);
				price.setDonor(0);
				price.setDonor_times(0);
			}
			return price;
		}
		
		public static RechargePriceEntity getIosDynamicPrice(long randomPriceid){
			RechargeCustomPriceEntity price = getDynamicPrice(randomPriceid);
			RechargePriceEntity iosPrice = new RechargePriceEntity();
			iosPrice.setId(randomPriceid);
			iosPrice.setTitle(price.getTitle());
			iosPrice.setDescription(price.getDescription());
			iosPrice.setMoney(price.getMoney());
			iosPrice.setDiamond(price.getDiamond());
			iosPrice.setDonor_type(0);
			iosPrice.setDonor(price.getDonor());
			iosPrice.setDonor_times(0);
			return iosPrice;
		}
		

		/**
		 * 首冲价格
		 * @return
		 */
		public static EnergyCustomPriceEntity getEnergyPrice(){
			return energyPrice;
		}
	}

	/**
	 * 通过ip138线上获得地址数组信息
	 * @param ip
	 * @return
	 */
	public static String[] get138ArrayByIP(String ip) {
		String url = "http://api.ip138.com/query/?ip="+ip+"&datatype=jsonp&token=e51bf0b079e4db40a3a8fa57decc99e8";
		//logger.info("请求地址="+url);
		String content = HttpUtil.get(url, "UTF-8");
		try {
			JSONObject json = JsonHelper.toJsonObject(content);
			if (json!=null) {
				if (json.get("ret").equals("ok")) {
					String basic = json.getString("data").replace("\"", "").replace("[", "").replace("]", "");
					String[] area = basic.split(",");
					if (area.length>3) {
						return area;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
