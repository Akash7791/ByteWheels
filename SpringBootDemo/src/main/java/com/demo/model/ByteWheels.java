package com.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="BYTEWHEELS")
@Table(name="BYTEWHEELS")
public class ByteWheels implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;
	@Column(name="NAME")
	private String name;
	@Column(name="CATEGORY")
	private String category;
	@Column(name="RATE")
	private int rate;
	
	public ByteWheels(){
		
	}

	public ByteWheels(String id, String name, String category, int rate) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.rate = rate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
