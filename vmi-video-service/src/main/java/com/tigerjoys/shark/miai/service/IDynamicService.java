package com.tigerjoys.shark.miai.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.DynamicDto;
import com.tigerjoys.shark.miai.dto.service.DynamicPostDto;

/**
 * 处理动态和相册相关的服务接口
 * @author shiming
 */
public interface IDynamicService {
	
	public ActionResult insertDynamic(DynamicPostDto dto, byte[] bytes) throws Exception;
	
	public ActionResult insertDynamic(DynamicPostDto dto, MultipartFile file) throws Exception;
	
	public ActionResult insertDynamic(DynamicPostDto dto, List<String> paths) throws Exception; 
	
	public ActionResult getDynamicTabList(long userId, int pagesize) throws Exception;
	
	public List<DynamicDto> getDynamicListData(long userId, long favorId, int pagesize, String stamp, int type) throws Exception;
	
	public ActionResult addDynamicAudience(long userId, long dynamicId) throws Exception;
	
	public ActionResult addDynamicFavor(long userId, long dynamicId) throws Exception;

	public ActionResult deleteDynamicbyId(long userId, long dynamicId) throws Exception;
	
	public List<String> getTopPhoto(long userId) throws Exception;
	
	public List<String> getTopDynamic(long userId) throws Exception;
	
}
