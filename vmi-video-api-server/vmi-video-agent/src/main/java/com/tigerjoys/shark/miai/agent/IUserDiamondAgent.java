/**
 * 
 */
package com.tigerjoys.shark.miai.agent;

import java.util.Map;

import com.tigerjoys.shark.miai.agent.dto.result.DiamondResultDto;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.UserChatGiftLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserDiamondAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserVideoChatRecordChannelTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomFreeIncomeTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomSettlementTypeEnum;

/** 
 * ClassName: IUserDiamondAgent <br/> 
 * date: 2017年5月10日 下午4:45:57 <br/> 
 * 
 * @author mouzhanpeng 
 * @version  
 * @since JDK 1.8.0 
 */
public interface IUserDiamondAgent {

	/**
	 * 用户充值或消费
	 * @param userId - 用户ID
	 * @param amount - 钻石数量
	 * @param money - 充值钱数（分），消费为 null
	 * @param type - 充值或者消费的类型，参见@see {@link UserDiamondAccountLogTypeEnum}
	 * @param logType - 0消费，1充值
	 * @param payType - 充值类型,消费为 null
	 * @param transactionFlow - 流水标识
	 * @param memo - 备注
	 * @return DiamondResultDto<Long> - 本次操作成功或者失败和原因，用户的钻石余额
	 * @throws Exception
	 */
	public DiamondResultDto<Long> changeDiamondAccount(long userId, long amount, Long money, int type, int logType, Integer payType,
			String transactionFlow, String memo)
			throws Exception;

	/**
	 * 用户钻石余额
	 * @param userId
	 * @return
	 */
	public long getDiamondBalance(long userId);
	
	/**
	 * 用户充值总钻数
	 * @param userId
	 * @return
	 */
	public long getDiamondDeposit(long userId);

	/**
	 * 聊天每分钟扣钻
	 * @param type      类型 1-音频，2-视频
	 * @param userId	  自身ID
	 * @param otherId	  对方ID
	 * @param serialNum   会话流水号
	 * @param diamond	  钻/分钟
	 * @param channelType  渠道类型  UserVideoChatRecordChannelTypeEnum 枚举类
	 * @return          会话流水号
	 * @throws Exception
	 */
	public DiamondResultDto<Map<String, Long>> mediaChatInMinute(int type, long userId, long otherId, Long serialNum, int diamond,UserVideoChatRecordChannelTypeEnum channelType) throws Exception;
	
	/**
	 * 视频和语音聊天收益
	 * @param logType    			-	收益类型
	 * @param settleType    	-	结算类型 {@link VchatRoomSettlementTypeEnum}
	 * @param userId  			-	用户ID
	 * @param amount			-	钻
	 * @param sysDuration		-	时长（秒）
	 * @param sysOffsetDuration	-	时长偏移量(秒)
	 * @param transactionFlow	-	流水号
	 * @param memo
	 * @return
	 * @throws Exception
	 */
	public IncomeResultDto<Long> chatVideoAndAudioIncome(UserIncomeAccountLogTypeEnum type,VchatRoomSettlementTypeEnum settleType, long userId,long amount,int sysDuration,int sysOffsetDuration, String transactionFlow, String memo) throws Exception;
	
	/**
	 * 体验用户增加收益
	 * @param type      类型 1-音频，2-视频
	 * @param serialNum   会话流水号
	 * @param anchorid	  主播ID
	 * @param userId	  自身ID
	 * @param diamond	 钻
	 * @param vacuateFlag	是否分成  true 按钻石收益分成   false 固定1元分成
	 * @throws Exception
	 */
	public IncomeResultDto<Long> freeVchatAddIncome(int type, Long serialNum, long anchorid, long userid,
			int diamond,VchatRoomFreeIncomeTypeEnum incomeType,boolean vacuateFlag ) throws Exception;
	
	/**
	 * 查看对方信息扣钻石
	 * @param userId     -用户ID
	 * @param otherId    -对方ID
	 * @param diamond    -钻
	 * @param type       -类型，1-手机，2-视频
	 * @return
	 * @throws Exception
	 */
	public DiamondResultDto<Long> checkUserRight(long userId, long otherId, int diamond, int type) throws Exception;

	/**
	 * 世界杯竞猜
	 * @param userId     -用户ID
	 * @param diamond    -钻
	 * @param choice     -选择，1-主胜，2-平，3-客胜
	 * @param gameId		 -比赛ID
	 * @return
	 * @throws Exception
	 */
	public DiamondResultDto<Long> worldCupGuess(long userId, int diamond, int choice, long gameId) throws Exception;

	/**
	 * 世界杯竞猜奖励
	 * @param choice 选择，1-主胜，2-平，3-客胜
	 * @param gameId 比赛ID
	 * @return
	 * @throws Exception
	 */
	public DiamondResultDto<Long> worldCupGuessGain(int choice, long gameId) throws Exception;

	/**
	 * 聊天送礼物
	 * @param userId
	 * @param otherId
	 * @param giftId
	 * @param type  // 1视频 2 聊天   @see{@link UserChatGiftLogTypeEnum}
	 * @param giftBox  // false 不是礼物盒   true 礼物盒
	 * @return   
	 * @throws Exception
	 */
	public DiamondResultDto<Long> chatGiftCost(long userId, long otherId, long giftId,Integer type,boolean giftBox) throws Exception;
	
	/**
	 * 互动转盘
	 * @param userId
	 * @param otherId
	 * @param turntableId   转盘ID
	 * @return
	 * @throws Exception
	 */
	public DiamondResultDto<Long> chatTurntableCost(long userId, long otherId, long turntableId,long orderId) throws Exception;

	/**
	 * 聊天
	 * @param userId
	 * @param otherId
	 * @param diamond
	 * @return
	 * @throws Exception
	 */
	//public DiamondResultDto<Long> chatTextCost(long userId, long otherId, int diamond) throws Exception;
}
