package com.tigerjoys.shark.miai.agent.neteasecheck;

/**
 * 图片监黄服务
 * @author shiming
 *
 */
public interface INeteaseTextCheck {

	public String check(long oriderid,String chatText) throws Exception;
	
}
