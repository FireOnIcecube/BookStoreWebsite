package com.bookstore.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Review;

public class ReviewServices {
	
	private ReviewDAO reviewDAO;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		super();
		
		this.request = request;
		this.response = response;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		this.reviewDAO = new ReviewDAO();
	}
	
	public void listAllReview() throws ServletException, IOException {
		listAllReview(null);
	}
	
	
	public void listAllReview(String message) throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		request.setAttribute("listReviews", listReviews);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "review_list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
		dispatcher.forward(request, response);
	}

	public void editReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		String destPage ="review_form.jsp";
		
		if (review != null) {		
			request.setAttribute("review", review);		
		} else {
			String message = "Could not find review with ID " + reviewId;
			
			destPage = "message.jsp";
			request.setAttribute("message", message);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

	public void updateReview() throws ServletException, IOException {
		
		Integer reviewId = Integer.parseInt(request.getParameter("reviewId"));
		String headline =request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = reviewDAO.get(reviewId);
		review.setHeadline(headline);
		review.setComment(comment);
		
		reviewDAO.update(review);
		
		String message = "The review has been updated successfully."; 
		
		listAllReview(message);
	}

	public void deleteReview() throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		
		if (review != null) {
			reviewDAO.delete(reviewId);
			String message = "The review has been deleted successfully.";
			listAllReview(message);
		} else {
			String message = "Could you find review with ID " + reviewId
					+ ", or it might have been deleted by another admin";
			
			String  destPage = "message.jsp";
			
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);
			
		}		
	}
	
	
	
}
