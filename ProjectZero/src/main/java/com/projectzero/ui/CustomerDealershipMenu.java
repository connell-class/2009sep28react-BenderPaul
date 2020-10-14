package com.projectzero.ui;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.projectzero.Driver;
import com.projectzero.controller.CarController;
import com.projectzero.controller.CustomerController;
import com.projectzero.controller.SellingHistoryController;
import com.projectzero.model.Customer;

public class CustomerDealershipMenu {
	
	/**
	 * 
	 * This is the main dealership page for accessing features as a customer
	 * This page is accessible by successfully logging in as a customer
	 **/
	
	static Logger LOGGER = Logger.getLogger(CustomerDealershipMenu.class);
	private SellingHistoryController shc = new SellingHistoryController();
	private CarController carCont = new CarController();
	private CustomerController cc;
	
	public CustomerDealershipMenu() {
		this(new CustomerController());
	}
	
	public CustomerDealershipMenu(CustomerController cc) {
		super();
		this.cc = cc;
	}
	
	
	
	public String customerDealershipMenu(Customer c, Scanner scan) {
		/**
		 * 
		 * The following options will pull, alter, update data from the database.
		 **/
		System.out.println("\n======================================================================================\n");
		System.out.println("\n          Welcome to the Dealership, "+ c.getFirstName() + "!\n");
		System.out.println("1. View cars in the lot            2. Make an offer on a car");
		System.out.println("3. View cars I own                 4. View my remaining payments for a car");
		System.out.println("5. Make a payment on a car I own   0. Logout");
		String select = scan.next();
		switch (select){
			case "1":
				LOGGER.info("Customer viewing cars in lot");
				System.out.println(carCont.findAll().toString());
				customerDealershipMenu(c, scan);
				break;
			case "2":
				System.out.println("Input the Tag ID of the car you'd like to make an offer on:");
				String offer_amount = scan.next();
				carCont.makeOffer(Integer.parseInt(offer_amount), c);
				customerDealershipMenu(c, scan);
				break;
			case "3":
				System.out.println("Retreiving your cars...");
				System.out.println(shc.getCustomerCars(c).toString());
				customerDealershipMenu(c, scan);
				break;
			case "4":
				System.out.println("Retreiving your remaining balance...");	
				ArrayList<String> customersCars = shc.getCustomerCars(c);
				for (int i = 0; i < customersCars.size(); i++) {
					String[] carArray = customersCars.get(i).split("\\,()");
					System.out.println("Car with Tag " + carArray[0].toString() + " has a remaining balance of: " + carArray[10].toString() + ", to be paid with at a rate of " + carArray[9].toString() + " dollars per month.");
				}
				customerDealershipMenu(c, scan);
				break;
			case "5": 
				System.out.println(shc.getCustomerCars(c).toString());
				System.out.println("For which car would you like to submit a monthly payment?");
				System.out.println("Please enter the number coinciding with the desired selection: ");
				Integer choice = scan.nextInt();
				shc.makePayment(shc.findById(choice));
				customerDealershipMenu(c, scan);
				break;
			case "0":
				LOGGER.info("Employee number "+ c.getCustomerId() + " logged out of the system.");
				System.out.println("\nLogging out..");
				break;
			default:
				System.out.println("Invalid selection");
				customerDealershipMenu(c, scan);
				break;
		}
		
		
		
		return null;
	}
	}

	//constructor with a parameter for customer
	
//DONT FORGET A LOGOUT OPTION