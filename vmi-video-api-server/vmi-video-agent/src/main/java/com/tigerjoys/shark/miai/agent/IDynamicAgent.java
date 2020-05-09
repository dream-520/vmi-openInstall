package com.tigerjoys.shark.miai.agent;

import java.util.List;
import java.util.Map;

/**
 * 对上层提供的获取 动态和相册前几条的接口
 * 本接口提供给个人主页中使用
 * @author shiming
 */
public interface IDynamicAgent {
	
	/**
	 * 在他的主页同时获取前十张和前一百张图片的处理
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<String>> getTopPhoto(long userId) throws Exception;

	/**
	 * 获取动态中前五张图片
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<String> getTopDynamic(long userId) throws Exception;
	
}
