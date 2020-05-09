package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.dto.service.ProxyShareDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteMappingContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInvitecodeContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInvitecodeEntity;
import com.tigerjoys.shark.miai.service.IUserInviteService;

@Service
public class UserInviteServiceImpl implements IUserInviteService {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private IUserInviteMappingContract userInviteMappingContract;

	@Autowired
	private IUserInvitecodeContract userInvitecodeContract;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public UserInviteMappingEntity getUserDefaultInviteCode(long userId,String packageName) throws Exception {
		UserInviteMappingEntity mapping = userInviteMappingContract.getFirstUserInviteMapping(userId);
		if (Tools.isNull(mapping)) {
			UserInvitecodeEntity inviteCode = userInvitecodeContract.getInviteCode();
			if (Tools.isNotNull(inviteCode)) {
				mapping = new UserInviteMappingEntity();
				mapping.setUserid(userId);
				mapping.setName("默认邀请码");
				mapping.setInvitecode(inviteCode.getInviteCode());
				mapping.setInvite_num(0);
				mapping.setTrade_num(0);
				mapping.setTrade_amount(0);
				mapping.setPackage_name(packageName);
				mapping.setCreate_time(new Date());
				userInviteMappingContract.insert(mapping);
				userInvitecodeContract.updateUsed(inviteCode.getId());
			} else {
				return null;
			}
		}
		if(Tools.isNull(mapping.getPackage_name())){
			userInviteMappingContract.updateByStatement("package_name='"+packageName+"'", "id="+mapping.getId());
		}
		return mapping;
	}

	@Override
	public ActionResult addUserInviteCode(long userId, String inviteName,String packageName) throws Exception {

		UserInviteMappingEntity mapping = userInviteMappingContract.queryUserInviteMapping(userId, inviteName);
		if (Tools.isNull(mapping)) {
			UserInvitecodeEntity inviteCode = userInvitecodeContract.getInviteCode();
			if (Tools.isNotNull(inviteCode)) {
				mapping = new UserInviteMappingEntity();
				mapping.setUserid(userId);
				mapping.setName(inviteName);
				mapping.setInvitecode(inviteCode.getInviteCode());
				mapping.setTalentvip_num(0);
				mapping.setInvite_num(0);
				mapping.setTrade_num(0);
				mapping.setTrade_amount(0);
				mapping.setPackage_name(packageName);
				mapping.setCreate_time(new Date());
				userInviteMappingContract.insert(mapping);
				userInvitecodeContract.updateUsed(inviteCode.getId());
			} else {
				return ActionResult.fail(ErrorCodeEnum.share_invite_code_isnull);
			}
		} else {
			return ActionResult.fail(ErrorCodeEnum.share_invite_name_exist);
		}
		logger.info("addUserInviteCode:" + JsonHelper.toJson(mapping));

		return ActionResult.success(ProxyShareDto.pareDto(mapping));
	}

	@Override
	public ActionResult updateInviteName(long userid, long id, String inviteName) throws Exception {

		UserInviteMappingEntity mapping = userInviteMappingContract.queryUserInviteMapping(userid, inviteName);
		if (Tools.isNull(mapping)) {
			userInviteMappingContract.updateInviteName(id, inviteName);
		} else {
			return ActionResult.fail(ErrorCodeEnum.share_invite_name_exist);
		}
		return ActionResult.success();
	}

	@Override
	public ActionResult proxyShareList(long userid) throws Exception {
		List<UserInviteMappingEntity> mappingList = userInviteMappingContract.proxyShareList(userid);
		List<ProxyShareDto> list = new ArrayList<>();
		if (Tools.isNotNull(mappingList)) {
			for (UserInviteMappingEntity re : mappingList) {
				list.add(ProxyShareDto.pareDto(re));
			}
		}
		return ActionResult.success(list);
	}

	@Override
	public UserInviteMappingEntity gotoUserShare(long id) throws Exception {

		return userInviteMappingContract.findById(id);
	}

}
