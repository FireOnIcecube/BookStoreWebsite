package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest  {

	private static BookDAO bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}

	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook =new Book();
		existBook.setBookId(1);
		
		
		Category category = new Category("Core Java");
		category.setCategoryId(1);
		existBook.setCategory(category);
		
		existBook.setTitle("Effective Java (3rd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities");
		existBook.setPrice(40f);
		existBook.setIsbn("0321356683");
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existBook.setPublishDate(publishDate);
		
		String imagePath ="C:\\Users\\PC\\Desktop\\books\\Effective Java.jpg";
		
		byte[] imageBytes =  Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		
		
		Book updatedBook = bookDao.update(existBook);
		
		assertEquals(updatedBook.getTitle(), "Effective Java (3rd Edition)");
	}
	
	
	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook =new Book();
		
		Category category = new Category("Advanced Java");
		category.setCategoryId(2);
		newBook.setCategory(category);
		
		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop, varargs, concurrency utilities");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);
		
		String imagePath ="C:\\Users\\PC\\Desktop\\books\\Effective Java.jpg";
		
		byte[] imageBytes =  Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		
		
		Book createdBook = bookDao.create(newBook);
		
		assertTrue(createdBook.getBookId()>0);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 1;
		
		bookDao.delete(bookId);
		
		assertTrue(true);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 2;
		
		 Book b = bookDao.get(bookId);
		
		assertNotNull(b);
	}
	
	@Test
	public void testListAll() {
		
		List<Book> books = bookDao.listAll();
		
		for(Book book : books) {
			System.out.println(book.getBookId()+" - "+book.getTitle());
		}
		
		assertFalse(books.isEmpty());
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinkin in Java";
		Book book = bookDao.findByTitle(title);
		
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleExist() {
		String title = "Effective Java (2nd Edition)";
		Book book = bookDao.findByTitle(title);
		
		assertNotNull(book);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks =  bookDao.listNewBooks();
		
		for(Book aBook : listNewBooks) {
			System.out.println(aBook.getTitle()+ " - " +aBook.getPublishDate());
		}
		
		assertEquals(listNewBooks.size(), 4);
	}
	
	@Test
	public void testSearchBookIntitle() {
		String keyword = "Kathy Sierra, Bert Bates";
		List<Book> result = bookDao.search(keyword);
	
		
		for(Book aBook:result) {
			System.out.println(aBook.getTitle());

		}
		
		assertEquals(1,result.size());
	}
	
	@Test
	public void testSearchBookInAuthor() {
		String keyword = "Java";
		List<Book> result = bookDao.search(keyword);
	
		
		for(Book aBook:result) {
			System.out.println(aBook.getTitle());

		}
		
		assertEquals(2,result.size());
	}
	
	
	@Test
	public void testListByCategory() {
		int categoryId = 2;
		
		List<Book> listBooks = bookDao.listByCategory(categoryId);
		
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void testCountByCategory() {
		int categoryId = 2;
		
		long numOfBooks = bookDao.countByCategory(categoryId);
		
		assertEquals(numOfBooks, 3);;
	}
	
	@Test
	public void testFindByCategory() {
		int categoryId = 5;
		
		List<Book> listBooks = bookDao.listByCategory(categoryId);
		
		System.out.println(listBooks.size());
		
		assertTrue(listBooks.size()>0);
	}
	
	@Test
	public void testCount() {
		
		
		assertEquals(bookDao.count(), 2);
	}

}
