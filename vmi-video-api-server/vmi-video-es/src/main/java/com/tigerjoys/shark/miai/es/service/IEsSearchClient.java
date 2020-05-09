package com.tigerjoys.shark.miai.es.service;

import java.util.List;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateResponse;

import com.alibaba.fastjson.JSONObject;

public interface IEsSearchClient {
	/**
     * 搜索结果
     */
	public List<JSONObject> search(SearchRequest request) throws Exception;
 
    public List<String> searchString(SearchRequest request) throws Exception;
    /**
     * 搜索
     */
    public <T> List<T> search(SearchRequest request, Class<T> tClass) throws Exception;
 
    /**
     * 插入数据
     * @param index 数据库名称
     * @param type 数据库表名
     * @param id 数据库主键ID
     * @param t 实体class
     * @param <T>
     * @return
     */
    public <T> IndexResponse saveEntity(String index, String type, String id, T t) throws Exception;

    
    /**
     * 批量插入数据
     * @param index 数据库名称
     * @param type 数据库表名
     * @param id 数据库主键ID
     * @param t 实体class
     * @param <T>
     * @return
     */
    public <T> BulkResponse saveAll(String index, String type, List<T> t) throws Exception;
    
	/**
	 * 更改
	 * @param index
	 * @param type
	 * @param id
	 * @param t
	 */
	public <T> UpdateResponse updateEntity(String index, String type, String id,T t) throws Exception;
	/**
	 * 创建索引
	 * @param index
	 * @throws Exception
	 */
	public void createIndex(String index) throws Exception;
	
	/**
	 * 索引是否存在
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public boolean existsIndex(String index) throws Exception;
	
	
	/**
	 * 不存在创建索引
	 * @param index
	 * @throws Exception
	 */
	public void noExistsCreateIndex(String index) throws Exception;
	
	
	/**
	 * 删除记录
	 * @param index
	 * @param type
	 * @param id
	 * @throws Exception
	 */
	public DeleteResponse delete(String index, String type, String id) throws Exception;
	
}
