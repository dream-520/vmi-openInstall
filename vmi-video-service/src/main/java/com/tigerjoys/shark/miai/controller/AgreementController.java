package com.tigerjoys.shark.miai.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.shark.miai.common.enums.AppNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.BootstrapListener;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.constant.LoggerAgentConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.es.dto.EsMobileAppListReordDto;
import com.tigerjoys.shark.miai.es.dto.EsMobileDeviceReordDto;
import com.tigerjoys.shark.miai.es.dto.EsMobileUserAppListReordDto;
import com.tigerjoys.shark.miai.es.service.IEsMobileAppListRecordService;
import com.tigerjoys.shark.miai.es.service.IEsMobileDeviceRecordService;
import com.tigerjoys.shark.miai.es.service.IEsMobileUserAppRecordService;
import com.tigerjoys.shark.miai.inter.contract.IAppIosDeviceUdidContract;
import com.tigerjoys.shark.miai.inter.contract.IDeviceBaseStationContract;
import com.tigerjoys.shark.miai.inter.entity.AppIosDeviceUdidEntity;
import com.tigerjoys.shark.miai.inter.entity.DeviceBaseStationEntity;

/**
 * 用户协议相关接口
 * 
 * @author yangjunming
 *
 */
@Controller
@RequestMapping(value = "/web", produces = Produce.TEXT_HTML)
public class AgreementController {
	
	  @Autowired
	  private IAppIosDeviceUdidContract appIosDeviceUdidContract;

	  @Autowired
	  private IDeviceBaseStationContract deviceBaseStationContract;
	  
	  @Autowired
	  private IEsMobileDeviceRecordService esMobileDeviceRecordService;
	  
	  @Autowired
	  private IEsMobileAppListRecordService esMobileAppListRecordService;
	  
	  @Autowired
	  private IEsMobileUserAppRecordService esMobileUserAppRecordService;
	  
	  
	  
	  @Autowired
	  private IUserAgent userAgent;
	  
	/**
	 * 充值相关日志
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());	
	
	/**
	 * 数据采集
	 */
	private final Logger mobileInfoLogger = LoggerFactory.getLogger(LoggerAgentConst.MOBILEINFO_LOGGER_NAME);	
	
	/**
	 * 新的数据采集  2019/08/01
	 */
	private final Logger mobileDeviceLogger = LoggerFactory.getLogger(LoggerAgentConst.MOBILEDEVICE_LOGGER_NAME);	
	
	
	/**
	 * 用户采集数据采集  2019/09/27
	 */
	private final Logger mobileAppListLogger = LoggerFactory.getLogger(LoggerAgentConst.MOBILEAPPLIST_LOGGER_NAME);	
	
	
	
	/**
	 * 用户协义
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/agreement/{path}", produces = Produce.TEXT_HTML)
	public String agreement(@PathVariable String path) throws Exception {
		return "protocol/protocol" + path;

	}
	
	
	/**
	 * 用户协义模型
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/agreement/model/package", produces = Produce.TEXT_HTML)
	public String agreementModelPackage(Model model) throws Exception {
		model.addAttribute("appName",AppNameEnum.getByDesc("com.vvtalk.vvtalk"));
		return "protocol/protocol_model";

	}
	
	/**
	 * 防骗说明
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/instruction/preventFraud", produces = Produce.TEXT_HTML)
	public String preventFraud() throws Exception {
		return "instruction/preventCheat";
	}
	
	
	/**
	 * 6间房直播
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/appOutPath/go6cn", produces = Produce.TEXT_HTML)
	public String go6cn() throws Exception {
		return "appoint/go6cn";
	}
	
	/**
	 * 羚萌直播
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/appOutPath/goLingming", produces = Produce.TEXT_HTML)
	public String goLingming() throws Exception {
		return "appoint/goLingming";
	}
	
	
	/**
	 * IOS用户隐私协议
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/ios/milianPcy", produces = Produce.TEXT_HTML)
	public String milianPcy(Model model) throws Exception {
		/*  之后改动态的，运维配值
		Map<String,String> hsmp = new HashMap<>();
		hsmp.put(AppNameEnum.ios_com_jiaoyou_quliao.getPackageName(), "quliaojiaoyou@163.com");
		hsmp.put(AppNameEnum.ios_com_xq_yuanyuan.getPackageName(), "yuanyuanxq@163.com");
		*/
		
