package com.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class JunitdemoApplicationTests {

	// must needed Calculator object, because we will use object of calculator.
	private Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}


	@Test
	// Actual Result = Expected Result => Test Passed.
	void testSum(){

		// Actual Result
		int actualResult = calculator.doSum(12, 3, 2);

		// Expected Result
		int expectedResult = 17;

		assertThat(actualResult).isEqualTo(expectedResult);

	}


	@Test
	@Disabled // Should not run this method.
	// Actual Result = Expected Result => Test Passed.
	void testSum1(){

		// Actual Result
		int actualResult = calculator.doSum1(10, 5);

		// Expected Result
		int expectedResult = 15;

		assertThat(actualResult).isEqualTo(expectedResult);

	}


	@Test
	// Actual Result = Expected Result => Tested Pass
	void testMultiple(){

		// Actual Result
		int actualResult = calculator.doMultiple(12, 3);

		// Expected Result
		int expectedResult = 36;

		assertThat(actualResult).isEqualTo(expectedResult);
	}


	@Test
	// a==b => Test Passed.
	void testCompareTwoNumber(){

		// Actual Result
		boolean actualResult = calculator.doCompareTwoNumber(5, 5);

		assertThat(actualResult).isTrue();


	}

	@Test
	void testEvenOrNot(){
		boolean actualResult = calculator.isEven(10);
		assertThat(actualResult).isTrue();
	}

}
