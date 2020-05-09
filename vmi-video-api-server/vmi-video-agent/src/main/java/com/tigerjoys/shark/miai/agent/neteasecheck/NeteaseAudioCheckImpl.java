package com.tigerjoys.shark.miai.agent.neteasecheck;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.shark.miai.common.enums.AppNameEnum;
import org.shark.miai.common.enums.PlatformEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.agent.INeteaseAgent;
import com.tigerjoys.shark.miai.agent.INewPushAgent;
import com.tigerjoys.shark.miai.agent.ITextAutioMsgAgent;
import com.tigerjoys.shark.miai.agent.IUserAgent;
import com.tigerjoys.shark.miai.agent.dto.PushParamDto;
import com.tigerjoys.shark.miai.agent.dto.TextAudioLabelDto;
import com.tigerjoys.shark.miai.agent.dto.TextAutioMsgDto;
import com.tigerjoys.shark.miai.agent.dto.UserBO;
import com.tigerjoys.shark.miai.agent.dto.VChatVideoTCPDto;
import com.tigerjoys.shark.miai.agent.enums.NewPushAppTagEnum;
import com.tigerjoys.shark.miai.agent.enums.PushContentTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.PushTypeEnum;
import com.tigerjoys.shark.miai.agent.enums.TextAudioMsgEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoStatusEnum;
import com.tigerjoys.shark.miai.agent.enums.VChatVideoTCPTypeEnum;
import com.tigerjoys.shark.miai.agent.utils.PushHelper;
import com.tigerjoys.shark.miai.inter.contract.IUserAudioChatInfoLogContract;
import com.tigerjoys.shark.miai.inter.entity.UserAudioChatInfoLogEntity;

/**
 * 调用易盾反垃圾云服务查询点播语音结果接口API示例
 *
 * @author yangjunming
 */
