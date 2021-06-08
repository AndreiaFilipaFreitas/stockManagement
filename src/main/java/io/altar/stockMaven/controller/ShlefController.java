package io.altar.stockMaven.controller;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.altar.stockMaven.models.Shelf;
import io.altar.stockMaven.repositories.ShelfRepository;
import io.altar.stockMaven.services.ShelfService;

@RequestScoped
@Path("shelves")
public class ShlefController extends EntityController<ShelfService, ShelfRepository, Shelf> {

	@GET
	@Path("available")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shelf> getAvailableShelves() {
		return service.getAvailableShelves();
	}

	@GET
	@Path("all")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllShelves() {
		return service.getAllShelves().toString();
	}

	@GET
	@Path("byProdId/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getShelvesByProdId(@PathParam("id") long id) {
		String error = validateProdId(id);
		if (error.equals("")) {
			return Response.ok(service.getShelvesByProdId(id)).build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}

	@GET
	@Path("howMany")
	@Produces(MediaType.APPLICATION_JSON)
	public long count() {
		return service.getShelvesCount();
	}

	@GET
	@Path("allIds")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllShelvesIds() {
		return service.getAllShelvesIds().toString();
	}

	@PUT
	@Path("byProdId/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response clearAllShelvesByProdId(@PathParam("id") long id) {
		String error = validateProdId(id);
		if (error.equals("")) {
			return Response.ok(service.clearAllShelvesByProdId(id)).build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}

	@PUT
	@Path("addProd/{shelfId}/{prodId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProdToShelf(@PathParam("shelfId") long shelfId, @PathParam("prodId") long prodId) {
		String error = validateAvailability(service.checkEntity(shelfId), shelfId, prodId);
		if (error.equals("")) {
			return Response.ok(service.addProdToShelf(shelfId, prodId)).build();
		} else {
			return Response.status(422).entity(error).build();
		}
	}

	@GET
	@Path("empty")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shelf> getEmptyShelves() {
		return service.getEmptyShelves();
	}

	@GET
	@Path("full")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shelf> getFullShelves() {
		return service.getFullShelves();
	}

	@GET
	@Path("availableIds")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAvailableShelvesIds() {
		return service.getAvailableShelvesIds().toString();
	}

	@Override
	public String validateEntity(Shelf s) {
		String result = "";
		if (s.getCap() > 1) {
			result = "Capacidade inválida. Por favor escolha 0 ou 1.";
		};
		if (s.getProd() != null) {
			if (s.getCap() == 0 && s.getProd().getId() > 0) {
				result = "A prateleira não tem capacidade.";
			};
			if (s.getCap() == 1 && s.getProd().getId() > 0) {
				List<Long> prods = service.getProdsIds();
				if (!prods.contains(s.getProd().getId())){
					result = " O produto indicado não é válido.";
				};
			};
		}
		return result;
	}
	
	@Override
	public String validateId(long id) {
		String result = "";
		List<Long> shelves = service.getAllShelvesIds();
		if (!shelves.contains(id)) {
			result += " A prateleira indicada não é válida.";
		}
		return result;
	}

	public String validateAvailability(Shelf s, long shelfId, long prodId) {
		String result = "";
		List<Long> shelves = service.getAllShelvesIds();
		List<Long> prods = service.getProdsIds();
		if (!shelves.contains(shelfId)) {
			result += "A prateleira indicada não é válida.";
		} else {
			if (s.getCap() == 0 || s.getProd() != null) {
				result = "A prateleira indicada não tem capacidade.";
			}
			;
		}
		;
		if (!prods.contains(prodId)) {
			result += " O produto indicado não é válido.";
		}
		return result;
	}

	public String validateProdId(long id) {
		String result = "";
		List<Long> prods = service.getProdsIds();
		if (!prods.contains(id)) {
			result += " O produto indicado não é válido.";
		}
		return result;
	}
}
