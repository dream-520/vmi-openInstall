package com.tigerjoys.shark.miai.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shark.miai.common.enums.CouponGroupEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.beans.Produce;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.MD5;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.BaseController;
import com.tigerjoys.nbs.web.annotations.FilterHeader;
import com.tigerjoys.nbs.web.annotations.Login;
import com.tigerjoys.nbs.web.annotations.NoSign;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.ICouponAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.dto.transfer.UserCreatDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IWorldCupBetLogContract;
import com.tigerjoys.shark.miai.inter.contract.IWorldCupGameContract;
import com.tigerjoys.shark.miai.inter.entity.WorldCupBetLogEntity;
import com.tigerjoys.shark.miai.inter.entity.WorldCupGameEntity;

/**
 * yoyo社交活动页处理类
 *
 * @author shiming
 */
@Controller
@RequestMapping(value = "/api")
public class ActivityController extends BaseController {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private IUserAgent userAgent;

  @Autowired
  @Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
  private CacheRedis cacheRedis;

  @Autowired
  private ICouponAgent couponAgent;

  @Autowired
  private IWorldCupGameContract worldCupGameContract;

  @Autowired
  private IWorldCupBetLogContract worldCupBetLogContract;

  @Autowired
  private IUserDiamondAgent userDiamondAgent;

  /**
   * 达人认证活动
   */
  @RequestMapping(value = "/activity/authentication", produces = Produce.TEXT_HTML)
  public String authentication() throws Exception {
    return "daren_authentication/daren_authentication";
  }

