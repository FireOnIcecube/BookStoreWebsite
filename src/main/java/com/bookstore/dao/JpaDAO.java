package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO<E> {
	
	private static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStore");
	}
	
	public JpaDAO() {
	}
	
	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		
		return entity;
	}
	
	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}
	
	public E find(Class<E> type, Object id) {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		E entity =  entityManager.find(type, id);
		
		if(entity != null)
		{
			entityManager.refresh(entity);
		}
		entityManager.close();
		return entity;
	}
	
	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		
		Object reference =  entityManager.getReference(type, id);
		entityManager.remove(reference);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<E> findWithNameQuery(String queryName){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}
	
	public List<E> findWithNameQuery(String queryName ,int firstResult,int maxResults){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}
	
	public List<E> findWithNameQuery(String queryName,String paramNam, Object paramValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		
		query.setParameter(paramNam, paramValue);
		
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}
	
	
	
	public List<E> findWithNameQuery(String queryName,Map<String ,Object> parameters){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		
		Set<Entry<String,Object>>setParameter =  parameters.entrySet();
		
		for(Entry<String,Object> entry :setParameter) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		List<E> result =query.getResultList();
		entityManager.close();
		return  result;
	}
	
	public long countWithNamedQuery(String  queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		long result = (long) query.getSingleResult();
		entityManager.close();
		return result;
	}
	
	public long countWithNamedQuery(String  queryName,String paramName, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		
		long result = (long) query.getSingleResult();
		entityManager.close();
		return result;
	}
	
	
	public void close() {
		if(entityManagerFactory !=null) {
			entityManagerFactory.close();
		}
	}
	
	

}
