package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.FreeVideoChatExperienceMapper;

/**
 * 数据库中  1分钟兔费聊体验[t_free_video_chat_experience]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-17 10:14:37
 *
 */
@Repository
public class FreeVideoChatExperienceContractImpl extends AbstractBaseContract<FreeVideoChatExperienceEntity , FreeVideoChatExperienceMapper> implements IFreeVideoChatExperienceContract {

	@Override
	public int insertRecord(long userid,String channel,int second) throws Exception {
		FreeVideoChatExperienceEntity entity = mapper.findByProperty("userid", userid);
		if(Tools.isNotNull(entity)){
			return 0;
		}
		entity = new FreeVideoChatExperienceEntity();
		entity.setUserid(userid);
		entity.setChannel(channel);
		Calendar cal = Calendar.getInstance();
		entity.setCreate_time(cal.getTime());
		/*
		if("huawei_yoyo3".equalsIgnoreCase(channel)){
			cal.add(Calendar.SECOND, second);
			entity.setUpdate_time(cal.getTime());
		}else{
			cal.add(Calendar.SECOND, second);
			entity.setUpdate_time(cal.getTime());
		}
		*/
		//选写死了
		cal.add(Calendar.SECOND, 60*2);
		entity.setUpdate_time(cal.getTime());
		mapper.insertIgnore(entity);
		return 1;
	}
	
}
