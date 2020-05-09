package com.tigerjoys.shark.miai.es.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tigerjoys.nbs.common.utils.ExecutorServiceHelper;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.common.utils.sequence.IdGenerater;
import com.tigerjoys.shark.miai.es.configuration.RestClientFactory;
import com.tigerjoys.shark.miai.es.constant.EsConst;
import com.tigerjoys.shark.miai.es.dto.EsMobileUserAppListReordDto;
import com.tigerjoys.shark.miai.es.service.IEsMobileUserAppRecordService;
import com.tigerjoys.shark.miai.es.service.IEsSearchClient;

@Service
public class EsMobileUserAppRecordServiceImpl implements IEsMobileUserAppRecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(EsMobileUserAppRecordServiceImpl.class);
  
	private String esType = "userapplist";
	
	@Autowired
    private IEsSearchClient esSearchClient;

	@Autowired
	private RestClientFactory restClientFactory;
	
 
	public void saveEntity(EsMobileUserAppListReordDto dto) throws Exception{
		String id = IdGenerater.generateId()+"";
		//if(!Const.is_test){
			ExecutorServiceHelper.executor.execute(new SendESThread(esType,id,dto));
		//}	
	}

	public List<String> queryLastLoginAppList(long userId) throws Exception{
		List<String> appList = null;
		
		SearchRequest searchRequest = new SearchRequest(EsConst.USREAPP_ES_INDEX_NAME);
		searchRequest.types(esType);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(QueryBuilders.termQuery("userId", userId));
		sourceBuilder.sort("createTime", SortOrder.DESC);
		sourceBuilder.size(1);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET "+EsConst.USREAPP_ES_INDEX_NAME+"/"+esType+"/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		if (RestStatus.OK.equals(searchResponse.status())) {
			SearchHits searchHits = searchResponse.getHits();
			for(SearchHit hit:searchHits.getHits()){
				Map<String,Object> map = hit.getSourceAsMap();
				Object obj = map.get("appList");
				appList = (ArrayList<String>)obj;
				logger.info("elasticsearch:userId="+userId+";result:"+hit.getSourceAsString());
			}
		}
		
		return appList;
	}


	private  class SendESThread implements Runnable{
		private String esType;
		private String id;
		private EsMobileUserAppListReordDto dto;
		
		public SendESThread(String esType,String id,EsMobileUserAppListReordDto dto){
			this.esType = esType;
			this.dto = dto;
			this.id = id;
		}
		@Override
		public void run() {
			try{
				logger.info("RestClientFactory_info:index:"+EsConst.USREAPP_ES_INDEX_NAME+";esType:"+esType+";id"+id+";dto:"+JsonHelper.toJson(dto));
				IndexResponse indexResponse =  esSearchClient.saveEntity(EsConst.USREAPP_ES_INDEX_NAME, esType,id, dto);
				if(Tools.isNotNull(indexResponse)){
					String index = indexResponse.getIndex();
		            String type = indexResponse.getType();
		            String idRes = indexResponse.getId();
		            long version = indexResponse.getVersion();
		            logger.info("RestClientFactory_info:返回结果码:"+indexResponse.getResult()+";version:"+version+";index="+index+";type="+type+";idRes="+idRes);
				}
				}catch(Exception e){
					logger.info("RestClientFactory_info:index_result-异常",e);
				}
		}
	}



	

}
