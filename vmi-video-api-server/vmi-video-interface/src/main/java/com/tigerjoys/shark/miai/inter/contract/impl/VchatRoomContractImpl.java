package com.tigerjoys.shark.miai.inter.contract.impl;

import org.springframework.stereotype.Repository;

import com.tigerjoys.shark.miai.inter.contract.IVchatRoomContract;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.contract.AbstractBaseContract;
import com.tigerjoys.shark.miai.inter.mapper.VchatRoomMapper;

/**
 * 数据库中  [t_vchat_room]表 接口实现类
 * @author yangjunming
 * @Date 2019-07-18 15:18:56
 *
 */
@Repository
public class VchatRoomContractImpl extends AbstractBaseContract<VchatRoomEntity , VchatRoomMapper> implements IVchatRoomContract {

	@Override
	public int enterRoomUserId(long orderId, long userid) throws Exception {
		int rows = mapper.updateByStatement("userid="+userid, "orderId="+orderId+" and userid=0");
		int result = 0;
		if(rows>0){
			VchatRoomEntity entity =  mapper.findByProperty("orderId", orderId);
			if(entity.getAnchorid()>0){
				result = mapper.updateByStatement("pay_create=now(),pay_time=now(),total_price=price","orderId="+orderId);
			}
		}
		
		return result;
	}

	@Override
	public int enterRoomAnchor(long orderId, long anchorId) throws Exception {
		int rows = mapper.updateByStatement("anchorid="+anchorId, "orderId="+orderId+" and anchorid=0");
		int result = 0;
		if(rows>0){
			VchatRoomEntity entity =  mapper.findByProperty("orderId", orderId);
			if(entity.getUserid()>0){
				result = mapper.updateByStatement("pay_create=now(),pay_time=now(),total_price=price","orderId="+orderId);
			}
		}
		
		return result;
	}

	@Override
	public int pay(long orderId) throws Exception {
		return mapper.updateByStatement("pay_time=DATE_ADD(pay_time,INTERVAL 1 MINUTE),total_price=total_price+price", "orderId="+orderId+" and UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(pay_time)>=60");
	}

	@Override
	public int payYX(long id,boolean firstEntry) throws Exception {
		int rows = 0;
		if(firstEntry){
			rows = mapper.updateByStatement("pay_create=now(),pay_time=SUBDATE(NOW(),INTERVAL 5 SECOND),vchat_duration=0,total_price=0", "id="+id+" and pay_time is null");
			if(rows >0){
				rows = 2;
			}
		}
		if(rows == 0){
			rows = mapper.updateByStatement("pay_time=DATE_ADD(pay_time,INTERVAL 1 MINUTE)", "id="+id+" and pay_time is not null and UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(pay_time)>=60");
		}
		return rows;
	}
	
	public int payYXRecvExit(long id) throws Exception{
		return  mapper.updateByStatement("pay_time=DATE_ADD(pay_time,INTERVAL 1 MINUTE)", "id="+id+" and pay_time is not null and UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(pay_time)>=65");
	}
	

	@Override
	public int updateFinishTime(long id) throws Exception {
		return mapper.updateByStatement("finish_time=now(),sys_duration=UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(pay_create)","id="+id+" and sys_duration=0 and  pay_create is not null ");
	}
	
	@Override
	public int closeExceptionVchatRoom(long id) throws Exception {
		return mapper.updateByStatement("finish_time=now(),sys_duration=UNIX_TIMESTAMP(pay_time)+5-UNIX_TIMESTAMP(pay_create)","id="+id+" and sys_duration=0 and  pay_create is not null ");
	}
	
	@Override
	public int updateTotalPrice(long id,int price) throws Exception {
		return mapper.updateByStatement("vchat_duration=vchat_duration+1,total_price=total_price+"+price, "id="+id);
	}
	
	@Override
	public int updateFreeVchatDuration(long id) throws Exception {
		return mapper.updateByStatement("vchat_duration=vchat_duration+1", "id="+id);
	}
	
	@Override
	public int updateWeekCardDuration(long id) throws Exception {
		return mapper.updateByStatement("vchat_duration=vchat_duration+1,weekcard_duration=weekcard_duration+1", "id="+id);
	}

	@Override
	public int updateDialingFalg(long id) throws Exception {
		return mapper.updateByStatement("dialing_falg=1", "id="+id);
	}
	
	@Override
	public int updateWyChatId(long id,long wyChatId) throws Exception {
		return mapper.updateByStatement("wy_chatid="+wyChatId, "id="+id);
	}

	@Override
	public int updateFreeVchartFalg(long id ,int freeVchartFalg) throws Exception {
		return mapper.updateByStatement("free_vchart_falg="+freeVchartFalg, "id="+id);
	}
	
	@Override
	public int userExit(long id,Long userid,String shutInfo) throws Exception {
		String shutUser = "";
		if(Tools.isNotNull(userid)){
			shutUser = "shut_user="+userid+",";
		}
		return mapper.updateByStatement(shutUser+" shut_info='"+shutInfo+"'", "id="+id+" and LENGTH(shut_info)=0");
	}

	@Override
	public int checkPorn(long orderid,Long userid, int type) throws Exception {
		VchatRoomEntity room = getIdByOrderId(orderid);
		int rows = 0;
		if(Tools.isNotNull(room)){
			String shutUser = room.getUserid().equals(userid)?"用户":"主播";;
			String shutInfo = "";
			if(type ==2){
				shutInfo = shutUser+"色情";
			}else{
				shutInfo = shutUser+"低俗";
			}
			rows = mapper.updateByStatement("porn_falg="+type+" ,shut_info='"+shutInfo+"'", "id="+room.getId()+" and LENGTH(shut_info)=0");
		}
		return rows;
	}
	
	public VchatRoomEntity getIdByOrderId(long orderid) throws Exception {
		return mapper.findByProperty("orderId", orderid);
	}

	@Override
	public int updateFreeIncomeFalg(long id, int incomeFalg) throws Exception {
		return mapper.updateByStatement("free_income_falg="+incomeFalg, "id="+id);
	}

	@Override
	public int updateIncomeFalg(long id, int status) throws Exception {
		return mapper.updateByStatement("income_falg="+status, "id="+id);
	}

	@Override
	public int updateSysOffsetDuration(long id, int second) throws Exception {
		return mapper.updateByStatement("sys_offset_duration="+second, "id="+id);
	}
	
	
	
	
	
}
