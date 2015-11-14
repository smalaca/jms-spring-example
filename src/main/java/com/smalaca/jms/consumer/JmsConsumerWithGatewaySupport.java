package com.smalaca.jms.consumer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

public class JmsConsumerWithGatewaySupport extends JmsGatewaySupport {
    public String receiveMessage() throws JMSException {
        TextMessage textMessage = (TextMessage) getJmsTemplate().receive();
        return textMessage.getText();
    }
}
