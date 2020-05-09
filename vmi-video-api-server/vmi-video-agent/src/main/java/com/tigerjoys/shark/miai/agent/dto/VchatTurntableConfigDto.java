/**
 *
 */
package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.nbs.common.utils.JsonHelper;

/**
 * ClassName: FirstChargeConfigDto <br/>
 * 互动转盘配值
 * @author yangjunming
 * @version
 * @since JDK 1.8.0
 */
public class VchatTurntableConfigDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1921933089263806157L;

	/**
	 * 用户端图标
	 */
	private String userPic ;
	
	/**
	 * 主播端图标
	 */
	private String anchorPic ;
	
	
	/**
	 * 规则
	 */
	private String ruleText ;

	

	public String getUserPic() {
		return userPic;
	}



	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}



	public String getAnchorPic() {
		return anchorPic;
	}



	public void setAnchorPic(String anchorPic) {
		this.anchorPic = anchorPic;
	}



	public String getRuleText() {
		return ruleText;
	}



	public void setRuleText(String ruleText) {
		this.ruleText = ruleText;
	}



	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
