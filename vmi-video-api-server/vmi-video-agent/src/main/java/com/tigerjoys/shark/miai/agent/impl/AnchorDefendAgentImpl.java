package com.tigerjoys.shark.miai.agent.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IAnchorDefendAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendItemDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendPayDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendTopDto;
import com.tigerjoys.shark.miai.agent.dto.AnchorDefendUserItemDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.AnchorDefendLogEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorDefendContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorDefendLogContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipOrderContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorDefendEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorDefendLogEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorIntimateRankingsEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipOrderEntity;

@Service
public class AnchorDefendAgentImpl implements IAnchorDefendAgent {

	@Autowired
	private IAnchorDefendContract anchorDefendContract;
	
	@Autowired
	private IAnchorDefendLogContract anchorDefendLogContract;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	
	@Autowired
	private IGuardVipOrderContract guardVipOrderContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IAnchorIntimateRankingsContract anchorIntimateRankingsContract;
	
	@Override
	public void buyAnchorDefend(long orderid) {
		try {
			//根据对应的订单找到对应的购买信息
			GuardVipOrderEntity order = guardVipOrderContract.findById(orderid);
			if(Tools.isNotNull(order)) {
				//查询当前订单对应是否有对应的守护信息
				GuardVipCategoryEntity guard = guardVipCategoryContract.findById(order.getPrice_id());
				if(Tools.isNotNull(guard)) {
					Date create_time = new Date();
					//确定是购买或者延长对应的守护信息
					PageModel pageModel = PageModel.getPageModel();
					pageModel.addQuery(Restrictions.eq("userid", order.getUser_id()));
					pageModel.addQuery(Restrictions.eq("anchorid", order.getAnchorId()));
					pageModel.addQuery(Restrictions.eq("level", guard.getLevel()));
					
					AnchorDefendLogEntity log = new AnchorDefendLogEntity();
					log.setUserid(order.getUser_id());
					log.setAnchorid(order.getAnchorId());
					log.setOrderid(orderid);
					log.setLevel(guard.getLevel());
					log.setDays(guard.getDays());
					log.setCreate_time(create_time);
					
					List<AnchorDefendEntity> defends = anchorDefendContract.load(pageModel);
					if(Tools.isNotNull(defends) && defends.size() > 0) {
						//已经存在对应的守护类型
						AnchorDefendEntity defend = defends.get(0);
						log.setStart_date(defend.getStart_date());
						log.setEnd_date(defend.getEnd_date());
						//分析对应的守护是否过期    今天是否在结束日期的后面
						if(Tools.getDate(Tools.getDate(create_time)).compareTo(defend.getEnd_date()) > 0) {
							//处于过期中
							defend.setStart_date(create_time);
							defend.setEnd_date(Tools.getdate(create_time, guard.getDays()-1));
							log.setType(AnchorDefendLogEnum.withoutPeriod.getCode());
						} else {
							log.setType(AnchorDefendLogEnum.withinPeriod.getCode());
							//处于有效期内  设置延长的时间值
							defend.setEnd_date(Tools.getdate(defend.getEnd_date(), guard.getDays()));
						}
						defend.setUpdate_time(create_time);
						anchorDefendContract.update(defend);
					} else {
						//首次进行插入本类型守护操作处理
						//检测是否有对应的高等级的守护
						pageModel.clearAll();
						pageModel.addPageField(0, 1);
						pageModel.addQuery(Restrictions.eq("userid", order.getUser_id()));
						pageModel.addQuery(Restrictions.eq("anchorid", order.getAnchorId()));
						pageModel.addQuery(Restrictions.gt("level", guard.getLevel()));
						//添加一个确认是否在有效期内的判断字段
						pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
						pageModel.desc("end_date");
						List<AnchorDefendEntity> gLevels = anchorDefendContract.load(pageModel);
						int flag = 0;
						if(Tools.isNotNull(gLevels)) {
							flag = 1;
						}
						
						AnchorDefendEntity defend = new AnchorDefendEntity();
						defend.setUserid(order.getUser_id());
						defend.setAnchorid(order.getAnchorId());
						defend.setLevel(guard.getLevel());
						defend.setStart_date(Tools.getDate(Tools.getDate(create_time)));
						if(flag == 0) {
							defend.setEnd_date(Tools.getdate(create_time, guard.getDays()-1));
						} else {
							defend.setEnd_date(Tools.getdate(gLevels.get(0).getEnd_date(), guard.getDays()));
						}
						defend.setCreate_time(create_time);
						defend.setUpdate_time(create_time);
						anchorDefendContract.insert(defend);
						
						log.setStart_date(Tools.getDate(Tools.getDate(create_time)));
						log.setEnd_date(Tools.getdate(create_time, guard.getDays()-1));
						log.setType(AnchorDefendLogEnum.firstPay.getCode());
					}
					
					//记录对应的日志
					anchorDefendLogContract.insert(log);
					
					//查询是否有对应的低等级有效期内的守护存在
					pageModel.clearAll();
					pageModel.addQuery(Restrictions.eq("userid", order.getUser_id()));
					pageModel.addQuery(Restrictions.eq("anchorid", order.getAnchorId()));
					pageModel.addQuery(Restrictions.lt("level", guard.getLevel()));
					//添加一个确认是否在有效期内的判断字段
					pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
					
					defends = anchorDefendContract.load(pageModel);
					if(Tools.isNotNull(defends) && defends.size() > 0) {
						//对低级有效期内的守护进行延长操作处理     延长有效期为 购买或者延长的天数
						for (AnchorDefendEntity defend : defends) {
							log = new AnchorDefendLogEntity();
							log.setStart_date(defend.getStart_date());
							log.setEnd_date(defend.getEnd_date());
							
							//延长对应的天数   注意此处的延长天数有问题需要重新进行计算
							//计算有效期剩余天数
							int days = differentDaysByDate(Tools.getDate(Tools.getDate(create_time)), defend.getEnd_date());
							defend.setEnd_date(Tools.getdate(create_time, guard.getDays() + days));
							defend.setUpdate_time(create_time);
							anchorDefendContract.update(defend);
							
							//处理延长记录日志
							log.setUserid(order.getUser_id());
							log.setAnchorid(order.getAnchorId());
							log.setOrderid(orderid);
							log.setLevel(guard.getLevel());
							log.setType(AnchorDefendLogEnum.extend.getCode());
							log.setDays(guard.getDays());
							log.setCreate_time(create_time);
							anchorDefendLogContract.insert(log);
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	private int differentDaysByDate(Date before,Date after) {   
		int days = (int) ((after.getTime() - before.getTime()) / (1000*3600*24));   
		return days;
	}
	
	@Override
	public boolean userIsAnchorDefend(long userid, long anchorid) {
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
			//添加一个确认是否在有效期内的判断字段
			pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
			long count = anchorDefendContract.count(pageModel);
			if(count > 0)
				return true;
		} catch (Exception e) {
			
		}
		return false;
	}
	
	@Override
	public List<AnchorDefendTopDto> anchorTop4Defend(long anchorid) {
		List<AnchorDefendTopDto> dtos = null;
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addPageField(0, 3);
			pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
			//添加一个确认是否在有效期内的判断字段
			pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
			pageModel.addProjection(Projections.max("level").as("l"));
			pageModel.addProjection(Projections.groupProperty("userid"));
			pageModel.desc("l");
			List<Map<String, Object>> maps = anchorDefendContract.loadGroupBy(pageModel);
			if (Tools.isNotNull(maps)) {
				dtos = new ArrayList<AnchorDefendTopDto>();
				for (Map<String, Object> map : maps) {
					Long userid = Tools.parseLong(map.get("userid"));
					UserBO bo = userAgent.findById(userid);
					if(Tools.isNotNull(bo)) {
						Long level = Tools.parseLong(map.get("l"));
						GuardVipCategoryEntity guard = guardVipCategoryContract.findByProperty("level", level);
						if(Tools.isNotNull(guard)) {
							//处理数据拼接操作处理
							AnchorDefendTopDto dto = new AnchorDefendTopDto();
							dto.setGuardLevel(Const.getCdn(guard.getIcon_back()));
							dto.setLevel(level.intValue());
							if(Tools.isNotNull(bo.getPhoto())) {
								dto.setPhoto(Const.getCdn(bo.getPhoto()));
							}
							//拼接亲密值数据
							pageModel.clearAll();
							pageModel.addQuery(Restrictions.eq("anchor_userid", anchorid));
							pageModel.addQuery(Restrictions.eq("userid", userid));
							List<AnchorIntimateRankingsEntity> ranks = anchorIntimateRankingsContract.load(pageModel);
							if(Tools.isNotNull(ranks)) {
								dto.setClose(ranks.get(0).getTotal_money());
							}
							dtos.add(dto);
						}
					}
				}
				//进行排序处理
				dtos = dtos.stream().sorted(Comparator.comparing(AnchorDefendTopDto::getLevel,Comparator.reverseOrder()).thenComparing(Comparator.comparing(AnchorDefendTopDto::getClose,Comparator.reverseOrder()))).collect(Collectors.toList());
			}
		} catch (Exception e) {
			
		}
		return dtos;
	}

	@Override
	public List<AnchorDefendPayDto> expiryAnchorDefend(long userid, long anchorid) {
		List<AnchorDefendPayDto> dtos = null;
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
			//添加一个确认是否在有效期内的判断字段
			pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
			pageModel.asc("level");
			List<AnchorDefendEntity> defends = anchorDefendContract.load(pageModel);
			if(Tools.isNotNull(defends) && defends.size() > 0) {
				dtos = new ArrayList<>();
				for (AnchorDefendEntity defend : defends) {
					GuardVipCategoryEntity guard = guardVipCategoryContract.findByProperty("level", defend.getLevel());
					if(Tools.isNotNull(guard)) {
						AnchorDefendPayDto dto = new AnchorDefendPayDto();
						dto.setName(guard.getName());
						dto.setDate(Tools.getDate(defend.getEnd_date()));
						dtos.add(dto);
					}
				}
			}
		} catch (Exception e) {
			
		}
		return dtos;
	}
	
	
	@Override
	public GuardVipCategoryEntity getCurrentAnchorDefendByUser(long userid, long anchorid) {
		GuardVipCategoryEntity dtos = null;
		try {
			PageModel pageModel = PageModel.getLimitModel(0,1);
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
			//添加一个确认是否在有效期内的判断字段
			pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
			pageModel.desc("level");
			List<AnchorDefendEntity> defends = anchorDefendContract.load(pageModel);
			if(Tools.isNotNull(defends) && defends.size() > 0) {
				GuardVipCategoryEntity guard = guardVipCategoryContract.findByProperty("level", defends.get(0).getLevel());
				if(Tools.isNotNull(guard)) {
					dtos = guard;
				}
			}
		} catch (Exception e) {
			
		}
		return dtos;
	}

	@Override
	public List<AnchorDefendUserItemDto> getUserAnchorDefend(long userid) {
		List<AnchorDefendUserItemDto> dtos = null;
		//获取有效期内最长的一个做为展示结果
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userid));
			pageModel.addProjection(Projections.max("end_date").as("d"));
			pageModel.addProjection(Projections.groupProperty("anchorid"));
			pageModel.asc("d");
			List<Map<String, Object>> maps = anchorDefendContract.loadGroupBy(pageModel);
			if (Tools.isNotNull(maps)) {
				dtos = new ArrayList<AnchorDefendUserItemDto>();
				for (Map<String, Object> map : maps) {
					long anchorid = Tools.parseLong(map.get("anchorid"));
					Object date = map.get("d");
					if(anchorid > 0 && Tools.isNotNull(date)) {
						AnchorDefendUserItemDto dto = new AnchorDefendUserItemDto();
						dto.setAnchorid(anchorid);
						dto.setDate(date.toString());
						dtos.add(dto);
					}
				}
			}
		} catch (Exception e) {
			
		}
		return dtos;
	}

	@Override
	public List<AnchorDefendItemDto> getAnchorDefends(long anchorid) {
		List<AnchorDefendItemDto> dtos = null;
		try {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("anchorid", anchorid));
			//添加一个确认是否在有效期内的判断字段
			pageModel.addQuery(Restrictions.ge("end_date", Tools.getDate()));
			pageModel.addProjection(Projections.max("level").as("l"));
			pageModel.addProjection(Projections.groupProperty("userid"));
			pageModel.desc("l");
			List<Map<String, Object>> maps = anchorDefendContract.loadGroupBy(pageModel);
			if (Tools.isNotNull(maps)) {
				dtos = new ArrayList<AnchorDefendItemDto>();
				for (Map<String, Object> map : maps) {
					long userid = Tools.parseLong(map.get("userid"));
					long level = Tools.parseLong(map.get("l"));
					if(userid > 0 && level > 0) {
						AnchorDefendItemDto dto = new AnchorDefendItemDto();
						dto.setUserid(userid);
						dto.setLevel((int)level);
						dtos.add(dto);
					}
				}
			}
		} catch (Exception e) {
			
		}
		return dtos;
	}

}
