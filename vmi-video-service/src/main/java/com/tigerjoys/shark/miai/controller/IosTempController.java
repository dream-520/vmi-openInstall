package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoLogin;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.IosTempDto;
import com.tigerjoys.shark.miai.enums.StaticWebUrlEnum;
import com.tigerjoys.shark.miai.inter.contract.IAppChannelContract;
import com.tigerjoys.shark.miai.inter.contract.IAppPackageContract;
import com.tigerjoys.shark.miai.inter.contract.IAppVersionContract;
import com.tigerjoys.shark.miai.inter.entity.AppChannelEntity;
import com.tigerjoys.shark.miai.inter.entity.AppPackageEntity;
import com.tigerjoys.shark.miai.inter.entity.AppVersionEntity;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * IOS临时接口controller
 * @author lipeng
 *
 */
@Login
@Controller
@RequestMapping(value="/api", produces=Produce.TEXT_ENCODE)
public class IosTempController {
	
	private static final Logger logger = LoggerFactory.getLogger(IosTempController.class);
	@Autowired
	private IAppPackageContract appPackageContract;
	
	@Autowired
	private IAppChannelContract appChannelContract;

	@Autowired
	private IAppVersionContract appVersionContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	
	
	/**
	 * iostemp接口1
	 * @param body
	 * @return
	 * @throws Exception
	 */
	@NoLogin
	@UserClientService("iosTemp")
	@RequestMapping(value="/iosTemp")
	public @ResponseBody ActionResult checkVersion() throws Exception {
		UserBO user = userAgent.findByUsername("15873952675");
		if (RequestUtils.getCurrent().getHeader().getUserid()==user.getUserid()) {
			return null;
		}
		UserBO user1 = userAgent.findByUsername("18811315514");
		if (RequestUtils.getCurrent().getHeader().getUserid()==user1.getUserid()) {
			return null;
		}
		UserBO userTemp = userAgent.findById(RequestUtils.getCurrent().getHeader().getUserid());
		if (userTemp==null||userTemp.getFr()==4) {
			return null;
		}
		AppPackageEntity appPackage = appPackageContract.findByProperty("name", RequestUtils.getCurrent().getHeader().getPackageName());
		AppChannelEntity channel = appChannelContract.findByProperty("name", RequestUtils.getCurrent().getHeader().getChannel());
		RequestHeader requestHeader = RequestUtils.getCurrent().getHeader();
		if (appPackage!=null && channel!=null) {
			PageModel pageModel = PageModel.getPageModel(0, 1);
			pageModel.addQuery(Restrictions.eq("platform", 2));
			pageModel.addQuery(Restrictions.eq("code", requestHeader.getVersioncode()));
			pageModel.addQuery(Restrictions.eq("channel_id", channel.getId()));
			pageModel.addQuery(Restrictions.eq("package_id", appPackage.getId()));
			pageModel.addQuery(Restrictions.eq("status", 1));
			pageModel.addQuery(Restrictions.eq("block_apple_pay", 1));
			List<AppVersionEntity> list = appVersionContract.load(pageModel);
			List<IosTempDto> voList = new ArrayList<IosTempDto>();
			if (Tools.isNotNull(list)) {
				IosTempDto dto = new IosTempDto();
				dto.setTitle("我的钱包");
				dto.setIcon(ServiceHelper.getCdnPhoto("/img/wallet/wallet_icon.png"));
				dto.setPushType(1);
				dto.setUrl(Const.WEB_SITE+"/api/income/zuanList");
				voList.add(dto);
			}
			if (requestHeader.getPackageName().equals("com.zdkj.lttool")) {
				IosTempDto dto = new IosTempDto();
				dto.setTitle("邀请好友");
				dto.setIcon(ServiceHelper.getCdnPhoto("/img/share/milao/share_icon.png"));
				dto.setPushType(1);
				dto.setUrl(Const.WEB_SITE+StaticWebUrlEnum.MY_SHARE.getPath());
				voList.add(dto);
			}
			return ActionResult.success(voList);
		}
		return null;
	}
	
}
