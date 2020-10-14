package com.projectzero.controller;

import java.util.List;

import com.projectzero.model.Offers;
import com.projectzero.service.OffersService;

public class OffersController {
	private OffersService os;
	
	public OffersController() {
		this(new OffersService());
	}

	public OffersController(OffersService os) {
		super();
		this.os = os;
	}

	public List<Offers> findAll(){
		return os.findAll();
	}
	
	public Offers findById(Integer i) {
		return os.findById(i);
	}
	
	public Offers update(Offers t) {
		return os.update(t);
	}
	
	public Offers create(Offers t) {
		return os.create(t);
	}
	
	public int delete(Integer i) {
		return os.delete(i);
	}
}
