package io.altar.stockMaven.services;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.stockMaven.models.Entity_;
import io.altar.stockMaven.repositories.EntityRepository;

@Transactional
public abstract class EntityService<R extends EntityRepository<E>, E extends Entity_> {

	@Inject
	protected R repository;
	
	public long createEntity (E entity) { 
		return repository.createEntity(entity);
	}
	
	public E checkEntity(long ID) { //returns the entity based on a given ID
		return repository.checkEntity(ID);
	}
	
	public E change(E entity) { //returns the entity with the modifications
		return repository.change(entity);
	}

	public void deleteEntity(long ID) { // deletes entity
		repository.deleteEntity(ID);
	}
}
