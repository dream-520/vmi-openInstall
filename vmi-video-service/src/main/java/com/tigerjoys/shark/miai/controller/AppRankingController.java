package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.AppRankingDto;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppRankingContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppRankingEntity;

/**
 * 排行榜展示
 * @author shiming
 *
 */
@Controller
@RequestMapping(value = "/api")
public class AppRankingController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAppRankingContract appRankingContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@RequestMapping(value = "/app/ranking", produces = Produce.TEXT_ENCODE)
	@ResponseBody
	public ActionResult getRanking(@RequestBody String body) throws Exception {
		List<AppRankingDto> data = null;
		JSONObject json = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(json)) {
			logger.info("payment:" + body);
			//获取对应的数据类型
			int type = json.getIntValue("type");
			if(type > 0) {
				PageModel pageModel = PageModel.getLimitModel(0, 30);
				pageModel.addQuery(Restrictions.eq("type", type));
				pageModel.addQuery(Restrictions.eq("status", 1));
				pageModel.desc("contribution");
				List<AppRankingEntity> rankings = appRankingContract.load(pageModel);
				if(Tools.isNotNull(rankings) && rankings.size() > 0) {
					data = new ArrayList<>();
					//进行数据封装操作处理
					for (AppRankingEntity ranking : rankings) {
						UserBO bo = userAgent.findById(ranking.getUserid());
						if(Tools.isNotNull(bo)) {
							AppRankingDto dto = new AppRankingDto();
							dto.setUserId(ranking.getUserid());
							dto.setNickName(bo.getNickname());
							dto.setPhoto(Const.getCdn(bo.getPhoto()));
							if(type == 1) {
								//处理主播魅力榜数据
								AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", ranking.getUserid());
								if(Tools.isNotNull(anchor)) {
									dto.setAnchorStar(anchor.getStar());
								}
								dto.setContent("魅力值:"+ranking.getContribution());
							} else if(type == 2){
								//处理用户财富榜数据
								dto.setNormalLevel("LV."+ranking.getLevel());
								dto.setContent("财富值:"+ranking.getContribution());
							}
							data.add(dto);
						}
					}
				}
			}
		}
	    return ActionResult.success(data, null, false);
	 }
}
