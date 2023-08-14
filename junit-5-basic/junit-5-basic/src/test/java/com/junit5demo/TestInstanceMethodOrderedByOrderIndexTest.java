package com.junit5demo;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)  // Default
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInstanceMethodOrderedByOrderIndexTest {

    StringBuilder stringBuilder = new StringBuilder("");

    @AfterEach
    void afterEach(){
        System.out.println("The state of instance object is: "+ stringBuilder);
    }

    @Order(1) // Order Index number
    @Test
    void testC(){
        System.out.println("Running method Test C");
        stringBuilder.append("1");
    }

    @Order(2) // Order Index number
    @Test
    void testB(){
        System.out.println("Running method Test B");
        stringBuilder.append("2");
    }

    @Order(3) // Order Index number
    @Test
    void testA(){
        System.out.println("Running method Test A");
        stringBuilder.append("3");
    }

    @Order(4) // Order Index number
    @Test
    void testD(){
        System.out.println("Running method Test D");
        stringBuilder.append("4");
    }
}
