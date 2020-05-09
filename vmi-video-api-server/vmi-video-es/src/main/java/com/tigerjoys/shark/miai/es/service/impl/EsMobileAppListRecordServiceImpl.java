package com.tigerjoys.shark.miai.es.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
import com.tigerjoys.shark.miai.es.dto.EsMobileAppListReordDto;
import com.tigerjoys.shark.miai.es.service.IEsMobileAppListRecordService;
import com.tigerjoys.shark.miai.es.service.IEsSearchClient;

@Service
public class EsMobileAppListRecordServiceImpl implements IEsMobileAppListRecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(EsMobileAppListRecordServiceImpl.class);
  
	private String esType = "mobileapplist";
	
	@Autowired
	private IEsSearchClient esSearchClient;

	@Autowired
	private RestClientFactory restClientFactory;
 
	public void saveAll(List<EsMobileAppListReordDto> dto) throws Exception{
		long id = IdGenerater.generateId();
		//if(!Const.is_test){
			ExecutorServiceHelper.executor.execute(new SendESThread(esType,dto));
		//}	
	}

	@Override
	public List<String> queryTopApp(int userType ,int size) throws Exception{
		List<String> appList = null;
	
		SearchRequest searchRequest = new SearchRequest(EsConst.APPLIST_ES_INDEX_NAME);
		searchRequest.types(esType);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		if(userType>=0){
			sourceBuilder.query(QueryBuilders.termQuery("userType", userType));
		}
		///////////////////////2020.2.5号之后
		/*
		Calendar cal = Calendar.getInstance();
		cal.set(2020,2, 5);
		Long low = cal.getTime().getTime();
		long hight = new Date().getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createDate").from(low, true).to(hight,true);
		sourceBuilder.query(matchQueryBuilder);
		*/
		
		//TermsAggregationBuilder   appNameAggregation = AggregationBuilders.terms("by_appName").field("appName.keyword").order(BucketOrder.count(false));
		TermsAggregationBuilder   appNameAggregation = AggregationBuilders.terms("by_appName").field("appName.keyword").order(BucketOrder.aggregation("distict_userId", false));
		appNameAggregation.size(size);
		// 多字段 group by 
		//TermsAggregationBuilder   userIdAggregation = AggregationBuilders.terms("by_userId").field("userId");
		//userIdAggregation.size(size);
		//count(distinct)
		CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId").field("userId");
		
		TermsAggregationBuilder allAggregation = appNameAggregation.subAggregation(aggregation);
		
		sourceBuilder.aggregation(allAggregation);
		sourceBuilder.size(0);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET "+EsConst.APPLIST_ES_INDEX_NAME+"/"+esType+"/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = null;
		searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		if (RestStatus.OK.equals(searchResponse.status())) {
			appList = new ArrayList<String>();
			Aggregations aggregations = searchResponse.getAggregations();
			 Terms byAppNameAggregation = aggregations.get("by_appName");
			   logger.info("queryTopApp.aggregation by_appName 结果");
               logger.info("queryTopApp.docCountError: " + byAppNameAggregation.getDocCountError());
               logger.info("queryTopApp.sumOfOtherDocCounts: " + byAppNameAggregation.getSumOfOtherDocCounts());
              // BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"));
               for(Bucket buck : byAppNameAggregation.getBuckets()) {
            	   //out.write(buck.getKeyAsString()+"\t \t"+buck.getDocCount());
            	   //out.newLine();
            	   appList.add(buck.getKeyAsString());
            	   
            	   Aggregations subAggregations =  buck.getAggregations();
            	   Aggregation ation = subAggregations.get("distict_userId");
            	   Cardinality valueCount = (Cardinality)ation;
					System.out.println("valueCount:"+ valueCount.getValue());
            	   System.out.println("xxxx:"+buck.getKeyAsString()+"   "+valueCount.getValue());
               }
              // out.flush();
              // out.close();
		}
		
		return appList;
	}


	private  class SendESThread implements Runnable{
		private String esType;
		private String id;
		private List<EsMobileAppListReordDto> dto;
		
		public SendESThread(String esType,List<EsMobileAppListReordDto> dto){
			this.esType = esType;
			this.dto = dto;
		}
		@Override
		public void run() {
			try{
				logger.info("RestClientFactory_info:index:"+EsConst.APPLIST_ES_INDEX_NAME+";esType:"+esType+";id"+id+";dto:"+JsonHelper.toJson(dto));
				BulkResponse bulkResponse =  esSearchClient.saveAll(EsConst.APPLIST_ES_INDEX_NAME, esType, dto);
				if(Tools.isNotNull(bulkResponse)){
		            logger.info("RestClientFactory_info:返回结果码:"+bulkResponse.getIngestTookInMillis());
				}
				}catch(Exception e){
					logger.info("RestClientFactory_info:index_result-异常",e);
				}
		}
	}
	

}
