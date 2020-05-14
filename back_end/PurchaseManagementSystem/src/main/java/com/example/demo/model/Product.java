package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pId;
	private String pName;
	private String pDescription;
	private LocalDate pManufactureDate;
	private LocalDate pExpiryDate;
	private int pQuantity;
	private double pSellingPrice;
	private double pCostPrice;
	private int sId;
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDescription() {
		return pDescription;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public LocalDate getpManufactureDate() {
		return pManufactureDate;
	}
	public void setpManufactureDate(LocalDate pManufactureDate) {
		this.pManufactureDate = pManufactureDate;
	}
	public LocalDate getpExpiryDate() {
		return pExpiryDate;
	}
	public void setpExpiryDate(LocalDate pExpiryDate) {
		this.pExpiryDate = pExpiryDate;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	public double getpSellingPrice() {
		return pSellingPrice;
	}
	public void setpSellingPrice(double pSellingPrice) {
		this.pSellingPrice = pSellingPrice;
	}
	public double getpCostPrice() {
		return pCostPrice;
	}
	public void setpCostPrice(double pCostPrice) {
		this.pCostPrice = pCostPrice;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	
	
}
