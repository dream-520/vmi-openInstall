package com.tigerjoys.shark.miai.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * H5静态页地址
 * @author yangjunming
 *
 */
public enum StaticWebUrlEnum {
	INCOME_DETAIL("/api/income/cash/list","收益明细"),
    WITHDRAW_RECORD("/api/withdrawal/cash/list","提现记录"),
    REWARDS_SCVC( "/api/bonus/scvc/help", "SCVC说明"),
    //USER_AGREEMENT("/web/agreement","用户协议"),
    USER_AGREEMENT("/api/login/protocol","用户协议"),
    MY_SHARE("/web/userShareApp" ,"邀请有礼" ), //我的分享
    MY_TASK("/api/task/home/list",""), //我的任务
    ANCHOR_PROMISE("/web/anchor/promise",""), //V聊主播绿色承诺书
    ANCHOR_APPLY("/web/anchor/apply",""), //V聊主播认证申请结果返回
    IOS_SITE("/web/appointsite/home",""), //IOS预订场地
    SHARE_H5("/web/userShareApp","邀请有礼"), //他享H5页面
    RECEVING_GOODS("/api/commodity/getCommodityList",""), //领取商品
    INCOME_PAGE("/api/income/h5Page",""), //我的收益H5
    //SHARE_DIVIDED("/web/userShareApp" ,"" ), //分享分成
    WALLET_PAGE("/api/income/zuanList" ,"我的钱包" ), //我的钱包
    USER_PRIVACY_AGREEMENT("/web/instruction/privacy" ,"用户隐私协议" ), //用户隐私协议
    INTEGRAL_EXCHANGE("/api/point/exchange" ,"积分兑换" ), //用户隐私协议
    USER_VIP_SERVICE_H5("/recharge/category/chargeVipList" ,"VIP特色服务" ), //VIP特色服务
 	;
	
	private String path;
	private String desc;
	
	private static final Map<String , String> STATUS_DESC = new HashMap<String , String>();
	
	static {
		for(StaticWebUrlEnum refer : StaticWebUrlEnum.values()) {
			STATUS_DESC.put(refer.getPath(), refer.getDesc());
		}
	}
	
	private StaticWebUrlEnum(String path, String desc) {
		this.path = path;
		this.desc = desc;
	}
	
	public static String getByDesc(String path) {
		String desc = STATUS_DESC.get(path);
		if(desc == null) {
			return null;
		}
		return desc;
	}
	


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
