package com.junit5demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class DemoRepeatedTest {

    MathUtils mathUtils;

    @BeforeEach
    public void beforeEachTestMethod(){
        System.out.println("Execute Before each method");
        mathUtils = new MathUtils();
    }

    @DisplayName("Division By Zero")
    @RepeatedTest(value = 3, name ="{displayName}.Repetition{currentRepetition} of " + "{totalRepetitions}")  // it should run 3 times.
    void testIntegerDivision(RepetitionInfo repetitionInfo, TestInfo testInfo){

        System.out.println(" Running " + testInfo.getTestMethod().get().getName());
        System.out.println("Repetition # " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());

        // Arrange
        int a = 4;
        int b = 0;
        String expectedExceptionMessage = "/ by zero";

        // Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            //Act
            mathUtils.divide(a, b);
        }, "Division by zero should have thrown an Arithmetic Exception");

        // Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "Unexpected Exception message");
    }
}
