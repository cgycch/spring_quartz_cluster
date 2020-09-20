package org.cch.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cch.InAppBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;

public class QuartzDbServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String sql_qrtz_fired_triggers = "select * from qrtz_fired_triggers";
	private static final String sql_qrtz_simple_triggers = "select * from qrtz_simple_triggers";
	private static final String sql_qrtz_simprop_triggers = "select * from qrtz_simprop_triggers";
	private static final String sql_qrtz_cron_triggers = "select * from qrtz_cron_triggers";
	private static final String sql_qrtz_blob_triggers = "select * from qrtz_blob_triggers";
	private static final String sql_qrtz_triggers = "select * from qrtz_triggers";
	private static final String sql_qrtz_job_details = "select * from qrtz_job_details";
	private static final String sql_qrtz_calendars = "select * from qrtz_calendars";
	private static final String sql_qrtz_paused_trigger_grps = "select * from qrtz_paused_trigger_grps";
	private static final String sql_qrtz_scheduler_state = "select * from qrtz_scheduler_state";

	Gson gson = null;
       
    public QuartzDbServlet() {
        super();
		gson = new Gson();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = gson.toJson("success");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) InAppBeanFactory.getBean("jdbcTemplate");
		String act = request.getParameter("act");
		if ("listAll".equals(act)) {
			List<Map<String, Object>> fired_triggersMap = jdbcTemplate.queryForList(sql_qrtz_fired_triggers);
			List<Map<String, Object>> simple_triggersMap = jdbcTemplate.queryForList(sql_qrtz_simple_triggers);
			List<Map<String, Object>> simprop_triggersMap = jdbcTemplate.queryForList(sql_qrtz_simprop_triggers);
			List<Map<String, Object>> cron_triggersMap = jdbcTemplate.queryForList(sql_qrtz_cron_triggers);
			List<Map<String, Object>> blob_triggersMap = jdbcTemplate.queryForList(sql_qrtz_blob_triggers);
			List<Map<String, Object>> triggersMap = jdbcTemplate.queryForList(sql_qrtz_triggers);
			List<Map<String, Object>> job_detailsMap = jdbcTemplate.queryForList(sql_qrtz_job_details);
			List<Map<String, Object>> calendarsMap = jdbcTemplate.queryForList(sql_qrtz_calendars);
			List<Map<String, Object>> paused_trigger_grpsMap = jdbcTemplate.queryForList(sql_qrtz_paused_trigger_grps);
			List<Map<String, Object>> scheduler_stateMap = jdbcTemplate.queryForList(sql_qrtz_scheduler_state);

			Map<String, Object> data = new HashMap<>();
			data.put("fired_triggersMap", fired_triggersMap);

			data.put("simple_triggersMap", simple_triggersMap);

			data.put("simprop_triggersMap", simprop_triggersMap);

			data.put("cron_triggersMap", cron_triggersMap);

			data.put("blob_triggersMap", blob_triggersMap);

			data.put("triggersMap", triggersMap);

			data.put("job_detailsMap", job_detailsMap);

			data.put("calendarsMap", calendarsMap);

			data.put("paused_trigger_grpsMap", paused_trigger_grpsMap);

			data.put("scheduler_stateMap", scheduler_stateMap);
			result = gson.toJson(data);
		}
		response.getWriter().append(result);
	}


}
