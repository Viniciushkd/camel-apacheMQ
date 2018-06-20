package br.com.simpleRoute.main;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SimpleProducer {

	public static void main(String[] args) throws JMSException {
		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
			connection = factory.createConnection();
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("Message");
			
			MessageProducer messageProducer = session.createProducer(destination);
			messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			String message = "Teste de Route Message.";
			TextMessage textMessage = session.createTextMessage(message);
			
			System.out.println("Enviando mensagem: " + textMessage.hashCode());
			messageProducer.send(textMessage);
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			session.close();
			connection.close();
		}
	}
}
