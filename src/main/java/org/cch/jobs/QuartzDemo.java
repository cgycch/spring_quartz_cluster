package org.cch.jobs;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

public class QuartzDemo {
	
	public static void getAllJobs(Scheduler scheduler){
		System.out.println("getAllJobs");
        try {
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    System.out.println("jobName:"+jobName+"  jobGroup:"+jobGroup);
                    @SuppressWarnings("unchecked")
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    for (Trigger trigger : triggers) {
						System.out.println("trigger.getKey():"+trigger.getKey());
						if(trigger instanceof CronTrigger) {
							String cron = ((CronTrigger) trigger).getCronExpression();
							System.out.println("cron:" + cron);
						}
					}
                   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
