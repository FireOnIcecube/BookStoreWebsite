package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.catalina.User;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	
	public Users create(Users user) {
		// TODO Auto-generated method stub
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
