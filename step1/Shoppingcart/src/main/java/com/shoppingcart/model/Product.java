package com.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String name;
	private String description;
	private int categoryId;
	private double unitPrice;
	private String code;

	public boolean equals(Object o) {

		if (o != null && o instanceof Product) {
			Product product = (Product) o;
			return product.getCode() == null ? Boolean.FALSE : product.getCode().equals(code);
		}

		return false;
	}
}
