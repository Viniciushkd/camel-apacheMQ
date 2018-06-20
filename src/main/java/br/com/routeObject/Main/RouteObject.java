package br.com.routeObject.Main;

import javax.jms.JMSException;

import br.com.routeObject.ProducerRouteObject;
import br.com.routeObject.entity.Entity;

public class RouteObject {

	public static void main(String[] args) {
		ProducerRouteObject routeObject = new ProducerRouteObject();
		Entity entity = new Entity("Vinicius", 10);
	
		try {
			routeObject.sendMessage(entity);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
