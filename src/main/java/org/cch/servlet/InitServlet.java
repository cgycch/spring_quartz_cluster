package org.cch.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.cch.utils.PropManager;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
		String ServerInfo = config.getServletContext().getServerInfo();
		System.out.println("PropManager setting....on Server: " + ServerInfo);
		PropManager.setProp("ServerInfo", ServerInfo);
    }

}
