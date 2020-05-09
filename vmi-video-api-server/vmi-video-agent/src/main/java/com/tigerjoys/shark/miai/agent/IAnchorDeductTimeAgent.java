package com.tigerjoys.shark.miai.agent;

/**
 * 计算主播抵扣时间
 * @author shiming
 *
 */
public interface IAnchorDeductTimeAgent {

	//计算抵扣时间接口
	public int anchorDeductTime(long userid, int second);
}
