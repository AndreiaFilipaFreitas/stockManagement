package io.altar.stockMaven.models;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = Product.GET_DISCOUNT_PRODUCTS, query="SELECT p FROM Product p WHERE p.discount > 0"),
	@NamedQuery(name = Product.GET_ALL_PRODUCTS, query="SELECT p FROM Product p"),
	@NamedQuery(name = Product.GET_PRODUCTS_BY_PROD_LOW_PRICE, query="SELECT p FROM Product p WHERE p.pvp < :price"),
	@NamedQuery(name = Product.GET_PRODUCTS_COUNT, query = "SELECT COUNT(p.id) FROM Product p"),
	@NamedQuery(name = Product.GET_ALL_PRODUCTS_IDS, query="SELECT p.id FROM Product p")
})

public class Product extends Entity_ {
	
	public static final String GET_DISCOUNT_PRODUCTS = "getDiscountProducts";
	public static final String GET_ALL_PRODUCTS = "getAllProducts";
	public static final String GET_PRODUCTS_BY_PROD_LOW_PRICE = "getProductsByLowPrice";
	public static final String GET_PRODUCTS_COUNT = "getProductsCount";
	public static final String GET_ALL_PRODUCTS_IDS = "getAllProductsIds";
	
	private double discount;
	private int iva;
	private double pvp;
	private static final long serialVersionUID = 1L;
	
	public Product(int discount, int iva, int pvp) {
		this.discount = discount;
		this.iva = iva;
		this.pvp = pvp;
	}

	public Product() {}
	
	@Override
	public String toString() {
		return "ID do Produto: " + this.getId() + "  Desconto (€): " + discount + "  IVA: " + iva + "  PVP: " + pvp;
	}

	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount){
		this.discount = discount;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva){
		this.iva = iva;
	}

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}
	
}
