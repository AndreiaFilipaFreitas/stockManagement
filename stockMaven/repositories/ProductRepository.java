package io.altar.stockMaven.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import io.altar.stockMaven.models.Product;

@ApplicationScoped
public class ProductRepository extends EntityRepository<Product> {
	
	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}
	
	public List<Product> getDiscountProducts() {
		return eManager.createNamedQuery(Product.GET_DISCOUNT_PRODUCTS, getEntityClass()).getResultList();
	}
	
	public List<Product> getAllProducts() {
		return eManager.createNamedQuery(Product.GET_ALL_PRODUCTS, getEntityClass()).getResultList();
	}

	public List<Product> getProductsByLowPrice(double price) {
		return eManager.createNamedQuery(Product.GET_PRODUCTS_BY_PROD_LOW_PRICE, getEntityClass()).setParameter("price", price).getResultList();
	}
	
	public long getProductsCount() {
		return eManager.createNamedQuery(Product.GET_PRODUCTS_COUNT, Long.class).getSingleResult();
	}
	
	public List<Long> getAllProductsIds() {
		return eManager.createNamedQuery(Product.GET_ALL_PRODUCTS_IDS, Long.class).getResultList();
	}
}
