package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest {

	static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDAO = new UserDAO(entityManager);
	}
	
	@Test
	public void testCreateUsers() {
		
		

		Users user1 = new Users();
		user1.setEmail("test6@gmail.com");
		user1.setFullName("Test TT6");
		user1.setPassword("4444");
		
		user1 = userDAO.create(user1);
		
		
		assertTrue(user1.getUserId()>0);
	}
	
	
	@Test
	public void testUpdateUsers() {
		
		
		
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("nam@codejava.net");
		user.setFullName("Nam Ha Bin");
		user.setPassword("mysecret");
		
		
		user =  userDAO.update(user);
		
		
		assertNotNull(user);
		
		
	}
	
	@Test
	public void testGetUsersFound() {
	
		Integer userId = 99;
		Users user = userDAO.get(userId);
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		
		Integer userId = 4;
		
		userDAO.delete(userId);
		Users user =  userDAO.get(userId);
		
		
		
		assertNull(user);
	}
	
	@Test
	public void  testCheckLoginSuccess() {
		String email = "nam@codejava.n";
		String password = "mysecret";
		assertTrue(userDAO.checkLogin(email, password)); 
		
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		
		for(Users user :listUsers) {
			System.out.println(user.getEmail());
		}
		
		
		assertTrue(listUsers.size()>0);
	}
	
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		
		assertTrue(totalUsers == 6);
	}
	
	@Test
	public void testFindByEmail() {
		String  email="createTest4@gmail.com";
		Users user =  userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		tearDownAfterClass();
	}

}
