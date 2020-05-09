package com.tigerjoys.shark.miai.service.impl;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.enums.RouletteWheel;
import com.tigerjoys.shark.miai.inter.contract.IRouletteWheelLogContract;
import com.tigerjoys.shark.miai.inter.contract.ITopHeadInfoContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.RouletteWheelLogEntity;
import com.tigerjoys.shark.miai.service.IRouletteService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: mouzhanpeng.
 * @date: created in [2018/7/25 10:14].
 */
@Service
public class RouletteServiceImpl implements IRouletteService {

  private final Logger logger = LoggerFactory.getLogger(RouletteServiceImpl.class);

  private final Random random = new Random();

  @Autowired
  private IRouletteWheelLogContract logContract;

  @Autowired
  private IUserDiamondAgent userDiamondAgent;

  @Autowired
  private IUserPayActionContract userPayActionContract;

  @Autowired
  private IUserIncomeAgent userIncomeAgent;

  @Autowired
  private IUserIncomeAccountLogContract incomeAccountLogContract;

  @Autowired
  private ITopHeadInfoContract topHeadInfoContract;

  @Autowired
  @Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
  private CacheRedis cacheRedis;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ActionResult roulette() throws Exception {
    long logId = IdGenerater.generateId();
    UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
    DiamondResultDto<Long> result = DiamondResultDto.fail(AgentErrorCodeEnum.error.getCode());
    long count = cacheRedis.hincrBy("roulette_free_daily", String.valueOf(user.getUserid()), 1);
    if (count <= 3 || AgentErrorCodeEnum.success.getCode() == (result = userDiamondAgent
        .changeDiamondAccount(user.getUserid(), Const.ROULETTE_DIAMONDS_EACH_TIME, null, UserDiamondAccountLogTypeEnum.roulette_wheel_cost.getCode(),
            0, null, String.valueOf(logId), UserDiamondAccountLogTypeEnum.roulette_wheel_cost.getDesc())).getCode()) {
      RouletteWheel wheel = randomWheel(user.getUserid());
      RouletteWheelLogEntity entity = new RouletteWheelLogEntity();
      entity.setId(logId);
      entity.setCreate_time(new Date());
      entity.setUser_id(user.getUserid());
      entity.setWheel_id(wheel.getId());
      entity.setDescription(wheel.getDesc());
      logContract.insert(entity);
      if (wheel.getAmount() > 0) {
        userIncomeAgent
            .changeIncomeAccount(user.getUserid(), wheel.getAmount() * 100, 1, UserIncomeAccountLogTypeEnum.roulette_wheel, String.valueOf(logId),
                UserIncomeAccountLogTypeEnum.roulette_wheel.getDesc());
        try {
          topHeadInfoContract.addTopHeadInfo(user.getNickname(), "大转盘抽奖获得" + wheel.getAmount() + "金币奖励");
        } catch (Exception e) {
          logger.warn("roulette adds top tip error!", e);
        }
      }
      Map<String, Object> data = new HashMap<>();
      if (wheel.getAmount() > 0) {
        data.put("text", "恭喜你获得" + wheel.getAmount() + "金币奖励");
      } else {
        data.put("text", RouletteWheel.Low.THANKS.getDesc());
      }
      data.put("index", wheel.getId());
      data.put("balance", result.getData());
      data.put("award", wheel.getAmount());
      return ActionResult.success(data);
    } else {
      return ActionResult.fail(result.getCode(), result.getMsg());
    }
  }

  @Override
  public ActionResult getTotalIncome() throws Exception {
    PageModel pageModel = PageModel.getPageModel();
    pageModel.addQuery(Restrictions.eq("service_id", RequestUtils.getCurrent().getUserid()));
    pageModel.addQuery(Restrictions.eq("type", UserIncomeAccountLogTypeEnum.roulette_wheel.getLogType()));
    pageModel.addProjection(Projections.sum("service_amount").as("total"));
    List<Map<String, Object>> maps = incomeAccountLogContract.loadGroupBy(pageModel);
    if (Tools.isNotNull(maps)) {
      Map<String, Object> map = maps.get(0);
      if (Tools.isNotNull(map)) {
        return ActionResult.success(Tools.parseLong(map.get("total")) / 100);
      } else {
        return ActionResult.success(0);
      }
    } else {
      return ActionResult.success(0);
    }
  }

  /**
   * 随机轮盘
   */
  private RouletteWheel randomWheel(long userId) throws Exception {
    PageModel pageModel = PageModel.getPageModel();
    pageModel.addQuery(Restrictions.eq("user_id", userId));
    pageModel.addQuery(Restrictions.eq("status", 1));
    if (0 < userPayActionContract.count(pageModel)) {
      long incomeBalance = userIncomeAgent.getIncomeBalance(userId, AccountType.GENERAL);
      if(incomeBalance <= 100){
        return randomHighWheel();
      }else if(incomeBalance <= 300){
        return randomMiddleWheel();
      }else{
        return randomLowhWheel();
      }
    } else {
      pageModel.clearAll().addQuery(Restrictions.eq("user_id", userId));
      long count = logContract.count(pageModel);
      if(count < 5){
        return randomHighWheel();
      }else if(count < 10){
        return randomMiddleWheel();
      }else{
        return randomLowhWheel();
      }
    }
  }

