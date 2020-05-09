package com.tigerjoys.shark.miai.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.INeteasePictureCheck;
import com.tigerjoys.shark.miai.utils.FileUploadResult;
import com.tigerjoys.shark.miai.utils.Helper;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 监控接口
 * @author chengang
 *
 */
@Controller
@RequestMapping(value = "/api/video", method = RequestMethod.POST, produces = Produce.TEXT_ENCODE)
public class VideoCheckUploadController extends BaseController {
	
	/*
	@NoLogin
	@NoSign
	@RequestMapping(value = "/checkUpload",method=RequestMethod.POST,produces=Produce.TEXT_ENCODE)
	public @ResponseBody ActionResult videoUpload(@RequestParam("parameters") String parameters,
			@RequestParam("file") MultipartFile file) throws Exception {
		String parametersStr = AESCipher.aesDecryptString(parameters);
		JSONObject json = JsonHelper.toJsonObject(parametersStr);
		long userId = json.getLongValue("userId");
		long otherId = json.getLongValue("otherId");
		long serialNum = json.getLongValue("serialNum");
		logger.info("checkUpload_parame:userId="+userId+"otherId:"+otherId+"serialNum:"+serialNum);
		//将视频截图上传到minio服务器
		//FileUploadResult fileResult = Helper.uploadLocatFile(file, "jpg,jpeg,gif,png,bmp","video/check");
		//logger.info("checkUpload_info:"+parametersStr+"result_code:"+fileResult.getCode()+"result_msg:"+fileResult.getMsg());
		return ActionResult.success();
	}
	*/
	
	@Autowired
	private INeteasePictureCheck neteasePictureCheck;
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	@NoLogin
	@NoSign
	@RequestMapping(value = "/checkUpload",method=RequestMethod.POST,produces=Produce.TEXT_ENCODE)
	public @ResponseBody ActionResult videoUpload(@RequestParam("parameters") String parameters, @RequestParam("file") MultipartFile file) throws Exception {
		//第一步首先进行图片文件和相关参数的上传操作处理
		String parametersStr = AESCipher.aesDecryptString(parameters);
		JSONObject json = JsonHelper.toJsonObject(parametersStr);
		long userId = json.getLongValue("userId");
		long otherId = json.getLongValue("otherId");
		long serialNum = json.getLongValue("serialNum");
		logger.info("checkUpload_parame:userId="+userId+"otherId:"+otherId+"serialNum:"+serialNum);
		FileUploadResult fileResult = checkUploadFile(file, "jpg,jpeg,gif,png,bmp","video/check");
		logger.info("checkUpload_info:"+parametersStr+"result_code:"+fileResult.getCode()+"result_msg:"+fileResult.getMsg());
		if(fileResult.getCode() == 0) {
			boolean isUpload = upYunCloudStorage.writeFile(fileResult.getFilePath(), file.getInputStream(), true);
			if(!isUpload) {
				//处理上传文件到又拍云出现了错误
				logger.error("图片上传又拍云出现了问题");
				return ActionResult.fail(ErrorCodeEnum.audio_upload_error);
			} else {
				neteasePictureCheck.checkAndAction(userId, otherId, serialNum, ServiceHelper.getCdnVideo(fileResult.getFilePath()));
			}
		} else {
			logger.error("图片上传失败了");
		}
		return ActionResult.success();
	}
	
