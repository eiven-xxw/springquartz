package com.spring.quartz.springquartz.configuration;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

@Configuration
public class QuartzConfig {
    public static final String QUARTZ_PROPERTIES_PATH = "/quartz.properties";

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //schedulerFactoryBean.setJobFactory(jobFactory);
        //schedulerFactoryBean.setStartupDelay(20);
        //用于quartz集群,加载quartz数据源配置
        Object aa = quartzProperties();
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        return schedulerFactoryBean;
    }

    public Properties quartzProperties(){
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_PATH));
        try {
            factoryBean.afterPropertiesSet();
            return factoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}