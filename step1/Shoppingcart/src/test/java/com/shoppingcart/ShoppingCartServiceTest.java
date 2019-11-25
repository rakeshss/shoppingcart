package com.shoppingcart;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.shoppingcart.exception.ProductException;
import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.model.Product;
import com.shoppingcart.service.ShoppingCartService;

@RunWith(JUnitPlatform.class)
public class ShoppingCartServiceTest {

	private ShoppingCartService testInstance;
	private String DOVE_SOAP = "DOVE_SOAP";

	@BeforeEach
	public void init() {
		testInstance = new ShoppingCartService();
	}

	@DisplayName("Given emptyCart When a product is added Then it should be stored in cart")
	@Test
	public void addProductToEmptyCart() throws ShoppingCartException {
		Product doapSoap = getProduct();
		int quantity = 5;

		testInstance.addProductToCart(doapSoap, quantity);

		assertAll("Verify Product Map properties",
				() -> assertThat(1, is(equalTo(testInstance.getProductMap().size()))),
				() -> assertThat(5, is(equalTo(testInstance.getProductMap().get(doapSoap)))),
				() -> assertThat(DOVE_SOAP, is(equalTo(doapSoap.getCode()))));
	}

	@DisplayName("Given a product with empty code When added Then it should throw exception")
	@Test
	public void addProductToEmptyCartWithNoProductCode_shouldThrowException() {

		Product doapSoap = new Product();
		int quantity = 5;

		assertThrows(ProductException.class, () -> {
			testInstance.addProductToCart(doapSoap, quantity);
		});
	}
	
	@DisplayName("Given a product with empty code When added Then it should throw exception")
	@Test
	public void addProductToEmptyCartWithNoZeroQuantity_shouldThrowException() {
		Product doapSoap = getProduct();
		int quantity = 0;

		assertThrows(ShoppingCartException.class, () -> {
			testInstance.addProductToCart(doapSoap, quantity);
		});
	}

	@DisplayName("Given a product is added and When CheckOut Then product price amount should match the final invoice amount")
	@Test
	public void checkoutWhenProductAdded() throws ShoppingCartException {
		Product doapSoap = getProduct();
		int quantity = 5;

		testInstance.addProductToCart(doapSoap, quantity);
		double expectedValue = testInstance.checkout();

		assertThat(199.95, is(equalTo(expectedValue)));
	}

	@DisplayName("Given a empty cart and When CheckOut Then it should throw exception")
	@Test
	public void checkOutWhenCartIsEmpty_shouldThrowException() {

		assertThrows(ShoppingCartException.class, () -> {
			testInstance.checkout();
		});
	}

	private Product getProduct() {
		Product product = new Product();
		product.setName("Dove");
		product.setDescription("Dove Soap");
		product.setCategoryId(1);
		product.setUnitPrice(39.99);
		product.setCode(DOVE_SOAP);
		return product;
	}
}