	/**
	 * 上传临时文件
	 */
	public static FileUploadResult checkUploadFile(MultipartFile file , String fileLimit, String directory) {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传图片文件为空");
		}
		if(file.getSize()>1024*1024*50) {
			return FileUploadResult.getFileUploadDto(80005, "上传图片文件不能大于50M");
		}
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getOriginalFilename();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到文件格式");                                                                    
		}
		fileExt = fileExt.toLowerCase();
		if (fileLimit != null) {
			if(fileLimit.indexOf(fileExt)==-1){
				return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传"+fileLimit);
			}
		}
		//获得一个上传目录
		String dirPath = Helper.getUploadFilePath(directory);
		//上传图片路径
		String path = dirPath+Helper.getUploadFileName(fileExt);
		return FileUploadResult.getFileUploadDto(ErrorCodeEnum.success.getCode(), "成功",path);
	}
	
	/*
	@NoLogin
	@NoSign
	@RequestMapping(value = "/checkUpload",method=RequestMethod.POST,produces=Produce.TEXT_ENCODE)
	public @ResponseBody ActionResult videoUpload(@RequestParam("parameters") String parameters, @RequestParam("file") MultipartFile file) throws Exception {
		//第一步首先进行图片文件和相关参数的上传操作处理
		String parametersStr = AESCipher.aesDecryptString(parameters);
		JSONObject json = JsonHelper.toJsonObject(parametersStr);
		long userId = json.getLongValue("userId");
		long otherId = json.getLongValue("otherId");
		long serialNum = json.getLongValue("serialNum");
		logger.info("checkUpload_parame:userId="+userId+"otherId:"+otherId+"serialNum:"+serialNum);
		FileUploadResult fileResult = uploadLocatFile(file, "jpg,jpeg,gif,png,bmp","video/check");
		logger.info("checkUpload_info:"+parametersStr+"result_code:"+fileResult.getCode()+"result_msg:"+fileResult.getMsg());
		if(fileResult.getCode() == 0) {
			//获得一个上传目录
			String dirPath = Helper.getUploadFilePath("video/check");
			String abs = Const.FILE_UPLOAD_DIR + dirPath;
			File uploaddir = new File(abs);
			if (!uploaddir.exists()) {
				uploaddir.mkdirs();
			}	
			//上传图片的路径
			String name = Helper.getUploadFileName(fileResult.getFilePath());
			String path = abs + name;
			File uploadFile = new File(path);
			//循环进行数据写入文件处理    首先将文件上传到本地服务器上
			try (InputStream is = file.getInputStream();FileOutputStream out = new FileOutputStream(uploadFile)) {
				FileCopyUtils.copy(is, out);
				//如果是测试环境  就进行上传又拍云处理
				if(Const.is_test) {
					boolean isUpload = upYunCloudStorage.writeFile(dirPath+name, uploadFile, true);
					if(isUpload) {
						//第二部  启动对应的线程来完成对应的图片监黄业务和相关流程
						neteasePictureCheck.checkAndAction(userId, otherId, serialNum, ServiceHelper.getCdnVideo(dirPath+name));
					} else {
						logger.error("上传又拍云失败了");
					}
				}
			} catch (IOException e) {
				logger.error("处理图片出现了问题");
				return ActionResult.fail();
			}
		} else {
			logger.error("图片上传失败了");
		}
		return ActionResult.success();
	}
	*/
	
	/*
	private FileUploadResult uploadLocatFile(MultipartFile file , String fileLimit, String directory) {
		if(file == null || file.isEmpty()) {
			return FileUploadResult.getFileUploadDto(80005, "上传图片为空");
		}
		//获得文件格式
		String fileExt = null;
		String picFileName = file.getOriginalFilename();
		if(picFileName.indexOf(".")>-1){
			fileExt = picFileName.substring(picFileName.lastIndexOf(".")+1).toLowerCase();
		}
		if(Tools.isNull(fileExt)){
			return FileUploadResult.getFileUploadDto(80012, "没有获得到文件格式");                                                                    
		}
		fileExt = fileExt.toLowerCase();
		if (fileLimit != null) {
			if(fileLimit.indexOf(fileExt)==-1){
				return FileUploadResult.getFileUploadDto(80102, "文件格式只能上传"+fileLimit);
			}
		}
		return FileUploadResult.getFileUploadDto(0, "成功", fileExt);
	}
	*/
	
	
	/**
	 * 手机数据采集
	 * 
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@FilterHeader
	@RequestMapping(value = "/data/moblie", method=RequestMethod.POST, produces = Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult moblieDeviceInfo(@RequestBody String body) throws Exception {
		logger.info("checkUpload_info:moblieDeviceInfo="+body);
		return ActionResult.success();
	}
	
	
}
