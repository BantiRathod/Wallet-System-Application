package com.walletApplication.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class UserEntity {

	@Size(min = 4, max = 30)
	private String name;
	@Id
	@Size(min = 10, max = 10)
	private String mobileNumber;
	@Size(min = 5, max = 10)
	private String password;
	private Date date;

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String name, String mobileNumber, String password, Date date) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", mobileNumber=" + mobileNumber + ", password=" + password + ", date="
				+ date + "]";
	}

}
