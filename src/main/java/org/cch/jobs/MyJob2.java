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
public class MyJob2 extends QuartzJobBean {
	 
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    	System.out.println("MyJob2222:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
