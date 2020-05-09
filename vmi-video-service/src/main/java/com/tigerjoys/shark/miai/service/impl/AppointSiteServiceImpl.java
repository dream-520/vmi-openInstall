package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.nbs.web.context.UserDetails;
import com.tigerjoys.shark.miai.agent.IPayAgent;
import com.tigerjoys.shark.miai.agent.dto.PayAccessDto;
import com.tigerjoys.shark.miai.agent.enums.PayChannelEnum;
import com.tigerjoys.shark.miai.agent.enums.PayTypeEnum;
import com.tigerjoys.shark.miai.dto.service.AppointSiteDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAppointSiteContract;
import com.tigerjoys.shark.miai.inter.contract.IAppointSiteOrderContract;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteEntity;
import com.tigerjoys.shark.miai.inter.entity.AppointSiteOrderEntity;
import com.tigerjoys.shark.miai.service.IAppointSiteService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 场地服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class AppointSiteServiceImpl implements IAppointSiteService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IAppointSiteContract appointSiteContract;
	
	@Autowired
	private IPayAgent payAgent;
	
	@Autowired
	private IAppointSiteOrderContract appointSiteOrderContract;

	@Override
	public Map<String, Object> siteIndex() throws Exception {
		Map<String, Object> outHsmp = new HashMap<String, Object>();
		List<AppointSiteEntity> randomList = appointSiteContract.loadRandomList(10);
		outHsmp.put("tabItem0", transToDto(randomList));
		outHsmp.put("tabItem1", transToDto(siteTypeList(1)));
		outHsmp.put("tabItem2", transToDto(siteTypeList(2)));
		outHsmp.put("tabItem3", transToDto(siteTypeList(3)));
		outHsmp.put("tabItem4", transToDto(siteTypeList(4)));
		outHsmp.put("tabItem5", transToDto(siteTypeList(5)));
		outHsmp.put("tabItem6", transToDto(siteTypeList(6)));
		outHsmp.put("tabItem7", transToDto(siteTypeList(7)));
		outHsmp.put("tabItem8", transToDto(siteTypeList(8)));
		return outHsmp;
	}

	@Override
	public List<AppointSiteEntity> siteTypeList(int type) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("type", type));
		return appointSiteContract.load(pageModel);
	}

	public List<AppointSiteDto> transToDto(List<AppointSiteEntity> list) throws Exception {
		List<AppointSiteDto> dtoList = new ArrayList<>();
		if (Tools.isNull(list)) {
			return dtoList;
		}
		list.forEach(v -> {
			dtoList.add(AppointSiteDto.pareDto(v));
		});
		return dtoList;
	}

	public AppointSiteDto siteDesc(long id) throws Exception {
		AppointSiteEntity entity = appointSiteContract.findById(id);
		if(Tools.isNull(entity)){
			return null;
		}
		return AppointSiteDto.pareDto(entity);
	}
	
	
	public ActionResult recharge(long id, int channelCode, long money, String receipt) throws Exception{
		BeatContext context = RequestUtils.getCurrent();
		RequestHeader header = context.getHeader();
		UserDetails user = context.getUser();
		Long userId = user.getUserid();
		String nickname = Tools.isNull(user.getNickname()) ? "" : user.getNickname();
		String mobile = Tools.isNull(user.getMobile()) ? "" : user.getMobile();

		AppointSiteEntity site = appointSiteContract.findById(id);
		if (Tools.isNotNull(site)) {
			if (money != site.getCost().longValue()) {
				return ActionResult.fail(ErrorCodeEnum.parameter_error);
			}
		} else {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		if (PayChannelEnum.iap.getCode() == channelCode && Tools.isNull(receipt)) {
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		// 同一订单可能多次验证
		Long orderId = payAgent.ensureOrderViaReceipt(MD5.encode(receipt));
		if (null == orderId || null == appointSiteOrderContract.findById(orderId)) {
			Date date = new Date();
			AppointSiteOrderEntity order = new AppointSiteOrderEntity();
			orderId=IdGenerater.generateId();
			order.setOrder_id(orderId);
			order.setCreate_time(date);
			order.setUpdate_time(date);
			order.setUser_id(userId);
			order.setMobile(mobile);
			order.setNickname(nickname);
			order.setSite_id(site.getId());;
			order.setCost(site.getCost());
			order.setStatus(0);
			appointSiteOrderContract.insert(order);
			orderId = order.getOrder_id();
		}

		PayAccessDto access = new PayAccessDto();
		access.setUser_id(user.getUserid());
		access.setNickname(user.getNickname());
		access.setMobile(user.getMobile());
		access.setOrder_id(orderId);
		String fm = ""+site.getCost();
		access.setSubject("[场地费用：" + fm + "元]");
		access.setDescription("用户预订场地花费：" + fm + "元");
		access.setMoney(site.getCost().longValue()*100);
		access.setPay_channel(PayChannelEnum.getByCode(channelCode));
		access.setType(PayTypeEnum.pay_appoint_site);
		access.setApp_type(header.getOs_type());
		access.setApp_channel(header.getChannel());
		access.setApp_version(header.getVersion());
		access.setPackage_name(header.getPackageName());
		return ServiceHelper.dealPayData(payAgent.preparePay(access), PayChannelEnum.getByCode(channelCode));
	
	}
	
	
}
