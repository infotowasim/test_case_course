package com.junit5demo.oederofunitestclasses;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(3)
public class OrderServiceTest {

    @BeforeAll
    static void setUp(){
        System.out.println("Test method related to ORDER SERVICE");
    }

    @Test
    void testCreateOrder(){

    }
}
