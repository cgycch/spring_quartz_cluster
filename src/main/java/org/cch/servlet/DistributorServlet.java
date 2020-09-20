package org.cch.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cch.demo.DbTimeOutTest;

public class DistributorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DistributorServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = "success";
		String act = request.getParameter("act");

		if ("test_db_time_out".equals(act)) {
			String timeout = request.getParameter("timeout");
			try {
				DbTimeOutTest.test(timeout);
			} catch (SQLException e) {
				System.out.println("error....");
				e.printStackTrace();
			}
		} else {
			System.out.println("param act is not exist or not match: " + act);
		}
		response.getWriter().append(result);
	}

}
