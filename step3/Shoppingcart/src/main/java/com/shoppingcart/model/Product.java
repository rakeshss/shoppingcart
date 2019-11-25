package com.shoppingcart.model;

import lombok.Data;

@Data
public class Product {
	private int id;
	private String name;
	private String description;
	private int categoryId;
	private double unitPrice;
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean equals(Object o) {

		if (o instanceof Product) {
			Product product = (Product) o;
			return product.getCode() == null ? Boolean.FALSE : product.getCode().equals(this.code);
		}

		return false;
	}

	public int hashCode() {
		return code.hashCode();
	}

}
