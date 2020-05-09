package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.StreamUtils;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoCheckedLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserPhotoCheckedLogMapper;

/**
 * 数据库中  用户付费查看用户信息记录[t_user_photo_checked_log]表 接口实现类
 * @author chengang
 * @Date 2020-02-24 16:47:47
 *
 */
@Repository
public class UserPhotoCheckedLogContractImpl extends AbstractBaseContract<UserPhotoCheckedLogEntity , UserPhotoCheckedLogMapper> implements IUserPhotoCheckedLogContract {

	@Override
	public UserPhotoCheckedLogEntity lockByUserId(long userId, long photoId, int type) {
		if(userId <= 0 || photoId <= 0) {
			throw new IllegalArgumentException("userId or photoId is error");
		}
		return mapper.lockByUserId(userId, photoId, type);
	}

	@Override
	public Map<Long, UserPhotoCheckedLogEntity> getUserPhotoCheckedLogs(long userId, List<Long> photoIdList, int type) throws Exception {
		if(Tools.isNull(photoIdList)) {
			return Collections.emptyMap();
		}
		
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		pageModel.addQuery(Restrictions.eq("type", type));
		pageModel.addQuery(Restrictions.in("photo_id", photoIdList));
		
		List<UserPhotoCheckedLogEntity> list = mapper.load(pageModel);
		if(Tools.isNull(list)) {
			return Collections.emptyMap();
		}
		
		return StreamUtils.toMap(list, UserPhotoCheckedLogEntity::getPhoto_id);
	}
	
}
