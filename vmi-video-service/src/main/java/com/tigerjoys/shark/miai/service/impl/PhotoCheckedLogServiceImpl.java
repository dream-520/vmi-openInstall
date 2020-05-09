package com.tigerjoys.shark.miai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.PhotoCheckedLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserPhotoCheckedLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.UserPhotoCheckedLogEntity;
import com.tigerjoys.shark.miai.service.IPhotoCheckedLogService;

/**
 * 作品购买服务接口实现类
 * @author chengang
 *
 */
@Service
public class PhotoCheckedLogServiceImpl implements IPhotoCheckedLogService {
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserPhotoCheckedLogContract userPhotoCheckedLogContract;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> pay(long userId, AnchorOnlineEntity anchor, long photoId, int diamond, PhotoCheckedLogTypeEnum type) throws Exception {
		if(diamond < 0) {
			throw new IllegalArgumentException("diamond is error");
		}
		
		UserPhotoCheckedLogEntity userPhotoCheckedLogEntity = userPhotoCheckedLogContract.lockByUserId(userId, photoId, type.getCode());
		if(userPhotoCheckedLogEntity != null) {
			return DiamondResultDto.success();
		}
		
		DiamondResultDto<Long> result;
		if(diamond > 0) {
			UserDiamondAccountLogTypeEnum logType = type.getAccountLogType();
			
			result = userDiamondAgent.changeDiamondAccount(userId, diamond, null, logType.getCode(), 0, null, userId+"#"+photoId, logType.getDesc());
		} else {
			result = DiamondResultDto.success();
		}
		
		if(result.getCode() == 0) {
			Date currDate = new Date();
			userPhotoCheckedLogEntity = new UserPhotoCheckedLogEntity();
			userPhotoCheckedLogEntity.setAnchor_id(anchor.getUserid());
			userPhotoCheckedLogEntity.setCreate_time(currDate);
			userPhotoCheckedLogEntity.setDiamond(diamond);
			userPhotoCheckedLogEntity.setPhoto_id(photoId);
			userPhotoCheckedLogEntity.setType(type.getCode());
			userPhotoCheckedLogEntity.setUpdate_time(currDate);
			userPhotoCheckedLogEntity.setUser_id(userId);
			userPhotoCheckedLogContract.insert(userPhotoCheckedLogEntity);
			
			//添加主播收益
			if(diamond > 0) {
				//计算主播的收益
				float ratio = anchor.getRatio();
				if(ratio <= 0)
					ratio = 40;
				int amount = Math.round(diamond * (anchor.getRatio() / 100));
				if(amount > 0) {
					UserIncomeAccountLogTypeEnum incomeAccountLogtype = type.getIncomeLogType();
					
					userIncomeAgent.changeIncomeAccount(anchor.getUserid(), amount, 1, incomeAccountLogtype, String.valueOf(photoId), incomeAccountLogtype.getDesc());
				}
			}
		}
		
		return result;
	}

	@Override
	public UserPhotoCheckedLogEntity getUserPhotoCheckedLog(long userId, long photoId, PhotoCheckedLogTypeEnum type) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		pageModel.addQuery(Restrictions.eq("photo_id", photoId));
		pageModel.addQuery(Restrictions.eq("type", type.getCode()));
		
		List<UserPhotoCheckedLogEntity> list = userPhotoCheckedLogContract.load(pageModel);
		
		return Tools.isNull(list)?null:list.get(0);
	}

}
