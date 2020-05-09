package com.tigerjoys.shark.miai.agent.constant;

/**
 * redis缓存常量
 * @author chengang
 *
 */
public final class AgentRedisCacheConst {
	
	/**
	 * 缓存的默认秒数，1小时
	 */
	public static final int DEFAULT_CACHE_EXPIRE = 3600;
	
	/**
	 * 缓存的默认秒数，1天
	 */
	public static final int DEFAULT_CACHE_EXPIRE_DAY = 3600*24;
	
	/**
	 * 短信验证码记录的过期时间,10分钟
	 */
	public static final int REDIS_MOBILE_SEND_CODE_EXPIRE = 600;
	
	/**
	 * 公共缓存Redis客户端名称
	 */
	public static final String REDIS_PUBLIC_CACHE = "publicRedisCache";
	
	/**
	 * 用户缓存Redis客户端名称
	 */
	public static final String REDIS_USER_CACHE = "publicRedisCache";
	
	/**
	 * 用户扩展信息缓存Redis客户端名称
	 */
	public static final String REDIS_USER_EXTENSION_CACHE = "publicRedisCache";
	
	/**
	 * 道具缓存Redis客户端名称
	 */
	public static final String USER_CHARM_CACHE = "publicRedisCache";
	
	/**
	 * 道具缓存Redis客户端名称
	 */
	public static final String USER_MYPAGE_ACT_CACHE = "publicRedisCache";
	
	/**
	 * 发送短信Redis客户端名称
	 */
	public static final String VALID_CODE_CACHE = "publicRedisCache";
	
	/**
	 * 系统配置缓存Redis客户端名称
	 */
	public static final String SYS_CONFIG_CACHE = "publicRedisCache";
	
	/**
	 * 用户游戏信息Redis客户端名称
	 */
	public static final String REDIS_USER_INFO_CACHE = "publicRedisCache";
	
	/**
	 * 用户在线列表/登录Token Redis客户端名称
	 */
	public static final String REDIS_USER_ONLINE_LIST_CACHE = "publicRedisCache";
	
	/**
	 * 用户好友列表Redis客户端名称
	 */
	public static final String REDIS_USER_FRIEND_LIST_CACHE = "publicRedisCache";
	
	/**
	 * 全局广播缓存Redis客户端名称
	 */
	public static final String GLOBAL_BROADCAST_CACHE = "publicRedisCache";
	
	
	/**
	 * 消息推送Redis客户端名称
	 */
	public static final String REDIS_PUSH_MESSAGE_CACHE = "publicRedisCache";
	
	/////////////////////////////////////用户信息相关/////////////////////////////////////////////////
	
	/**
	 * 用户实体缓存key前缀
	 */
	public static final String USER_CACHE_KEY = "v19_u_";
	
	/**
	 * 全局广播实体缓存key前缀
	 */
	public static final String GLOBAL_BROADCAST_CACHE_KEY = "global_broadcast_";
	
	/**
	 * 用户扩展实体缓存key前缀
	 */
	public static final String USER_EXTENSION_CACHE_KEY = "v1_u_ext_";
	
	/**
	 * 用户定位缓存前缀
	 */
	public static final String USER_GEO_CACHE_KEY = "v0_u_geo_";
	
	/////////////////////////////////////达人信息相关/////////////////////////////////////////////////
	
	/**
	 * 达人实体缓存key前缀
	 */
	public static final String TALENT_CACHE_KEY = "v0_u_p_";
	
	/////////////////////////////////////用户认证视频信息相关/////////////////////////////////////////////////
	
	/**
	 * 用户视频认证缓存key前缀
	 */
	public static final String USER_VIDEO_AUTH_CACHE_KEY = "v0_u_v_";
	
	/////////////////////////////////////用户在线列表相关/////////////////////////////////////////////////
	
	/**
	 * 用户在线列表key值
	 */
	public static final String USER_ONLINE_CACHE_KEY = "user_online";
	
	/**
	 * 用户处于前端的列表key值
	 */
	public static final String USER_FOREGROUND_CACHE_KEY = "user_foreground";
	
