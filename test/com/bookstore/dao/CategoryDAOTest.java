package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {

	static CategoryDAO categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		BaseDAOTest.setUpBeforeClass();
		
		categoryDao = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Java Core");
		Category category =  categoryDao.create(newCat);
		
		System.out.println("-=----------------------------------");
		System.out.println(category);
		System.out.println(category.getCategoryId());
		
		
		assertTrue(category != null && category.getCategoryId()>0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Sport");
		cat.setCategoryId(1);
		Category category = categoryDao.update(cat);
		
		assertEquals(cat.getName(), category.getName());
	}

	@Test
	public void testGet() {
		Integer catId =23;
		Category cat = categoryDao.get(catId);
		
		assertNotNull(cat);
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
