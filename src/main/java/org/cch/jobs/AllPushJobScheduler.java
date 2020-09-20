package org.cch.jobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class AllPushJobScheduler {

	private static final Log logger = LogFactory.getLog(AllPushJobScheduler.class);
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	public AllPushJobScheduler() {
		System.out.println("AllPushJobScheduler()");
	}
	
	/**
	 * 添加任务
	 * 
	 * @param allPushMessage
	 */
	public void addJob(AllPushMessage allPushMessage) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = getJobKey(allPushMessage);
		try {
			if (scheduler.checkExists(jobKey)) {
				logger.info("all push job existed!:" + jobKey.getName());
				return;
			}
		} catch (SchedulerException e) {
			logger.error("get exception:" + e.getMessage(), e);
		}
		logger.info("schedule with job name"+ jobKey.getName());
		JobDetail notifyJob = JobBuilder.newJob(AllPushNotifyJob.class).withIdentity(jobKey).build();
		try {
			
			CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(allPushMessage))
					// .forJob(notifyJob)
					.withSchedule(CronScheduleBuilder.cronSchedule(allPushMessage.getCronExpression()))
                    .build();
			scheduler.scheduleJob(notifyJob, trigger);
			if(scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (SchedulerException e) {
			logger.error("get exception when executing quartz job" + e);
		}

	}
	
	/**
	 * 更新定时任务
	 * 
	 * @param
	 * @param
	 * @throws Exception
	 */
	public void updateJob(AllPushMessage allPushMessage) throws Exception {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = getTriggerKey(allPushMessage);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(allPushMessage.getCronExpression());
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			System.out.println("更新定时任务失败" + e);
			throw new Exception("更新定时任务失败");
		}
	}

	/**
	 * 删除任务
	 * 
	 * @param allPushMessage
	 * @throws Exception
	 */
	public void deleteJob(AllPushMessage allPushMessage) throws Exception {

		try {
			// 删除定时任务时 先暂停任务，然后再删除
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = getJobKey(allPushMessage);
			scheduler.pauseJob(jobKey);
			scheduler.deleteJob(jobKey);
		} catch (Exception e) {
			System.out.println("删除定时任务失败" + e);
			throw new Exception("删除定时任务失败");
		}
	}

	/**
	 * 获取jobKey
	 * 
	 * @param allPushMessage
	 * @return
	 */
	public JobKey getJobKey(AllPushMessage allPushMessage) {
		return JobKey.jobKey(String.valueOf(allPushMessage.getPush_id()));
	}

	/**
	 * 获取TriggerKey
	 *
	 * @param
	 * @param
	 * @return
	 */
	public static TriggerKey getTriggerKey(AllPushMessage allPushMessage) {
		return TriggerKey.triggerKey(String.valueOf(allPushMessage.getPush_id()));
	}

	

	/**
	 * 暂停定时任务
	 * 
	 * @param allPushMessage
	 * @throws Exception
	 */
	public void pauseJob(AllPushMessage allPushMessage) throws Exception {

		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = getJobKey(allPushMessage);
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			System.out.println("暂停定时任务失败" + e);
			throw new Exception("暂停定时任务失败");
		}
	}

	/**
	 * 恢复任务
	 * 
	 * @param
	 * @param
	 * @param
	 * @throws Exception
	 */
	public void resumeJob(AllPushMessage allPushMessage) throws Exception {

		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = getJobKey(allPushMessage);
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			System.out.println("恢复定时任务失败" + e);
			throw new Exception("恢复定时任务失败");
		}
	}

	/**
	 * 运行一次任务
	 * 
	 * @param allPushMessage
	 * @throws Exception
	 */
	public void runOnce(AllPushMessage allPushMessage) throws Exception {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = getJobKey(allPushMessage);
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			System.out.println("运行任务失败" + e);
			throw new Exception("运行一次定时任务失败");
		}
	}


	public void testAdd() {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey("myJob1", "myGroup1");
			if (scheduler.checkExists(jobKey)) {
				logger.info("all push job existed!:" + jobKey.getName());
				return;
			}
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(MyJob1.class).withIdentity(jobKey).build();

			// 触发器
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组
			triggerBuilder.withIdentity("triggerName1", "triggerGroupName1");
			// triggerBuilder.startNow();
			// 触发器时间设定
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();

			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);

			// 启动
			if (scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
