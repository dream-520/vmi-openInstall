/**
 * 
 */
package com.tigerjoys.shark.miai.agent.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserBalanceAccountAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum.AccountType;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.ITalentVacuateContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeAccountLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserIncomeWithdrawalContract;
import com.tigerjoys.shark.miai.inter.contract.IUserInviteContract;
import com.tigerjoys.shark.miai.inter.contract.IUserNoWithdrawalBoundsContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeAccountLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserIncomeWithdrawalEntity;
import com.tigerjoys.shark.miai.inter.entity.UserInviteEntity;
import com.tigerjoys.shark.miai.inter.entity.UserNoWithdrawalBoundsEntity;

/**
 * ClassName: UserIncomeAgent <br/>
 * date: 2017年8月16日 下午4:29:09 <br/>
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
@Service
public class UserIncomeAgentImpl implements IUserIncomeAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserIncomeAccountLogContract userIncomeAccountLogContract;

	@Autowired
	private IUserIncomeAccountContract userIncomeAccountContract;

	@Autowired
	private IUserIncomeWithdrawalContract userIncomeWithdrawalContract;

	@Autowired
	private ISysConfigAgent sysConfigAgent;

	@Autowired
	private IUserInviteContract userInviteContract;

	@Autowired
	private IUserBalanceAccountAgent userBalanceAccountAgent;
	
	@Autowired 
	private ITalentVacuateContract talentVacuateContract;
	
	@Autowired 
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired 
	private IUserNoWithdrawalBoundsContract userNoWithdrawalBoundsContract;
	
	@Autowired
	@Qualifier(AgentRedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;
	
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> changeIncomeAccount(long userId, long originAmount, long amount, int io, UserIncomeAccountLogTypeEnum type, String transactionFlow, VacuateConfigDto vacuate, String memo) throws Exception {
		logger.info("[changeIncomeAccount params]--userId:" + userId + ",originAmount:" + originAmount + ",amount:" + amount + ",io:" + io + ",type:" + type + ",transactionFlow:" + transactionFlow + ",vacuate:" + vacuate + ",memo:" + memo);
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		// 查询流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type.getLogType()));
		if (userIncomeAccountLogContract.count(pageModel) != 0) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.repeate_record.getCode());
		}
		// 服务用户账户
		UserIncomeAccountEntity serviceAccount = userIncomeAccountContract.lockByUserId(userId, type.getAccountType());
		if (serviceAccount == null) {
			serviceAccount = createIncomeAccount(userId, type.getAccountType());
		}
		if (0 == io && serviceAccount.getBalance() < amount) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.not_enough.getCode());
		}
		updateIncomeAccount(serviceAccount.getId(), io, amount, 100);

		UserIncomeAccountLogEntity logEntity = new UserIncomeAccountLogEntity();
		logEntity.setAmount(originAmount);
		logEntity.setInternal_amount(amount);
		logEntity.setService_id(userId);
		logEntity.setService_amount(amount);
		logEntity.setService_balance(serviceAccount.getBalance() + (1 == io ? amount : -amount));
		logEntity.setPlatform_amount(originAmount - amount);
		logEntity.setVacuate(Tools.isNull(vacuate) ? "" : vacuate.toString());
		logEntity.setCreate_time(new Date());
		logEntity.setIo(io);
		logEntity.setType(type.getLogType());
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(null == memo ? "" : memo.length() > 100 ? memo.substring(0, 100) : memo);
		// 增加流水记录
		userIncomeAccountLogContract.insert(logEntity);
		return IncomeResultDto.success(logEntity.getService_balance());
	}
	
	@Override
	public IncomeResultDto<Long> changeIncomeAccount(long userId, long amount, int io, UserIncomeAccountLogTypeEnum type, String transactionFlow, String memo) throws Exception {
		return changeIncomeAccount(userId, amount, amount, io, type, transactionFlow, null, memo);
	}
	
	
		
	@Override
	public IncomeResultDto<Long> departIncomeAccount(long userId, long amount, UserIncomeAccountLogTypeEnum type,
			VacuateConfigDto vacuate, String transactionFlow, String memo) throws Exception {
		return departIncomeAccount(userId, amount,amount, type, vacuate, transactionFlow, memo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> departIncomeAccount(long userId, long amount,long internalAmount, UserIncomeAccountLogTypeEnum type, VacuateConfigDto vacuate, String transactionFlow, String memo) throws Exception {
		logger.info("[departIncomeAccount params]--userId:" + userId + ",amount:" + amount + ",type:" + type + ",transactionFlow:" + transactionFlow + ",memo:" + memo);
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		// 查询流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type.getLogType()));
		if (userIncomeAccountLogContract.count(pageModel) != 0) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.repeate_record.getCode());
		}
		// 服务用户账户
		UserIncomeAccountEntity serviceAccount = userIncomeAccountContract.lockByUserId(userId, type.getAccountType());
		if (serviceAccount == null) {
			serviceAccount = createIncomeAccount(userId, type.getAccountType());
		}
		UserIncomeAccountLogEntity logEntity = new UserIncomeAccountLogEntity();
		logEntity.setCreate_time(new Date());
		logEntity.setIo(1);
		logEntity.setType(type.getLogType());
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(null == memo ? "" : memo.length() > 100 ? memo.substring(0, 100) : memo);
		logEntity.setAmount(amount);
		logEntity.setInternal_amount(internalAmount);
		logEntity.setVacuate(JsonHelper.toJson(vacuate));
		logEntity.setLabor_id(0L);
		// 服务者分成
		//首先获取服务者的分成比例
		AnchorOnlineEntity anchor = anchorOnlineContract.findByProperty("userid", userId);
		if(Tools.isNotNull(anchor)) {
			logEntity.setLabor_id(anchor.getLabor_id());
			if(UserIncomeAccountLogTypeEnum.text_chat_flower == type){
				if(amount-anchor.getMsg_ratio()<=0){
					return IncomeResultDto.fail(AgentErrorCodeEnum.sign_error.getCode());
				}
				
				logEntity.setService_amount(anchor.getMsg_ratio().longValue());
				logEntity.setPlatform_amount(amount-anchor.getMsg_ratio());
				updateIncomeAccount(serviceAccount.getId(), 1, logEntity.getService_amount(), 100.0f);
			}else if(UserIncomeAccountLogTypeEnum.free_income_video_lt_30s == type || UserIncomeAccountLogTypeEnum.free_income_video_ge_30s_action2 == type ){
				logEntity.setService_amount(amount);
				logEntity.setPlatform_amount(0L);
				updateIncomeAccount(serviceAccount.getId(), 1, logEntity.getService_amount(), 100.0f);
			}else {
				float ratio = anchor.getRatio();
				if(UserIncomeAccountLogTypeEnum.audio_diamond == type ){
					ratio = anchor.getAudio_ratio().floatValue();
				}else if(UserIncomeAccountLogTypeEnum.video_diamond == type || UserIncomeAccountLogTypeEnum.free_income_video_ge_30s == type ){
					ratio = anchor.getRatio();
				}else if(UserIncomeAccountLogTypeEnum.gift_diamond== type || UserIncomeAccountLogTypeEnum.turntable_diamond== type){
					ratio = anchor.getGift_ratio().floatValue();
				}
				
				logger.info("当前使用主播自己的分成比例"+anchor.getRatio()+" 对应的主播id:"+userId);
				updateIncomeAccount(serviceAccount.getId(), 1, amount, ratio);
				logEntity.setService_amount(Math.round(amount * (ratio / 100.0)));
				logEntity.setPlatform_amount(Math.round(amount * (1 - ratio / 100.0)));
			}
		} else {
			logger.info("当前使用平台的分成比例"+vacuate.getServiceRatio()+" 对应的主播id:"+userId);
			updateIncomeAccount(serviceAccount.getId(), 1, amount, vacuate.getServiceRatio());
			int noWithdrawalAmount = Math.round(amount * (vacuate.getServiceRatio() / 100));
			userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, noWithdrawalAmount);
			logEntity.setService_amount(Math.round(amount * (vacuate.getServiceRatio() / 100.0)));
			logEntity.setPlatform_amount(Math.round(amount * (1 - vacuate.getServiceRatio() / 100.0)));
		}
		
		logEntity.setService_id(userId);
		logEntity.setService_balance(serviceAccount.getBalance() + logEntity.getService_amount());
		// 邀请者分成
		
		UserInviteEntity inviter = userInviteContract.findByProperty("userid", userId);
		if (Tools.isNotNull(inviter)) {
			//邀请者
			long inviterId = inviter.getInvitation();
			
			AnchorOnlineEntity invitUserType = anchorOnlineContract.getAnchorOnlineEntity(inviterId);
			if(Tools.isNotNull(invitUserType) && Tools.isNotNull(anchor) /*&& invitUserType.getState() == 1*/){
				if(invitUserType.getLabor_id() == 0 || anchor.getLabor_id() == 0){
					if(Const.LABOR_ANCHOR_INVITER_INCOME_LIST.contains(type.getLogType())){
						UserIncomeAccountEntity inviterAccount = userIncomeAccountContract.lockByUserId(inviterId, type.getAccountType());
						if (Tools.isNull(inviterAccount)) {
							inviterAccount = createIncomeAccount(inviterId, type.getAccountType());
						}
						updateIncomeAccount(inviterAccount.getId(), 1, logEntity.getService_amount(), vacuate.getProxyRatio());
						logEntity.setInviter_id(inviterId);
						logEntity.setInviter_amount(Math.round(logEntity.getService_amount() * (vacuate.getProxyRatio() / 100.0)));
						logEntity.setInviter_balance(inviterAccount.getBalance() + logEntity.getInviter_amount());
						//logEntity.setPlatform_amount(Math.round(amount * (1 - (vacuate.getServiceRatio() + vacuate.getProxyRatio()) / 100.0)));
						logEntity.setPlatform_amount(logEntity.getAmount().longValue()-logEntity.getService_amount()-logEntity.getInviter_amount());
					}
				}
				
			}
		} else {// 没有邀请者，收益归平台
			//logEntity.setPlatform_amount(Math.round(amount * (1 - vacuate.getServiceRatio() / 100.0)));
		}
		
		// 增加流水记录
		userIncomeAccountLogContract.insert(logEntity);
		if(UserIncomeAccountLogTypeEnum.audio_diamond == type || UserIncomeAccountLogTypeEnum.video_diamond == type ){
			if(Tools.isNotNull(anchor)){
				if(anchor.getShow_income()==0){
					setVchatRoomSettlement(transactionFlow,BigDecimal.valueOf(logEntity.getAmount()).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_UP).doubleValue()+"");
				}else{
					setVchatRoomSettlement(transactionFlow,BigDecimal.valueOf(logEntity.getService_amount()).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_UP).doubleValue()+"");
				}
			}
		}
		return IncomeResultDto.success(logEntity.getService_balance());
	}

	/**
	 * 更新收益账户
	 * 
	 * @param accountId
	 * @param io
	 * @param amount
	 * @param ratio
	 * @return
	 * @throws Exception
	 */
	private void updateIncomeAccount(long accountId, int io, long amount, float ratio) throws Exception {
		amount = Math.round(amount * (ratio / 100));
		StringBuilder sb = new StringBuilder();
		if (1 == io) {// 收入
			sb.append("deposit=deposit+").append(amount).append(",balance=balance+").append(amount);
		} else {// 提取
			sb.append("extract=extract+").append(amount).append(",balance=balance-").append(amount);
		}
		userIncomeAccountContract.updateByStatement(sb.toString(), "id=" + accountId);
	}

	/**
	 * 创建收益账户
	 * 
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private UserIncomeAccountEntity createIncomeAccount(long userId, int type) throws Exception {
		Date date = new Date();
		UserIncomeAccountEntity account = new UserIncomeAccountEntity();
		account.setCreate_time(date);
		account.setUpdate_time(date);
		account.setDeposit(0L);
		account.setBalance(0L);
		account.setExtract(0L);
		account.setStatus(1);
		account.setUser_id(userId);
		account.setType(type);
		userIncomeAccountContract.insert(account);
		return account;
	}

	@Override
	public IncomeResultDto<Long> chatDiamondIncome(int type, long userId, long amount, String transactionFlow, String memo) throws Exception {
		VacuateConfigDto vacuate = sysConfigAgent.vacuate();
		long money = (long) (amount * vacuate.getDiamondToMoney() * 100);
		UserIncomeAccountLogTypeEnum logType = null;
		switch (type) {
			case 1:
				logType = UserIncomeAccountLogTypeEnum.text_diamond;
				break;
				/*
			case 2:
				logType = UserIncomeAccountLogTypeEnum.audio_diamond;
				break;
			case 3:
				logType = UserIncomeAccountLogTypeEnum.video_diamond;
				break;
				*/
			case 4:
				logType = UserIncomeAccountLogTypeEnum.gift_diamond;
				break;
			case 5:
				logType = UserIncomeAccountLogTypeEnum.turntable_diamond;
				break;
			case 6:
				logType = UserIncomeAccountLogTypeEnum.free_income_video_ge_30s;
				break;
			default:
				throw new IllegalArgumentException("args error !");
		}
		return departIncomeAccount(userId, money, logType, vacuate, transactionFlow, memo);
	}
	
	

	@Override
	public long getIncomeBalance(long userId, AccountType type) {
		UserIncomeAccountEntity account = userIncomeAccountContract.lockByUserId(userId, type.getCode());
		return Tools.isNull(account) ? 0L : account.getBalance();
	}

	@Override
	public long getIncomeDeposit(long userId, AccountType type) {
		UserIncomeAccountEntity account = userIncomeAccountContract.lockByUserId(userId, type.getCode());
		return Tools.isNull(account) ? 0L : account.getDeposit();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Map<String, Long>> withdrawalMoney(int amount, long userId, String nickname, int via, UserIncomeAccountLogTypeEnum type, String name, String account)
			throws Exception {
		logger.info("[withdrawalMoney params]--userId:" + userId + ",nickname:" + nickname + ",amount:" + amount + ",via:" + via + ",type:" + type + ",name:" + name + ",account:"
				+ account);
		if (userId <= 0 || amount <= 0) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		/*
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if(!(dayOfWeek == 7 || dayOfWeek == 1 )){
			return IncomeResultDto.fail(AgentErrorCodeEnum.user_withdrawal_saturday_and_sunday.getCode());
		}
		*/
		
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.ge("create_time", new Date(Tools.getDayTime())));
		pageModel.addQuery(Restrictions.eq("user_id", userId));
		long rows = userIncomeWithdrawalContract.count(pageModel);
		if(rows>0){
			return IncomeResultDto.fail(AgentErrorCodeEnum.user_withdrawal_limit_times.getCode());
		}
		
		// 服务用户账户
		UserIncomeAccountEntity serviceAccount = userIncomeAccountContract.lockByUserId(userId, type.getAccountType());
		if (serviceAccount == null) {// 正常情况是不可能为空的
			serviceAccount = createIncomeAccount(userId, type.getAccountType());
		}
		//不可提现账户
		long nowithdrawalBalance = 0;
		UserNoWithdrawalBoundsEntity noWithdrawal = userNoWithdrawalBoundsContract.lockByUserId(userId, type.getAccountType());
		if(Tools.isNotNull(noWithdrawal)){
			nowithdrawalBalance = noWithdrawal.getBalance();
		}
		if (serviceAccount.getBalance()-nowithdrawalBalance < amount) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.not_enough.getCode());
		}
		VacuateConfigDto vacuate = sysConfigAgent.vacuate();
		boolean isGeneral = type.isGeneralAccount();
		UserIncomeWithdrawalEntity withdrawalEntity = new UserIncomeWithdrawalEntity();
		withdrawalEntity.setCreate_time(new Date());
		withdrawalEntity.setSerial(IdGenerater.generateId());
		withdrawalEntity.setUser_id(userId);
		withdrawalEntity.setNickname(nickname);
		withdrawalEntity.setApply_money(amount);
		withdrawalEntity.setStatus(1 == via ? 1 : 0);
		withdrawalEntity.setUpdate_adminid(0L);
		withdrawalEntity.setVia(via);
		withdrawalEntity.setType(type.getAccountType());
		withdrawalEntity.setVacuate(JsonHelper.toJson(vacuate));
		withdrawalEntity.setTax((int) Math.round(amount * (vacuate.getTaxRatio() / 100.0)));
		if(isGeneral){
			long balance = getIncomeBalance(userId, AccountType.BONUS);
			int bonus = amount / (Const.WITHDRAWAL_INCOME_BONUS_BASE * 100) * 10000;
			bonus = balance >= bonus ? bonus : (int)balance / 10000 * 10000;
			withdrawalEntity.setBonus(bonus);
			withdrawalEntity.setBonux_tax((int) Math.round(bonus * (vacuate.getTaxRatio() / 100.0)));
		}else{// 任务单独提现
			withdrawalEntity.setBonus(0);
			withdrawalEntity.setBonux_tax(0);
		}
		withdrawalEntity.setAli_name(Tools.formatString(name));
		withdrawalEntity.setAli_account(Tools.formatString(account));
		userIncomeWithdrawalContract.insert(withdrawalEntity);
		// 收益账户
		IncomeResultDto<Long> changeIncomeAccount = changeIncomeAccount(userId,(long) amount, 0, type, String.valueOf(withdrawalEntity.getSerial()), "收益提现");
		if(AgentErrorCodeEnum.success.getCode() != changeIncomeAccount.getCode()){
			return IncomeResultDto.fail(changeIncomeAccount.getCode());
		}
		// 奖励金账户
		if(withdrawalEntity.getBonus() > 0){
			IncomeResultDto<Long> changeIncomeAccount2 = changeIncomeAccount(userId, amount, 0, UserIncomeAccountLogTypeEnum.bonus_withdrawal, String.valueOf(withdrawalEntity.getSerial()), "奖励金提现");
			if(AgentErrorCodeEnum.success.getCode() != changeIncomeAccount2.getCode()){
				return IncomeResultDto.fail(changeIncomeAccount2.getCode());
			}
		}
		// 提现到余额
		if (1 == via) {
			long money = (long) withdrawalEntity.getApply_money() + withdrawalEntity.getBonus() - withdrawalEntity.getTax() - withdrawalEntity.getBonux_tax();
			AgentResult changeAccount = userBalanceAccountAgent.changeAccount(userId, money, UserBalanceAccountLogTypeEnum.withdrawal_order, String.valueOf(withdrawalEntity.getSerial()), "用户收益提现到余额");
			if(AgentErrorCodeEnum.success.getCode() != changeAccount.getCode()){
				return IncomeResultDto.fail(changeAccount.getCode());
			}
		}
		Map<String, Long> data = new HashMap<>();
		data.put("balance", changeIncomeAccount.getData());
		data.put("bonus", (long)withdrawalEntity.getBonus());
		return IncomeResultDto.success(data);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> withdrawalRollback(long amount, long userId ,UserIncomeAccountLogTypeEnum type, String transactionFlow, String memo)
			throws Exception {
		logger.info("[withdrawalRollback params]--userId:" + userId + ",amount:" + amount + ",type:" + type + ",transactionFlow:" + transactionFlow + ",memo:" + memo);
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		// 查询流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type.getLogType()));
		if (userIncomeAccountLogContract.count(pageModel) != 0) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.repeate_record.getCode());
		}
		// 服务用户账户
		UserIncomeAccountEntity serviceAccount = userIncomeAccountContract.lockByUserId(userId, type.getAccountType());
		if (serviceAccount == null) {
			serviceAccount = createIncomeAccount(userId, type.getAccountType());
		}
		serviceAccount.setBalance(serviceAccount.getBalance() + amount);
		serviceAccount.setExtract(serviceAccount.getExtract() - amount);
		userIncomeAccountContract.update(serviceAccount);

		UserIncomeAccountLogEntity logEntity = new UserIncomeAccountLogEntity();
		logEntity.setAmount(amount);
		logEntity.setService_id(userId);
		logEntity.setService_amount(amount);
		logEntity.setService_balance(serviceAccount.getBalance());
		logEntity.setCreate_time(new Date());
		logEntity.setIo(1);
		logEntity.setType(type.getLogType());
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(null == memo ? "" : memo.length() > 100 ? memo.substring(0, 100) : memo);
		// 增加流水记录
		userIncomeAccountLogContract.insert(logEntity);
		return IncomeResultDto.success(logEntity.getService_balance());
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> withdrawalAddInvitationIncomes(long amount, long userId , String transactionFlow) throws Exception{
		UserInviteEntity inviter = userInviteContract.findByProperty("userid", userId);
		if (Tools.isNotNull(inviter)) {
			//邀请者
			long inviterId = inviter.getInvitation();
			AnchorOnlineEntity invitUserType = anchorOnlineContract.getAnchorOnlineEntity(inviterId);
			AnchorOnlineEntity self = anchorOnlineContract.getAnchorOnlineEntity(userId);
			if(Tools.isNull(invitUserType) || Tools.isNull(self) || invitUserType.getState() != 1 || invitUserType.getFlag()!=0 || self.getState() != 1 || self.getFlag()!=0){
				return IncomeResultDto.success("邀请者是普通用户没有收益", null);
			}
			VacuateConfigDto vacuate = sysConfigAgent.vacuate();
			long inviterIncome = Math.round(amount * vacuate.getProxyRatio() / 100.0);
			return changeIncomeAccount(inviterId, inviterIncome, 1, UserIncomeAccountLogTypeEnum.anchor_settle_back_inviter_income, transactionFlow, UserIncomeAccountLogTypeEnum.anchor_settle_back_inviter_income.getDesc());
		}
		return IncomeResultDto.success("没有邀请者",null);
	}

	@Override
	public long getInvitationIncomes(long userId) throws Exception {
		long total = 0;
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("inviter_id", userId));
		pageModel.addProjection(Projections.sum("inviter_amount").as("total"));
		List<Map<String, Object>> maps = userIncomeAccountLogContract.loadGroupBy(pageModel);
		Map<String, Object> map = maps.get(0);
		if(Tools.isNotNull(map)){
			total += Tools.parseLong(map.get("total"));
		}
		pageModel.clearAll();
		pageModel.addQuery(Restrictions.eq("service_id", userId));
		pageModel.addQuery(Restrictions.eq("type", UserIncomeAccountLogTypeEnum.recharge_back_invitation_income.getLogType()));
		pageModel.addProjection(Projections.sum("service_amount").as("total"));
		maps = userIncomeAccountLogContract.loadGroupBy(pageModel);
		map = maps.get(0);
		if(Tools.isNotNull(map)){
			total += Tools.parseLong(map.get("total"));
		}
		return total;
	}

	@Override
	public long getNoWithdrawalBounds(long userId, AccountType type) {
		UserNoWithdrawalBoundsEntity account = userNoWithdrawalBoundsContract.lockByUserId(userId, type.getCode());
		return Tools.isNull(account) ? 0L : account.getBalance();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> userNoWithdrawalIncomeAdd(long userId, long amount, UserIncomeAccountLogTypeEnum type, String transactionFlow) throws Exception {
		IncomeResultDto<Long> dto = changeIncomeAccount(userId, amount, 1, type, transactionFlow, type.getDesc());
		userNoWithdrawalBoundsContract.updateBalance(userId, AccountType.GENERAL.getCode(), 1, amount);
		return dto;
	}
	
	
	
	public void setVchatRoomSettlement(String orderid,String income){
		try {
			cacheRedis.transaction(tx -> {
				tx.set(AgentRedisCacheConst.VCHAT_ROOM_SETTLEMENT_INCOME_PREFIX + orderid, income);
				tx.expire(AgentRedisCacheConst.VCHAT_ROOM_SETTLEMENT_INCOME_PREFIX + orderid, 10);
			});
		} catch (Exception e) {
			logger.info("设置收益失败:"+orderid,e);
		}
	
	}
	

	
	
}