@Service
public class NeteaseAudioCheckImpl implements INeteaseAudioCheck{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 读取classpath下properties文件的信息单例类
	 */
	private static ServiceConfig serviceConfig = ServiceConfig.getInstance();
	 /**
     * 产品密钥ID，产品标识
     */
    private final static String SECRETID = serviceConfig.getString("netease_audio_check_SECRETID" , "");
    /**
     * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     */
    private final static String SECRETKEY = serviceConfig.getString("netease_audio_check_SECRETKEY" , "");
    /**
     * 业务ID，易盾根据产品业务特点分配
     */
    private final static String BUSINESSID = serviceConfig.getString("netease_audio_check_BUSINESSID" , "");
    /**
     * 易盾反垃圾云服务查询点播语音结果接口地址
     */
    private final static String API_URL = "https://as.dun.163yun.com/v1/audio/query/task";
    /**
     * 实例化HttpClient，发送http请求使用，可根据需要自行调参
     */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 10000, 2000, 2000);

    
	@Autowired
	private ITextAutioMsgAgent textAutioMsgAgent;
	
	@Autowired
	private IUserAudioChatInfoLogContract userAudioChatInfoLogContract;
	
	@Autowired
	private IUserAgent userAgent;

	@Autowired
	private INeteaseAgent neteaseAgent;
	
	@Autowired
	private INewPushAgent newPushAgent;
	
	public boolean verifySignature(HttpServletRequest request) throws Exception {
		 return SignatureUtils.verifySignature(request, SECRETID, SECRETKEY, BUSINESSID);
	} 
	
    
    /**
     *	根据taskId 查询语音检测结果
     * @param args
     * @throws Exception
     */
    public List<TextAutioMsgDto> audioQueryByTaskIds(List<String> taskIdList) throws Exception {
    	List<TextAutioMsgDto> autioMsgList = new ArrayList<TextAutioMsgDto>();
    	if(Tools.isNull(taskIdList)) {
    		return autioMsgList;
    	}
        Map<String, String> params = new HashMap<String, String>();
        // 1.设置公共参数
        params.put("secretId", SECRETID);
        params.put("businessId", BUSINESSID);
        params.put("version", "v1");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(new Random().nextInt()));

        // 2.设置私有参数
        Set<String> taskIds = new HashSet<String>();
        
        taskIdList.forEach(v->{
        	 taskIds.add(v);
        });
       // taskIds.add("202b1d65f5854cecadcb24382b681c1a");
       // taskIds.add("0f0345933b05489c9b60635b0c8cc721");
        params.put("taskIds", new Gson().toJson(taskIds));

        // 3.生成签名信息
        String signature = SignatureUtils.genSignature(SECRETKEY, params);
        params.put("signature", signature);

        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        String response = HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);

        // 5.解析接口返回值
        JsonObject resultObject = new JsonParser().parse(response).getAsJsonObject();
        int code = resultObject.get("code").getAsInt();
        String msg = resultObject.get("msg").getAsString();
        if (code == 200) {
            JsonArray resultArray = resultObject.getAsJsonArray("result");
            if (resultArray.size() == 0) {
                System.out.println("暂无回调数据");
            } else {
                for (JsonElement jsonElement : resultArray) {
                    JsonObject jObject = jsonElement.getAsJsonObject();
                    int action = jObject.get("action").getAsInt();
                    String taskId = jObject.get("taskId").getAsString();
                    JsonArray labelArray = jObject.getAsJsonArray("labels");
                    if (action == 0) {
                        System.out.println(String.format("callback=%s，结果：通过", taskId));
                    } else if (action == 2) {
                        System.out
                                .println(String.format("callback=%s，结果：不通过，分类信息如下：%s", taskId, labelArray.toString()));
                    }
                }
            }
        } else {
            System.out.println(String.format("ERROR: code=%s, msg=%s", code, msg));
        }

        return autioMsgList;
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    public void sendAudioMsgHandle(List<TextAutioMsgDto> audioMsgList) throws Exception{
		if (Tools.isNull(audioMsgList)) {
			return ;
		}
		/*
		List<String> taskIdList = audioMsgList.stream().map(TextAutioMsgDto::getTaskId).collect(Collectors.toList());
		PageModel pageModel = PageModel.getPageModel();
		pageModel.addQuery(Restrictions.in("check_taskid", taskIdList));
		List<UserAudioChatInfoLogEntity> userAudioList =  userAudioChatInfoLogContract.load(pageModel);
		for (UserAudioChatInfoLogEntity dto : userAudioList) {
			//userAudioChatInfoLogContract
		}
		*/
		for (TextAutioMsgDto dto : audioMsgList) {
			UserAudioChatInfoLogEntity audioLog = userAudioChatInfoLogContract.lockById(dto.getOrderId());
			if (Tools.isNotNull(audioLog)) {
				if (audioLog.getCheck_status() != TextAudioMsgEnum.unknown.getCode()) {
					continue;
				}
				if (dto.getAction() == 0) {
					audioLog.setCheck_status(TextAudioMsgEnum.success.getCode());
					textAutioMsgAgent.updateAutioMsg(audioLog);
					logger.info("sendAudioMsgHandle:success;orderId="+dto.getOrderId()+";"+JsonHelper.toJson(dto));
					neteaseAgent.sendAudioMsg(audioLog.getUser_id(), audioLog.getOther_id(), audioLog.getId(), audioLog.getAudio_url(), audioLog.getAudio_time(), true);
				}else {
					audioLog.setCheck_status(TextAudioMsgEnum.fail.getCode());
					if (Tools.isNotNull(dto.getLabels())) {
						TextAudioLabelDto labelDto = dto.getLabels().get(0);
						audioLog.setCheck_type(NeteaseTextCheckLabelEnum.getDescByCode(labelDto.getLabel()));
						audioLog.setCheck_type_code(labelDto.getLabel());
						if (Tools.isNotNull(labelDto.getHintInfo())) {
							audioLog.setCheck_text(JsonHelper.toJson(labelDto.getHintInfo()));
						}
						
					}
					textAutioMsgAgent.updateAutioMsg(audioLog);
					logger.info("sendAudioMsgHandle:fail;orderId="+dto.getOrderId()+";"+JsonHelper.toJson(dto));
					//neteaseAgent.pushOneMessage(audioLog.getUser_id(), audioLog.getUser_id(), "发送失败：包含"+audioLog.getCheck_text()+"", false);
					String showMessage = "包含敏感词"+(Tools.isNotNull(audioLog.getCheck_text())?(":包含"+audioLog.getCheck_text()):"");
					audioiPushFailMessage(audioLog.getUser_id(),"语音发送失败",showMessage);
					
					UserBO userBO = userAgent.findById(audioLog.getUser_id());
					if (Tools.isNotNull(userBO) && AppNameEnum.getByOsType(userBO.getPackageName()) == PlatformEnum.ios) {
						logger.info("sendAudioMsgHandle:IOS_TCP;"+showMessage);
						VChatVideoTCPDto tcpDto = new VChatVideoTCPDto();
						tcpDto.setType(VChatVideoTCPTypeEnum.video.getCode());
						tcpDto.setSubType(VChatVideoStatusEnum.audio_msg_fail.getCode());
						HashMap<String, Object> outMap = new HashMap<>();
						outMap.put("showInfo", showMessage);
						tcpDto.setData(JsonHelper.toJson(outMap));
						neteaseAgent.pushOneAttachMessage(audioLog.getUser_id(),audioLog.getUser_id(), JsonHelper.toJson(tcpDto)); // IOS 发送自定义
					}
					
				}
			}
		}
		
	}
    
    
    
    public void audioiPushFailMessage(long userId,String title,String content) throws Exception {
		UserBO user = userAgent.findById(userId);
		PushParamDto paramC = PushHelper.getPushParamDto(user, PushTypeEnum.type_goto_app, PushContentTypeEnum.necessary, NewPushAppTagEnum.wakeup_user);
		paramC.setTitle(title);
		paramC.setContent(content);
		paramC.setUserId(userId);
		newPushAgent.pushMessageToSingleUser(paramC);
	}

}
