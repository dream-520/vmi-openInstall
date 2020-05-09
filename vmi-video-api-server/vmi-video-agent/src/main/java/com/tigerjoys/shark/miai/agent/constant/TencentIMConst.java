package com.tigerjoys.shark.miai.agent.constant;

import com.tigerjoys.nbs.common.ServiceConfig;
import com.tigerjoys.shark.miai.agent.utils.WebRTCSigApi;

/**
 * 腾讯云常量类
 * @author yangjunming
 *
 */
public class TencentIMConst {
	
	/**
	 * 读取classpath下properties文件的信息单例类
	 */
	private static ServiceConfig serviceConfig = ServiceConfig.getInstance();
	
	public static final String ACCOUNT_IMPORT_URL="https://console.tim.qq.com/v4/im_open_login_svc/account_import";
	
	
	/**
	 * 云通讯usersig失效时间
	 */
	public static final String TENCENT_USERSIG_FAILURE = serviceConfig.getString("tencent_usersig_failure" , "300");
	
	/**
	 * 云通讯sdkappid
	 */
	public static final String TENCENT_SDKAPPID = serviceConfig.getString("tencent_sdkappid" , "0");
	
	/**
	 * 云通讯privateKey
	 */
	public static final String TENCENT_PRIVATEKEY = serviceConfig.getString("tencent_privateKey" , "");
	
	/**
	 * 云通讯publicKey
	 */
	public static final String TENCENT_PUBLICKEY = serviceConfig.getString("tencent_publicKey" , "");
	
	/**
	 *  云通讯REST API管理员账号
	 */
	public static final String TENCENT_ADMIN = serviceConfig.getString("tencent_admin" , "");
	
	
	public static  WebRTCSigApi rtcSigApi = null;
	
	static{
		rtcSigApi = new WebRTCSigApi();
		rtcSigApi.setSdkAppid(Integer.valueOf(TENCENT_SDKAPPID));
		rtcSigApi.setPrivateKey(TENCENT_PRIVATEKEY);
		rtcSigApi.setPublicKey(TENCENT_PUBLICKEY);
	}
	
	public static final String getUserSig(String userid){
		return rtcSigApi.genUserSig(userid,Integer.valueOf(TENCENT_USERSIG_FAILURE));
	} 
	
	public static final String getUserSig(String userid,int expire){
		return rtcSigApi.genUserSig(userid,expire);
	}
	
	public static final String genPrivateMapKey(String userid,int roomid){
		return rtcSigApi.genPrivateMapKey(userid,roomid,Integer.valueOf(TENCENT_USERSIG_FAILURE));
	} 
	
	public static final String genPrivateMapKey(String userid,int roomid,int expire){
		return rtcSigApi.genPrivateMapKey(userid,roomid,expire);
	} 
	
}
