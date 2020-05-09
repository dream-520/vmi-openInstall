package com.tigerjoys.shark.miai.service;

import java.util.List;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.shark.miai.dto.service.LotteryPrizeDto;

public interface IWebLotteryService {

	/**
	 *  轮盘抽奖
	 * @return
	 * @throws Exception
	 */
	public ActionResult roulette() throws Exception;
	
	public List<LotteryPrizeDto> randPrizes();
	
	/**
	 * 老虎机抽奖
	 * @return
	 * @throws Exception
	 */
	public ActionResult slot() throws Exception;
	
}
