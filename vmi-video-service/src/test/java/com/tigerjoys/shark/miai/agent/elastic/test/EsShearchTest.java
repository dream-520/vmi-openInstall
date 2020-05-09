package com.tigerjoys.shark.miai.agent.elastic.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
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
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestion;
import org.junit.Test;
import org.shark.miai.common.enums.UserTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.nbs.common.cache.CacheRedis;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.shark.miai.RedisCacheConst;
import com.tigerjoys.shark.miai.agent.constant.AgentRedisCacheConst;
import com.tigerjoys.shark.miai.es.configuration.RestClientFactory;
import com.tigerjoys.shark.miai.es.constant.EsConst;
import com.tigerjoys.shark.miai.es.dto.EsMobileAppListReordDto;
import com.tigerjoys.shark.miai.es.dto.EsMobileDeviceReordDto;
import com.tigerjoys.shark.miai.es.dto.EsUserOnlineReordDto;
import com.tigerjoys.shark.miai.es.enums.EsUserOnlineUserEventEnum;
import com.tigerjoys.shark.miai.es.service.IEsMobileAppListRecordService;
import com.tigerjoys.shark.miai.es.service.IEsMobileDeviceRecordService;
import com.tigerjoys.shark.miai.es.service.IEsMobileUserAppRecordService;
import com.tigerjoys.shark.miai.es.service.IEsSearchClient;
import com.tigerjoys.shark.miai.es.service.IEsUserOnlineRecordService;
import com.tigerjoys.shark.miai.utils.BaseTestConfig;

/**
 * 用户代理测试
 * @author chengang
 *
 */
