package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;
import com.bookstore.service.HashGenerator;

public class CustomerDAOTest {

	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("billy.jane@gmail.com");
		customer.setFullname("Jane Billy");
		customer.setCity("New York");
		customer.setCountry("United States");
		customer.setAddress("100 North Avenue");
		customer.setPassword("secret");
		customer.setPhone("18001900");
		customer.setZipcode("100000");
		
		Customer savedCustomer = customerDao.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 1;
		Customer customer = customerDao.get(customerId);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(1);
		String fullName = "Tom Tom Tom";
		customer.setFullname(fullName);
		Customer updatedCustomer = customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFullname().equals(fullName));
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerId = 1;
		customerDao.delete(customerId);
		Customer customer = customerDao.get(1);
		
		assertNull(customer);
	}
	
	@Test
	public void testCountCustomer() {
		customerDao.count();
		
		assertEquals(customerDao.count(), 2);
	}
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		assertEquals(listCustomers.size(), 2);
	}
	
	@Test
	public void testFindByEmail() {
		String email="tom@gmal.com";
		
		Customer customer = customerDao.findByEmail(email);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "tom@gmail.com";
		String password = "secret";
		
		Customer customer = customerDao.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email = "aaa@gmail.com";
		String password = "ddd";
		
		Customer customer = customerDao.checkLogin(email, password);
		
		assertNull(customer);
	}
	
	@Test
	public void testHashGenerator() {
		System.out.println(HashGenerator.generateMD5("secret"));
		System.out.println(HashGenerator.generateMD5("secret"));
		System.out.println(HashGenerator.generateMD5("secret"));
		System.out.println(HashGenerator.generateMD5("secret"));
		System.out.println(HashGenerator.generateMD5("secret"));
		
	}

}
