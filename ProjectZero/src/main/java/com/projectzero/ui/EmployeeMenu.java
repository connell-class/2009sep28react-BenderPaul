package com.projectzero.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.projectzero.controller.EmployeeController;
import com.projectzero.model.Customer;
import com.projectzero.model.Employee;

public class EmployeeMenu {
	private EmployeeController ec;
	private EmployeeDealershipMenu edm = new EmployeeDealershipMenu(ec);
	static Logger LOGGER = Logger.getLogger(CustomerMenu.class);
	
	public EmployeeMenu() {
		this(new EmployeeController());
	}
	
	public EmployeeMenu(EmployeeController ec) {
		super();
		this.ec = ec;
	}
	
	public String employeeMenu (Scanner scan) {
		String userId = null;
		String password = null;
		System.out.println("\n======================================================================================\n");
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. Login to existing Employee account");
		System.out.println("0. Return to Main Menu");
		String selection = scan.next();
		switch(selection) {
		case "1":
			System.out.println("Enter your Username");
			userId = scan.next();
			System.out.println("Enter your password:");
			password = scan.next();
			try {
				if (ec.findById(ec.findIdByLogin(userId)).getPass().equalsIgnoreCase(password)){
					LOGGER.info("Employee number "+ ec.findIdByLogin(userId) + " logged into the system.");
					edm.employeeDealershipMenu(ec.findById(ec.findIdByLogin(userId)), scan);
					break;
				}
			}catch (NullPointerException e) {
				System.out.println("Invalid Login Credentials");
				employeeMenu(scan);
			}
			break;
		case "0":
			LOGGER.info("Employee number "+ ec.findIdByLogin(userId) + " logged out of the system.");
			return null;
		default:
			System.out.println("Invalid entry");
			employeeMenu(scan);
		}
		return null;
	}			
}
