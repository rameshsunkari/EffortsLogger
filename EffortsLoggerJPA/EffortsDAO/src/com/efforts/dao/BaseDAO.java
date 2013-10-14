package com.efforts.dao;

import java.util.UUID;

import javax.persistence.EntityManager;

public abstract class BaseDAO<T> {

	private Class<T> entityClass;

	public BaseDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
		getEntityManager().persist(entity);
		// getEntityManager().flush();
	}

	public void edit(T entity) {
		getEntityManager().merge(entity);
		// getEntityManager().flush();
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
		// getEntityManager().flush();
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String getGUID() {
		return UUID.randomUUID().toString();
	}

}
