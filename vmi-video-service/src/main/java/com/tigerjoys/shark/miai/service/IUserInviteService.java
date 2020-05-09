package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;

/**
 * 代金券服务类
 * @author yangjunming
 *
 */
public interface IUserInviteService {
	

	/**
	 * 获得用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserInviteMappingEntity getUserDefaultInviteCode(long userId,String packageName) throws Exception ;
	
	/**
	 * 添加邀请码分类
	 * @param userid
	 * @param inviteName	分类名称
	 * @return
	 * @throws Exception
	 */
	public ActionResult addUserInviteCode(long userId, String inviteName,String packageName) throws Exception;
	
	/**
	 * 修改分类名称
	 * @param userid
	 * @param id
	 * @param inviteName
	 * @return
	 * @throws Exception
	 */
	public ActionResult updateInviteName(long userid,long id,String inviteName) throws Exception;
	
	/**
	 * 代理人分享分类列表
	 * @param userid
	 * @param id
	 * @param inviteName
	 * @return
	 * @throws Exception
	 */
	public ActionResult proxyShareList(long userid) throws Exception;
	
	/**
	 * 跳转代理人分享页
	 * @param userid
	 * @param id
	 * @param inviteName
	 * @return
	 * @throws Exception
	 */
	public UserInviteMappingEntity gotoUserShare(long id) throws Exception;
}
