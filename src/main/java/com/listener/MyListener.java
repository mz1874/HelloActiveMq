package com.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component(value = "MyListener")
@Slf4j
public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if (message!=null && message instanceof TextMessage){
            TextMessage textMessage=(TextMessage)message;
//            try {
//                textMessage.acknowledge();
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
            try {
                log.warn("监听器接收到的消息 ---->{}",textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
