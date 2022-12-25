package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		Users user1 = new Users();
		user1.setEmail("you@robotics.com");
		user1.setFullName("Mr President");
		user1.setPassword("power");
		
		EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		UserDAO userDAO = new UserDAO(entityManager);
		user1 = userDAO.create(user1);
		
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("A Users Object was persisted");
	}

}
