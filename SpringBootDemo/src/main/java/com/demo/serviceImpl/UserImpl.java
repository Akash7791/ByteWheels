package com.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.dao.UserDaoCustom;
import com.demo.model.User;

@Component
public class UserImpl implements UserDaoCustom {

	@Autowired
	private UserDao userDao;
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public String getUserName(User user){
		List<User> u = em.createQuery("SELECT u FROM USERS u WHERE u.emailId LIKE :EMAILID").
				setParameter("EMAILID", user.getEmailId()).getResultList();
		if(!u.isEmpty()){
			userDao.save(user);
			return "Congratulations, You are added to ByteWheels!!";
		}else{
			return "Welcome, You are already added to ByteWheels.";
		}
	}
	
}
