package org.cch.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

import lombok.Setter;

@Setter
public class JMSReceiver implements MessageListener {

	private JmsTemplate jmsTemplate;

	public JMSReceiver(JmsTemplate jmsTemplate) {
		super();
		this.jmsTemplate = jmsTemplate;
		System.out.println("JMSReceiver..." + jmsTemplate);
	}

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("reviced msg is:" + (new java.util.Date()) + ":" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void processMessage() {
		Message msg = jmsTemplate.receive(jmsTemplate.getDefaultDestinationName());
		try {
			System.out.println("getJMSType: " + msg.getJMSType());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
