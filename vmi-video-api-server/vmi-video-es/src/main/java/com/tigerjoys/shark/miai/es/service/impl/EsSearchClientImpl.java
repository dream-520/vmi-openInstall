package com.tigerjoys.shark.miai.es.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.es.configuration.RestClientFactory;
import com.tigerjoys.shark.miai.es.service.IEsSearchClient;

@Service
public class EsSearchClientImpl implements IEsSearchClient {
	
	private static final Logger log = LoggerFactory.getLogger(EsSearchClientImpl.class);
	
	@Autowired
	private RestClientFactory restClientFactory;
	
	@Override
	public List<JSONObject> search(SearchRequest request) throws Exception {
		logInfo(request);
		SearchResponse response = restClientFactory.getHighLevelClient().search(request, RequestOptions.DEFAULT);
		if (response.getHits() == null) {
			return null;
		}
		List<JSONObject> list = new ArrayList<>();
		response.getHits().forEach(item -> list.add(JSON.parseObject(item.getSourceAsString())));
		log.info("Hits", response.getHits().toString());
		return list;

	}
 
	@Override
	public List<String> searchString(SearchRequest request) throws Exception {
		logInfo(request);
		SearchResponse response = restClientFactory.getHighLevelClient().search(request, RequestOptions.DEFAULT);
		if (response.getHits() == null) {
			return null;
		}
		List<String> list = new ArrayList<>();
		response.getHits().forEach(item -> list.add(item.getSourceAsString()));
		return list;

	}
 
    @Override
    public <T> List<T> search(SearchRequest request, Class<T> tClass) throws Exception{
    	
        List<JSONObject> searchResponse = this.search(request);
        if (searchResponse == null) {
            return null;
        }
        List<T> list = new ArrayList<>(searchResponse.size());
        searchResponse.forEach(item -> list.add(JSON.parseObject(JSON.toJSONString(item), tClass)));
        return list;
    }
 
    @Override
    public <T> IndexResponse saveEntity(String index, String type, String id, T t ) throws Exception{
		IndexRequest indexRequest = new IndexRequest(index, type, id);
		indexRequest.source(JSON.toJSONString(t), XContentType.JSON);
		return restClientFactory.getHighLevelClient().index(indexRequest, RequestOptions.DEFAULT);
    }

    
	
	@Override
	public <T> BulkResponse saveAll(String index, String type, List<T> t) throws Exception {
		if (Tools.isNull(t)) {
			return null;
		}
		BulkRequest request = new BulkRequest();
		for (T re : t) {
			long id = IdGenerater.generateId();
			IndexRequest indexRequest = new IndexRequest(index, type, id + "");
			indexRequest.source(JSON.toJSONString(re), XContentType.JSON);
			request.add(indexRequest);
		}
		return restClientFactory.getHighLevelClient().bulk(request, RequestOptions.DEFAULT);

	}

	@Override
	public <T> UpdateResponse updateEntity(String index, String type, String id, T t) throws Exception{
    	UpdateRequest request = new UpdateRequest(index, type, id.toString());
		request.doc(JSON.toJSONString(t), XContentType.JSON);
		return restClientFactory.getHighLevelClient().update(request, RequestOptions.DEFAULT);
	}

	/**
     * 创建索引
     * @param index
     * @throws IOException
     */
    public void createIndex(String index) throws Exception {
    	CreateIndexRequest request = new CreateIndexRequest(index);
        CreateIndexResponse createIndexResponse = restClientFactory.getHighLevelClient().indices().create(request,RequestOptions.DEFAULT);
        System.out.println("createIndex: " + JSON.toJSONString(createIndexResponse));
    }

	@Override
	public boolean existsIndex(String index) throws Exception {
		GetIndexRequest request = new GetIndexRequest(index);
		return restClientFactory.getHighLevelClient().indices().exists(request, RequestOptions.DEFAULT);
	}
	
	public void noExistsCreateIndex(String index) throws Exception {
		// 判断是否存在索引
		if (!existsIndex(index)) {
			// 不存在则创建索引
			createIndex(index);
		}
	}

	@Override
	public DeleteResponse delete(String index, String type, String id) throws Exception {
		DeleteRequest deleteRequest = new DeleteRequest(index, type, id.toString());
		return restClientFactory.getHighLevelClient().delete(deleteRequest,RequestOptions.DEFAULT);
		
	}


	/**
	 * 示例：
	 * 搜索 分页
	 * @param index
	 * @param type
	 * @param name
	 * @throws IOException
	 */
	private void search(String index, String type, String name) throws IOException {
		BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
		boolBuilder.must(QueryBuilders.matchQuery("name", name)); // 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
		// boolBuilder.must(QueryBuilders.matchQuery("id", tests.getId().toString()));
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(boolBuilder);
		sourceBuilder.from(0);
		sourceBuilder.size(100); // 获取记录数，默认10
		sourceBuilder.fetchSource(new String[] { "id", "name" }, new String[] {}); // 第一个是获取字段，第二个是过滤的字段，默认获取全部
		SearchRequest searchRequest = new SearchRequest(index);
		searchRequest.types(type);
		searchRequest.source(sourceBuilder);
		//SearchResponse response = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		SearchResponse response = restClientFactory.getHighLevelClient().search(searchRequest,RequestOptions.DEFAULT);
		System.out.println("search: " + JSON.toJSONString(response));
		SearchHits hits = response.getHits();
		SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
			System.out.println("search -> " + hit.getSourceAsString());
		}
	}

	
	private void logInfo(SearchRequest request){
		if(Tools.isNotNull(request)){
			log.info("elasticsearch:GET"+JsonHelper.toJson(request.indices())+"/"+JsonHelper.toJson(request.types())+"/_search" + (Tools.isNotNull(request.source())?request.source().toString():""));
		}
		
	}

}
