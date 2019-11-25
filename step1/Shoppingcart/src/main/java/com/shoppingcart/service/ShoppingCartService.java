package com.shoppingcart.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.shoppingcart.exception.ProductException;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.model.Product;

public class ShoppingCartService {

	private Map<Product, Integer> productMap = new HashMap<>();

	public void addProductToCart(Product product, int quantity) throws ShoppingCartException {
		if(validateInput(product, quantity)) {
			productMap.put(product, quantity);
		}
	}

	private boolean validateInput(Product product, int quantity) throws ShoppingCartException {
		boolean isValid = true;
		
		if (null == product.getCode()) {
			throw new ProductException("Product code can't be empty");
		}

		if (quantity <= 0) {
			throw new ShoppingCartException("Quantity can't be less than One");
		}
		
		return isValid;
	}

	public double checkout() throws ShoppingCartException {
		double finalPrice = 0.0;

		if (productMap.entrySet().isEmpty()) {
			throw new ShoppingCartException("Cart is empty,cart can't be empty");
		}

		for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
			finalPrice += entry.getKey().getUnitPrice() * entry.getValue();
		}
		return formatToTwoPrecision(finalPrice);
	}

	private double formatToTwoPrecision(double value) {
		BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

}
