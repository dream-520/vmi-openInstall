package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.agent.INeteasePictureCheckAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.neteasecheck.HttpClient4Utils;
import com.tigerjoys.shark.miai.agent.neteasecheck.SignatureUtils;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;

/**
 * 网易图片监黄业务
 * @author shiming
 */
@Service
public class NeteasePictureCheckAgentImpl implements INeteasePictureCheckAgent {

	 /**
     * 产品密钥ID，产品标识
     */
    private final static String SECRETID = "76c0f34964b66e731362aab85e9185d0";
    /**
     * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     */
    private final static String SECRETKEY = "183ee30d37ab4a45773f7b0d87c45cbd";
    /**
     * 业务ID，易盾根据产品业务特点分配
     */
    private final static String BUSINESSID = "58390ff96b123d9ceff0448243eba8d7";
    /**
     * 易盾反垃圾云服务图片在线检测接口地址
     */
    private final static String API_URL = "https://as.dun.163yun.com/v4/image/check";
    /**
     * 实例化HttpClient，发送http请求使用，可根据需要自行调参
     */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 10000, 4000, 4000);
    
    private static final Logger logger = LoggerFactory.getLogger(NeteasePictureCheckAgentImpl.class);
    
    @Autowired
	private ISysConfigContract sysConfigContract;
	
	@Override
	public int check(String url, double pornographicR, double lowR) throws Exception {
		Map<String, String> params = new HashMap<>();
        // 1.设置公共参数
        params.put("secretId", SECRETID);
        params.put("businessId", BUSINESSID);
        params.put("version", "v4");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(new Random().nextInt()));
        
        JSONArray jsonArray = new JSONArray();
        JSONObject image = new JSONObject();
        image.put("name", IdGenerater.generateId()+"");
        image.put("type", 1);
        image.put("data", url);
        jsonArray.add(image);
        params.put("images", jsonArray.toString());
        String signature = SignatureUtils.genSignature(SECRETKEY, params);
        params.put("signature", signature);
        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        String result = HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);
        int succ = 0;
		String name = null;
		int status = 0;
		String taskId = null;
		int action = 0;
		int pornographicLevel = 0;
		double pornographicRate = 0;
		int lowLevel = 0;
		double lowRate = 0;
		//然后分析对应的图片检测结果
		JSONObject resultObject = JsonHelper.toJsonObject(result);
		if(Tools.isNotNull(resultObject)) {
			int code = resultObject.getIntValue("code");
	        String msg = resultObject.getString("msg");
	        if(code == 200) {
	        	//图片反垃圾结果
	        	JSONArray array = resultObject.getJSONArray("antispam");
	        	if(Tools.isNotNull(array)) {
	        		//获取一张图片的检测结果
	        		JSONObject imageRes = array.getJSONObject(0);
	        		if(Tools.isNotNull(imageRes)) {
	        			//处理色情标签和低俗标签的值信息
	        			name = imageRes.getString("name");
	                    status = imageRes.getIntValue("status");
	                    taskId = imageRes.getString("taskId");
	                    //图片维度结果
	                    action = imageRes.getIntValue("action");
	                    logger.error("taskId:"+taskId+" status:"+status+"name:"+name+" action:"+action);
	                    JSONArray labels = imageRes.getJSONArray("labels");
	                    if(Tools.isNotNull(labels)) {
	                    	//设置对应的成功标识
	                    	succ = 1;
	                    	for(int i = 0; i<labels.size(); i++) {
	                    		JSONObject label = labels.getJSONObject(i);
	                    		if(Tools.isNotNull(label)) {
	                    			int labelValue = label.getIntValue("label");
	                                int level = label.getIntValue("level");
	                                double rate = label.getDoubleValue("rate");
	                                logger.error("label:"+labelValue+" level:"+level+"rate:"+rate);
	                                if(labelValue == 100) {
	                                	//处理色情值
	                                	pornographicLevel = level;
	                                	pornographicRate = rate;
	                                } else if(labelValue == 110) {
										//处理低俗标签
	                                	lowLevel = level;
	                                	lowRate = rate;
									}
	                    		}
	                    	}
	                    }
	        		}
	        	}
	        } else {
				//输出对应的错误日志信息
	        	logger.error("进行监黄处理失败:"+resultObject.toJSONString());
			}
		}
		
		int warn = 0;
		if(succ == 1) {
			warn = checkPornographic(pornographicLevel, pornographicRate, pornographicR, pornographicR);
			if (warn == 0) {
				warn = checkLow(lowLevel, lowRate, lowR, lowR);
			}
		}
		return warn;
	}
	
	private int checkPornographic(int pornographicLevel, double pornographicRate, double indeterminacy, double determine) {
		int check = 0;
		if(pornographicLevel == 1) {
			if(pornographicRate >= indeterminacy) {
				check = 1;
			}
		} else if(pornographicLevel == 2) {
			if(pornographicRate >= determine) {
				check = 1;
			}
		}
		return check;
	}
	
	private int checkLow(int lowLevel, double lowRate, double indeterminacy, double determine) {
		int check = 0;
		if(lowLevel == 1) {
			if(lowRate >= indeterminacy) {
				check = 2;
			}
		} else if(lowLevel == 2) {
			if(lowRate >= determine) {
				check = 2;
			}
		}
		return check;
	}

	@Override
	public int checkAll(String url) throws Exception {
		// 后台文字聊天控制
		SysConfigEntity config = sysConfigContract.findByProperty("name", AgentRedisCacheConst.PICTURE_CHAT_CONTROL_PREFIX);
		HashMap<Integer, Integer> ctrMap = new HashMap<>();
		if(Tools.isNotNull(config)) {
			JSONArray ctrlList = JsonHelper.toJsonArray(config.getValue());
			if(Tools.isNotNull(ctrlList)) {
				for(int i=0 ; i<ctrlList.size();i++) {
					Object object = ctrlList.get(i);
					ctrMap.put(Integer.valueOf(object.toString()), Integer.valueOf(object.toString()));
				}
			}
		}
		
		Map<String, String> params = new HashMap<>();
        // 1.设置公共参数
        params.put("secretId", SECRETID);
        params.put("businessId", BUSINESSID);
        params.put("version", "v4");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(new Random().nextInt()));
        
        JSONArray jsonArray = new JSONArray();
        JSONObject image = new JSONObject();
        image.put("name", IdGenerater.generateId()+"");
        image.put("type", 1);
        image.put("data", url);
        jsonArray.add(image);
        params.put("images", jsonArray.toString());
        String signature = SignatureUtils.genSignature(SECRETKEY, params);
        params.put("signature", signature);
        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        String result = HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);
        int succ = 0;
		String name = null;
		int status = 0;
		String taskId = null;
		int action = 0;
		List<Integer> codes = new ArrayList<Integer>();
		//然后分析对应的图片检测结果
		JSONObject resultObject = JsonHelper.toJsonObject(result);
		if(Tools.isNotNull(resultObject)) {
			int code = resultObject.getIntValue("code");
	        String msg = resultObject.getString("msg");
	        if(code == 200) {
	        	//图片反垃圾结果
	        	JSONArray array = resultObject.getJSONArray("antispam");
	        	if(Tools.isNotNull(array)) {
	        		//获取一张图片的检测结果
	        		JSONObject imageRes = array.getJSONObject(0);
	        		if(Tools.isNotNull(imageRes)) {
	        			//处理色情标签和低俗标签的值信息
	        			name = imageRes.getString("name");
	                    status = imageRes.getIntValue("status");
	                    taskId = imageRes.getString("taskId");
	                    //图片维度结果
	                    action = imageRes.getIntValue("action");
	                    logger.error("taskId:"+taskId+" status:"+status+"name:"+name+" action:"+action);
	                    JSONArray labels = imageRes.getJSONArray("labels");
	                    if(Tools.isNotNull(labels)) {
	                    	//设置对应的成功标识
	                    	succ = 1;
	                    	for(int i = 0; i<labels.size(); i++) {
	                    		JSONObject label = labels.getJSONObject(i);
	                    		if(Tools.isNotNull(label)) {
	                    			int labelValue = label.getIntValue("label");
	                                int level = label.getIntValue("level");
	                                double rate = label.getDoubleValue("rate");
	                                logger.error("label:"+labelValue+" level:"+level+"rate:"+rate);
	                                if(level > 0 && rate >= 0.5) {
	                                	if(ctrMap.containsKey(labelValue))
	                                		//将对应的类型添加到违规集合中
	                                		codes.add(labelValue);
	                                }
	                    		}
	                    	}
	                    }
	        		}
	        	}
	        } else {
				//输出对应的错误日志信息
	        	logger.error("进行监黄处理失败:"+resultObject.toJSONString());
			}
		}
		
		//最终获取分析的检测结果值
		if(succ == 1) {
			if(Tools.isNotNull(codes) && codes.size() > 0) {
				//返回第一条违规索引值
				return codes.get(0);
			} 
			return 1;
		}
		return 0;
	}

}
