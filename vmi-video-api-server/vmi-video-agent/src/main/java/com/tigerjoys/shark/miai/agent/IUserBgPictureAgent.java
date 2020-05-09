package com.tigerjoys.shark.miai.agent;

import java.util.List;

import com.tigerjoys.shark.miai.agent.dto.UserBgPictureBO;

/**
 * 用户背景图片Info服务代理接口
 * @author lipeng
 *
 */
public interface IUserBgPictureAgent {
	
	/**
	 * 根据ID获得用户对象，此方法走缓存
	 * @param userid - 用户ID
	 * @return UserBO
	 * @throws Exception
	 */
	public UserBgPictureBO findById(long userid) throws Exception;
	
	/**
	 * 获得背景图list
	 * @return
	 * @throws Exception
	 */
	public List<UserBgPictureBO> getBgPicList() throws Exception;
	
	/**
	 * 随机获得一张背景图
	 * @return
	 * @throws Exception
	 */
	public String getBgPic() throws Exception;
	
}
