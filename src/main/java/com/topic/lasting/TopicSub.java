package com.topic.lasting;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Slf4j
public class TopicSub {
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://47.102.127.38:61616");

        Connection connection = connectionFactory.createConnection();

        connection.setClientID("MZ");

        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //订阅者的名字

        //订阅哪个队列
        Topic topicWd = session.createTopic("TOPIC_WD");

        //订阅
        TopicSubscriber topicSubscriber=session.createDurableSubscriber(topicWd,"备注");
        connection.start();

        Message receive = topicSubscriber.receive();
        while (null != receive){
            TextMessage textMessage=(TextMessage)receive;
            log.warn("获取的消息------->{}",textMessage.getText());
            receive=topicSubscriber.receive(10000L);
        }
        session.close();
        connection.close();
        log.warn(Thread.currentThread().getName());
        log.warn("主线程结束 -----");
    }
}
