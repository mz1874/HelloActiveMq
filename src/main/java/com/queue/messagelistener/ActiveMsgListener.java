package com.queue.messagelistener;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

@Slf4j
public class ActiveMsgListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMsgListener.class);

    private final static String USERNAME="admin";

    private final static String PASSWORD="admin";

    private final static String URL="tcp://47.102.127.38:61616";

    /**
     * 消费者
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.warn("*********我是一号消费者************");
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

            /**
             * 带有监听的消费者
             */
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    if (null != message && message instanceof TextMessage){
                        TextMessage textMessage= (TextMessage) message;
                        try {
                            LOGGER.warn("获取的消息 ---- >{}",textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });

            System.in.read();

            consumer.close();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
