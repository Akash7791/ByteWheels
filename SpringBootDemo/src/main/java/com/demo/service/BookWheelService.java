package com.demo.service;

import java.util.List;

import com.demo.model.BookWheel;
import com.demo.model.User;

public interface BookWheelService {

	public boolean bookWheel(BookWheel bWheel);
	public boolean notifyClient(User user);
}
