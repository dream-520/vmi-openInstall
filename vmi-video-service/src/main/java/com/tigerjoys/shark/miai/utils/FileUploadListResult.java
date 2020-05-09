package com.tigerjoys.shark.miai.utils;

import java.util.List;

/**
 * 上传图片或者视频的返回码
 * @author shiming
 *
 */
public class FileUploadListResult {
	
	/**
	 * 返回码
	 */
	private int code;
	
	/**
	 * 返回信息
	 */
	private String msg;
	
	/**
	 * 图片列表或者视频的保存地址
	 */
	private List<String> paths;
	
	private FileUploadListResult(){
		
	}
	
	public static FileUploadListResult getFileUploadDto(int code , String msg) {
		return getFileUploadDto(code , msg , null);
	}
	
	public static FileUploadListResult getFileUploadDto(int code , String msg , List<String> paths) {
		FileUploadListResult dto = new FileUploadListResult();
		dto.code = code;
		dto.msg = msg;
		dto.paths = paths;
		return dto;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public List<String> getPaths() {
		return paths;
	}
	
}
