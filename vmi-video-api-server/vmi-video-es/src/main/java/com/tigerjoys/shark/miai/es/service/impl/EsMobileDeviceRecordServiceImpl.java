package com.tigerjoys.shark.miai.es.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
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
import com.tigerjoys.shark.miai.es.dto.EsMobileDeviceReordDto;
import com.tigerjoys.shark.miai.es.service.IEsMobileDeviceRecordService;
import com.tigerjoys.shark.miai.es.service.IEsSearchClient;

@Service
public class EsMobileDeviceRecordServiceImpl implements IEsMobileDeviceRecordService {
	
	private static final Logger logger = LoggerFactory.getLogger(EsMobileDeviceRecordServiceImpl.class);
  
	private String esType = "mobiledevice";
	
	@Autowired
	private IEsSearchClient esSearchClient;

	@Autowired
	private RestClientFactory restClientFactory;
 
	public void saveAll(List<EsMobileDeviceReordDto> dto) throws Exception {
		long id = IdGenerater.generateId();
		//if(!Const.is_test){
			ExecutorServiceHelper.executor.execute(new SendESThread(esType,dto));
		//}	
	}

	@Override
	public List<String> queryTopApp(int size) throws Exception{
		List<String> appList = null;
	
		SearchRequest searchRequest = new SearchRequest(EsConst.COLLECT_ES_INDEX_NAME);
		searchRequest.types(esType);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		TermsAggregationBuilder   aggregation = AggregationBuilders.terms("by_appName").field("appName.keyword").order(BucketOrder.count(false));
		aggregation.size(size);
		sourceBuilder.aggregation(aggregation);
		sourceBuilder.size(0);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET "+EsConst.COLLECT_ES_INDEX_NAME+"/"+esType+"/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
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
            	//   out.write(buck.getKeyAsString()+"\t \t"+buck.getDocCount());
            	//   out.newLine();
            	   appList.add(buck.getKeyAsString());
               }
               //out.flush();
               //out.close();
		}
		
		return appList;
	}


	private  class SendESThread implements Runnable{
		private String esType;
		private String id;
		private List<EsMobileDeviceReordDto> dto;
		
		public SendESThread(String esType,List<EsMobileDeviceReordDto> dto){
			this.esType = esType;
			this.dto = dto;
		}
		@Override
		public void run() {
			try{
				logger.info("RestClientFactory_info:index:"+EsConst.COLLECT_ES_INDEX_NAME+";esType:"+esType+";id"+id+";dto:"+JsonHelper.toJson(dto));
				BulkResponse bulkResponse =  esSearchClient.saveAll(EsConst.COLLECT_ES_INDEX_NAME, esType, dto);
				if(Tools.isNotNull(bulkResponse)){
		            logger.info("RestClientFactory_info:返回结果码:"+bulkResponse.getIngestTookInMillis());
				}
				}catch(Exception e){
					logger.info("RestClientFactory_info:index_result-异常",e);
				}
		}
	}
	

}
