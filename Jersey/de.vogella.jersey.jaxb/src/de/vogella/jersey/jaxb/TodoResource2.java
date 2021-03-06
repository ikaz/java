package de.vogella.jersey.jaxb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.vogella.jersey.jaxb.model.Todo2;

@Path("todo2")
public class TodoResource2 {
	
	@GET 
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Todo2 getXML() {
		Todo2 todo2 = new Todo2();
		todo2.setSummary("This is my second to do");
		todo2.setDescription("The description");
		return todo2;
	}
	
	@GET
	@Produces({ MediaType.TEXT_XML})
	public Todo2 getHTML(){
		Todo2 t2 = new Todo2();
		t2.setSummary("Second summary");
		t2.setDescription("2nd todo description");
		return t2;
	}	
}