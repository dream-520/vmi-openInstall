package com.tigerjoys.shark.miai.agent.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.LongHolder;
import org.shark.miai.common.enums.AppNameEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.BeanUtils;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.nbs.mybatis.core.page.PageModel;
import com.tigerjoys.nbs.mybatis.core.sql.Projections;
import com.tigerjoys.nbs.mybatis.core.sql.Restrictions;
import com.tigerjoys.nbs.web.context.RequestUtils;
import com.tigerjoys.shark.miai.agent.IAnchorRecvUserAgent;
import com.tigerjoys.shark.miai.agent.IGlobalBroadcastAgent;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.ISysConfigAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IUserGiftAgent;
import com.tigerjoys.shark.miai.agent.IUserGiftBoxAgent;
import com.tigerjoys.shark.miai.agent.IUserIncomeAgent;
import com.tigerjoys.shark.miai.agent.IUserPointAgent;
import com.tigerjoys.shark.miai.agent.IUserSpecialRechargeAgent;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VChatTCPInfoNotifyDto;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.dto.VacuateConfigDto;
import com.tigerjoys.shark.miai.agent.dto.result.AgentResult;
import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.AnchorRecvUserEnum;
import com.tigerjoys.shark.miai.agent.enums.GlobalBroadcastTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserChatGiftBoxLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserChatGiftLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserPointAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserVideoChatRecordChannelTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomFreeIncomeTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomSettlementTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IAnchorIntimateRankingsContract;
import com.tigerjoys.shark.miai.inter.contract.IAnchorOnlineContract;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserChatGiftLogContract;
import com.tigerjoys.shark.miai.inter.contract.ICopyUserContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatGiftLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatTurntableContract;
import com.tigerjoys.shark.miai.inter.contract.IUserChatTurntableLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserDiamondAccountLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserRightCheckedLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserTextChatRecordContract;
import com.tigerjoys.shark.miai.inter.contract.IUserVchatExperienceIncomeLogContract;
import com.tigerjoys.shark.miai.inter.contract.IUserVideoChatRecordContract;
import com.tigerjoys.shark.miai.inter.contract.IUserVideoChatRecordLogContract;
import com.tigerjoys.shark.miai.inter.contract.IWorldCupBetLogContract;
import com.tigerjoys.shark.miai.inter.contract.IWorldCupGameContract;
import com.tigerjoys.shark.miai.inter.entity.AnchorOnlineEntity;
import com.tigerjoys.shark.miai.inter.entity.CopyUserChatGiftLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatGiftLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableEntity;
import com.tigerjoys.shark.miai.inter.entity.UserChatTurntableLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountEntity;
import com.tigerjoys.shark.miai.inter.entity.UserDiamondAccountLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserRightCheckedLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserVchatExperienceIncomeLogEntity;
import com.tigerjoys.shark.miai.inter.entity.UserVideoChatRecordLogEntity;
import com.tigerjoys.shark.miai.inter.entity.WorldCupBetLogEntity;
import com.tigerjoys.shark.miai.inter.entity.WorldCupGameEntity;
import com.tigerjoys.shark.miai.inter.mapper.UserDiamondAccountMapper;

/**
 * ClassName: UserDiamondAgentImpl <br/>
 * date: 2017年5月10日 下午6:11:19 <br/>
 * 
 * @author chengang
 * @version
 * @since JDK 1.8.0
 */
@Service
public class UserDiamondAgentImpl implements IUserDiamondAgent {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IUserDiamondAccountLogContract userDiamondAccountLogContract;

	@Autowired
	private UserDiamondAccountMapper userDiamondAccountMapper;

	@Autowired
	private IUserVideoChatRecordContract userVideoChatRecordContract;

	@Autowired
	private IUserVideoChatRecordLogContract userVideoChatRecordLogContract;
	
	@Autowired
	private IUserPointAgent userPointAgent;
	
