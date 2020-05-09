package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.shark.miai.common.constant.CommonConst;
import org.shark.miai.common.enums.IndexActivityAreaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.dto.index.GotoDataItem;
import com.tigerjoys.shark.miai.dto.service.BannerBData;
import com.tigerjoys.shark.miai.dto.service.HomeBDto;
import com.tigerjoys.shark.miai.dto.service.TopLineHeadDto;
import com.tigerjoys.shark.miai.inter.contract.IBannerContract;
import com.tigerjoys.shark.miai.inter.contract.ITopHeadInfoContract;
import com.tigerjoys.shark.miai.inter.entity.BannerEntity;
import com.tigerjoys.shark.miai.inter.entity.TopHeadInfoEntity;
import com.tigerjoys.shark.miai.service.IConfService;
import com.tigerjoys.shark.miai.service.IHomeBService;

/**
 * 首页服务类
 * 
 * @author yangjunming
 *
 */
@Service
public class HomeBServiceImpl implements IHomeBService {

	@Autowired
	private ITopHeadInfoContract topHeadInfoContract;
	
	@Autowired
	private IBannerContract bannerContract;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private IConfService confService;

	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	@Override
	public ActionResult index(long userId, int platform) throws Exception {
		HomeBDto dto = new HomeBDto();
		
		BannerBData bannerData = new BannerBData();
		bannerData.setBanners(getBanners(platform));
		bannerData.setTime(getTime());
		dto.setBannerData(bannerData);
		
		String userName = RequestUtils.getCurrent().getUser().getUsername();
		boolean testIos = false;
		//if (Const.IOS_TEST_MOBILE_ACCOUNT_MAP.containsKey(userName)) {
		if (confService.testIOS()) {
			testIos = true;
		}
		dto.setTopLineList(getTop(testIos));

		return ActionResult.success(dto);
	}

	/**
	 * 头条信息展示
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<TopLineHeadDto> getTop(boolean falg) throws Exception {
		List<TopLineHeadDto> dtoList = new ArrayList<>();
			PageModel pageModel = PageModel.getLimitModel(0, 100);
			pageModel.addQuery(Restrictions.eq("status", 1));
			pageModel.addQuery(Restrictions.eq("top_type", falg?2:1));
			pageModel.desc("create_time");
			List<TopHeadInfoEntity> topHeadlist = topHeadInfoContract.load(pageModel);
			if (Tools.isNotNull(topHeadlist)) {
				for (TopHeadInfoEntity re : topHeadlist) {
					dtoList.add(TopLineHeadDto.preDto(re));
				}
			}
		if (dtoList.size() % 2 == 1) {
			dtoList = dtoList.subList(0, dtoList.size() - 1);
		}
		return dtoList;
	}

	/**
	 * 获得轮播图数据
	 * @return
	 * @throws Exception 
	 */
	private List<GotoDataItem> getBanners(int platform) throws Exception {
		List<GotoDataItem> banners = new ArrayList<>();
		List<BannerEntity> bannerEnties = bannerContract.getBannerByGroupCode(CommonConst.MEET_STRAMGER_BANNER, 0, 10);
		if(Tools.isNotNull(bannerEnties)) {
			for(BannerEntity banner : bannerEnties) {
				banners.add(this.buildBanner(banner,platform));
			}
		}
		return banners;
	}
	
	
	/**
	 * 根据手机类型构建轮播数据
	 * @param banner
	 * @param platform
	 * @return
	 */
	private GotoDataItem buildBanner(BannerEntity banner,int platform) {
		GotoDataItem item = new GotoDataItem();
		String parameters = banner.getParameters();
		item.setAndroidPage(IndexActivityAreaEnum.getAndroidPageByCode(parameters));
		item.setIosPage(IndexActivityAreaEnum.getIosPageByCode(parameters));
		item.setParame(banner.getParame());
		item.setClickEvent("banner_click_"+banner.getId().longValue());
		item.setImageurl(Const.getCdn(banner.getImageurl()));
		
		return item;
	}


	
	/**
	 * 获得首页配置的轮播图播放时间
	 * @return
	 * @throws Exception
	 */
	private int getTime() throws Exception {
		int time = 2;
		return sysConfigAgent.getIntValue(CommonConst.BANNER_INTERVAL, time);
	}
	
	
}
