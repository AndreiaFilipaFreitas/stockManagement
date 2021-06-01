package io.altar.stockMaven.services;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.stockMaven.models.Shelf;
import io.altar.stockMaven.repositories.ShelfRepository;

@RequestScoped
public class ShelfService extends EntityService<ShelfRepository, Shelf> {

	@Inject
	ProductService ps;
	
	public List<Shelf> getAvailableShelves() {
		return repository.getAvailableShelves();
	}
	
	public List<Shelf> getAllShelves() {
		return repository.getAllShelves();
	}

	public List<Shelf> getShelvesByProdId(long prodId) {
		return repository.getShelvesByProdId(prodId);
	}
	
	public long getShelvesCount() {
		return repository.getShelvesCount();
	}
	
	public List<Long> getAllShelvesIds() {
		return repository.getAllShelvesIds();
	}
	
	public int clearAllShelvesByProdId(long prodId) {
		return repository.clearAllShelvesByProdId(prodId);
	}
	
	public Shelf addProdToShelf(long shelfId, long prodId) {
		return repository.addProdToShelf(shelfId, prodId);
	}
	
	public List<Shelf> getEmptyShelves() {
		return repository.getEmptyShelves();
	}
	
	public List<Shelf> getFullShelves() {
		return repository.getFullShelves();
	}
	
	public List<Long> getAvailableShelvesIds() {
		return repository.getAvailableShelvesIds();
	}
	
	public List<Long> getProdsIds() {
		return ps.getAllProductsIds();
	}
}
