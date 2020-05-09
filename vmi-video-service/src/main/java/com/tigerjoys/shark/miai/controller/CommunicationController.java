/**
 *
 */
package com.tigerjoys.shark.miai.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.UserClientService;
import com.tigerjoys.nbs.web.context.BeatContext;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserOnlineStateAgent;
import com.tigerjoys.shark.miai.agent.IUserVideoAuthAgent;
import com.tigerjoys.shark.miai.agent.dto.AreaDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserChatGiftLogTypeEnum;
import com.tigerjoys.shark.miai.agent.impl.UserGiftBoxAgentImpl;
import com.tigerjoys.shark.miai.agent.service.IAppAreaService;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.IAppStartSendMsgContract;
import com.tigerjoys.shark.miai.inter.contract.ISysConfigContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftBoxContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftContract;
import com.tigerjoys.shark.miai.inter.contract.IUserToOtherContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.AppStartSendMsgEntity;
import com.tigerjoys.shark.miai.inter.entity.SysConfigEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftBoxEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;
import com.tigerjoys.shark.miai.inter.entity.UserToOtherEntity;
import com.tigerjoys.shark.miai.service.IConfService;
import com.tigerjoys.shark.miai.service.IFriendsService;
import com.tigerjoys.shark.miai.service.IVChatTextYXService;

/**
 * ClassName: CommunicationController <br/> date: 2017年5月2日 上午11:03:35 <br/>
 *
 * @author mouzhanpeng
 * @since JDK 1.8.0
 */
@Controller
@RequestMapping(value = "/api/comm", produces = Produce.TEXT_ENCODE)
public class CommunicationController extends BaseController {

  private final Logger logger = LoggerFactory.getLogger(CommunicationController.class);

  @Autowired
  private IUserAgent userAgent;

  @Autowired
  private INeteaseAgent neteaseAgent;

  @Autowired
  private IUserToOtherContract userToOtherContract;

  @Autowired
  private IFriendsService friendsService;

  @Autowired
  private IUserDiamondAgent userDiamondAgent;

  @Autowired
  private IAppAreaService appAreaService;

  @Autowired
  private IUserVideoAuthAgent userVideoAuthAgent;

  @Autowired
  @Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
  private CacheRedis cacheRedis;

  @Autowired
  private IUserChatGiftContract userChatGiftContract;

  @Autowired
  private IRedFlowerAgent redFlowerAgent;

  @Autowired
  private ISysConfigContract sysConfigContract;

  @Autowired
  private IAnchorOnlineContract anchorOnlineContract;

  @Autowired
  private IUserOnlineStateAgent userOnlineStateAgent;
  
  @Autowired
  private IConfService confService;
  
  @Autowired
  private IAppStartSendMsgContract appStartSendMsgContract;
  
  @Autowired
  private IUserChatGiftBoxContract userChatGiftBoxContract;
  
  @Autowired
  private IVChatTextYXService vChatTextYXService;
  
  
  

  /***
   * 聊天第一次交互，获取toId(用于从网易获取聊天消息历史)
   *
   * @see {@link NotifyDataController#attachNeteaseMsg(javax.servlet.http.HttpServletRequest)}
   *      集成到消息抄送中，可以两厢对比
   * @return
   * @throws Exception
   */
  @Login
  @UserClientService("comm")
  @RequestMapping(value = "history", method = RequestMethod.POST)
  @ResponseBody
  public ActionResult neteaseChatFirst(@RequestBody String json) throws Exception {
    long userId = RequestUtils.getCurrent().getUserid();
    long otherId = JsonHelper.toJsonObject(json).getLongValue("otherId");
    PageModel pageModel = PageModel.getLimitModel(0, 1);
    pageModel.addQuery(Restrictions.or(Restrictions.and(Restrictions.eq("from_id", userId), Restrictions.eq("to_id", otherId)),
        Restrictions.and(Restrictions.eq("from_id", otherId), Restrictions.eq("to_id", userId))));
    List<UserToOtherEntity> list = userToOtherContract.load(pageModel);
    if (Tools.isNotNull(list)) {
      UserToOtherEntity uto = list.get(0);
      if (1 == uto.getStatus()) {
        uto.setStatus(0);
      }
      userToOtherContract.update(uto);
    } else {
      UserToOtherEntity uto = new UserToOtherEntity();
      uto.setCreate_time(new Date());
      uto.setFrom_id(userId);
      uto.setTo_id(otherId);
      uto.setStatus(0);
      userToOtherContract.insert(uto);
    }
    return ActionResult.success();
  }

