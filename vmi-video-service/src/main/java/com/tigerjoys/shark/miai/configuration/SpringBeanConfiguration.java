package com.tigerjoys.shark.miai.configuration;

import org.shark.miai.common.cloud.storage.MinioCloudStorage;
import org.shark.miai.common.cloud.storage.UpYunCloudStorage;
import org.shark.miai.common.cloud.upyun.UpYunCloudVideoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tigerjoys.shark.miai.BootstrapListener;
import com.tigerjoys.shark.miai.es.configuration.ElasticSearchPoolConfig;
import com.tigerjoys.shark.miai.es.configuration.RestClientFactory;

import io.minio.errors.MinioException;

/**
 * 常用的Bean实体配置
 * @author chengang
 *
 */
@Configuration
public class SpringBeanConfiguration {
	
	@Bean
	public BootstrapListener bootstrapListener() {
		return new BootstrapListener();
	}
	
	/**
	 * 又拍云存储对象
	 * @param properties - StorageUpaiyunProperties
	 * @return UpYunCloudStorage
	 */
	@Bean
	public UpYunCloudStorage upYunCloudStorage(StorageUpaiyunProperties properties) {
		return new UpYunCloudStorage(properties.getBucketname(), properties.getUsername(), properties.getPassword());
	}
	
	/**
	 * 又拍云存储对象
	 * @param properties - StorageUpaiyunProperties
	 * @return UpYunCloudVideoHandler
	 */
	@Bean
	public UpYunCloudVideoHandler upYunCloudVideoHandler(StorageUpaiyunProperties properties) {
		return new UpYunCloudVideoHandler(properties.getBucketname(), properties.getUsername(), properties.getPassword());
	}
	
	/**
	 * Minio存储对象
	 * @param properties - StorageMinioProperties
	 * @return MinioCloudStorage
	 * @throws MinioException
	 */
	@Bean
	public MinioCloudStorage minioCloudStorage(StorageMinioProperties properties) throws MinioException {
		return new MinioCloudStorage(properties.getEndpoint(), properties.getBucketname(), properties.getUsername(), properties.getPassword());
	}
	
	/**
	 * Elastic对象
	 * @param properties - ElasticsearchProperties
	 * @return RestClientFactory
	 */
	@Bean
	public RestClientFactory restClientFactory(ElasticsearchProperties properties) {
		ElasticSearchPoolConfig config = new ElasticSearchPoolConfig();
		config.setHost(properties.getHost());
		config.setPort(properties.getPort());
		config.setUser(properties.getUser());
		config.setPassword(properties.getPassword());
		config.setSchema(properties.getSchema());
		config.setConnectTimeOut(properties.getConnectTimeOut());
		config.setSocketTimeOut(properties.getSocketTimeOut());
		config.setConnectionRequestTimeOut(properties.getConnectionRequestTimeOut());
		config.setMaxConnectNum(properties.getMaxConnectNum());
		config.setMaxConnectPerRoute(properties.getMaxConnectPerRoute());
		
		return new RestClientFactory(config);
	}

}
