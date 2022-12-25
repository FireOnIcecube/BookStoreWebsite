package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator(10,2);
		
		int testValue =  calculator.Add();
		int expected = 2;
		
		assertNotNull(testValue);
	}

}
