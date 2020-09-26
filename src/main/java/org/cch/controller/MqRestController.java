package org.cch.controller;

import java.util.Date;

import org.cch.jms.JMSSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/mq")
public class MqRestController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private JMSSender sender;

	@RequestMapping("")
	public String sendMsg() {
		System.out.println("message sent by MqRestController's " + jmsTemplate);
		jmsTemplate.convertAndSend("hello " + new Date());
		return "AAA";
	}

	@RequestMapping("/sender")
	public String sendMsg2() {
		sender.send();
		return "BBB";
	}
}
