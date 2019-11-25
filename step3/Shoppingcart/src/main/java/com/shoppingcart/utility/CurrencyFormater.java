package com.shoppingcart.utility;

public class CurrencyFormater {

	public static double formatToTwoPrecision(double currencyValue) {
		return Double.valueOf(String.format("%.2f", currencyValue));
	}
}
