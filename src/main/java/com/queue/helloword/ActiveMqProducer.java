package com.queue.helloword;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


@Slf4j
public class ActiveMqProducer{

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMqProducer.class);

    private final static String USERNAME="admin";

    private final static String PASSWORD="admin";

    private final static String URL="tcp://47.102.127.38:61616";

    /**
     * 生产者
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
            //创建生产者
            MessageProducer producer = session.createProducer(queue);
            //发送消息
            for (int i=0;i<50;i++){
                TextMessage textMessage = session.createTextMessage("你好"+i);
                producer.send(textMessage);
            }
            producer.close();
            connection.close();
            LOGGER.warn("消息投递成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
