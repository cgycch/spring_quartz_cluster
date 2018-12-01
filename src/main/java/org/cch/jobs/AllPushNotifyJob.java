package org.cch.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AllPushNotifyJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("hello: "+ DateUtils.format(new Date(), "HH:mm:ss"));
    }
}
