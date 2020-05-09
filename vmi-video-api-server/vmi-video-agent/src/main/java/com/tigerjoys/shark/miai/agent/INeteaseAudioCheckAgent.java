package com.tigerjoys.shark.miai.agent;

import java.util.Map;

/**
 * 网易音频检测接口
 * @author shiming
 *
 */
public interface INeteaseAudioCheckAgent {

	/**
	 * 提价音频监黄
	 * @param url 音频路径 
	 * @param callbackUrl 检测结果回调路径
	 * @return taskId 任务的id status 状态
	 * @throws Exception
	 */
	public Map<String, String> check(String url, String callbackUrl) throws Exception;
}