public class EsShearchTest extends BaseTestConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RestClientFactory restClientFactory;
	
	@Autowired
    private IEsSearchClient esSearchClient ;
	
	@Autowired
    private IEsUserOnlineRecordService esUserOnlineRecordService ;

	@Autowired
    private IEsMobileDeviceRecordService esMobileDeviceRecordService ;
	
	@Autowired
	private IEsMobileAppListRecordService esMobileAppListRecordService;
  
	@Autowired
	private IEsMobileUserAppRecordService esMobileUserAppRecordService;
	
	
	@Autowired
	@Qualifier(RedisCacheConst.REDIS_PUBLIC_CACHE)
	private CacheRedis cacheRedis;

	
	
	@Test
	public void testCreateIndexRequest(){
	//	RestHighLevelClient rhlClient = ElasticSearchClientFactiory.getHighLevelClient();
		RestHighLevelClient rhlClient = restClientFactory.getHighLevelClient();

        CreateIndexRequest request = new CreateIndexRequest("mobiletest");
        CreateIndexResponse createIndexResponse =null;
		
			try {
				createIndexResponse = rhlClient.indices().create(request,RequestOptions.DEFAULT);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		System.out.println("XXXXXXXXXXXXXXXVVVVV");
        System.out.println("createIndex: ");
		
	}
	/**
	 * 不存在创建索引
	 */
	@Test
	public void noExistsCreateIndex(){
		try {
			esSearchClient.noExistsCreateIndex("vmiuserapp");
			//esSearchClient.noExistsCreateIndex("vmiuserapp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 添加记录
	 */
	@Test
	public void testInsertAll(){
		try {
			List<EsMobileDeviceReordDto>  list = new ArrayList<>();
			list.add(EsMobileDeviceReordDto.preDto("f5514528700c50ae9acc3526ede2f32d", "com.ydwx.yoyo", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("22222222", "www.xxxxx.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("33333333", "www.baidu.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("44444444", "www.baidu.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("55555555", "www.xxxxx.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("66666666", "www.baidu.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("77777777", "www.baidu.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("88888888", "www.zzzzz.com", new Date()));
//			list.add(EsMobileDeviceReordDto.preDto("99999999", "www.baidu.com", new Date()));
			esMobileDeviceRecordService.saveAll(list);
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 添加记录
	 */
	@Test
	public void testAppListInsertAll(){
		try {
			List<EsMobileAppListReordDto>  list = new ArrayList<>();
			list.add(EsMobileAppListReordDto.preDto(154009850727170304L,0,"com.ydwx.yoyo3", LocalDateTime.now()));
			
			esMobileAppListRecordService.saveAll(list);
			System.out.println(new Date().getTime()+"   "+JSON.toJSONString(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加记录
	 */
	@Test
	public void testQueryAll(){
		try {
			
			List<String> list = esMobileDeviceRecordService.queryTopApp(10000);
			System.out.println("-----------json----:"+JsonHelper.toJson(list));
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按    const(distinct *)  排序
	 */
	@Test
	public void testGroupByQueryAll(){
		try {
			
			List<String> list = esMobileAppListRecordService.queryTopApp(0,10000);
			System.out.println("-----------json----:"+JsonHelper.toJson(list));
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加记录
	 */
	@Test
	public void testAdd(){
		try {
			esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(1008L,UserTypeEnum.ordinary,EsUserOnlineUserEventEnum.refresh,new Date()));
			esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(1009L,UserTypeEnum.ordinary,EsUserOnlineUserEventEnum.refresh,new Date()));
			esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(1013L,UserTypeEnum.ordinary,EsUserOnlineUserEventEnum.refresh,new Date()));
			esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(1017L,UserTypeEnum.ordinary,EsUserOnlineUserEventEnum.refresh,new Date()));
			esUserOnlineRecordService.saveEntity(EsUserOnlineReordDto.preDto(1016L,UserTypeEnum.ordinary,EsUserOnlineUserEventEnum.refresh,new Date()));
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 查数据 并 排序
	 */
	@Test
	public void testQueryUserAppAll(){
		try {
			
			List<String> list = esMobileUserAppRecordService.queryLastLoginAppList(156040471628546304L);
			System.out.println("-----------json----:"+JsonHelper.toJson(list));
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 建义查询
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testSuggestion(){
		
		TermQueryBuilder matchQueryBuilder = QueryBuilders.termQuery("type", 0);
		
		SuggestionBuilder  cardinality = SuggestBuilders.termSuggestion("userId").text("kmichy"); 
		
		   SuggestBuilder suggestBuilder = new SuggestBuilder();
		   suggestBuilder.addSuggestion("suggest_type", cardinality);
		//cardinality.subAggregation(AggregationBuilders.max("userId"));
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		sourceBuilder.suggest(suggestBuilder);
		sourceBuilder.query(matchQueryBuilder);
		sourceBuilder.size(0); 
		SearchRequest searchRequest = new SearchRequest("vmitest");
		searchRequest.types("useronline");
		searchRequest.source(sourceBuilder);
		
		System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = null;
		try {
			searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		
			 if(RestStatus.OK.equals(searchResponse.status())) {
	                // 获取建议结果
	                Suggest suggest = searchResponse.getSuggest(); 
	                TermSuggestion termSuggestion = suggest.getSuggestion("suggest_type"); 
	                for (TermSuggestion.Entry entry : termSuggestion.getEntries()) { 
	                    logger.info("text: " + entry.getText().string());
	                    for (TermSuggestion.Entry.Option option : entry) { 
	                        String suggestText = option.getText().string();
	                        logger.info("   suggest option : " + suggestText);
	                    }
	                }
	            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("XXXXX:"+(Tools.isNotNull(response)?searchResponse.getSuccessfulShards():""));
	}
	
	
	/**
	 * 聚合查询
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testAggregation(){
		
		SearchRequest searchRequest = new SearchRequest("vmitest");
		searchRequest.types("useronline");
		
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		
		 TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_userId")
                 .field("userId").order(BucketOrder.aggregation("average_balance", true));
		 
		//计算每组的平均balance指标
         aggregation.subAggregation(AggregationBuilders.avg("average_balance")
                 .field("userId"));
		 
		//sourceBuilder.query(matchQueryBuilder);
		 sourceBuilder.aggregation(aggregation);
		sourceBuilder.size(0); 
		
		
		searchRequest.source(sourceBuilder);
		System.out.println(searchRequest.source().toString());
		System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = null;
		try {
			searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		
			 if(RestStatus.OK.equals(searchResponse.status())) {
	                // 获取建议结果
				 Aggregations aggregations = searchResponse.getAggregations(); 
				 Terms byAgeAggregation = aggregations.get("by_userId"); 
	                logger.info("aggregation by_age 结果");
	                logger.info("docCountError: " + byAgeAggregation.getDocCountError());
	                logger.info("sumOfOtherDocCounts: " + byAgeAggregation.getSumOfOtherDocCounts());
	                logger.info("------------------------------------");
	                for(Bucket buck : byAgeAggregation.getBuckets()) {
	                    logger.info("key: " + buck.getKeyAsNumber());
	                    logger.info("docCount: " + buck.getDocCount());
	                    logger.info("docCountError: " + buck.getDocCountError());
	                    //取子聚合
	                   // Avg averageBalance = buck.getAggregations().get("average_balance"); 

	                 //   logger.info("average_balance: " + averageBalance.getValue());
	                    logger.info("------------------------------------");
	                }   
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * 去重查询
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testCardinalityAggregation(){
		
		SearchRequest searchRequest = new SearchRequest("vmitest");
		searchRequest.types("useronline");
		
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		
		//ValueCountAggregationBuilder valueCount = AggregationBuilders.count("count_UserId").field("userId");
		CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId")
                 .field("userId");
		
		//aggregation.subAggregation(valueCount);
		//sourceBuilder.query(matchQueryBuilder);
		 sourceBuilder.aggregation(aggregation);
		sourceBuilder.size(0); 
		
		
		searchRequest.source(sourceBuilder);
		System.out.println("source:"+searchRequest.source().toString());
		SearchResponse searchResponse = null;
		try {
			searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
			System.out.println("searchResponse:"+searchResponse.getTotalShards());
			//  {"asMap":{"distict_userId":{"name":"distict_userId","value":7,"type":"cardinality","valueAsString":"7.0","fragment":true}},"fragment":true}  //
			 if(RestStatus.OK.equals(searchResponse.status())) {
	                // 获取建议结果
				 Aggregations aggregations = searchResponse.getAggregations(); 
				 
				 	System.out.println("TEST:"+JsonHelper.toJson(aggregations));
				 	Aggregation ation = aggregations.get("distict_userId"); 
				 	
				 	System.out.println("ation.toString:"+ation.toString());
				 	System.out.println("ation.toJson:"+JsonHelper.toJson(ation));
					JSONObject json =JsonHelper.toJsonObject(JsonHelper.toJson(ation));
					System.out.println("ation.value:"+json.getLong("value"));
					
					Cardinality valueCount = (Cardinality)ation;
					System.out.println("valueCount:"+ valueCount.getValue());
	               
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 范围
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testIfCardinalityAggregation(){
		
		SearchRequest searchRequest = new SearchRequest("vmitest");
		searchRequest.types("useronline");
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId")
                 .field("userId");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Long low = cal.getTime().getTime();
		long hight = new Date().getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createDate").from(low, true).to(hight,true);
		
		sourceBuilder.query(matchQueryBuilder);
		 sourceBuilder.aggregation(aggregation);
		sourceBuilder.size(0); 
		
		
		searchRequest.source(sourceBuilder);
		System.out.println("source:"+searchRequest.source().toString());
		SearchResponse searchResponse = null;
		try {
			searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
			System.out.println("searchResponse:"+searchResponse.getTotalShards());
			//  {"asMap":{"distict_userId":{"name":"distict_userId","value":7,"type":"cardinality","valueAsString":"7.0","fragment":true}},"fragment":true}  //
			 if(RestStatus.OK.equals(searchResponse.status())) {
	                // 获取建议结果
				 Aggregations aggregations = searchResponse.getAggregations(); 
				 	Aggregation ation = aggregations.get("distict_userId"); 
					Cardinality valueCount = (Cardinality)ation;
					System.out.println("valueCount:"+ valueCount.getValue());
	
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testqueryUserOnlineNum() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -3);
		Date begin = cal.getTime();
		Date end = new Date();
		Integer userType =1;
		SearchRequest searchRequest = new SearchRequest(EsConst.ES_INDEX_NAME);
		searchRequest.types("useronline");

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId").field("userId");
		
		long low = begin.getTime();
		long hight = end.getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createDate").from(low, true).to(hight, true);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();  
		boolQueryBuilder.must(matchQueryBuilder);
		
		if(Tools.isNotNull(userType)){
			boolQueryBuilder.must(QueryBuilders.termQuery("userType", userType));
		}
		sourceBuilder.query(boolQueryBuilder);
		sourceBuilder.aggregation(aggregation);
		sourceBuilder.size(0);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET"+EsConst.ES_INDEX_NAME+"/useronline/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = null;
		searchResponse = restClientFactory.getHighLevelClient().search(searchRequest,RequestOptions.DEFAULT);
		System.out.println("searchResponse:" + searchResponse.getTotalShards());
		if (RestStatus.OK.equals(searchResponse.status())) {
			Aggregations aggregations = searchResponse.getAggregations();
			Aggregation ation = aggregations.get("distict_userId");
			Cardinality cardinality = (Cardinality) ation;
			System.out.println("count:"+cardinality.getValue());
		}

	}
	
	@Test
	public void testqueryUserOnline() throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -3);
		Date begin = cal.getTime();
		Date end = new Date();
		Integer userType =1;
		List<Long> userIdList = new ArrayList<>();
		//SearchRequest searchRequest = new SearchRequest(Const.ES_INDEX_NAME);
		SearchRequest searchRequest = new SearchRequest("vmivideo");
		searchRequest.types("useronline");

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		//CardinalityAggregationBuilder aggregation = AggregationBuilders.cardinality("distict_userId").field("userId");
		
		TermsAggregationBuilder   appNameAggregation = AggregationBuilders.terms("group_userId").field("userId");
		appNameAggregation.size(10000);
		long low = begin.getTime();
		long hight = end.getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createDate").from(low, true).to(hight, true);

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();  
		boolQueryBuilder.must(matchQueryBuilder);
		
		if(Tools.isNotNull(userType)){
			boolQueryBuilder.must(QueryBuilders.termQuery("userType",userType));
		}
		sourceBuilder.query(boolQueryBuilder);
		sourceBuilder.aggregation(appNameAggregation);
		sourceBuilder.size(10000);
		searchRequest.source(sourceBuilder);
		logger.info("elasticsearch:GET"+EsConst.ES_INDEX_NAME+"/useronline/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = null;
		searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		Aggregations aggregations = searchResponse.getAggregations();
		 Terms byAppNameAggregation = aggregations.get("group_userId");
          for(Bucket buck : byAppNameAggregation.getBuckets()) {
        	  Long userId = Tools.parseLong(buck.getKeyAsString());
        	  userIdList.add(userId);
        	  System.out.println("userid:"+userId);
          }
          
          System.out.println(JsonHelper.toJson(userIdList));
	}
	
	
	
	@Test
	public void testqueryOnlineNum() throws Exception{
		 long value = cacheRedis.incrBy(AgentRedisCacheConst.RECOMMEND_ONLINE_ANCHOR_FALG_PREFIX+Tools.getFormatDate(new Date(), "yyyyMMdd"), 1);
		 logger.info("value================="+value);
	}
	
	@Test
	public void testNextBoolean() throws Exception{
		Random random = new Random();
		for(int i=1;i<100;i++){
			System.out.println(random.nextBoolean());
		}
	}

	
	
	
	@Test
	public void queryTopApp() throws Exception{
		String esType = "mobileapplist";
		int userType = 0 ;
		int size = 10000;
		List<String> appList = null;
	
		String APPLIST_ES_INDEX_NAME = "vmiapplist";
		SearchRequest searchRequest = new SearchRequest(APPLIST_ES_INDEX_NAME);
		searchRequest.types(esType);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		
		BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
		//if(userType>=0){
			//sourceBuilder.query(QueryBuilders.termQuery("userType", userType));
			booleanQueryBuilder.must(QueryBuilders.termQuery("userType", userType));
		//}
		///////////////////////2020.2.5号之后
		Calendar cal = Calendar.getInstance();
		//cal.set(2019,2, 5);
		cal.set(2020,1, 5, 0, 0, 0);
		Long low = cal.getTime().getTime();
		long hight = new Date().getTime();
		RangeQueryBuilder matchQueryBuilder = QueryBuilders.rangeQuery("createTime").from(low, true).to(hight,true);
		booleanQueryBuilder.must(matchQueryBuilder);
		sourceBuilder.query(booleanQueryBuilder);
		
		
		
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
		logger.info("elasticsearch:GET "+APPLIST_ES_INDEX_NAME+"/"+esType+"/_search" +(Tools.isNotNull(searchRequest.source())?searchRequest.source().toString():""));
		SearchResponse searchResponse = null;
		searchResponse = restClientFactory.getHighLevelClient().search(searchRequest, RequestOptions.DEFAULT);
		if (RestStatus.OK.equals(searchResponse.status())) {
			appList = new ArrayList<String>();
			Aggregations aggregations = searchResponse.getAggregations();
			 Terms byAppNameAggregation = aggregations.get("by_appName");
			   logger.info("queryTopApp.aggregation by_appName 结果");
               logger.info("queryTopApp.docCountError: " + byAppNameAggregation.getDocCountError());
               logger.info("queryTopApp.sumOfOtherDocCounts: " + byAppNameAggregation.getSumOfOtherDocCounts());
               BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"));
               for(Bucket buck : byAppNameAggregation.getBuckets()) {
            	 //  out.write(buck.getKeyAsString()+"\t \t"+buck.getDocCount());
            	 //  out.newLine();
            	   appList.add(buck.getKeyAsString());
            	   
            	   Aggregations subAggregations =  buck.getAggregations();
            	   Aggregation ation = subAggregations.get("distict_userId");
            	   Cardinality valueCount = (Cardinality)ation;
            	   out.write(buck.getKeyAsString()+"\t \t"+valueCount.getValue());
            	   out.newLine();
					//System.out.println("valueCount:"+ valueCount.getValue());
            	   //System.out.println("xxxx:"+buck.getKeyAsString()+"   "+valueCount.getValue());
               }
               out.flush();
               out.close();
		}
		
	}
	
	
	@Test
	public void testMobile() throws Exception{
		if (Tools.isMobile(null)) {
			System.out.println("XXXXXX---sucees");
		}else {
			System.out.println("XXXXXX---false");
		}
	}
	
}
