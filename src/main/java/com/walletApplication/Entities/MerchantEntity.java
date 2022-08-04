package com.walletApplication.Entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MerchantEntity {
	private String name;
	@Id
	private String mobileNumber;
	private String Password;
	private String address;
	private Date date;

	public MerchantEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantEntity(String name, String mobileNumber, String password, String address, Date date) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		Password = password;
		this.address = address;
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
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "MerchantEntity [name=" + name + ", mobileNumber=" + mobileNumber + ", Password=" + Password
				+ ", address=" + address + ", date=" + date + "]";
	}

}
