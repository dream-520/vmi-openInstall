package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IAnchorDefendAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendItemDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendUserItemDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.dto.service.AnchorDefendItemDataDto;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.service.IAnchorDefendService;

@Service
public class AnchorDefendServiceImpl implements IAnchorDefendService {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private IAnchorDefendAgent anchorDefendAgent;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorIntimateRankingsContract anchorIntimateRankingsContract;
	
	@Override
	public ActionResult getAnchorDefend(long anchorid) {
		List<AnchorDefendItemDataDto> datas = null;
		try {
			List<AnchorDefendItemDto> dtos = anchorDefendAgent.getAnchorDefends(anchorid);
			if(Tools.isNotNull(dtos)) {
				datas = new ArrayList<AnchorDefendItemDataDto>();
				PageModel pageModel = PageModel.getPageModel();
				List<GuardVipCategoryEntity> guards = guardVipCategoryContract.load(pageModel);
				Map<Integer, String> icons = new HashMap<>();
				if(Tools.isNotNull(guards)) {
					for (GuardVipCategoryEntity guard : guards) {
						icons.put(guard.getLevel(), guard.getIcon_back());
					}
				}
				//获取对应的用户id集合
				List<Long> userids = new ArrayList<>();
				for (AnchorDefendItemDto dto : dtos) {
					userids.add(dto.getUserid());
				}
				//获取对应的所有的用户信息
				Map<Long, UserBO> users = userAgent.findById(userids);
				//获取对应的所有的用户对该主播的贡献值
				pageModel.addQuery(Restrictions.eq("anchor_userid", anchorid));
				pageModel.addQuery(Restrictions.in("userid", userids));
				List<AnchorIntimateRankingsEntity> ranks = anchorIntimateRankingsContract.load(pageModel);
				Map<Long, Integer> closes = new HashMap<Long, Integer>();
				if(Tools.isNotNull(ranks)) {
					for (AnchorIntimateRankingsEntity rank : ranks) {
						closes.put(rank.getUserid(), rank.getTotal_money());
					}
				}
				//进行数据拼接操作处理
				for (AnchorDefendItemDto dto : dtos) {
					UserBO bo = users.get(dto.getUserid());
					if(Tools.isNotNull(bo)) {
						logger.error("找到对应的用户 :"+bo.getNickname());
						AnchorDefendItemDataDto data = new AnchorDefendItemDataDto();
						data.setUserId(dto.getUserid());
						data.setNickName(bo.getNickname());
						if(Tools.isNotNull(bo.getPhoto()))
							data.setPhoto(Const.getCdn(bo.getPhoto()));
						data.setLevel(dto.getLevel());
						Integer close = closes.get(dto.getUserid());
						if(Tools.isNotNull(close))
							data.setClose(close);
						String icon = icons.get(dto.getLevel());
						if(Tools.isNotNull(icon)) 
							data.setGuardLevel(Const.getCdn(icon));
						datas.add(data);
					}
				}
				logger.error("拼接后的数据数量为:"+datas.size());
				//datas.stream().sorted(Comparator.comparing(AnchorDefendItemDataDto::getLevel, (x, y) -> y.compareTo(x)).thenComparing(Comparator.comparing(AnchorDefendItemDataDto::getClose, (x, y) -> y.compareTo(x))));
				datas = datas.stream().sorted(Comparator.comparing(AnchorDefendItemDataDto::getLevel,Comparator.reverseOrder()).thenComparing(Comparator.comparing(AnchorDefendItemDataDto::getClose,Comparator.reverseOrder()))).collect(Collectors.toList());
				for (AnchorDefendItemDataDto data : datas) {
					logger.error("重新拼接数据处理:"+data.getLevel()+" close:"+data.getClose());
					data.setCloseValue("亲密值: "+ data.getClose());
				}
			}
		} catch (Exception e) {
			
		}
		return ActionResult.success(datas);
	}

	@Override
	public ActionResult getUserDefend(long userid) {
		List<UserBaseInfo> datas = null;
		try {
			List<AnchorDefendUserItemDto> dtos = anchorDefendAgent.getUserAnchorDefend(userid);
			if(Tools.isNotNull(dtos) && dtos.size() > 0) {
				datas = new ArrayList<>();
				//获取对应的用户id集合
				List<Long> userids = new ArrayList<>();
				for (AnchorDefendUserItemDto dto : dtos) {
					userids.add(dto.getAnchorid());
				}
				//获取对应的所有的用户信息
				Map<Long, UserBO> users = userAgent.findById(userids);
				for (AnchorDefendUserItemDto dto : dtos) {
					UserBO bo = users.get(dto.getAnchorid());
					if(Tools.isNotNull(bo)) {
						UserBaseInfo data = new UserBaseInfo();
						data.setNickName(bo.getNickname());
						data.setUserId(dto.getAnchorid());
						if(Tools.isNotNull(bo.getPhoto()))
							data.setPhoto(Const.getCdn(bo.getPhoto()));
						//检测是否已经过期
						if(Tools.getDate().compareTo(dto.getDate()) > 0) {
							//已经过期了
							data.setBackText1("有效期:  "+"已过期");
						} else {
							//有效期内
							data.setBackText1("有效期:  "+dto.getDate());
						}
						datas.add(data);
					}
				}
			}
		} catch (Exception e) {
			
		}
		return ActionResult.success(datas);
	}

}
