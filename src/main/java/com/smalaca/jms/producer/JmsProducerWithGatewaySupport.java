package com.smalaca.jms.producer;

import com.smalaca.jms.domain.PersonDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

public class JmsProducerWithGatewaySupport extends JmsGatewaySupport {
    public void sendMessage(final String msg) {
        System.out.println("Producer sends " + msg);

        getJmsTemplate().send(session -> session.createTextMessage(msg));
    }

    public void sendMessage(PersonDTO person) {
        System.out.println("Producer sends " + person);
        getJmsTemplate().convertAndSend(person);
    }
}
