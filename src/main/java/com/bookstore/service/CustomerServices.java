package com.bookstore.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		super();
		request.setCharacterEncoding("UTF-8");
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}
	
	public void  listCustomers() throws ServletException, IOException {
		
		listCustomers(null);
	}
	
	
	public void  listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
		
		if(message !=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomer", listCustomer);
		
		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
		
	}
	
	public void createCustomer() throws ServletException, IOException {
		String  email = request.getParameter("email");
		Customer existCustomer =customerDAO.findByEmail(email);
		
		if(existCustomer != null) {
			String message = "Could not create customer: the email " 
						+ email +" is already registered by another customer.";
			listCustomers(message);
		}else {
		
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			
			String message = "New customer has been created successfully.";
			listCustomers(message);
		}
		
	}
	
	private void updateCustomerFieldsFromForm(Customer customer) {
		String  email = request.getParameter("email");
		String  fullName = request.getParameter("fullName");
		String  password = request.getParameter("password");
		String  phone = request.getParameter("phone");
		String  address = request.getParameter("address");
		String  city = request.getParameter("city");
		String  zipCode = request.getParameter("zipCode");
		String  country = request.getParameter("country");
		
		
		customer.setEmail(email);
		customer.setFullname(fullName);
		customer.setPassword(password);
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipCode);
		customer.setCountry(country);
	}
	
	public void registerCustomer() throws ServletException, IOException {
		String  email = request.getParameter("email");
		Customer existCustomer =customerDAO.findByEmail(email);
		String  message;
		
		
		if(existCustomer != null) {
			 message = "Could not register. the email " 
						+ email +" is already registered by another customer.";
		}else {
			
			Customer newCustomer = new Customer();
			updateCustomerFieldsFromForm(newCustomer);
			customerDAO.create(newCustomer);

			
			 message = "You have been registered successfully! Thank you. <br/>"
					 +"<a href='login'>Click here</a> to login";
		}
		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public void editCustomer() throws ServletException, IOException {

		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		
		String destPage = "customer_form.jsp";
		
		if (customer != null) {
			request.setAttribute("customer", customer);
		} else {
			String message = "Could not find customer with ID " + customerId;
			request.setAttribute("message", message);
			destPage = "message.jsp";
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
	
	}

	public void updateCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		
		Customer customerByEmail = customerDAO.findByEmail(email);
		
		String message;
		
		if(customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
			
			message = "Could not update the Customer Id "+customerId+
					" because there's an existing customer having the same email.";
			
		}else {
			Customer customerById = customerDAO.get(customerId);
			updateCustomerFieldsFromForm(customerById);
			
			customerDAO.update(customerById);
			
			message="The customer has been updated successfully";
		}
		listCustomers(message);
		
	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		String message ;
		
		
		if(customer == null) {
			message = "Could not find customer with ID " + customerId 
					+ ", or it might have been deleted";
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}else {
			
			customerDAO.delete(customerId);
			
			message="The customer has been deleted successfully.";
			listCustomers(message);
		}
		
		
	}
	
	
	
}