package com.tigerjoys.shark.miai.agent;

public interface INeteasePictureCheckAgent {

	public int check(String url, double pornographicRate, double lowRate) throws Exception;
	
	/**
	 * 全类型检测图片
	 * @param url
	 * @return 0 检测失败      1 检测成功不违规       >1 检测成功 同时违规了
	 * @throws Exception
	 */
	public int checkAll(String url) throws Exception;
	
}
