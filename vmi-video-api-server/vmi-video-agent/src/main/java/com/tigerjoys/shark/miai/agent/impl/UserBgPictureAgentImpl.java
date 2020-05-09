package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.shark.miai.agent.IUserBgPictureAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBgPictureBO;
import com.tigerjoys.shark.miai.inter.contract.IUserBgPictureContract;
import com.tigerjoys.shark.miai.inter.entity.UserBgPictureEntity;

/**
 * 用户视频认证代理服务实现类
 * @author chengang
 *
 */
@Service
public class UserBgPictureAgentImpl implements IUserBgPictureAgent {
	
	@Autowired
	private IUserBgPictureContract userBgPictureContract;
	
	@Override
	public UserBgPictureBO findById(long id) throws Exception {
		if(id <= 0) return null;
		
		return transferUserBgPicture(userBgPictureContract.findById(id));
	}

	/**
	 * 将数据库用户视频认证对象转换为UserVideoAuthEntity对象
	 * @param auth - UserVideoAuthBO
	 * @return UserVideoAuthBO
	 */
	private UserBgPictureBO transferUserBgPicture(UserBgPictureEntity bg) {
		if(bg == null) return null;
		
		UserBgPictureBO dto = new UserBgPictureBO();
		dto.setId(bg.getId());
		dto.setUrl(bg.getUrl());
		return dto;
	}

	@Override
	public List<UserBgPictureBO> getBgPicList() throws Exception {
		List<UserBgPictureBO> boList = new ArrayList<UserBgPictureBO>();
		PageModel pageModel = PageModel.getLimitModel(0, 12);//最多12张 多的不取
		List<UserBgPictureEntity> list = userBgPictureContract.load(pageModel);
		if (Tools.isNull(list)) return null;
		for (UserBgPictureEntity userBgPictureEntity : list) {
			UserBgPictureBO bo = new UserBgPictureBO();
			bo.setId(userBgPictureEntity.getId());
			bo.setUrl(userBgPictureEntity.getUrl());
			boList.add(bo);
		}
		return boList;
	}

	@Override
	public String getBgPic() throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 12);//最多12张 多的不取
		Long l = userBgPictureContract.count(pageModel);
		if (l == 0) return null;
		List<UserBgPictureEntity> list = userBgPictureContract.load(pageModel);
		int b=(int)(Math.random()*l-1);
		return list.get(b).getUrl();
	}
	
	

}
