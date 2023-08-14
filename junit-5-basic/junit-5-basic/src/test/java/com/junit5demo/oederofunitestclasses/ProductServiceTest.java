package com.junit5demo.oederofunitestclasses;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
public class ProductServiceTest {

    @BeforeAll
    static void setUp(){
        System.out.println("Test method related to PRODUCT SERVICE");
    }

    @Test
    void testCreateProduct(){

    }
}
