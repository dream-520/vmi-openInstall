package com.tigerjoys.shark.miai.inter.contract.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteMappingContract;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserInviteMappingMapper;

/**
 * 数据库中 [t_user_invite_mapping]表 接口实现类
 * 
 * @author yangjunming
 * @Date 2017-10-21 17:05:26
 *
 */
@Repository
public class UserInviteMappingContractImpl extends
		AbstractBaseContract<UserInviteMappingEntity, UserInviteMappingMapper> implements IUserInviteMappingContract {

	@Override
	public UserInviteMappingEntity getFirstUserInviteMapping(long userid) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.asc("create_time");
		List<UserInviteMappingEntity> list = mapper.load(pageModel);
		if (Tools.isNotNull(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public UserInviteMappingEntity queryUserInviteMapping(long userid, String inviteName) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("name", inviteName));
		List<UserInviteMappingEntity> list = mapper.load(pageModel);
		if (Tools.isNotNull(list)) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public int updateInviteName(long id, String inviteName) throws Exception {
		return mapper.updateByStatement("name='" + inviteName.trim() + "'", "id=" + id);
	}

	@Override
	public List<UserInviteMappingEntity> proxyShareList(long userid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));

		return mapper.load(pageModel);
	}

	@Override
	public int addIncome(long id, int tradeAmount) throws Exception {
		return mapper.updateByStatement("trade_num=trade_num+1,trade_amount=trade_amount+" + tradeAmount, "id=" + id);
	}

	@Override
	public int addTalentVipNum(long id) throws Exception {
		return mapper.updateByStatement("talentvip_num=talentvip_num+1", "id=" + id);
	}

	@Override
	public int addinviteNum(long id) throws Exception {
		return mapper.updateByStatement("invite_num=invite_num+1", "id=" + id);
	}

}
