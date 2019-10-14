package com.queue.helloword;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

@Slf4j
public class ActiveMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMqConsumer.class);

    private final static String USERNAME="admin";

    private final static String PASSWORD="admin";

    private final static String URL="tcp://47.102.127.38:61616";

    /**
     * 消费者
     * @param args
     */
    public static void main(String[] args) {
        try {
            //创建连接工厂
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
            //创建连接
            Connection connection = factory.createConnection();
            //开启连接
            connection.start();
            //是否开启事务，签收模式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列
            Queue queue = session.createQueue("QUEUE");
            MessageConsumer consumer = session.createConsumer(queue);
            while (true){
                //receive()若队列中没消息的话则一直等待,消费者一直保持receive(long Time) 保持等待一段时间
                LOGGER.warn("进入");
                TextMessage receive = (TextMessage) consumer.receive();
                if (null !=receive){
                    LOGGER.warn("收到的消息 --- >{}",receive.getText());
                }else {
                    break;
                }
            }
            consumer.close();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
