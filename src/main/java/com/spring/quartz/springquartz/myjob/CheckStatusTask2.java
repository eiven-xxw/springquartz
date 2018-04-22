package com.spring.quartz.springquartz.myjob;

import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class CheckStatusTask2 implements Job {

    private Logger log = Logger.getLogger(CheckStatusTask2.class);


    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("===============checkStatus2 开始==============");
    }

}
