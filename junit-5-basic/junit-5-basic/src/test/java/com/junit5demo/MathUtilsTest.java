package com.junit5demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {

    MathUtils mathUtils;


    @BeforeAll
    static void beforeAllInit(){

        System.out.println("This should run before all");
    }

    // Initializing before anything in this class run.
    @BeforeEach
    void init(){

        mathUtils = new MathUtils();
    }


    @AfterEach
    void cleanUp(){
        System.out.println("Cleaning up code...");
    }

    @Test
    @DisplayName("Testing add numbers")
    void testAdd() {

        //Actual Result
        int actualResult = mathUtils.add(5, 5);
        //Expected Result
        int expectedResult = 10;

        //Test Condition -> Tests Passed
        assertEquals(actualResult,expectedResult,"This method should add two numbers");
    }

    @Test
    void testComputeCircleArea() {

        double actualResult = mathUtils.computeCircleArea(10);
        double expectedResult = 314.1592653589793;
        assertEquals(actualResult,expectedResult,"Should return right circle area");
    }


    @Test
 //1   @EnabledOnOs(OS.LINUX)  // its like @Disabled (1)
    void testDivide() {
//        boolean isServerUp = true; // its like @Disabled (2)
//        boolean isServerUp = false;  // its like @Disabled (2)
//        assertTrue(isServerUp);  // its like @Disabled (2)

        // Tests Passes
        assertThrows(ArithmeticException.class, ()-> mathUtils.divide(1,0), "Divided by zero should return");

//      //Tests Failed
//        assertThrows(NullPointerException.class, ()-> mathUtils.divide(1,0), "Divided by zero should return");

    }


    @Test
    @Disabled
    @DisplayName("Ignoring Test method")
    void contextLoads() {
        fail("This method should not be run");
    }


    @Test
    @DisplayName("Multiply Method")
    void testMultiply(){
        // In one short I am passing 4 result using assertAll() method.
        assertAll(
                () -> assertEquals(mathUtils.multiply(3, 3),9),
                () -> assertEquals(mathUtils.multiply(3, -1),-3),
                () -> assertEquals(mathUtils.multiply(1, 0),0),
                () -> assertEquals(mathUtils.multiply(5, 1),5)
        );
    }

    @Test
    void testMultiplyy() {
        int actualResult = mathUtils.multiply(3, 3);
        int expectedResult = 9;
        assertEquals(actualResult,expectedResult);
    }

    @Test
    void testSubtract() {
        int actualResult = mathUtils.subtract(3, 3);
        int expectedResult = 0;
        assertEquals(actualResult,expectedResult);
    }


    @Nested
    class TestAdd{


        @Test
        @DisplayName("Testing add method for positive")
        void testAddPositive() {
            int actualResult = mathUtils.add(5, 5);
            int expectedResult = 10;
            assertEquals(actualResult,expectedResult,"This method should add two numbers");
        }

        @Test
        @DisplayName("Testing add method for negative")
        void testAddNegative() {
            int actualResult = mathUtils.add(-5, -5);
            int expectedResult = -10;
            assertEquals(actualResult,expectedResult,"This method should add two numbers");
        }

    }
}