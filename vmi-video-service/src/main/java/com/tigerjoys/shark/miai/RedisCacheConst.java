package com.tigerjoys.shark.miai;

/**
 * redis缓存常量
 * @author chengang
 *
 */
public final class RedisCacheConst {
	
	/**
	 * 缓存的默认秒数，1小时
	 */
	public static final int DEFAULT_CACHE_EXPIRE = 3600;
	
	/**
	 * 公共缓存Redis客户端名称
	 */
	public static final String REDIS_PUBLIC_CACHE = "publicRedisCache";
	
	/**
	 * mybatis DAO的Redis缓存客户端名称
	 */
	public static final String PUBLIC_MYBATIS_SPRING_BEAN_NAME = "mybatisRedisCache";
	
	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 达人秀技能点击缓存前缀
	 */
	public static final String PAID_STILL_CLICK_PREFIX = "still_click_";

	/**
	 * 世界杯投票
	 */
	public static final String WORLD_CUP_VOTE = "W_C_VOTE";
	
	
	
	
}
