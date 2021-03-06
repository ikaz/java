package de.vogella.jersey.jaxb.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Test2 {

	public static void main(String[] args){
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		
		WebResource svc = client.resource(getBaseURI());
		System.out.println(svc.path("rest").path("todo2")
				.accept(MediaType.TEXT_XML));
		System.out.println(svc.path("rest").path("todo2")
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		System.out.println(svc.path("rest").path("todo2")
				.accept(MediaType.APPLICATION_XML).get(String.class));
	}

	private static URI getBaseURI() {
		return 	UriBuilder.fromUri(
				"http://localhost:8080/mx.ikaz.jersey.jaxb").build();
	}
}
