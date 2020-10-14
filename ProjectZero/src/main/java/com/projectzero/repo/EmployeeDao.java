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
import com.projectzero.model.Employee;

public class EmployeeDao implements DaoContract<Employee, Integer>{ 
	
	/**
	 * 
	 * The CarDao class implements the DaoContract, and allows us to implement methods between the tables within our database and the Objects in our application.
	 **/

public EmployeeDao() {
	super();
	// TODO Auto-generated constructor stub
}

@Override
public List<Employee> findAll() {
	List<Employee> employees = new LinkedList<>();
	String sqlQuery = "select * from employees";
	try(Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
		//ps.setString(1, "employees");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
		}
		rs.close();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return employees;
}


@Override
public Employee findById(Integer i) {
	Employee findEmployee = null;
	String sqlQuery = "select * from employees where employee_id = ?";
	try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			findEmployee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
		}
		rs.close();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return findEmployee;
}

public int findIdByLogin(String username) {
	int loginId = 0;
	String sqlQuery = "select employee_id from employees where username = ?";
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
public Employee update(Employee t) {
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
	String sqlQuery = "update employees set ? = ? where employee_id = ?";
	try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
		ps.setString(1, updatingValue);
		ps.setString(2, updateValueTo);
		ps.setInt(3, t.getEmployeeId());
		ps.executeUpdate();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	whatToUpdate.close();
	System.out.println("\nCredentials updated successfully.");
	return t;
}

@Override
public Employee create(Employee t) {
	Scanner employeeRegistration = new Scanner(System.in);
	String sqlQuery = "insert into employees (employee_id, first_name, last_name, username, pass, access_level) values (default, ?, ?, ?, ?, 1)";
	try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){	
		
		try {
		
		System.out.println("\nEnter employee's first name: ");
		ps.setString(1, employeeRegistration.next());
		System.out.println("\nEnter employee's last name: ");
		ps.setString(2,employeeRegistration.next());
		System.out.println("\nEnter employee's account username: ");
		ps.setString(3,employeeRegistration.next());
		System.out.println("\nEnter employee's account password: ");
		ps.setString(4,employeeRegistration.next());
		
		}catch (InputMismatchException e) {
			System.out.println("Invalid format Entered. Please try again.\n");
			create(t);
		}
		ps.execute();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	//employeeRegistration.close();
	System.out.println("\nSuccessfully registered new employee\n");
	return t;
}

@Override
public int delete(Integer i) {
	String confirmation = null;
	String sqlQuery = "delete from employees where employee_id = ?";
	Scanner deleteEmployee = new Scanner(System.in);
	System.out.println("Are you sure you want to delete employee with ID =" + i + "?  y/n");
	try {
		confirmation = deleteEmployee.next();
	} catch (InputMismatchException e){
		System.out.println("Invalid format: Enter y or n.\n");
		delete(i);
	}
	switch(confirmation) {
		case "y":
			try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
				ps.setInt(1, i);
				ps.execute();
				System.out.println("\n Employee successfully deleted.\n");
				//deleteEmployee.close();
				return i;
			}catch (SQLException e) {
				e.printStackTrace();
			}
		case "n":
			deleteEmployee.close();
			return i;
		default:
			System.out.println("Invalid input. Please try again.");
			delete(i);
		}
		//deleteEmployee.close();
		return i;
	}
}
