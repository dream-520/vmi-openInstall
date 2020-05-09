/**
 * 
 */
package com.tigerjoys.shark.miai.agent.constant;

/**
 * ClassName: NeteaseApiConst <br/>
 * date: 2017年5月2日 下午3:24:20 <br/>
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public final class NeteaseApiConst {
	/** ==============================================网易云信常量================================================ */
	// 开发者平台分配的appKey
	public static final String APP_KEY = "afc7265de3857bbaa7404b4ea92b191e";
	// 开发者平台分配的appSecret
	public static  final String APP_SECRET = "c34bd403b29a";
	// 随机数（最大长度128个字符）
	public static final String NONCE = "q&jtm3#4j%kl*f8!64";

	/** ==============================================云信接口地址================================================ */
	// 向网易IM服务器注册用户
	public static final String USER_CREATE = "https://api.netease.im/nimserver/user/create.action";

	// 更新用户信息
	public static final String USER_UPDATE = "https://api.netease.im/nimserver/user/updateUinfo.action";

	// 聊天历史
	public static final String HISTORY_QUERY = "https://api.netease.im/nimserver/history/querySessionMsg.action";

	// 发送聊天消息
	public static final String MSG_SEND = "https://api.netease.im/nimserver/msg/sendMsg.action";
	
	// 黑名单
	public static final String BAD_RELATION = "https://api.netease.im/nimserver/user/setSpecialRelation.action";

	// 黑名单列表
	public static final String LIST_BAD_RELATION = "https://api.netease.im/nimserver/user/listBlackAndMuteList.action";

	// 系统自定义消息
	public static final String SEND_ATTACH_MSG = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
	
	// 查询在线状态
	public static final String QUERY_ONLING_STATUS = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
}
