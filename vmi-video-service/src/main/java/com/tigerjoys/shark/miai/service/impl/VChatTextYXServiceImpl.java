package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.shark.miai.common.delayqueue.Message;
import org.shark.miai.common.enums.AppNameEnum;
import org.shark.miai.common.enums.IndexActivityAreaEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.BeanUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.web.context.RequestHeader;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IAnchorDefendAgent;
import com.tigerjoys.shark.miai.agent.IAnchorRecvUserAgent;
import com.tigerjoys.shark.miai.agent.IIOSUserSmsAgent;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.TextAudioLabelDto;
import com.tigerjoys.shark.miai.agent.dto.TextAutioMsgDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.AnchorOnlineStateEnum;
import com.tigerjoys.shark.miai.agent.enums.AnchorRecvUserEnum;
import com.tigerjoys.shark.miai.agent.enums.RedFlowerAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.TextAudioMsgEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.neteasecheck.INeteaseAudioCheck;
import com.tigerjoys.shark.miai.agent.neteasecheck.INeteaseTextCheck;
import com.tigerjoys.shark.miai.agent.neteasecheck.NeteaseTextCheckLabelEnum;
import com.tigerjoys.shark.miai.dto.service.BtnData;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPage;
import com.tigerjoys.shark.miai.dto.service.DlgAndGoPageNew;
import com.tigerjoys.shark.miai.dto.service.UserBaseInfo;
import com.tigerjoys.shark.miai.enums.DlgAndGoPageEnum;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;
import com.tigerjoys.shark.miai.enums.StaticWebUrlEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppChannelContract;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserContract;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserTextChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.contract.IFreeVideoChatExperienceContract;
import com.tigerjoys.shark.miai.inter.contract.IPayUserRecordContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserAudioChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTextChatHistoryContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTextChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppChannelEntity;
import com.tigerjoys.shark.miai.inter.entity.CopyUserTextChatInfoLogEntity;
import com.tigerjoys.shark.miai.inter.entity.FreeVideoChatExperienceEntity;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.PayUserRecordEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatHistoryEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTextChatInfoLogEntity;
import com.tigerjoys.shark.miai.service.IChannelCheckService;
import com.tigerjoys.shark.miai.service.IMsgSceneService;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;
import com.tigerjoys.shark.miai.utils.ServiceHelper;