	@Autowired
	private IGlobalBroadcastAgent globalBroadcastAgent;
	
	@Autowired
	private IUserAgent userAgent;
	
	@Autowired
	private IUserGiftAgent userGiftAgent;

	@Autowired
	private IUserIncomeAgent userIncomeAgent;

	@Autowired
	private IUserRightCheckedLogContract userRightCheckedLogContract;

	@Autowired
	private IWorldCupBetLogContract worldCupBetLogContract;

	@Autowired
	private IWorldCupGameContract worldCupGameContract;

	@Autowired
	private IUserChatGiftContract userChatGiftContract;

	@Autowired
	private IUserChatGiftLogContract userChatGiftLogContract;

	@Autowired
	private INeteaseAgent neteaseAgent;

	@Autowired
	private IUserTextChatRecordContract userTextChatRecordContract;
	
	@Autowired
	private IAnchorIntimateRankingsContract anchorIntimateRankingsContract;
	
	@Autowired
	private IAnchorOnlineContract anchorOnlineContract;
	
	@Autowired
	private IUserChatTurntableContract userChatTurntableContract;
	
	@Autowired
	private IUserChatTurntableLogContract userChatTurntableLogContract;
	
	@Autowired
	private IUserVchatExperienceIncomeLogContract userVchatExperienceIncomeLogContract;
	
	@Autowired
	private ISysConfigAgent sysConfigAgent;
	
	@Autowired
	private IUserGiftBoxAgent userGiftBoxAgent;
	
	@Autowired
	private IAnchorRecvUserAgent anchorRecvUserAgent;
	
	@Autowired
	private ICopyUserContract copyUserContract;
	
	@Autowired
	private ICopyUserChatGiftLogContract copyUserChatGiftLogContract;
	
	@Autowired
	private IUserSpecialRechargeAgent userSpecialRechargeAgent;
	
	
	
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> changeDiamondAccount(long userId, long amount, Long money, int type, int logType, Integer payType, String transactionFlow, String memo)
			throws Exception {

		logger.info("userid:" + userId + ",amount:" + amount + ",money:" + money + ",type:" + type + ",logType:" + logType + ",transactionFlow:" + transactionFlow + ",memo:" + memo
				+ ",payType:" + payType);
		Date date = new Date();
		if (userId <= 0 || amount <= 0 || transactionFlow == null) {
			return DiamondResultDto.fail(AgentErrorCodeEnum.parameter_error.getCode());
		}
		// 根据用户ID加锁查询对应的信息，如果不存在的话则创建一条初始化信息
		UserDiamondAccountEntity account = userDiamondAccountMapper.lockByUserId(userId);
		if (account == null) {
			account = new UserDiamondAccountEntity();
			account.setBalance(0L);
			account.setConsume(0L);
			account.setCreate_time(date);
			account.setDeposit(0L);
			account.setStatus(1);
			account.setUpdate_time(date);
			account.setUser_id(userId);
			userDiamondAccountMapper.insert(account);
		}
		// 查询充值或消费流水，防止多次点击
		PageModel pageModel = PageModel.getLimitModel(0, 1);
		pageModel.addQuery(Restrictions.eq("transaction_flow", transactionFlow));
		pageModel.addQuery(Restrictions.eq("type", type));
		if (userDiamondAccountLogContract.count(pageModel) != 0) {
			return DiamondResultDto.fail(AgentErrorCodeEnum.repeate_record.getCode());
		}
		// 验证用户账户余额是够满足本次消费
		if (0 == logType && account.getBalance() < amount) {
			return DiamondResultDto.fail(AgentErrorCodeEnum.not_enough.getCode());
		}
		// 更新账户表的信息
		StringBuilder buf = new StringBuilder("balance=balance").append(logType == 1 ? "+" : "-").append(amount).append(",update_time=now()");
		if (logType == 1) {// 充值
			buf.append(",deposit=deposit+");
		} else {// 消费
			buf.append(",consume=consume+");
		}
		buf.append(amount);
		userDiamondAccountMapper.updateByStatement(buf.toString(), "id=" + account.getId());

		if (memo == null) {
			memo = "";
		} else if (memo.length() > 100) {
			memo = memo.substring(0, 100);
		}
		// 增加充值或者消费的流水记录
		UserDiamondAccountLogEntity logEntity = new UserDiamondAccountLogEntity();
		logEntity.setBalance(account.getBalance() + (logType == 0 ? -amount : amount));
		logEntity.setCreate_time(date);
		logEntity.setDiamond(amount);
		logEntity.setLog_type(logType);
		logEntity.setType(type);
		logEntity.setUser_id(userId);
		logEntity.setTransaction_flow(transactionFlow);
		logEntity.setMemo(memo);
		logEntity.setMoney(Tools.longValue(money));
		logEntity.setPay_type(Tools.intValue(payType));
		userDiamondAccountLogContract.insert(logEntity);

		return DiamondResultDto.success(logEntity.getBalance());
	}

