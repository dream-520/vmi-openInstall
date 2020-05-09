package com.tigerjoys.shark.miai.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.PhotoPictureDto;

/**
 * 处理相册相关的接口服务
 * @author shiming
 *
 */
public interface IPhotoService {
	
	public ActionResult addPhotoPicture(long userId, List<String> paths) throws Exception;
	
	public List<PhotoPictureDto> getPhotoList(long userId, int pagesize, long stamp) throws Exception;

	public ActionResult deletePhotobyId(long userId, JSONArray arr) throws Exception;
	
}
