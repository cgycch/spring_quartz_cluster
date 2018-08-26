package org.cch.utils;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
public class QuartzTaskUtils {
	
    public static String addTask(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
            String cronExp, Class<?> jobClass, JdbcTemplate jdbcTemplate) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            CronTrigger simpleTrigger = newTrigger().withIdentity(triggerName, triggerGroupName)
                    .withSchedule(cronSchedule(cronExp)).startNow().build();
            scheduler.scheduleJob(jobDetail, simpleTrigger);
            scheduler.start();
            System.err.println("定时任务开始执行");
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
            return "相同组内有重名的工作名称或者触发器名称";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "保存定时任务异常";
        }
        return "success";
    }

    private static JobBuilder newJob(Class<?> jobClass) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
            JdbcTemplate jdbcTemplate) {
        try {
            String jobSql = "select * from qrtz_triggers where JOB_NAME = ? and JOB_GROUP = ?";
            List<Map<String, Object>> jobList = jdbcTemplate.queryForList(jobSql, jobName, jobGroupName);
            if (jobList.size() < 1) {
                return "数据库没有该任务";
            }
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            JobKey jobKey = new JobKey(jobName, jobGroupName);
            scheduler.deleteJob(jobKey);// 删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }


}
