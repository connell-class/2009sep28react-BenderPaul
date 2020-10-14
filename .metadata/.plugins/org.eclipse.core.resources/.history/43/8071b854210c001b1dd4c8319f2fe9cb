package com.projectzero.service;

import java.util.List;

import com.projectzero.model.Customer;
import com.projectzero.repo.CustomerDao;

public class CustomerService {
	private CustomerDao cd;
	
	public CustomerService() {
		this(new CustomerDao());
	}
	
	public CustomerService(CustomerDao cd) {
		super();
		this.cd = cd;
	}
	
	public List<Customer> findAll(){
		return cd.findAll();
	}
	
	public Customer findById(Integer i) {
		return cd.findById(i);
	}
	
	public Customer update(Customer t) {
		return cd.update(t);
	}
	
	public Customer create(Customer t) {
		return cd.create(t);
	}
	
	public int delete(Integer i) {
		return cd.delete(i);
	}
	
	public int findIdByLogin(String username) {
		return cd.findIdByLogin(username);
	}
}
