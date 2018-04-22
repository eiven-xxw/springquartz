package com.spring.quartz.springquartz.configuration;

import com.spring.quartz.springquartz.myjob.CheckStatusTask;
import com.spring.quartz.springquartz.myjob.CheckStatusTask2;
import org.jboss.logging.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import javax.annotation.PostConstruct;

@Component
public class QuartzScheduler {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private Logger logger = Logger.getLogger(QuartzScheduler.class);


    @PostConstruct
    public void init() throws SchedulerException {
        scheduleJobs();
    }

    public void scheduleJobs() throws SchedulerException {
        logger.info("=======任务初始化========");
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //需要传递数据,就是使用JobDataMa
        //       JobDataMap jobDataMap = new JobDataMap();
        //       jobDataMap.put("jobArg", "world");
        //CheckStatusTask.class 是需要执行定时任务的类名
        JobDetail jobDetail = JobBuilder.newJob(CheckStatusTask.class)
                //              .setJobData(jobDataMap)
                .withDescription("CheckStatusTask")
                .withIdentity("job-CheckStatus", "demo-group")
                .build();
        //InsertEvaluateTask.class 是需要执行定时任务的类名
        JobDetail jobDetail2 = JobBuilder.newJob(CheckStatusTask2.class)
                //            .setJobData(jobDataMap)
                .withDescription("InsertEvaluateTask")
                .withIdentity("job-InsertEvaluate", "demo-group")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(cronSchedule("*/5 * * * * ? "))
                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .forJob(jobDetail2)
                .withSchedule(cronSchedule("*/8 * * * * ? "))
                .build();

        try {
            if(!scheduler.checkExists(JobKey.jobKey("job-CheckStatus","demo-group"))){
                scheduler.scheduleJob(jobDetail,trigger);
            }
            if(!scheduler.checkExists(JobKey.jobKey("job-InsertEvaluate","demo-group"))){
                scheduler.scheduleJob(jobDetail2,trigger2);
            }
            scheduler.start();
            logger.info("=======任务初始化完成========");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}