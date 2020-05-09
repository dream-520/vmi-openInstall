package com.tigerjoys.shark.miai.agent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.shark.miai.common.enums.UserCreditLogTypeEnum;
import org.shark.miai.common.enums.UserCreditRecordEnum;
import org.shark.miai.common.enums.UserCreditScoreAccountResultEnum;
import org.shark.miai.common.enums.UserTypeEnum;
import org.shark.miai.common.util.TimeTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.CreditRecordBO;
import com.tigerjoys.shark.miai.agent.dto.CreditScoreConfigureBO;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.service.IUserCreditScoreService;
import com.tigerjoys.shark.miai.inter.contract.ICreditScoreConfigureContract;
import com.tigerjoys.shark.miai.inter.contract.IUserCreditScoreContract;
import com.tigerjoys.shark.miai.inter.contract.IUserCreditScoreLogContract;
import com.tigerjoys.shark.miai.inter.entity.CreditScoreConfigureEntity;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreEntity;
import com.tigerjoys.shark.miai.inter.entity.UserCreditScoreLogEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserCreditScoreMapper;

/**
 * 用户信用分服务实现类
 * @author liuman
 *
 */
@Service
public class UserCreditScoreServiceImpl implements IUserCreditScoreService {
	
	@Autowired
	private ICreditScoreConfigureContract creditScoreConfigureContract;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserCreditScoreLogContract userCreditScoreLogContract;
	
	@Autowired
	private IUserCreditScoreContract userCreditScoreContract;
	
	@Autowired
	private UserCreditScoreMapper userCreditScoreMapper;
	
	@Override
	public List<CreditScoreConfigureBO> findPurchaseCreditScoreList(long userid) throws Exception {
		//查询`t_credit_score_configure`表
		PageModel pageModel = PageModel.getPageModel();
		UserBO user = userAgent.findById(userid);
		if (user.isWaiter()) {
			pageModel.addQuery(Restrictions.eq("type", UserTypeEnum.servicer.getCode()));
		} else {
			pageModel.addQuery(Restrictions.eq("type", UserTypeEnum.ordinary.getCode()));
		}
		List<CreditScoreConfigureEntity> configures = creditScoreConfigureContract.load(pageModel);
		List<CreditScoreConfigureBO> configureBOs = null;
		if (CollectionUtils.isNotEmpty(configures)) {
			configureBOs = new ArrayList<>();	
			for (CreditScoreConfigureEntity configure : configures) {
				//entity to bo
				configureBOs.add(this.entityToBO(configure));
			}
		}

		return configureBOs;
	}
	
	@Override
	public List<CreditRecordBO> findCreditScoreRecordList(long userid) throws Exception {
		// 查询t_user_credit_score_log表
		PageModel pageModel = PageModel.getLimitModel(0, 100);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		pageModel.desc("create_time");
		List<UserCreditScoreLogEntity> logs = userCreditScoreLogContract.load(pageModel);
		List<CreditRecordBO> records = null;
		if (CollectionUtils.isNotEmpty(logs)) {
			records = new ArrayList<>();
			for (UserCreditScoreLogEntity log : logs) {
				//entity to bo
				records.add(this.entityToBO(log));
			}
		}
		return records;
	}

	@Override
	public List<CreditRecordBO> findCreditScoreRecordListByPaging(long userid,long recordId , int pagesize) throws Exception {
		// 查询t_user_credit_score_log表
		PageModel pageModel = PageModel.getLimitModel(0, pagesize+1);
		pageModel.addQuery(Restrictions.eq("userid", userid));
		if (recordId > 0l) {
			pageModel.addQuery(Restrictions.le("id", recordId));
		}
		pageModel.desc("create_time");
		List<UserCreditScoreLogEntity> logs = userCreditScoreLogContract.load(pageModel);
		List<CreditRecordBO> records = null;
		if (CollectionUtils.isNotEmpty(logs)) {
			records = new ArrayList<>();
			for (UserCreditScoreLogEntity log : logs) {
				//entity to bo
				records.add(this.entityToBO(log));
			}
		}
		return records;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> changeCreditScore(Long userId, boolean isAdd, long changeScore, String transactionFlow,int type,String memo) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		List<UserCreditScoreLogEntity> userLogHistories = this.queryAccountLogs(transactionFlow, type);
		//如果该流水已经更新到t_user_credit_score_log表,说明充值或消费已经成功了,不需要再对账户表进行操作,直接返回数据
		if (CollectionUtils.isNotEmpty(userLogHistories)) {
			UserCreditScoreLogEntity userLogHistory = userLogHistories.get(0);
			if (userLogHistory != null) {
				resultMap.put(UserCreditScoreAccountResultEnum.haveBeCall.getKey(), UserCreditScoreAccountResultEnum.haveBeCall.getCode());
				return resultMap;
			}
		}
		
		//查询该账号( select * from t for update)
		//此处看扣除是否成功
		UserCreditScoreEntity account = userCreditScoreMapper.lockByUserId(userId);
		
		if(memo == null) memo = "";
		
		StringBuilder buf = this.organizeUpdateStatement(isAdd, changeScore);
		userCreditScoreMapper.updateByStatement(buf.toString(), "id="+account.getId());
		Date currentDate = new Date();
		//记录日志
		UserCreditScoreLogEntity log = new UserCreditScoreLogEntity();
		log.setAmount(changeScore);
		log.setBalance(account.getBalance()+(isAdd ? changeScore : -changeScore));
		log.setCreate_time(currentDate);
		log.setLogtype(isAdd ? 1 : 0);
		log.setMemo(memo);
		log.setType(type);
		log.setUserid(userId);
		log.setTransaction_flow(transactionFlow);
		userCreditScoreLogContract.insert(log);
		
		resultMap.put(UserCreditScoreAccountResultEnum.success.getKey(), UserCreditScoreAccountResultEnum.success.getCode());
		
		return resultMap;
	}

