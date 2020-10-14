package com.projectzero.controller;

import java.util.ArrayList;
import java.util.List;

import com.projectzero.model.Customer;
import com.projectzero.model.SellingHistory;
import com.projectzero.service.SellingHistoryService;

public class SellingHistoryController {
	
	/*
	 * 
	 * The controller package acts a communication medium between the UI and the service package. 
	 * 
	 * There is a controller class for each table that exists in the database.
	 * 
	 * The Implementation for each method is described in the Corresponding DAO class.
	 */
	
	//private Customer c;
	private SellingHistoryService shs;
	
	
	public SellingHistoryController() {
		this(new SellingHistoryService());
	}

	public SellingHistoryController(SellingHistoryService shs) {
		super();
		this.shs = shs;
	}

	public List<SellingHistory> findAll(){
		return shs.findAll();
	}
	
	public SellingHistory findById(Integer i) {
		return shs.findById(i);
	}
	
	public SellingHistory update(SellingHistory t) {
		return shs.update(t);
	}
	
	public SellingHistory create(SellingHistory t) {
		return shs.create(t);
	}
	
	public int delete(Integer i) {
		return shs.delete(i);
	}
	public ArrayList<String> getCustomerCars(Customer c){
		return shs.getCustomerCars(c);
	}
	public SellingHistory makePayment(SellingHistory t) {
		return shs.makePayment(t);
	}
}
