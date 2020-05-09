package com.tigerjoys.shark.miai.service.test;

import org.junit.Test;

import com.tigerjoys.nbs.common.http.HttpUtils;
import com.tigerjoys.shark.miai.agent.constant.Const;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;
import com.tigerjoys.shark.miai.utils.Helper;

public class DownLoadFileTest extends BaseTestConfig {

	@Test
	public void testDownLoadFile() throws Exception {
		String fileExt = "mp4";
		String path = getFileName("anchor/video", fileExt);
		String abs = Const.FILE_UPLOAD_DIR + path;
		logger.error("下载视频需要存储的位置======================" + abs);
		//启动下载视频
		boolean suc =  HttpUtils.downloadFile("http://jdvodr6xgogfm.vod.126.net/jdvodr6xgogfm/0-51643731806165-1576041625533-0-mix.mp4", abs);
		System.err.println("下载结果:"+suc);
	}
	
	public static String getFileName(String directory, String fileExt) {
		String filePath = Helper.getUploadFilePath(directory)+Helper.getUploadFileName(fileExt);
		return filePath;
	}
	
}