@Service
public class VChatTextYXServiceImpl implements IVChatTextYXService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private INeteaseAgent neteaseAgent;

	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;

	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IRedFlowerAgent redFlowerAgent;
	
	@Autowired
	private INeteaseTextCheck neteaseTextCheck;
	
	@Autowired
	private ISysConfigContract sysConfigContract;
	
	@Autowired
	private IUserTextChatInfoLogContract userTextChatInfoLogContract;
	
	@Autowired
	private IMsgSceneService msgSceneService;
	
	
	@Autowired
	private IChannelCheckService channelCheckService;
	
	@Autowired
	private TextChatSendMsgConsumer textChatSendMsgConsumer;
	
	@Autowired
	private IUserTextChatHistoryContract userTextChatHistoryContract;
	
	@Autowired
	private IFreeVideoChatExperienceContract freeVideoChatExperienceContract;
	
	@Autowired
	private IAnchorDefendAgent anchorDefendAgent;
	
	@Autowired
	private IAnchorRecvUserAgent anchorRecvUserAgent;
	
	
	@Autowired
	private IUserAudioChatInfoLogContract userAudioChatInfoLogContract;
	
	@Autowired
	private INeteaseAudioCheck neteaseAudioCheck;
	
	@Autowired
	private ICopyUserTextChatInfoLogContract copyUserTextChatInfoLogContract;
	
	@Autowired
	private ICopyUserContract copyUserContract;
	
	@Autowired
	private IPayUserRecordContract payUserRecordContract;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private IIOSUserSmsAgent iOSUserSmsAgent;
	
	
	@Autowired
	private IAppChannelContract appChannelContract;
	
	
	
	private int CHECK_STATUS_SUCCESS  = TextAudioMsgEnum.success.getCode();
	
	private int CHECK_STATUS_FAIL  = TextAudioMsgEnum.fail.getCode();
	
	private final int  CHAT_TEXT_PAYR_EDFLOWER = 1;
	
	private final int  CHAT_AUDIO_PIC_PAYR_EDFLOWER = 2;

	@Override
	public ActionResult textChatCheck(long userId, Long toUserId) throws Exception {
		UserBO user = userAgent.findById(userId);
		UserBO other = userAgent.findById(toUserId);
		if(Tools.isNull(user) || Tools.isNull(other)){
			return ActionResult.fail(ErrorCodeEnum.anchor_inexistence);
		}
		Map<String, Object> data = new HashMap<>();
		UserBaseInfo userInfo = new UserBaseInfo();
		userInfo.setNickName(other.getNickname());
		userInfo.setPhoto(Const.getCdn(other.getPhoto()));
		userInfo.setUserType(other.isWaiter() ? 1 : 0);
		userInfo.setUserId(toUserId);
		
		if(Tools.isNotNull(user) && user.isWaiter()){
			String balanceStr = "";
			FreeVideoChatExperienceEntity freeVideoChat = freeVideoChatExperienceContract.findByProperty("userid", toUserId);
			if(Tools.isNotNull(freeVideoChat)){
				balanceStr = "有钻";
			}else{
				long bal = userDiamondAgent.getDiamondBalance(toUserId);
				if(bal>0){
					balanceStr = "有钻";
				}else{
					balanceStr = "无钻";
				}
			}
			userInfo.setBalance("余额:"+balanceStr);
		
			GuardVipCategoryEntity guardList = anchorDefendAgent.getCurrentAnchorDefendByUser(toUserId, userId);
			
			if(Tools.isNotNull(guardList)){
				data.put("guardIcon", Const.getCdn(guardList.getIcon_small()));
			}else{
				data.put("guardIcon", "");
			}
			data.put("normalLevel", "LV"+other.getDegreeid());
			data.put("vip", other.vipValue());
			data.put("custom", "");
			
		}
		
		if(Tools.isNotNull(user) && !user.isWaiter()){
			GuardVipCategoryEntity guardList = anchorDefendAgent.getCurrentAnchorDefendByUser(userId,toUserId);
			if(Tools.isNotNull(guardList)){
				data.put("payGuard", Const.getCdn("/img/guardvip/extend_guard.png"));
			}else{
				data.put("payGuard", Const.getCdn("/img/guardvip/create_guard.png"));
			}
			
		}
		
		
		if(channelCheckService.checkChannel()){
			long anchorUserId = 0;
			if(other.isWaiter()){
				anchorUserId = other.getUserid();
			}else{
				anchorUserId = user.getUserid();
			}
			
			AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", anchorUserId);
			if(Tools.isNotNull(online)) {
				userInfo.setAnchorTypeAudio(online.getAudio_type());
				userInfo.setAnchorTypeVideo(online.getVideo_type());
			}
			data.put("state", 1);
			data.put("userInfo", userInfo);
			
			if (iOSUserSmsAgent.getUserIdList().containsKey(userId)) {
				userInfo.setAnchorTypeAudio(0);
				userInfo.setAnchorTypeVideo(online.getVideo_type());
			}
			
			return ActionResult.success(data);
		}
		
		
		
		//检测发送的主播用户是否处于禁言状态
		int silent = 0;
		if(user.isWaiter()) {
			AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", user.getUserid());
			
			userInfo.setAnchorTypeAudio(online.getAudio_type());
			userInfo.setAnchorTypeVideo(online.getVideo_type());
			
			if (iOSUserSmsAgent.getUserIdList().containsKey(userId)) {
				userInfo.setAnchorTypeAudio(0);
				userInfo.setAnchorTypeVideo(online.getVideo_type());
			}
			
			if(Tools.isNotNull(online)) {
				silent = online.getSilent().intValue();
				if(silent == 1 || online.getState() == -8) {
				
					data.put("state", 0);
					data.put("userInfo", userInfo);
					DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
					String hintInfo = "您暂无发送消息的权限";
					dlgAndGoPage.setHintInfo(hintInfo);
					data.put("gotoData", dlgAndGoPage);
					return ActionResult.success(data);
				}
			}
			if(online.getOnline()== AnchorOnlineStateEnum.quiet.getCode() ){
				data.put("state", 0);
				data.put("userInfo", userInfo);
				DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
				String hintInfo = "勿扰状态下不能发信息";
				dlgAndGoPage.setHintInfo(hintInfo);
				data.put("gotoData", dlgAndGoPage);
				return ActionResult.success(data);
			}
			
			
		}
		
		if (other.isWaiter()) {
			long bal = userDiamondAgent.getDiamondBalance(userId);
			AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", toUserId);
			if(Tools.isNull(online)){
	        	return ActionResult.fail(ErrorCodeEnum.anchor_inexistence);
	        }
			userInfo.setAnchorTypeAudio(online.getAudio_type());
			userInfo.setAnchorTypeVideo(online.getVideo_type());
			
			
			int versionCode = RequestUtils.getCurrent().getHeader().getVersioncode();
			if(versionCode<50){
				if (online.getPrice() * 1 > bal) {
					// 余额不足
					data.put("state", 0);
					/*
					DlgAndGoPage dlgAndGoPage = new DlgAndGoPage();
					dlgAndGoPage.setHintInfo("您的钻石余额不足与大V通话1分钟\n无法发送私信!是否立即充值?");
					dlgAndGoPage.setBtnName("去充值");
					dlgAndGoPage.setAndroidPageTag(DlgAndGoPageEnum.chargePage.getAndroidPage());
					dlgAndGoPage.setAndroidPageParam(DlgAndGoPage.getTagParam(0));
					dlgAndGoPage.setIosPageTag(DlgAndGoPageEnum.chargePage.getIosPage());
					dlgAndGoPage.setIosPageParam(DlgAndGoPage.getTagParam(0));
					data.put("gotoData", dlgAndGoPage);
					*/
					String hintInfo = "您的钻石余额不足与大V通话1分钟\n无法发送私信!是否立即充值?";
					DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
					BtnData chargebtn = new BtnData();
					chargebtn.setBtnName("去充值");
					chargebtn.setAndroidPageTag(DlgAndGoPageEnum.webSingle.getAndroidPage());
					chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+"/api/income/zuanList","我的钱包"));
//					BtnData cancelBtn = new BtnData();
//					cancelBtn.setBtnName("取消");
//					dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn,cancelBtn));
					dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
				
					dlgAndGoPage.setIosBtnName("去充值");
					dlgAndGoPage.setIosPageTag(DlgAndGoPageEnum.webSingle.getIosPage());
					dlgAndGoPage.setIosPageParam(DlgAndGoPage.getTagParam(0));
					dlgAndGoPage.setHintInfo(hintInfo);
					data.put("gotoData", dlgAndGoPage);
					
					
				} else {
					data.put("state", 1);
				}
			}else{
				data.put("state", 1);
			}
		} else {
			
			AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", user.getUserid());
			if(Tools.isNull(online)){
	        	return ActionResult.fail(ErrorCodeEnum.anchor_inexistence);
	        }
			userInfo.setAnchorTypeAudio(online.getAudio_type());
			userInfo.setAnchorTypeVideo(online.getVideo_type());
			data.put("state", 1);
			
		}
	
		if (iOSUserSmsAgent.getUserIdList().containsKey(userId)) {
			userInfo.setAnchorTypeAudio(0);
			//userInfo.setAnchorTypeVideo(0);
		}
		
		data.put("userInfo", userInfo);
		return ActionResult.success(data);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult payChat(long userId, Long toUserId, String chatText) throws Exception {
		Map<String, Object> data = new HashMap<>();
		if(Tools.isNull(chatText)){
			return ActionResult.fail(ErrorCodeEnum.parameter_error);
		}
		
		if(userId == 153302058427023872L && toUserId == 153310968754012416L || userId == 153310968754012416L && toUserId == 153302058427023872L ){
			return CheckFailReturnDesc("对方已下架");
		}
		
		UserBO user = userAgent.findById(userId);
		UserBO other = userAgent.findById(toUserId);
		if(Tools.isNull(user) || Tools.isNull(other)){
			return ActionResult.fail(ErrorCodeEnum.anchor_inexistence);
		}
		
		if(user.isWaiter() && other.isWaiter()){
			return ActionResult.fail(ErrorCodeEnum.anchor_send_msg_to_anchor);
		}
		
		if (checkShowVIPFragment(user)) {
			return CheckFailVIPReturnDesc();
		}
		
		/*
		if(other.isWaiter() && userFirstRechargeLogContract.checkFirstRecharge(userId,FirstPayTypeEnum.diamond.getCode())){
			PageModel pageModel = PageModel.getLimitModel(0, 1);
			pageModel.addQuery(Restrictions.eq("anchorId", toUserId));
			pageModel.addQuery(Restrictions.eq("userid", userId));
			List<AnchorRecvUserEntity> anchorRecvUserList = anchorRecvUserContract.load(pageModel);
			if(Tools.isNull(anchorRecvUserList)){
				return CheckFailReturnDesc("只有和她视频聊天后才可以私信哦");
			}
		}
		*/
		
		int silent = 0;
		if(user.isWaiter()) {
			AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", user.getUserid());
			if(Tools.isNotNull(online)) {
				silent = online.getSilent().intValue();
				if(silent == 1 || online.getState() == -8) {
					return CheckFailReturnDesc("您暂无发送消息的权限");
				}
			}
			
			if(online.getOnline()== AnchorOnlineStateEnum.quiet.getCode() ){
				return CheckFailReturnDesc("勿扰状态下不能发信息");
			}
			
		}
		// 后台文字聊天控制
		SysConfigEntity config = sysConfigContract.findByProperty("name", AgentRedisCacheConst.TEXT_CHAT_CONTROL_PREFIX);
		HashMap<Integer, Integer> ctrMap = new HashMap<>();
		if(Tools.isNotNull(config)) {
			JSONArray ctrlList = JsonHelper.toJsonArray(config.getValue());
			if(Tools.isNotNull(ctrlList)) {
				for(int i=0 ; i<ctrlList.size();i++){
					Object object = ctrlList.get(i);
					ctrMap.put(Integer.valueOf(object.toString()), Integer.valueOf(object.toString()));
				}
			}
		}
		
		
		long orderId = IdGenerater.generateId();
		JSONObject jObject =JsonHelper.toJsonObject(neteaseTextCheck.check(orderId, chatText)) ;
		int code = jObject.getIntValue("code");
	    String msg = jObject.getString("msg");
	    int checkStatus = 0;
	    String taskId = "";
	    int typeGuangGo = 0;
	    List<Integer> checkTypeList = new ArrayList<>();
        if (code == 200) {
        	JSONObject resultObject = jObject.getJSONObject("result");
            int action = resultObject.getIntValue("action");
            taskId = resultObject.getString("taskId");
            JSONArray labelArray = resultObject.getJSONArray("labels");
            for (int i=0;i<labelArray.size();i++) {
            	JSONObject lObject = labelArray.getJSONObject(i);
                int label = lObject.getIntValue("label");
                int level = lObject.getIntValue("level");
                if(label >0){
                	 checkTypeList.add(label);
                }
            }
            if (action == 0) {
            	checkStatus = 1;
            	logger.info("neteaseTextCheck:"+String.format("taskId=%s，文本机器检测结果：通过", taskId));
            } else if (action == 1) {
            	checkStatus = 0;
            	logger.info("neteaseTextCheck:"+String.format("taskId=%s，文本机器检测结果：嫌疑，需人工复审，分类信息如下：%s", taskId, labelArray.toString()));
            } else if (action == 2) {
            	checkStatus = 0;
            	logger.info("neteaseTextCheck:"+String.format("taskId=%s，文本机器检测结果：不通过，分类信息如下：%s", taskId, labelArray.toString()));
            }
            if(checkStatus == 0){
            	boolean flag = false;
            	for(Integer re:checkTypeList){
            		if(Tools.isNotNull(ctrMap.get(re))){
            			flag = true;
            		}
            	}
            	if(flag){
            		checkStatus = 0;
            	}else{
            		checkStatus = 1;
            		
            		if(Tools.isNotNull(checkTypeList)){
            			typeGuangGo = checkTypeList.get(0);
            		}
            		
            	}
            }
         	
            
        } else {
        	checkStatus = 0;
        	logger.info("neteaseTextCheck:"+String.format("ERROR: code=%s, msg=%s", code, msg));
        }
        String orderid = IdGenerater.generateId()+"";
        
        int os_type = RequestUtils.getCurrent().getHeader().getOs_type();
		if (checkStatus  == 0) {
			int consumeRedFlower = 0;
			//普通用户 反垃圾的也扣费
			if(!user.isWaiter() && !checkNoPayFlower()){
				AgentResult  agentResult= redFlowerAgent.consumerFlowerAndIncome(userId,toUserId, CHAT_TEXT_PAYR_EDFLOWER, RedFlowerAccountLogTypeEnum.user_chat_consumption,UserIncomeAccountLogTypeEnum.text_chat_flower, orderid,"文字聊天收益");
				if(agentResult.getCode() == AgentErrorCodeEnum.success.getCode()){
					consumeRedFlower = CHAT_TEXT_PAYR_EDFLOWER;
				}
			}
			insertChatTextRecord(orderId,userId, toUserId, chatText, CHECK_STATUS_FAIL, checkTypeList,taskId, consumeRedFlower);
			String checkInfo = "";
			for(Integer re:checkTypeList){
				checkInfo+=","+NeteaseTextCheckLabelEnum.getDescByCode(re);
			}
			if(checkInfo.length()>0){
				checkInfo = checkInfo.substring(1);
			}
			logger.info("neteaseTextCheck:你输入的内容包含("+checkInfo+");");
			String hintInfo = "你输入的内容包含"+checkInfo;
			return CheckFailReturnDesc(hintInfo);
		}
		
		if(user.isWaiter() ){
			neteaseAgent.pushOneMessage(userId, toUserId, chatText, true);
			insertChatTextRecord(orderId,userId, toUserId, chatText, CHECK_STATUS_SUCCESS, checkTypeList,taskId,0);
			data.put("state", 1);
		}else{
			AgentResult  agentResult= null;
			if (!checkNoPayFlower()) {
				agentResult= redFlowerAgent.consumerFlowerAndIncome(userId,toUserId, CHAT_TEXT_PAYR_EDFLOWER, RedFlowerAccountLogTypeEnum.user_chat_consumption,UserIncomeAccountLogTypeEnum.text_chat_flower, orderid,"文字聊天收益");
			} else {
				agentResult = AgentResult.success();
			}
			
			if(agentResult.getCode() == AgentErrorCodeEnum.success.getCode()) {
				neteaseAgent.pushOneMessage(userId, toUserId, chatText, true);
				insertChatTextRecord(orderId,userId, toUserId, chatText, CHECK_STATUS_SUCCESS, checkTypeList,taskId, os_type == 1 ? CHAT_TEXT_PAYR_EDFLOWER:0);
				anchorRecvUserAgent.checkAnchorRecvUser(userId, toUserId, AnchorRecvUserEnum.msg.getCode());
				data.put("state", 1);
				try {
					sendAutoMsg(userId, toUserId,typeGuangGo);
				} catch (Exception e) {
					logger.info("发送失败:userId="+userId+";toUserId="+toUserId+";typeGuangGo="+typeGuangGo,e);
				}
				
				//触发场景消息检测处理
				try{
				msgSceneService.sendMsgScene(userId, toUserId);
				}catch (Exception e) {
					logger.info("触发场景消息出划:userId="+userId+";toUserId="+toUserId+";",e);
				}
				//在非提审状态下不进行发送消息
				boolean flag = channelCheckService.checkChannel();
				if(!flag)
					//触发检测用户主动引发场景消息的检测处理
					msgSceneService.checkAutoInMsgScene(userId, toUserId);
				
			} else if(agentResult.getCode() == AgentErrorCodeEnum.not_enough.getCode()) {
				return checkFailChargeFlower();
			}
		}
	
		return ActionResult.success(data);
	}
	
	@Override
	public ActionResult checkAudioSend(long userId, long otherId,int type) throws Exception {
		UserBO user = userAgent.findById(userId);
		UserBO other = userAgent.findById(otherId);
		if(Tools.isNull(user) || Tools.isNull(other)){
			return ActionResult.fail(ErrorCodeEnum.anchor_inexistence);
		}
		if(user.isWaiter() && other.isWaiter()){
			return ActionResult.fail(ErrorCodeEnum.anchor_send_msg_to_anchor);
		}
		
		int silent = 0;
		if(user.isWaiter()) {
			AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", user.getUserid());
			if(Tools.isNotNull(online)) {
				silent = online.getSilent().intValue();
				if(silent == 1 || online.getState() == -8) {
					return CheckFailReturnDesc("您暂无发送消息的权限");
				}
			}
			
			if(online.getOnline()== AnchorOnlineStateEnum.quiet.getCode() ){
				return CheckFailReturnDesc("勿扰状态下不能发信息");
			}
			
		}
		
		Map<String, Object> data = new HashMap<>();
		UserBO userBO = userAgent.findById(userId);
		if (userBO.isWaiter()) {
			data.put("state", 1);
			return ActionResult.success(data);
		}
		if (checkShowVIPFragment(userBO) ){
			return CheckFailVIPReturnDesc();
		}
		if (! checkNoPayFlower()) {
			long flowerBalance = redFlowerAgent.getRedFlowerBalance(userId);
			if (flowerBalance < CHAT_AUDIO_PIC_PAYR_EDFLOWER){
				return checkFailChargeFlower();
			}
		}
	
		data.put("state", 1);
		return ActionResult.success(data);
	}
	
	
	

	@Override
	public ActionResult getAudioMsg(long userId, long audioId) throws Exception {
		Map<String, Object> data = new HashMap<>();
		UserBO userBO = userAgent.findById(userId);
		UserAudioChatInfoLogEntity logEntity = userAudioChatInfoLogContract.findById(audioId);
		if (Tools.isNull(logEntity)) {
			return ActionResult.fail(ErrorCodeEnum.audio_msg_nonexistence);
		}
		/*
		if (userBO.isWaiter()) {
			data.put("state", 1);
			data.put("audioUrl",ServiceHelper.getVchatAudio(logEntity.getAudio_url()) );
			return ActionResult.success(data);
		}
		if(checkShowVIPFragment(userBO) ){
			return CheckFailVIPReturnDesc();
		}
		
		if (logEntity.getUser_id() != userId && logEntity.getAudio_recv_watch_falg() == 0) {
			AgentResult result = redFlowerAgent.changeRedFlowerAccount(userId, CHAT_AUDIO_USER_LOOK_PAYR_EDFLOWER, null, RedFlowerAccountLogTypeEnum.look_audio_consumption.getCode(), 0, null, logEntity.getId()+"", RedFlowerAccountLogTypeEnum.look_audio_consumption.getDesc());
			if (AgentErrorCodeEnum.success.getCode() == result.getCode() || AgentErrorCodeEnum.repeate_record.getCode() == result.getCode()) {
				userAudioChatInfoLogContract.updateAudioRecvWatchFalg(logEntity.getId());
			}else if (AgentErrorCodeEnum.not_enough.getCode() == result.getCode()) {
				return checkFailChargeFlower("需要小红花才能够听哦~");
			}else if (result.getCode() != AgentResult.success().getCode()) {
				return ActionResult.fail(result.getCode(), result.getCodemsg());
			}
		}
		*/
		data.put("state", 1);
		data.put("audioUrl",ServiceHelper.getVchatAudio(logEntity.getAudio_url()) );
		return ActionResult.success(data);
	}

	/**
	 * 
	 * @param orderId	订单号
	 * @param userId	用户
	 * @param toUserId	对方ID
	 * @param chatText	文本
	 * @param checkStatus	核查状态
	 * @param checkType		核查类型
	 * @param flowerPrice	小红花
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	private void insertChatTextRecord(long orderId,long userId,long toUserId,String chatText,int checkStatus,List<Integer> checkType,String taskId,long flowerPrice) throws Exception{
		UserTextChatInfoLogEntity log = new UserTextChatInfoLogEntity();
		log.setId(orderId);
		log.setUser_id(userId);
		log.setOther_id(toUserId);
		log.setFlower(flowerPrice);
		log.setChat_text(chatText.length()>200?chatText.substring(0, 200):chatText);
		log.setCheck_taskid(taskId);
		log.setCheck_status(checkStatus);
		if(Tools.isNotNull(checkType)){
			List<String> list = new ArrayList<>();
			checkType.forEach(v->{
				list.add(NeteaseTextCheckLabelEnum.getDescByCode(v));
			});
			log.setCheck_type(JsonHelper.toJson(list));
			log.setCheck_type_code(checkType.get(0));
		}else {
			log.setCheck_type("");
			log.setCheck_type_code(0);
		}
		
		log.setCreate_time(new Date());
		userTextChatInfoLogContract.insert(log);
		
		if (Tools.isNotNull(copyUserContract.findById(userId)) && Tools.isNotNull(copyUserContract.findById(toUserId))) {
			CopyUserTextChatInfoLogEntity copyLog = new CopyUserTextChatInfoLogEntity();
			BeanUtils.copyBean(log, copyLog);
			copyUserTextChatInfoLogContract.insert(copyLog);
		}
		
		try {
			UserTextChatHistoryEntity history = new UserTextChatHistoryEntity();
			history.setLog_id(log.getId());;
			history.setUser_id(log.getUser_id());
			history.setOther_id(log.getOther_id());
			history.setFlower(log.getFlower());
			history.setChat_text(log.getChat_text());
			history.setCheck_taskid(log.getCheck_taskid());
			history.setCheck_status(log.getCheck_status());
			history.setCheck_type(log.getCheck_type());
			history.setCheck_type_code(log.getCheck_type_code());
			history.setSend_type(1);
			history.setCreate_time(new Date());
			userTextChatHistoryContract.insert(history);
		} catch (Exception e) {
			logger.info("查入失败",e);
		}
		
		
	}
	
	
	
	public ActionResult CheckFailReturnDesc(String msg){
		Map<String, Object> data = new HashMap<>();
		String hintInfo = msg;
		DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
		BtnData cancelBtn = new BtnData();
		cancelBtn.setBtnName("确定");
		dlgAndGoPage.setBtnDataList(Arrays.asList(cancelBtn));
		dlgAndGoPage.setHintInfo(hintInfo);
		data.put("gotoData", dlgAndGoPage);
		data.put("iosNeedRecharge", 0);
		data.put("iosMessage", hintInfo);
		data.put("state", 0);
		return ActionResult.success(data);
	}
	
	
	public ActionResult CheckFailVIPReturnDesc() throws Exception {
		Map<String, Object> data = new HashMap<>();
		DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
		String	hintInfo = "还没有开通VIP特色服务功能哦\n开通后可以畅聊\n更获得100元话费";
		SysConfigEntity sysConfig = sysConfigAgent.getSysConfig(com.tigerjoys.shark.miai.agent.constant.Const.HINT_INFO_VIP_POP);
		if(Tools.isNotNull(sysConfig)){
			hintInfo = sysConfigAgent.replaceToN(sysConfig.getValue());
		}
		
		int os_type = RequestUtils.getCurrent().getHeader().getOs_type();
		BtnData chargebtn = new BtnData();
		chargebtn.setBtnName("去开通");
		BtnData cancelbtn = new BtnData();
		cancelbtn.setBtnName("取消");
		if( os_type == 1){
			chargebtn.setAndroidPageTag(IndexActivityAreaEnum.webSingle.getAndroidPage());
			chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+StaticWebUrlEnum.USER_VIP_SERVICE_H5.getPath(),"VIP特色服务"));
			dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
		} else {
			chargebtn.setAndroidPageTag(IndexActivityAreaEnum.vipMemeberPage.getAndroidPage());
			chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(0));
			dlgAndGoPage.setBtnDataList(Arrays.asList(cancelbtn,chargebtn));
		}
		
		dlgAndGoPage.setHintInfo(hintInfo);
		
		data.put("gotoData", dlgAndGoPage);
		data.put("state", 0);

		data.put("iosNeedRecharge", 0);
		data.put("iosMessage", hintInfo);
		return ActionResult.success(data);
	}
	
	public ActionResult checkFailChargeFlower() {
		return checkFailChargeFlower("需要小红花才能够发消息哦~");
	}
	
	public ActionResult checkFailChargeFlower(String hintInfo) {
		Map<String, Object> data = new HashMap<>();
		DlgAndGoPageNew dlgAndGoPage = new DlgAndGoPageNew();
		BtnData chargebtn = new BtnData();
		chargebtn.setBtnName("去充值");
		chargebtn.setAndroidPageTag(DlgAndGoPageEnum.webSingle.getAndroidPage());
		chargebtn.setAndroidPageParam(DlgAndGoPage.getTagParam(Const.WEB_SITE+"/api/income/zuanList?pricePage=1","我的钱包"));
		dlgAndGoPage.setBtnDataList(Arrays.asList(chargebtn));
	
		dlgAndGoPage.setIosBtnName("去充值");
		dlgAndGoPage.setIosPageTag(DlgAndGoPageEnum.webSingle.getIosPage());
		dlgAndGoPage.setIosPageParam(DlgAndGoPage.getTagParam("?pricePage=1"));
		dlgAndGoPage.setHintInfo(hintInfo);
		data.put("gotoData", dlgAndGoPage);
		data.put("state", 0);
		
		data.put("iosNeedRecharge", 1);
		data.put("iosMessage", hintInfo);
		return ActionResult.success(data);
	}
	
	
	public void sendAutoMsg(long userId,long toUserId,int typeGuangGo) throws Exception{
		if(NeteaseTextCheckLabelEnum.advertising.getCode() == typeGuangGo ){
			AnchorOnlineEntity online = anchorOnlineContract.getAnchorOnlineEntity(toUserId);
			if(Tools.isNotNull(online)){
				List<String> magList = new ArrayList<>();
				magList.add("咱们先视频呗，有你想要的");
				magList.add("先不要那么急，你给我打视频，咱们视频里面说");
				magList.add("不在这里说，视频里沟通");
				
				Random random = new Random();
				int index = random.nextInt(magList.size());
				String msgStr = magList.get(index);
				
				Map<String,Object> hsmp = new HashMap<>();
				hsmp.put("userId", toUserId);
				hsmp.put("toUserId", userId);
				hsmp.put("msg", msgStr);
				
				Message consumerMsg = new Message(IdGenerater.generateId()+"",JsonHelper.toJson(hsmp),3000);
				try {
					insertAutoChatTextHistory(userId,toUserId,msgStr);
				} catch (Exception e) {
					logger.info("查入失败",e);
				}
			
				textChatSendMsgConsumer.put(consumerMsg);
			}
		}
	}
	
	
	public void insertAutoChatTextHistory(long userId,long toUserId,String chatText) throws Exception{
		UserTextChatHistoryEntity history = new UserTextChatHistoryEntity();
		history.setUser_id(userId);
		history.setOther_id(toUserId);
		history.setFlower(0L);
		history.setChat_text(chatText);
		history.setCheck_status(CHECK_STATUS_SUCCESS);
		history.setCheck_type(null);
		history.setCheck_type_code(0);
		history.setSend_type(0);
		history.setCreate_time(new Date());
		userTextChatHistoryContract.insert(history);
	}
	
	public void audioCallBackRecv(long orderId ,HttpServletRequest request) throws Exception{
		 boolean verifyFlag = neteaseAudioCheck.verifySignature(request);
	        if (!verifyFlag) {
	            throw new RuntimeException("signature verify failed");
	        }
	        
	    	// 后台文字聊天控制
			SysConfigEntity config = sysConfigContract.findByProperty("name", AgentRedisCacheConst.TEXT_CHAT_CONTROL_PREFIX);
			HashMap<Integer, Integer> ctrMap = new HashMap<>();
			if(Tools.isNotNull(config)) {
				JSONArray ctrlList = JsonHelper.toJsonArray(config.getValue());
				if(Tools.isNotNull(ctrlList)) {
					for(int i=0 ; i<ctrlList.size();i++){
						Object object = ctrlList.get(i);
						ctrMap.put(Integer.valueOf(object.toString()), Integer.valueOf(object.toString()));
					}
				}
			}
	        
	        List<TextAutioMsgDto> autioMsgList = new ArrayList<>(); 
	        String callbackData = request.getParameter("callbackData");
	        JSONObject callBackDataObject = JsonHelper.toJsonObject(callbackData);
	        JSONArray antispamArray =  callBackDataObject.getJSONArray("antispam");
	        for (int n = 0; n < antispamArray.size(); n++) {
	        	TextAutioMsgDto autioMsg = new TextAutioMsgDto();
	        	autioMsg.setOrderId(orderId);
	        	JSONObject resultObject = antispamArray.getJSONObject(n);
	        	int action = resultObject.getIntValue("action");
		        String taskId = resultObject.getString("taskId");
		        autioMsg.setAction(action);
		        autioMsg.setTaskId(taskId);
		        JSONArray labelArray = resultObject.getJSONArray("labels");
		        List<TextAudioLabelDto> labels = new ArrayList<>();
		        for (int i = 0; i < labelArray.size(); i++) {
		        	JSONObject lObject = labelArray.getJSONObject(i);
		        	TextAudioLabelDto labelDto = new TextAudioLabelDto();
		            int label = lObject.getIntValue("label");
		            int level = lObject.getIntValue("level");
		            labelDto.setLabel(label);
		            labelDto.setLevel(level);
		            JSONObject detailsObject=lObject.getJSONObject("details");
		            JSONArray hintArray = detailsObject.getJSONArray("hint");
		            List<String> hintList = new ArrayList<>();
		            for (int m = 0 ;m<hintArray.size(); m++) {
		            	JSONObject hintObject = hintArray.getJSONObject(m);
		            	String hintValue = hintObject.getString("value");
		            	hintList.add(hintValue);
		            }
		            labelDto.setHintInfo(hintList);
		            labels.add(labelDto);
		        }
		        autioMsg.setLabels(labels);
		        
		        //根据后台等级配值，是否禁用发送
		        if (action != 0) {
		        	List<Integer> labelList = new ArrayList<>();
		        	if (Tools.isNotNull(labels)) {
		        		labels.forEach(v->{
		        			if (ctrMap.containsKey(v.getLabel())) {
		        				labelList.add(v.getLabel());
		        			}
		        		});
		        	}
		        	if (Tools.isNull(labelList)) {
		        		logger.info("sendAudioMsgHandle:fail;orderId="+orderId+";autioMsg="+JsonHelper.toJson(autioMsg));
		        		autioMsg.setAction(0);
		        	}
		        }
	        
		        
		        autioMsgList.add(autioMsg);
		        
		        if (action == 0) {
		            logger.info(String.format("callback=%s，结果：通过", taskId));
		        } else if (action == 2) {
		        	logger.info(String.format("callback=%s，结果：不通过，分类信息如下：%s", taskId,
		                    labelArray.toString()));
		        }
	        }
			
	        neteaseAudioCheck.sendAudioMsgHandle(autioMsgList);
	}
	
	/**
	 * 发消息 不用付小红花判断  
	 * 手机类型和 渠道
	 * @return
	 * @throws Exception
	 */
	public boolean checkNoPayFlower() throws Exception{
		RequestHeader header = RequestUtils.getCurrent().getHeader();
		int os_type = header.getOs_type();
		String  channel = header.getChannel();
		// int  versioncode = header.getVersioncode();
		 
		return os_type == 2 || "ydwx_Vivo_AP_DM_YO".equals(channel);
	}
	
	
	
	public boolean checkShowVIPFragment(long userId) throws Exception{
		UserBO userBO = userAgent.findById(userId);
		return checkShowVIPFragment(userBO);
	}
	
	/**
	 * 发消息 不用VIP弹窗判断
	 */
	public boolean checkShowVIPFragment(UserBO userBO) throws Exception {
		if (Tools.isNull(userBO)) {
			return true;
		}
		boolean showVip = false;
		int os_type = RequestUtils.getCurrent().getHeader().getOs_type();
		String channel = RequestUtils.getCurrent().getHeader().getChannel();
		int versionCode = RequestUtils.getCurrent().getHeader().getVersioncode();
		String packageName = RequestUtils.getCurrent().getHeader().getPackageName();

		/*
		if ("ydwx_Vivo_AP_DM_YO".equals(channel) || "kuaishou_cpd".equals(channel)) {
			return false;
		}
		*/
		AppChannelEntity channelEntity = appChannelContract.findByProperty("name", channel);
		if(Tools.isNotNull(channelEntity)){
			if(channelEntity.getVip_status() == 1){
				return false;
			}
		}
		
		
		if (AppNameEnum.andriod_com_tjhj_miliao.getPackageName().equals(packageName)) {
			return false;
		}
		
		PayUserRecordEntity payUserRecordEntity = payUserRecordContract.findByProperty("userid", userBO.getUserid());
		boolean oldUserChargedVip = true;
		if (Tools.isNotNull(payUserRecordEntity)) {
			long current = Tools.longValue(Tools.getFormatDate(payUserRecordEntity.getReg_time(), "yyyyMMdd"));
			if (current < 20200120L) {
				oldUserChargedVip = false;
			}
		}
		if (!userBO.isWaiter() && userBO.vipValue() == 0  && sysConfigAgent.getVIPPopStatus() == 1
				&& oldUserChargedVip && versionCode >= 56) {
			showVip = true;

			if(os_type == 2 && showVip && !checkIOSVipPackageName(packageName,versionCode)){
				showVip = false;
			}
		}
		
		return showVip;
	}
	
	
	public boolean checkIOSVipPackageName(String packageName,int versionCode){
		boolean showVip = false;
		Map<String,Integer> hsmp = new HashMap<>();
		hsmp.put("com.jiaoyou.quliao", 55);
		
		Integer version = hsmp.get(packageName);
		if(Tools.isNotNull(version) && version.intValue()<=versionCode ){
			showVip = true;
		}
		
		return showVip;
	}
	
}
