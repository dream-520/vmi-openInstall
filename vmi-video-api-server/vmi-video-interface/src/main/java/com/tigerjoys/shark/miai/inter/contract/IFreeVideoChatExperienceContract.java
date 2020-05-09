package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.nbs.mybatis.core.BaseContract;

/**
 * 数据库中  1分钟兔费聊体验[t_free_video_chat_experience]表 接口类
 * @author yangjunming
 * @Date 2019-07-17 10:14:37
 *
 */
public interface IFreeVideoChatExperienceContract extends BaseContract<FreeVideoChatExperienceEntity> {
	public int insertRecord(long userid,String channel,int second) throws Exception;
}
