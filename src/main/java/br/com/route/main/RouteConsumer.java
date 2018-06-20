package br.com.route.main;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.builder.RouteBuilder;

public class RouteConsumer {

	public static void main(String[] args) throws Exception {
		CamelContext ctx = new DefaultCamelContext();

		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
		ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(factory));
		
		ctx.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("jms:queue:route").to("stream:out").to("file:C:\\Development\\routeOut");
			}
		});
		
		ctx.start();
		Thread.sleep(5 * 60 * 1000);
		ctx.stop();

	}
}
