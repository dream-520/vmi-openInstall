package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ITaskAgent;
import com.tigerjoys.shark.miai.agent.enums.TaskCategoryEnum;
import com.tigerjoys.shark.miai.dto.service.PhotoPictureDto;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.shark.miai.service.IPhotoService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

@Service
public class PhotoServiceImpl implements IPhotoService {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Autowired
	private ITaskAgent taskAgent;

	@Override
	public ActionResult addPhotoPicture(long userId, List<String> paths) throws Exception {
		//这个地方需要将对应的图片路径进行翻转处理   先获取的后加入到数据库中  这样才能保证图片的顺序性
		Collections.reverse(paths);
		//配置需要插入的图片数据
		List<UserPhotoResourceEntity> entities = new ArrayList<>();
		Date now = new Date();
		for (String path : paths) {
			UserPhotoResourceEntity entity = new UserPhotoResourceEntity();
			entity.setUserid(userId);
			entity.setPath(path);
			//设置为对应的审核状态   2   2 为待审核状态  1为通过状态
			entity.setState(2);
			entity.setCreate_time(now);
			entity.setUpdate_time(now);
			entity.setObscure(0);
			entities.add(entity);
		}
		userPhotoResourceContract.insertAll(entities);
		//完成发送相册图片的任务
		try {
			taskAgent.finishTask(userId, TaskCategoryEnum.PHOTOS);
		} catch (Exception e) {
			
		}
		return ActionResult.success();
//		//这里上传图片成功之后 需要再次获取一遍最新18的数据
//		int pagesize = 18;
//		List<PhotoPictureDto> url = getPhotoList(userId, pagesize, 0);
//		boolean isNext = false;
//		String stamp = null;
//		if(Tools.isNotNull(url) && url.size() > 0) {
//			int total = url.size();
//			if(total > pagesize) {
//				isNext = true;
//				url = url.subList(0, pagesize);
//			}
//			stamp = url.get(url.size() - 1).getPhotoId().toString();
//		}
//		return ActionResult.success(url, stamp, isNext);
	}
	
	@Override
	public List<PhotoPictureDto> getPhotoList(long userId, int pagesize, long stamp) throws Exception {
		PageModel pageModel = new PageModel();
		pageModel.setPageSize(pagesize + 1);
		pageModel.addQuery(Restrictions.eq("userid", userId));
		//pageModel.addQuery(Restrictions.eq("state", 1));
		pageModel.addQuery(Restrictions.in("state", 1,2));
		if(stamp > 0) {
			pageModel.addQuery(Restrictions.lt("id", stamp));
		}
		pageModel.desc("id");
		List<UserPhotoResourceEntity> list = userPhotoResourceContract.load(pageModel);
		List<PhotoPictureDto> paths = new ArrayList<>();
		if(Tools.isNotNull(list) && list.size() > 0) {
			//进行数据拼接处理
			PhotoPictureDto dto = null;
			for (UserPhotoResourceEntity entity : list) {
				dto = new PhotoPictureDto();
				dto.setPhotoId(entity.getId());
				dto.setBigUrl(ServiceHelper.getCdnPhoto(entity.getPath()));
				//处理获取对应的小图
				dto.setSmallUrl(ServiceHelper.getCdnPhoto(entity.getPath(), ServiceHelper.COMMON_150_TAG));
				if(entity.getState().intValue() == 2) {
					dto.setVerifyText("审核中");
				}
				paths.add(dto);
			}
		}
		return paths;
	}

	@Override
	public ActionResult deletePhotobyId(long userId, JSONArray arr) throws Exception {
		for(int i=0, size=arr.size(); i<size; i++) {
			long id = arr.getLongValue(i);
			UserPhotoResourceEntity entity = userPhotoResourceContract.findById(id);
			if(Tools.isNotNull(entity) && entity.getUserid().equals(userId)){
				entity.setState(0);
				entity.setUpdate_time(new Date());
				userPhotoResourceContract.update(entity);
			}
		}
		//这里返回修改动态成功的处理结果
		return ActionResult.success();
	}
	
}
