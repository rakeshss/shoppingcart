package com.shoppingcart.service;

import java.util.HashMap;
import java.util.Map;

import com.shoppingcart.exception.ProductException;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.model.Product;
import com.shoppingcart.utility.CurrencyFormater;

public class ShoppingCartService {

	private Map<Product, Integer> productMap = new HashMap<Product, Integer>();

	public void addProductToCart(Product product, int quantity) throws ShoppingCartException {

		if (validateInput(product, quantity)) {
			if (isProductExists(product)) {
				int existingQuantity = productMap.get(product);
				productMap.put(product, existingQuantity + quantity);
			} else {
				productMap.put(product, quantity);
			}
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

	private boolean isProductExists(Product product) {
		return productMap.get(product) != null;
	}

	public double checkout() {
		double finalPrice = 0.0;

		for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
			finalPrice += entry.getKey().getUnitPrice() * entry.getValue();
		}
		return CurrencyFormater.formatToTwoPrecision(finalPrice);
	}

	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

}