  /**
   * 获取用户昵称和头像（当从网易拿不到的时候启用）
   */
  @Login
  @UserClientService("comm")
  @RequestMapping(value = "userinfo", method = RequestMethod.POST)
  @ResponseBody
  public ActionResult bakeUserInfo(@RequestBody String json) throws Exception {
    try {
      long otherId = JsonHelper.toJsonObject(json).getLongValue("otherId");
      UserBO user = userAgent.findById(otherId);
      Map<String, String> data = new HashMap<>();
      data.put("nickName", user.getNickname());
      data.put("icon", Tools.isNull(user.getPhoto()) ? Const.getDefaultUserFace() : Const.getCdn(user.getPhoto()));
      return ActionResult.success(data);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }

  /**
   * 聊天权限检查
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "chat/judge", method = RequestMethod.POST)
  public ActionResult communicationJudge(@RequestBody String body) {
    try {
      long otherId = JsonHelper.toJsonObject(body).getLongValue("otherId");
      UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
      Map<String, Object> data = new HashMap<>();
      UserBO other = userAgent.findById(otherId);
      data.put("nickName", other.getNickname());
      data.put("icon", Tools.isNull(other.getPhoto()) ? Const.getDefaultUserFace() : Const.getCdn(other.getPhoto()));
      data.put("followState", friendsService.relationStatus(user.getUserid(), otherId));
      data.put("videoState", Const.USER_VIDEO_CHAT_DIAMONDS_COST * 3 > userDiamondAgent.getDiamondBalance(user.getUserid()) ? 2 : 1);
      long bal = Tools.parseLong(redFlowerAgent.getTotalFlowers(user.getUserid()).getData());
      data.put("balance", bal);
      if(0 == user.getFlowerToasted()){
        data.put("limitText", "解锁聊天需" + Const.RED_FLOWERS_EVERY_TIME + "朵小红花，\n一朵小红花只能聊一句");
        userAgent.updateFlowerToasted(user.getUserid());
      }
      if (0 < user.vipValue()) {
        data.put("state", 1);// 可聊天
      } else {
        data.put("state", 0);
      }
      return ActionResult.success(data);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }

  /**
   * 聊天权限检查
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "chat/judge2", method = RequestMethod.POST)
  public ActionResult communicationJudge2() {
    try {
      UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
      if (0 == user.vipValue()) {
       // redFlowerAgent.decreaseAllFlowers(user.getUserid(), Const.RED_FLOWERS_EVERY_TIME);
      }
      return ActionResult.success();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }

  /**
   * 图文聊天权限检查 v2
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "chat2/judge", method = RequestMethod.POST)
  public ActionResult communication2Judge(@RequestBody String body) {
    try {
      long otherId = JsonHelper.toJsonObject(body).getLongValue("otherId");
      UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
      Map<String, Object> data = new HashMap<>();
      UserBO other = userAgent.findById(otherId);
      data.put("nickName", other.getNickname());
      data.put("icon", Tools.isNull(other.getPhoto()) ? Const.getDefaultUserFace() : Const.getCdn(other.getPhoto()));
      data.put("followState", friendsService.relationStatus(user.getUserid(), otherId));
      long bal = userDiamondAgent.getDiamondBalance(user.getUserid());
      if(user.getSex() != other.getSex() && 2 == user.getSex()){
        // 女 -  男
        bal = -1;
        data.put("videoState", 1);
        data.put("audioState", 1);
      }else{
        bal += Tools.parseLong(redFlowerAgent.getTotalFlowers(user.getUserid()).getData());
        data.put("videoState", Const.USER_VIDEO_CHAT_DIAMONDS_COST * 3 > bal ? 2 : 1);
        data.put("audioState", Const.USER_AUDIO_CHAT_DIAMONDS_COST * 3 > bal ? 2 : 1);
      }
      data.put("balance", bal);
      if(0 == user.getFlowerToasted()){
        data.put("limitText", "您有" + Const.RED_FLOWERS_DAILY + "句免费聊天消息");
        userAgent.updateFlowerToasted(user.getUserid());
      }
      return ActionResult.success(data);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }

  /**
   * 图文聊天扣费 v2
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "chat2/judge2", method = RequestMethod.POST)
  @Transactional(rollbackFor = Exception.class)
  public ActionResult communication2Judge2(@RequestBody String body) {
    try {
      UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
      JSONObject object = JsonHelper.toJsonObject(body);
      long otherId = object.getLongValue("otherId");
      UserBO other = userAgent.findById(otherId);
      // 同性扣发起者，异性扣男性发起者
      if(user.getSex() == other.getSex() || 1 == user.getSex()){
        /*
    	  AgentResult agentResult = redFlowerAgent.decreaseAllFlowers(user.getUserid(), Const.RED_FLOWERS_EVERY_TIME);
        if(AgentErrorCodeEnum.not_enough.getCode() == agentResult.getCode()){
          DiamondResultDto<Long> longDiamondResultDto = userDiamondAgent.chatTextCost(user.getUserid(), otherId, Const.USER_TEXT_CHAT_DIAMONDS_COST);
          if(AgentErrorCodeEnum.success.getCode() != longDiamondResultDto.getCode()){
            return ActionResult.fail(longDiamondResultDto.getCode(), longDiamondResultDto.getMsg());
          }
        }
          */
      }
      return ActionResult.success();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }

  /**
   * 电话失败通知
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "phone/fail", method = RequestMethod.POST)
  public ActionResult communicationPhoneFail(@RequestBody String body) {
    try {
      BeatContext context = RequestUtils.getCurrent();
      JSONObject object = JsonHelper.toJsonObject(body);
     /*
      neteaseAgent.pushOneMessage(context.getUserid(), object.getLongValue("otherId"),
          2 == object.getIntValue("type") ? "视频通话未接听，记得联系我哟！" : (context.getUser().getNickname() + "邀请您进行语音聊天"), false);
     */
      return ActionResult.success();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }

  /**
   * 视频聊天，接收方获取对方用户信息
   */
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "video/userinfo", method = RequestMethod.POST)
  public ActionResult videoChatUserInfo(@RequestBody String body) {
    try {
      JSONObject object = JsonHelper.toJsonObject(body);
      UserBO other = userAgent.findById(object.getLongValue("otherId"));
      Map<String, Object> data = new HashMap<>();
      data.put("photo", Tools.isNotNull(other.getPhoto()) ? Const.getCdn(other.getPhoto()) : null);
      data.put("nickName", other.getNickname());
      AreaDto area = appAreaService.getById(other.getCityid());
      data.put("cityName", Tools.isNotNull(area) ? area.getName() : null);
      data.put("gender", 0 == other.getSex() ? 2 : other.getSex());
      data.put("age", Tools.getAge(other.getBirthday()));
      data.put("vipLevel", other.getVip());
      data.put("identification", userVideoAuthAgent.ifAuthVideo(other.getUserid()) ? 1 : 0);
      data.put("unitTimeDiamond", Const.USER_VIDEO_CHAT_DIAMONDS_COST);
      return ActionResult.success(data);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }



  /**
   * 视频聊天按钮状态
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "video/judge", method = RequestMethod.POST)
  public ActionResult videoChatJudge() {
    try {
      long diamondBalance = userDiamondAgent.getDiamondBalance(RequestUtils.getCurrent().getUserid());
      if (Const.USER_VIDEO_CHAT_DIAMONDS_COST * 3 > diamondBalance) {
        return ActionResult.success(0);
      } else {
        return ActionResult.success(1);
      }
    } catch (Exception e) {
      return ActionResult.fail();
    }
  }
  
  /**
   * 查询当前余额
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "diamondBalance", method = RequestMethod.POST)
  public ActionResult diamondBalance() {
    try {
      long diamondBalance = userDiamondAgent.getDiamondBalance(RequestUtils.getCurrent().getUserid());
      return ActionResult.success(diamondBalance);
    } catch (Exception e) {
      return ActionResult.fail();
    }
  }

  /**
   * 聊天礼物列表
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "gift/list", method = RequestMethod.POST)
  public ActionResult chatGifts() {
    try {
      Map<String, Object> res = new HashMap<>();
      SysConfigEntity config = sysConfigContract.findByProperty("name", "CHAT_GIFT_VERSION");
      if(Tools.isNotNull(config)){
        res.put("version", Tools.parseInt(config.getValue()));
      }else{
        res.put("version", 0);
      }
      res.put("balance", userDiamondAgent.getDiamondBalance(RequestUtils.getCurrent().getUserid()));
      PageModel pageModel = PageModel.getPageModel();
      pageModel.addQuery(Restrictions.eq("status", 1));
      pageModel.desc("priority");
      List<UserChatGiftEntity> gifts = userChatGiftContract.load(pageModel);
      List<Map<String, Object>> collect = gifts.stream().map(gift -> {
        Map<String, Object> data = new HashMap<>();
        data.put("id", gift.getId());
        data.put("name", gift.getName());
        data.put("price", gift.getDiamond());
        data.put("picUrl", Const.getCdn(gift.getIcon()));
        return data;
      }).collect(Collectors.toList());
      res.put("gifts", collect);
      return ActionResult.success(res);
    } catch (Exception e) {
      return ActionResult.fail();
    }
  }
  
  
  /**
   * 聊天礼物列表
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "giftBox/list", method = RequestMethod.POST)
  public ActionResult chatgiftBoxs() {
    try {
      Map<String, Object> res = new HashMap<>();
      long userId = RequestUtils.getCurrent().getUserid();
      res.put("balance", userDiamondAgent.getDiamondBalance(userId));
      List<Map<String, Object>> outGiftsList = new ArrayList<>();
      PageModel pageModel = PageModel.getPageModel();
      pageModel.addQuery(Restrictions.eq("user_id", userId));
      pageModel.addQuery(Restrictions.ge("amount", 1));
      List<UserChatGiftBoxEntity> giftBox = userChatGiftBoxContract.load(pageModel);
      if(Tools.isNotNull(giftBox)){
    	  Map<Long, Long> boxGiftNumMap = new HashMap<>();
    	  giftBox.forEach(v->{
    		  boxGiftNumMap.put(v.getGift_id(), v.getAmount());
    	  });
    	  List<Long> giftIdlist = giftBox.stream().map(UserChatGiftBoxEntity::getGift_id).collect(Collectors.toList());
    	  pageModel.clearAll();
    	  pageModel.addQuery(Restrictions.eq("status", 1));
    	  pageModel.addQuery(Restrictions.in("id", giftIdlist));
          pageModel.asc("diamond");
          List<UserChatGiftEntity> gifts = userChatGiftContract.load(pageModel);
          if (Tools.isNotNull(gifts)) {
			for (UserChatGiftEntity re : gifts) {
				Map<String, Object> data = new HashMap<>();
				data.put("id", re.getId());
				data.put("name", re.getName());
				data.put("price", re.getDiamond());
				data.put("picUrl", Const.getCdn(re.getIcon()));
				data.put("num", boxGiftNumMap.get(re.getId()));
				outGiftsList.add(data);
			}
          }
      }
      res.put("gifts", outGiftsList);
      return ActionResult.success(res);
    } catch (Exception e) {
      return ActionResult.fail();
    }
  }

  /**
   * 聊天礼物扣费
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "chat/gift", method = RequestMethod.POST)
  public ActionResult sendChatGift(@RequestBody String body) {
    try {
      JSONObject object = JsonHelper.toJsonObject(body);
      long userId = RequestUtils.getCurrent().getUserid();
      long otherId = object.getLongValue("otherId");
      long giftId = object.getLongValue("giftId");
      Integer type = object.getInteger("type");   // 1视频   2 聊天  礼物发送
      Integer boxType = object.getIntValue("boxType");   // 0 用钻石发送  1 礼物盒 发送
      if(Tools.isNull(type)){
    	  type = UserChatGiftLogTypeEnum.chat_text.getCode();
      }
      
      DiamondResultDto<Long> result = userDiamondAgent.chatGiftCost(userId, otherId, giftId,type,boxType==1 ?true:false);
      if(AgentErrorCodeEnum.success.getCode() == result.getCode()){
    	  //检测是否是给假主播发送的礼物
    	  try {
    		  AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", otherId);
    		  if(Tools.isNotNull(anchor) && anchor.getFlag().intValue() == 4) {
    			  List<String> msgs = new ArrayList<String>();
    			  msgs.add("谢谢哥哥的礼物哦");
    			  msgs.add("收到了，谢谢你的礼物");
    			  msgs.add("哇，收到礼物了，谢谢");
    			  msgs.add("受宠了，感谢！");
    			  Collections.shuffle(msgs);
    			  //使用加主播给用户发送一个消息
    			  String msg = msgs.get(1);
    			  AppStartSendMsgEntity t = new AppStartSendMsgEntity();
    			  t.setFromId(otherId);
    			  t.setToId(userId);
    			  t.setMsg(msg);
    			  t.setSend_time(new Date().getTime() + 4000);
    			  appStartSendMsgContract.insert(t);
    			  vChatTextYXService.insertAutoChatTextHistory(otherId, userId, msg);
    		  }
		} catch (Exception e) {
			logger.error("假主播发送消息失败",e);
		}
    	  
        return ActionResult.success(result.getData());
      }else{
        return ActionResult.fail(result.getCode(), result.getMsg());
      }
    } catch (Exception e) {
    	logger.error("假主播发送消息失败",e);
      return ActionResult.fail();
    }
  }

  /**
   * V-图文聊天权限检查
   */
  @Login
  @UserClientService("comm")
  @ResponseBody
  @RequestMapping(value = "chat/check", method = RequestMethod.POST)
  public ActionResult vChatCheck(@RequestBody String body) {
    try {
      long otherId = JsonHelper.toJsonObject(body).getLongValue("otherId");
      UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
      UserBO other = userAgent.findById(otherId);
      Map<String, Object> data = new HashMap<>();
      data.put("nickname", other.getNickname());
      data.put("icon", Tools.isNull(other.getPhoto()) ? Const.getDefaultUserFace() : Const.getCdn(other.getPhoto()));
      data.put("waiter",other.isWaiter()?1:0);
      if(other.isWaiter()){
        long bal = userDiamondAgent.getDiamondBalance(user.getUserid());
        AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", otherId);
        if(online.getPrice() * 1 > bal){
        // if(false){
          // 余额不足
          data.put("state", 0);
          data.put("stateShow", 1);
          data.put("note", "您的余额不足,请充值");
        }else{
          data.put("state", 1);
          data.put("stateShow", 0);
        }
        if(confService.testIOS()){
        	 data.put("state", 1);
             data.put("stateShow", 0);
        }
        data.put("video", userOnlineStateAgent.userOnlineState(otherId));
      }else{
        long bal = userDiamondAgent.getDiamondBalance(otherId);
        AnchorOnlineEntity online = anchorOnlineContract.findByProperty("userid", user.getUserid());
        data.put("state", 1);
        if(online.getPrice() * 1 > bal){
        //if(false){
          // 对方余额不足
          data.put("stateShow", 2);
          data.put("note", "您的余额不足,请充值");
        }else{
          data.put("stateShow", 0);
        }
        if(confService.testIOS()){
         data.put("stateShow", 0);
       }
        data.put("video", 3 != userOnlineStateAgent.userOnlineState(otherId) ? 4 : 3);
      }
      return ActionResult.success(data);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      return ActionResult.fail();
    }
  }
  
  
  
  
  

}