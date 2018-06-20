package br.com.route;

public class RouteBuilder extends org.apache.camel.builder.RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("file:C:\\Development\\route").split().tokenize("\n").to("jms:queue:route");
	}
	
}
