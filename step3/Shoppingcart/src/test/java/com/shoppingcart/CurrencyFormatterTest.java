package com.shoppingcart;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.shoppingcart.utility.CurrencyFormater;

@RunWith(JUnitPlatform.class)
public class CurrencyFormatterTest {
	
	@ParameterizedTest
	@MethodSource("provideDifferentCurrency")
	public void testCurrencyFormatterPrecision1(double input,double expectedResult) {
		assertThat(expectedResult, is(equalTo(CurrencyFormater.formatToTwoPrecision(input))));
		
	}
	
	private static Stream<Arguments> provideDifferentCurrency() {
		return Stream.of(Arguments.of(0.564, 0.56)
				, Arguments.of(0.567,0.57)
				, Arguments.of(314.955,314.96)
				);
	}
}
