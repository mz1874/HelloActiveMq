package com.ack;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

@Slf4j
public class AckSub {
    private final static String URL="tcp://47.102.127.38:61616";

    public static void main(String[] args) throws JMSException {


        ActiveMQConnectionFactory connectionFactory=new ActiveMQConnectionFactory(URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Queue queue=session.createQueue("QUEUE");

        MessageConsumer consumer = session.createConsumer(queue);


        consumer.setMessageListener(
                (message -> {
                 TextMessage textMessage= (TextMessage) message;
                    try {
                        log.warn(textMessage.getText()+"\t");
                        textMessage.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                })
        );

        session.commit();


        connection.close();

    }
}
