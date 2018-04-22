package com.spring.quartz.springquartz.myjob;

import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class CheckStatusTask implements Job {

    private Logger log = Logger.getLogger(CheckStatusTask.class);


    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("===============定时刷新CheckStatusTask");
    }

}
