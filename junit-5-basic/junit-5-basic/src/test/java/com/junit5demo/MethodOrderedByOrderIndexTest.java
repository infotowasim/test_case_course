package com.junit5demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderedByOrderIndexTest {


    @Order(1) // Order Index number
    @Test
    void testC(){
        System.out.println("Running method Test C");
    }

    @Order(2) // Order Index number
    @Test
    void testB(){
        System.out.println("Running method Test B");
    }

    @Order(3) // Order Index number
    @Test
    void testA(){
        System.out.println("Running method Test A");
    }

    @Order(4) // Order Index number
    @Test
    void testD(){
        System.out.println("Running method Test D");
    }
}
