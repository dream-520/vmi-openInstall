package com.tigerjoys.shark.miai.agent;

/**
 * 用户预约主播的相关业务接口
 * @author shiming
 *
 */
public interface IUserSubscribeAnchorAgent {

	/**
	 * 检测是否有预约关系
	 * @param userid
	 * @param anchorid
	 * @throws Exception
	 */
	public boolean checkSubscribe(long userid, long anchorid) throws Exception;
	
	/**
	 * 拨打检测时返回是否有预约的钻石值
	 * @param userid    用户id
	 * @param anchorid  主播id
	 * @param type      0 视频类型 1 音频类型
	 * @return
	 */
	public long dailCheckSubscribe(long userid, long anchorid, int type);
	
	/**
	 * 接通返回对应用户的预约金额
	 * @param userid
	 * @param anchorid
	 * @param type
	 */
	public void checkReturnSubscribeDiamond(long userid, long anchorid, int type);
}
