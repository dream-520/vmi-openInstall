/**
 *
 */
package com.tigerjoys.shark.miai.agent.dto;

import java.io.Serializable;

import com.tigerjoys.nbs.common.utils.JsonHelper;

/**
 *	蒙层配值
 * @author yangjunming
 * @version
 * @since JDK 1.8.0
 */
public class VchatRoomObscurationConfigDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1921933089263806157L;

	/**
	 * 用户文本
	 */
	private String userText ="";
	
	
	/**
	 * 用户文本
	 */
	private String anchorText ="";
	
	/**
	 * 蒙层类型
	 */
	private Integer type = 0 ;
	
	/**
	 * 是否允许用户主动拨打  0 允许拨打  1 不允许拨打
	 */
	private Integer dailType = 0 ;
	
	/**
	 * 延迟大V助手时间 (秒)
	 */
	private Integer dialHelperTime = 0 ;
	
	/**
	 * 体验是否有收益  0 没有  1 有收益
	 */
	private Integer freeIncome = 0 ;
	
	/**
	 * 蒙层视频地址1
	 */
	private String videoPath1 ;
	
	/**
	 * 蒙层视频地址2
	 */
	private String videoPath2 ;
	
	/**
	 * 蒙层视频地址3
	 */
	private String videoPath3 ;

	public String getUserText() {
		return userText;
	}



	public void setUserText(String userText) {
		this.userText = userText;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public String getAnchorText() {
		return anchorText;
	}



	public void setAnchorText(String anchorText) {
		this.anchorText = anchorText;
	}


	public Integer getDailType() {
		return dailType;
	}

	public void setDailType(Integer dailType) {
		this.dailType = dailType;
	}



	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}



	public String getVideoPath1() {
		return videoPath1;
	}



	public void setVideoPath1(String videoPath1) {
		this.videoPath1 = videoPath1;
	}



	public String getVideoPath2() {
		return videoPath2;
	}



	public void setVideoPath2(String videoPath2) {
		this.videoPath2 = videoPath2;
	}



	public String getVideoPath3() {
		return videoPath3;
	}



	public void setVideoPath3(String videoPath3) {
		this.videoPath3 = videoPath3;
	}



	public Integer getDialHelperTime() {
		return dialHelperTime;
	}



	public void setDialHelperTime(Integer dialHelperTime) {
		this.dialHelperTime = dialHelperTime;
	}



	public Integer getFreeIncome() {
		return freeIncome;
	}



	public void setFreeIncome(Integer freeIncome) {
		this.freeIncome = freeIncome;
	}
	
	
	
}
