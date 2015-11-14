package com.smalaca.jms.consumer;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

public class JmsConsumer {
    private Destination destination;
    private JmsTemplate jmsTemplate;

    public JmsConsumer(Destination destination, JmsTemplate jmsTemplate) {
        this.destination = destination;
        this.jmsTemplate = jmsTemplate;
    }

    public String receiveMessage() throws JMSException {
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        return textMessage.getText();
    }
}
