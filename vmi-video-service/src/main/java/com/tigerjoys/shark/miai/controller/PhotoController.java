package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.dto.service.PhotoPictureDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.service.IPhotoService;
import com.tigerjoys.shark.miai.utils.FileUploadResult;
import com.tigerjoys.shark.miai.utils.Helper;

/**
 * 处理相册相关的请求处理
 * @author shiming
 */
@Login
@Controller
@RequestMapping(value="/api/photo" , method=RequestMethod.POST, produces=Produce.TEXT_ENCODE)
public class PhotoController extends BaseController {
	
	@Autowired
	private IPhotoService photoService;

	@UserClientService("photo")
	@RequestMapping(value = "/add")
	@NoSign
	@ResponseBody
	public ActionResult addPhoto(@RequestParam("pictures") MultipartFile[] pictures) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		if(pictures == null || pictures.length == 0) {
			return ActionResult.fail(ErrorCodeEnum.photo_upload_null_error);
		}
		List<String> picList = new ArrayList<>(pictures.length>9?9:pictures.length);
		for(int i=0; i<pictures.length && i<9; i++) {
			FileUploadResult result = Helper.uploadPicture(pictures[i], "photo/picture");
			if(result.getCode() == ErrorCodeEnum.success.getCode()) {
				picList.add(result.getFilePath());
			} else {
				logger.error("相册图片上传失败 code=" + result.getCode()+ ",msg=" + result.getMsg());
			}
		}	
		if(Tools.isNull(picList)) {
			return ActionResult.fail(ErrorCodeEnum.photo_upload_error);
		}
		return photoService.addPhotoPicture(userId, picList);
	}
	
	@UserClientService("photo")
	@RequestMapping(value = "/list")
	@ResponseBody
	public ActionResult photoList(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		//获取对应的时间戳和查询类型字段
		JSONObject obj = JsonHelper.toJsonObject(body);
		String stamp = obj.getString("stamp");
		long index = 0L;
		if(Tools.isNotNull(stamp)){
			index = Long.parseLong(stamp);
		}
		int pagesize = obj.getIntValue("pagesize");
		
		//处理iso一次请求所有数据的处理
		if(pagesize != 1000) {
			if(pagesize == 0){
				pagesize = 21;
			}
			//首次加载时 使得数据能够按照3个填充满数据
			if(index == 0){
				pagesize = 20;
			}	
		}
		
		List<PhotoPictureDto> url = photoService.getPhotoList(userId, pagesize, index);
		boolean isNext = false;
		stamp = null;
		if(Tools.isNotNull(url) && url.size() > 0) {
			int total = url.size();
			if(total > pagesize) {
				isNext = true;
				url = url.subList(0, pagesize);
			}
			stamp = url.get(url.size() - 1).getPhotoId().toString();
		}
		return ActionResult.success(url, stamp, isNext);
	}
	
	@UserClientService("photo")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ActionResult deletePhoto(@RequestBody String body) throws Exception {
		//首先进行检测参数是否正确
		long userId = RequestUtils.getCurrent().getUserid();
		if(userId <= 0) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		JSONObject obj = JsonHelper.toJsonObject(body);
		JSONArray arr = obj.getJSONArray("photoId");
		if(Tools.isNull(arr)) {
			return ActionResult.success();
		}
		//调用业务层进行业务处理
		return photoService.deletePhotobyId(userId, arr);
	}
	
}
