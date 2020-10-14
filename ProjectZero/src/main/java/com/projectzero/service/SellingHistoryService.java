package com.projectzero.service;

import java.util.ArrayList;
import java.util.List;

import com.projectzero.model.Customer;
import com.projectzero.model.SellingHistory;
import com.projectzero.repo.SellingHistoryDao;

public class SellingHistoryService {

	private SellingHistoryDao shd;
	
	public SellingHistoryService() {
		this(new SellingHistoryDao());
	}

	public SellingHistoryService(SellingHistoryDao shd) {
		super();
		this.shd = shd;
	}

	public List<SellingHistory> findAll(){
		return shd.findAll();
	}
	
	public SellingHistory findById(Integer i) {
		return shd.findById(i);
	}
	
	public SellingHistory update(SellingHistory t) {
		return shd.update(t);
	}
	
	public SellingHistory create(SellingHistory t) {
		return shd.create(t);
	}
	
	public int delete(Integer i) {
		return shd.delete(i);
	}
	public ArrayList<String> getCustomerCars(Customer c){
		return shd.getCustomerCars(c);
	}
	public SellingHistory makePayment(SellingHistory t) {
		return shd.makePayment(t);
	}
}
