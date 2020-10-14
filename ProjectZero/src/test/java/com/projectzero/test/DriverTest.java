package com.projectzero.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.projectzero.model.Car;
import com.projectzero.model.Employee;

public class DriverTest {
	
	Car testCar = new Car(100, BigDecimal.valueOf(1010) , "super", "car", "porple", BigDecimal.valueOf(2000000), false);

	@Test
	public void testGetterColor() {
		assertEquals("porple", testCar.getColor());
	}
	@Test
	public void testGetterMake() {
		assertEquals("super", testCar.getMake());
	}
	@Test
	public void testGetterTagId() {
		assertEquals(100, testCar.getTagId());
	}
}
	
