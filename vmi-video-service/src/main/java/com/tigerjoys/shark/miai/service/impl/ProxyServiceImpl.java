package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shark.miai.common.enums.UserInviteProxyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.ProxyDto;
import com.tigerjoys.shark.miai.dto.service.ProxyPersonnelDto;
import com.tigerjoys.shark.miai.dto.service.UserDividedMappingDto;
import com.tigerjoys.shark.miai.dto.service.UserPersonnelMappingDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IProxyContract;
import com.tigerjoys.shark.miai.inter.contract.IProxyTransContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteMappingContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTalentVipStatisticsContract;
import com.tigerjoys.shark.miai.inter.entity.ProxyEntity;
import com.tigerjoys.shark.miai.inter.entity.ProxyTransEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteMappingEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTalentVipStatisticsEntity;
import com.tigerjoys.shark.miai.service.IProxyService;

/**
 * 代金券服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class ProxyServiceImpl implements IProxyService {
	@Autowired
	private IProxyTransContract proxyTransContract;

	@Autowired
	private IProxyContract proxyContract;

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private IUserInviteMappingContract userInviteMappingContract;
	
	@Autowired
	private IUserTalentVipStatisticsContract userTalentVipStatisticsContract;
	
	@Autowired
	private IUserInviteContract userInviteContract;

	@Override
	public HashMap<String, Object> queryProxy(long mappingid) throws Exception {
		PageModel pageModel = PageModel.getPageModel(0, 100);
		pageModel.addQuery(Restrictions.eq("mappingid", mappingid));
		pageModel.desc("id");
		List<ProxyTransEntity> list = proxyTransContract.load(pageModel);
		ArrayList<Long> userIdList = new ArrayList<Long>();

		List<ProxyDto> proxyList = new ArrayList<ProxyDto>();
		if (Tools.isNotNull(list)) {
			list.forEach(v -> {
				userIdList.add(v.getIncomeid());
			});
			Map<Long, UserBO> userList = userAgent.findById(userIdList);
			for (ProxyTransEntity dto : list) {
				UserBO uesrBo = userList.get(dto.getIncomeid());
				proxyList.add(ProxyDto.pareDto(dto, uesrBo));
			}
		}
		UserInviteMappingEntity mappingEntity = userInviteMappingContract.findById(mappingid);
		HashMap<String, Object> outHsmp = new HashMap<>();
		outHsmp.put("noRecord", proxyList.isEmpty() ? true : false);
		outHsmp.put("proxyList", proxyList);
		outHsmp.put("mappingName", mappingEntity.getName());
		outHsmp.put("tradeNum", mappingEntity.getTrade_num());
		outHsmp.put("dividedAmount", mappingEntity.getTrade_amount() / 100.0);
		return outHsmp;
	}

	@Override
	public HashMap<String, Object> queryProxyInviteMapping(long userId) throws Exception {
		ProxyEntity proxy = proxyContract.findByProperty("userid", userId);
		PageModel pageMode = PageModel.getPageModel();
		pageMode.addQuery(Restrictions.eq("userid", userId));
		pageMode.asc("id");
		List<UserDividedMappingDto> mappingList = new ArrayList<UserDividedMappingDto>();

		List<UserInviteMappingEntity> inviteMappingList = userInviteMappingContract.load(pageMode);
		if (Tools.isNotNull(inviteMappingList)) {
			inviteMappingList.forEach(v -> {
				mappingList.add(UserDividedMappingDto.pareDto(v));
			});
		}
		HashMap<String, Object> outHsmp = new HashMap<>();
		outHsmp.put("noRecord", mappingList.isEmpty() ? true : false);
		outHsmp.put("mappingList", mappingList);
		outHsmp.put("tradeNum", proxy.getTrade_num());
		outHsmp.put("dividedAmount", proxy.getDivided_amount() / 100.0);
		return outHsmp;
	}

	@Override
	public ActionResult queryPersonnelMapping(long userId, int pageSize, long stamp) throws Exception {
		ProxyEntity proxy = proxyContract.findByProperty("userid", userId);
		if (Tools.isNull(proxy)) {
			return ActionResult.fail(ErrorCodeEnum.user_no_proxy);
		}
		PageModel pageMode = PageModel.getPageModel();
		pageMode.setPageSize(pageSize + 1);
		pageMode.addQuery(Restrictions.eq("userid", userId));
		if (stamp > 0) {
			pageMode.addQuery(Restrictions.gt("id", stamp));
		}
		pageMode.asc("id");
		List<UserPersonnelMappingDto> mappingList = new ArrayList<UserPersonnelMappingDto>();

		List<UserInviteMappingEntity> inviteMappingList = userInviteMappingContract.load(pageMode);
		if (Tools.isNotNull(inviteMappingList)) {
			for (UserInviteMappingEntity re : inviteMappingList) {
				mappingList.add(UserPersonnelMappingDto.pareDto(re));
			}
		}

		boolean nextPage = false;
		long newLastStampId = stamp;
		if (Tools.isNotNull(mappingList)) {
			if (mappingList.size() > pageSize) {
				nextPage = true;
				mappingList = mappingList.subList(0, pageSize);
				newLastStampId = mappingList.get(mappingList.size() - 1).getId();
			}
		}

		HashMap<String, Object> outHsmp = new HashMap<>();
		outHsmp.put("typeList", mappingList);
		outHsmp.put("totalNum", proxy.getInvitation());
		outHsmp.put("talentTotalNum", proxy.getTalent_vip_num());
		return ActionResult.success(outHsmp, newLastStampId, nextPage);
	}

	@Override
	public ActionResult queryDividedMapping(long userId, int pageSize, long stamp) throws Exception {
		ProxyEntity proxy = proxyContract.findByProperty("userid", userId);
		if (Tools.isNull(proxy)) {
			return ActionResult.fail(ErrorCodeEnum.user_no_proxy);
		}
		PageModel pageMode = PageModel.getPageModel();
		pageMode.setPageSize(pageSize + 1);
		pageMode.addQuery(Restrictions.eq("userid", userId));
		if (stamp > 0) {
			pageMode.addQuery(Restrictions.gt("id", stamp));
		}
		pageMode.asc("id");
		List<UserDividedMappingDto> mappingList = new ArrayList<UserDividedMappingDto>();

		List<UserInviteMappingEntity> inviteMappingList = userInviteMappingContract.load(pageMode);
		if (Tools.isNotNull(inviteMappingList)) {
			for (UserInviteMappingEntity re : inviteMappingList) {
				mappingList.add(UserDividedMappingDto.pareDto(re));
			}
		}

		boolean nextPage = false;
		long newLastStampId = stamp;
		if (Tools.isNotNull(mappingList)) {
			if (mappingList.size() > pageSize) {
				nextPage = true;
				mappingList = mappingList.subList(0, pageSize);
				newLastStampId = mappingList.get(mappingList.size() - 1).getId();
			}
		}

		HashMap<String, Object> outHsmp = new HashMap<>();
		outHsmp.put("typeList", mappingList);
		outHsmp.put("totalNum", proxy.getTrade_num());
		outHsmp.put("totalAmount", proxy.getDivided_amount() / 100.0);
		return ActionResult.success(outHsmp, newLastStampId, nextPage);
	}
	
	
	
	
	@Override
	public ActionResult queryDividedDesc(long mappingid, int pageSize, long stamp) throws Exception {
		UserInviteMappingEntity mapping = userInviteMappingContract.findById(mappingid);
		if (Tools.isNull(mapping)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}

		PageModel pageMode = PageModel.getPageModel();
		pageMode.setPageSize(pageSize + 1);
		pageMode.addQuery(Restrictions.eq("mappingid", mappingid));
		if (stamp > 0) {
			pageMode.addQuery(Restrictions.lt("id", stamp));
		}
		pageMode.desc("id");
		List<ProxyTransEntity> list = proxyTransContract.load(pageMode);
		ArrayList<Long> userIdList = new ArrayList<Long>();
		List<ProxyDto> transList = new ArrayList<ProxyDto>();
		if (Tools.isNotNull(list)) {
			list.forEach(v -> {
				userIdList.add(v.getIncomeid());
			});
			Map<Long, UserBO> userList = userAgent.findById(userIdList);
			for (ProxyTransEntity dto : list) {
				UserBO uesrBo = userList.get(dto.getIncomeid());
				transList.add(ProxyDto.pareDto(dto, uesrBo));
			}
		}

		boolean nextPage = false;
		long newLastStampId = stamp;
		if (Tools.isNotNull(transList)) {
			if (transList.size() > pageSize) {
				nextPage = true;
				transList = transList.subList(0, pageSize);
				newLastStampId = transList.get(transList.size() - 1).getId();
			}
		}

		HashMap<String, Object> outHsmp = new HashMap<>();
		outHsmp.put("totalNum", mapping.getTrade_num());
		outHsmp.put("totalAmount", mapping.getTrade_amount() / 100.0);
		outHsmp.put("transList", transList);
		return ActionResult.success(outHsmp, newLastStampId, nextPage);
	}

	@Override
	public ActionResult queryPersonnelDesc(long mappingid, int pageSize, long stamp) throws Exception {
		UserInviteMappingEntity mapping = userInviteMappingContract.findById(mappingid);
		if (Tools.isNull(mapping)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}

		PageModel pageMode = PageModel.getPageModel();
		pageMode.setPageSize(pageSize + 1);
		pageMode.addQuery(Restrictions.eq("mapping", mappingid));
		pageMode.addQuery(Restrictions.eq("proxy", UserInviteProxyEnum.proxy.getCode()));
		if (stamp > 0) {
			pageMode.addQuery(Restrictions.lt("id", stamp));
		}
		pageMode.desc("id");
		List<UserInviteEntity> list = userInviteContract.load(pageMode);
		ArrayList<Long> userIdList = new ArrayList<Long>();
		List<ProxyPersonnelDto> userList = new ArrayList<ProxyPersonnelDto>();
		if (Tools.isNotNull(list)) {
			list.forEach(v -> {
				userIdList.add(v.getUserid());
			});
			Map<Long, UserBO> userMap = userAgent.findById(userIdList);
			PageModel pmode = PageModel.getPageModel();
			pmode.addQuery(Restrictions.in("userid", userIdList));
			List<UserTalentVipStatisticsEntity> statisticsList = userTalentVipStatisticsContract.load(pmode);
			Map<Long, UserTalentVipStatisticsEntity> statisticsMap = new HashMap<Long, UserTalentVipStatisticsEntity>();
			if (Tools.isNotNull(statisticsList)) {
				statisticsList.forEach(v->{
					statisticsMap.put(v.getUserid(), v);
				});
			}
			for (UserInviteEntity dto : list) {
				UserBO uesrBo = userMap.get(dto.getUserid());
				UserTalentVipStatisticsEntity statistics = statisticsMap.get(dto.getUserid());
				userList.add(ProxyPersonnelDto.pareDto(dto, uesrBo,statistics));
			}
		}

		boolean nextPage = false;
		long newLastStampId = stamp;
		if (Tools.isNotNull(userList)) {
			if (userList.size() > pageSize) {
				nextPage = true;
				userList = userList.subList(0, pageSize);
				newLastStampId = userList.get(userList.size() - 1).getId();
			}
		}

		HashMap<String, Object> outHsmp = new HashMap<>();
		outHsmp.put("totalNum", mapping.getInvite_num());
		outHsmp.put("talentNum", mapping.getTalentvip_num());
		outHsmp.put("userList", userList);
		return ActionResult.success(outHsmp, newLastStampId, nextPage);
	}
	

}
