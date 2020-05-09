package com.tigerjoys.shark.miai;

import java.util.HashMap;

import org.junit.Test;

import com.tigerjoys.nbs.common.utils.Tools;

/**
 * 云盘测试
 * @author chengang
 *
 */
public class TestYunPan {

	public static void main(String[] args) {
//		ICloudStorage upaiyunClient = new UpYunCloudStorage("yoyo-test", "yoyotest", "!QAZXSW@");
		
		HashMap<Long, Long> hashMap = new HashMap<>();
		Long long1 = 1L;
		System.err.println(long1.hashCode());
		
		Long long2 = 1L;
		System.err.println(long2.hashCode());

		long min = (System.currentTimeMillis() - Tools.getDate("2018-11-15 17:52:41").getTime())/Tools.MINUTE_MILLIS;
		System.err.println("time:"+min);
		
//		boolean b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514287968412_5011.mp4", new File("G:/upload/user/video/2017/12/26/1514287968412_5011.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514287977213_5099.mp4", new File("/upload/user/video/2017/12/26/1514287977213_5099.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514287983479_6308.mp4", new File("G:/upload/user/video/2017/12/26/1514287983479_6308.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514287995274_7990.mp4", new File("G:/upload/user/video/2017/12/26/1514287995274_7990.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288005620_1438.mp4", new File("G:/upload/user/video/2017/12/26/1514288005620_1438.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288016400_2437.mp4", new File("G:/upload/user/video/2017/12/26/1514288016400_2437.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288021675_9804.mp4", new File("G:/upload/user/video/2017/12/26/1514288021675_9804.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288028353_4772.mp4", new File("G:/upload/user/video/2017/12/26/1514288028353_4772.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288035579_1295.mp4", new File("G:/upload/user/video/2017/12/26/1514288035579_1295.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288041538_2520.mp4", new File("G:/upload/user/video/2017/12/26/1514288041538_2520.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288048927_56.mp4", new File("G:/upload/user/video/2017/12/26/1514288048927_56.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288054476_3097.mp4", new File("G:/upload/user/video/2017/12/26/1514288054476_3097.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288065350_2430.mp4", new File("G:/upload/user/video/2017/12/26/1514288065350_2430.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288073787_5426.mp4", new File("G:/upload/user/video/2017/12/26/1514288073787_5426.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288069503_6423.mp4", new File("G:/upload/user/video/2017/12/26/1514288069503_6423.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288078760_4424.mp4", new File("G:/upload/user/video/2017/12/26/1514288078760_4424.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288094087_6865.mp4", new File("G:/upload/user/video/2017/12/26/1514288094087_6865.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288100003_852.mp4", new File("G:/upload/user/video/2017/12/26/1514288100003_852.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288112212_1493.mp4", new File("G:/upload/user/video/2017/12/26/1514288112212_1493.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288117985_8069.mp4", new File("G:/upload/user/video/2017/12/26/1514288117985_8069.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288124625_1121.mp4", new File("G:/upload/user/video/2017/12/26/1514288124625_1121.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288130818_2089.mp4", new File("G:/upload/user/video/2017/12/26/1514288130818_2089.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288139954_6828.mp4", new File("G:/upload/user/video/2017/12/26/1514288139954_6828.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288145172_6142.mp4", new File("G:/upload/user/video/2017/12/26/1514288145172_6142.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288151175_8074.mp4", new File("G:/upload/user/video/2017/12/26/1514288151175_8074.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288157311_8717.mp4", new File("G:/upload/user/video/2017/12/26/1514288157311_8717.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288161647_3769.mp4", new File("G:/upload/user/video/2017/12/26/1514288161647_3769.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288168107_8536.mp4", new File("G:/upload/user/video/2017/12/26/1514288168107_8536.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288174873_1384.mp4", new File("G:/upload/user/video/2017/12/26/1514288174873_1384.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288180853_9893.mp4", new File("G:/upload/user/video/2017/12/26/1514288180853_9893.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288192662_6632.mp4", new File("G:/upload/user/video/2017/12/26/1514288192662_6632.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288204680_6938.mp4", new File("G:/upload/user/video/2017/12/26/1514288204680_6938.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288211102_5752.mp4", new File("G:/upload/user/video/2017/12/26/1514288211102_5752.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288216865_2043.mp4", new File("G:/upload/user/video/2017/12/26/1514288216865_2043.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288226537_6567.mp4", new File("G:/upload/user/video/2017/12/26/1514288226537_6567.mp4"), true);
//		System.err.println(b);
//		b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288231540_4277.mp4", new File("G:/upload/user/video/2017/12/26/1514288231540_4277.mp4"), true);
//		boolean b = upaiyunClient.writeFile("/upload/user/video/2017/12/26/1514288104814_1805.mp4", new File("G:/upload/user/video/2017/12/26/1514288104814_1805.mp4"), true);
//		System.err.println(b);
		
		
		
		/*upaiyunClient.readDir("/test/video/").forEach(v -> {
			System.out.println(JsonHelper.toJson(v));
		});*/
		
		//boolean b = upaiyunClient.mediaSnapshot("/test/video/aa.mp4", "/test/video/aa.jpg", "00:00:01");
		//System.err.println(b);
		
//		FileInfo file = upaiyunClient.getFileInfo("/upload/dynamic/video/2017/10/09/1507528697964_7340.mp4");
//		if(Tools.isNotNull(file)) {
//			System.out.println(file);
//		}
//		
//		List<FileInfo> list = upaiyunClient.readDir("/upload/dynamic/video/2017/10/09/");
//		System.out.println(list);
//		
//		System.out.println(EFileContentTypeEnum.getContentTypeByExt(".mp3"));
	}

}
