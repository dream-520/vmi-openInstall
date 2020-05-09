package com.tigerjoys.shark.miai.service;

import java.io.UnsupportedEncodingException;

/**
 * 图片监黄服务
 * @author shiming
 *
 */
public interface INeteasePictureCheck {

	public String check(String path) throws UnsupportedEncodingException;
	
	public void checkAndAction(long userId, long otherId, long serialNum, String path) throws Exception;
}