		return "ioshtm/milian_pcy";
	}
	
	/**
	 * IOS用户隐私协议
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/ios/milianSpt", produces = Produce.TEXT_HTML)
	public String milianSpt(Model model) throws Exception {
		model.addAttribute("path", "support2");
		return "ioshtm/milian_spt";
	}
	
	/**
	 * IOS用户产品说明，给苹果审核用的，地址为动态的
	 * path 为图片地址
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = { "/ios/milianSpt/{path}"}, produces = Produce.TEXT_HTML)
	public String milianSptPath(@PathVariable String path,Model model) throws Exception {
		model.addAttribute("path", path);
		return "ioshtm/milian_spt";
	}
	
	/**
	 * 充值送大礼
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/charge/sendGift", produces = Produce.TEXT_HTML)
	public String chargeSendGift() throws Exception {
		
		return "activity/rechargeGift/rechangeCourtesy";
	}
	
	/**
	 * VIP充值弹窗
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/charge/tovipPop", produces = Produce.TEXT_HTML)
	public String tovipPop() throws Exception {
		return "activity/goddess/tovipPop";
	}
	
	/**
	 * 约会弹窗弹窗
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/iosDownChannel/{channel}", produces = Produce.TEXT_HTML)
	public String iosDownChannel(@PathVariable String channel,Model model) throws Exception {
		model.addAttribute("channel", channel);
		return "iosdevice/channels";
	}
	
	/**
	 * IOS动态渠道下载连接
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/appointment/pop", produces = Produce.TEXT_HTML)
	public String appointmentPop() throws Exception {
		return "activity/goddess/appointmentPop";
	}
	
	/**
	 * 隐私
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/instruction/support", produces = Produce.TEXT_HTML)
	public String support() throws Exception {
		return "protocol/support";
	}
	
	/**
	 * 防骗说明
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/instruction/privacy", produces = Produce.TEXT_HTML)
	public String privacy() throws Exception {
		return "protocol/privacy";
	}
	
	/**
	 * 对方没有回应怎么办
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/instruction/otherDoesntRespond", produces = Produce.TEXT_HTML)
	public String otherDoesntRespond() throws Exception {
		return "instruction/otherDoesntRespond";

	}
	
	/**
	 * 送红包有什么好处
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/instruction/advantagesGivingRedbag", produces = Produce.TEXT_HTML)
	public String advantagesGivingRedbag() throws Exception {
		return "instruction/advantagesGivingRedbag";

	}

	
	/**
	 * 文明公约
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/instruction/civilProtocol", produces = Produce.TEXT_HTML)
	public String civiProtocol() throws Exception {
		return "activity/civilProtocol";
	}
	
	/**
	 * 分享企业包
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/share/enterprise", produces = Produce.TEXT_HTML)
	public String enterpriseIos() throws Exception {
		return "share/enterprise_ios";
	}
	
	
	/**
	 * 数据采集
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/data/moblieinfo", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult moblieinfo(@RequestBody String body) throws Exception {
		 JSONObject object = JsonHelper.toJsonObject(body);
	      String clientID = object.getString("clientID");
	      String data = object.getString("data");
	      HashMap<String, String> hsmp = new HashMap<>();
	      hsmp.put("clientID", clientID);
	      hsmp.put("data", data);
	      mobileInfoLogger.info(JsonHelper.toJson(hsmp));
		return ActionResult.success();
	}
	
	
	/**
	 * 数据采集
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@RequestMapping(value = "/data/mobile/appList", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult appList(@RequestBody String body) throws Exception {
		JSONObject object = JsonHelper.toJsonObject(body);
		logger.info("mobile_appList:" + body);
		JSONArray appListJson = object.getJSONArray("appList");
		JSONArray curAppListJson = object.getJSONArray("curAppList");
		long userId = RequestUtils.getCurrent().getUserid();
		List<String> appList = getAppListJsonArray(appListJson);
		List<String> curAppList = getAppListJsonArray(curAppListJson);
		logger.info("mobile_appList:userId=" + userId + ";appList" + JsonHelper.toJson(appList) + ";curAppList="
				+ JsonHelper.toJson(curAppList));
		
		if(userId>0){
			UserBO userBo = userAgent.findById(userId);
			if(Tools.isNotNull(userBo)){
				int userType = userBo.isWaiter()?1:0;
				Date current = new Date();
				Map<String ,Object> outMap = new HashMap<>();
				outMap.put("userId", userId);
				outMap.put("userType",userType);
				outMap.put("appList", appList);
				outMap.put("curAppList", curAppList);
				outMap.put("createTime", Tools.getFormatDate(current, "yyyy-MM-dd HH:mm:ss"));
				mobileAppListLogger.info(JsonHelper.toJson(outMap));
				if(Tools.isNotNull(appList)){
					LocalDateTime currentLocal = LocalDateTime.now();
					List<EsMobileAppListReordDto> dto = new ArrayList<>();
					for(String re:appList){
						dto.add(EsMobileAppListReordDto.preDto(userId,userType, re, currentLocal));
					}
					esMobileAppListRecordService.saveAll(dto);
					esMobileUserAppRecordService.saveEntity(EsMobileUserAppListReordDto.preDto(userId, userType, appList, curAppList, currentLocal));
				}
				
			}
		}
		return ActionResult.success();
	}
	
	public List<String> getAppListJsonArray(JSONArray array){
		List<String> list = new ArrayList<>();
		Map<String,String> hsmp = new HashMap<>();
		if(Tools.isNotNull(array)){
			for(int i=0;i<array.size();i++){
				String appName = array.getString(i);
				if(Tools.isNull(hsmp.get(appName))){
					list.add(appName);
				}
				hsmp.put(appName,appName);
			}
		}
		return list;
	}
	
	/**
	 * 手机数据采集
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/data/moblieDeviceInfo", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult moblieDeviceInfo(@RequestBody String body) throws Exception {
		JSONObject object = JsonHelper.toJsonObject(body);
		String clientID = object.getString("clientID");
		String modelData = object.getString("modelData");
		HashMap<String, String> hsmp = new HashMap<>();
		hsmp.put("clientID", clientID);
		hsmp.put("modelData", modelData);
		mobileDeviceLogger.info(JsonHelper.toJson(hsmp));
		try{
			Pattern root = Pattern.compile("mRegistered=YES.*?ta=");
			Matcher rootMatcher = root.matcher(modelData);
			List<Map<String, String>> deviceMap = new ArrayList<>();
			boolean findFlag = false;
			while (rootMatcher.find()) {
				Map<String, String> subMap = new HashMap<>();
				String subStr = rootMatcher.group();
				System.out.println(subStr);
				Pattern p = Pattern.compile("\\w*=[\\w-]*");
				Matcher m = p.matcher(rootMatcher.group());
				while (m.find()) {
					String[] re = Tools.split(m.group(), "=");
					if (re.length == 2) {
						if("mci".equals(re[0].toLowerCase()) 
						   || "mmcc".equals(re[0].toLowerCase())
						   || "mmnc".equals(re[0].toLowerCase())
						   || "mtac".equals(re[0].toLowerCase())
						   || "rsrq".equals(re[0].toLowerCase())
								){
							subMap.put(re[0].toLowerCase(), re[1]);
						}
					
					}
				}
				if(Tools.isNull(subMap.get("mci")) ||
						Tools.isNull(subMap.get("mmcc")) ||
						Tools.isNull(subMap.get("mmnc")) ||
						Tools.isNull(subMap.get("mtac")) 
						){
					continue;
				}else{
					deviceMap.add(subMap);
					findFlag = true;
				}
			}
			if (findFlag) {
	
				logger.debug("deviceInfo_msg:" + JsonHelper.toJson(deviceMap));
				long userid = 0;
				RequestHeader header = RequestUtils.getCurrent().getHeader();
				if (Tools.isNotNull(header)) {
					userid = header.getUserid();
				}
				if(deviceMap.size() == 0){
					return ActionResult.success();
				}
				DeviceBaseStationEntity entity = deviceBaseStationContract.findByProperty("clientid", clientID);
				if (Tools.isNotNull(entity)) {
					entity.setStation_ci(deviceMap.get(0).get("mci"));
					entity.setStation_mcc(deviceMap.get(0).get("mmcc"));
					entity.setStation_mnc(deviceMap.get(0).get("mmnc"));
					entity.setStation_lac(deviceMap.get(0).get("mtac"));
					entity.setStation_rsrq(deviceMap.get(0).get("rsrq"));
					String otherInfo = "";
					if(deviceMap.size()>1){
						otherInfo =JsonHelper.toJson(deviceMap.subList(1, deviceMap.size()>4? 4: deviceMap.size()));
					}
					if(otherInfo.length()<290){
						entity.setBase_station_info(otherInfo);
					}else{
						entity.setBase_station_info("");
					}
					entity.setUpdate_time(new Date());
					entity.setStatus(0);
					deviceBaseStationContract.update(entity);
				} else {
					Date current = new Date();
					entity = new DeviceBaseStationEntity();
					entity.setClientid(clientID);
					entity.setUserid(userid);
					entity.setStation_ci(deviceMap.get(0).get("mci"));
					entity.setStation_mcc(deviceMap.get(0).get("mmcc"));
					entity.setStation_mnc(deviceMap.get(0).get("mmnc"));
					entity.setStation_lac(deviceMap.get(0).get("mtac"));
					entity.setStation_rsrq(deviceMap.get(0).get("rsrq"));
					String otherInfo = "";
					if(deviceMap.size()>1){
						otherInfo =JsonHelper.toJson(deviceMap.subList(1, deviceMap.size()>4? 4: deviceMap.size()));
					}
					if(otherInfo.length()<290){
						entity.setBase_station_info(otherInfo);
					}else{
						entity.setBase_station_info("");
					}
					entity.setCreate_time(current);
					entity.setUpdate_time(current);
					entity.setStatus(0);
					deviceBaseStationContract.insert(entity);
				}
			}
		}catch(Exception e){
			logger.info("moblieDeviceInfo:异常",e);
		}
		
			try{
				Date current = new Date();
				List<EsMobileDeviceReordDto> esMobileList = new ArrayList<>();
				Pattern rootTest=Pattern.compile("\"appList\":\\[.*?\\]"); 
				Matcher rootMatcherTest=rootTest.matcher(modelData); 
				logger.info("moblieText:---appList---------");
				while(rootMatcherTest.find()){
					String matchertext = rootMatcherTest.group();
					logger.info("moblieText:"+matchertext);
					 JSONObject jsonApp = JsonHelper.toJsonObject("{"+matchertext+"}");
				     JSONArray appList = jsonApp.getJSONArray("appList");
				     for(int i=0;i<appList.size();i++){
				    	 Object re = appList.get(i);
				    	 logger.info("moblieText:"+i+"="+String.valueOf(re));
				    	 esMobileList.add(EsMobileDeviceReordDto.preDto(clientID, String.valueOf(re), current));
				     }
				
				}
				esMobileDeviceRecordService.saveAll(esMobileList);
			}catch(Exception e){
				logger.info("moblieText:",e);
			}
	      
		return ActionResult.success();
	}
	
	/**
	 * IOS采集下载页
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/iosApp/divice/info", produces = Produce.TEXT_HTML)
	public String iosAppDiviceInfo() throws Exception {
		return "iosdevice/iosDiviceIndex";
	}
	
	/**
	 * IOS的设备采集描述文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/iosApp/divice/config", method=RequestMethod.GET, produces = Produce.TEXT_HTML)
	public String iosAppDivice(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		response.setContentType("application/x-apple-aspen-config");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path = System.getProperty("evan.webapp");  
		
		path = BootstrapListener.getSpringContext().getResource("/WEB-INF/views").getURI().getPath();
		
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
	
		String downLoadPath =path+ "/iosdevice/udid.mobileconfig";  //注意不同系统的分隔符
		
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-apple-aspen-config");
			response.setHeader("Content-disposition", "attachment; filename=" + new String("udid.mobileconfig".getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;	


	}
	
	/**
	 * IOS的设备号入库
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/iosApp/divice/addudid", method=RequestMethod.POST, produces = Produce.TEXT_HTML)
	public void addIosDiviceUdid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 response.setContentType("text/html;charset=UTF-8");
		    request.setCharacterEncoding("UTF-8");
		    //获取HTTP请求的输入流
		    InputStream is = request.getInputStream();
		    //已HTTP请求输入流建立一个BufferedReader对象
		    BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		    StringBuilder sb = new StringBuilder();
		 
		    //读取HTTP请求内容
		    String buffer = null;
		    while ((buffer = br.readLine()) != null) {
		         sb.append(buffer);
		    }
		    String content = sb.toString().substring(sb.toString().indexOf("<?xml"), sb.toString().indexOf("</plist>")+8);
		    //content就是接收到的xml字符串
		    Map<String,String> m = new HashMap<>();
		    InputStream in = new ByteArrayInputStream(content.getBytes("UTF-8"));
		    SAXReader reader  = new SAXReader();
		    List<Map<String,String>> list = new ArrayList<>();
		    Map<String,String> map = new HashMap<>();
		    try {
	            //加载文件
	           Document document = reader.read(in);
	            //获取根结点
	            Element rowData = document.getRootElement();
	            //根节点迭代器
	            Iterator it = rowData.elementIterator();
	         
	            while(it.hasNext()){
	                //一级节点
	                Element row = (Element)it.next();
	                //一级节点迭代器
	                Iterator itt = row.elementIterator();
	                while(itt.hasNext()){
	                    Element rowChild = (Element)itt.next();
	                    String key = rowChild.getStringValue();
	                    rowChild = (Element)itt.next();
	                    String value = rowChild.getStringValue();
	                    map.put(key, value);
	                }
	                list.add(map);
	            }

	        }catch (DocumentException ex){
	            ex.printStackTrace();
	        }
		    in.close();
		    String udid = map.get("UDID");
		    if( Tools.isNotNull(udid)){
		    	AppIosDeviceUdidEntity entity = appIosDeviceUdidContract.findByProperty("udid",udid );
			    if(Tools.isNull(entity)){
			    	entity = new AppIosDeviceUdidEntity();
				    entity.setUdid(map.get("UDID"));
				    entity.setProduct(map.get("PRODUCT"));
				    entity.setVersion(map.get("VERSION"));
				    entity.setImei(map.get("IMEI"));
				    entity.setCreate_time(new Date());
				    appIosDeviceUdidContract.insert(entity);
			    }
		    	
		    }else {
		    	 logger.info("springContent:解析UDID为空");
			}
		    logger.info("springContent.json:"+JsonHelper.toJson(list));
			logger.info("springContent.xml:"+content);
		    response.setStatus(301); //301之后iOS设备会自动打开safari浏览器
		   response.setHeader("Location", Const.WEB_SITE+"/web/iosApp/divice/iossucess");
	}
	
	
	/**
	 * IOS采集成功页
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/iosApp/divice/iossucess", produces = Produce.TEXT_HTML)
	public String iosAppDiviceSuccess() throws Exception {
		return "iosdevice/iosSucess";
	}
	
	
	

	
}