  private RouletteWheel randomHighWheel() {
    int select = random.nextInt(1000);
    if (select < RouletteWheel.High.THANKS.getOccupy()) {
      return RouletteWheel.High.THANKS;
    } else if (select < RouletteWheel.High.THANKS.getOccupy() + RouletteWheel.High.TWO_CASH.getOccupy()) {
      return RouletteWheel.High.TWO_CASH;
    } else if (select < RouletteWheel.High.THANKS.getOccupy() + RouletteWheel.High.TWO_CASH.getOccupy() + RouletteWheel.High.FIVE_CASH.getOccupy()) {
      return RouletteWheel.High.FIVE_CASH;
    } else if (select < RouletteWheel.High.THANKS.getOccupy() + RouletteWheel.High.TWO_CASH.getOccupy() + RouletteWheel.High.FIVE_CASH.getOccupy()
        + RouletteWheel.High.FIFTEEN_CASH.getOccupy()) {
      return RouletteWheel.High.FIFTEEN_CASH;
    } else if (select < RouletteWheel.High.THANKS.getOccupy() + RouletteWheel.High.TWO_CASH.getOccupy() + RouletteWheel.High.FIVE_CASH.getOccupy()
        + RouletteWheel.High.FIFTEEN_CASH.getOccupy() + RouletteWheel.High.THIRTY_CASH.getOccupy()) {
      return RouletteWheel.High.THIRTY_CASH;
    } else if (select < RouletteWheel.High.THANKS.getOccupy() + RouletteWheel.High.TWO_CASH.getOccupy() + RouletteWheel.High.FIVE_CASH.getOccupy()
        + RouletteWheel.High.FIFTEEN_CASH.getOccupy() + RouletteWheel.High.THIRTY_CASH.getOccupy() + RouletteWheel.High.FIFTY_CASH.getOccupy()) {
      return RouletteWheel.High.FIFTY_CASH;
    } else {
      return RouletteWheel.High.THANKS;
    }
  }

  private RouletteWheel randomMiddleWheel() {
    int select = random.nextInt(1000);
    if (select < RouletteWheel.Middle.THANKS.getOccupy()) {
      return RouletteWheel.Middle.THANKS;
    } else if (select < RouletteWheel.Middle.THANKS.getOccupy() + RouletteWheel.Middle.TWO_CASH.getOccupy()) {
      return RouletteWheel.Middle.TWO_CASH;
    } else if (select < RouletteWheel.Middle.THANKS.getOccupy() + RouletteWheel.Middle.TWO_CASH.getOccupy() + RouletteWheel.Middle.FIVE_CASH
        .getOccupy()) {
      return RouletteWheel.Middle.FIVE_CASH;
    } else if (select < RouletteWheel.Middle.THANKS.getOccupy() + RouletteWheel.Middle.TWO_CASH.getOccupy() + RouletteWheel.Middle.FIVE_CASH
        .getOccupy() + RouletteWheel.Middle.FIFTEEN_CASH.getOccupy()) {
      return RouletteWheel.Middle.FIFTEEN_CASH;
    } else if (select < RouletteWheel.Middle.THANKS.getOccupy() + RouletteWheel.Middle.TWO_CASH.getOccupy() + RouletteWheel.Middle.FIVE_CASH
        .getOccupy() + RouletteWheel.Middle.FIFTEEN_CASH.getOccupy() + RouletteWheel.Middle.THIRTY_CASH.getOccupy()) {
      return RouletteWheel.Middle.THIRTY_CASH;
    } else if (select < RouletteWheel.Middle.THANKS.getOccupy() + RouletteWheel.Middle.TWO_CASH.getOccupy() + RouletteWheel.Middle.FIVE_CASH
        .getOccupy() + RouletteWheel.Middle.FIFTEEN_CASH.getOccupy() + RouletteWheel.Middle.THIRTY_CASH.getOccupy() + RouletteWheel.Middle.FIFTY_CASH
        .getOccupy()) {
      return RouletteWheel.Middle.FIFTY_CASH;
    } else {
      return RouletteWheel.Middle.THANKS;
    }
  }

  private RouletteWheel randomLowhWheel() {
    int select = random.nextInt(1000);
    if (select < RouletteWheel.Low.THANKS.getOccupy()) {
      return RouletteWheel.Low.THANKS;
    } else if (select < RouletteWheel.Low.THANKS.getOccupy() + RouletteWheel.Low.TWO_CASH.getOccupy()) {
      return RouletteWheel.Low.TWO_CASH;
    } else if (select < RouletteWheel.Low.THANKS.getOccupy() + RouletteWheel.Low.TWO_CASH.getOccupy() + RouletteWheel.Low.FIVE_CASH.getOccupy()) {
      return RouletteWheel.Low.FIVE_CASH;
    } else if (select < RouletteWheel.Low.THANKS.getOccupy() + RouletteWheel.Low.TWO_CASH.getOccupy() + RouletteWheel.Low.FIVE_CASH.getOccupy()
        + RouletteWheel.Low.FIFTEEN_CASH.getOccupy()) {
      return RouletteWheel.Low.FIFTEEN_CASH;
    } else if (select < RouletteWheel.Low.THANKS.getOccupy() + RouletteWheel.Low.TWO_CASH.getOccupy() + RouletteWheel.Low.FIVE_CASH.getOccupy()
        + RouletteWheel.Low.FIFTEEN_CASH.getOccupy() + RouletteWheel.Low.THIRTY_CASH.getOccupy()) {
      return RouletteWheel.Low.THIRTY_CASH;
    } else if (select < RouletteWheel.Low.THANKS.getOccupy() + RouletteWheel.Low.TWO_CASH.getOccupy() + RouletteWheel.Low.FIVE_CASH.getOccupy()
        + RouletteWheel.Low.FIFTEEN_CASH.getOccupy() + RouletteWheel.Low.THIRTY_CASH.getOccupy() + RouletteWheel.Low.FIFTY_CASH.getOccupy()) {
      return RouletteWheel.Low.FIFTY_CASH;
    } else {
      return RouletteWheel.Low.THANKS;
    }
  }
}