	@Override
	public long getDiamondBalance(long userId) {
		UserDiamondAccountEntity entity = userDiamondAccountMapper.findByProperty("user_id", userId);
		return Tools.isNull(entity) ? 0L : entity.getBalance();
	}
	
	@Override
	public long getDiamondDeposit(long userId) {
		UserDiamondAccountEntity entity = userDiamondAccountMapper.findByProperty("user_id", userId);
		return Tools.isNull(entity) ? 0L : entity.getDeposit();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Map<String, Long>> mediaChatInMinute(int type, long userId, long otherId, Long serialNum, int diamond,UserVideoChatRecordChannelTypeEnum channelType) throws Exception {
		logger.info("mediaChatInMinute-[" + "type:" + type  + ",userId:" + userId + ",otherId:" + otherId + ",serialNum:" + serialNum + ",diamond:" + diamond+ ",channelType:" + channelType.getCode() + "]");
		Date date = new Date();
		Map<String, Long> data = new HashMap<>();
		//订单号不存在
		if (Tools.isNull(serialNum) || serialNum==0){
			return DiamondResultDto.fail(AgentErrorCodeEnum.vchat_orderid_no_exist.getCode());
		}
		
		long diamondBalance = getDiamondBalance(userId);
		
		//复查余额情况
		if (diamond > diamondBalance){
			return DiamondResultDto.fail(AgentErrorCodeEnum.not_enough.getCode());
		}
		
		// 扣费记录
		UserVideoChatRecordLogEntity chatLog = new UserVideoChatRecordLogEntity();
		chatLog.setDiamond((long)diamond);
		chatLog.setChannel_type(channelType.getCode());
		chatLog.setLast(0);
		chatLog.setCreate_time(date);
		chatLog.setRecord_id(serialNum);
		userVideoChatRecordLogContract.insert(chatLog);
		// 聊天结算收益
		//userIncomeAgent.chatDiamondIncome(type + 1, otherId, diamond, String.valueOf(chatLog.getId()), type ==1?"音频聊天收益" :"视频聊天收益");
	
		// 扣钻
		UserDiamondAccountLogTypeEnum logType = 1 == type ? UserDiamondAccountLogTypeEnum.audio_chat : UserDiamondAccountLogTypeEnum.video_chat;
		DiamondResultDto<Long> bal = changeDiamondAccount(userId, diamond, null, logType.getCode(), 0, null, String.valueOf(chatLog.getId()), logType.getDesc());
		if(AgentErrorCodeEnum.success.getCode() == bal.getCode()){
			data.put("balance", bal.getData());
			data.put("serialNum", serialNum);
			logger.info("mediaChatInMinute-[" + "type:" + type + " ,userId:" + userId + ",otherId:" + otherId + ",serialNum:" + serialNum + ",diamond:" + diamond+ ",channelType:" + channelType.getCode() + "]_success");
			return DiamondResultDto.success(data);
		}else{
			data.put("serialNum", serialNum);
			throw new Exception("code:"+bal.getCode()+",msg:"+bal.getMsg()); 
			//return DiamondResultDto.failAndData(bal.getCode(), data);
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> chatVideoAndAudioIncome(UserIncomeAccountLogTypeEnum type,VchatRoomSettlementTypeEnum settleType, long userId,long amount,int sysDuration,int sysOffsetDuration, String transactionFlow, String memo) throws Exception {
		VacuateConfigDto vacuate = sysConfigAgent.vacuate();
		long money = (long) (amount * vacuate.getDiamondToMoney() * 100);
		long duration = (long)sysDuration - sysOffsetDuration;
		long initMoney = getMoney(settleType,money,duration);
		long internalAmount = getMoney(settleType,money,(long)sysDuration);
		
		return  userIncomeAgent.departIncomeAccount(userId, initMoney,internalAmount, type, vacuate, transactionFlow, memo);
	}
	
	
	public long getMoney(VchatRoomSettlementTypeEnum settleType,long money,long duration){
		long initMoney = money;
		if(VchatRoomSettlementTypeEnum.minute == settleType){
			long div = duration/60;
			if(duration%60>0){
				div = div+1;
			}
			initMoney = money * div;
		}else if(VchatRoomSettlementTypeEnum.second == settleType){
			long div = duration/10;
			if(duration<10){
				div = 1;
			}
			initMoney = money * div/6;
		}
		return initMoney;
	}
	
	
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> freeVchatAddIncome(int type, Long serialNum, long anchorid, long userid,
			int diamond,VchatRoomFreeIncomeTypeEnum incomeType,boolean vacuateFlag ) throws Exception {
		if (Tools.isNull(serialNum) || serialNum == 0) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.vchat_orderid_no_exist.getCode());
		}
		// 扣费记录
		UserVchatExperienceIncomeLogEntity incomeLog = userVchatExperienceIncomeLogContract.findByProperty("orderId", serialNum);
		if(Tools.isNull(incomeLog)){
			incomeLog = new UserVchatExperienceIncomeLogEntity();
			incomeLog.setOrderId(serialNum);
			incomeLog.setAnchorid(anchorid);
			incomeLog.setUserid(userid);
			incomeLog.setPrice(diamond);
			incomeLog.setAv_type(type);
			incomeLog.setStatus(1);
			incomeLog.setDuration_falg(vacuateFlag?1:0);
			incomeLog.setCreate_time(new Date());
			userVchatExperienceIncomeLogContract.insert(incomeLog);
		}else{
			return IncomeResultDto.success();
		}
		
		IncomeResultDto<Long> result = null;
		VacuateConfigDto vacuate = sysConfigAgent.vacuate();
		if(vacuateFlag){
			// 聊天结算收益
			if(incomeType == VchatRoomFreeIncomeTypeEnum.free_old_income ){
				result = userIncomeAgent.chatDiamondIncome(6, anchorid, diamond, String.valueOf(serialNum),"视频聊天通话激励");
			}else if(incomeType == VchatRoomFreeIncomeTypeEnum.free_new_income){
				result = userIncomeAgent.departIncomeAccount(anchorid, 100, UserIncomeAccountLogTypeEnum.free_income_video_ge_30s_action2, vacuate, String.valueOf(serialNum), "视频聊天通话激励");
			}else if(incomeType == VchatRoomFreeIncomeTypeEnum.free_new2_income){
				result = userIncomeAgent.departIncomeAccount(anchorid, 100, UserIncomeAccountLogTypeEnum.free_income_video_ge_30s_action2, vacuate, String.valueOf(serialNum), "视频聊天通话激励");
			}
		}else{
			if(incomeType == VchatRoomFreeIncomeTypeEnum.free_old_income ){
				result = userIncomeAgent.departIncomeAccount(anchorid, 100, UserIncomeAccountLogTypeEnum.free_income_video_lt_30s, vacuate, String.valueOf(serialNum), "视频聊天通话激励");
			}else if(incomeType == VchatRoomFreeIncomeTypeEnum.free_new2_income){
				result = userIncomeAgent.departIncomeAccount(anchorid, 50, UserIncomeAccountLogTypeEnum.free_income_video_lt_30s, vacuate, String.valueOf(serialNum), "视频聊天通话激励");
			}
		}
	    if(Tools.isNull(result)){
	    	return IncomeResultDto.fail(1,"没有符合条件,没有结算收益");
	    }
		
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> checkUserRight(long userId, long otherId, int diamond, int type) throws Exception {
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
			DiamondResultDto<Long> bal = changeDiamondAccount(userId, diamond,null,UserDiamondAccountLogTypeEnum.check_user.getCode(), 0, null, String.valueOf(userRightCheckedLogEntity.getId()), UserDiamondAccountLogTypeEnum.check_user.getDesc());
			if(bal.getCode() == AgentErrorCodeEnum.success.getCode()){
				userRightCheckedLogEntity.setUpdate_time(new Date());
				userRightCheckedLogEntity.setStatus(1);
				userRightCheckedLogContract.update(userRightCheckedLogEntity);
			}
			return bal;
		}else{
			if(1 == userRightCheckedLogEntity.getStatus()){
				return DiamondResultDto.success();
			}else{
				DiamondResultDto<Long> bal = changeDiamondAccount(userId, diamond,null,UserDiamondAccountLogTypeEnum.check_user.getCode(), 0, null, String.valueOf(userRightCheckedLogEntity.getId()), UserDiamondAccountLogTypeEnum.check_user.getDesc());
				if(bal.getCode() == AgentErrorCodeEnum.success.getCode()){
					userRightCheckedLogEntity.setUpdate_time(new Date());
					userRightCheckedLogEntity.setStatus(1);
					userRightCheckedLogContract.update(userRightCheckedLogEntity);
				}
				return bal;
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> worldCupGuess(long userId, int diamond, int choice, long gameId) throws Exception {
		WorldCupGameEntity game = worldCupGameContract.findById(gameId);
		if(null == game || (1 != choice && 2 != choice && 3 != choice)){
			return DiamondResultDto.fail(-1, "数据错误！");
		}
		if(game.getGame_time().before(new Date())){
			return DiamondResultDto.fail(-2, "时间已过，不可投注！");
		}
		long id = IdGenerater.generateId();
		DiamondResultDto<Long> resultDto = changeDiamondAccount(userId, diamond, null, UserDiamondAccountLogTypeEnum.world_cup_guess.getCode(),
				0, null, String.valueOf(id), UserDiamondAccountLogTypeEnum.world_cup_guess.getDesc());
		if(AgentErrorCodeEnum.success.getCode() == resultDto.getCode()){
			WorldCupBetLogEntity log = new WorldCupBetLogEntity();
			log.setId(id);
			log.setCreate_time(new Date());
			log.setUser_id(userId);
			log.setDiamond(diamond);
			log.setChoice(choice);
			log.setGame_id(gameId);
			log.setRatio(getRatioByChoice(choice, game));
			worldCupBetLogContract.insert(log);
		}
		return resultDto;
	}

	/**
	 * 获得赔率
	 * @param choice
	 * @param game
	 * @return
	 */
	private int getRatioByChoice(int choice, WorldCupGameEntity game) {
		switch (choice) {
			case 1:
				return game.getHome_win();
			case 2:
				return game.getDogfall();
			case 3:
				return game.getGuest_win();
			default:
				return 0;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> worldCupGuessGain(int choice, long gameId) throws Exception {
		if(1 != choice && 2 != choice && 3 != choice){
			return DiamondResultDto.fail(-1, "数据错误！");
		}
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.eq("game_id", gameId));
		pageModel.addQuery(Restrictions.eq("choice", choice));
		List<WorldCupBetLogEntity> list = worldCupBetLogContract.load(pageModel);
		for (WorldCupBetLogEntity log : list){
			changeDiamondAccount(log.getUser_id(), log.getDiamond() * log.getRatio() / 100, null, UserDiamondAccountLogTypeEnum.world_cup_guess_gain.getCode(),
					1, null, String.valueOf(log.getId()), UserDiamondAccountLogTypeEnum.world_cup_guess_gain.getDesc());
		}
		return DiamondResultDto.success();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> chatGiftCost(long userId, long otherId, long giftId,Integer type,boolean giftBox) throws Exception {
		UserChatGiftEntity gift = userChatGiftContract.findById(giftId);
		long logId = IdGenerater.generateId();
		Integer giftDiamond = gift.getDiamond();
		String packageName = RequestUtils.getCurrent().getHeader().getPackageName();
		/*
		if (AppNameEnum.ios_com_tjhj_miyou.getPackageName().equals(packageName)){
			giftDiamond = giftDiamond;
		}
		*/
		if(AppNameEnum.ios_com_duidui_duijiaoyou.getPackageName().equals(packageName) ){
			giftDiamond = giftDiamond / 20;
		}
		
		String checkClientId = RequestUtils.getCurrent().getHeader().getClientId();
		HashMap<String, String> userCheck = new HashMap<>();
		userCheck.put("163763228044755200", "userId");
		userCheck.put("163578174150607104", "userId");
		userCheck.put("d5b748e4bc3beeaf426624b95aee0f89", "clientid");
		userCheck.put("223.91.195.36", "IP");
		userCheck.put("223.91.195.226", "IP");
		userCheck.put("74258793973743872", "testUser");
		userCheck.put("159805667281010944", "testUser");
		if(userCheck.containsKey(String.valueOf(userId)) 
				|| userCheck.containsKey(checkClientId) 
				|| userCheck.containsKey(Tools.getIP(RequestUtils.getCurrent().getRequest())) 
				){
			String date = Tools.getFormatDate(new Date(),"yyyy-MM-dd");
			String begin = date+" 00:00:00";
			String end = date+" 23:59:59";
			PageModel pageModel = PageModel.getPageModel();
			pageModel.addProjection(Projections.sum("diamond").as("totalPrice"));
			pageModel.addQuery(Restrictions.eq("user_id", userId));
			pageModel.addQuery(Restrictions.ge("create_time", begin));
			pageModel.addQuery(Restrictions.le("create_time", end));
			List<Map<String, Object>> maps = userChatGiftLogContract.loadGroupBy(pageModel);
			LongHolder totalPrice = new LongHolder(0);
			if (Tools.isNotNull(maps)) {
				maps.stream().forEach(v -> {
					if (Tools.isNotNull(v)) {
						totalPrice.value = Tools.parseLong(v.get("totalPrice"));
					}
				});
			}
			if(totalPrice.value+giftDiamond>=20000){
				return DiamondResultDto.fail(101,"今天发送太多礼物，明天再试");
			}
			//return DiamondResultDto.fail(101,"此功能升级中，无法发送");
		}
		
		if (userSpecialRechargeAgent.getUserIdList().contains(userId)) {
			if (giftDiamond >= 4000) {
				giftDiamond = 20;
			}
		}
	
		UserBO userBo = userAgent.findById(userId);
		if(Tools.isNotNull(userBo) && !userBo.isWaiter() && userBo.vipValue()==0 && gift.getType()==2 && !giftBox){
			return DiamondResultDto.fail(AgentErrorCodeEnum.gift_vip_no_send.getCode());
		}
		DiamondResultDto<Long> result = null;
		if(giftBox){
			AgentResult resultAgent = userGiftBoxAgent.changeGiftBoxAccount(userId, otherId, giftId, 1, UserChatGiftBoxLogTypeEnum.user_presented, String.valueOf(logId), "礼物盒发送["+gift.getName()+"]");
			if(AgentErrorCodeEnum.success.getCode() == resultAgent.getCode()){
				result =  DiamondResultDto.success(getDiamondBalance(userId));
			}else{
				result = DiamondResultDto.fail(resultAgent.getCode(),resultAgent.getCodemsg());
			}
		}else{
			result = changeDiamondAccount(userId, giftDiamond, null,
					UserDiamondAccountLogTypeEnum.chat_gift.getCode(), 0, null, String.valueOf(logId), UserDiamondAccountLogTypeEnum.chat_gift.getDesc());
		}
		if(AgentErrorCodeEnum.success.getCode() == result.getCode()){
			//不是主播也累计，以后成为主播，累计收益还在
			anchorIntimateRankingsContract.addIncome(otherId, userId,  giftDiamond);
			UserChatGiftLogEntity log = new UserChatGiftLogEntity();
			log.setId(logId);
			log.setCreate_time(new Date());
			log.setGift_id(giftId);
			log.setSend_type(type);
			log.setDiamond(giftBox?0:giftDiamond);
			log.setUser_id(userId);
			log.setOther_id(otherId);
			log.setBox_flag(giftBox?1:0);
			userChatGiftLogContract.insert(log);
			
			if (Tools.isNotNull(copyUserContract.findById(userId)) && Tools.isNotNull(copyUserContract.findById(otherId))) {
				CopyUserChatGiftLogEntity copyLog = new CopyUserChatGiftLogEntity();
				BeanUtils.copyBean(log, copyLog);
				copyUserChatGiftLogContract.insert(copyLog);
			}
			
			
			//修改或添加礼物统计
			userGiftAgent.insertOrUpdate(userId, otherId, giftDiamond);
			//送积分
			userPointAgent.changePointAccount(userId, UserPointAccountLogTypeEnum.send_gifts.getCode(), 1, String.valueOf(log.getId()), UserPointAccountLogTypeEnum.send_gifts.getDesc());
			//添加送礼物全局广播信息 
			globalBroadcastAgent.insert(userId, otherId, 0, GlobalBroadcastTypeEnum.gift.getCode(), giftId);
			
			UserBO otherBo = userAgent.findById(otherId);
			if(Tools.isNotNull(otherBo)){
				if(!otherBo.isWaiter()){
					userGiftBoxAgent.changeGiftBoxAccount(otherId,userId, giftId, 1, UserChatGiftBoxLogTypeEnum.anchor_presented, String.valueOf(logId), gift.getName());
				}else{
					if(giftId != 23){
						userIncomeAgent.chatDiamondIncome(4, otherId, giftDiamond, String.valueOf(logId), "["+ gift.getName() +"]礼物收益");
					}
				}
			}
		
			
			// 发送礼物
			logger.info("chatGiftCost:userid={};anchor={}",otherId);
			neteaseAgent.sendGiftPic(userId, otherId, gift.getName(), gift.getIcon(), giftId,true);
			if(!userBo.isWaiter()){
				anchorRecvUserAgent.checkAnchorRecvUser(userId, otherId, AnchorRecvUserEnum.gift.getCode());
			}
			if(UserChatGiftLogTypeEnum.audio.getCode()==type || UserChatGiftLogTypeEnum.video.getCode()==type){
				//UserBO userBo = userAgent.findById(userId);
				String nickname = Tools.isNotNull(userBo)?userBo.getNickname():"";
				VChatTCPInfoNotifyDto notify = new VChatTCPInfoNotifyDto();
				notify.setText( Tools.getFormatDate(new Date(),"HH:mm")+":收到来自"+nickname+"的一个"+gift.getName()+"礼物");
				notify.setTextColor("#ffffff");
				notify.setTextShadowColor("#333333");
				VChatVideoTCPDto dto = new VChatVideoTCPDto();
				dto.setType(VChatVideoTCPTypeEnum.video.getCode());
				dto.setSubType(VChatVideoStatusEnum.info_notify.getCode());
				dto.setData(JsonHelper.toJson(notify));
				neteaseAgent.pushOneAttachMessage(userId,
						otherId, JsonHelper.toJson(dto)); // 主播用户可能是假账户
				
				AnchorOnlineEntity anhcor = anchorOnlineContract.findByProperty("userid", otherId);
					if(Tools.isNotNull(anhcor)){
						if(anhcor.getParent_userid() > 0){
							// 发送主账号礼物
							logger.info("chatGiftCost:userid={};anchor={}",userId,anhcor.getParent_userid());
							neteaseAgent.sendGiftPic(userId, anhcor.getParent_userid(), gift.getName(), gift.getIcon(), giftId,false);
							neteaseAgent.pushOneAttachMessage(userId,
									otherId, JsonHelper.toJson(dto)); // 主播用户可能是假账户
						}
					}
			}
		}
		
		return  result;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> chatTurntableCost(long userId, long otherId, long turntableId,long orderId) throws Exception {
		UserChatTurntableEntity turntable = userChatTurntableContract.findById(turntableId);
		//long logId = IdGenerater.generateId();
		DiamondResultDto<Long> result = changeDiamondAccount(userId, turntable.getDiamond(), null,
				UserDiamondAccountLogTypeEnum.chat_turntable.getCode(), 0, null, String.valueOf(orderId), UserDiamondAccountLogTypeEnum.chat_turntable.getDesc());
		if(AgentErrorCodeEnum.success.getCode() == result.getCode()){
			//不是主播也累计，以后成为主播，累计收益还在
			anchorIntimateRankingsContract.addIncome(otherId, userId,  turntable.getDiamond());
			UserChatTurntableLogEntity log = new UserChatTurntableLogEntity();
			log.setId(orderId);
			log.setCreate_time(new Date());
			log.setTurntable_id(turntable.getId());
			log.setTurntable_name(turntable.getName());
			log.setDiamond(turntable.getDiamond());
			log.setUser_id(userId);
			log.setOther_id(otherId);
			userChatTurntableLogContract.insert(log);
			//修改或添加礼物统计
			//userGiftAgent.insertOrUpdate(userId, otherId, gift.getDiamond());
			//添加送礼物全局广播信息 
			//globalBroadcastAgent.insert(userId, otherId, 0, GlobalBroadcastTypeEnum.gift.getCode(), giftId);
			userIncomeAgent.chatDiamondIncome(5, otherId, turntable.getDiamond(), String.valueOf(orderId), "["+ turntable.getName() +"]转盘收益");
		}
		return result;
	}

	/*
	@Override
	@Transactional(rollbackFor = Exception.class)
	public DiamondResultDto<Long> chatTextCost(long userId, long otherId, int diamond) throws Exception {
		long logId = IdGenerater.generateId();
		DiamondResultDto<Long> result = changeDiamondAccount(userId, diamond, null,
				UserDiamondAccountLogTypeEnum.txt_chat.getCode(), 0, null, String.valueOf(logId), UserDiamondAccountLogTypeEnum.txt_chat.getDesc());
		if(AgentErrorCodeEnum.success.getCode() == result.getCode()){
			UserTextChatRecordEntity log = new UserTextChatRecordEntity();
			log.setId(logId);
			log.setCreate_time(new Date());
			log.setDiamond((long)diamond);
			log.setUser_id(userId);
			log.setOther_id(otherId);
			userTextChatRecordContract.insert(log);
			userIncomeAgent.chatDiamondIncome(4, otherId, diamond, String.valueOf(logId), "图文聊天收益");
		}
		return  result;
	}
	*/
	
	
	
}
