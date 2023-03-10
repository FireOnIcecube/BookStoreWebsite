package com.bookstore.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {

	private CustomerDAO customerDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		super();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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
		
		if(email != null && !email.equals("")) {
			customer.setEmail(email);
		}
		
		customer.setFullname(fullName);
		
		if (password != null & !password.isEmpty()) {
			String encryptedPassword = HashGenerator.generateMD5(password);
			customer.setPassword(encryptedPassword);				
		}		
		
		
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
		
		if (customer == null) {
			
			String message = "Could not find customer with ID " + customerId;
			request.setAttribute("message", message);
			destPage = "message.jsp";
			
			request.setAttribute("customer", customer);
			
		} else {
			customer.setPassword(null);
			request.setAttribute("customer", customer);
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
		
		
		if(customer != null) {
			ReviewDAO reviewDAO = new ReviewDAO();
			long reviewCount = reviewDAO.countByCustomer(customerId);
			
			if(reviewCount == 0) {
				customerDAO.delete(customerId);
				
				message="The customer has been deleted successfully.";
				listCustomers(message);
			}else {
				message = "Could not delete customer with ID " + customerId
						+ " because he/she posted reviews for books.";
				
				request.setAttribute("message", message);
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
			
		}else {
			message = "Could not find customer with ID " + customerId 
					+ ", or it might have been deleted";
			
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		
		
	}

	public void showLogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
		dispatcher.forward(request, response);
		
	}

	public void doLogin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		if(customer == null) {
			String message = "Login failed. Please check your email and password.";
			request.setAttribute("message", message);
			showLogin();
			
		}else {
			request.getSession().setAttribute("loggedCustomer", customer);
			
			showCustomerProfile();
		}
		
	}
	
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage = "frontend/customer_profile.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
		dispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		String  editPage = "frontend/edit_profile.jsp";
		RequestDispatcher dispatcher= request.getRequestDispatcher(editPage);
		dispatcher.forward(request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer = (Customer)request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();
		
	}
	
	
	
}
