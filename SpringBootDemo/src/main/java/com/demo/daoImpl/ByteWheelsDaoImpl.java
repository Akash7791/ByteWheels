package com.demo.daoImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.demo.dao.ByteWheelsDao;
import com.demo.model.AvailWheels;
import com.demo.model.BookWheel;
import com.demo.model.ByteWheels;

@Component
public class ByteWheelsDaoImpl implements ByteWheelsDao{

	DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AvailWheels> getAvailWheels() {
		return em.createQuery("SELECT a FROM AVAILWHEELS a").getResultList();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AvailWheels> getSelectedAvailWheels(BookWheel bWheel) {
		Date dbookedTo;
		Date currDate = new Date();
		
		Query query = em.createQuery("SELECT a from AVAILWHEELS a WHERE a.wname LIKE :WNAME AND a.uniqueWid LIKE :UNIQUEWID");
		query.setParameter("WNAME", bWheel.getBname());
		query.setParameter("UNIQUEWID", bWheel.getBwheelId());
		List<AvailWheels> avail = query.getResultList();
		/*String bookedTo = avail.iterator().next().getBooked_date_to();
		if(null!=bookedTo){
			try {
				dbookedTo = sf.parse(bookedTo);
				if(dbookedTo.before(currDate)){
					avail.iterator().next().setIsAvail("Y");
				}	
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
		
		return avail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookWheel> isBooked(BookWheel bWheel) {
		
		Query query = em.createQuery("SELECT a from BOOKWHEEL a WHERE a.bwheelId LIKE :BWHEELID");
		query.setParameter("BWHEELID", bWheel.getBwheelId());
		List<BookWheel> booked = query.getResultList();
		return booked;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ByteWheels> getCategorizedWheels(ByteWheels byteWheels) {
		Query query = em.createQuery("SELECT a from BYTEWHEELS a WHERE a.category LIKE :CATEGORY");
		query.setParameter("CATEGORY", byteWheels.getCategory());
		List<ByteWheels> selectedByteWheels = query.getResultList();	
		return selectedByteWheels;
	}

	@Override
	public long getamount(BookWheel bWheel) {
		
		Query query = em.createQuery("SELECT a from BYTEWHEELS a WHERE a.name LIKE :NAME");
		query.setParameter("NAME", bWheel.getBname());
		List<ByteWheels> selectedByteWheels = query.getResultList();	
		
		return selectedByteWheels.iterator().next().getRate();
	}

}