	/**
	 * 主播处于前端的列表key值
	 */
	public static final String ANCHOR_FOREGROUND_CACHE_KEY = "anchor_foreground";
	
	/**
	 * 主播处于前端的列表key值
	 */
	public static final String ANCHOR_BACKGROUND_CACHE_KEY = "anchor_background";
	
	/**
	 * 主播在线列表key值
	 */
	public static final String ANCHOR_ONLINE_CACHE_KEY = "anchor_online";
	
	/**
	 * 主播行为在线列表key值
	 */
	public static final String ANCHOR_ACTION_ONLINE_CACHE_KEY = "anchor_action_online";
	
	/**
	 * 用户在线登录详情信息
	 */
	public static final String USER_ONLINE_DETAIL_PREFIX = "user_online_prefix_";
	
	/**
	 * 用户心跳列表,zset , score也是userid这样可以防止列表抖动，value为用户ID
	 * 规则是每隔1小时，将心跳用户的信息存储到数据库中
	 */
    public static final String USER_ONLINE_HEART_LIST_KEY = "user_online_heart_list";
    
    /**
     * 用户心跳列表的详细信息
     */
    public static final String USER_ONLINE_HEART_PREFIX = "user_online_heart_";
    
	/**
	 * 用户在线缓存过期时间，5分钟,(SEC)
	 */
    public static final int USER_ONLINE_EXPIRE_TIME_SEC = 5*60;
    
    /**
     * 用户心跳列表的详细信息过期时间，2小时，(SEC)
     */
    public static final int USER_ONLINE_HEART_EXPIRE_TIME_SEC = 2*3600;
    
	/**
	 * 主播行为在线缓存过期时间，30分钟,(SEC)
	 */
    public static final int ANCHOR_ACTION_ONLINE_EXPIRE_TIME_SEC = 30*60;
    
	
	/**
	 * 用户ID
	 */
    public static final String USER_ONLINE_USERID_KEY = "userId";
    
    /**
     * 客户端ID
     */
    public static final String USER_ONLINE_CLIENT_KEY = "clientId";
    
    /**
     * 用户登录时间，这个其实是最近访问系统的时间
     */
    public static final String USER_ONLINE_LOGINTIME_KEY = "loginTime";
    
    /**
     * 用户最后刷新时间
     */
    public static final String USER_ONLIN_LASTTIME_KEY = "lastTime";
    
    /**
     * 用户每天在线时长累计，给予scvc奖励
     */
    public static final String USER_ONLIN_SCVC_AWARD_KEY = "user_online_scvc_award";
    
	/////////////////////////////////////好友列表和排行相关/////////////////////////////////////////////////
	
	/**
	 * 用户好友数据hash key 前缀
	 */
	public static final String USER_FRIEND_HASH_CACHE_KEY = "user_friend_hash_";
	
	/**
	 * 用户的关注数量缓存key
	 */
	public static final String USER_FRIEND_LEN_CACHE_KEY = "user_friend_len_";
	
	/**
	 * 用户的好友数量缓存key
	 */
	public static final String USER_FRIEND_POWDER_LEN_CACHE_KEY = "user_friend_powder_len_";
	
	/**
	 * 用户的粉丝数量缓存key
	 */
	public static final String FRIEND_USER_LEN_CACHE_KEY = "friend_user_len_";
	
	////////////////////////////////////用户魅力值///////////////////////////////////////////////////////
	/**
	 * 用户魅力值缓存key前缀
	 */
	public static final String USER_CHARM_KEY = "user_charm_";
	////////////////////////////////////用户魅力值///////////////////////////////////////////////////////
	
	/**
	 * 我的主页的活动按钮缓存key前缀
	 */
	public static final String USER_MYPAGE_ACTIVITY_KEY = "user_mypage_activity";
	
	////////////////////////////////////短信相关///////////////////////////////////////////////////////
	/**
	 * 短信缓存key前缀
	 */
	public static final String VALID_CODE_KEY = "valid_code_";
	
	////////////////////////////////////分享相关///////////////////////////////////////////////////////
	/**
	 * 用户分享详情缓存key前缀
	 */
	public static final String SHARE_CACHE_KEY = "SHARE_detail_";
	
