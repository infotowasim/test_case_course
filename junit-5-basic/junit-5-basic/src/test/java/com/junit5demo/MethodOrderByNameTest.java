package com.junit5demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class MethodOrderByNameTest {


    @Test
    void testC(){
        System.out.println("Running method Test C");
    }

    @Test
    void testB(){
        System.out.println("Running method Test B");
    }

    @Test
    void testA(){
        System.out.println("Running method Test A");
    }

    @Test
    void testD(){
        System.out.println("Running method Test D");
    }
}
