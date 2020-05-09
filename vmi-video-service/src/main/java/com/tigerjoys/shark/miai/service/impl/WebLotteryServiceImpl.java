package com.tigerjoys.shark.miai.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
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
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.dto.service.LotteryPrizeDto;
import com.tigerjoys.shark.miai.enums.RouletteWheel;
import com.tigerjoys.shark.miai.enums.SlotMachine;
import com.tigerjoys.shark.miai.inter.contract.IRouletteWheelLogContract;
import com.tigerjoys.shark.miai.inter.contract.ISlotMachineLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserPayActionContract;
import com.tigerjoys.shark.miai.inter.entity.RouletteWheelLogEntity;
import com.tigerjoys.shark.miai.inter.entity.SlotMachineLogEntity;
import com.tigerjoys.shark.miai.service.IWebLotteryService;

@Service
public class WebLotteryServiceImpl implements IWebLotteryService {

	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	@Autowired
	private IRouletteWheelLogContract logContract;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	@Autowired
	private IUserPayActionContract userPayActionContract;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private ISlotMachineLogContract slotMachineLogContract;
	
	private final Random random = new Random();
	
	/**
	 * 1每个新用户有一次免费机会
	 * 2每次消耗10朵小红花
	 * 3中奖钻石直接发送到账户
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult roulette() throws Exception {
		DiamondResultDto<Long> result = DiamondResultDto.fail(AgentErrorCodeEnum.error.getCode());
		RouletteWheel wheel = null;
		long logId = IdGenerater.generateId();
	    UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
	    //获取对应的用户充值情况
	    long totalMoney = getUserPay(user.getUserid());
	    int free = 1;
	    if(totalMoney == 0) {
	    	//对于未充值的用户 检测是否已经进行过抽奖操作处理   首次一定会中100元抵扣卷
	    	long count = cacheRedis.hincrBy("web_roulette_free_daily", String.valueOf(user.getUserid()), 1);
	    	if(count <= 1) {
	    		//首次参加活动   一定必中100元抵扣钻
	    		//wheel = RouletteWheel.NewV.THIRTY_CASH;
	    		wheel = RouletteWheel.NewV.TWO_CASH;
	    	}
	    } else {
			//对充值的用户 检测今天是否使用过对应的免费机会  免费机会一定中奖 谢谢惠顾
	    	long count = cacheRedis.hincrBy("web_roulette_free_daily_"+Tools.getDate(), String.valueOf(user.getUserid()), 1);
	    	if(count <= 1) {
	    		//免费机会一定中奖 谢谢惠顾
	    		wheel = RouletteWheel.NewV.THANKS;
	    	}
		}
	    
	    //检测是否使用了免费机会 
	    if(Tools.isNull(wheel)) {
	    	//检测是否扣费成功
	    	if(AgentErrorCodeEnum.success.getCode() == (result = userDiamondAgent.changeDiamondAccount(user.getUserid(), Const.WEB_ROULETTE_DIAMONDS_EACH_TIME, null, UserDiamondAccountLogTypeEnum.roulette_wheel_cost.getCode(),0, null, String.valueOf(logId), UserDiamondAccountLogTypeEnum.roulette_wheel_cost.getDesc())).getCode()) {
	    		wheel = randomWheel(totalMoney);
	    		free = 0;
	    	} else {
	    		 return ActionResult.fail(result.getCode(), result.getMsg());
			}
	    }
	    
	    if(Tools.isNull(wheel))
	    	wheel = RouletteWheel.NewV.THANKS;
	    
	    //设置记录
	    RouletteWheelLogEntity entity = new RouletteWheelLogEntity();
	    entity.setId(logId);
	    entity.setCreate_time(new Date());
	    entity.setUser_id(user.getUserid());
	    entity.setWheel_id(wheel.getId());
	    entity.setDescription(wheel.getDesc());
	    entity.setFree(free);
	    if(free == 0)
	    	entity.setCost(Const.WEB_ROULETTE_DIAMONDS_EACH_TIME);
	    if(wheel.getId() != 4)
	    	entity.setAward(wheel.getAmount());
	    logContract.insert(entity);
	    //处理中奖信息
	    if(wheel.getAmount() > 0) {
	    	if(wheel.getId() == 4) {
	    		//检测是否是特殊的100元抵扣卷
	    		userIncomeAgent.userNoWithdrawalIncomeAdd(user.getUserid(), wheel.getAmount() * 100, UserIncomeAccountLogTypeEnum.web_roulette_award, IdGenerater.generateId()+"");
	    	} else {
	    		userDiamondAgent.changeDiamondAccount(user.getUserid(), (long)wheel.getAmount(), (long)0, UserDiamondAccountLogTypeEnum.web_roulette_award.getCode(), 1, null, logId+"", UserDiamondAccountLogTypeEnum.web_roulette_award.getDesc());
			}
	    }
	    //拼接对应的响应结果
	    Map<String, Object> data = new HashMap<>();
	    if (wheel.getAmount() > 0) {
	    	data.put("title", "中奖了");
	    	if(wheel.getId() == 4) {
	    		data.put("text", "恭喜你获得" + wheel.getAmount() + "元抵扣券");
	    	} else {
	    		data.put("text", "恭喜你获得" + wheel.getAmount() + "钻");
			}
	    } else {
	    	data.put("title", "很遗憾");
	        data.put("text", "没有中奖，下次运气会更好");
	    }
	   data.put("index", wheel.getId());
	   return ActionResult.success(data);
	}
	
	//获取用户的总的充值额度
	private long getUserPay(long userid) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addProjection(Projections.sum("money").as("totalMoney"));
		pageModel.addQuery(Restrictions.eq("status",1));
		pageModel.addQuery(Restrictions.eq("user_id",userid));
		List<Map<String, Object>> maps = userPayActionContract.loadGroupBy(pageModel);
		long totalMoney = 0;
		if (Tools.isNotNull(maps)) {
			for(Map<String, Object> map:maps) {
				if (Tools.isNotNull(map)) {
					totalMoney = Tools.parseLong(map.get("totalMoney"));
				}
			}
		}
		return totalMoney/100;
	}

	private RouletteWheel randomWheel(long totalMoney) {
		RouletteWheel wheel = RouletteWheel.NewV.THANKS;
		if(totalMoney < 200) {
			//走200元以下概率
			wheel = randomWheel0();
		} else if(totalMoney < 1000) {
			//走1000元以下概率
			wheel = randomWheel200();
		} else {
			//走1000元以上概率
			wheel = randomWheel1000();
		}
		return wheel;
	}
	
	private RouletteWheel randomWheel0() {
		int select = random.nextInt(10000);
		if (select < RouletteWheel.NewV.TWO_CASH.getOccupy()) {
			return RouletteWheel.NewV.TWO_CASH;
		} else if (select < RouletteWheel.NewV.TWO_CASH.getOccupy() + RouletteWheel.NewV.FIVE_CASH.getOccupy()) {
		    return RouletteWheel.NewV.FIVE_CASH;
		} else if (select < RouletteWheel.NewV.TWO_CASH.getOccupy() + RouletteWheel.NewV.FIVE_CASH.getOccupy() + RouletteWheel.NewV.FIFTEEN_CASH.getOccupy()) {
		    return RouletteWheel.NewV.FIFTEEN_CASH;
		} else if (select < RouletteWheel.NewV.TWO_CASH.getOccupy() + RouletteWheel.NewV.FIVE_CASH.getOccupy() + RouletteWheel.NewV.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV.THIRTY_CASH.getOccupy()) {
		    return RouletteWheel.NewV.THIRTY_CASH;
		} else if (select < RouletteWheel.NewV.TWO_CASH.getOccupy() + RouletteWheel.NewV.FIVE_CASH.getOccupy() + RouletteWheel.NewV.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV.THIRTY_CASH.getOccupy() + RouletteWheel.NewV.FIFTY_CASH.getOccupy()) {
		    return RouletteWheel.NewV.FIFTY_CASH;
		} else if (select < RouletteWheel.NewV.TWO_CASH.getOccupy() + RouletteWheel.NewV.FIVE_CASH.getOccupy() + RouletteWheel.NewV.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV.THIRTY_CASH.getOccupy() + RouletteWheel.NewV.FIFTY_CASH.getOccupy() + RouletteWheel.NewV.THANKS.getOccupy()) {
		    return RouletteWheel.NewV.THANKS;
		} else {
		    return RouletteWheel.NewV.THANKS;
		}
	}
	
	private RouletteWheel randomWheel200() {
		int select = random.nextInt(10000);
		if (select < RouletteWheel.NewV200.TWO_CASH.getOccupy()) {
			return RouletteWheel.NewV200.TWO_CASH;
		} else if (select < RouletteWheel.NewV200.TWO_CASH.getOccupy() + RouletteWheel.NewV200.FIVE_CASH.getOccupy()) {
		    return RouletteWheel.NewV200.FIVE_CASH;
		} else if (select < RouletteWheel.NewV200.TWO_CASH.getOccupy() + RouletteWheel.NewV200.FIVE_CASH.getOccupy() + RouletteWheel.NewV200.FIFTEEN_CASH.getOccupy()) {
		    return RouletteWheel.NewV200.FIFTEEN_CASH;
		} else if (select < RouletteWheel.NewV200.TWO_CASH.getOccupy() + RouletteWheel.NewV200.FIVE_CASH.getOccupy() + RouletteWheel.NewV200.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV200.THIRTY_CASH.getOccupy()) {
		    return RouletteWheel.NewV200.THIRTY_CASH;
		} else if (select < RouletteWheel.NewV200.TWO_CASH.getOccupy() + RouletteWheel.NewV200.FIVE_CASH.getOccupy() + RouletteWheel.NewV200.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV200.THIRTY_CASH.getOccupy() + RouletteWheel.NewV200.FIFTY_CASH.getOccupy()) {
		    return RouletteWheel.NewV200.FIFTY_CASH;
		} else if (select < RouletteWheel.NewV200.TWO_CASH.getOccupy() + RouletteWheel.NewV200.FIVE_CASH.getOccupy() + RouletteWheel.NewV200.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV200.THIRTY_CASH.getOccupy() + RouletteWheel.NewV200.FIFTY_CASH.getOccupy() + RouletteWheel.NewV200.THANKS.getOccupy()) {
		    return RouletteWheel.NewV200.THANKS;
		} else {
		    return RouletteWheel.NewV200.THANKS;
		}
	}
	
	private RouletteWheel randomWheel1000() {
		int select = random.nextInt(10000);
		if (select < RouletteWheel.NewV1000.TWO_CASH.getOccupy()) {
			return RouletteWheel.NewV1000.TWO_CASH;
		} else if (select < RouletteWheel.NewV1000.TWO_CASH.getOccupy() + RouletteWheel.NewV1000.FIVE_CASH.getOccupy()) {
		    return RouletteWheel.NewV1000.FIVE_CASH;
		} else if (select < RouletteWheel.NewV1000.TWO_CASH.getOccupy() + RouletteWheel.NewV1000.FIVE_CASH.getOccupy() + RouletteWheel.NewV1000.FIFTEEN_CASH.getOccupy()) {
		    return RouletteWheel.NewV1000.FIFTEEN_CASH;
		} else if (select < RouletteWheel.NewV1000.TWO_CASH.getOccupy() + RouletteWheel.NewV1000.FIVE_CASH.getOccupy() + RouletteWheel.NewV1000.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV1000.THIRTY_CASH.getOccupy()) {
		    return RouletteWheel.NewV1000.THIRTY_CASH;
		} else if (select < RouletteWheel.NewV1000.TWO_CASH.getOccupy() + RouletteWheel.NewV1000.FIVE_CASH.getOccupy() + RouletteWheel.NewV1000.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV1000.THIRTY_CASH.getOccupy() + RouletteWheel.NewV1000.FIFTY_CASH.getOccupy()) {
		    return RouletteWheel.NewV1000.FIFTY_CASH;
		} else if (select < RouletteWheel.NewV1000.TWO_CASH.getOccupy() + RouletteWheel.NewV1000.FIVE_CASH.getOccupy() + RouletteWheel.NewV1000.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewV1000.THIRTY_CASH.getOccupy() + RouletteWheel.NewV1000.FIFTY_CASH.getOccupy() + RouletteWheel.NewV1000.THANKS.getOccupy()) {
		    return RouletteWheel.NewV1000.THANKS;
		} else {
		    return RouletteWheel.NewV1000.THANKS;
		}
	}

	@Override
	public List<LotteryPrizeDto> randPrizes() {
		//获取随机的40条滚动记录
		List<String> nicknames = new ArrayList<String>();
		nicknames.add("Nigeria");
		nicknames.add("朦朦世界");
		nicknames.add("周也");
		nicknames.add("Xtransfer");
		nicknames.add("幽默已无笑声");
		nicknames.add("1235");
		nicknames.add("荒年`");
		nicknames.add("动情");
		nicknames.add("孤独的心");
		nicknames.add("一海一川");
		nicknames.add("&飞天&");
		nicknames.add("青芒");
		nicknames.add("太阳");
		nicknames.add("霖");
		nicknames.add("一个靠谱的小男生");
		nicknames.add("乐哥");
		nicknames.add("却遗");
		nicknames.add("流逝的星");
		nicknames.add("刘洲");
		nicknames.add("小远");
		nicknames.add("陈思伟蚌埠");
		nicknames.add("吐泡泡的人");
		nicknames.add("好加伙");
		nicknames.add("懒癌晚");
		nicknames.add("轻松一刻");
		nicknames.add("天使de眼泪");
		nicknames.add("发条协奏");
		nicknames.add("xu浩");
		nicknames.add("爱在记忆中找你");
		nicknames.add("在梦里");
		nicknames.add("21341");
		nicknames.add("哈哈");
		nicknames.add("于德磊");
		nicknames.add("展箔");
		nicknames.add("寻找");
		nicknames.add("张海辉");
		nicknames.add("小丑先生二毛");
		nicknames.add("昔我往矣");
		nicknames.add("丑小鸭");
		nicknames.add("平常心");
		nicknames.add("WhyNot");
		nicknames.add("君子康");
		nicknames.add("候鸟北飞");
		nicknames.add("风狆哋煙");
		nicknames.add("藕霸");
		nicknames.add("初如温暖时光");
		nicknames.add("爱");
		nicknames.add("One and On");
		nicknames.add("邹");
		nicknames.add("look");
		nicknames.add("A一身傲骨");
		nicknames.add("小幸运");
		nicknames.add("王者荣耀");
		nicknames.add("皓");
		nicknames.add("橙孟");
		nicknames.add("李少彬");
		nicknames.add("冰城花开");
		nicknames.add("淡忘一切");
		nicknames.add("杜");
		nicknames.add("崔枢扬");
		nicknames.add("随便");
		nicknames.add("余微");
		nicknames.add("风");
		nicknames.add("阿旭");
		nicknames.add("倪湘军Nick");
		nicknames.add("天行健");
		nicknames.add("混沌之灵");
		nicknames.add("奋斗");
		nicknames.add("余生继续浪");
		nicknames.add("姚师");
		nicknames.add("喔喔喔");
		nicknames.add("poison");
		nicknames.add("新年好");
		nicknames.add("W.J.Y");
		nicknames.add("Hndidisn");
		Collections.shuffle(nicknames);
		List<LotteryPrizeDto> dtos = new ArrayList<>();
		for(int i = 0; i < 40; i++) {
			LotteryPrizeDto dto = new LotteryPrizeDto();
			dto.setNickName(nicknames.get(i));
			RouletteWheel wheel = randomWheelPrize();
			dto.setPrizeText(wheel.getDesc());
			dtos.add(dto);
		}
		return dtos;
	}
	
	private RouletteWheel randomWheelPrize() {
		int select = random.nextInt(10000);
		if (select < RouletteWheel.NewVPrize.TWO_CASH.getOccupy()) {
			return RouletteWheel.NewVPrize.TWO_CASH;
		} else if (select < RouletteWheel.NewVPrize.TWO_CASH.getOccupy() + RouletteWheel.NewVPrize.FIVE_CASH.getOccupy()) {
		    return RouletteWheel.NewVPrize.FIVE_CASH;
		} else if (select < RouletteWheel.NewVPrize.TWO_CASH.getOccupy() + RouletteWheel.NewVPrize.FIVE_CASH.getOccupy() + RouletteWheel.NewVPrize.FIFTEEN_CASH.getOccupy()) {
		    return RouletteWheel.NewVPrize.FIFTEEN_CASH;
		} else if (select < RouletteWheel.NewVPrize.TWO_CASH.getOccupy() + RouletteWheel.NewVPrize.FIVE_CASH.getOccupy() + RouletteWheel.NewVPrize.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewVPrize.THIRTY_CASH.getOccupy()) {
		    return RouletteWheel.NewVPrize.THIRTY_CASH;
		} else if (select < RouletteWheel.NewVPrize.TWO_CASH.getOccupy() + RouletteWheel.NewVPrize.FIVE_CASH.getOccupy() + RouletteWheel.NewVPrize.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewVPrize.THIRTY_CASH.getOccupy() + RouletteWheel.NewVPrize.FIFTY_CASH.getOccupy()) {
		    return RouletteWheel.NewVPrize.FIFTY_CASH;
		} else if (select < RouletteWheel.NewVPrize.TWO_CASH.getOccupy() + RouletteWheel.NewVPrize.FIVE_CASH.getOccupy() + RouletteWheel.NewVPrize.FIFTEEN_CASH.getOccupy()
		      + RouletteWheel.NewVPrize.THIRTY_CASH.getOccupy() + RouletteWheel.NewVPrize.FIFTY_CASH.getOccupy() + RouletteWheel.NewVPrize.HUAWEI_P20.getOccupy()) {
		    return RouletteWheel.NewVPrize.HUAWEI_P20;
		} else {
		    return RouletteWheel.NewVPrize.TWO_CASH;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActionResult slot() throws Exception {
		long logId = IdGenerater.generateId();
	    UserBO user = (UserBO) RequestUtils.getCurrent().getUser();
	    DiamondResultDto<Long> result = DiamondResultDto.fail(AgentErrorCodeEnum.error.getCode());
	    long count = cacheRedis.hincrBy("slot_free_daily", String.valueOf(user.getUserid()), 1);
	    if (count <= 1 || AgentErrorCodeEnum.success.getCode() == (result = userDiamondAgent
	        .changeDiamondAccount(user.getUserid(), Const.SLOT_DIAMONDS_EACH_TIME, null, UserDiamondAccountLogTypeEnum.slot_machine_cost.getCode(), 0,
	            null, String.valueOf(logId), UserDiamondAccountLogTypeEnum.slot_machine_cost.getDesc())).getCode()) {
	      SlotMachine slotMachine = randomSlot();
	      SlotMachineLogEntity entity = new SlotMachineLogEntity();
	      entity.setId(logId);
	      entity.setCreate_time(new Date());
	      entity.setUser_id(user.getUserid());
	      entity.setNickname(Tools.formatString(user.getNickname()));
	      entity.setSlot_id(slotMachine.getIndex());
	      entity.setCost(Const.SLOT_DIAMONDS_EACH_TIME);
	      entity.setAward(slotMachine.getAward());
	      entity.setDescription(slotMachine.getDesc());
	      entity.setMemo(JsonHelper.toJson(SlotMachine.values()));
	      slotMachineLogContract.insert(entity);
	      if (slotMachine.getAward() > 0) {
	    	  userDiamondAgent.changeDiamondAccount(user.getUserid(), slotMachine.getAward(), null, UserDiamondAccountLogTypeEnum.slot_machine_award.getCode(), 1,
		            null, String.valueOf(IdGenerater.generateId()), UserDiamondAccountLogTypeEnum.slot_machine_award.getDesc());
	      }
	      Map<String, Object> data = new HashMap<>();
	      if (SlotMachine.ZERO == slotMachine) {
	        List<SlotMachine> slotMachines = Arrays.asList(SlotMachine.values()).stream().filter(o -> o.getAward() > 0).collect(Collectors.toList());
	        int i = slotMachines.remove(random.nextInt(slotMachines.size())).getIndex() - 1, j =
	            slotMachines.get(random.nextInt(slotMachines.size())).getIndex() - 1, k =
	            slotMachines.get(random.nextInt(slotMachines.size())).getIndex() - 1;
	        data.put("indexes", Arrays.asList(i, j, k));
	      } else {
	        data.put("indexes", Arrays.asList(slotMachine.getIndex() - 1, slotMachine.getIndex() - 1, slotMachine.getIndex() - 1));
	      }
	      data.put("balance", userDiamondAgent.getDiamondBalance(user.getUserid()));
	      data.put("award", slotMachine.getAward());
	      data.put("nickname", user.getNickname());
	      data.put("bei", slotMachine.getAward() / Const.SLOT_DIAMONDS_EACH_TIME);
	      return ActionResult.success(data);
	    } else {
	      return ActionResult.fail(result.getCode(), result.getMsg());
	    }
	}
	
	private SlotMachine randomSlot() {
		int select = random.nextInt(Stream.of(SlotMachine.values()).mapToInt(SlotMachine::getOccupy).sum());
		if (select < SlotMachine.ZERO.getOccupy()) {
			return SlotMachine.ZERO;
		} else if (select < SlotMachine.ZERO.getOccupy() + SlotMachine.SINGLE.getOccupy()) {
		    return SlotMachine.SINGLE;
		} else if (select < SlotMachine.ZERO.getOccupy() + SlotMachine.SINGLE.getOccupy() + SlotMachine.DOUBLE.getOccupy()) {
		    return SlotMachine.DOUBLE;
		} else if (select < SlotMachine.ZERO.getOccupy() + SlotMachine.SINGLE.getOccupy() + SlotMachine.DOUBLE.getOccupy() + SlotMachine.TRIPLE.getOccupy()) {
		    return SlotMachine.TRIPLE;
		} else if (select < SlotMachine.ZERO.getOccupy() + SlotMachine.SINGLE.getOccupy() + SlotMachine.DOUBLE.getOccupy() + SlotMachine.TRIPLE.getOccupy() + SlotMachine.QUADRUPLE.getOccupy()) {
		    return SlotMachine.QUADRUPLE;
		} else if (select < SlotMachine.ZERO.getOccupy() + SlotMachine.SINGLE.getOccupy() + SlotMachine.DOUBLE.getOccupy() + SlotMachine.TRIPLE.getOccupy() + SlotMachine.QUADRUPLE.getOccupy() + SlotMachine.QUINTUPLE.getOccupy()) {
		    return SlotMachine.QUINTUPLE;
		} else {
		    return SlotMachine.TENFOLD;
		}
	}	
}
