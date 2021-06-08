package io.altar.stockMaven.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = Shelf.GET_AVAILABLE_SHELVES, query="SELECT s FROM Shelf s WHERE s.prod = null AND s.cap = 1"),
	@NamedQuery(name = Shelf.GET_ALL_SHELVES, query="SELECT s FROM Shelf s"),
	@NamedQuery(name = Shelf.GET_SHELVES_BY_PROD_ID, query="SELECT s FROM Shelf s WHERE s.prod.id = :prodId"),
	@NamedQuery(name = Shelf.GET_SHELVES_COUNT, query = "SELECT COUNT(s.id) FROM Shelf s"),
	@NamedQuery(name = Shelf.GET_ALL_SHELVES_IDS, query="SELECT s.id FROM Shelf s"),
	@NamedQuery(name = Shelf.CLEAR_ALL_SHELVES_BY_PROD_ID, query="UPDATE Shelf s SET s.prod = null WHERE s.prod.id = :prodId"),
	@NamedQuery(name = Shelf.GET_EMPTY_SHELVES, query="SELECT s FROM Shelf s WHERE s.prod = null"),
	@NamedQuery(name = Shelf.GET_FULL_SHELVES, query="SELECT s FROM Shelf s WHERE s.prod <> null"),
	@NamedQuery(name = Shelf.GET_AVAILABLE_SHELVES_IDS, query="SELECT s.id FROM Shelf s WHERE s.prod = null AND s.cap = 1")
})

public class Shelf  extends Entity_ {
	
	public static final String GET_AVAILABLE_SHELVES = "getAvailableShelves";
	public static final String GET_ALL_SHELVES = "getAllShelves";
	public static final String GET_SHELVES_BY_PROD_ID = "getShelvesByProdId";
	public static final String GET_SHELVES_COUNT = "getShelvesCount";
	public static final String GET_ALL_SHELVES_IDS = "getAllShelvesIds";
	public static final String CLEAR_ALL_SHELVES_BY_PROD_ID = "clearAllShelvesByProdId";
	public static final String GET_EMPTY_SHELVES = "getEmptyShelves";
	public static final String GET_FULL_SHELVES = "getFullShelves";
	public static final String GET_AVAILABLE_SHELVES_IDS = "getAvailableShelvesIds";
	
	private static final long serialVersionUID = 1L;
	private int cap;
	@ManyToOne
	private Product prod;
	private double price;

	public Shelf(int cap, Product prod, double price) {
		this.cap = cap;
		this.prod = prod;
		this.price = price;
	}
	
	public Shelf() {}
	
	@Override
	public String toString() {
		return "ID da Prateleira: " + this.getId() + " Capacidade: " + cap + "  Com os Produtos: " + prod + "  Preço: " + price;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
