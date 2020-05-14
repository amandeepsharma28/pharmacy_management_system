package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="orders")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int soId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate soDate;
	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private String customerPhone;
	private String soStatus;
	private double subTotal;
	private double tax;
	private double total;
	private int sellerId;
	private int totalQuantity;
   @OneToMany(mappedBy="orderId", cascade= {CascadeType.ALL}, fetch=FetchType.EAGER)
   private Set<OrderProducts> orderProducts;

	public Order() {
	super();
	// TODO Auto-generated constructor stub
}


	public Order(int soId, LocalDate soDate, String customerName, String customerAddress, String customerEmail,
			String customerPhone, String soStatus, double subTotal, double tax, double total, int sellerId,
			int totalQuantity, Set<OrderProducts> orderProducts) {
		super();
		this.soId = soId;
		this.soDate = soDate;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmail = customerEmail;
		this.customerPhone = customerPhone;
		this.soStatus = soStatus;
		this.subTotal = subTotal;
		this.tax = tax;
		this.total = total;
		this.sellerId = sellerId;
		this.totalQuantity = totalQuantity;
		this.orderProducts = orderProducts;
	}


	public int getSoId() {
		return soId;
	}
	public void setSoId(int soId) {
		this.soId = soId;
	}
	public LocalDate getSoDate() {
		return soDate;
	}
	public void setSoDate(LocalDate soDate) {
		this.soDate = soDate;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getSoStatus() {
		return soStatus;
	}
	public void setSoStatus(String soStatus) {
		this.soStatus = soStatus;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public Set<OrderProducts> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(Set<OrderProducts> orderProducts) {
		this.orderProducts = orderProducts;
	}
	
}
