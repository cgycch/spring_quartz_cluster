package org.cch.jobs;

import java.io.Serializable;
import java.util.Date;

public class AllPushMessage implements Serializable{
	
	private static final long serialVersionUID = 7318761931811587803L;
	private char[] id;
	private Date date;
	private String cron;

	public Date getPush_time() {
		return date;
	}

	public void setPush_time(Date date) {
		this.date = date;
	}

	public char[] getPush_id() {
		return id;
	}

	public void setPush_id(char[] id) {
		this.id = id;
	}

	public String getCronExpression() {
		return cron;
	}


	public void setCronExpression(String cron) {
		this.cron = cron;
	}
	

}
