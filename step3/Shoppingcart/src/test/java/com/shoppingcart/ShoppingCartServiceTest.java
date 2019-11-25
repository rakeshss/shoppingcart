package com.shoppingcart;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.shoppingcart.exception.ShoppingCartException;
import com.shoppingcart.model.Product;
import com.shoppingcart.service.SalesTax;
import com.shoppingcart.service.ShoppingCartService;
import com.shoppingcart.service.Tax;
import com.shoppingcart.utility.CurrencyFormater;

@RunWith(JUnitPlatform.class)
public class ShoppingCartServiceTest {

	private ShoppingCartService testInstance;
	private String DOVE_SOAP = "DOVE_SOAP";
	private String AXE_PERFUME = "AXE_PERFUME";

	@BeforeEach
	public void init() {
		testInstance = new ShoppingCartService();
	}

	@DisplayName("Given emptyCart When a product is added Then it should be stored in cart")
	@Test
	public void emptyCartScenario_AddProductToCart() throws ShoppingCartException {
		Product doapSoap = getDoveProduct();
		int quantity = 5;

		testInstance.addProductToCart(doapSoap, quantity);

		assertAll("Verify Product Map properties",
				() -> assertThat(1, is(equalTo(testInstance.getProductMap().size()))),
				() -> assertThat(5, is(equalTo(testInstance.getProductMap().get(doapSoap)))),
				() -> assertThat(DOVE_SOAP, is(equalTo(doapSoap.getCode()))));
	}

	@DisplayName("Given a empty cart When a same product added twice Then quantity should be adjusted accordingly")
	@Test
	public void addSameProductTwiceToShoppingCart() throws ShoppingCartException {
		Product doapSoap = getDoveProduct();
		int quantity = 5;

		Product doapSoap2 = getDoveProduct();
		int newQuantity = 3;

		testInstance.addProductToCart(doapSoap, quantity);
		testInstance.addProductToCart(doapSoap2, newQuantity);

		assertAll("Verify Product Map properties",
				() -> assertThat(1, is(equalTo(testInstance.getProductMap().size()))),
				() -> assertThat(8, is(equalTo(testInstance.getProductMap().get(doapSoap)))),
				() -> assertThat(DOVE_SOAP, is(equalTo(doapSoap.getCode()))));
	}

	@DisplayName("Given a empty cart When a same product added twice, Then checkout should return added invoice amount")
	@Test
	public void emptyCartScenario_Checkout() throws ShoppingCartException {
		Product doapSoap = getDoveProduct();
		int quantity = 5;

		Product doapSoap2 = getDoveProduct();
		int newQuantity = 3;

		testInstance.addProductToCart(doapSoap, quantity);
		testInstance.addProductToCart(doapSoap2, newQuantity);

		double expectedValue = testInstance.checkout();

		assertThat(319.92, is(equalTo(expectedValue)));
	}

	@DisplayName("Given a empty cart When a different product added, Then checkout should return added invoice amount")
	@Test
	public void addDifferentProductToShoppingCart() throws ShoppingCartException {
		Product doapSoap = getDoveProduct();
		int quantity = 2;

		Product axeProduct = getAxeProduct();
		int axeProductQuantity = 2;

		testInstance.addProductToCart(doapSoap, quantity);
		testInstance.addProductToCart(axeProduct, axeProductQuantity);

		assertAll("Verify Product Map properties",
				() -> assertThat(2, is(equalTo(testInstance.getProductMap().size()))),
				() -> assertThat(2, is(equalTo(testInstance.getProductMap().get(doapSoap)))),
				() -> assertThat(2, is(equalTo(testInstance.getProductMap().get(axeProduct)))));
	}

	@DisplayName("Given a empty cart When a different product added, then quantity should be adjusted accordingly")
	@Test
	public void checkOutDifferentProductToShoppingCart() throws ShoppingCartException {
		Tax tax = new SalesTax();
		
		Product doapSoap = getDoveProduct();
		int quantity = 2;

		Product axeProduct = getAxeProduct();
		int axeProductQuantity = 2;

		testInstance.addProductToCart(doapSoap, quantity);
		testInstance.addProductToCart(axeProduct, axeProductQuantity);
		double checkOutValue = testInstance.checkout();

		double expectedValue = tax.addTax(checkOutValue);

		assertThat(314.96, is(equalTo(CurrencyFormater.formatToTwoPrecision(expectedValue))));
	}

	private Product getDoveProduct() {
		Product product = new Product();
		product.setName("Dove");
		product.setDescription("Dove Soap");
		product.setCategoryId(1);
		product.setUnitPrice(39.99);
		product.setCode(DOVE_SOAP);
		return product;
	}

	private Product getAxeProduct() {
		Product product = new Product();
		product.setName("Axe Deos");
		product.setDescription("Axe perfume");
		product.setCategoryId(2);
		product.setUnitPrice(99.99);
		product.setCode(AXE_PERFUME);
		return product;
	}
}
