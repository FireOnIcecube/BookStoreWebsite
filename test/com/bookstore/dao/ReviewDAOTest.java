package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {

	private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Book book = new Book(); 
		book.setBookId(13);
		
		Customer customer = new Customer();
		customer.setCustomerId(10);
		
		review.setBook(book);
		review.setCustomer(customer);

		review.setHeadline("This is a very good book!");
		review.setRating(3);
		review.setComment("I have just read this book. Very good.");
		
		Review savedReview =  reviewDao.create(review); 
		
		assertTrue(savedReview.getReviewId() > 0);
		
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId =1;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Excellent book");
		
		Review updatedReview = reviewDao.update(review);
		
		assertEquals(updatedReview.getHeadline(), review.getHeadline());
	}

	@Test
	public void testGet() {
		Review review = reviewDao.get(2);
		
		assertNotNull(review);
	}

	@Test
	public void testDeleteObject() {
		int reviewId = 2;
		
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
		for(Review review : listReview) {
			System.out.println(review.getReviewId() +" : " + review.getBook().getTitle()+ " : " +
						review.getCustomer().getFullname()+ " : "+
						review.getHeadline() +" : "+ review.getRating());
		}
		
		assertTrue(listReview.size() >0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		
		assertTrue(totalReviews == 2);
	}

}
