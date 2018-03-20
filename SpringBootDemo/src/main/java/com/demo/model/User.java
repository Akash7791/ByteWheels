package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="USERS")
@Table(name="USERS")
public class User {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(name="NAME")
	private String username;
	@Column(name="EMAILID")
	private String emailId;
	@Column(name="CONTACT_NUMBER")
	private long num;
	
	
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public User(int userId, String username, String emailId, int num) {
		super();
		this.userId = userId;
		this.username = username;
		this.emailId = emailId;
		this.num = num;
	}
	
	public User(){
		
	}
}
