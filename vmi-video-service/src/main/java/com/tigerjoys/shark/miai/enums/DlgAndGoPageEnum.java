package com.tigerjoys.shark.miai.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.shark.miai.common.dto.KeyBean;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 跳转或弹窗 枚举
 * @author yangjunming
 *
 */
public enum DlgAndGoPageEnum {
	
		webSingle("webSingle" , "WebSingleSaveFragment", "13","H5页面" , "1#H5地址,2#H5标题"),
		chargePage("chargePage" , "WalletFragment", "13","我的钱包" ,"" ),
		vchatEndEvaluate("vchatEndEvaluate" , "VideoChatEvaluateDialog", "14","V聊结束评价" ,"" ),
		anchorToUserEvaluateDialog("anchorToUserEvaluateDialog" , "AnchorEvaluateDialog", "15","主播对用户评价" ,"" ),
		recommendAnchorDlg("recommendAnchorDlg" , "RecommendAnchorDlg", "16","主播推荐" ,"" ),
		payDiamondDlg("payDiamondDlg" , "PayDiamondDlg", "17","快速充值" ,"" ),
		payVipDlg("payVip" , "MyVipFragment", "18", "开通vip业务" ,"" ),
		iosChargeDiamond ("iosChargeDiamond" , "diamondFragment", "27","IOS原生充值钻" , ""), //IOS IAP转用
		;
	
	private static final Map<String , List<KeyBean>> paramMap = new HashMap<>();
	//此处添加映射关系
		static {
			for(DlgAndGoPageEnum v : DlgAndGoPageEnum.values()) {
				List<KeyBean> list = new ArrayList<>();
				
				String[] sarray = Tools.split(v.getParam());
				if(Tools.isNotNull(sarray)) {
					for(String s : sarray) {
						String[] k = s.split("#");
						list.add(new KeyBean("strValue"+k[0], k[1]));
					}
				}
				
				paramMap.put(v.getCode(), list);
			}
		}
		
		private String code;
		private String androidPage;
		private String iosPage;
		private String desc;
		private String param;//参数名称

		private DlgAndGoPageEnum(String code,String androidPage , String iosPage ,String desc ,String param ) {
			this.code = code;
			this.androidPage = androidPage;
			this.iosPage = iosPage;
			this.desc = desc;
			this.param = param;
		}

		public static String getDescByCode(String code) {
			if(code == null) return null;
			for (DlgAndGoPageEnum refer : DlgAndGoPageEnum.values())
				if (code.equals(refer.getCode()))
					return refer.getDesc();
			return null;
		}
		
		public static String getAndroidPageByCode(String code) {
			if(code == null) return null;
			for (DlgAndGoPageEnum refer : DlgAndGoPageEnum.values())
				if (code.equals(refer.getCode()))
					return refer.getAndroidPage();
			return null;
		}
		
		public static String getIosPageByCode(String code) {
			if(code == null) return null;
			for (DlgAndGoPageEnum refer : DlgAndGoPageEnum.values())
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

		public String getParam() {
			return param;
		}

		public void setParam(String param) {
			this.param = param;
		}


}
