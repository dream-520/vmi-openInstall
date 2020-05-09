package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shark.miai.common.enums.IndexActivityAreaEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.IAnchorDynamicPriceAgent;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.IUserSubscribeAnchorAgent;
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
import com.tigerjoys.shark.miai.dto.service.AnchorSubscribeDto;
import com.tigerjoys.shark.miai.dto.service.BtnData;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPage;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPageNew;
import com.tigerjoys.shark.miai.dto.service.Subscribe;
import com.tigerjoys.shark.miai.enums.DlgAndGoPageEnum;
import com.tigerjoys.shark.miai.enums.StaticWebUrlEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorSubscribeContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AnchorSubscribeEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.service.IUserSubscribeAnchorService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

/**
 * 预约业务实现
 * @author shiming
 */
@Service
public class UserSubscribeAnchorServiceImpl implements IUserSubscribeAnchorService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.SYS_CONFIG_CACHE)
	private CacheRedis cacheRedis;
	
	@Autowired
	private IAnchorSubscribeContract anchorSubscribeContract;
	
	@Autowired
	private IUserSubscribeAnchorAgent userSubscribeAnchorAgent;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private IUserOnlineStateAgent userOnlineStateAgent;
	
	@Autowired
	private IAnchorDynamicPriceAgent anchorDynamicPriceAgent;
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Override
	public ActionResult checkSubscribe(long userid, long anchorid) throws Exception {
		//根据提供的用户和主播检测是否有预约关系
		boolean subscribe = userSubscribeAnchorAgent.checkSubscribe(userid, anchorid);
		AnchorSubscribeDto dto = new AnchorSubscribeDto();
		UserBO bo = userAgent.findById(anchorid);
		if(Tools.isNotNull(bo)) {
			dto.setNickName(bo.getNickname());
			dto.setPhoto(ServiceHelper.getUserSmallPhoto(bo.getPhoto()));
		}
		if(subscribe) {
			dto.setDescription("您已经预约了，她会尽快联系你");
			dto.setStatus(1);
		} else {
			int time = 20;
			try {
				SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.APP_ROBOT_COFIG);
				JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
				if(Tools.isNotNull(ctrl)) {
					int subscribeTime = ctrl.getIntValue("subscribeTime");
					if(subscribeTime > 0)
						time = subscribeTime;
				}
			} catch (Exception e) {
				
			}
			dto.setDescription("预约将收取"+time+"分钟的预约费，1小时后大V未回拨将自动返还预约费。");
			dto.setStatus(0);
		}
		return ActionResult.success(dto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult commitSubscribe(long userid, long anchorid) throws Exception {
		UserBO bo = userAgent.findById(userid);
		if(Tools.isNotNull(bo)) {
			DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
			String hintInfo ="";
			//2 检测用户是否是对应的vip用户
			if(bo.vipValue() == 0) {
				//非vip用户
				hintInfo = "现在还不是VIP特色服务身份哦\n开启后可以消息畅聊\n更获得100元话费";
				BtnData chargebtn = new BtnData();
				chargebtn.setBtnName("去开通");
				//chargebtn.setAndroidPageTag(DlgAndGoPageEnum.payVipDlg.getAndroidPage());
				chargebtn.setAndroidPageTag(IndexActivityAreaEnum.webSingle.getAndroidPage());
				chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+StaticWebUrlEnum.USER_VIP_SERVICE_H5.getPath(),"VIP特色服务"));
				BtnData cancelBtn = new BtnData();
				cancelBtn.setBtnName("取消");
				//dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn,cancelBtn));
				dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
				dlgAndGoPage.setHintInfo(hintInfo);
				return ActionResult.success(dlgAndGoPage);
			}
			//1 检测当前时间是否有预约过本主播
			
			//3 检测用户是否有足够的预约金
			AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", anchorid);
			if(Tools.isNotNull(anchor)) {
				int type = 0;
				int price = 0;
				int video = anchor.getVideo_type();
				int audio = anchor.getAudio_type();
				//获取主播的价格类型
				if(video == 1 && audio == 1) {
					//type = 2;
					type = 0;
				} else if(video == 1) {
					type = 0;
				} else if(audio == 1) {
					type = 1;
				}
				//类型为 0为视频  1 为音频  2 全视频  
				if(type == 1) {
					price = anchorDynamicPriceAgent.getAnchorDynamicPrice(userid, anchor.getAudio_price());
				} else {
					price = anchorDynamicPriceAgent.getAnchorDynamicPrice(userid, anchor.getPrice());
				}
				int time = 20;
				try {
					SysConfigEntity config = sysConfigContract.findByProperty("name", com.tigerjoys.shark.miai.agent.constant.Const.APP_ROBOT_COFIG);
					JSONObject ctrl = JsonHelper.toJsonObject(config.getValue());
					if(Tools.isNotNull(ctrl)) {
						int subscribeTime = ctrl.getIntValue("subscribeTime");
						if(subscribeTime > 0)
							time = subscribeTime;
					}
				} catch (Exception e) {
					
				}
				//尝试进行扣钻处理
				DiamondResultDto<Long> result = userDiamondAgent.changeDiamondAccount(userid, price * time, null, UserDiamondAccountLogTypeEnum.user_subscribe_anchor_cost.getCode(),0, null, String.valueOf(IdGenerater.generateId()), UserDiamondAccountLogTypeEnum.user_subscribe_anchor_cost.getDesc());
				if(AgentErrorCodeEnum.success.getCode() == result.getCode()) {
					//预约成功操作
					hintInfo = "预约成功\n大V将尽快回拨给你";
					BtnData cancelBtn = new BtnData();
					cancelBtn.setBtnName("确定");
					dlgAndGoPage.setBtnDataList(Arrays.asList(cancelBtn));
					dlgAndGoPage.setHintInfo(hintInfo);
					AnchorSubscribeEntity t = new AnchorSubscribeEntity();
					t.setUserid(userid);
					t.setAnchorid(anchorid);
					t.setType(type);
					t.setDiamond(price * time);
					t.setCreate_time(new Date());
					//设置1小时有效期
					t.setEnd_time(Tools.getDateTime(Tools.getDateTime(new Date().getTime() + 1*60*60*1000)));
					t.setUpdate_time(new Date());
					anchorSubscribeContract.insert(t);
					
					//处理预约缓存业务
					//String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + type + "_" + userid + "_" + anchorid;
					//cacheRedis.setex(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, String.valueOf(price * 3));
					
					String key = AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX + userid + "_" + anchorid;
					Map<String, Object> param = new  HashMap<String, Object>();
					//标识已经进行了一对一预约  0 一对一   1  全局预约
					param.put("flag", 0);
					//预约的类型 0 视频 1 音频
					param.put("type", type);
					param.put("id", t.getId());
					param.put("diamond", price * time);
					cacheRedis.setex(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE, JsonHelper.toJson(param));
					
					//预约成功发送消息 和 通知栏业务   处理小红点业务 
					ExecutorServiceHelper.executor.execute(new UserSubscribeAnchorThread(bo.getNickname(), anchorid));
					return ActionResult.success(dlgAndGoPage);
				} else {
					//预约金不足 需要展示充值弹窗
					hintInfo = "你的钻石不足，快快充值";
					BtnData chargebtn = new BtnData();
					chargebtn.setBtnName("去充值");
					chargebtn.setAndroidPageTag(DlgAndGoPageEnum.webSingle.getAndroidPage());
					chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+"/api/income/zuanList","我的钱包"));
					dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
					dlgAndGoPage.setHintInfo(hintInfo);
					return ActionResult.success(dlgAndGoPage);
				}
			}
		}
		return ActionResult.fail();
	}
	
	@Override
	public ActionResult getSubscribes(long userid) throws Exception {
		//根据提供的用户的id标识来区分获取对应的列表数据 
		UserBO bo = userAgent.findById(userid);
		if(Tools.isNotNull(bo)) {
			if(bo.isWaiter()) {
				return getAnchorSubscribes(userid);
			} else {
				return getUserSubscribes(userid);
			}
		}
		return ActionResult.fail();
	}
	
	/**
	 * 用户获取预约的主播
	 * @param userid
	 */
	private ActionResult getUserSubscribes(long userid) throws Exception {
		//根据提供的类型获取对应的50条预约信息
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addPageField(0, 50);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		//按照id倒序排列
		pageModel.desc("id");
		List<AnchorSubscribeEntity> subscribes = anchorSubscribeContract.load(pageModel);
		List<Subscribe> datas = null;
		if(Tools.isNotNull(subscribes) && subscribes.size() > 0) {
			datas = new ArrayList<>();
			//首先查询所有主播对应的信息
			Set<Long> userids = new HashSet();
			for (AnchorSubscribeEntity subscribe : subscribes) {
				userids.add(subscribe.getAnchorid());
			}
			pageModel.clearAll();
			pageModel.addQuery(Restrictions.in("userid", userids));
			List<AnchorOnlineEntity> anchors = anchorOnlineContract.load(pageModel);
			Map<Long, AnchorOnlineEntity> map = new HashMap<Long, AnchorOnlineEntity>();
			for (AnchorOnlineEntity anchor : anchors) {
				map.put(anchor.getUserid(), anchor);
			}
			//循环进行封装数据操作处理
			Subscribe data = null;
			long current = System.currentTimeMillis();
			for (AnchorSubscribeEntity subscribe : subscribes) {
				AnchorOnlineEntity anchor = map.get(subscribe.getAnchorid());
				if(Tools.isNotNull(anchor)) {
					//拼接对应的数据
					data = new Subscribe();
					data.setUserId(anchor.getUserid());
					data.setNickName(anchor.getNickname());
					data.setPhoto(ServiceHelper.getUserSmallPhoto(anchor.getPhoto()));
					
					//处理在线状态信息
					data.setAnchorStatus(anchor.getOnline());
					
					//设置是否有对应的按钮---用户端看不到对应的拨打按钮
					data.setBtnAction(0);
					
					//拼接对应的预约状态信息
					//首先根据状态确定 是否已经明确了预约类型
					if(subscribe.getState() == AnchorSubscribeStateEnum.subscribe.getCode()) {
						//在预约中状态的需要进一步根据对应的时间来确认预约状态
						//如果对应的时间超时 那也是预约失败 
						long end = subscribe.getEnd_time().getTime();
						if(current > end) {
							//其实已经处理预约失败的状态了
							data.setBackText1(AnchorSubscribeStateEnum.fail.getDesc());
							data.setTextColor(AnchorSubscribeStateEnum.fail.getColor());
						} else {
							int min = (int) Math.ceil((end-current)/1000/60);
							data.setBackText1(AnchorSubscribeStateEnum.subscribe.getDesc());
							data.setTextColor(AnchorSubscribeStateEnum.subscribe.getColor());
							data.setBackText2("还剩余"+min+"分钟");
						}
					} else if(subscribe.getState() == AnchorSubscribeStateEnum.fail.getCode()){
						data.setBackText1(AnchorSubscribeStateEnum.fail.getDesc());
						data.setTextColor(AnchorSubscribeStateEnum.fail.getColor());
					} else if(subscribe.getState() == AnchorSubscribeStateEnum.success.getCode()){
						data.setBackText1(AnchorSubscribeStateEnum.success.getDesc());
						data.setTextColor(AnchorSubscribeStateEnum.success.getColor());
					}
					data.setBackText3(Tools.getFormatDate(subscribe.getCreate_time(), "MM月dd日  HH时mm分"));
					datas.add(data);
				}
			}
		}
		return ActionResult.success(datas);
	}
	
	/**
	 * 主播获取预约的用户
	 */
	private ActionResult getAnchorSubscribes(long userid) throws Exception {
		//根据提供的类型获取对应的50条预约信息
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addPageField(0, 50);
		pageModel.addQuery(Restrictions.eq("anchorid", userid));
		//按照id倒序排列
		pageModel.desc("id");
		List<AnchorSubscribeEntity> subscribes = anchorSubscribeContract.load(pageModel);
		List<Subscribe> datas = null;
		if(Tools.isNotNull(subscribes) && subscribes.size() > 0) {
			//有预约检测一下是否需要清除未读取的预约
			String unread = cacheRedis.hget(AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_UNREAD, userid+"");
			if(Tools.isNotNull(unread)) {
				int unreadCount = Integer.parseInt(unread);
				int readCount = 0;
				String read = cacheRedis.hget(AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_READ, userid+"");
				if(Tools.isNotNull(read)) {
					readCount = Integer.parseInt(read);
				}
				if(unreadCount > readCount) {
					//标识有未读的预约消息
					cacheRedis.hset(AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_READ, userid+"", unreadCount+"");
				}
			}
			
			datas = new ArrayList<>();
			//首先查询所有的用户信息
			Set<Long> userids = new HashSet();
			for (AnchorSubscribeEntity subscribe : subscribes) {
				userids.add(subscribe.getUserid());
			}
			Map<Long, UserBO> map = userAgent.findById(new ArrayList<>(userids));
			//循环进行封装数据操作处理
			Subscribe data = null;
			long current = System.currentTimeMillis();
			for (AnchorSubscribeEntity subscribe : subscribes) {
				UserBO bo = map.get(subscribe.getUserid());
				if(Tools.isNotNull(bo)) {
					//拼接对应的数据
					data = new Subscribe();
					data.setUserId(bo.getUserid());
					data.setNickName(bo.getNickname());
					data.setPhoto(ServiceHelper.getUserSmallPhoto(bo.getPhoto()));
					//处理在线状态信息
					data.setOnlineStatue(userOnlineStateAgent.onlineState(bo.getUserid()));
					//设置是否有对应的按钮---用户端看不到对应的拨打按钮
					if (subscribe.getType() == 1 ) {
						data.setBtnAction(1);
					} else {
						data.setBtnAction(2);
					}

					//拼接对应的预约状态信息
					//首先根据状态确定 是否已经明确了预约类型
					if(subscribe.getState() == AnchorSubscribeStateEnum.subscribe.getCode()) {
						//在预约中状态的需要进一步根据对应的时间来确认预约状态
						//如果对应的时间超时 那也是预约失败 
						long end = subscribe.getEnd_time().getTime();
						if(current > end) {
							//其实已经处理预约失败的状态了
							data.setBackText1(AnchorSubscribeStateEnum.fail.getDesc());
							data.setTextColor(AnchorSubscribeStateEnum.fail.getColor());
						} else {
							int min = (int) Math.ceil((end-current)/1000/60);
							data.setBackText1(AnchorSubscribeStateEnum.subscribe.getDesc());
							data.setTextColor(AnchorSubscribeStateEnum.subscribe.getColor());
							data.setBackText2("还剩余"+min+"分钟");
						}
					} else if(subscribe.getState() == AnchorSubscribeStateEnum.fail.getCode()){
						data.setBackText1(AnchorSubscribeStateEnum.fail.getDesc());
						data.setTextColor(AnchorSubscribeStateEnum.fail.getColor());
					} else if(subscribe.getState() == AnchorSubscribeStateEnum.success.getCode()){
						data.setBackText1(AnchorSubscribeStateEnum.success.getDesc());
						data.setTextColor(AnchorSubscribeStateEnum.success.getColor());
					}
					
					data.setBackText3(Tools.getFormatDate(subscribe.getCreate_time(), "MM月dd日  HH时mm分"));
					datas.add(data);
				}
			}
		}
		return ActionResult.success(datas);
	}

	private class UserSubscribeAnchorThread implements Runnable {

		private String nickname;
		
		private long userid;
		
		public UserSubscribeAnchorThread(String nickname, long userid) {
			this.nickname = nickname;
			this.userid = userid;
		}
		
		@Override
		public void run() {
			try {
				//给对应的主播发送预约消息
				if(Const.IS_TEST) {
					neteaseAgent.pushOneMessage(131879189602173184L, userid, nickname + "用户已经预约了和你聊天，赶紧和他联系吧", true);
				} else {
					neteaseAgent.pushOneMessage(65418719477628672L, userid, nickname + "用户已经预约了和你聊天，赶紧和他联系吧", true);
				}
				//给对应的主播发送预约通知栏
				UserBO userBO = userAgent.findById(userid);
				if(Tools.isNotNull(userBO) && Tools.isNotNull(userBO.getClientid())) {
					PushParamDto paramC = PushHelper.getPushParamDto(userBO, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.user_subscribe_anchor);
					paramC.setTitle(nickname + "用户已经预约了和你聊天，赶紧和他联系吧");
					paramC.setContent("赶紧找他聊天吧！");
					newPushAgent.pushMessageToSingleUser(paramC);
				}
				//处理小红点业务
				cacheRedis.hincrBy(AgentRedisCacheConst.REDIS_USER_SUBSCRIBE_ANCHOR_UNREAD, userid+"", 1);
			} catch (Exception e) {
				logger.error("给对应的主播发送唤醒消息出现了错误  启动的用户id为:"+userid);
			}
		}
	}
	
}
