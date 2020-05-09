package com.tigerjoys.shark.miai.agent.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.IAnchorDeductTimeAgent;
import com.tigerjoys.shark.miai.agent.IUserDiamondAgent;
import com.tigerjoys.shark.miai.agent.IVchatRoomSettlementAgent;
import com.tigerjoys.shark.miai.agent.dto.result.IncomeResultDto;
import com.tigerjoys.shark.miai.agent.enums.AgentErrorCodeEnum;
import com.tigerjoys.shark.miai.agent.enums.UserIncomeAccountLogTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomFreeVchartFalgEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomIncomeFalgEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomSettlementTypeEnum;
import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;

@Service
public class VchatRoomSettlementAgent implements IVchatRoomSettlementAgent {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IVchatRoomContract vchatRoomContract;
	
	@Autowired
	private IAnchorDeductTimeAgent anchorDeductTimeAgent;
	
	@Autowired
	private IUserDiamondAgent userDiamondAgent;
	
	/**
	 * 通话结算
	 * @param roomId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public IncomeResultDto<Long> vchatRoomSettlement(long roomId) throws Exception{
		VchatRoomEntity room = vchatRoomContract.findById(roomId);
		if(Tools.isNull(room)) {
			return IncomeResultDto.fail(AgentErrorCodeEnum.db_error.getCode());
		}
		logger.info("vchatRoomSettlement:roomId:"+roomId+";orderid:"+room.getOrderId()+";userid:"+room.getUserid()+";anchorid:"+room.getAnchorid());
		if(room.getSys_duration() == 0){
			vchatRoomContract.updateIncomeFalg(roomId, VchatRoomIncomeFalgEnum.anchor_no_income.getCode());
			return IncomeResultDto.success();
		}
		
		if(room.getFree_vchart_falg() == VchatRoomFreeVchartFalgEnum.free_vchart.getCode()){
			vchatRoomContract.updateIncomeFalg(roomId, VchatRoomIncomeFalgEnum.anchor_no_income.getCode());
			return IncomeResultDto.success();
		}
		
		UserIncomeAccountLogTypeEnum logType = 1 == room.getAv_type() ? UserIncomeAccountLogTypeEnum.audio_diamond : UserIncomeAccountLogTypeEnum.video_diamond;
		int sysOffsetDuration = 0;
		
		int sysDuration = room.getSys_duration()>room.getVchat_duration()*60? room.getVchat_duration()*60:room.getSys_duration();
		if(room.getFree_vchart_falg() == VchatRoomFreeVchartFalgEnum.free_vchart_charge.getCode()){
			if(room.getVchat_duration() == 1){
				vchatRoomContract.updateIncomeFalg(roomId, VchatRoomIncomeFalgEnum.anchor_no_income.getCode());
				return IncomeResultDto.success();
			}
			if(room.getVchat_duration()>1 && sysDuration>60 ){
				sysDuration = sysDuration-60;
			}
		}
		if(room.getOffset_flag()>0 && sysDuration>=60){
			sysOffsetDuration = anchorDeductTimeAgent.anchorDeductTime(room.getAnchorid(), sysDuration);
			vchatRoomContract.updateSysOffsetDuration(roomId,sysOffsetDuration);
		}
		int settlePrice = Tools.intValue(Math.round(room.getSettle_price()*room.getPay_discount()));
		IncomeResultDto<Long> result = userDiamondAgent.chatVideoAndAudioIncome(logType, VchatRoomSettlementTypeEnum.getByCode(room.getSettlement_type()), room.getAnchorid(), settlePrice,sysDuration, sysOffsetDuration, room.getOrderId()+"",  room.getAv_type() == 1 ?"音频聊天":"视频聊天");
		if(result.getCode() == IncomeResultDto.success().getCode()){
			vchatRoomContract.updateIncomeFalg(roomId, VchatRoomIncomeFalgEnum.income_recorded.getCode());
		}else{
			logger.info("vchatRoomSettlement:roomId:"+roomId+";orderid:"+room.getOrderId()+";userid:"+room.getUserid()+";anchorid:"+room.getAnchorid()+";result.code:"+result.getCode()+";result.msg:"+result.getMsg()+";出错");
		}
		
		return result;
	}
	


}
