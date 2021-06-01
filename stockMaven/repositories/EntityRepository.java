package io.altar.stockMaven.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.altar.stockMaven.models.Entity_;

public abstract class EntityRepository< T extends Entity_> {
	
	protected abstract Class<T> getEntityClass();
	
	@PersistenceContext(unitName = "database")
	protected EntityManager eManager;
	
	public long createEntity (T entity) { 
		eManager.persist(entity);
		return entity.getId();
	}
	
	public T checkEntity(long ID) { 
		return eManager.find(getEntityClass(), ID);
	}
	
	public T change(T entity) { 
		return eManager.merge(entity);
	}

	
	public void deleteEntity(long ID) { 
		T ent = eManager.find(getEntityClass(), ID);
		eManager.remove(ent);
	}

}
