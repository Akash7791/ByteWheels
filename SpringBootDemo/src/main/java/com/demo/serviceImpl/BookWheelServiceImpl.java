package com.demo.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ByteWheelsDao;
import com.demo.model.AvailWheels;
import com.demo.model.BookWheel;
import com.demo.model.User;
import com.demo.service.BookWheelService;

@Component
public class BookWheelServiceImpl implements BookWheelService{

	@Autowired
    public JavaMailSender emailSender;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	private ByteWheelsDao byteWheelsDao;
	
	DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	@Transactional
	public boolean bookWheel(BookWheel bWheel) {
		
		
		if(isAvailWheel(bWheel)){
			Date d1=null;
			Date d2=null;
			em.persist(bWheel);
			AvailWheels avail = em.find(AvailWheels.class, bWheel.getBwheelId());
			avail.setIsAvail("N");
			avail.setBooked_date_from(bWheel.getBfrom());
			avail.setBooked_date_to(bWheel.getBto());
			
			long rate = countAmount(bWheel);
			
			try {
				d1 = sf.parse(bWheel.getBfrom());
				d2 = sf.parse(bWheel.getBto());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			bWheel.setAmountToBePaid(rate*((d2.getDay()-d1.getDay())+1));
			return true;
		}
		
		return false;					
	}
	
	public long countAmount(BookWheel bWheel){
		
		return byteWheelsDao.getamount(bWheel);
		
	}
	
	public boolean isAvailWheel(BookWheel bWheel){
		boolean isAvail = false;
		Date dbookedDateFrom;
		Date dbookedDateTo;
		Date drequestedBookingDate;
		Date drequestedBookingDateToDate;
		String bookedDateFrom;
		String bookedDateTo;
		String requestedBookingDate;
		String requestedBookingDateTo;
		
		List<AvailWheels> availWheels = byteWheelsDao.getSelectedAvailWheels(bWheel);
		if(!availWheels.isEmpty()){
			Iterator itr = availWheels.iterator();
			while(itr.hasNext()){
				AvailWheels aWheels = (AvailWheels) itr.next();
				
				List<BookWheel> booked = byteWheelsDao.isBooked(bWheel);
				
				if(booked.isEmpty()){
					isAvail = true;
					return isAvail;
				}else{
					bookedDateFrom = booked.iterator().next().getBfrom();
					bookedDateTo = booked.iterator().next().getBto();
					requestedBookingDate = bWheel.getBfrom();
					requestedBookingDateTo = bWheel.getBto();
					try {
						dbookedDateFrom = sf.parse(bookedDateFrom);
						dbookedDateTo = sf.parse(bookedDateTo);
						drequestedBookingDate = sf.parse(requestedBookingDate);
						drequestedBookingDateToDate = sf.parse(requestedBookingDateTo);
						if(dbookedDateFrom.before(drequestedBookingDate) || dbookedDateFrom.equals(drequestedBookingDate) || drequestedBookingDateToDate.equals(dbookedDateFrom)){
							if(dbookedDateTo.after(drequestedBookingDate) || dbookedDateTo.equals(drequestedBookingDate) || dbookedDateTo.equals(drequestedBookingDateToDate)){
								System.out.println("Sorry, This car is already booked for "+bookedDateFrom+" to "+bookedDateTo+". Kindly book another slot.");
								isAvail = false;
								return isAvail;
							}
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					isAvail = true;
					return isAvail;
				}
				
				/*if("Y".equalsIgnoreCase(aWheels.getIsAvail())){
					isAvail = true;
				}else{
					String bookedDateFrom = aWheels.getBooked_date_from();
					String bookedDateTo = aWheels.getBooked_date_to();
					String requestedBookingDate = bWheel.getBfrom();
					
					try {
						dbookedDateFrom = sf.parse(bookedDateFrom);
						dbookedDateTo = sf.parse(bookedDateTo);
						drequestedBookingDate = sf.parse(requestedBookingDate);
						if(dbookedDateFrom.before(drequestedBookingDate) || dbookedDateFrom.equals(drequestedBookingDate)){
							if(dbookedDateTo.after(drequestedBookingDate) || dbookedDateTo.equals(drequestedBookingDate)){
								System.out.println("Sorry, This car is already booked for "+bookedDateFrom+" to "+bookedDateTo+". Kindly book another slot.");
								isAvail = false;
								return isAvail;
							}
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
			}
		}
		return isAvail;
	}

	@Override
	public boolean notifyClient(User user) {
		
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(user.getEmailId()); 
        message.setSubject("Booking with ByteWheel"); 
        message.setText("Congratulations, You have booked the Wheels.");
        emailSender.send(message);
		
		return true;
	}

}
