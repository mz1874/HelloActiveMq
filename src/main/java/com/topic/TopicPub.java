package com.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;


@Slf4j
public class TopicPub {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicPub.class);

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
            Topic queue =session.createTopic("Topic-wd");
            //创建生产者
            MessageProducer producer = session.createProducer(queue);
            //发送消息
            for (int i=0;i<50;i++){
                TextMessage textMessage = session.createTextMessage("你好"+i);
                //设置目的地
//                textMessage.setJMSDestination();
                //是否持久化 默认持久化
                textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

                //设置优先级  默认为4
                textMessage.setJMSPriority(4);

                //设置消息的失效时间
                textMessage.setJMSExpiration(0);


                /**
                 * void send(
                 *         Destination destination,   //目的地
                 *         Message message,           //消息体
                 *         int deliveryMode,          //是否持久化
                 *         int priority,              //优先级 默认优先级为4 最高为9
                 *         long timeToLive)           //失效时间
                 */
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
