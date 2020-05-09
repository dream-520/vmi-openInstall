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
import com.tigerjoys.shark.miai.agent.IUserTariffeAgent;
import com.tigerjoys.shark.miai.agent.dto.result.TariffeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserTariffeAccountLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserTariffeAccountLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserTariffeAccountMapper;

/**
 * ClassName: UserTariffeAgentImpl <br/>
 * date: 2019年9月6日 下午6:11:19 <br/>
 * 
 * @author lipeng
 * @version
 * @since JDK 1.8.0
 */
@Service
public class UserTariffeAgentImpl implements IUserTariffeAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserTariffeAccountLogContract userTariffeAccountLogContract;

	@Autowired
	private UserTariffeAccountMapper userTariffeAccountMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public TariffeResultDto<Long> changeTariffeAccount(long userid, long tariffe, int type, int logType, String transactionFlow, String memo) throws Exception {
		logger.info("userid:" + userid + ",tariffe:" + tariffe + ",type:" + type + ",logType:" + logType + ",transactionFlow:" + transactionFlow + ",memo:" + memo);
		Date currDate = new Date();
		if (userid <= 0 || tariffe <= 0 || transactionFlow == null) {
			return TariffeResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		long balance = 0;
		UserTariffeAccountEntity account = userTariffeAccountMapper.lockByUserid(userid);
		if (account == null) {
			account = new UserTariffeAccountEntity();
			account.setCreate_time(currDate);
			account.setUpdate_time(currDate);
			account.setUserid(userid);
			account.setDeposit(0L);
			account.setConsume(0L);
			account.setBalance(0L);
			account.setStatus(0);
			userTariffeAccountMapper.insert(account);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		if (userTariffeAccountLogContract.count(pageModel) != 0) {
			return TariffeResultDto.fail(AgentErrorCodeEnum.repeate_record.getCode());
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == logType && account.getBalance() < tariffe) {
			return TariffeResultDto.fail(AgentErrorCodeEnum.not_enough.getCode());
		}
		// 更新账户表的信息
		StringBuilder buf = new StringBuilder("balance=balance").append(logType == 1 ? "+" : "-").append(tariffe).append(",update_time=now()");
		if (logType == 1) {// 充值
			buf.append(",deposit=deposit+");
		} else {// 消费
			buf.append(",consume=consume+");
		}
		buf.append(tariffe);
		userTariffeAccountMapper.updateByStatement(buf.toString(), "id=" + account.getId());
		
		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		UserTariffeAccountLogEntity logEntity = new UserTariffeAccountLogEntity();
		logEntity.setCreate_time(currDate);
		logEntity.setUserid(userid);
		logEntity.setTariffe(tariffe);
		logEntity.setBalance(account.getBalance() + (logType == 0 ? -tariffe : tariffe));
		logEntity.setLog_type(logType);
		logEntity.setType(type);
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		userTariffeAccountLogContract.insert(logEntity);
		balance = logEntity.getBalance();
	
		return TariffeResultDto.success(balance);
	}

	@Override
	public long getTariffeBalance(long userid) {
		UserTariffeAccountEntity entity = userTariffeAccountMapper.findByProperty("userid", userid);
		return Tools.isNull(entity) ? 0L : entity.getBalance();
	}
	
	@Override
	public long getTariffeDeposit(long userid) {
		UserTariffeAccountEntity entity = userTariffeAccountMapper.findByProperty("userid", userid);
		return Tools.isNull(entity) ? 0L : entity.getDeposit();
	}

}
