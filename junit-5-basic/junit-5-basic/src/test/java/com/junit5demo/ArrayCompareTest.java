package com.junit5demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayCompareTest {

    // Array.sort

    @Test
    public void testArraySort_RandomArray(){
        int[] numbers = {12,1,3,5,14,6};
        int[] expected = {1,3,5,6,12,14};
        Arrays.sort(numbers);

        assertArrayEquals(expected,numbers);

    }

    // Testing exception normally

    @Test
    public void testArraySort_NullArray(){
        int[] numbers = null;

        try {
            Arrays.sort(numbers);
        }catch (NullPointerException e){
            // Success
        }
    }


    // Testing exception using Junit method assertThrows().
    @Test
    public void testArraySort_NullArray1(){
        int[] numbers = null;
        assertThrows(NullPointerException.class, ()->{
            Arrays.sort(numbers);
        });
        }


        // Array Sorting performance test
    @Test
//    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Timeout(500)
    public void testSort_Performance(){
        int array[] = {12,3,4};
        for (int i=0; i<10000;i++){
            array[0]=i;
            Arrays.sort(array);
        }
    }


}
