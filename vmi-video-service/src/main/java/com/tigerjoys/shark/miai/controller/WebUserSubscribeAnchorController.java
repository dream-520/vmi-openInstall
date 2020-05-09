package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.annotations.TestEncrypt;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.AnchorSubscribeStateEnum;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.dto.service.WebSubscribeAnchorDetailDto;
import com.tigerjoys.shark.miai.dto.service.WebSubscribeIndexDto;
import com.tigerjoys.shark.miai.dto.service.WebSubscribeUserDetailDto;
import com.tigerjoys.shark.miai.dto.service.WebSubscribeUserDto;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeDetailContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSuperSubscribeUserContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeDetailEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorSuperSubscribeUserEntity;
import com.tigerjoys.shark.miai.utils.Helper;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * H5版一键预约
 * @author shiming
 */
@Controller
//@TestEncrypt("2aLQ0DC5fftG9VUKZm76P6nRBq5qUC2ZmfPaxlWKfYsrZtPcFij5Vsdas7hTRKtWpCBBAC5BZWIvytZ5xvU4cjkj9068aSNCaEdSxpUgJUHc2YAmknqRo3BNd/758kCItAHDgMi6NGC+QDc8O+TSeVtxI5SIVx7xOB5fhTJFg91bzjOtF+zmVMpTOsl8yX79BTadOGV1cGl2nIWsA1xGVf6rg7fDpx/16O0RFmnambfrwMnSn4PIe2Mqw6zh8voYhc9qgWKUCTPjoR5qYb453e8XViaApoXPfKJY/A2pZIsp26HunUwIhbBXsQDGgRT0bJrBuu2mg0X9xIvQiI8IU8d9YkPJqa7O7eDAmdR1Cf+u3PBXGrPywFFeGDqjEPGvEzEDZ9x9CXhBMzYCXBBN7LD4kb2hkJ9KDBGD4ESbWXrMRhkIm65IRJQsrXzQFRSmCnCK/2NFROz7KZKAF7R4bny33TMkeV4j+qLtzv27pYC4xqZnzgngBY4aVAgwKXGKVpxvpZ29RNidC5qYI/X71g==")
@RequestMapping(value = "/web/subscribe")
public class WebUserSubscribeAnchorController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAnchorSuperSubscribeContract anchorSuperSubscribeContract;
	
	@Autowired
	private IAnchorSuperSubscribeUserContract anchorSuperSubscribeUserContract;
	
	@Autowired
	private IAnchorSuperSubscribeDetailContract anchorSuperSubscribeDetailContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	private IUserOnlineStateAgent userOnlineStateAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.SYS_CONFIG_CACHE)
	private CacheRedis cacheRedis;
	
	@Login
	@RequestMapping(value = "/index", produces = Produce.TEXT_HTML)
	public String subscribeIndex(Model model) throws Exception {
		//给对应的页面设置加密头数据
		model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
		//获取对应的金牌预约的大v数据
		PageModel pageModel = PageModel.getPageModel();
		pageModel.desc("id");
		List<AnchorSuperSubscribeEntity> list = anchorSuperSubscribeContract.load(pageModel);
		List<WebSubscribeIndexDto> anchors = null;
		if(Tools.isNotNull(list) && list.size() > 0) {
			List<Long> ids = new ArrayList<>();
			for (AnchorSuperSubscribeEntity item : list) {
				ids.add(item.getAnchorid());
			}
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", ids));
			List<AnchorOnlineEntity> items = anchorOnlineContract.load(pageModel);
			if(Tools.isNotNull(items)) {
				Map<Long, AnchorOnlineEntity> map = new HashMap<Long, AnchorOnlineEntity>();
				for (AnchorOnlineEntity item : items) {
					map.put(item.getUserid(), item);
				}
				anchors = new ArrayList<WebSubscribeIndexDto>();
				for (AnchorSuperSubscribeEntity item : list) {
					AnchorOnlineEntity anchor = map.get(item.getAnchorid());
					if(Tools.isNotNull(anchor)) {
						WebSubscribeIndexDto dto = new WebSubscribeIndexDto();
						dto.setUserid(anchor.getUserid()+"");
						dto.setNickname(anchor.getNickname());
						dto.setPhoto(ServiceHelper.getUserSmallPhoto(anchor.getPhoto()));
						anchors.add(dto);
					}
				}
			}
		}
		model.addAttribute("anchors", anchors);
		return "subscribe/appointment";
	}
	
	@NoSign
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value = "/index/commit", produces=Produce.TEXT_JSON)
	@ResponseBody
	public ActionResult commitSubscribe(@RequestBody(required=false) String body) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		JSONObject json = JsonHelper.toJsonObject(body);
		if(Tools.isNotNull(json)) {
			int moneys = json.getIntValue("moneys");
			if(userId > 0 && moneys > 0) {
				//检测是否已经有过全局的预约
				String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_FLAG_PREFIX + userId;
				String value = cacheRedis.get(key);
				if(Tools.isNull(value)) {
					//检测对应的钻石是否充足
					long diamond = userDiamondAgent.getDiamondBalance(userId);
					if(diamond >= moneys) {
						//检测金牌预约主播是否有预约关系
						PageModel pageModel = PageModel.getPageModel();
						pageModel.desc("id");
						List<AnchorSuperSubscribeEntity> list = anchorSuperSubscribeContract.load(pageModel);
						if(Tools.isNotNull(list)) {
							List<Long> anchorids = new ArrayList<>();
							for (AnchorSuperSubscribeEntity item : list) {
								anchorids.add(item.getAnchorid());
							}
							List<Long> canSubscribes = new ArrayList<>();
							if(Tools.isNotNull(anchorids)) {
								for (Long anchorid : anchorids) {
									String keys = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userId + "_" + anchorid;
									String exist = cacheRedis.get(keys);
									if(Tools.isNull(exist)) {
										//此主播尚未被预约过
										canSubscribes.add(anchorid);
									}
								}
							}
							
							if(Tools.isNotNull(canSubscribes) && canSubscribes.size() > 0) {
								//首先进行设置全局预约
								long succ = cacheRedis.setnx(key, userId+"");
								if(succ > 0) {
									//已经成功设置了预约状态
									cacheRedis.expire(key, AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_TIME);
									//进行尝试扣费操作处理
									DiamondResultDto<Long> result = userDiamondAgent.changeDiamondAccount(userId, moneys, null, UserDiamondAccountLogTypeEnum.user_super_subscribe_anchor_cost.getCode(), 0, null, String.valueOf(IdGenerater.generateId()), UserDiamondAccountLogTypeEnum.user_super_subscribe_anchor_cost.getDesc());
									if(AgentErrorCodeEnum.success.getCode() == result.getCode()) {
										pageModel.clearAll();
										pageModel.addQuery(Restrictions.eq("userid", userId));
										long count = anchorSuperSubscribeUserContract.count(pageModel);
										Date date = new Date();
										//生成对应的预约处理状态信息
										AnchorSuperSubscribeUserEntity entity = new AnchorSuperSubscribeUserEntity();
										entity.setUserid(userId);
										entity.setState(0);
										entity.setDiamond(moneys);
										entity.setEnd_time(Tools.getDateTime(Tools.getDateTime(date.getTime() + 30*60*1000)));
										entity.setCreate_time(date);
										entity.setUpdate_time(date);
										entity.setTimes((int)(count+1));
										anchorSuperSubscribeUserContract.insert(entity);
										List<AnchorSuperSubscribeDetailEntity> details = new ArrayList<>();
										for (Long canSubscribe : canSubscribes) {
											AnchorSuperSubscribeDetailEntity detail = new AnchorSuperSubscribeDetailEntity();
											detail.setAnchorid(canSubscribe);
											detail.setSubscribe(entity.getId());
											detail.setUserid(userId);
											detail.setState(0);
											detail.setCreate_time(date);
											detail.setEnd_time(Tools.getDateTime(Tools.getDateTime(date.getTime() + 30*60*1000)));
											detail.setUpdate_time(date);
											details.add(detail);
											
											//同时设置对应的预约标识
											String keys = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userId + "_" + canSubscribe;
											Map<String, Object> param = new  HashMap<String, Object>();
											//标识已经进行了一对一预约  0 一对一   1  全局预约
											param.put("flag", 1);
											//预约的类型 0 视频 1 音频
											param.put("type", 0);
											param.put("diamond", moneys);
											param.put("id", entity.getId());
											cacheRedis.setex(keys, AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_TIME, JsonHelper.toJson(param));
										}
										anchorSuperSubscribeDetailContract.insertAll(details);
										//预约成功发送消息 和 通知栏业务
										ExecutorServiceHelper.executor.execute(new UserSuperSubscribeAnchorThread(userId, canSubscribes));
										//返回对应的预约详情id值
										return ActionResult.success(Const.WEB_SITE+"/web/subscribe/user/detail/"+entity.getId());
									} else {
										//进行扣除钻石失败了
										cacheRedis.delete(key);
										//设置返回预约金不足
										return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_diamond_not_enough);
									}
								} else {
									//说明进行重复的预约操作了
									return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_error);
								}
							} else {
								//没有可以预约的主播了
								return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_anchor_not_enough);
							}
						} else {
							//没有可以预约的主播了
							return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_anchor_not_enough);
						}
					} else {
						//返回预约金不足
						return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_diamond_not_enough);
					}
				} else {
					//已经进行预约过了
					return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_exist);
				}
			}
		}
		return ActionResult.fail(ErrorCodeEnum.web_anchor_subscribe_error);
	}
	
	@Login
	@RequestMapping(value = "/user", produces = Produce.TEXT_HTML)
	public String userSubscribe(Model model) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		List<WebSubscribeUserDto> subscribes = null;
		if(userId > 0) {
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addQuery(Restrictions.eq("userid", userId));
			pageModel.desc("id");
			List<AnchorSuperSubscribeUserEntity> superSubscribes = anchorSuperSubscribeUserContract.load(pageModel);
			if(Tools.isNotNull(superSubscribes)) {
				subscribes = new ArrayList<>();
				long current = System.currentTimeMillis();
				//拼接对应的预约数据
				for (AnchorSuperSubscribeUserEntity superSubscribe : superSubscribes) {
					WebSubscribeUserDto dto = new WebSubscribeUserDto();
					dto.setId(superSubscribe.getId());
					dto.setCount("预约"+superSubscribe.getTimes());
					//根据当前的预约状态
					if(superSubscribe.getState() == AnchorSubscribeStateEnum.subscribe.getCode()) {
						//在预约中状态的需要进一步根据对应的时间来确认预约状态
						//如果对应的时间超时 那也是预约失败 
						long end = superSubscribe.getEnd_time().getTime();
						if(current > end) {
							//其实已经处理预约失败的状态了
							dto.setState(AnchorSubscribeStateEnum.fail.getCode());
						} else {
							int min = (int) Math.ceil((end-current)/1000/60);
							dto.setState(AnchorSubscribeStateEnum.subscribe.getCode());
							dto.setSurplus("还剩余"+min+"分钟");
						}
					} else if(superSubscribe.getState() == AnchorSubscribeStateEnum.fail.getCode()){
						dto.setState(AnchorSubscribeStateEnum.fail.getCode());	
					} else if(superSubscribe.getState() == AnchorSubscribeStateEnum.success.getCode()){
						dto.setState(AnchorSubscribeStateEnum.success.getCode());
					}
					dto.setTime(Tools.getFormatDate(superSubscribe.getCreate_time(), "MM月dd日  HH:mm"));
					subscribes.add(dto);
				}
			}
		}
		model.addAttribute("subscribes", subscribes);
		//给客户端传递是否有集合元素
		if(Tools.isNotNull(subscribes) && subscribes.size() > 0) {
			model.addAttribute("empty", 0);
		} else {
			model.addAttribute("empty", 1);
		}
		return "subscribe/myAppointment";
	}
	
	@Login
	@RequestMapping(value = "/user/detail/{id}", produces = Produce.TEXT_HTML)
	public String userSubscribeDetail(Model model, @PathVariable String id) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		List<WebSubscribeUserDetailDto> dtos = null;
		if(userId > 0) {
			//根据对应的id查询对应的预约统计信息
			AnchorSuperSubscribeUserEntity superSubscribe = anchorSuperSubscribeUserContract.findById(Long.parseLong(id));
			if(Tools.isNotNull(superSubscribe) && superSubscribe.getUserid().longValue() == userId) {
				long current = System.currentTimeMillis();
				if(superSubscribe.getState() == AnchorSubscribeStateEnum.subscribe.getCode()) {
					//在预约中状态的需要进一步根据对应的时间来确认预约状态
					//如果对应的时间超时 那也是预约失败 
					long end = superSubscribe.getEnd_time().getTime();
					if(current > end) {
						//其实已经处理预约失败的状态了
						model.addAttribute("subTitle", "预约失败");
					} else {
						int min = (int) Math.ceil((end-current)/1000/60);
						model.addAttribute("subTitle", "剩余时间："+min+"分钟");
					}
				} else if(superSubscribe.getState() == AnchorSubscribeStateEnum.fail.getCode()){
					model.addAttribute("subTitle", "预约失败");	
				} else if(superSubscribe.getState() == AnchorSubscribeStateEnum.success.getCode()){
					model.addAttribute("subTitle", "预约成功");
				}
				model.addAttribute("title", "预约"+superSubscribe.getTimes());
				
				//获取预约详情信息
				PageModel pageModel = PageModel.getPageModel();
				pageModel.addQuery(Restrictions.eq("userid", userId));
				pageModel.addQuery(Restrictions.eq("subscribe", id));
				List<AnchorSuperSubscribeDetailEntity> subscribes = anchorSuperSubscribeDetailContract.load(pageModel);
				if(Tools.isNotNull(subscribes)) {
					//首先查询所有主播对应的信息
					List<Long> userids = new ArrayList<>();
					for (AnchorSuperSubscribeDetailEntity subscribe : subscribes) {
						userids.add(subscribe.getAnchorid());
					}
					pageModel.clearAll();
					pageModel.addQuery(Restrictions.in("userid", userids));
					List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
					Map<Long, AnchorOnlineEntity> map = new HashMap<Long, AnchorOnlineEntity>();
					for (AnchorOnlineEntity anchor : anchors) {
						map.put(anchor.getUserid(), anchor);
					}
					dtos = new ArrayList<>();
					//循环进行封装数据操作处理
					for (AnchorSuperSubscribeDetailEntity subscribe : subscribes) {
						AnchorOnlineEntity anchor = map.get(subscribe.getAnchorid());
						if(Tools.isNotNull(anchor)) {
							WebSubscribeUserDetailDto dto = new WebSubscribeUserDetailDto();
							dto.setUserid(subscribe.getAnchorid()+"");
							dto.setNickname(anchor.getNickname());
							dto.setPhoto(ServiceHelper.getUserSmallPhoto(anchor.getPhoto()));
							//处理在线状态信息
							dto.setOnlineState(anchor.getOnline());
							if(subscribe.getState() == AnchorSubscribeStateEnum.subscribe.getCode()) {
								//在预约中状态的需要进一步根据对应的时间来确认预约状态
								//如果对应的时间超时 那也是预约失败 
								long end = subscribe.getEnd_time().getTime();
								if(current > end) {
									//其实已经处理预约失败的状态了
									dto.setState(AnchorSubscribeStateEnum.fail.getCode());
								} else {
									dto.setState(AnchorSubscribeStateEnum.subscribe.getCode());
								}
							} else if(subscribe.getState() == AnchorSubscribeStateEnum.fail.getCode()){
								dto.setState(AnchorSubscribeStateEnum.fail.getCode());	
							} else if(subscribe.getState() == AnchorSubscribeStateEnum.success.getCode()){
								dto.setState(AnchorSubscribeStateEnum.success.getCode());
							}
							dtos.add(dto);
						}
					}
				}
			}
		}
		model.addAttribute("details", dtos);
		return "subscribe/appointmentV";
	}
	
	@Login
	@RequestMapping(value = "/anchor", produces = Produce.TEXT_HTML)
	public String anchorSubscribeList(Model model) throws Exception {
		long userId = RequestUtils.getCurrent().getUserid();
		List<WebSubscribeAnchorDetailDto> dtos = null;
		if(userId > 0) {
			//获取预约详情信息
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addPageField(0, 25);
			pageModel.addQuery(Restrictions.eq("anchorid", userId));
			pageModel.desc("id");
			List<AnchorSuperSubscribeDetailEntity> subscribes = anchorSuperSubscribeDetailContract.load(pageModel);
			if(Tools.isNotNull(subscribes)) {
				//首先查询所有的用户信息
				Set<Long> userids = new HashSet();
				for (AnchorSuperSubscribeDetailEntity subscribe : subscribes) {
					userids.add(subscribe.getUserid());
				}
				Map<Long, UserBO> map = userAgent.findById(new ArrayList<>(userids));
				//循环进行封装数据操作处理
				dtos = new ArrayList<>();
				long current = System.currentTimeMillis();
				for (AnchorSuperSubscribeDetailEntity subscribe : subscribes) {
					UserBO bo = map.get(subscribe.getUserid());
					if(Tools.isNotNull(bo)) {
						WebSubscribeAnchorDetailDto dto = new WebSubscribeAnchorDetailDto();
						dto.setUserid(bo.getUserid()+"");
						dto.setNickname(bo.getNickname());
						dto.setPhoto(ServiceHelper.getUserSmallPhoto(bo.getPhoto()));
						dto.setOnlineState(userOnlineStateAgent.onlineState(bo.getUserid()));
						if(subscribe.getState() == AnchorSubscribeStateEnum.subscribe.getCode()) {
							//在预约中状态的需要进一步根据对应的时间来确认预约状态
							//如果对应的时间超时 那也是预约失败 
							long end = subscribe.getEnd_time().getTime();
							if(current > end) {
								//其实已经处理预约失败的状态了
								dto.setState(AnchorSubscribeStateEnum.fail.getCode());
							} else {
								int min = (int) Math.ceil((end-current)/1000/60);
								dto.setState(AnchorSubscribeStateEnum.subscribe.getCode());
								dto.setSurplus("还剩余"+min+"分钟");
							}
						} else if(subscribe.getState() == AnchorSubscribeStateEnum.fail.getCode()){
							dto.setState(AnchorSubscribeStateEnum.fail.getCode());
						} else if(subscribe.getState() == AnchorSubscribeStateEnum.success.getCode()){
							dto.setState(AnchorSubscribeStateEnum.success.getCode());
						}
						dto.setTime(Tools.getFormatDate(subscribe.getCreate_time(), "MM月dd日  HH:mm"));
						dtos.add(dto);
					}
				}
			}
		}
		model.addAttribute("details", dtos);
		return "subscribe/resAppointment";
	}
	
	private class UserSuperSubscribeAnchorThread implements Runnable {

		private long userId;
		
		private List<Long> anchorids;
		
		public UserSuperSubscribeAnchorThread(long userId, List<Long> anchorids) {
			this.userId = userId;
			this.anchorids = anchorids;
		}
		
		@Override
		public void run() {
			try {
				UserBO bo = userAgent.findById(userId);
				if(Tools.isNotNull(bo)) {
					//给对应的主播发送预约消息
					for (Long anchorid : anchorids) {
						if(Const.IS_TEST) {
							neteaseAgent.pushOneMessage(131879189602173184L, anchorid, bo.getNickname() + "用户已经预约了和你聊天，赶紧和他联系吧", true);
						} else {
							neteaseAgent.pushOneMessage(65418719477628672L, anchorid, bo.getNickname() + "用户已经预约了和你聊天，赶紧和他联系吧", true);
						}
						//给对应的主播发送预约通知栏
						UserBO userBO = userAgent.findById(anchorid);
						if(Tools.isNotNull(userBO) && Tools.isNotNull(userBO.getClientid())) {
							//此处是直接跳转到对应的H5界面
							//PushParamDto paramC = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.user_subscribe_anchor);
							//paramC.setTitle(bo.getNickname() + "用户已经预约了和你聊天，赶紧和他联系吧");
							//paramC.setContent("赶紧找他聊天吧！");
							//newPushAgent.pushMessageToSingleUser(paramC);
							
							PushParamDto paramC = PushHelper.getPushParamDto(bo, PushTypeEnum.type_goto_H5, PushContentTypeEnum.necessary, NewPushAppTagEnum.sysmessage_H5_fragment);
							paramC.setContent("赶紧找他聊天吧！");
							paramC.setTitle(bo.getNickname() + "用户已经预约了和你聊天，赶紧和他联系吧");
							//paramC.setH5Title(message.getTitle());
							String url = Const.WEB_SITE+"/web/subscribe/anchor";
							paramC.setUrl(url);
						}
					}
				}
			} catch (Exception e) {
				logger.error("给对应的主播发送唤醒消息出现了错误  启动的用户id为:"+userId);
			}
		}
	}
	
}
