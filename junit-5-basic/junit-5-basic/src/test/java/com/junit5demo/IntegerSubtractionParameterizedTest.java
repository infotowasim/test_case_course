package com.junit5demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerSubtractionParameterizedTest {

    // There are 3 way to getting multiple value .
    // 1st way using @MethodSource("integerSubtractionInputParameters") and private static Stream<Arguments> integerSubtractionInputParameters(){}

//    @DisplayName("Test Interger Subtraction")
//    @ParameterizedTest
//    @MethodSource("integerSubtractionInputParameters")
//    public void integerSubtraction(int a, int b, int expectedResult){
//
//        System.out.println("Running Test"+a+"-"+b+ "=" + expectedResult);
//        MathUtils mathUtils = new MathUtils();
//
//        int actualResult = mathUtils.subtract(a, b);
//        assertEquals(expectedResult,actualResult,  ()-> a + "-" + b + "did not produce" + expectedResult );
//
//
//    }
//
//    private static Stream<Arguments> integerSubtractionInputParameters(){
//        return Stream.of(
//                Arguments.of(33,1,32),
//                Arguments.of(54,1,53),
//                Arguments.of(24,1,23)
//        );
//    }



    // 2nd way using @CsvSource({})

//    @DisplayName("Test Interger Subtraction")
//    @ParameterizedTest
////    @CsvSource({
////            "33,1,32",
////            "54,1,53",
////            "24,1,23"
////    })
//
//
//    public void integerSubtraction(int a, int b, int expectedResult){
//
//        System.out.println("Running Test"+a+"-"+b+ "=" + expectedResult);
//        MathUtils mathUtils = new MathUtils();
//
//        int actualResult = mathUtils.subtract(a, b);
//        assertEquals(expectedResult,actualResult,  ()-> a + "-" + b + "did not produce" + expectedResult );
//
//    }



    // 3rd way using @CsvFileSource(resources = "/integerDivision.csv") file

    @DisplayName("Test Interger Subtraction")
    @ParameterizedTest
    @CsvFileSource(resources = "/integerDivision.csv")

    public void integerSubtraction(int a, int b, int expectedResult){

        System.out.println("Running Test"+a+"-"+b+ "=" + expectedResult);
        MathUtils mathUtils = new MathUtils();

        int actualResult = mathUtils.subtract(a, b);
        assertEquals(expectedResult,actualResult,  ()-> a + "-" + b + "did not produce" + expectedResult );

    }


    @ParameterizedTest
    @ValueSource(strings = {"Wasim","Boni","Rahul"})
    void valueSourceDemonstraction(String firstName){
        System.out.println(firstName);
        assertNotNull(firstName);

    }





}
