package com.junit5demo;

import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS) // static not required with beforeTestClass() & afterAllTest() method if you did @TestInstance
public class QuickBeforeAfterTest {


    @BeforeAll
    public static void beforeTestClass(){  // if u not use @TestInstance then u have to put static with beforeTestClass() method.
        System.out.println("Before All ");
    }


    @BeforeEach
    public void setUp(){
        System.out.println("Before Test");
    }

    @Test
    public void test1(){
        System.out.println("test1 executed");
    }

    @Test
    public void test2(){
        System.out.println("test2 executed");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("After Test");
    }

    @AfterAll
    public static void afterAllTest(){  // if u not use @TestInstance then u have to put static with afterAllTest() method.
        System.out.println("After All Test");
    }
}
