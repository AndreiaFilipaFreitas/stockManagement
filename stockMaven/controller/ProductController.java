package io.altar.stockMaven.controller;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.stockMaven.models.Product;
import io.altar.stockMaven.repositories.ProductRepository;
import io.altar.stockMaven.services.ProductService;

@RequestScoped
@Path("products")
public class ProductController extends EntityController<ProductService, ProductRepository, Product> {

	@GET
	@Path("discount")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDiscountProducts() { 
		return service.getDiscountProducts().toString();
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllProducts() { 
		return service.getAllProducts().toString();
	}
	
	@GET
	@Path("byLowPrice/{price}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductsByLowPrice(@PathParam("price")double price) { 
		return service.getProductsByLowPrice(price).toString();
	}
	
	@GET
	@Path("howMany")
	@Produces(MediaType.APPLICATION_JSON)
	public long count() {
		return service.getProductsCount();
	}
	
	@GET
	@Path("allIds")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllProductsIds() { 
		return service.getAllProductsIds().toString();
	}
	
	@Override
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

	@Override
	public String validateEntity(Product p){
		String result = "";
		Integer[] ivas = {4,5,6,9,12,13,18,22,23};
		if (!Arrays.asList(ivas).contains(p.getIva())){
			result += "Iva inválido.";
		};
		if (p.getDiscount() > p.getPvp()) {
			result += " Desconto inválido.";
		};
		return result;
	}
	
	@Override
	public String validateId(long id) {
		String result = "";
		List<Long> prods = service.getAllProductsIds();
		if (!prods.contains(id)) {
			result += " O produto indicado não é válido.";
		}
		return result;
	}
}
