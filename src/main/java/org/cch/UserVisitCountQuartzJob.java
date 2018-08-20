package org.cch;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 统计昨日登录人数(已注册,而且昨日访问过APP的用户人数)类
 * @author ChaiXY
 */
public class UserVisitCountQuartzJob implements Job {
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			System.err.println(new SimpleDateFormat("YYYY-MM-dd-HH:mm:ss").format(new Date()));
		} catch (Exception e) {
		}
	}
}
