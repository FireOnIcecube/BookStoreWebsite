package com.bookstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;

public class BaseDAOTest {
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	
	public static void setUpBeforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static void tearDownAfterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}
}
