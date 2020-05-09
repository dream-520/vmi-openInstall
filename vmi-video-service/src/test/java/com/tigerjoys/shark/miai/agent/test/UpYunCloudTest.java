package com.tigerjoys.shark.miai.agent.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shark.miai.common.cloud.storage.ICloudStorage;
import org.shark.miai.common.cloud.upyun.Result;
import org.shark.miai.common.cloud.upyun.UpYunCloudVideoHandler;
import org.shark.miai.common.dto.UpYunVideoTransQueryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.Const;
import com.tigerjoys.shark.miai.enums.ErrorCodeEnum;

/**
 * 用户代理测试
 * @author chengang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpYunCloudTest {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("upYunCloudStorage")
	private ICloudStorage upYunCloudStorage;
	
	
	@Autowired
	private UpYunCloudVideoHandler upYunCloudVideoHandler;
	
	@Test
	public void testUpYunCloud() throws Exception{
		boolean ss = upYunCloudStorage.readFile("/vmi2/css/public/common.css", "d:/vvv.css");
		System.out.println("result="+ss);
	}
	
	
	@Test
	public void testupYunCloudVideoHandler() throws Exception{
	
			
			String videoFilePath = Const.TEMP_FILE_UPLOAD_DIR + "/upload/download/app/ios/1111111.mov";
			String newPath = Const.TEMP_FILE_UPLOAD_DIR + "/upload/download/app/ios/kk_test.mp4";
			//File videoFile = new File(videoFilePath);
			
			File video = new File("D:\\hh\\1522134040520_3897.mov");
			InputStream in = new FileInputStream(video);
			try {
				//if(videoFile.exists()) {
					//首先获取对应的需要上传到又拍云的文件对象  同时检测对应的文件是否存在
					logger.info("上传又拍云的文件路径======================" + videoFilePath);
					//boolean isUpload = upYunCloudStorage.writeFile(uploadResult.getFilePath(), videoFile, true);
					boolean isUpload = upYunCloudStorage.writeFile(videoFilePath, in, true);
					logger.info("uploadResult=SUCESS");
					if(!isUpload){
						//处理上传文件到又拍云出现了错误
						logger.info("uploadResult="+ErrorCodeEnum.video_upload_error.getDesc());
					}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		/*
		List<String> list = upYunCloudVideoHandler.mediaTransProcess(videoFilePath, newPath, UpYunCloudVideoExtEnum.mp4,null);
		
		logger.info("result="+JsonHelper.toJson(list));
		*/
		/*
		boolean ss = upYunCloudStorage.readFile(videoFilePath, "d:/fvvv.mov");
		System.out.println("result="+ss);
		*/
	}
	@Test
	public void fileExtTest(){
		String source = "d://fffs/aaa.mov";
		String fileExt = null;
		String saveAs = null;
		if (source.indexOf(".") > -1) {
			fileExt = source.substring(source.lastIndexOf(".") + 1).toLowerCase();
		}
		if(Tools.isNotNull(fileExt) && !"mp4".equals(fileExt)){
			saveAs = source.substring(0, source.lastIndexOf("."))+".mp4";
		}
		
		System.out.println("indexOf:"+source.indexOf("."));
		System.out.println("saveAs:"+saveAs);
	}
	
	
	@Test
	public void testQueryProcess() {
		List<String> taskIdList = new ArrayList<>();
		taskIdList.add("bff35754efc0291d68666a503baaf2e4");
		taskIdList.add("315e8eee79f96cbaf3933232c0c41b72");
		Result result = upYunCloudVideoHandler.testMediaStatus(taskIdList);
		System.out.println("result:"+result.getMsg());
		if (result.getCode() == 200) {
			JSONObject json = JsonHelper.toJsonObject(result.getMsg());
			JSONObject tasksJson = json.getJSONObject("tasks");
			if (Tools.isNotNull(tasksJson)) {
				Map<String, Integer> map = tasksJson.toJavaObject(Map.class);
				System.out.println("map:"+JsonHelper.toJson(map));
				for (String re : taskIdList) {
					System.out.println(map.get(re));
				}
			}

		}
	}
	
	
	@Test
	public void testQueryResult() {
		List<String> taskIdList = new ArrayList<>();
		taskIdList.add("bff35754efc0291d68666a503baaf2e4");
		taskIdList.add("315e8eee79f96cbaf3933232c0c41b72");
		Result result = upYunCloudVideoHandler.testMediaResult(taskIdList);
		System.out.println("result:"+result.getMsg());
		if (result.getCode() == 200) {
			JSONObject json = JsonHelper.toJsonObject(result.getMsg());
			JSONObject tasksJson = json.getJSONObject("tasks");
				Map<String, Object> map = tasksJson.toJavaObject(Map.class);
				System.out.println("map:"+JsonHelper.toJson(map));
				for (String re : taskIdList) {
					Object dto =map.get(re);
					String str =JsonHelper.toJson(dto);
					JSONObject jsonStr = JsonHelper.toJsonObject(str);
					UpYunVideoTransQueryDto query = jsonStr.toJavaObject(UpYunVideoTransQueryDto.class);
					System.out.println(query.getSignature());
				}

		}
	}

}
