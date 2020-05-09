package com.tigerjoys.shark.miai.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.encry.AESCipher;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.DynamicDto;
import com.tigerjoys.shark.miai.dto.service.DynamicPostDto;
import com.tigerjoys.shark.miai.enums.DynamicReqTypeEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IAppointSiteService;
import com.tigerjoys.shark.miai.service.IConfService;
import com.tigerjoys.shark.miai.service.IDynamicService;
import com.tigerjoys.shark.miai.utils.FileUploadResult;
import com.tigerjoys.shark.miai.utils.Helper;

/**
 * 对动态相关的请求处理
 * @author shiming
 */
@Login
//@TestEncrypt("GICgxQY/4aYVhOBJA0J0XfTuqYMzT1qwE+fsn4Tv5wZ7u9YQjEysmDHPnLl06ct8cGICEJcUT19j4oH17U1Gd8xkB/yH4EyfB0ol4P9cCOpMh0Bz6DhytwhZSCDcBIKPA7VAdteZX1anbNPcqIxlFtGP1t2KIXt3rYmFyuCRrgn8Zk3mBc5IUtScRMXM2LMaJf0JTjNUK+rbmpZ2rSxqQ1qrWLoG/xxWANamX6qFO7GEJb5FMtn9oOzjqKMe8W5idrsXFGdjfYQOypSPuxggbagnSON4hnQYWkknZQqxOdM2aH5HaspEPmNArSd4DMXVBd0x9RbiOqO3eXce+ZQT80Nd6CDNaPl+PclId+3/sM9g7JbJAZG2Hck8KIjUC4drjbQ+kUywpnA8P3T7NVFsrQ0lN6RuCAgkM5fU4/grqEM=")
@Controller
@RequestMapping(value="/api/dynamic", produces=Produce.TEXT_HTML)
public class DynamicController extends BaseController {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IDynamicService dynamicService;
	
	@Autowired
	private IAppointSiteService appointSiteService;
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private IConfService confService;
	
	@RequestMapping(value="/test", produces=Produce.TEXT_HTML)
	public String dynamicTest(Model model) throws Exception {
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		model.addAttribute("website", Const.getWebSite());
		return "dynamic_test";
	}

	@Login
	@RequestMapping(value="/dynamic", produces=Produce.TEXT_HTML)
	public String dynamicIndex(Model model) throws Exception {
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		//平台
		model.addAttribute("ostype", RequestUtils.getCurrent().getHeader().getOs_type());
//		model.addAttribute("encrypt", "2aLQ0DC5fftG9VUKZm76P6nRBq5qUC2ZmfPaxlWKfYvBEHmYaxIxVC0vhSXhv9pZ51ZQBLEkN+9TRufVW9TLqU+bhe2gH6sEcO2IpBMVTS+Wo9Ti0Pxlg7T+hnYPdTZNNfllcfcflRCq2Aeundu+PMcbQMcQ5ZGQ7DvwnTN4V76QR3RCkUyqi6dvFalSs6cRjldP/pgOEr4HwLO9L1oexFcg3/8OsTA/k8J7mvMuHs1ytXL3/g5gLr6Nr1piVlPEo/5SsNJMTMdZNk4kb0PZLyx6SjAEdIQ70VskVSuHVe9zqAibhIhMWFq+sKjx1WJjMxjo0h/0JryViV+NSga3bOJR5uX8oguEgMVBSGJpqbt81/ooCpITPEw6U6OlQTd6qxYSCEP0pJ5gca60SzJgE+9pVMBRFW2zs/w8h6d81I4=");
//		model.addAttribute("website", "/shark-miai-service");
		if(RequestUtils.getCurrent().getHeader().getOs_type() == 2) {
			UserBO user = userAgent.findById(RequestUtils.getCurrent().getUserid());
			//if(Tools.isNotNull(user) && Const.IOS_TEST_MOBILE_ACCOUNT_MAP.containsKey(user.getUsername())) {
			if(Tools.isNotNull(user) && confService.testIOS()) {
				Map<String, Object> outHsmp = appointSiteService.siteIndex();
				model.addAllAttributes(outHsmp);
				return "appointsite/appointsite";
			}
		}
		if(Const.IS_TEST) {
			model.addAttribute("userid", 53621987785900288L);
		} else {
			model.addAttribute("userid", 38379693671514368L);
		}
//		return "dynamic/dynamic";
		return "redirect:/api/roulette/index";
	}
	
