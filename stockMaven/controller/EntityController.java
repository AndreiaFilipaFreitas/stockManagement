package io.altar.stockMaven.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.stockMaven.models.Entity_;
import io.altar.stockMaven.repositories.EntityRepository;
import io.altar.stockMaven.services.EntityService;

public abstract class EntityController<S extends EntityService<R,E>, R extends EntityRepository<E>, E extends Entity_> {

	@Inject
	protected S service;
	
	public abstract String validateEntity(E e);
	public abstract String validateId(long id);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createEntity (E entity) { 
		String error = validateEntity(entity);
		if (error.equals("")) {
			return Response.ok(service.createEntity(entity)).build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}
	
	@GET
	@Path("/{ID}")
	@Produces(MediaType.TEXT_PLAIN)
	public E checkEntity(@PathParam("ID")long ID) { 
		return service.checkEntity(ID);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public E change(E entity) { 
		return service.change(entity);
	}
	
	@DELETE
	@Path("/{ID}")
	public Response deleteEntity(@PathParam("ID")long ID) {
		String error = validateId(ID);
		if (error.equals("")) {
			service.deleteEntity(ID);
			return Response.ok("Removido com sucesso!").build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}
}