	@Override
	public void changeUserCreditStatus(long userid, int status, String memo) throws Exception {
		
	}

	@Override
	public CreditScoreConfigureBO entityToBO(CreditScoreConfigureEntity configure) {
		CreditScoreConfigureBO configfureBO = new CreditScoreConfigureBO();
		configfureBO.setDiamonds(configure.getDiamonds());
		configfureBO.setId(configure.getId());
		configfureBO.setScore(configure.getScore());
		configfureBO.setSub_theme(configure.getSub_theme());
		configfureBO.setTheme(configure.getTheme());
		configfureBO.setType(configure.getType());

		return configfureBO;
	}

	@Override
	public CreditRecordBO entityToBO(UserCreditScoreLogEntity log) {
		CreditRecordBO record = new CreditRecordBO();
		record.setCreateDate(Tools.getFormatDate(log.getCreate_time(), TimeTools.YYYY_MM_DD_HH_mm));
		record.setId(log.getId());
		int logType = log.getLogtype();
		if (logType == UserCreditLogTypeEnum.consume.getCode()) {
			record.setScoreDesc("-" + log.getAmount() + "信用分");
		} else {
			record.setScoreDesc("+" + log.getAmount() + "信用分");
		}
		record.setSource(log.getMemo());
		return record;
	}

	@Override
	public long getUserCreditBalance(long userid) throws Exception {
		UserCreditScoreEntity credit = userCreditScoreContract.findByProperty("userid", userid);
		return credit!=null?credit.getBalance():0L;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void initUserCreaditAccount(long userid,int type) throws Exception {
		//userid不合法,则抛出异常
		if(userid <= 0) {
			throw new Exception("传入参数有错误，无法进行操作");
		}
		UserCreditScoreEntity userCreditScore = new UserCreditScoreEntity();
		long changeScore = 100;
		
		Date currentDate = new Date();
		userCreditScore.setBalance(changeScore);
		userCreditScore.setConsume(0l);
		userCreditScore.setCreate_time(currentDate);
		userCreditScore.setDeposit(changeScore);
		userCreditScore.setStatus(1);
		userCreditScore.setType(type);
		userCreditScore.setUpdate_time(currentDate);
		userCreditScore.setUserid(userid);
		userCreditScoreContract.insert(userCreditScore);
		
		//记录日志
		UserCreditScoreLogEntity log = new UserCreditScoreLogEntity();
		log.setAmount(changeScore);
		log.setBalance(changeScore);
		log.setCreate_time(currentDate);
		log.setLogtype(1);
		log.setMemo(UserCreditRecordEnum.initAdd.getDesc());
		log.setType(UserCreditRecordEnum.initAdd.getCode());
		log.setUserid(userid);
		log.setTransaction_flow(String.valueOf(userid));
		userCreditScoreLogContract.insert(log);
	}
	
	/**
	 * 查询流水记录日志
	 * @param transactionFlow 流水号
	 * @param type 类型
	 * @return
	 * @throws Exception
	 */
	private List<UserCreditScoreLogEntity> queryAccountLogs(String transactionFlow,int type) throws Exception {
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		return userCreditScoreLogContract.load(pageModel);
	}

	/**
	 * 拼接更新语句
	 * @param isAdd
	 * @param amount
	 * @return
	 */
	private StringBuilder organizeUpdateStatement(boolean isAdd, long amount){
		StringBuilder buf = new StringBuilder("balance=balance");
		buf.append(isAdd ? "+" : "-");
		buf.append(amount);
		//更新操作时间
		buf.append(",update_time=now()");
		//如果是充值调用,充值金额字段要增加
		if(isAdd) {
			buf.append(",deposit=deposit+");
		} else {//如果是消费调用,消费金额字段
			buf.append(",consume=consume+");
		}
		buf.append(amount);
		
		return buf;
	}
}
