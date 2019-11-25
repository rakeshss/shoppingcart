package com.shoppingcart.service;

public class SalesTax implements Tax{

	private static final double SALES_TAX = 12.5;
	
	public double addTax(double amount) {
		return amount + amount*SALES_TAX/100;
	}
}
