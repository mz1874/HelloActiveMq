package com.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Slf4j
@Component
public class Spring_ActiveMQPubs {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("application.xml");
        Spring_ActiveMQPubs bean = applicationContext.getBean(Spring_ActiveMQPubs.class);
        bean.jmsTemplate.send(session -> {
        TextMessage hello = session.createTextMessage("你好");
        return hello;
    });
     log.warn("发送消息完毕 ------------");
}
}
