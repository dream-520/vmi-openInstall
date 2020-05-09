package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.ILoggerAgent;
import com.tigerjoys.shark.miai.agent.IUserBalanceAccountAgent;
import com.tigerjoys.shark.miai.agent.dto.UserBalanceAccountBO;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.UserBalanceAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.exception.PaidAppointAccountException;
import com.tigerjoys.shark.miai.inter.contract.IUserBalanceAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserBalanceAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserBalanceAccountLogEntity;

/**
 * 付费约余额账户接口实现类
 * @author chengang
 *
 */
@Service
public class UserBalanceAccountAgentImpl implements IUserBalanceAccountAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserBalanceAccountContract userBalanceAccountContract;
	
	@Autowired
	private IUserBalanceAccountLogContract userBalanceAccountLogContract;
	
	@Autowired
	private ILoggerAgent loggerAgent;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public AgentResult changeAccount(long userid, long amount, UserBalanceAccountLogTypeEnum logType, String transactionFlow, String memo) throws PaidAppointAccountException , Exception {
		logger.info("change user balance account userid="+userid+",amount="+amount+",logType="+logType.toString()+",transactionFlow="+transactionFlow+",memo="+memo);
		
		if(userid <= 0 || amount <= 0 || transactionFlow == null) {
			throw new PaidAppointAccountException(1000 , "参数异常");
		}
		
		//查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0 , 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", logType.getLogType()));
		if (userBalanceAccountLogContract.count(pageModel) != 0) {
			throw new PaidAppointAccountException(1001 , "重复操作");
		}
		
		//根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		UserBalanceAccountEntity account = userBalanceAccountContract.lockByUserId(userid);
		if(account == null) {
			account = this.initAccount(userid);
		}
		
		// 验证用户账户余额是够满足本次消费
		if (0 == logType.getLogType() && account.getBalance() < amount) {
			throw new PaidAppointAccountException(1002 , "余额不足");
		}
		
		// 更新账户表的信息
		StringBuilder buf = new StringBuilder("balance=balance").append(logType.getLogType() == 1 ? "+" : "-").append(amount).append(",update_time=now()");
		if (logType.getLogType() == 1) {// 充值
			buf.append(",deposit=deposit+");
		} else {// 消费
			buf.append(",consume=consume+");
		}
		buf.append(amount);
		userBalanceAccountContract.updateByStatement(buf.toString(), "id=" + account.getId());
		
		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		
		// 增加充值或者消费的流水记录
		UserBalanceAccountLogEntity logEntity = new UserBalanceAccountLogEntity();
		logEntity.setBalance(account.getBalance() + (logType.getLogType() == 0 ? -amount : amount));
		logEntity.setCreate_time(new Date());
		logEntity.setAmount(amount);
		logEntity.setType(logType.getCode());
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		logEntity.setLogtype(logType.getLogType());
		logEntity.setUserid(userid);
		userBalanceAccountLogContract.insert(logEntity);
		
		//记录到日志中
		loggerAgent.paidAppointAccountLogger(userid, logType, amount, logEntity.getBalance(), transactionFlow , memo);
		
		return AgentResult.success(logEntity.getBalance());
	}

	@Override
	public UserBalanceAccountBO getAccountByUserId(long userId) throws Exception {
		return transferAccount(userBalanceAccountContract.findByProperty("userid", userId));
	}

	@Override
	public long getBalanceByUserId(long userId) throws Exception {
		UserBalanceAccountEntity account = userBalanceAccountContract.findByProperty("userid", userId);
		if(Tools.isNull(account)){
			return 0;
		}
		
		return account.getBalance();
	}
	
	/**
	 * 将数据库对象转换为业务对象
	 * @param account - UserBalanceAccountEntity
	 * @return PaidAppointAccountBO
	 */
	private UserBalanceAccountBO transferAccount(UserBalanceAccountEntity account) {
		if(account == null) return null;
		
		UserBalanceAccountBO bo = new UserBalanceAccountBO();
		bo.setAccountId(account.getId());
		bo.setBalance(account.getBalance());
		bo.setConsume(account.getConsume());
		bo.setDeposit(account.getDeposit());
		bo.setStatus(account.getStatus());
		bo.setUserid(account.getUserid());
		
		return bo;
	}
	
	/**
	 * 初始化用户付费约余额账户信息
	 * @param userId - 用户ID
	 * @return UserBalanceAccountEntity
	 * @throws Exception
	 */
	private UserBalanceAccountEntity initAccount(long userId) throws Exception {
		Date currDate = new Date();
		
		UserBalanceAccountEntity account = new UserBalanceAccountEntity();
		account.setBalance(0L);
		account.setConsume(0L);
		account.setCreate_time(currDate);
		account.setDeposit(0L);
		account.setStatus(1);
		account.setUpdate_time(currDate);
		account.setUserid(userId);
		userBalanceAccountContract.insert(account);
		
		return account;
	}

	@Override
	public boolean refundFromOrder(long orderId) throws Exception {
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", String.valueOf(orderId)));
		pageModel.addQuery(Restrictions.eq("type", UserBalanceAccountLogTypeEnum.cancel_dispatch.getCode()));
		return Tools.isNotNull(userBalanceAccountLogContract.load(pageModel)) ? true : false;
	}
}
