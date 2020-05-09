package org.shark.miai.common.constant;

import com.tigerjoys.nbs.common.ServiceConfig;

/**
 * 业务公共变量定义
 * @author liuman
 *
 */
public class CommonConst {
	
	public static final String fruitSplit = "&";
	
	/*
	 * 任务完成key
	 */
	public static final String task_been_finished_message = "task_been_finished_message";
	
	/**
	 * 新手任务完成key
	 */
	public static final String novice_task_been_finished_message = "novice_task_been_finished_message";
	
	/**
	 * 系统消息key
	 */
	public static final String sys_message = "sys_message";
	
	/**
	 * 用户系统消息key
	 */
	public static final String sys_user_message = "sys_user_message";
	
	/**
	 * 业务消息key
	 */
	public static final String business_message = "business_message";
	
	/**
	 * 用户偷红包个数计数器的key
	 */
	public static final String steal_red_package_count_key = "steal_red_package_count_key";
	
	/**
	 * 心愿树redis缓存set集合key(所有)
	 */
	public static final String MeetWishTree = "MeetWishTree";
	
	/**
	 * 心愿树redis缓存set集合key(男)
	 */
	public static final String MeetWishTreeMan = "MeetWishTreeMan";
	
	/**
	 * 心愿树redis缓存set集合key(女)
	 */
	public static final String MeetWishTreeWoman = "MeetWishTreeWoman";
	
	/**
	 * 心愿树上展示的心愿个数
	 */
	public static final int wishCount = 10;
	
	/**
	 * 包含用户最大的情况下取出最多的用户心愿数量
	 */
	public static final int wishMaxCount = 19;
	
	/**
	 * 心愿数据实体缓存key前缀
	 */
	public static final String USER_WISH_KEY = "v2_wish_";
	
	/**
	 * 系统公告(系统消息)
	 */
	public static final int MSG_TYPE_SYS_NOTICE = 1;
	
	/**
	 * 系统通知（业务消息）
	 */
    public static final int MSG_TYPE_SYS_INFO = 2;
    
    /**
     * 我购买的付费约
     */
    public static final int MSG_TYPE_PAY_DATE_BUY = 3; //付费约 - 买方 
    
    /**
     * 我发布的付费约
     */
    public static final int MSG_TYPE_PAY_DATE_SELL = 4; //付费约 - 卖方
    
    /**
     * 我购买的普通约
     */
    public static final int MSG_TYPE_EGT_DATE_BUY = 5; //免费费约 - 买方
    
    /**
     * 我发布的普通约
     */
    public static final int MSG_TYPE_EGT_DATE_SELL = 6; //免费费约 - 卖方
    
    /**
     * 派单约会的达人接单
     */
    public static final int MSG_TYPE_PAY_DATE_SELL_ORDERS = 7; //付费约 - 卖方 - 接单
    
    /**
     * 临时添加的活动消息
     */
    public static final int MSG_TYPE_MY_TEMP = 10000; //我的临时消息 10000-19999
    
	/**
	 * 注册用户的注册时间存储到redis缓存zset集合key
	 */
	public static final String registerUser = "registerUser";
	
	/**
	 * 百度密钥
	 */
	public static final String baidu_map_ak = "4u5kVk5H2LzED5j0NVhvuTk1yEkUWPdp";
	
	/**
	 * 是否是测试环境
	 */
	public static final boolean IS_TEST = "offline".equals(ServiceConfig.getInstance().getString("env"));
	
	/**
	 * 轮播图播放间隔秒
	 */
	public static final String BANNER_INTERVAL = "banner_interval";
	
	/**
	 * 遇见模块轮播
	 */
	public static final String MEET_STRAMGER_BANNER = "meet_stranger_banner";
	
	/**
	 * 遇见页面筛选出来的推荐每页用户的数量
	 */
	public static final int meet_stranger_pagesize = 10;
	
	/**
	 * 用户置顶时间key
	 */
	public static final String latest_stick_user_time = "latest_stick_user_time";
	
	/**
	 * 小红点未读数量总和key
	 */
	public static final String totalUnreadKey = "totalUnreadKey";
	
	/**
	 * '我购买的'业务消息key
	 */
	public static final String myPurchase = "myPurchase";
	
	/**
	 * '我发布的'业务消息key
	 */
	public static final String myPublish = "myPublish";
	
	/**
	 * 临时消息key
	 */
	public static final String myTemp = "myTemp";
	
	/**
	 * 我的页面活动临时key
	 */
	public static final String myPageKey = "myPageKey_";
	
	/**
	 * 百度地图服务端请求数据ak秘钥
	 */
	public static final String ak = ServiceConfig.getInstance().getString("ak");
	
	/**
	 * '我购买的'业务消息key
	 */
	public static final String myPurchaseFree = "myPurchaseFree";
	
	/**
	 * '我发布的'业务消息key
	 */
	public static final String myPublishFree = "myPublishFree";
	
	/**
	 * '达人接单'业务消息key
	 */
	public static final String masterReceiveDispatch = "masterReceiveDispatch";
	

	/**
	 * 达人服务ID集合，只取前1000个
	 */
	public static final String TALENT_PAID_APPOINT_USERID_SET = "talent_paid_appoint_userid_set";
	
	/**
	 * 普通约订单ID集合，只取前1000个
	 */
	public static final String FREE_APPOINT_ID_SET = "free_appoint_id_set";
	
	
	/**
	 * 普通约订单ID集合，只取前1000个
	 */
	public static final String TOP_HEAD_INFO_SET = "top_head_info_set";
	
	/**
	 * 遇见文字公告播放间隔秒
	 */
	public static final String MEET_STRAMGER_NOTICE_INTERVAL = "meet_stranger_notice_interval";
	
	/**
	 * 首页弹窗信息redis的key
	 */
	public static final String HOMEPOPKEY = "homePop";
	
	/**
	 * 首页活动区域
	 */
	public static final String INDEX_ACTIVITY_AREA = "index_activity_area";
	
	/**
	 * 首页活动关键区域
	 */
	public static final String INDEX_ACTIVITY_KEY_AREA = "index_activity_key_area";
	
	/**
	 * 首页悬浮窗
	 */
	public static final String INDEX_ACTIVITY_POP = "index_activity_pop";
	
	
	/**
	 * H5活动区域
	 */
	public static final String INDEX_ACTIVITY_H5 = "index_activity_h5";
	
	
	/**
	 * 首页热门分类
	 */
	public static final String INDEX_HOT_CATEGORY = "index_hot_category";
	
	/**
	 * 达人->最高人气推荐
	 */
	public static final String TALENT_COMMEND = "talent_commend";
	
	/**
	 * 达人接收派单信息每页数据量
	 */
	public final static int MASTER_RECEIVE_DISPATCH_PAGESIZE = 20;
	
	/**
	 * 用户收藏的达人记录每页数据量
	 */
	public final static int MASTER_COLLECT_PAGESIZE = 3;
	
	/**
	 * 推荐页倒计时初始时间
	 */
	public static final long originalCountDownTime = 30*60*1000;
//	public static final long originalCountDownTime = 2*60*1000;
	
	/**
	 * 推荐页倒计时分钟数
	 */
	public static final long originalCountDownMinutes = 30;
//	public static final long originalCountDownMinutes = 2;
}
