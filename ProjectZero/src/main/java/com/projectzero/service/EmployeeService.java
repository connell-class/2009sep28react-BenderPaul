package com.projectzero.service;

import java.util.List;

import com.projectzero.model.Employee;
import com.projectzero.repo.EmployeeDao;

public class EmployeeService {

	private EmployeeDao ed;
	
	public EmployeeService() {
		this(new EmployeeDao());
	}
	
	public EmployeeService(EmployeeDao ed) {
		super();
		this.ed = ed;
	}
	
	public List<Employee> findAll(){
		return ed.findAll();
	}
	
	public Employee findById(Integer i) {
		return ed.findById(i);
	}
	
	public Employee update(Employee t) {
		return ed.update(t);
	}
	
	public Employee create(Employee t) {
		return ed.create(t);
	}
	
	public int delete(Integer i) {
		return ed.delete(i);
	}
	
	public int findIdByLogin(String username) {
		return ed.findIdByLogin(username);
	}
}
