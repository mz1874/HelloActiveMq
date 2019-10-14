package com.topic.lasting;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 生产者模型
 */

@Slf4j
public class TopicPub {
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://47.102.127.38:61616");

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);

        Topic topic_wd = session.createTopic("TOPIC_WD");

        MessageProducer producer = session.createProducer(topic_wd);

        //设置持久化
        producer.setPriority(DeliveryMode.PERSISTENT);
        for (int i=1;i<=100;i++){
            TextMessage String = session.createTextMessage("王迪是而较简洁只猪"+i);
            producer.send(String);
        }
        session.commit();

        producer.close();

        connection.close();

        log.warn("消息发送完毕");
    }

}
