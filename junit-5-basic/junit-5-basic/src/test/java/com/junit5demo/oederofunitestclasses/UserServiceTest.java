package com.junit5demo.oederofunitestclasses;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(1)
public class UserServiceTest {

    @BeforeAll
    static void setUp(){
        System.out.println("Test method related to USER SERVICE");
    }

    @Test
    void testCreateUser(){

    }
}
