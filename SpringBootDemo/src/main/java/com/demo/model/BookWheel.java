package com.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="BOOKWHEEL")
@Table(name="BOOKWHEEL")
public class BookWheel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int bid;
	private String bwheelId;
	private String bname;
	private String bfrom;
	private String bto;
	private long amountToBePaid;
	
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BID")
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	@Column(name="BWHEELID")
	public String getBwheelId() {
		return bwheelId;
	}
	public void setBwheelId(String bwheelId) {
		this.bwheelId = bwheelId;
	}
	@Column(name="BNAME")
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	@Column(name="BFROM")
	public String getBfrom() {
		return bfrom;
	}
	public void setBfrom(String bfrom) {
		this.bfrom = bfrom;
	}
	@Column(name="BTO")
	public String getBto() {
		return bto;
	}
	public void setBto(String bto) {
		this.bto = bto;
	}
	@Column(name="AMOUNT_TOBE_PAID")
	public long getAmountToBePaid() {
		return amountToBePaid;
	}
	public void setAmountToBePaid(long amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}
	public BookWheel(int bid, String bwheelId, String bname, String bfrom, String bto, User user) {
		super();
		this.bid = bid;
		this.bwheelId = bwheelId;
		this.bname = bname;
		this.bfrom = bfrom;
		this.bto = bto;
		this.user = user;
	}
	public BookWheel(){
		
	}
	
}
