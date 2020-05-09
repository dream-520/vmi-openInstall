package com.tigerjoys.shark.miai.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.tigerjoys.nbs.common.utils.NameableThreadFactory;
import com.tigerjoys.nbs.common.utils.SystemUtils;

/**
 * 定时器配置
 * @author chengang
 *
 */
@EnableScheduling
@Configuration
@ConditionalOnProperty(name="myapp.config.scheduler.enabled" , havingValue = "true")
public class SchedulerConfiguration implements SchedulingConfigurer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerConfiguration.class);
	
	/**
	 * 实现自定义的定时器线程池
	 * @return Executor
	 */
	@Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
		LOGGER.info("======================初始化自定义的线程池========================");
		
		//创建自定义线程池
		return Executors.newScheduledThreadPool(SystemUtils.getCpuProcessorCount(), new NameableThreadFactory("[Scheduled Pool] ---- ", false));
    }

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskScheduler());
	}

}
