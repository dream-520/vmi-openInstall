package com.tigerjoys.shark.miai.dto.service;

import java.util.Date;

import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomAvTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomFreeIncomeFalgEnum;
import com.tigerjoys.shark.miai.agent.enums.VchatRoomFreeVchartFalgEnum;
import com.tigerjoys.shark.miai.inter.entity.UserVchatExperienceIncomeLogEntity;
import com.tigerjoys.shark.miai.inter.entity.VchatRoomEntity;

/**
 * 我的通话
 * @author yangjunming
 *
 */
public class MyPhoneDto {
	/**
     * 用户信息
     */
	private UserBaseInfo  userInfo;
    /**
     * 1 无人接听 2 未接来电 3 通话时长
     */
    private int status;
    
    /**
     * 1 语音  2 视频
     */
    private int avType;
    
    /**
     * 描述（无人接听/未接来电/通话时长收入）
     */
    private String describe;
    /**
     * 日期
     */
    private String date;
    
    /**
     * 时间
     */
    private String time;
    
    public static MyPhoneDto preDto(VchatRoomEntity re,UserBO otherUser,UserVchatExperienceIncomeLogEntity incomeLog){
    	Date current = new Date();
    	MyPhoneDto dto = new MyPhoneDto();
    	UserBaseInfo info = new UserBaseInfo();
    	info.setUserId(otherUser.getUserid());
    	info.setNickName(otherUser.getNickname());
    	info.setPhoto(Const.getCdn(otherUser.getPhoto()));
    	info.setUserType(otherUser.isWaiter()?1:0);
    	//TODO
    	//info.setAnchorTypeAudio(anchorTypeAudio);
    	dto.setUserInfo(info);
    	dto.setAvType(re.getAv_type());
    	if(Tools.isNull(re.getPay_create())){
	    	if(re.getSponsor_user()== otherUser.getUserid()){
	    		dto.setStatus(2);
	    		dto.setDescribe("("+VchatRoomAvTypeEnum.getDescByCode(re.getAv_type())+")未接来电");
	    	}else{
	    		dto.setStatus(1);
	    		dto.setDescribe("("+VchatRoomAvTypeEnum.getDescByCode(re.getAv_type())+")无人接听");
	    	}
    	}else{
    		dto.setStatus(3);
    		String hms = "";
    		if(re.getSys_duration()>0){
    			if(re.getSys_duration()<re.getVchat_duration()*60){
    				int sysShowDuration = re.getSys_duration()/60;
    				if(re.getSys_duration()%60 >0){
    					sysShowDuration = re.getSys_duration()/60+1;
    				}
    				if(sysShowDuration == re.getVchat_duration()){
    					hms = Tools.formatMillisToDuration(re.getSys_duration()*1000);
    				}else{
    					hms = Tools.formatMillisToDuration(re.getVchat_duration()*60*1000);
    				}
    			}else{
    				hms = Tools.formatMillisToDuration(re.getVchat_duration()*60*1000);
    			}
    		}else{
    			hms = Tools.formatMillisToDuration(re.getVchat_duration()*60*1000);
    		}
    		String payType = "";
    		if(otherUser.getUserid()== re.getAnchorid()){
    			payType ="支出";
    		}else{
    			payType ="收入";
    		}
    	
    		String incomeInfo = "通话时长"+hms+ (re.getTotal_price()>0?"("+payType+re.getTotal_price()+"钻)":"");
    	
    		if(re.getFree_vchart_falg() == VchatRoomFreeVchartFalgEnum.free_vchart.getCode()){
				//incomeInfo = "通话时长"+hms+"(免费体验)";
    			String incomeLogInfo = "";
    			if( otherUser.getUserid()== re.getUserid() ){
    				 if(re.getFree_income_falg() == VchatRoomFreeIncomeFalgEnum.income_recorded.getCode()){
    	        			if(Tools.isNotNull(incomeLog)){
    	        				incomeLogInfo = incomeLog.getDuration_falg()==1 ? "("+payType+incomeLog.getPrice()+"钻)":"(1钻)";
    	        			}
    	        		}else if(re.getFree_income_falg() == VchatRoomFreeIncomeFalgEnum.not_recorded.getCode()){
    	        			incomeLogInfo =  "("+VchatRoomFreeIncomeFalgEnum.not_recorded.getDesc()+")";
    	        		}
    			} 
    			incomeInfo = "通话时长"+hms+incomeLogInfo;
			}else if(re.getFree_vchart_falg() == VchatRoomFreeVchartFalgEnum.free_vchart_charge.getCode()){
				//incomeInfo = "通话时长"+hms+(re.getTotal_price()>0?"("+payType+re.getTotal_price()+"钻,1分钟免费体验)":"");
				String incomeLogInfo = "";
    			if( otherUser.getUserid()== re.getUserid() ){
    				 if(re.getFree_income_falg() == VchatRoomFreeIncomeFalgEnum.income_recorded.getCode()){
    	        			if(Tools.isNotNull(incomeLog)){
    	        				//incomeLogInfo = "(体验:"+payType+(incomeLog.getDuration_falg()==1?incomeLog.getPrice():1)+"钻)";
    	        				incomeLogInfo = "("+payType+(re.getTotal_price()+incomeLog.getPrice())+"钻)";
    	        			}
    	        		}else if(re.getFree_income_falg() == VchatRoomFreeIncomeFalgEnum.not_recorded.getCode()){
    	        			//incomeLogInfo =  "(体验:"+VchatRoomFreeIncomeFalgEnum.not_recorded.getDesc()+")";
    	        			incomeLogInfo = "("+VchatRoomFreeIncomeFalgEnum.not_recorded.getDesc()+")";
    	        		}
    			} 
				//incomeInfo = "通话时长"+hms+(re.getTotal_price()>0?"("+payType+re.getTotal_price()+"钻)":"")+incomeLogInfo;
    			incomeInfo = "通话时长"+hms+incomeLogInfo;
			}
    		//dto.setDescribe("("+VchatRoomAvTypeEnum.getDescByCode(re.getAv_type())+")"+incomeInfo);
    		dto.setDescribe(VchatRoomAvTypeEnum.getDescByCode(re.getAv_type())+"聊天");
    	}
    	if(Tools.getFormatDate(re.getCreate_time(), "yyyyMMdd").equals(Tools.getFormatDate(current, "yyyyMMdd"))){
    		dto.setDate("今天");
    	}else if(!Tools.getFormatDate(re.getCreate_time(), "yyyy").equals(Tools.getFormatDate(current, "yyyy"))){
    		dto.setDate(Tools.getFormatDate(re.getCreate_time(), "yyyy-MM-dd"));
    	}else{
    		dto.setDate(Tools.getFormatDate(re.getCreate_time(),"MM月dd日"));
    	}
    	dto.setTime(Tools.getFormatDate(re.getCreate_time(),"HH:mm"));
    	return dto;
    }
    
    
	public UserBaseInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserBaseInfo userInfo) {
		this.userInfo = userInfo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


	public int getAvType() {
		return avType;
	}


	public void setAvType(int avType) {
		this.avType = avType;
	}

	
	
}
