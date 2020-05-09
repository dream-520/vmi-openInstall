package org.shark.miai.common.cloud.upyun;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;

public class UpYunCloudVideoHandler  {
	private static final Logger logger = LoggerFactory.getLogger(UpYunCloudVideoHandler.class);
    protected final MediaHandler handler;
    
    protected final String bucketName;
    
	public UpYunCloudVideoHandler(String bucketName,String userName,String password) {
		handler = new MediaHandler(bucketName,userName,password);
		this.bucketName = bucketName;
	}
	
	  /**
     * 发起异步影视频转码处理请求
     * @param source  源文件
     * @param saveAs  目标文件
     * @param ext  转码格式
     * @return TaskId 数组
     */
    public List<String> mediaTransProcess(String source,String saveAs,UpYunCloudVideoExtEnum ext,String notifyUrl) throws Exception {
    	if(Tools.isNull(source) || Tools.isNull(saveAs)){
    		return null;
    	}
    	//初始化参数组 Map
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        //添加必选参数 bucket_name, notify_url, source, tasks, accept
        //空间名
        paramsMap.put(MediaHandler.Params.BUCKET_NAME, bucketName);
        //回调地址
        paramsMap.put(MediaHandler.Params.NOTIFY_URL,  notifyUrl);
        //必须指定为 json
        paramsMap.put(MediaHandler.Params.ACCEPT, "json");
        //需要处理视频路径
        paramsMap.put(MediaHandler.Params.SOURCE, source);


        //已json格式生成任务信息
        JSONArray array = new JSONArray();
        JSONObject json = new JSONObject();

        //添加处理参数
        //不同的处理任务对应不同的 type
        json.put(MediaHandler.Params.TYPE, "video");
        //影视频处理参数 包括视频转码,HLS 切片,视频水印,视频截图,视频拼接,音频剪辑,元数据获取 请见API文档
//      json.put(MediaHandler.Params.AVOPTS, "/s/240p(4:3)/as/1/r/30");
        json.put(MediaHandler.Params.AVOPTS, "/f/"+ext.getValue());
        //是否返回 JSON 格式元数据，默认 false。支持 type 值为 video 功能
        json.put(MediaHandler.Params.RETURN_INFO, "true");
        //输出文件保存路径（同一个空间下），如果没有指定，系统自动生成在同空间同目录下
        json.put(MediaHandler.Params.SAVE_AS, saveAs);
        array.put(json);
        
        //array 可以加多个

        //添加任务信息
        paramsMap.put(MediaHandler.Params.TASKS, array);

        try {
            Result result = handler.process(paramsMap);
            logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap)+",result:"+JsonHelper.toJson(result));
            if (result.isSucceed()) {
                String[] ids = handler.getTaskId(result.getMsg());
                System.out.println(Arrays.toString(ids));
                return Arrays.asList(ids);

            }
        } catch (IOException e) {
        	logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),e);
        } catch (UpException e) {
        	logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),e);
        }

        return null;
    }

    /**
     * 查询处理进度
     *
     * @param ids 需要查询的任务ID
     */
    public  Result testMediaStatus(List<String> ids) {
        //初始化参数组 Map
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put(MediaHandler.Params.BUCKET_NAME, bucketName);
        StringBuilder sb = new StringBuilder();
        ids.forEach(v->{
        	 sb.append(v + ",");
        });

        String task_ids = sb.toString().substring(0, sb.length() - 1);
        paramsMap.put(MediaHandler.Params.TASK_IDS, task_ids);
        Result result = null;
        try {
            result = handler.getStatus(paramsMap);
            logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),JsonHelper.toJson(result));

        } catch (IOException e) {
        	logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),e);
        } catch (UpException e) {
        	logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),e);
        }
        return result;
    }

    /**
     * 查询处理结果
     *
     * @param ids 需要查询的任务ID
     */
    public  Result testMediaResult(List<String> ids) {
        //初始化参数组 Map
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put(MediaHandler.Params.BUCKET_NAME, bucketName);

        StringBuilder sb = new StringBuilder();
        ids.forEach(v->{
       	 sb.append(v + ",");
       });

        String task_ids = sb.toString().substring(0, sb.length() - 1);
        paramsMap.put(MediaHandler.Params.TASK_IDS, task_ids);
        Result result = null;
        try {
            result = handler.getResult(paramsMap);
            logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),JsonHelper.toJson(result));

        } catch (IOException e) {
        	logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),e);
        } catch (UpException e) {
        	logger.info("mediaTrans:"+JsonHelper.toJson(paramsMap),e);
        }
        return result;

    }

	
	
	
	
}
