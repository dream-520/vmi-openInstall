package com.tigerjoys.shark.miai.agent;

import java.util.List;

/**
 * 根据主播id获取对应的个人主页的形象图片
 * @author shiming
 *
 */
public interface IAnchorImageAgent {

	public List<String> getAnchorImage(long userid) throws Exception ;
}
