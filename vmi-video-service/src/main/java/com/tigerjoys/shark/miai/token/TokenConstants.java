package com.tigerjoys.shark.miai.token;

/**
 * token常量
 * @author chengang
 *
 */
public final class TokenConstants {
	
	//gateway token前缀,后面跟clientID
	public static final String VMI_GATEWAY_TOKEN_PREFIX		= "vmi_gw_tk_";
	//gateway token 网关过期时间,秒
	public static final int VMI_GATEWAY_TOKEN_SECONDS			= 300;
	//publish rtmp token前缀，后面跟主播ID
	public static final String VMI_RTMP_TOKEN_PREFIX			= "vmi_rtmp_tk_";
	//publish rtmp token 过期时间,秒 TODO 此处的时间需要额外的刷新
	public static final int VMI_RTMP_TOKEN_SECONDS				= 7200;
	//token密钥
	public static final String VMI_TOKEN_KEYSECRET				= "82d123e0c9d907143f17606044f942ae";
	
	private TokenConstants(){}

}
