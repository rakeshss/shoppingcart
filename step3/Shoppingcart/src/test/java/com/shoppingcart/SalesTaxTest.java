package com.shoppingcart;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.shoppingcart.service.SalesTax;

@RunWith(JUnitPlatform.class)
public class SalesTaxTest {

	private SalesTax testInstance;
	
	@BeforeEach 
	public void init(){
		testInstance = new SalesTax();
	}
	
	@Test
	public void testAddTax() {
		assertThat(314.955, is(equalTo(testInstance.addTax(279.96))));
	}
}
