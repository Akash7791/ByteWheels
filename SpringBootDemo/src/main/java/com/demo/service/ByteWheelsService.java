package com.demo.service;

import java.util.List;

import com.demo.model.AvailWheels;
import com.demo.model.ByteWheels;

public interface ByteWheelsService {

	public List<AvailWheels> getAvailWheels();
	public List<ByteWheels> getCategorizedWheels(ByteWheels byteWheels);
}
