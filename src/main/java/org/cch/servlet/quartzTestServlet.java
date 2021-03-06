package org.cch.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cch.InAppBeanFactory;
import org.cch.jobs.AllPushJobScheduler;
import org.cch.jobs.AllPushMessage;

public class quartzTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public quartzTestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AllPushJobScheduler scheduler = null;
		try {
			scheduler = (AllPushJobScheduler) InAppBeanFactory.getBean("allPushJobScheduler");
		} catch (Exception e) {
			response.getWriter().append("The bean JobScheduler has not been initialized. Please check web.xml").flush();
			return;
		}
		
		System.out.println("###################");
		String act = request.getParameter("act");
		Writer writer = response.getWriter();
		writer.append("act: " + act + "  ");

		AllPushMessage allPushMessage = new AllPushMessage();
		allPushMessage.setCronExpression("0/20 * * * * ?");
		allPushMessage.setPush_id("testAdd".toCharArray());
		allPushMessage.setPush_time(new Date());

		if("1".equals(act)) {
			writer.append(" add ");
			scheduler.addJob(allPushMessage);
		}else if("2".equals(act)) {
			writer.append(" delete ");
			try {
				scheduler.deleteJob(allPushMessage);
			} catch (Exception e) {
				writer.append(" delete err " + e.getMessage());
			}
		}else if("3".equals(act)) {
			writer.append(" update ");
			try {
				allPushMessage.setCronExpression("0/30 * * * * ?");
				scheduler.updateJob(allPushMessage);
			} catch (Exception e) {
				writer.append(" updateJob err " + e.getMessage());
			}
		}else if("4".equals(act)) {
			writer.append(" pause ");
			try {
				scheduler.pauseJob(allPushMessage);
			} catch (Exception e) {
				writer.append(" pauseJob err " + e.getMessage());
			}
		}else if("5".equals(act)) {
			writer.append(" restart ");
			try {
				scheduler.resumeJob(allPushMessage);
			} catch (Exception e) {
				writer.append(" resumeJob err " + e.getMessage());
			}
		}
		
		System.out.println("end");
		response.getWriter().flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
