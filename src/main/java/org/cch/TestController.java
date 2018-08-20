package org.cch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class TestController {

	@Autowired
	protected JdbcTemplate jdbcTemplate;// jdbc模版类

	@RequestMapping("/addTask")
	@ResponseBody
	public String addTask(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		String cronExp = "0/5 * * * * ?";// 每隔5秒执行一次
		Class jobClass = UserVisitCountQuartzJob.class;
		String result = QuartzTaskUtils.addTask(jobName, jobGroupName, triggerName, triggerGroupName, cronExp, jobClass,
				jdbcTemplate);
		return result;
	}

	@RequestMapping("/removeTask")
	@ResponseBody
	public String removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		String result = QuartzTaskUtils.removeJob(jobName, jobGroupName, triggerName, triggerGroupName, jdbcTemplate);
		return result;
	}

}