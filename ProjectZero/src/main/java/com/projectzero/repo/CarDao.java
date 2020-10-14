package com.projectzero.repo;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.projectzero.config.EnvironmentConnectionUtil;
import com.projectzero.model.Car;
import com.projectzero.model.Customer;


public class CarDao implements DaoContract<Car, Integer>{
		
	/**
	 * 
	 * The CarDao class implements the DaoContract, and allows us to implement methods between the tables within our database and the Objects in our application.
	 **/
		
	public CarDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Car> findAll() {
		List<Car> cars = new LinkedList<>();
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection()){
			Statement s = conn.createStatement();
			String sql = "select * from cars";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				cars.add(new Car(rs.getInt(1), rs.getBigDecimal(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getBoolean(7)));
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}

	@Override
	public Car findById(Integer i) {
		Car findCar = null;
		String sqlQuery = "select * from cars where tag_id = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				findCar = new Car(rs.getInt(1), rs.getBigDecimal(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBigDecimal(6), rs.getBoolean(7));
			}
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return findCar;
	}

	@Override
	public Car update(Car t) {
		int price = 0;
		Scanner newPrice = new Scanner(System.in) ;
		System.out.println("Enter new price: ");
		try {
			price = newPrice.nextInt();
		} catch (InputMismatchException e){
			System.out.println("Invalid format: Enter a price from 0 - 2147483647\n");
			update(t);
		}
		String sqlQuery = "update cars set default_price = ? where tag_id = ?";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, price);
			ps.setInt(2, t.getTagId());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//newPrice.close();
		System.out.println("\nCar price successfully updated.");
		return t;
	}

	@Override
	public Car create(Car t) {
		Scanner carDetails = new Scanner(System.in);
		String sqlQuery = "call add_new_car_to_lot(?,?,?,?,?,?)";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){	
			
			try {
			
			System.out.println("\nEnter new car's Year: ");
			ps.setBigDecimal(1,carDetails.nextBigDecimal());
			System.out.println("\nEnter new car's Make: ");
			ps.setString(2,carDetails.next());
			System.out.println("\nEnter new car's Model: ");
			ps.setString(3,carDetails.next());
			System.out.println("\nEnter new car's Color: ");
			ps.setString(4,carDetails.next());
			System.out.println("\nEnter new car's Initial Price: ");
			ps.setBigDecimal(5,carDetails.nextBigDecimal());
			System.out.println("\nDoes the new car already have a purchase offer? true/false: ");
			ps.setBoolean(6,(boolean)carDetails.nextBoolean());
			
			}catch (InputMismatchException e) {
				System.out.println("Invalid format Entered. Please try again.\n");
				create(t);
			}
		
			ps.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//carDetails.close();
		System.out.println("\nCar added successfully\n");
		return t;
	}

	@Override
	public int delete(Integer i) {
		BigDecimal price = null;
		Scanner soldPrice = new Scanner(System.in);
		//Ask the user how much car sold for
		System.out.println("Enter the sell price: ");
		try {
			price = soldPrice.nextBigDecimal();
		} catch (InputMismatchException e){
			System.out.println("Invalid format: Enter a price from 0 - 2147483647\n");
			delete(i);
		}
		String sqlQuery = "call sell_car(?,?,?,?,?,?,?)";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, i);
			ps.setBigDecimal(2, price);
			System.out.println("Enter customer's first name");
			ps.setString(3, soldPrice.next());
			System.out.println("Enter customer's last name");
			ps.setString(4, soldPrice.next());
			System.out.println("Enter down payment");
			ps.setBigDecimal(5, soldPrice.nextBigDecimal());
			System.out.println("Enter monthly payment");
			ps.setBigDecimal(6, soldPrice.nextBigDecimal());
			System.out.println("Enter remaining Balance");
			ps.setBigDecimal(7, soldPrice.nextBigDecimal());
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//soldPrice.close();
		System.out.println("\nCar successfully sold. Records added to database");
		return i;
	}
	
	public int makeOffer(Integer i, Customer c) {
		BigDecimal offer = null;
		Scanner offerAmount = new Scanner(System.in);
		System.out.println("How much would you like to offer?");
		try {
			offer = offerAmount.nextBigDecimal();
		} catch (InputMismatchException e){
			System.out.println("Invalid format: Enter a price from 0 - 2147483647\n");
			makeOffer(i,c);
		}
		String sqlQuery = "call add_existing_car_to_offer_table(?,?,?,?)";
		try (Connection conn = EnvironmentConnectionUtil.getInstance().getConnection(); PreparedStatement ps = conn.prepareStatement(sqlQuery)){
			ps.setInt(1, i);
			ps.setBigDecimal(2, offer);
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//offerAmount.close();
		System.out.println("\nOffer submitted");
		return i;
	}
}