	///////////////////////////////////动态相关的key/////////////////////////////////////////////////
	
	/**
	 * 动态点赞set
	 */
	public static final String REDIS_DYNAMIC_SET_FAVOR_CACHE = "d_s_favor";
	
	/**
	 * 动态点赞hash
	 */
	public static final String REDIS_DYNAMIC_HASH_FAVOR_CACHE = "d_h_favor";
	
	/**
	 * 动态观看set
	 */
	public static final String REDIS_DYNAMIC_SET_LOOK_CACHE = "d_s_audience";
	
	/**
	 * 动态观看hash
	 */
	public static final String REDIS_DYNAMIC_HASH_LOOK_CACHE = "d_h_audience";
	
	//////////////////////////////////SysConfig Key//////////////////////////////////////////////////
	
	/**
	 * 约会类型版本管理
	 */
	public static final String REDIS_SYSCONFIG_APPOINT_TYPE_VERSION = "appoint_type_version";
	
	/**
	 * Tab类型版本管理
	 */
	public static final String REDIS_SYSCONFIG_TAB_TYPE_VERSION = "tab_type_version";
	
	
   //////////////////////////////////UserSubscribeAnchor  Key//////////////////////////////////////////////////
	
	/**
	 * 主播预约未读取数量记录
	 */
	public static final String REDIS_USER_SUBSCRIBE_ANCHOR_UNREAD = "anchor_subscribe_unread";
	
	/**
	 * 主播预约已读取数量记录
	 */
	public static final String REDIS_USER_SUBSCRIBE_ANCHOR_READ = "anchor_subscribe_read";
	
	/**
	 * 用户预约主播前缀
	 */
	public static final String REDIS_USER_SUBSCRIBE_ANCHOR_FLAG_PREFIX = "user_subscribe_anchor_";
	
	/**
	 * 用户金牌预约主播前缀
	 */
	public static final String REDIS_USER_SUBSCRIBE_FLAG_PREFIX = "user_subscribe_anchor_";
	
	/**
	 * 群预约过期时间，30分钟,(SEC)
	 */
    public static final int REDIS_USER_SUBSCRIBE_TIME = 30*60;
    
    //用户是否启动过消息场景
    public static final String REDIS_USER_SCENCE_FLAG_PREFIX = "user_chat_scence_";
   //////////////////////////////////Task Key//////////////////////////////////////////////////
	
	/**
	 * 聊天对话排重(同一人多次聊天算一次)
	 */
	public static final String TASK_CHAT_CHECK = "task_chat_check_times";
	
	
	/**
	 * 每天赠送红花
	 */
	public static final String RED_FLOWER_DAILY_DONOR = "donor_daily_flowers";
	
	
	/**
	 * 创建房间前缀
	 */
	public static final String VCHAT_ROOM_CREATE_PREFIX = "vchat_room_id_";
	
	/**
	 * 用户ID关联的定单号
	 */
	public static final String VCHAT_USER_ORDER_PREFIX = "vchat_user_order_";
	
	
	/**
	 * 用户拨打标记
	 */
	public static final String VCHAT_USER_DIALING_FALG_PREFIX = "vchat_user_dialing_falg_";
	
	
	/**
	 * 视频聊退出标记
	 */
	public static final String VCHAT_ROOM_CLOSE_FALG_PREFIX = "vchat_room_close_falg_";
	
	/**
	 * 视频聊检查通过标记
	 */
	public static final String VCHAT_ROOM_CHECK_PASS_FALG_PREFIX = "vchat_check_pass_";
	
	/**
	 * 推荐主播
	 */
	public static final String RECOMMEND_ONLINE_ANCHOR_FALG_PREFIX = "recommend_online_anchor_falg_";
	
	/**
	 * 用户所在房间前缀
	 */
	public static final String VCHAT_USER_ROOM_PREFIX = "vchat_user_room_";
	
	
	/**
	 * 用户在线直播状态
	 */
	public static final String VCHAT_USER_VIDEO_ONLINE = "vchat_user_video_online";
	
	
	/**
	 * 普通用户在线列表
	 */
	public static final String VCHAT_USER_ORDINARY_ONLINE = "vchat_user_ordinary_online";
	
