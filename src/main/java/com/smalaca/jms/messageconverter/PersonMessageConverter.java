package com.smalaca.jms.messageconverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import com.smalaca.jms.domain.PersonDTO;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class PersonMessageConverter implements MessageConverter{

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        PersonDTO person = (PersonDTO) object;
        MapMessage message = session.createMapMessage();
        message.setString("name", person.getName());
        message.setInt("age", person.getAge());

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        MapMessage mapMessage = (MapMessage) message;

        return aPersonDTO(mapMessage);
    }

    private PersonDTO aPersonDTO(MapMessage mapMessage) throws JMSException {
        return new PersonDTO(mapMessage.getString("name"), mapMessage.getInt("age"));
    }
}
