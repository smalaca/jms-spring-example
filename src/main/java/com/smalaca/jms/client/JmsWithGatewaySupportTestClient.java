package com.smalaca.jms.client;

import com.smalaca.jms.consumer.JmsConsumer;
import com.smalaca.jms.consumer.JmsConsumerWithGatewaySupport;
import com.smalaca.jms.producer.JmsProducer;
import com.smalaca.jms.producer.JmsProducerWithGatewaySupport;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URI;

public class JmsWithGatewaySupportTestClient {

    public static void main(String[] args) throws Exception {
        BrokerService broker = aBrokerService();
        broker.start();

        Context context = new Context("app-context-with-jms-gateway-support.xml");

        try {
            context.getProducer().sendMessage("Bla bla bla");
            System.out.println("Consumer receives " + context.getConsumer().receiveMessage());
        } finally {
            broker.stop();
            context.close();
        }
    }

    private static BrokerService aBrokerService() throws Exception {
        return BrokerFactory.createBroker(new URI("broker:(tcp://127.0.0.1:61616)"));
    }

    private static class Context {
        private ClassPathXmlApplicationContext context;

        private Context(String contextFileName) {
            context = new ClassPathXmlApplicationContext(contextFileName);
        }

        private JmsProducerWithGatewaySupport getProducer() {
            return (JmsProducerWithGatewaySupport) context.getBean("jmsProducer");
        }

        private JmsConsumerWithGatewaySupport getConsumer() {
            return (JmsConsumerWithGatewaySupport) context.getBean("jmsConsumer");
        }

        public void close() {
            context.close();
        }
    }
}