  /**
   * 世界杯投票首页票数
   */
  @FilterHeader
  @RequestMapping(value = "/world/cup/tickets", method= RequestMethod.GET, produces = Produce.TEXT_JSON)
  public void worldCupVoteIndex(HttpServletRequest req, HttpServletResponse res) throws Exception {
    res.setContentType("text/plain");
    res.setHeader("Pragma", "No-cache");
    res.setHeader("Cache-Control", "no-cache");
    res.setDateHeader("Expires", 0);
    res.setHeader("Access-Control-Allow-Origin", "*");//添加跨域访问

    String jsonpCallback = req.getParameter("callback");//客户端请求参数
    PrintWriter out = res.getWriter();
    try {
      String[] fields = Arrays.asList(WorldCupCountry.values()).stream().map(one -> one.name()).collect(Collectors.toList()).toArray(new String[0]);
      List<Integer> hmget = cacheRedis.hmget(RedisCacheConst.WORLD_CUP_VOTE, fields).stream().map(Tools::parseInt).collect(Collectors.toList());
      out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.success(hmget)) +")");//返回jsonp格式数据
    } catch (Exception e) {
      logger.error("验证身份证出错！",e);
      out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail()) +")");//返回jsonp格式数据
    } finally {
      out.flush();
      out.close();
    }
  }

  /**
   * 创建用户
   */
  @FilterHeader
  @RequestMapping(value = "/world/cup/create", produces = Produce.TEXT_JSON)
  public void worldCupCreateUser(HttpServletRequest req, HttpServletResponse res) throws Exception{
    res.setContentType("text/plain");
    res.setHeader("Pragma", "No-cache");
    res.setHeader("Cache-Control", "no-cache");
    res.setDateHeader("Expires", 0);
    res.setHeader("Access-Control-Allow-Origin", "*");//添加跨域访问

    String jsonpCallback = req.getParameter("callback");//客户端请求参数
    PrintWriter out = res.getWriter();
    try {
      String mobile = req.getParameter("mobile");
      if (!Tools.isMobile(mobile)) {
        out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail(-1, "手机号不合法！")) +")");
        return;
      }
      UserBO user = userAgent.findByUsername(mobile);
      if (null != user) {
        out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail(-2, "手机号已被注册！")) +")");
        return;
      }
      UserCreatDto dto = new UserCreatDto();
      dto.setFr(0);
      dto.setSex(2);
      dto.setCountryid(0);
      dto.setProvinceid(0);
      dto.setCityid(0);
      dto.setClientid(MD5.encode(mobile + System.currentTimeMillis()));
      dto.setOpenid(mobile);
      dto.setPlatform(0);
      userAgent.createUser(dto, entity -> {
        couponAgent.addUserCoupon(entity.getId(), CouponGroupEnum.new_user.getGroup());
      });
      out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.success()) +")");//返回jsonp格式数据
    } catch (Exception e) {
      logger.error("请求数据出错！",e);
      out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail()) +")");//返回jsonp格式数据
    } finally {
      out.flush();
      out.close();
    }
  }

  /**
   * 冠军投票
   */
  @FilterHeader
  @RequestMapping(value = "/world/cup/vote", produces = Produce.TEXT_JSON)
  public void worldCupVoteClick(HttpServletRequest req, HttpServletResponse res) throws Exception{
    res.setContentType("text/plain");
    res.setHeader("Pragma", "No-cache");
    res.setHeader("Cache-Control", "no-cache");
    res.setDateHeader("Expires", 0);
    res.setHeader("Access-Control-Allow-Origin", "*");//添加跨域访问

    int ordinal = Tools.parseInt(req.getParameter("ordinal"));
    String jsonpCallback = req.getParameter("callback");//客户端请求参数
    PrintWriter out = res.getWriter();
    try {
      out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.success(cacheRedis.hincrBy(RedisCacheConst.WORLD_CUP_VOTE, WorldCupCountry.getByOrdinal(ordinal).name(), 1))) +")");//返回jsonp格式数据
    } catch (Exception e) {
      logger.error("请求数据出错！",e);
      out.println(jsonpCallback+"("+ JsonHelper.toJson(ActionResult.fail()) +")");//返回jsonp格式数据
    } finally {
      out.flush();
      out.close();
    }
  }

  private enum WorldCupCountry {
    GERMANY, //德国
    ARGENTINA,//阿根廷
    ENGLAND,//英格兰
    BRAZIL,//巴西
    COLUMBIA,//哥伦比亚
    BELGIUM,//比利时
    FRANCE,//法国
    COSTA_RICA,//哥斯达黎加
    RUSSIA,//俄罗斯
    MEXICO;//墨西哥

    /**
     * 根据索引获取国家
     */
    public static WorldCupCountry getByOrdinal(int ordinal) {
      WorldCupCountry[] vals = values();
      if (ordinal < 0 || ordinal >= vals.length) {
        throw new IllegalArgumentException("argument error!");
      }
      return vals[ordinal];
    }
  }

  //++++++++++++++++++++++++世界杯竞猜++++++++++++++++++++++++++

  /**
   * 竞猜首页
   */
  @Login
  @NoSign
  @RequestMapping(value = "/world/cup/games", produces = Produce.TEXT_HTML)
  public String guessIndex(Model model) throws Exception {
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    long currentDay = Tools.getDayTime();
    model.addAttribute("firstDate",format.format(new Date(currentDay)));
    model.addAttribute("first", getGamesByTime(Tools.getDateTime(currentDay), Tools.getDateTime(currentDay + Tools.DAY_MILLIS)));
    model.addAttribute("secondDate",format.format(new Date(currentDay + Tools.DAY_MILLIS)));
    model.addAttribute("second", getGamesByTime(Tools.getDateTime(currentDay + Tools.DAY_MILLIS), Tools.getDateTime(currentDay + 2 * Tools.DAY_MILLIS)));
    model.addAttribute("thirdDate",format.format(new Date(currentDay + 2 * Tools.DAY_MILLIS)));
    model.addAttribute("third", getGamesByTime(Tools.getDateTime(currentDay + 2 * Tools.DAY_MILLIS), Tools.getDateTime(currentDay + 3 * Tools.DAY_MILLIS)));
    model.addAttribute("encrypt", RequestUtils.getCurrent().getHeaderEncrypt());
    return "activity/guess";
  }

  /**
   * 获取某时间段的比赛
   * @param start
   * @param end
   * @return
   * @throws Exception
   */
  private List<Map<String, Object>> getGamesByTime(String start, String end) throws Exception {
    PageModel pm = PageModel.getPageModel();
    pm.addQuery(Restrictions.gt("game_time", start));
    pm.addQuery(Restrictions.le("game_time", end));
    pm.asc("game_time");
    List<WorldCupGameEntity> list = worldCupGameContract.load(pm);
    List<Map<String, Object>> games = new ArrayList<>();
    if (Tools.isNotNull(list)) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
      for (WorldCupGameEntity game : list) {
        Map<String, Object> one = new HashMap<>();
        one.put("gameId", game.getId());
        one.put("deadline", format.format(game.getGame_time()));
        one.put("home", game.getHome());
        one.put("homeFlag", Const.getCdn(game.getHome_flag()));
        one.put("homeWin", Tools.formatDouble2(game.getHome_win() / 100.0));
        one.put("guest", game.getGuest());
        one.put("guestFlag", Const.getCdn(game.getGuest_flag()));
        one.put("guestWin", Tools.formatDouble2(game.getGuest_win() / 100.0));
        one.put("dogfall", Tools.formatDouble2(game.getDogfall() / 100.0));
        // 0-不可下注，1-可下注，2-已下注
        if (game.getGame_time().after(new Date())) {
          pm.clearAll();
          pm.addQuery(Restrictions.eq("user_id", RequestUtils.getCurrent().getUserid()));
          pm.addQuery(Restrictions.eq("game_id", game.getId()));
          if (0 < worldCupBetLogContract.count(pm)) {
            one.put("bet", 2);
          } else {
            one.put("bet", 1);
          }
        } else {
          one.put("bet", 0);
        }
        games.add(one);
      }
    }
    return games;
  }

  /**
   * 竞猜
   */
  @Login
  @NoSign
  @RequestMapping(value = "/world/cup/guess", produces = Produce.TEXT_JSON)
  @ResponseBody
  public ActionResult guessDiamond(@RequestBody String body) {
    try {
      JSONObject json = JsonHelper.toJsonObject(body);
      int choice = json.getIntValue("choice");
      long gameId = json.getLongValue("gameId");
      int diamond = json.getIntValue("diamond");
      DiamondResultDto<Long> result = userDiamondAgent.worldCupGuess(RequestUtils.getCurrent().getUserid(), diamond, choice, gameId);
      if (AgentErrorCodeEnum.success.getCode() == result.getCode()) {
        return ActionResult.success();
      } else {
        return ActionResult.fail(result.getCode(), result.getMsg());
      }
    } catch (Exception e) {
      logger.error("请求数据出错", e);
      return ActionResult.fail();
    }
  }

  /**
   * 竞猜记录
   */
  @Login
  @NoSign
  @RequestMapping(value = "/world/cup/guess/logs", produces = Produce.TEXT_JSON)
  @ResponseBody
  public ActionResult guessLogs() {
    try {
     PageModel pm = PageModel.getPageModel();
     pm.addQuery(Restrictions.eq("user_id", RequestUtils.getCurrent().getUserid()));
     pm.desc("create_time");
      List<WorldCupBetLogEntity> list = worldCupBetLogContract.load(pm);
      List<Map<String, Object>> logs = new ArrayList<>();
      if(Tools.isNotNull(list)){
        SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");
        for (WorldCupBetLogEntity log : list){
          Map<String, Object> one = new HashMap<>();
          one.put("time", format.format(log.getCreate_time()));
          one.put("diamond", log.getDiamond());
          WorldCupGameEntity game = worldCupGameContract.findById(log.getGame_id());
          if(0 == game.getResult()){
            //未开奖
            one.put("guess", 1);
            one.put("guessTxt", "未开奖");
          }else{
            one.put("guess", log.getChoice() == game.getResult() ? 3 : 2);
            one.put("guessTxt", log.getChoice() == game.getResult() ? "投注赢" : "投注输");
          }
          one.put("result", guessResult(log.getChoice(), game));
          logs.add(one);
        }
      }
      return ActionResult.success(logs);
    } catch (Exception e) {
      logger.error("请求数据出错", e);
      return ActionResult.fail();
    }
  }

  private String guessResult(int choice, WorldCupGameEntity game){
    switch (choice){
      case 1:
        return game.getHome() + "胜（赔率：" + Tools.formatDouble2(game.getHome_win() / 100.0) + ")";
      case 2:
        return "平局（赔率：" + Tools.formatDouble2(game.getDogfall() / 100.0) + ")";
      case 3:
        return game.getGuest() + "胜（赔率：" + Tools.formatDouble2(game.getGuest_win() / 100.0) + ")";
      default:
        throw new IllegalArgumentException("argument error!");
    }
  }

  @Login
  @NoSign
  @RequestMapping(value = "activity/roulette/home", produces = Produce.TEXT_HTML)
  public String roulette(){
    return "";
  }
}
