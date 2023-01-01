package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;
import com.bookstore.service.HashGenerator;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	
	public Users create(Users user) {
		String encryptedPassword = HashGenerator.generateMD5(user.getPassword());
		user.setPassword(encryptedPassword);
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		
		
		return super.find(Users.class, userId);
	}
	
	public Users findByEmail(String email) {
		List<Users> listUsers =  super.findWithNameQuery("Users.findByEmail", "email",email);
		
		if(listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
	
		return null;
	}
	
	public boolean checkLogin(String email, String password) {
		
		Map<String, Object> parameters = new HashMap<>();
		String encryptedPassword = HashGenerator.generateMD5(password);
		parameters.put("email", email);
		parameters.put("password", encryptedPassword);
		
		List<Users> listUsers =  super.findWithNameQuery("Users.checkLogin",parameters);
		
		if(listUsers.size() ==1) {
			return true;
		}
		
		return false;
	}
	

	@Override
	public void delete(Object userId) {
		// TODO Auto-generated method stub
		
		super.delete(Users.class, userId);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNameQuery("Users.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Users.countAll");
	}

	

}
