package com.tigerjoys.shark.miai.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@SuppressWarnings("deprecation")
@Configuration
public class VelocityConfiguration {
	
	/**
     * velocity 解析器配置
     * @return
     */
	@Bean
    VelocityViewResolver viewResolver(){
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setSuffix(".html");
        resolver.setPrefix("/views/");
        resolver.setCache(false);
        resolver.setExposeSessionAttributes(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setRequestContextAttribute("rc");
        resolver.setDateToolAttribute("dateTool");
        resolver.setNumberToolAttribute("numberTool");
        return resolver;
    }

    /**
     * velocity 引擎配置
     * @return
     */
    @Bean
    VelocityConfigurer velocityConfigurer(){
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        Properties properties = new Properties();
        properties.setProperty("input.encoding","UTF-8");
        properties.setProperty("output.encoding","UTF-8");
        velocityConfigurer.setConfigLocation(new ClassPathResource("/velocity/velocity.properties"));
        velocityConfigurer.setVelocityProperties(properties);
        velocityConfigurer.setResourceLoaderPath("/views/");
        return velocityConfigurer;
    }

}
