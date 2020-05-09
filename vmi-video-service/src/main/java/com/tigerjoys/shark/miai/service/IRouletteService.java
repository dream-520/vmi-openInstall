package com.tigerjoys.shark.miai.service;

import com.tigerjoys.nbs.common.ActionResult;

/**
 * @author: mouzhanpeng.
 * @date: created in [2018/7/25 10:14].
 */
public interface IRouletteService {

  /**
   * 轮盘抽奖
   * @return
   */
  public ActionResult roulette() throws Exception;

  /**
   * 轮盘抽奖奖励总额
   * @return
   * @throws Exception
   */
  public ActionResult getTotalIncome() throws Exception;
}
