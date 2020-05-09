/**
 * 
 */
package com.tigerjoys.shark.miai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.tigerjoys.nbs.common.ServiceConfig;



/**
 * ClassName: BootstrapListener <br/>
 * Function: 当spring启动的时候自动加载onApplicationEvent <br/>
 * date: 2016年11月17日 下午7:07:02 <br/>
 * 
 * @author mouzhanpeng
 * @version
 * @since JDK 1.8.0
 */
public class BootstrapListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(BootstrapListener.class);
	// spring 上下文
	private static ApplicationContext context;

	private ServiceConfig serviceConfig = ServiceConfig.getInstance();


//	// 分发服务包名
//	private static final String SERVICE_PACKAGE_PATH = "com.tigerjoys.orca.gateway.dispatcher.service";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// root application context 没有parent，他就是老大.
		// 防止web项目下这个被执行两次
		ApplicationContext applicationContext = event.getApplicationContext();
		if (applicationContext.getParent() == null) {
			logger.info("start service . . .");
			try {
				context = applicationContext;
			} catch (Exception e) {
				logger.error("start  failed . . .", e);
			}
			logger.info("service started . . .");
		}
	}

	

	/**
	 * 获取spring上下文
	 * 
	 * @return
	 */
	public static ApplicationContext getSpringContext() {
		return context;
	}


}
