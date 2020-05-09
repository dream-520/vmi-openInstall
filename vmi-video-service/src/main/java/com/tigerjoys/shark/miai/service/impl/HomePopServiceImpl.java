package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IAppUserAllowanceAgent;
import com.tigerjoys.shark.miai.agent.IHomePopAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AppUserAllowanceTypeEnum;
import com.tigerjoys.shark.miai.dto.service.HomePopBeanDto;
import com.tigerjoys.shark.miai.dto.service.HomePopDto;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppVersionUpgradePopContract;
import com.tigerjoys.shark.miai.inter.contract.IHomePopVersionLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserRegLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppVersionUpgradePopEntity;
import com.tigerjoys.shark.miai.inter.entity.HomePopEntity;
import com.tigerjoys.shark.miai.inter.entity.HomePopLogEntity;
import com.tigerjoys.shark.miai.inter.entity.HomePopVersionLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserRegLogEntity;
import com.tigerjoys.shark.miai.service.IHomePopService;

@Service
public class HomePopServiceImpl implements IHomePopService {
	
	@Autowired
	private IHomePopAgent homePopAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAppUserAllowanceAgent appUserAllowanceAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IHomePopVersionLogContract homePopVersionLogContract;
	
	@Autowired
	private IAppVersionUpgradePopContract appVersionUpgradePopContract;
	
	
	@Autowired
	private IUserRegLogContract userRegLogContract;
	
	@Override
	public ActionResult showPage(String clientId,long userId) throws Exception {
		
		int platform = RequestUtils.getCurrent().getHeader().getOs_type();
		List<HomePopEntity> list = homePopAgent.getHomePopList(platform);
		String url = null;
		UserBO user = userAgent.findById(userId);
		//如果弹出框数据存在的话
		if(Tools.isNotNull(list)){
			if(list.size()>1){
				Collections.shuffle(list);
			}
			
			List<HomePopLogEntity> logList = homePopAgent.getHomePopLogList(clientId, 18L);
			if(Tools.isNull(logList)){
				UserBO userBO = userAgent.findById(userId);
				if(Tools.isNotNull(userBO)){
					long userReqTime = Long.valueOf(Tools.getFormatDate(userBO.getCreateTime(), "yyyyMMdd"));
					if(20191126>userReqTime){
						//return ActionResult.success(Const.WEB_SITE+"/web/app/update/pop/updateDiamond");
						list = new ArrayList<>();
						HomePopEntity popEntity = new HomePopEntity();
						popEntity.setId(18L);
						popEntity.setUrl(Const.WEB_SITE+"/web/app/update/pop/updateDiamond");
						popEntity.setUsertype(0);
						popEntity.setShowtype(0);
						list.add(popEntity);
					}
				}
			}
			
			
			
			
			/*
			int type =appUserAllowanceAgent.showAllowanceWindow(userId);
			logger.info("showPage_showAllowanceWindow:type="+type);
			/*
			if(type == 0){
				return ActionResult.success(url);
			}
			*/

			for(HomePopEntity homePop:list){
				//HomePopEntity homePop = list.get(0);
				
				/*
				if(homePop.getPop_type() != type){
					continue;
				}
				*/

				//根据用户的设备号和弹窗的数据id查询今日日志记录信息
				logList = homePopAgent.getHomePopLogList(clientId, homePop.getId());
				//如果之前没有弹出框记录的话
				if (Tools.isNull(logList)) {
					//如果是新手用户的弹窗
					if (homePop.getUsertype() == 1) {
						//今天注册的用户并且没有弹窗
						if (Tools.getDate(user.getCreateTime()).equals(Tools.getDate())) {
								homePopAgent.addHomePopLog(clientId, homePop.getId(), userId);
								url = homePop.getUrl();
						}
					} else {//针对所有用户的弹窗
						homePopAgent.addHomePopLog(clientId, homePop.getId(), userId);
						url = homePop.getUrl();
					}
				} else {//有弹出日志记录的时候,通过更新时间与当前时间的比对,对弹出次数++
					if(homePop.getShowtype() == 1) {
						HomePopLogEntity popLog = logList.get(0);
						Date currDate = new Date();
						if(!Tools.getDate(popLog.getUpdate_time()).equals(Tools.getDate(currDate))) {
							homePopAgent.updateHomePopLog(clientId, popLog.getId(), popLog.getCount(),currDate);
							url = homePop.getUrl();
						}
					} else if(homePop.getShowtype() == 2) {
						HomePopLogEntity popLog = logList.get(0);
						Date currDate = new Date();
						homePopAgent.updateHomePopLog(clientId, popLog.getId(), popLog.getCount(),currDate);
						url = homePop.getUrl();
					}
					
				}
				
			}
		}
		return ActionResult.success(url);
	}


	@Override
	public ActionResult showNewPage(long userId) throws Exception {
		int show = 0;
		String text = null;
		HomePopDto dto = new HomePopDto();
		List<HomePopBeanDto> anchors = null;
		if(userId > 0) {
			int type = appUserAllowanceAgent.showAllowanceNewWindow(userId);

			if(type != AppUserAllowanceTypeEnum.no.getCode()) {
				show = 1;
				text = "恭喜获得免费体验视频聊天\n赶紧选择一个畅聊吧";
				//随机获取四个在线的主播
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("state", 1));
				pageModel.addQuery(Restrictions.eq("online", 3));
				List<Long> userids = new ArrayList<>();
				userids.add(146440642122285312L);
				userids.add(140571545920667904L);
				userids.add(140596694176825600L);		
				pageModel.addQuery(Restrictions.notin("userid", userids));
				pageModel.addQuery(Restrictions.sql("flag=0 order by rand() limit 4"));
				List<AnchorOnlineEntity> list = anchorOnlineContract.load(pageModel);
				if(Tools.isNotNull(list) && list.size() > 0) {
					anchors = new ArrayList<>();
					for (AnchorOnlineEntity anchor : list) {
						HomePopBeanDto bean = new HomePopBeanDto();
						bean.setUserId(anchor.getUserid());
						bean.setPhoto(Const.getCdn(anchor.getPhoto()));
						anchors.add(bean);
					}
				}
			} 
		}
		dto.setText(text);
		dto.setShow(show);
		dto.setData(anchors);
		return ActionResult.success(dto);
	}


	@Override
	public String showVersionUpgradePage(String clientId, long userId, int versionCode) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("status", 1));
		pageModel.addQuery(Restrictions.eq("version_code", versionCode));
		//pageModel.desc("id");
		List <AppVersionUpgradePopEntity> appVersion = appVersionUpgradePopContract.load(pageModel);
		if(Tools.isNull(appVersion)){
			return null;
		}
		AppVersionUpgradePopEntity popEntity = appVersion.get(0);
		UserRegLogEntity regLog = userRegLogContract.findById(userId);
		if(Tools.isNotNull(regLog)){
			if(popEntity.getVersion_text().equalsIgnoreCase(regLog.getAppversion())){
				return null;
			}
		}
		
		pageModel.clearAll();
		pageModel.addLimitField(0, 1);
		pageModel.addQuery(Restrictions.eq("client_id", clientId));
		pageModel.addQuery(Restrictions.eq("version_code",versionCode));
		if(homePopVersionLogContract.count(pageModel)==0){
			HomePopVersionLogEntity log = new HomePopVersionLogEntity();
			log.setClient_id(clientId);
			log.setUserid(userId);
			log.setVersion_code(versionCode);
			log.setCreate_time(new Date());
			homePopVersionLogContract.insert(log);
			return appVersion.get(0).getVersion_url();
		}
		return null;
	}
	
	
	
}
