package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pId;
	private String pName;
	private String pDescription;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate pManufactureDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	public void setpName(String pName) {
		this.pName = pName;
	}
	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}
	public void setpManufactureDate(LocalDate pManufactureDate) {
		this.pManufactureDate = pManufactureDate;
	}
	public void setpExpiryDate(LocalDate pExpiryDate) {
		this.pExpiryDate = pExpiryDate;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	public void setpSellingPrice(double pSellingPrice) {
		this.pSellingPrice = pSellingPrice;
	}
	public void setpCostPrice(double pCostPrice) {
		this.pCostPrice = pCostPrice;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getpName() {
		return pName;
	}
	public String getpDescription() {
		return pDescription;
	}
	public LocalDate getpManufactureDate() {
		return pManufactureDate;
	}
	public LocalDate getpExpiryDate() {
		return pExpiryDate;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public double getpSellingPrice() {
		return pSellingPrice;
	}
	public double getpCostPrice() {
		return pCostPrice;
	}
	public int getsId() {
		return sId;
	}
	
}
