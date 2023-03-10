package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {

	static CategoryDAO categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		categoryDao = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Gaming");
		Category category =  categoryDao.findByName(newCat.getName());
		
		if(category != null)
		{
		}
		
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
	public void testDeleteCategory() {
		Integer catId = 5;
		categoryDao.delete(catId);
		
		Category cat = categoryDao.get(catId);
		
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll(); 
		
		listCategory.forEach(c -> System.out.println(c.getName()));
		
		assertTrue(listCategory.size()>0);
	}

	@Test
	public void testCount() {
		long totalCategories = categoryDao.count();
		assertEquals(totalCategories, 4);
	}
	
	@Test
	public void testfindByName() {
		
		Category category = categoryDao.findByName("gaming");
		
		assertNotNull(category);
	}

}
