package com.projectzero.model;
import java.math.BigDecimal;

public class SellingHistory {
	
	
	/**
	 * 
	 * The SellingHistory class exists as a way of representing the data in the sold_cars table of our database.
	 * 
	 **/
	

	private int tagId;
	private BigDecimal year;
	private String make;
	private String model;
	private String color;
	private BigDecimal sellingPrice;
	private String customerFirstName;
	private String customerLastName;
	private BigDecimal downPayment;
	private BigDecimal monthlyPayment;
	private BigDecimal remainingBalance;
	
	/*
	 *  Getters and Setters
	 */
	
	public int getTagId() {
		return tagId;
	}
	public String getCustomerFirstName() {
		return customerFirstName;
	}
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}
	public String getCustomerLastName() {
		return customerLastName;
	}
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}
	public BigDecimal getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}
	public BigDecimal getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(BigDecimal monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public BigDecimal getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(BigDecimal remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public BigDecimal getYear() {
		return year;
	}
	public void setYear(BigDecimal year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	
	public SellingHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SellingHistory(int tagId, BigDecimal year, String make, String model, String color, BigDecimal sellingPrice,
			String customerFirstName, String customerLastName, BigDecimal downPayment, BigDecimal monthlyPayment,
			BigDecimal remainingBalance) {
		super();
		this.tagId = tagId;
		this.year = year;
		this.make = make;
		this.model = model;
		this.color = color;
		this.sellingPrice = sellingPrice;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.downPayment = downPayment;
		this.monthlyPayment = monthlyPayment;
		this.remainingBalance = remainingBalance;
	}
	@Override
	public String toString() {
		return "\nCar Tag ID: " + tagId + ", Info: " + color + " " + year + " " + make + " " + model + ", Sold Price: " + sellingPrice + ", Customer Info: " + customerFirstName
				+ " " + customerLastName + ", Down Payment: " + downPayment + ", Monthly Payment: " + monthlyPayment + ", Remaining Balance: " + remainingBalance + "\n"+"--------------------------------------------------------";
	}
}