package com.tigerjoys.shark.miai.agent.impl;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.omg.CORBA.LongHolder;
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
import com.tigerjoys.shark.miai.agent.IUserWeekCardAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IGuardVipCategoryContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDailyWeekcardContract;
import com.tigerjoys.shark.miai.inter.contract.IUserWeekcardAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserWeekcardAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.GuardVipCategoryEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDailyWeekcardEntity;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserWeekcardAccountLogEntity;

/** 
  * @author yangjunming 
  * @since JDK 1.8.0 
  */
@Service
public class UserWeekCardAgentImpl implements IUserWeekCardAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserWeekcardAccountContract userWeekcardAccountContract;
	
	@Autowired
	private IUserWeekcardAccountLogContract userWeekcardAccountLogContract;
	
	@Autowired
	private IGuardVipCategoryContract guardVipCategoryContract;
	

	@Autowired
	private IUserDailyWeekcardContract userDailyWeekcardContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis; 
	
	private  final long DAY_TIME = AgentRedisCacheConst.DEFAULT_CACHE_EXPIRE_DAY*1000;


	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult changeWeekCardAccount(long userId,long cardId, String transactionFlow, String memo) throws Exception {
		logger.info("userid:" + userId+",cardId:" + cardId  + ",transactionFlow:" + transactionFlow + ",memo:" + memo);
		Date date = new Date();
		if (userId <= 0 || cardId<=0 || transactionFlow == null) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		GuardVipCategoryEntity entity = guardVipCategoryContract.findById(cardId);
		if (Tools.isNull(entity)) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		if (userWeekcardAccountLogContract.count(pageModel) != 0) {
			return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
		}
		
		UserWeekcardAccountEntity fa = userWeekcardAccountContract.lockByUserId(userId,cardId);
		if(null == fa){
			fa = new UserWeekcardAccountEntity();
			fa.setCreate_time(date);
			fa.setUpdate_time(date);
			fa.setUserid(userId);
			fa.setCard_id(cardId);
			fa.setType(entity.getType());
			fa.setStart_time(new Date(Tools.getDayTime(2)));
			fa.setEnd_time(new Date(Tools.getDayTime(2)));
			userWeekcardAccountContract.insert(fa);
		}
	
		if(fa.getEnd_time().getTime()<date.getTime()){
			fa.setUpdate_time(date);
			long currentDay = 0;
			if(!checkDialTimeLimit()){
				currentDay = -1;
			}
			fa.setStart_time(new Date(Tools.getDayTime(currentDay)));
			fa.setEnd_time(new Date(Tools.getDayTime(currentDay-entity.getDays())));
			userWeekcardAccountContract.update(fa);
		}else{
			fa.setUpdate_time(date);
			fa.setEnd_time(new Date(fa.getEnd_time().getTime()+DAY_TIME*entity.getDays()));
			userWeekcardAccountContract.update(fa);
		}

		// 增加充值或者消费的流水记录
		UserWeekcardAccountLogEntity logEntity = new UserWeekcardAccountLogEntity();
		logEntity.setCreate_time(date);
		logEntity.setUserid(userId);
		logEntity.setCard_id(cardId);
		logEntity.setType(entity.getType());
		logEntity.setDays(entity.getDays());
		logEntity.setStart_time(new Date(Tools.getDayTime(fa.getEnd_time())-DAY_TIME*entity.getDays()));
		logEntity.setEnd_time(fa.getEnd_time());
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		userWeekcardAccountLogContract.insert(logEntity);
		
		// 充值更新当日周卡时长,20点以后不更新
		//if(checkDialTimeLimit()){
			rechargeUpdateTotalduration(userId,entity.getType());
		//}
		return AgentResult.success();
	}
	
	/**
	 * 
	 * @param userid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public long getDailyWeekCard(long userid,int type) throws Exception{
		String key = AgentRedisCacheConst.DAILY_WEEK_CARDID_ID+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+userid+"_"+type;
		String dailyId = cacheRedis.get(key);
		if(Tools.isNotNull(dailyId)){
			return Tools.longValue(dailyId);
		}
		UserDailyWeekcardEntity weekcard = getDailyWeekCardEntity(userid,type);
		Date current = new Date();
		String currentStr = Tools.getFormatDate(current, "yyyy-MM-dd");
		if(Tools.isNull(weekcard)){
			weekcard = new UserDailyWeekcardEntity();
			weekcard.setCreate_time(current);
			weekcard.setUpdate_time(current);
			weekcard.setUserid(userid);
			weekcard.setType(type);
			weekcard.setCheck_date(new Date(Tools.getDayTime(2)));
			weekcard.setTotal_duration(0);
			weekcard.setDuration(0);
			userDailyWeekcardContract.insert(weekcard);
		}
		if(!currentStr.equals(Tools.getFormatDate(weekcard.getCheck_date(), "yyyy-MM-dd"))){
			long totalMinute = getWeekCardTotalDuration(userid,type);
			userDailyWeekcardContract.updateByStatement("update_time=now(),total_duration="+totalMinute+",duration=0,check_date='"+currentStr+"'", "id="+weekcard.getId()+" and check_date<>'"+currentStr+"'");
			if(totalMinute == 0){
				setDailyWeekCardId(userid, type, 0L);
				return 0L;
			}
		}
		long dailyWeekCardId = 0;
		if(weekcard.getTotal_duration()-weekcard.getDuration()==0){
			setDailyWeekCardId(userid, type, 0L);
		}else{
			dailyWeekCardId= weekcard.getId();
		}
		return dailyWeekCardId;
	}
	
	
	public long updateDailyWeekCardDuration(long userid,int type) throws Exception{
		long weekCardId = getDailyWeekCard(userid,type);
		String currentStr = Tools.getFormatDate(new Date(), "yyyy-MM-dd");
		int rows = 0;
		if(weekCardId>0){
			rows = userDailyWeekcardContract.updateByStatement("update_time=now(),duration=duration+1", "id="+weekCardId+" and total_duration>duration  and check_date ='"+currentStr+"'");
			if(rows == 0){
				setDailyWeekCardId(userid, type, 0L);
			}
		}
		return rows;
	}
	
	
	private void rechargeUpdateTotalduration(long userId,int type) throws Exception{
		//之前清楚 
		clearCacheCurrentDailyWeekCard(userId,type);
		
		UserDailyWeekcardEntity weekcard = getDailyWeekCardEntity(userId,type);
		if(Tools.isNull(weekcard)){
			return ;
		}
		Date current = new Date();
		String currentStr = Tools.getFormatDate(current, "yyyy-MM-dd");
		long totalMinute = getWeekCardTotalDuration(userId,type);
		userDailyWeekcardContract.updateByStatement("update_time=now(),total_duration="+totalMinute,"id="+weekcard.getId()+" and check_date='"+currentStr+"'");
		
	}
	
	
	
	
	public long getWeekCardTotalDuration(long userid,int type) throws Exception{
		Date current = new Date();
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.addQuery(Restrictions.eq("type", type));
		pageModel.addQuery(Restrictions.le("start_time", current));
		pageModel.addQuery(Restrictions.gt("end_time", current));
		List<UserWeekcardAccountEntity> accountList = userWeekcardAccountContract.load(pageModel);
		long totalMinute = 0;
		if(Tools.isNotNull(accountList)){
			List<Long> idList = accountList.stream().map(UserWeekcardAccountEntity::getCard_id).collect(Collectors.toList());
			Map<Long , GuardVipCategoryEntity> weekList = guardVipCategoryContract.findById(idList);
			LongHolder total = new LongHolder(0); 
			if(Tools.isNotNull(weekList)){
				weekList.forEach((k,v)->{
					total.value = total.value+v.getBuy_num();
				});
			}
			totalMinute = total.value;
			logger.info("getDailyWeekCard:"+totalMinute);
		}
		return totalMinute;
	}
	
	public UserDailyWeekcardEntity getDailyWeekCardEntity(long userId,int type) throws Exception{
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("userid", userId));
		pageModel.addQuery(Restrictions.eq("type", type));
		List<UserDailyWeekcardEntity> weekcardList = userDailyWeekcardContract.load(pageModel);
		if(Tools.isNull(weekcardList)){
			return null;
		}
		return weekcardList.get(0);
	}
	
	private void setDailyWeekCardId(long userid,int type,long dailyId){
		String key = AgentRedisCacheConst.DAILY_WEEK_CARDID_ID+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+userid+"_"+type;
		cacheRedis.set(key, dailyId);
		long ttl = cacheRedis.ttl(key);
		if(ttl==-1){
			cacheRedis.expire(key,60*60*24 );
		}
	}
	
	public void clearCacheCurrentDailyWeekCard(long userId, int type) throws Exception {
		// 之前清楚数据
		delDailyWeekCardId(userId, type);
		// 如果先充值，没有拨打，更新到当前日期
		getDailyWeekCard(userId, type);
		// 之后清楚 更新充值之后的通话时长
		delDailyWeekCardId(userId, type);
	}
	
	private void delDailyWeekCardId(long userid,int type){
		String key = AgentRedisCacheConst.DAILY_WEEK_CARDID_ID+Tools.getFormatDate(new Date(), "yyyyMMdd")+"_"+userid+"_"+type;
		cacheRedis.del(key);
	}
	
	private boolean checkDialTimeLimit(){
		LocalTime localTime = LocalTime.now();
		return localTime.getHour()<20;
	}
	
}
