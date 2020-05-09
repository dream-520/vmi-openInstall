package com.tigerjoys.shark.miai.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.shark.miai.common.enums.ShortVideoStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Lists;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.enums.PhotoCheckedLogTypeEnum;
import com.tigerjoys.shark.miai.dto.service.UserPhotoDto;
import com.tigerjoys.shark.miai.dto.service.UserVideoDto;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoCheckedLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoResourceContract;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoResourceEntity;
import com.tigerjoys.shark.miai.service.IUserPhotoService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 用户相册接口实现类，6.0.0支持此接口
 * @author chengang
 *
 */
@Service
public class UserPhotoServiceImpl implements IUserPhotoService {
	
	private static int PAGESIZE = 10;
	
	@Autowired
	private IUserPhotoResourceContract userPhotoResourceContract;
	
	@Autowired
	private IUserPhotoCheckedLogContract userPhotoCheckedLogContract;
	
	@Autowired
	private IShortVideoContract shortVideoContract;

	@Override
	public ActionResult userPhotoList(long userId, long anchorId, String stamp) throws Exception {
		String lastId = null;
		boolean next = false;
		List<UserPhotoDto> dtoList = getUserPhotoList(userId, anchorId, PAGESIZE, stamp);
		
		if(Tools.isNotNull(dtoList)) {
			if(dtoList.size() > PAGESIZE) {
				next = true;
				dtoList = dtoList.subList(0, PAGESIZE);
			}
			lastId = dtoList.get(dtoList.size() - 1).getPhotoId();
		}
		
		return ActionResult.success(dtoList,Tools.formatString(lastId),next);
	}
	
	@Override
	public List<UserPhotoDto> getUserPhotoList(long userId, long anchorId, int pageSize, String stamp) throws Exception {
		Long lastId = 0L;
		List<UserPhotoDto> dtoList = Lists.newArrayListWithCapacity(pageSize);
		
		if (Tools.isNotNull(stamp)) {
			lastId = Long.parseLong(stamp);
		}
		boolean others = userId != anchorId;
		
		PageModel pageModel = PageModel.getLimitModel(0, PAGESIZE+1);
		pageModel.addQuery(Restrictions.eq("userid", anchorId));
		pageModel.addQuery(Restrictions.eq("state", 1));
		pageModel.desc("create_time");
		if(lastId > 0){
			pageModel.addQuery(Restrictions.lt("id", lastId));
		}
		
		List<UserPhotoResourceEntity> photoList = userPhotoResourceContract.load(pageModel);
		if (Tools.isNotNull(photoList)) {
			//获取所有的私密作品ID
			List<Long> photoIdList = photoList.stream().filter(p -> p.getObscure() == 1).map(UserPhotoResourceEntity::getId).collect(Collectors.toList());
			Map<Long, UserPhotoCheckedLogEntity> checkedLogMap = userPhotoCheckedLogContract.getUserPhotoCheckedLogs(userId, photoIdList, PhotoCheckedLogTypeEnum.PHOTO.getCode());
			
			for(UserPhotoResourceEntity p : photoList) {
				UserPhotoDto dto = new UserPhotoDto();
				dto.setPhotoId(String.valueOf(p.getId()));
				dto.setObscure(Tools.intValue(p.getObscure()));
				dto.setCostDiamond(Const.USER_PHOTO_PRIVACY_DIAMOND);
				//私密视频
				if(Tools.intValue(p.getObscure()) == 1) {
					//自己查看或者已购买
					if(!others || checkedLogMap.get(p.getId()) != null) {
						dto.setBuy(1);
					} else {
						dto.setBuy(0);
					}
					dto.setPath(ServiceHelper.getCdnPhoto(p.getPath()));
					dto.setObscurePath(ServiceHelper.getCdnPhoto(p.getObscure_path()));
				} else {
					dto.setBuy(0);
					dto.setPath(ServiceHelper.getCdnPhoto(p.getPath()));
				}
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	
	@Override
	public ActionResult userVideoList(long userId, long anchorId, String stamp) throws Exception {
		String lastId = null;
		boolean next = false;
		List<UserVideoDto> dtoList = getUserVideoList(userId, anchorId, PAGESIZE, stamp);
		
		if(Tools.isNotNull(dtoList)) {
			if(dtoList.size() > PAGESIZE) {
				next = true;
				dtoList = dtoList.subList(0, PAGESIZE);
			}
			lastId = dtoList.get(dtoList.size() - 1).getVideoId();
		}
		
		return ActionResult.success(dtoList,Tools.formatString(lastId),next);
	}
	
	@Override
	public List<UserVideoDto> getUserVideoList(long userId, long anchorId, int pageSize, String stamp) throws Exception {
		Long lastId = 0L;
		List<UserVideoDto> dtoList = Lists.newArrayListWithCapacity(PAGESIZE);
		
		if (Tools.isNotNull(stamp)) {
			lastId = Long.parseLong(stamp);
		}
		boolean others = userId != anchorId;
		
		PageModel pageModel = PageModel.getLimitModel(0, PAGESIZE+1);
		pageModel.addQuery(Restrictions.eq("userid", anchorId));
		pageModel.addQuery(Restrictions.eq("status",ShortVideoStatusEnum.online.getCode()));
		pageModel.desc("id");
		if(lastId > 0){
			pageModel.addQuery(Restrictions.lt("id", lastId));
		}
		
		List<ShortVideoEntity> shortVideoList = shortVideoContract.load(pageModel);
		if (Tools.isNotNull(shortVideoList)) {
			//获取所有的私密作品ID
			List<Long> photoIdList = shortVideoList.stream().filter(p -> p.getObscure() == 1).map(ShortVideoEntity::getId).collect(Collectors.toList());
			Map<Long, UserPhotoCheckedLogEntity> checkedLogMap = userPhotoCheckedLogContract.getUserPhotoCheckedLogs(userId, photoIdList, PhotoCheckedLogTypeEnum.VIDEO.getCode());
			
			for(ShortVideoEntity p : shortVideoList) {
				UserVideoDto dto = new UserVideoDto();
				dto.setVideoId(String.valueOf(p.getId()));
				dto.setObscure(Tools.intValue(p.getObscure()));
				dto.setVideoPath(ServiceHelper.getCdnVideo(p.getVideo_path()));
				dto.setCostDiamond(Const.USER_VIDEO_PRIVACY_DIAMOND);
				//私密视频
				if(Tools.intValue(p.getObscure()) == 1) {
					//自己查看或者已购买
					if(!others || checkedLogMap.get(p.getId()) != null) {
						dto.setBuy(1);
					} else {
						dto.setBuy(0);
					}
					dto.setVideoPhoto(ServiceHelper.getCdnPhoto(p.getVideo_photo()));
					dto.setObscurePath(ServiceHelper.getCdnPhoto(p.getObscure_path()));
				} else {
					dto.setBuy(0);
					dto.setVideoPhoto(ServiceHelper.getCdnPhoto(p.getVideo_photo()));
				}
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}

}
