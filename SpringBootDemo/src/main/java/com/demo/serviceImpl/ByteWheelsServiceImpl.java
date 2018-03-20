package com.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.ByteWheelsDao;
import com.demo.model.AvailWheels;
import com.demo.model.ByteWheels;
import com.demo.service.ByteWheelsService;

@Component
public class ByteWheelsServiceImpl implements ByteWheelsService{

	@Autowired
	private ByteWheelsDao byteWheelsDao;
	
	@Override
	public List<AvailWheels> getAvailWheels() {
		return byteWheelsDao.getAvailWheels();
	}

	@Override
	public List<ByteWheels> getCategorizedWheels(ByteWheels byteWheels) {
		return byteWheelsDao.getCategorizedWheels(byteWheels);
	}

	
	
}
