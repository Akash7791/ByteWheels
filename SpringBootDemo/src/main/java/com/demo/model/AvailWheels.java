package com.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="AVAILWHEELS")
@Table(name="AVAILWHEELS")
public class AvailWheels implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UNIQUEWID")
	private String uniqueWid; 
	@Column(name="WNAME")
	private String wname;
	@Column(name="WCAT")
	private String wcat;
	@Column(name="ISAVAIL")
	private String isAvail;
	@Column(name="BOOKED_DATE_FROM")
	private String booked_date_from;
	@Column(name="BOOKED_DATE_TO")
	private String booked_date_to;
	
	public String getUniqueWid() {
		return uniqueWid;
	}
	public void setUniqueWid(String uniqueWid) {
		this.uniqueWid = uniqueWid;
	}
	public String getBooked_date_from() {
		return booked_date_from;
	}
	public void setBooked_date_from(String booked_date_from) {
		this.booked_date_from = booked_date_from;
	}
	public String getBooked_date_to() {
		return booked_date_to;
	}
	public void setBooked_date_to(String booked_date_to) {
		this.booked_date_to = booked_date_to;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWcat() {
		return wcat;
	}
	public void setWcat(String wcat) {
		this.wcat = wcat;
	}
	public String getIsAvail() {
		return isAvail;
	}
	public void setIsAvail(String isAvail) {
		this.isAvail = isAvail;
	}
	
	public AvailWheels(String uniqueWid, String wname, String wcat, String isAvail, String booked_date_from,
			String booked_date_to) {
		super();
		this.uniqueWid = uniqueWid;
		this.wname = wname;
		this.wcat = wcat;
		this.isAvail = isAvail;
		this.booked_date_from = booked_date_from;
		this.booked_date_to = booked_date_to;
	}
	public AvailWheels(){
		
	}
}
