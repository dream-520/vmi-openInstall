package com.tigerjoys.shark.miai.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.CheckVideoDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.service.IVchatTcpMessageService;
import com.tigerjoys.shark.miai.dto.service.WarnInfo;
import com.tigerjoys.shark.miai.inter.contract.IAnchorVideoCheckContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorVideoCheckWarnContract;
import com.tigerjoys.shark.miai.inter.contract.IAppAreaCityContract;
import com.tigerjoys.shark.miai.inter.contract.IAppAreaContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorVideoCheckEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorVideoCheckWarnEntity;
import com.tigerjoys.shark.miai.inter.entity.AppAreaCityEntity;
import com.tigerjoys.shark.miai.inter.entity.AppAreaEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.service.INeteasePictureCheck;
import com.tigerjoys.shark.miai.utils.HttpClient4Utils;
import com.tigerjoys.shark.miai.utils.SignatureUtils;

@Service
public class NeteasePictureCheckImpl implements INeteasePictureCheck {

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
    
    private static final Logger logger = LoggerFactory.getLogger(NeteasePictureCheckImpl.class);
	
    @Autowired
	private IUserAgent userAgent;
    
    @Autowired
  	private IAppAreaContract appAreaContract;
    
    @Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_USER_ONLINE_LIST_CACHE)
	private CacheRedis cacheRedis; 
    
    @Autowired
	private IAppAreaCityContract appAreaCityContract;
    
    @Autowired
	private ISysConfigContract sysConfigContract;
    
    @Autowired
	private IAnchorVideoCheckContract anchorVideoCheckContract;
    
    @Autowired
	private IAnchorVideoCheckWarnContract anchorVideoCheckWarnContract;
    
    @Autowired
	private IVchatTcpMessageService vchatTcpMessageService;
    
	@Override
	public String check(String path) throws UnsupportedEncodingException {
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
        image.put("data", path);
        jsonArray.add(image);
        params.put("images", jsonArray.toString());
        String signature = SignatureUtils.genSignature(SECRETKEY, params);
        params.put("signature", signature);
        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        return HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);
	}

	@Override
	public void checkAndAction(long userId, long otherId, long serialNum, String path) throws Exception {
		//首先检测对应的用户是否处于需要进行检测的阶段
		//获取用户或者主播对应类型       此处的处理主要是获取对应的用户对应的类型  和 区域信息
		int waiter = 0;
		//默认是三四线区域
		int city = 3;
		UserBO bo = userAgent.findById(userId);
		if(Tools.isNotNull(bo)) {
			if(bo.isWaiter())
				waiter = 1;
			//首先在对应的缓存中查询对应的用户所处的区域
			String key = "user_area_"+userId;
			String area = cacheRedis.get(key);
			if(Tools.isNotNull(area)) {
				city = Integer.parseInt(area);
			} else {
				//在缓存中没有找到  就在对应的表查询对应的区域
				long cityid = bo.getCityid();
				if(cityid > 0) {
					AppAreaEntity appArea = appAreaContract.findById(cityid);
					if(Tools.isNotNull(appArea)) {
						int baidu = appArea.getBaidu_code();
						if(baidu > 0) {
							AppAreaCityEntity entity = appAreaCityContract.findByProperty("baidu_code", baidu);
							if(Tools.isNotNull(entity)) {
								city = entity.getCode();
								cacheRedis.set(key, city);
								//设置过期时间
								cacheRedis.expire(key, 5*60);
							}
						}
					}
				}
			}
			//根据对应的类型和区域 检测对应的是否需要启动检测服务    同时确定对应的检测类型
			int type = checkType(waiter, city);
			logger.error("用户类型:"+ waiter +" 用户所处的区域:"+city +" 检测类型:"+type);
			//根据获取到的检测类型 触发进行检测图片处理的操作
			if(type > 0) {
				ExecutorServiceHelper.executor.execute(new PictureCheckThread(userId, otherId, serialNum, path, type));
			}
		}
	}
	
	/**
	 * 进行检测的类型
	 * 0 不进行监黄处理  1 色情监黄 2 低俗监黄 3 色情和低俗都进行监黄
	 * @param waiter
	 * @param city
	 * @return
	 * @throws Exception 
	 */
	private int checkType(int waiter, int city) throws Exception {
		int type = 0;
		SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_CHECK_VIDEO_CONFIG);
		if(Tools.isNotNull(config)) {
			//获取对应的开关值 和 对应的数量值
			JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
			if(Tools.isNotNull(ctrl)) {
				CheckVideoDto dto = ctrl.toJavaObject(CheckVideoDto.class);
				if(Tools.isNotNull(dto)) {
					//首先处理是否是色情监黄
					//检测开关
					int pornographicOn = dto.getPornographicOn();
					if(pornographicOn == 1) {
						//检测对应的区域
						String area = dto.getPornographicArea();
						if(Tools.isNotNull(area) && area.contains(city+"")) {
							//检测用户类型
							if(waiter == 1) {
								int anchor = dto.getPornographicAnchor();
								if(anchor == 1)
									type = 1;
							} else {
								int user = dto.getPornographicUser();
								if(user == 1)
									type = 1;
							}
						}
					}
					
					//然后处理是否是低俗监黄
					int lowOn = dto.getLowOn();
					if(lowOn == 1) {
						//检测对应的区域
						String area = dto.getLowArea();
						if(Tools.isNotNull(area) && area.contains(city+"")) {
							//检测用户类型
							if(waiter == 1) {
								int anchor = dto.getLowAnchor();
								if(anchor == 1) {
									if(type == 1) {
										type = 3;
									} else {
										type = 2;
									}
								}
							} else {
								int user = dto.getLowUser();
								if(user == 1) {
									if(type == 1) {
										type = 3;
									} else {
										type = 2;
									}
								}
							}
						}
					}
				}
			}
		}
		return type;
	}

	/**
	 * 图片监黄检测流程
	 * @author shiming
	 */
	private class PictureCheckThread implements Runnable {
		
		private long userId;
		private long otherId;
		private long serialNum;
		private String path;
		private int type;

		public PictureCheckThread(long userId, long otherId, long serialNum, String path,int type) {
			this.userId = userId;
			this.otherId = otherId;
			this.serialNum = serialNum;
			this.path = path;
			this.type = type;
		}
		
		@Override
		public void run() {
			try {
				//首先进行网易图片检测
				String res = check(path);
				if(Tools.isNotNull(res) && res.length() > 0) {
					SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_CHECK_VIDEO_CONFIG);
					CheckVideoDto dto = null;
					if(Tools.isNotNull(config)) {
						JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
						if(Tools.isNotNull(ctrl)) {
							dto = ctrl.toJavaObject(CheckVideoDto.class);
						}
					}
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
					JSONObject resultObject = JsonHelper.toJsonObject(res);
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
				                    //taskId=61b6174f72b642d68f43cd5d52af52ec，status=0，name=145876407868653312，action=0
				                    //taskId=4770a4c23fec4a9381ea8dfc3c7569c2，status=0，name=145876407870750464，action=2   2号标识确认了
				                    System.err.println(String.format("taskId=%s，status=%s，name=%s，action=%s", taskId, status, name, action));
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
				                                //label:100, level=2, rate=0.9999998
							                    //label:100, level=0, rate=0.9999538
				                                System.err.println(String.format("label:%s, level=%s, rate=%s", labelValue, level, rate));
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
					System.err.println(String.format("pornographicLevel:%s, pornographicRate=%s, lowLevel=%s, lowRate=%s", pornographicLevel, pornographicRate, lowLevel, lowRate));
					//此处需要根据类型来确定监黄参数   0 不是黄色  1 色情黄色 2 低俗黄色
					int check = 0;
					if(succ == 1) {
						if(type == 1) {
							//只进行色情参数效验
							//首先确认参数   1 嫌疑   2 确定
							check = checkPornographic(pornographicLevel, pornographicRate, dto.getPornographicIndeterminacy(), dto.getPornographicDetermine());
						} else if(type == 2) {
							//只进行低俗参数效验
							check = checkLow(lowLevel, lowRate, dto.getLowIndeterminacy(), dto.getLowDetermine());
						} else {
							//同时进行色情和低俗检测 首先确定那个系数更高一些
							if(pornographicLevel > lowLevel) {
								//确定为色情等级
								check = checkPornographic(pornographicLevel, pornographicRate, dto.getPornographicIndeterminacy(), dto.getPornographicDetermine());
							} else if(pornographicLevel == lowLevel) {
								//在等级相同的情况下 查看对应的比例谁高
								if(pornographicRate >= lowRate) {
									check = checkPornographic(pornographicLevel, pornographicRate, dto.getPornographicIndeterminacy(), dto.getPornographicDetermine());
								} else {
									check = checkLow(lowLevel, lowRate, dto.getLowIndeterminacy(), dto.getLowDetermine());
								}
							} else {
								//确定为低俗等级
								check = checkLow(lowLevel, lowRate, dto.getLowIndeterminacy(), dto.getLowDetermine());
							}
						}
					}
					
					//进行数据入库操作处理
					AnchorVideoCheckEntity t = new AnchorVideoCheckEntity();
					t.setUserid(userId);
					t.setOtherid(otherId);
					t.setUrl(path);
					t.setSerialNum(serialNum);
					t.setCheckType(type);
					t.setCreate_time(new Date());
					t.setDefineType(check);
					t.setLowLevel(lowLevel);
					t.setLowRate(lowRate);
					t.setPornographicLevel(pornographicLevel);
					t.setPornographicRate(pornographicRate);
					anchorVideoCheckContract.insert(t);
					System.err.println("对应的检测点:"+t.getId());
					//检测是否进行警告操作处理
					if(check > 0) {
						//是否进行发送警告标示  0 不发送   1 发送色情警告 2 发送色情切断警告 3 发送低俗警告  4 发送低俗切断警告
						int warnType = 0;
						//此处使用使用对应的缓存进行处理
						String key = "video_check_" + userId + "_" + otherId + "_"+ serialNum + "_" + check;
						String warn = cacheRedis.get(key);
						if(Tools.isNotNull(warn) && warn.length() > 0) {
							//在缓存中检测到对应的存储信息
							WarnInfo info = JsonHelper.toObject(warn, WarnInfo.class);
							int num = info.getNum();
							long last = info.getSec();
							long current = System.currentTimeMillis();
							//根据当前配置的类型来确定下一步的处理
							if(check == 1) {
								//进一步检测对应的时间是否已经到了
								int time = dto.getPornographicWarnTime();
								if(last + time * 1000 <= current) {
									int warnOn = dto.getPornographicWarn();
									if(warnOn == 2) {
										warnType = 2;
									} else {
										if(num < dto.getPornographicWarnCount()) {
											warnType = 1;
										}
									}
									if(warnType > 0) {
										//更新最新的警告发送时间
										info.setNum(num + 1);
										info.setSec(current);
										cacheRedis.set(key, JsonHelper.toJson(info));
										//设置缓存20分钟
										cacheRedis.expire(key, 60*20);
									}
								}
								System.err.println("check:"+check+" num:"+num +" last:"+last +" current:"+current+" time:"+time+" warn:"+warnType);
							} else if(check == 2){
								//进一步检测对应的时间是否已经到了
								int time = dto.getLowWarnTime();
								if(last + time * 1000 <= current) {
									int warnOn = dto.getLowWarn();
									if(warnOn == 2) {
										warnType = 4;
									} else {
										if(num < dto.getLowWarnCount()) {
											warnType = 3;
										}
									}
									if(warnType > 0) {
										//更新最新的警告发送时间
										info.setNum(num + 1);
										info.setSec(current);
										cacheRedis.set(key, JsonHelper.toJson(info));
										//设置缓存20分钟
										cacheRedis.expire(key, 60*20);
									}
								}
								System.err.println("非首次 check:"+check+" num:"+num +" last:"+last +" current:"+current+" time:"+time+" warn:"+warnType);
							}
						} else {
							//在缓存中没有检测到对应的缓存警告
							WarnInfo info = new WarnInfo();
							info.setNum(1);
							info.setSec(System.currentTimeMillis());
							cacheRedis.set(key, JsonHelper.toJson(info));
							//设置缓存20分钟
							cacheRedis.expire(key, 60*20);
							if (check == 1) {
								warnType = 1;
							} else if(check == 2){
								warnType = 3;
							}
							System.err.println("首次 check:"+check+" warn:"+warnType);
						}
						//将最终的警告数据进行入库操作处理
						if(warnType > 0) {
							AnchorVideoCheckWarnEntity entity = new AnchorVideoCheckWarnEntity();
							entity.setCheckId(t.getId());
							entity.setUserid(userId);
							entity.setOtherid(otherId);
							entity.setSerialNum(serialNum);
							entity.setWarn(check);
							entity.setWarnType(warnType);
							entity.setCreate_time(new Date());
							anchorVideoCheckWarnContract.insert(entity);
							
							//根据对应的警告类型进行发送自定义消息处理
							if(warnType == 1) {
								vchatTcpMessageService.checkPorn(userId, serialNum, 0, dto.getPornographicWarnText().replace("aaa", "\n"));
							} else if(warnType == 2) {
								vchatTcpMessageService.checkPorn(userId, serialNum, 2, dto.getPornographicWarnStopUText().replace("aaa", "\n"));
								vchatTcpMessageService.checkPorn(otherId, serialNum, 0, dto.getPornographicWarnStopTText().replace("aaa", "\n"));
							} else if(warnType == 3) {
								vchatTcpMessageService.checkPorn(userId, serialNum, 0, dto.getLowWarnText().replace("aaa", "\n"));
							} else if(warnType == 4) {
								vchatTcpMessageService.checkPorn(userId, serialNum, 4, dto.getLowWarnStopUText().replace("aaa", "\n"));
								vchatTcpMessageService.checkPorn(otherId, serialNum, 0, dto.getLowWarnStopTText().replace("aaa", "\n"));
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		@Override
		public void run() {
			try {
				//首先进行网易图片检测
				String res = check(path);
				if(Tools.isNotNull(res) && res.length() > 0) {
					SysConfigEntity config = sysConfigContract.findByProperty("name", Const.APP_CHECK_VIDEO_CONFIG);
					CheckVideoDto dto = null;
					if(Tools.isNotNull(config)) {
						JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
						if(Tools.isNotNull(ctrl)) {
							dto = ctrl.toJavaObject(CheckVideoDto.class);
						}
					}
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
					JSONObject resultObject = JsonHelper.toJsonObject(res);
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
				                    //taskId=61b6174f72b642d68f43cd5d52af52ec，status=0，name=145876407868653312，action=0
				                    //taskId=4770a4c23fec4a9381ea8dfc3c7569c2，status=0，name=145876407870750464，action=2   2号标识确认了
				                    System.err.println(String.format("taskId=%s，status=%s，name=%s，action=%s", taskId, status, name, action));
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
				                                //label:100, level=2, rate=0.9999998
							                    //label:100, level=0, rate=0.9999538
				                                System.err.println(String.format("label:%s, level=%s, rate=%s", labelValue, level, rate));
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
					System.err.println(String.format("pornographicLevel:%s, pornographicRate=%s, lowLevel=%s, lowRate=%s", pornographicLevel, pornographicRate, lowLevel, lowRate));
					//此处需要根据类型来确定监黄参数   0 不是黄色  1 色情黄色 2 低俗黄色
					int check = 0;
					if(succ == 1) {
						if(type == 1) {
							//只进行色情参数效验
							//首先确认参数   1 嫌疑   2 确定
							check = checkPornographic(pornographicLevel, pornographicRate, dto.getPornographicIndeterminacy(), dto.getPornographicDetermine());
						} else if(type == 2) {
							//只进行低俗参数效验
							check = checkLow(lowLevel, lowRate, dto.getLowIndeterminacy(), dto.getLowDetermine());
						} else {
							//同时进行色情和低俗检测 首先确定那个系数更高一些
							if(pornographicLevel > lowLevel) {
								//确定为色情等级
								check = checkPornographic(pornographicLevel, pornographicRate, dto.getPornographicIndeterminacy(), dto.getPornographicDetermine());
							} else if(pornographicLevel == lowLevel) {
								//在等级相同的情况下 查看对应的比例谁高
								if(pornographicRate >= lowRate) {
									check = checkPornographic(pornographicLevel, pornographicRate, dto.getPornographicIndeterminacy(), dto.getPornographicDetermine());
								} else {
									check = checkLow(lowLevel, lowRate, dto.getLowIndeterminacy(), dto.getLowDetermine());
								}
							} else {
								//确定为低俗等级
								check = checkLow(lowLevel, lowRate, dto.getLowIndeterminacy(), dto.getLowDetermine());
							}
						}
					}
					
					//进行数据入库操作处理
					AnchorVideoCheckEntity t = new AnchorVideoCheckEntity();
					t.setUserid(userId);
					t.setOtherid(otherId);
					t.setUrl(path);
					t.setSerialNum(serialNum);
					t.setCheckType(type);
					t.setCreate_time(new Date());
					t.setDefineType(check);
					t.setLowLevel(lowLevel);
					t.setLowRate(lowRate);
					t.setPornographicLevel(pornographicLevel);
					t.setPornographicRate(pornographicRate);
					anchorVideoCheckContract.insert(t);
					System.err.println("对应的检测点:"+t.getId());
					//检测是否进行警告操作处理
					if(check > 0) {
						//是否进行发送警告标示  0 不发送   1 发送色情警告 2 发送色情切断警告 3 发送低俗警告  4 发送低俗切断警告
						//此处使用
						int warn = 0;
						//首先检测对应的警告类型
						PageModel pageModel = PageModel.getPageModel();
						pageModel.addQuery(Restrictions.eq("userid", userId));
						pageModel.addQuery(Restrictions.eq("otherid", otherId));
						pageModel.addQuery(Restrictions.eq("serialNum", serialNum));
						pageModel.addQuery(Restrictions.eq("warnType", check));
						long count = anchorVideoCheckWarnContract.count(pageModel);
						if(count > 0) {
							//获取最近的一条警告信息 方便后面使用
							pageModel.addLimitField(0, 1);
							pageModel.desc("create_time");
							List<AnchorVideoCheckWarnEntity> warns = anchorVideoCheckWarnContract.load(pageModel);
							Date time = null;
							if(Tools.isNotNull(warns)) {
								AnchorVideoCheckWarnEntity checkWarn = warns.get(0);
								time = checkWarn.getCreate_time();
							}
							if(check == 1) {
								//色情
								int warnOn = dto.getPornographicWarn();
								if(warnOn == 1) {
									//检测是否满足发送时间
									if(Tools.isNotNull(time)) {
										long last = time.getTime();
										long current = System.currentTimeMillis();
									}
									//此处绝对是第二次检测到色情  需要进行切断处理
									warn = 2;
								} else {
									//此处需要检测发送的数量是否满足了对应的发送数量   
									if(count < dto.getPornographicWarnCount()) {
										//进一步检测是否满足发送警告的时间
										
									}
								}
							} else if(check == 2) {
								int warnOn = dto.getLowWarn();
								if(warnOn == 1) {
									warn = 4;
								} else {
									if(count < dto.getLowWarnCount()) {
										
									}
								}
							}
						} else {
							//根据检测类型设置发送警告类型
							if(check == 1) {
								warn = 1;
							} else if(check == 2) {
								warn = 3;
							}
						}
						//检测最近发送的一条警告时间  同时获取对应的警告次数
						
						//检测是否需要发送下一条警告信息
						
						//将最终的警告数据进行入库操作处理
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
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
	}
	
}
