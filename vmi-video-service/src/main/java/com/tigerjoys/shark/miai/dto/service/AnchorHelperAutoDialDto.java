package com.tigerjoys.shark.miai.dto.service;

import java.util.List;

/**
 * 自动拨打返回数据
 * @author yangjunming
 *
 */
public class AnchorHelperAutoDialDto {
	
	/**
	 * 拨打类型
	 */
	private int avType;
	
	/**
	 * 对方ID
	 */
	private long otherId;

	public int getAvType() {
		return avType;
	}

	public void setAvType(int avType) {
		this.avType = avType;
	}

	public long getOtherId() {
		return otherId;
	}

	public void setOtherId(long otherId) {
		this.otherId = otherId;
	}
	
	

	
}
