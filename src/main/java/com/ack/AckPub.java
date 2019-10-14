package com.ack;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Slf4j
public class AckPub {
    private final static String URL="tcp://47.102.127.38:61616";

    public static void main(String[] args) throws JMSException {


        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);

        Queue queue=session.createQueue("QUEUE");

        MessageProducer producer = session.createProducer(queue);

        TextMessage textMessage = session.createTextMessage("你大爷的王迪");

        producer.send(textMessage);

        session.commit();

        producer.close();

        connection.close();

    }
}
