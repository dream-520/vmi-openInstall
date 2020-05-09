/**
 * 
 */
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
import com.tigerjoys.shark.miai.agent.IUserEnergyAgent;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserEnergyAccountLogTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IUserEnergyAccountContract;
import com.tigerjoys.shark.miai.inter.contract.IUserEnergyAccountLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserRightCheckedLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserEnergyAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserEnergyAccountLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserRightCheckedLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserEnergyAccountMapper;

/**
 * ClassName: UserDiamondAgentImpl <br/>
 * date: 2017年5月10日 下午6:11:19 <br/>
 * 
 * @author chengang
 * @version
 * @since JDK 1.8.0
 */
@Service
public class UserEnergyAgentImpl implements IUserEnergyAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserEnergyAccountContract userEnergyAccountContract;

	@Autowired
	private IUserEnergyAccountLogContract userEnergyAccountLogContract;

	@Autowired
	private UserEnergyAccountMapper userEnergyAccountMapper;

	@Autowired
	private IUserRightCheckedLogContract userRightCheckedLogContract;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult changeEnergyAccount(long userId, long amount, Long money, int type, int logType, Integer payType, String transactionFlow,
			String memo) throws Exception {
		logger.info("userid:" + userId + ",amount:" + amount + ",money:" + money + ",type:" + type + ",logType:" + logType + ",transactionFlow:" + transactionFlow + ",memo:" + memo
				+ ",payType:" + payType);
		Date date = new Date();
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return AgentResult.fail(AgentErrorCodeEnum.parameter_error);
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		UserEnergyAccountEntity account = userEnergyAccountMapper.lockByUserId(userId);
		if (account == null) {
			account = new UserEnergyAccountEntity();
			account.setBalance(0L);
			account.setConsume(0L);
			account.setCreate_time(date);
			account.setDeposit(0L);
			account.setStatus(1);
			account.setUpdate_time(date);
			account.setUser_id(userId);
			userEnergyAccountMapper.insert(account);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		if (userEnergyAccountLogContract.count(pageModel) != 0) {
			return AgentResult.fail(AgentErrorCodeEnum.repeate_record);
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == logType && account.getBalance() < amount) {
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
		userEnergyAccountMapper.updateByStatement(buf.toString(), "id=" + account.getId());

		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		UserEnergyAccountLogEntity logEntity = new UserEnergyAccountLogEntity();
		logEntity.setBalance(account.getBalance() + (logType == 0 ? -amount : amount));
		logEntity.setCreate_time(date);
		logEntity.setDiamond(amount);
		logEntity.setIo(logType);
		logEntity.setType(type);
		logEntity.setUser_id(userId);
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		logEntity.setMoney(Tools.longValue(money));
		logEntity.setPay_type(Tools.intValue(payType));
		userEnergyAccountLogContract.insert(logEntity);
		return AgentResult.success(logEntity.getBalance());
	}

	@Override
	public long getEnergyBalance(long userId) {
		UserEnergyAccountEntity account = userEnergyAccountMapper.findByProperty("user_id", userId);
		return Tools.isNull(account) ? 0L : account.getBalance();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResult checkUserRight(long userId, long otherId, int diamond, int type) throws Exception {
		UserRightCheckedLogEntity userRightCheckedLogEntity = userRightCheckedLogContract.lockByUserId(userId, otherId, type);
		Date date = new Date();
		if(Tools.isNull(userRightCheckedLogEntity)){
			userRightCheckedLogEntity = new UserRightCheckedLogEntity();
			userRightCheckedLogEntity.setCreate_time(date);
			userRightCheckedLogEntity.setUpdate_time(date);
			userRightCheckedLogEntity.setDiamond(diamond);
			userRightCheckedLogEntity.setUser_id(userId);
			userRightCheckedLogEntity.setOther_id(otherId);
			userRightCheckedLogEntity.setType(type);
			userRightCheckedLogEntity.setStatus(0);
			userRightCheckedLogContract.insert(userRightCheckedLogEntity);
			AgentResult bal = changeEnergyAccount(userId, diamond,null, UserEnergyAccountLogTypeEnum.check_user.getCode(), 0, null, String.valueOf(userRightCheckedLogEntity.getId()), UserEnergyAccountLogTypeEnum.check_user.getDesc());
			if(bal.getCode() == AgentErrorCodeEnum.success.getCode()){
				userRightCheckedLogEntity.setUpdate_time(new Date());
				userRightCheckedLogEntity.setStatus(1);
				userRightCheckedLogContract.update(userRightCheckedLogEntity);
			}
			return bal;
		}else{
			if(1 == userRightCheckedLogEntity.getStatus()){
				return AgentResult.success();
			}else{
				AgentResult bal = changeEnergyAccount(userId, diamond,null,UserEnergyAccountLogTypeEnum.check_user.getCode(), 0, null, String.valueOf(userRightCheckedLogEntity.getId()), UserEnergyAccountLogTypeEnum.check_user.getDesc());
				if(bal.getCode() == AgentErrorCodeEnum.success.getCode()){
					userRightCheckedLogEntity.setUpdate_time(new Date());
					userRightCheckedLogEntity.setStatus(1);
					userRightCheckedLogContract.update(userRightCheckedLogEntity);
				}
				return bal;
			}
		}
	}
}
