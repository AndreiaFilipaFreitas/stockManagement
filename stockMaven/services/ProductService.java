package io.altar.stockMaven.services;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.stockMaven.models.Product;
import io.altar.stockMaven.repositories.ProductRepository;

@RequestScoped
public class ProductService extends EntityService<ProductRepository, Product> {
	
	@Inject
	ShelfService s;
	
	@Override
	public void deleteEntity(long ID) { 
		s.clearAllShelvesByProdId(ID);
		repository.deleteEntity(ID);
	}

	public List<Product> getDiscountProducts() {
		return repository.getDiscountProducts();
	}
	
	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}

	public List<Product> getProductsByLowPrice(double price) {
		return repository.getProductsByLowPrice(price);
	}
	
	public long getProductsCount() {
		return repository.getProductsCount();
	}
	
	public List<Long> getAllProductsIds() {
		return repository.getAllProductsIds();
	}
}
