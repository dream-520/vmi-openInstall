package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceLogContract;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceLogEntity;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.FreeVideoChatExperienceLogMapper;

/**
 * 数据库中  1分钟兔费聊体验日志[t_free_video_chat_experience_log]表 接口实现类
 * @author yangjunming
 * @Date 2019-12-06 16:14:10
 *
 */
@Repository
public class FreeVideoChatExperienceLogContractImpl extends AbstractBaseContract<FreeVideoChatExperienceLogEntity , FreeVideoChatExperienceLogMapper> implements IFreeVideoChatExperienceLogContract {
	
}
