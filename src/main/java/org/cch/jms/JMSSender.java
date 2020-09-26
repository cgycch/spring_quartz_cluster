package org.cch.jms;

import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.cch.InAppBeanFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import lombok.Setter;

@Setter
public class JMSSender {

	private JmsTemplate jmsTemplate;

	public JMSSender(JmsTemplate jmsTemplate) {
		super();
		this.jmsTemplate = jmsTemplate;
		System.out.println("JMSSender..." + jmsTemplate);
	}

	public void send() {
		System.out.println("message sent by JMSSender's " + jmsTemplate);
		if (jmsTemplate == null) {
			reCreateJmsTemplate();
			jmsTemplate.convertAndSend("hello " + new Date());
		} else {
			jmsTemplate.convertAndSend("hello " + new Date());
			jmsTemplate = null;
		}

	}

	private void reCreateJmsTemplate() {
		ConnectionFactory connectionFactory = InAppBeanFactory.getBean(CachingConnectionFactory.class);
		Destination destination = (Destination) InAppBeanFactory.getBean("demoQueueDestination");
		jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(destination);
		System.out.println("recreate jmsTemplate..." + jmsTemplate);
	}
}