	@RequestMapping(value="/mydynamic", produces=Produce.TEXT_HTML)
	public String dynamicMy(Model model) throws Exception {
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
	//	model.addAttribute("encrypt", "GICgxQY/4aYVhOBJA0J0XfTuqYMzT1qwE+fsn4Tv5wZ7u9YQjEysmDHPnLl06ct8cGICEJcUT19j4oH17U1Gd8xkB/yH4EyfB0ol4P9cCOpMh0Bz6DhytwhZSCDcBIKPA7VAdteZX1anbNPcqIxlFtGP1t2KIXt3rYmFyuCRrgn8Zk3mBc5IUtScRMXM2LMaJf0JTjNUK+rbmpZ2rSxqQ1qrWLoG/xxWANamX6qFO7GEJb5FMtn9oOzjqKMe8W5idrsXFGdjfYQOypSPuxggbagnSON4hnQYWkknZQqxOdM2aH5HaspEPmNArSd4DMXVBd0x9RbiOqO3eXce+ZQT80Nd6CDNaPl+PclId+3/sM9g7JbJAZG2Hck8KIjUC4drjbQ+kUywpnA8P3T7NVFsrQ0lN6RuCAgkM5fU4/grqEM=");
//		model.addAttribute("website", "/shark-miai-service");
		return "dynamic/my_dynamic";
	}
	
	@RequestMapping(value="/tadynamic", produces=Produce.TEXT_HTML)
	public String dynamicTat(HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
//		model.addAttribute("website", "/shark-miai-service");
		return "dynamic/ta_dynamic";
	}
	
