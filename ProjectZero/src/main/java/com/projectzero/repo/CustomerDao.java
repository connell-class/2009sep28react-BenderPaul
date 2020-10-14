package com.projectzero.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.projectzero.config.EnvironmentConnectionUtil;
import com.projectzero.model.Customer;

public class CustomerDao implements DaoContract<Customer, Integer> {
	
	/**
	 * 
	 * The CarDao class implements the DaoContract, and allows us to implement methods between the tables within our database and the Objects in our application.
	 **/
	
	public CustomerDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new LinkedList<>();
		String sqlQuery = "select * from ?";
		try(Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
		    ps.setString(1, "customers");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer findById(Integer i) {
		Customer findCustomer = null;
		String sqlQuery = "select * from customers where customer_id = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				findCustomer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return findCustomer;
	}
	
	public int findIdByLogin(String username) {
		int loginId = 0;
		String sqlQuery = "select customer_id from customers where username = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				loginId = rs.getInt(1);
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return loginId;
	}

	@Override
	public Customer update(Customer t) {
		String updatingValue = null;
		String updateValueTo = null;
		Scanner whatToUpdate = new Scanner(System.in) ;
		System.out.println("What would you like to update?\n");
		System.out.println("1. Password");
		System.out.println("2. First Name");
		System.out.println("3. Last name");
		System.out.println("0: Exit");
		switch(whatToUpdate.nextLine()) {
		case "1":
			updatingValue = "pass";
			System.out.println("Set new password as: ");
			updateValueTo = whatToUpdate.nextLine();
		case "2":
			updatingValue = "first_name";
			System.out.println("Set first name as: ");
			updateValueTo = whatToUpdate.nextLine();
		case "3":
			updatingValue = "last_name";
			System.out.println("Set last name as: ");
			updateValueTo = whatToUpdate.nextLine();
		case "0":
			break;
		default:
			System.out.println("Invalid response. Try again.");
			update(t);
		}
		String sqlQuery = "update customers set ? = ? where customer_id = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setString(1, updatingValue);
			ps.setString(2, updateValueTo);
			ps.setInt(3, t.getCustomerId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\nCredentials updated successfully.");
		return t;
	}

	@Override
	public Customer create(Customer t) {
		Scanner customerRegistration = new Scanner(System.in);
		String sqlQuery = "insert into customers (customer_id, first_name, last_name, username, pass) values (default, ?, ?, ?, ?)";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){	
			
			try {
			
			System.out.println("\nEnter your first name: ");
			ps.setString(1, customerRegistration.next());
			System.out.println("\nEnter your last name: ");
			ps.setString(2,customerRegistration.next());
			System.out.println("\nEnter your desired username: ");
			ps.setString(3,customerRegistration.next());
			System.out.println("\nEnter your desired password: ");
			ps.setString(4,customerRegistration.next());
			
			}catch (InputMismatchException e) {
				System.out.println("Invalid format Entered. Please try again.\n");
				create(t);
			}
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\nSuccessfully registered new customer\n");
		return t;
	}

	@Override
	public int delete(Integer i) {
		String confirmation = null;
		String sqlQuery = "delete from customers where customer_id = ?";
		Scanner deleteCustomer = new Scanner(System.in);
		System.out.println("Are you sure you want to delete customer with ID =" + i + "?  y/n");
		try {
			confirmation = deleteCustomer.next();
		} catch (InputMismatchException e){
			System.out.println("Invalid format: Enter y or n.\n");
			delete(i);
		}
		switch(confirmation) {
			case "y":
				try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
					ps.setInt(1, i);
					ps.execute();
					System.out.println("\n Customer successfully deleted.\n");
					deleteCustomer.close();
					return i;
				}catch (SQLException e) {
					e.printStackTrace();
				}
			case "n":
				deleteCustomer.close();
				return i;
		}
		return i;
	}
}