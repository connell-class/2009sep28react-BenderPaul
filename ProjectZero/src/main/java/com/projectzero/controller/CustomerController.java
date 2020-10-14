package com.projectzero.controller;

import java.util.List;

import com.projectzero.model.Customer;
import com.projectzero.service.CustomerService;


/*
 * 
 * The controller package acts a communication medium between the UI and the service package. 
 * 
 * There is a controller class for each table that exists in the database.
 * 
 * The Implementation for each method is described in the Corresponding DAO class.
 */

public class CustomerController {
	private CustomerService cs;

	public CustomerController() {
		this(new CustomerService());
	}

	public CustomerController(CustomerService cs) {
		super();
		this.cs = cs;
	}	
	
	public List<Customer> findAll(){
		return cs.findAll();
	}
	
	public Customer findById(Integer i) {
		return cs.findById(i);
	}
	
	public Customer update(Customer t) {
		return cs.update(t);
	}
	
	public Customer create(Customer t) {
		return cs.create(t);
	}
	
	public int delete(Integer i) {
		return cs.delete(i);
	}
	
	public int findIdByLogin(String username) {
		return cs.findIdByLogin(username);
	}
}