	/**
	 * 主播当天拨打用户列表
	 */
	public static final String VCHAT_ANCHOR_DIALING_TODAY = "vchat_anchor_dialing_today_";
	
	
	/**
	 * 主播当天拨打次数
	 */
	public static final String VCHAT_ANCHOR_DIAL_TODAY_NUM = "vchat_anchor_dial_today_num_";
	
	/**
	 * 主播当天拨打接听列表
	 */
	public static final String VCHAT_ANCHOR_DIALING_RECV_TODAY = "vchat_anchor_dialing_recv_today_";
	
	/**
	 * IM消息发送标记
	 */
	public static final String VCHAT_IM_SEND_FALG = "vchat_im_send_falg_";
	
	/**
	 * 用户处于前端的列表key值
	 */
	public static final String USER_SEND_PUSH_MESSAGE_CACHE_KEY = "user_send_push_";
	
	
	/**
	 * 订单支付接口，用户显示信息
	 */
	public static final String VCHAT_USER_PAY_SHOW_NOTE_PREFIX = "vchat_user_pay_show_note_";
	
	/**
	 * 转盘订单信息
	 */
	public static final String VCHAT_USER_TURNTABLE_ORDER_PREFIX = "vchat_user_turntable_order_";
	
	
	
	/**
	 * 订单支付接口，用户弹窗
	 */
	public static final String VCHAT_USER_PAY_SHOW_DLG_PREFIX = "vchat_user_pay_show_dlg_";
	
	
	/**
	 * 订单支付接口，用户弹窗
	 */
	public static final String VCHAT_CLOSE_OBSCURATION_PREFIX = "vchat_close_obscuration_";
	
	/**
	 * 重拨打，关闭之前的
	 */
	public static final String ROOM_CLOSE_INITIATIVE_DIAL_PREFIX = "room_close_initiative_dial_";
	
	
	/**
	 * 短视频热门列表
	 */
	public static final String SHORT_VIDEO_USER_HOT_PREFIX = "short_video_user_hot_";
	
	
	/**
	 * 文字聊天控制列表
	 */
	public static final String TEXT_CHAT_CONTROL_PREFIX = "text_chat_control";
	
	/**
	 * 图片聊天控制列表
	 */
	public static final String PICTURE_CHAT_CONTROL_PREFIX = "picture_chat_control";
	
	
	/**
	 * 评论控制列表
	 */
	public static final String EVALUATION_CHAT_CONTROL_PREFIX = "evaluation_chat_control";
	
	
	/**
	 * vivo推送 AuthToken
	 */
	public static final String VIVO_PUSH_MESSAGE_AUTHTOKEN_PREFIX = "vivo_push_message_authtoken_";
	
	
	/**
	 * vivo推送 超量限制
	 */
	public static final String VIVO_PUSH_MESSAGE_LIMITE_PREFIX = "vivo_push_message_LIMITE_";
	
	
	/**
	 * 视频聊检查通过标记
	 */
	public static final String VCHAT_ROOM_SETTLEMENT_INCOME_PREFIX = "vchat_room_settlement_income_";
	
	
	/**
	 *  双11聊天榜
	 */
	public static final String DOUBLE_ELEVEN_VCHAT = "double_eleven_vchat";
	
	/**
	 *  双11礼物榜
	 */
	public static final String DOUBLE_ELEVEN_GIFT = "double_eleven_gift";
	
	/**
	 *  存放每日周卡ID
	 */
	public static final String DAILY_WEEK_CARDID_ID = "daily_week_card_id_";
	
	/**
	 *  守护和VIP增送小红花总数
	 */
	public static final String GUARD_VIP_USER_TOTAL_REDFLOWER = "guard_vip_total_RedFlower_";
	
	
	/**
	 *  守护和VIP增送小红花使用数
	 */
	public static final String GUARD_VIP_USER_USED_REDFLOWER = "guard_vip_used_RedFlower_";
	
	/**
	 *  设置主播禁止拨打用户
	 */
	public static final String Anchor_Forbid_Dial_REDFLOWER = "anchor_forbid_dial_";
	
}
