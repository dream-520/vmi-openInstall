package com.tigerjoys.shark.miai.inter.contract;

import java.util.List;

import com.tigerjoys.nbs.mybatis.core.BaseContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;

/**
 * 数据库中  [t_user_invite_mapping]表 接口类
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
public interface IUserInviteMappingContract extends BaseContract<UserInviteMappingEntity> {
	/**
	 * 查询默认验证码
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public UserInviteMappingEntity getFirstUserInviteMapping(long userid) throws Exception;
	
	/**
	 * 根据用户ID和 邀请分类名称查询
	 * @param userid
	 * @param inviteName
	 * @return
	 * @throws Exception
	 */
	public UserInviteMappingEntity queryUserInviteMapping(long userid,String inviteName) throws Exception;
	
	/**
	 * 修改邀请分类名称
	 * @param id
	 * @param inviteName
	 * @return
	 * @throws Exception
	 */
	public int updateInviteName(long id,String inviteName) throws Exception;
	
	/**
	 * 代理人分享分类列表
	 * @param userid
	 * @param id
	 * @param inviteName
	 * @return
	 * @throws Exception
	 */
	public List<UserInviteMappingEntity> proxyShareList(long userid) throws Exception;
	
	/**
	 * 更新分享分类收益累计
	 * @param id			分享邀请ID
	 * @param tradeAmount	收益明细
	 * @return
	 * @throws Exception
	 */
	public int addIncome(long id,int tradeAmount) throws Exception;
	
	/**
	 * 更新达人VIP累计人数
	 * @param id	用户分享映射表id
	 * @return
	 * @throws Exception
	 */
	public int addTalentVipNum(long id) throws Exception;
	
	/**
	 * 更新累计邀请人数
	 * @param id	用户分享映射表id
	 * @return
	 * @throws Exception
	 */
	public int addinviteNum(long id) throws Exception;

}
