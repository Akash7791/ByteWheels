package com.demo.dao;

import java.util.List;

import com.demo.model.AvailWheels;
import com.demo.model.BookWheel;
import com.demo.model.ByteWheels;

public interface ByteWheelsDao {

	public List<AvailWheels> getAvailWheels();
	public List<AvailWheels> getSelectedAvailWheels(BookWheel bWheel);
	public List<BookWheel> isBooked(BookWheel bWheel);
	public List<ByteWheels> getCategorizedWheels(ByteWheels byteWheels);
	public long getamount(BookWheel bWheel);
}
