package com.tigerjoys.shark.miai.es.constant;

import com.tigerjoys.nbs.common.ServiceConfig;

public class EsConst {
	
	/**
	 * 是否是测试环境
	 */
	public static final boolean is_test = "offline".equals(ServiceConfig.getInstance().getString("env"));
	
	/**
	 * ES索引名
	 */
	public static final String ES_INDEX_NAME = ServiceConfig.getInstance().getString("es_index_name", "vmitest");
	
	/**
	 * 采集ES索引名
	 */
	public static final String COLLECT_ES_INDEX_NAME = ServiceConfig.getInstance().getString("collect_es_index_name", "vmitest");
	
	/**
	 * 采集APPList的ES索引名
	 */
	public static final String APPLIST_ES_INDEX_NAME = ServiceConfig.getInstance().getString("applist_es_index_name", "vmitest");
	
	/**
	 * 采集用户APP的ES索引名
	 */
	public static final String USREAPP_ES_INDEX_NAME = ServiceConfig.getInstance().getString("userApp_es_index_name", "vmitest");
	
	private EsConst() {
		
	}

}
