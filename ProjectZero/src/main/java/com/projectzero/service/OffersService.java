package com.projectzero.service;

import java.util.List;

import com.projectzero.model.Offers;
import com.projectzero.repo.OffersDao;

public class OffersService {

		private OffersDao od;
		
		public OffersService() {
			this(new OffersDao());
		}

		public OffersService(OffersDao od) {
			super();
			this.od = od;
		}

		public List<Offers> findAll(){
			return od.findAll();
		}
		
		public Offers findById(Integer i) {
			return od.findById(i);
		}
		
		public Offers update(Offers t) {
			return od.update(t);
		}
		
		public Offers create(Offers t) {
			return od.create(t);
		}
		
		public int delete(Integer i) {
			return od.delete(i);
		}
//		public ArrayList<String> getCustomerCars(Customer c){
//			return shd.getCustomerCars(c);
//		}
//		public SellingHistory makePayment(SellingHistory t) {
//			return shd.makePayment(t);
//		}
}
