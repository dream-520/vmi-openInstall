package com.tigerjoys.shark.miai.agent.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.shark.miai.common.util.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IRedFlowerAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.RedFlowerAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IRedFlowerAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.RedFlowerAccountLogEntity;

/** 
  * @author mouzhanpeng at [2017年12月20日 下午4:21:50] 
  * @since JDK 1.8.0 
  */
@Service
public class RedFlowerAgentImpl implements IRedFlowerAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRedFlowerAccountContract redFlowerAccountContract;
	
	@Autowired
	private IRedFlowerAccountLogContract redFlowerAccountLogContract;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	
	@Autowired
	private IUserIncomeAgent userIncomeAgent;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis; 
	
	//脚本
	//private final String LUA = "local keyName,user,amount = KEYS[1],ARGV[1],ARGV[2]; local bal = redis.call('hget',keyName,user); if(bal and tonumber(bal) > 0) then if(tonumber(bal) >= tonumber(amount)) then redis.call('hincrBy',keyName,user,-1 * tonumber(amount));return 'ignore'; else redis.call('hset',keyName,user,'0');return bal; end else return 'account'; end";
	
	/*
	@Transactional(rollbackFor = Exception.class)
	private AgentResult changeFlowerAccount(long userId, long amount, boolean isAdd)throws Exception {
		LOGGER.info("changeFlowerAccount-[userId:" + userId + ",amount" + amount + ",isAdd" + isAdd +"]");
		if(0 >= userId || 0 >= amount){
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		RedFlowerAccountEntity fa = redFlowerAccountContract.lockByUserId(userId);
		Date date = new Date();
		if(null == fa){
			fa = new RedFlowerAccountEntity();
			fa.setCreate_time(date);
			fa.setUpdate_time(date);
			fa.setUser_id(userId);
			fa.setBalance(0L);
			fa.setConsume(0L);
			fa.setDeposit(0L);
			fa.setStatus(1);
			redFlowerAccountContract.insert(fa);
		}
		// 验证用户账户余额是够满足本次消费
		if (!isAdd && fa.getBalance() < amount) {
			return AgentResult.fail(AgentErrorCodeEnum.not_enough);
		}
		fa.setUpdate_time(date);
		if(isAdd){
			fa.setBalance(fa.getBalance() + amount);
			fa.setDeposit(fa.getDeposit() + amount);
		}else{
			fa.setBalance(fa.getBalance() - amount);
			fa.setConsume(fa.getConsume() + amount);
		}
		redFlowerAccountContract.update(fa);
		return AgentResult.success(fa.getBalance());
	}
	*/
	
	@Override
	public AgentResult increaseFlowerAccount(long userId, long amount,RedFlowerAccountLogTypeEnum type,String transactionFlow) throws Exception {
		return changeRedFlowerAccount(userId, Double.valueOf(amount).longValue() ,null, type.getCode(), 1, null,transactionFlow, type.getDesc());
	}

	@Override
	public AgentResult decreaseAllFlowers(long userId, double amount,RedFlowerAccountLogTypeEnum type, String transactionFlow) throws Exception {
		/*
		String mark = String.valueOf(cacheRedis.eval(LUA, 1, AgentRedisCacheConst.RED_FLOWER_DAILY_DONOR, String.valueOf(userId), String.valueOf(amount)));
		switch (mark) {
		case "ignore":
			break;
		case "account":
			
		default:
			return changeFlowerAccount(userId, amount - Tools.parseInt(mark), false);
		}
		return AgentResult.success();
		*/
		return changeRedFlowerAccount(userId, Double.valueOf(amount).longValue() ,null, type.getCode(), 0, null,transactionFlow, type.getDesc());
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult consumerFlowerAndIncome(long userId, long otherid, double amount,
			RedFlowerAccountLogTypeEnum type, UserIncomeAccountLogTypeEnum incomeType, String transactionFlow,String memo) throws Exception {
		AgentResult result = decreaseAllFlowers(userId, amount, type, transactionFlow);
		if(result.getCode() == AgentResult.success().getCode()){
			chatFlowerIncome(incomeType, otherid, amount, transactionFlow, memo);
		}
		return result;
	}




	@Override
	public AgentResult increaseDailyFlower(long userId, long amount) throws Exception {
		// 每天赠送一次
		/*if(1 == cacheRedis.hsetnx(AgentRedisCacheConst.RED_FLOWER_DAILY_DONOR, String.valueOf(userId), String.valueOf(amount))){
			cacheRedis.expireAt(AgentRedisCacheConst.RED_FLOWER_DAILY_DONOR, Tools.getNextDayTime(1) / 1000);
		}else{
			return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
		}*/
		return AgentResult.success();
	}

	@Override
	public AgentResult getTotalFlowers(long userId) throws Exception {
		//long bal = Tools.parseLong(cacheRedis.hget(AgentRedisCacheConst.RED_FLOWER_DAILY_DONOR, String.valueOf(userId)));
		return AgentResult.success(getRedFlowerBalance(userId));
	}

	
	@Override
	public IncomeResultDto<Long> chatFlowerIncome(UserIncomeAccountLogTypeEnum type, long userId, double amount, String transactionFlow, String memo) throws Exception {
		UserIncomeAccountLogTypeEnum logType = null;
		VacuateConfigDto vacuate = sysConfigAgent.vacuate();
		double money = (amount * (vacuate.getFlowerToMoney() * 100));
		return userIncomeAgent.departIncomeAccount(userId, Double.valueOf(money).longValue(), type, vacuate, transactionFlow, memo);
	}
	 
	
	private long getGuardVipUsedRedFlower(long userId){
		String key = AgentRedisCacheConst.GUARD_VIP_USER_USED_REDFLOWER+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+userId;
		long redFlower = cacheRedis.incrBy(key, 0);
		 long expire =  cacheRedis.ttl(key);
		if(expire == -1){
			cacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
		}
		return redFlower;
	}
	
	private long addGuardVipUsedRedFlower(long userId,long num) throws Exception{
		String key = AgentRedisCacheConst.GUARD_VIP_USER_USED_REDFLOWER+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+userId;
		long redFlower = cacheRedis.incrBy(key, num);
		 long expire =  cacheRedis.ttl(key);
		if(expire == -1){
			cacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
		}
		return redFlower;
	}
	
	
	
	@Override
	public long getRedFlowerBalance(long userId) throws Exception {
		RedFlowerAccountEntity account = redFlowerAccountContract.findByProperty("user_id", userId);
		return null == account ? getCurrentCacheRedFlowerBalance(userId) : (account.getBalance()+getCurrentCacheRedFlowerBalance(userId));
	}
	
	@Override
	public long getRedFlowerDeposit(long userId) throws Exception {
		RedFlowerAccountEntity entity = redFlowerAccountContract.findByProperty("user_id", userId);
		return Tools.isNull(entity) ? 0L : entity.getDeposit();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult changeRedFlowerAccount(long userId, long amount, Long money, int type, int logType,
			Integer payType, String transactionFlow, String memo) throws Exception {
		//只为更新数据缓存数据
		getGuardVipTotalRedFlower(userId);
		if (logType == 1) {
			return changeRedFlowerAccountNew(userId, amount, money, type, logType, payType, transactionFlow, memo);
		}
		//只为加锁
		redFlowerAccountContract.lockByUserId(userId);
		long subNum = getGuardVipTotalRedFlower(userId) - getGuardVipUsedRedFlower(userId);
		if (subNum > 0) {
			long addNum = 0;
			if (subNum >= amount) {
				addNum = amount;
			} else {
				addNum = subNum;
			}
			addGuardVipUsedRedFlower(userId, addNum);
			changeRedFlowerAccountNew(userId, addNum, null,RedFlowerAccountLogTypeEnum.recharge_guard_vip_give_redflower.getCode(), 1, null, transactionFlow,RedFlowerAccountLogTypeEnum.recharge_guard_vip_give_redflower.getDesc());
		}
		return changeRedFlowerAccountNew(userId, amount, money, type, logType, payType, transactionFlow, memo);
	}
	
	public long getCurrentCacheRedFlowerBalance(long userId) {
		return getGuardVipTotalRedFlower(userId)-getGuardVipUsedRedFlower(userId);
	}
	
	@Transactional(rollbackFor = Exception.class)
	private AgentResult changeRedFlowerAccountNew(long userId, long amount, Long money, int type, int logType, Integer payType, String transactionFlow, String memo)
			throws Exception {

		logger.info("userid:" + userId + ",amount:" + amount + ",money:" + money + ",type:" + type + ",logType:" + logType + ",transactionFlow:" + transactionFlow + ",memo:" + memo
				+ ",payType:" + payType);
		Date date = new Date();
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		RedFlowerAccountEntity fa = redFlowerAccountContract.lockByUserId(userId);
		if(null == fa){
			fa = new RedFlowerAccountEntity();
			fa.setCreate_time(date);
			fa.setUpdate_time(date);
			fa.setUser_id(userId);
			fa.setBalance(0L);
			fa.setConsume(0L);
			fa.setDeposit(0L);
			fa.setStatus(1);
			redFlowerAccountContract.insert(fa);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		if (redFlowerAccountLogContract.count(pageModel) != 0) {
			return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == logType && fa.getBalance() < amount) {
			return AgentResult.fail(AgentErrorCodeEnum.not_enough);
		}
		// 更新账户表的信息
		StringBuilder buf = new StringBuilder("balance=balance").append(logType == 1 ? "+" : "-").append(amount).append(",update_time=now()");
		if (logType == 1) {// 充值
			buf.append(",deposit=deposit+");
		} else {// 消费
			buf.append(",consume=consume+");
		}
		buf.append(amount);
		redFlowerAccountContract.updateByStatement(buf.toString(), "id=" + fa.getId());

		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		RedFlowerAccountLogEntity logEntity = new RedFlowerAccountLogEntity();
		logEntity.setBalance(fa.getBalance() + (logType == 0 ? -amount : amount));
		logEntity.setCreate_time(date);
		logEntity.setFlower(amount);
		logEntity.setLog_type(logType);
		logEntity.setType(type);
		logEntity.setUser_id(userId);
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		logEntity.setMoney(Tools.longValue(money));
		logEntity.setPay_type(Tools.intValue(payType));
		redFlowerAccountLogContract.insert(logEntity);

		return AgentResult.success(logEntity.getBalance());
	}
	
	
	
	public long getGuardVipTotalRedFlower(long userId) {
		String key = AgentRedisCacheConst.GUARD_VIP_USER_TOTAL_REDFLOWER+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+userId;
		String total = cacheRedis.get(key);
		if(Tools.isNotNull(total)){
			return Tools.longValue(total);
		}
		long vip =0;
		long guard =0;
		try {
			vip = getVipTotalRedFlower(userId);
		} catch (Exception e) {
			logger.info("更新小红花缓存出错",e);
		}
		try {
			guard = getGuardTotalRedFlower(userId);
		} catch (Exception e) {
			logger.info("更新小红花缓存出错",e);
		}
		
		long totalRedFloer = vip+guard;
		cacheRedis.set(key, totalRedFloer+"");
		long expire =  cacheRedis.ttl(key);
		if(expire == -1){
			cacheRedis.expire(key, AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY);
		}
		return totalRedFloer;
	}
	
	
	
	
	/**
	 * 获取守护小红花
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private long getGuardTotalRedFlower(long userId) throws Exception{
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("type", 1));
		pageModel.addQuery(Restrictions.eq("status", 1));
		List<GuardVipCategoryEntity> guardList = guardVipCategoryContract.load(pageModel);
		Map<Integer,Integer> guardRedFlowerMap = new HashMap<>();
		if(Tools.isNotNull(guardList)){
			guardList.forEach(v->{
				guardRedFlowerMap.put(v.getLevel(), v.getDonor());
			});
			
		}
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		int totalRedFlower = 0;
		String currentDate = Tools.getFormatDate(new Date(), "yyyy-MM-dd");
		try {
			//更新时间需要上线更新
			String sql = "SELECT a.lev,COUNT(a.lev) leveNum FROM (SELECT anchorid,MAX(LEVEL) lev FROM t_anchor_defend WHERE  update_time>='2019-11-18 00:00:00' AND userid="+userId+" AND  start_date<='"+currentDate+"' AND end_date>='"+currentDate+"' GROUP BY anchorid) a GROUP BY a.lev ;";
			connection = DBHelper.getConnection(sqlSession);
			statement = connection.createStatement();
			logger.info("getGuardTotalRedFlower_SQL:" + sql);
			result = statement.executeQuery(sql);
			while (result.next()) {
				int lev = result.getInt("lev");
				Integer redFower = guardRedFlowerMap.get(lev);
				if(Tools.isNotNull(redFower)){
					int leveNum = result.getInt("leveNum");
					totalRedFlower+=leveNum*redFower;
				}
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		} finally {
			DBHelper.closeDBA(result, statement, connection);
		}
		return totalRedFlower;
	}
	
	/**
	 * 获取VIP小红花
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private long getVipTotalRedFlower(long userId) throws Exception{
		UserBO userBo = userAgent.findById(userId);
		if(Tools.isNull(userBo)){
			return 0;
		}
		long redFlower = 0;
		if(userBo.vipValue()>0){
			PageModel pageModel = PageModel.getLimitModel(0, 1);
			pageModel.addQuery(Restrictions.eq("type", 2));
			pageModel.addQuery(Restrictions.eq("status", 1));
			pageModel.asc("priority");
			List<GuardVipCategoryEntity> vipList = guardVipCategoryContract.load(pageModel);
			if(Tools.isNotNull(vipList)){
				redFlower = vipList.get(0).getDonor();
			}
		}
		return redFlower;
	}
}
