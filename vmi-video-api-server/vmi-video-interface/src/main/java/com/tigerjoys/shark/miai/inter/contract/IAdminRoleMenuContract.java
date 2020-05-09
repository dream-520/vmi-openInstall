package com.tigerjoys.shark.miai.inter.contract;

import com.tigerjoys.nbs.mybatis.core.BaseCacheContract;
import com.tigerjoys.shark.miai.inter.entity.AdminRoleMenuEntity;

/**
 * 数据库中  角色跟按钮的关联表[t_admin_role_menu]表 接口类
 * @author chengang
 * @Date 2017-04-26 14:46:53
 *
 */
public interface IAdminRoleMenuContract extends BaseCacheContract<AdminRoleMenuEntity> {
	
	/**
	 * 根据角色ID和按钮ID获得实例
	 * @param roleId - 角色ID
	 * @param menuId - 按钮ID
	 * @return AdminRoleMenuEntity
	 * @throws Exception
	 */
	public AdminRoleMenuEntity getAdminRoleMenuEntity(long roleId , long menuId) throws Exception;
	
}
