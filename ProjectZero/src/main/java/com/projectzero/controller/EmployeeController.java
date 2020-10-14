package com.projectzero.controller;

import java.util.List;

import com.projectzero.model.Employee;
import com.projectzero.service.EmployeeService;

public class EmployeeController {
	private EmployeeService es;
	
	/*
	 * 
	 * The controller package acts a communication medium between the UI and the service package. 
	 * 
	 * There is a controller class for each table that exists in the database.
	 * 
	 * The Implementation for each method is described in the Corresponding DAO class.
	 */
	
	public EmployeeController() {
		this(new EmployeeService());
	}
	
	public EmployeeController(EmployeeService es) {
		super();
		this.es = es;
	}
	
	public List<Employee> findAll(){
		return es.findAll();
	}
	
	public Employee findById(Integer i) {
		return es.findById(i);
	}
	
	public Employee update(Employee t) {
		return es.update(t);
	}
	
	public Employee create(Employee t) {
		return es.create(t);
	}
	
	public int delete(Integer i) {
		return es.delete(i);
	}
	public int findIdByLogin(String username) {
		return es.findIdByLogin(username);
	}
}