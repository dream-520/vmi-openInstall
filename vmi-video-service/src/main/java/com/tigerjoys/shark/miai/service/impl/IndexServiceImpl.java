package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.IndexActivityAreaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.dto.index.GotoDataItem;
import com.tigerjoys.shark.miai.inter.contract.IBannerContract;
import com.tigerjoys.shark.miai.inter.entity.BannerEntity;
import com.tigerjoys.shark.miai.service.IIndexService;

/**
 * 遇见服务类
 * @author liuman
 *
 */
@Service
public class IndexServiceImpl implements IIndexService {
	
	@Autowired
	private IBannerContract bannerContract;
	
	/**
	 * 根据手机类型构建轮播数据
	 * @param banner
	 * @param platform
	 * @return
	 */
	private GotoDataItem buildBanner(BannerEntity banner,int platform) {
		GotoDataItem item = new GotoDataItem();
		String parameters = banner.getParameters();
		/*
		//如果是跳转到h5页面
		if (NOCHOICE.equals(parameters)) {
			item.setAndroidPage(NewPushAppTagEnum.common_message_H5_fragment.getAndroidCode());
			item.setIosPage(NewPushAppTagEnum.common_message_H5_fragment.getIosCode());
			
			PushMessageParamDto param = new PushMessageParamDto();
			//strValue1是H5的url
			param.addParam(1, banner.getOpenurl());
			if (Tools.isNotNull(banner.getParame())) {
				//将后台管理系统存储进去的H5页面需要展示的title存储进去
				Map<String,Object> map = JsonHelper.toMap(banner.getParame());
				param.addParam(2, map.get("strValue1"));
			}
			//将H5活动页需要的url和title重新组织成需要的参数格式传给app端
			item.setParame(JsonHelper.toJson(param.getParamMap()));
		} else {
		*/
			//如果是跳转到app页
			item.setAndroidPage(IndexActivityAreaEnum.getAndroidPageByCode(parameters));
			item.setIosPage(IndexActivityAreaEnum.getIosPageByCode(parameters));
			//页面跳转参数
			item.setParame(banner.getParame());
		//}
		
		//公共部分数据设置
		item.setClickEvent("banner_click_"+banner.getId().longValue());
		item.setImageurl(Const.getCdn(banner.getImageurl()));
		
		return item;
	}
	
	
	/**
	 * 获得活动专区数据
	 * @return
	 * @throws Exception 
	 */
	public List<GotoDataItem> getActivities(int platform) throws Exception {
		List<GotoDataItem> activities = new ArrayList<>();
		List<BannerEntity> banners = bannerContract.getBannerByGroupCode(CommonConst.INDEX_ACTIVITY_AREA, 0, 10);
		if(Tools.isNotNull(banners)) {
			for(BannerEntity banner : banners) {
				activities.add(this.buildBanner(banner,platform));
			}
		}
		return activities;
	}

}