	@UserClientService("dynamic")
	@NoSign
	@RequestMapping(value = "/publish/picture", produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult publishPictureDynamic(@RequestParam("parameters") String parameters , @RequestParam("pictures") MultipartFile[] pictures) throws Exception {
		String content = null;
		try {
			if(Tools.isNotNull(parameters) && parameters.length() > 0) {
				//在有参数的时候进行解密并获取对应的数据
				parameters = AESCipher.aesDecryptString(parameters);
				logger.info("解密后======================parameters"+parameters);
				if(parameters.length() > 1){
					JSONObject json = JsonHelper.toJsonObject(parameters);
					content = json.getString("content");
					if(Tools.isNotNull(content) && content.length() > 300){
						return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "动态内容不能超过300字符");
					}
				}
			}
		} catch (Exception e) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "参数解析错误");
		}
		
		List<String> picList = new ArrayList<>(pictures.length>9?9:pictures.length);
		for(int i=0; i<pictures.length && i<9; i++) {
			FileUploadResult result = Helper.uploadPicture(pictures[i], "dynamic/picture");
			if(result.getCode() == ErrorCodeEnum.success.getCode()) {
				picList.add(result.getFilePath());
			} else {
				logger.error("上传动态图片失败 code=" + result.getCode()+ ",msg=" + result.getMsg());
			}
		}
		//这个地方是否需要检测所有的图片都上传成功了
		
		//检测是否有需要上传的图片 进行数据插入处理
		if(Tools.isNotNull(picList) && picList.size() > 0) {
			DynamicPostDto dto = new DynamicPostDto();
			dto.setContent(content);
			dto.setType(1);
			return dynamicService.insertDynamic(dto, picList);
		}
		return ActionResult.fail();
	}
	
	@UserClientService("dynamic")
	@NoSign
	@RequestMapping(value = "/publish/video", produces=Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult publishVideoDynamic(@RequestParam("parameters") String parameters , @RequestParam("video") MultipartFile video) throws Exception {
		String content = null;
		try {
			if(Tools.isNotNull(parameters) && parameters.length() > 0) {
				//在有参数的时候进行解密并获取对应的数据
				parameters = AESCipher.aesDecryptString(parameters);
				logger.info("解密后======================parameters"+parameters);
				if(parameters.length() > 1){
					JSONObject json = JsonHelper.toJsonObject(parameters);
					content = json.getString("content");
					if(Tools.isNotNull(content) && content.length() > 300){
						return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "动态内容不能超过300字符");
					}
				}
			}
		} catch (Exception e) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error.getCode(), "参数解析错误");
		}

		//先上传文件到本地的临时文件中
		FileUploadResult uploadResult = Helper.uploadTempFile(video, "avi,mp4", "dynamic/video");
		if(uploadResult.getCode() == ErrorCodeEnum.success.getCode()) {
			String videoFilePath = Const.TEMP_FILE_UPLOAD_DIR + uploadResult.getFilePath();
			File videoFile = new File(videoFilePath);
			File uploadPicture = null;
			
			try {
				if(videoFile.exists()) {
					//首先获取对应的需要上传到又拍云的文件对象  同时检测对应的文件是否存在
					logger.info("上传又拍云的文件路径======================" + videoFilePath);
					boolean isUpload = upYunCloudStorage.writeFile(uploadResult.getFilePath(), videoFile, true);
					if(!isUpload){
						//处理上传文件到又拍云出现了错误
						return ActionResult.fail(ErrorCodeEnum.video_upload_error);
					}
					//上传完成后进行视频的截图调用处理
					//配置一个进行上传图片的路径和名称
					String base = Helper.getUploadFilePath("dynamic/video/snap");
					String savePath = base + Helper.getUploadFileName("jpg");
					logger.info("上传又拍云的视频截图路径======================" + savePath);	
					boolean isSnapshot = upYunCloudStorage.mediaSnapshot(uploadResult.getFilePath(), savePath, "00:00:00");
					if(!isSnapshot){
						//处理上传截图失败的错误
						return ActionResult.fail(ErrorCodeEnum.video_upload_error);
					}
					//在自己的服务器上创建对应的截图文件
					//首先在本地创建对应
					File uploaddir= new File(Const.TEMP_FILE_UPLOAD_DIR + base);
					if (!uploaddir.exists()){
						uploaddir.mkdirs();
					}
					uploadPicture = new File(Const.TEMP_FILE_UPLOAD_DIR + savePath);
					boolean isUploadPicture =  upYunCloudStorage.readFile(savePath, uploadPicture);
					if(!isUploadPicture){
						//处理上传截图失败的错误
						return ActionResult.fail(ErrorCodeEnum.video_upload_error);
					}
					//将视频截图上传到minio服务器
					FileUploadResult fileResult = Helper.uploadFile(new FileInputStream(uploadPicture), savePath);
					if(fileResult.getCode() != ErrorCodeEnum.success.getCode()) {
						return ActionResult.fail(ErrorCodeEnum.video_upload_error.getCode(), "上传视频截图失败");
					}
					//最后将视频上传到minio服务器上
					Helper.uploadFile(new FileInputStream(videoFile), uploadResult.getFilePath());
					
					List<String> paths = new ArrayList<>();
					//获取视频对应的前景图的路径	
					paths.add(savePath);
					paths.add(uploadResult.getFilePath());
					//进行数据插入处理
					DynamicPostDto dto = new DynamicPostDto();
					dto.setContent(content);
					dto.setType(2);
					dynamicService.insertDynamic(dto, paths);
					return ActionResult.success();
				} else {
					logger.warn("视频文件不存在,path:" + videoFile.getAbsolutePath());
				}
			} catch (Exception e) {
				logger.error(e.getMessage() , e);
			} finally {
				//删除视频文件
				videoFile.delete();
				//删除截图文件
				if(uploadPicture != null && uploadPicture.exists()) {
					uploadPicture.delete();
				}
			}
		}
		
		return ActionResult.fail(ErrorCodeEnum.video_upload_error);
	}
	
	@UserClientService("dynamic")
	@RequestMapping(value = "/tab")
	@ResponseBody
	public ActionResult getDynamicTabIndex(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//获取对应的时间戳和查询类型字段
		JSONObject obj = JsonHelper.toJsonObject(body);
		int pagesize = obj.getIntValue("pagesize");
		if(pagesize <= 0) {
			pagesize = 10;
		}
		return dynamicService.getDynamicTabList(userId, pagesize);
	}
	
	//展示对应用户的动态列表   
	@UserClientService("dynamic")
	@RequestMapping(value = "/list", produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult getDynamicList(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		logger.info("dynamic 效验用户id======================" + userId);	
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
//		logger.info("===== userId:" + userId);
//		long userId = 1468676853399808l;
		//获取对应的时间戳和查询类型字段
		JSONObject obj = JsonHelper.toJsonObject(body);
		String stamp = null;
		int type = obj.getIntValue("type");
		if(Tools.isNotNull(obj.getString("stamp")) && obj.getString("stamp").length() > 2) {
			stamp = obj.getString("stamp");
		}
		int pagesize = obj.getIntValue("pagesize");
		long otherid = obj.getLongValue("userid");
//		logger.info("===== type:" + type + "==== pagesize:" + pagesize + "====otherid:" + otherid);
		
//		int type = 2;
//		long stamp = 0;
//		int pagesize = 10;
//		long otherid = 1468676853399808l;
//		logger.info("===== type:" + type + "==== pagesize:" + pagesize + "====otherid:" + otherid);
		
		//处理没有填写或者填写不正确个数的页数数据的处理
		if(pagesize <= 0) {
			pagesize = 10;
		}
		logger.info("dynamic 效验类型======================" + type);	
		//检测是否填入正确的类型  
		if(type <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		
		DynamicReqTypeEnum reqType = DynamicReqTypeEnum.getByCode(type);
		if(Tools.isNull(reqType)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		List<DynamicDto> data = new ArrayList<>();
		//对查看他人动态的特殊判断处理
		if(reqType.equals(DynamicReqTypeEnum.ta)) {
			logger.info("dynamic 效验他的id类型======================" + otherid);
			if(otherid <= 0) {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
			data = dynamicService.getDynamicListData(otherid, userId, pagesize, stamp, type);
		} else {
			//最新动态 关注人的动态 我的动态  走这个地方
			data = dynamicService.getDynamicListData(userId, userId, pagesize, stamp, type);
		}
		boolean isNext = false;
		stamp = null;
		if(Tools.isNotNull(data) && data.size() > 0) {
			if(data.size() > pagesize) {
				data = data.subList(0, pagesize);
				isNext = true;
				stamp = data.get(data.size() - 1).getCreate_time();
			}
		}
		return ActionResult.success(data, stamp, isNext);
	}
	
	//增加动态的点赞数的处理请求
	@UserClientService("dynamic")
	@RequestMapping(value = "/addfavor", produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult addDynamicFavor(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
//		long userId = 1468676853399808l;
		JSONObject obj = JsonHelper.toJsonObject(body);
		long dynamicId = obj.getLongValue("dynamicId");
		if(dynamicId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//调用业务层进行业务处理
		return dynamicService.addDynamicFavor(userId, dynamicId);
	}
	
	//增加动态浏览数量的处理请求
	@UserClientService("dynamic")
	@RequestMapping(value = "/addaudience", produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult addDynamicAudience(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
//		long userId = 1468676853399808l;
		JSONObject obj = JsonHelper.toJsonObject(body);
		long dynamicId = obj.getLongValue("dynamicId");
		if(dynamicId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//调用业务层进行业务处理
		return dynamicService.addDynamicAudience(userId, dynamicId);
	}
	
	//删除对应的动态处理请求
	@UserClientService("dynamic")
	@RequestMapping(value = "/delete", produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult deleteDynamicbyId(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
//		long userId = 1468676853399808l;
		JSONObject obj = JsonHelper.toJsonObject(body);
		long dynamicId = obj.getLongValue("dynamicId");
		if(dynamicId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//调用业务层进行业务处理
		return dynamicService.deleteDynamicbyId(userId, dynamicId);
	}
	
}
