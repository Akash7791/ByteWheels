package com.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Main;
import com.demo.dao.ByteDao;
import com.demo.dao.UserDao;
import com.demo.dao.UserDaoCustom;
import com.demo.model.AvailWheels;
import com.demo.model.BookWheel;
import com.demo.model.ByteWheels;
import com.demo.model.User;
import com.demo.service.BookWheelService;
import com.demo.service.ByteWheelsService;
import com.demo.service.UserService;
import com.demo.serviceImpl.UserImpl;


@RestController
@EnableAutoConfiguration
@RequestMapping("/byteWheels")
public class ByteController {
	
	private static final Logger logger = LoggerFactory.getLogger(ByteController.class);
	
	@Autowired
	private ByteDao byteDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDaoCustom userDaoCustom;
	
	@Autowired
	private ByteWheelsService byteWheelsService;
	
	@Autowired
	private BookWheelService bookWheelService;
	
	private UserImpl userImpl;
	
	@Autowired
	public void setUserImpl(UserImpl userImpl) {
		this.userImpl = userImpl;
	}
	
	//get List of Wheels
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getListOfWheels", method=RequestMethod.POST)
	public List<ByteWheels> getListOfWheels(){
		logger.debug("Implementation of get list of Vehicles");
		return (ArrayList<ByteWheels>) byteDao.findAll();
	}
	
	/*//Save User
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public User getListOfWheels(@RequestBody User user){
		userDao.save(user);
		return user;
	}*/

	//Get User
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public String getUser(@RequestBody User user){
		logger.debug("Implementation of User Authentication, if not present, adding to ByteWheels");
		userDaoCustom.getUserName(user);
		return null;
	}
	
	@RequestMapping(value="/getAvailWheels",method=RequestMethod.GET)
	public List<AvailWheels> getAvailWheels(){
		logger.debug("Implementation of Available Vehicles");
		return byteWheelsService.getAvailWheels();
	}
	
	@RequestMapping(value="/getCategorizedWheels",method=RequestMethod.POST)
	public List<ByteWheels> getCategorizedWheels(@RequestBody ByteWheels byteWheels){
		logger.debug("Implementation of get list of requested Categories Vehicles");
		return byteWheelsService.getCategorizedWheels(byteWheels);
		
	}
	
	@RequestMapping(value="/bookWheel",method=RequestMethod.POST)
	public String bookWheel(@RequestBody BookWheel bWheel){
		DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dFrom=null;
		Date dTo=null;
		try {
			dFrom = sf.parse(bWheel.getBfrom());
			dTo = sf.parse(bWheel.getBto());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date currDate = new Date();
		if(dFrom.after(dTo)){
			return "Sorry Dear, Booking From Date should be before Booking To date.";
		}
		
		
		if(dFrom.before(currDate)){
			return "Sorry, Booking From should be future date.";
		}
		
		logger.debug("Implementation of Booking Vehicle");
		if(bookWheelService.bookWheel(bWheel)){
			bookWheelService.notifyClient(bWheel.getUser());
			return "Congratulations, Your wheels are booked. You are notified on your register Email. Amount to be paid for your ride: "+bWheel.getAmountToBePaid()+".";
		}
		return "Sorry, This car is already booked for "+bWheel.getBfrom()+" to "+bWheel.getBto()+". Kindly book another slot.";	
	}
	
}
