package org.shark.miai.common.enums;

/**
 * 发布普通约会跳转app内部页面(android和ios)
 * @author liuman
 *
 */
public enum PublishFreeAppointAppPageEnum {
	
	userInfoPage("userInfoPage" , "UserInfoFragment", "9","必须完善资料才能发布约会!"),
	creditScorePage("creditScorePage" , "CreditScoreFragment", "10","信用必须大于60分才能发布约会!"),
	applyInfoPage("applyInfoPage" , "UserInfoFragment", "9","必须完善基本资料才能申请约会!"),
	applyScorePage("applyScorePage" , "CreditScoreFragment", "10","信用必须大于60分才能申请约会!"),
	diamondPage("diamondPage", "WalletFragment", "13","您的钻石余额不足，请充值！"),
	applySuccessPage("applySuccessPage" , "ApplySuccessFragment", "14","申请成功"), //这个不用，只是申请成功提示
	;
	
	private String code;
	private String androidPage;
	private String iosPage;
	private String desc;

	private PublishFreeAppointAppPageEnum(String code,String androidPage , String iosPage ,String desc) {
		this.code = code;
		this.androidPage = androidPage;
		this.iosPage = iosPage;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		if(code == null) return null;
		for (PublishFreeAppointAppPageEnum refer : PublishFreeAppointAppPageEnum.values())
			if (code.equals(refer.getCode()))
				return refer.getDesc();
		return null;
	}
	
	public static String getAndroidPageByCode(String code) {
		if(code == null) return null;
		for (PublishFreeAppointAppPageEnum refer : PublishFreeAppointAppPageEnum.values())
			if (code.equals(refer.getCode()))
				return refer.getAndroidPage();
		return null;
	}
	
	public static String getIosPageByCode(String code) {
		if(code == null) return null;
		for (PublishFreeAppointAppPageEnum refer : PublishFreeAppointAppPageEnum.values())
			if (code.equals(refer.getCode()))
				return refer.getIosPage();
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAndroidPage() {
		return androidPage;
	}

	public void setAndroidPage(String androidPage) {
		this.androidPage = androidPage;
	}

	public String getIosPage() {
		return iosPage;
	}

	public void setIosPage(String iosPage) {
		this.iosPage = iosPage;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
