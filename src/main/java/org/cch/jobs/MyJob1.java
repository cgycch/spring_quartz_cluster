package org.cch.jobs;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class MyJob1 extends QuartzJobBean {
	 
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("MyJob1: getFireInstanceId= "+context.getFireInstanceId());
    	System.out.println("MyJob1: getJobInstance= "+context.getJobInstance());
    	System.out.println("MyJob1: getRefireCount= "+context.getRefireCount());
    	System.out.println("MyJob1:"+new SimpleDateFormat("YYYY-MM-dd-HH:mm:ss").format(new Date()));
    	System.out.println("");
    }
}
