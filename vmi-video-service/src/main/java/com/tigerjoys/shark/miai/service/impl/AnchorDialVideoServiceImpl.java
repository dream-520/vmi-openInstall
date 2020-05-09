package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IShortVideoContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.ShortVideoEntity;
import com.tigerjoys.shark.miai.service.IAnchorDialVideoService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

@Service
public class AnchorDialVideoServiceImpl implements IAnchorDialVideoService {

	private List<String> paths = new ArrayList<>();
	
	@Autowired
	private IShortVideoContract shortVideoContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@PostConstruct
	public void init() {
		paths.add("http://www.lanmifeng.com/vmi/video/1.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/2.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/3.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/4.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/5.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/6.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/7.mp4");
		paths.add("http://www.lanmifeng.com/vmi/video/8.mp4");
	}
	
	@Override
	public String anchorDialingVideo(long anchorid) {
		//随机一个对应的主播视频
		String path = null;
		try {
			//检测是否是真主播
			AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", anchorid);
			//真主播不展示视频
			if(Tools.isNotNull(anchor) && anchor.getFlag() == 0)
				return null;
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", anchorid));
			pageModel.addQuery(Restrictions.sql("status=1 order by rand() limit 1"));
			List<ShortVideoEntity> videos = shortVideoContract.load(pageModel);
			if(Tools.isNotNull(videos) && videos.size() > 0) {
				ShortVideoEntity video = videos.get(0);
				if(Tools.isNotNull(video))
					path = ServiceHelper.getCdnVideo(video.getVideo_path());
			}
			//如果没有对应的视频  获取一个产品提供的假视频
			if(Tools.isNull(path)) {
				Collections.shuffle(paths);
				path = paths.get(0);
			}
		} catch (Exception e) {
			
		}
		return path;
	}

}
