package com.tigerjoys.shark.miai.es.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.shark.miai.common.enums.UserTypeEnum;
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
import com.tigerjoys.shark.miai.es.dto.EsUserOnlineReordDto;
import com.tigerjoys.shark.miai.es.service.IEsSearchClient;
import com.tigerjoys.shark.miai.es.service.IEsUserOnlineRecordService;

@Service
public class EsUserOnlineRecordServiceImpl implements IEsUserOnlineRecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(EsUserOnlineRecordServiceImpl.class);
  
	private String esType = "useronline";
	
	@Autowired
    private IEsSearchClient esSearchClient;
	
	@Autowired
	private RestClientFactory restClientFactory;
	
	public void saveEntity(EsUserOnlineReordDto dto) throws Exception{
		long id = IdGenerater.generateId();
		if(!EsConst.is_test){
			ExecutorServiceHelper.executor.execute(new SendESThread(esType,""+id,dto));
		}	
	}

	@Override
	public long queryUserOnlineNum(Date bigin, Date end ,UserTypeEnum userType) throws Exception {
		if (Tools.isNull(bigin) || Tools.isNull(end)) {
			return 0;
		}
		SearchRequest searchRequest = new SearchRequest(EsConst.ES_INDEX_NAME);
		searchRequest.types(esType);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId").field("userId");
		long low = bigin.getTime();
		long hight = end.getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createDate").from(low, true).to(hight, true);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();  
		boolQueryBuilder.must(matchQueryBuilder);
		
		if(Tools.isNotNull(userType)){
			boolQueryBuilder.must(QueryBuilders.termQuery("userType", userType.getCode()));
		}
		sourceBuilder.query(boolQueryBuilder);
		sourceBuilder.aggregation(aggregation);
		sourceBuilder.size(0);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET"+EsConst.ES_INDEX_NAME+"/"+esType+"/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = null;
		searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		System.out.println("searchResponse:" + searchResponse.getTotalShards());
		if (RestStatus.OK.equals(searchResponse.status())) {
			Aggregations aggregations = searchResponse.getAggregations();
			Aggregation ation = aggregations.get("distict_userId");
			Cardinality cardinality = (Cardinality) ation;
			return cardinality.getValue();
		}

		return 0;
	}

	
	public List<Long> queryUserOnline(Date bigin, Date end ,UserTypeEnum userType) throws Exception{
		List<Long> userIdList = new ArrayList<Long>();
		if (Tools.isNull(bigin) || Tools.isNull(end)) {
			return userIdList;
		}
		SearchRequest searchRequest = new SearchRequest(EsConst.ES_INDEX_NAME);
		searchRequest.types(esType);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		//CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId").field("userId");
		TermsAggregationBuilder   appNameAggregation = AggregationBuilders.terms("group_userId").field("userId");
		appNameAggregation.size(10000);
		long low = bigin.getTime();
		long hight = end.getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createDate").from(low, true).to(hight, true);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();  
		boolQueryBuilder.must(matchQueryBuilder);
		
		if(Tools.isNotNull(userType)){
			boolQueryBuilder.must(QueryBuilders.termQuery("userType", userType.getCode()));
		}
		sourceBuilder.query(boolQueryBuilder);
		sourceBuilder.aggregation(appNameAggregation);
		sourceBuilder.size(0);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET"+EsConst.ES_INDEX_NAME+"/"+esType+"/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = null;
		searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		Aggregations aggregations = searchResponse.getAggregations();
		 Terms byAppNameAggregation = aggregations.get("group_userId");
          for(Bucket buck : byAppNameAggregation.getBuckets()) {
        	  Long userId = Tools.parseLong(buck.getKeyAsString());
        	  userIdList.add(userId);
          }
		return userIdList;
	}

	private  class SendESThread implements Runnable{
		private String esType;
		private String id;
		private EsUserOnlineReordDto dto;
		
		public SendESThread(String esType ,String id,EsUserOnlineReordDto dto){
			this.esType = esType;
			this.id = id;
			this.dto = dto;
		}
		@Override
		public void run() {
			try{
				logger.info("RestClientFactory_info:index:"+EsConst.ES_INDEX_NAME+";esType:"+esType+";id"+id+";dto:"+JsonHelper.toJson(dto));
				IndexResponse indexResponse =  esSearchClient.saveEntity(EsConst.ES_INDEX_NAME, esType, id+"", dto);
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
