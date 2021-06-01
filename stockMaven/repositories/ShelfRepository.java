package io.altar.stockMaven.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import io.altar.stockMaven.models.Shelf;

@RequestScoped
public class ShelfRepository extends EntityRepository<Shelf> {

	@Override
	protected Class<Shelf> getEntityClass() {
		return Shelf.class;
	}
	
	public List<Shelf> getEmptyShelves() {
		return eManager.createNamedQuery(Shelf.GET_EMPTY_SHELVES, getEntityClass()).getResultList();
	}
	
	public List<Shelf> getFullShelves() {
		return eManager.createNamedQuery(Shelf.GET_FULL_SHELVES, getEntityClass()).getResultList();
	}
	
	public List<Shelf> getAllShelves() {
		return eManager.createNamedQuery(Shelf.GET_ALL_SHELVES, getEntityClass()).getResultList();
	}

	public List<Shelf> getShelvesByProdId(Long prod_id) {
		return eManager.createNamedQuery(Shelf.GET_SHELVES_BY_PROD_ID, getEntityClass()).setParameter("prodId", prod_id).getResultList();
	}
	
	public long getShelvesCount() {
		return eManager.createNamedQuery(Shelf.GET_SHELVES_COUNT, Long.class).getSingleResult();
	}
	
	public List<Long> getAllShelvesIds() {
		return eManager.createNamedQuery(Shelf.GET_ALL_SHELVES_IDS, Long.class).getResultList();
	}
	
	public int clearAllShelvesByProdId(Long prodId) {
		return eManager.createNamedQuery(Shelf.CLEAR_ALL_SHELVES_BY_PROD_ID).setParameter("prodId", prodId).executeUpdate();
	}
	
	public Shelf addProdToShelf(long shelfId, long prodId) {
		String query = "UPDATE Shelf s SET s.prod.id = " + prodId + " WHERE s.id = " + shelfId;
		eManager.createQuery(query).executeUpdate();
		return checkEntity(shelfId);
	}
	
	public List<Shelf> getAvailableShelves() {
		return eManager.createNamedQuery(Shelf.GET_AVAILABLE_SHELVES, getEntityClass()).getResultList();
	}
	
	public List<Long> getAvailableShelvesIds() {
		return eManager.createNamedQuery(Shelf.GET_AVAILABLE_SHELVES_IDS, Long.class).getResultList();
	}
}

