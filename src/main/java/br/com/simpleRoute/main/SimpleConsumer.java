package br.com.simpleRoute.main;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SimpleConsumer {
	public static void main(String[] args) throws JMSException {
		Connection connection = null;
		MessageConsumer consumer = null;
		Session session = null;
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
			connection = factory.createConnection();
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("Message");

			consumer = session.createConsumer(destination);
			Message message = consumer.receive(1000);

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Mensagem recebida: " + text);
			} else {
				System.out.println("Mensagem não recebida.");
			}

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			consumer.close();
			connection.close();
			session.close();			
		}
	}
}
