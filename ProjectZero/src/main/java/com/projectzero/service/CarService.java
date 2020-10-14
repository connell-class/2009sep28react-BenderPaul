package com.projectzero.service;
import java.util.List;

import com.projectzero.model.Car;
import com.projectzero.model.Customer;
import com.projectzero.repo.CarDao;

public class CarService {

	private CarDao cd;
	
	public CarService() {
		this(new CarDao());
	}
	
	public CarService(CarDao cd) {
		super();
		this.cd = cd;
	}
	
	public List<Car> findAll(){
		return cd.findAll();
	}

	public Car findById(Integer i) {
		return cd.findById(i);
	}
	
	public Car update(Car t) {
		return cd.update(t);
	}
	
	public Car create(Car t) {
		return cd.create(t);
	}
	
	public int delete(Integer i) {
		return cd.delete(i);
	}
	
	public int makeOffer(Integer i, Customer c) {
		return cd.makeOffer(i, c);
	}
}
