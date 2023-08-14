package com.junitdemo;

public class Calculator {

    //Sum
    public int doSum(int a, int b, int c){
        return a+b+c;
    }


    public int doSum1(int a, int b){
        return a+b;
    }

    //Multiple
    public int doMultiple(int a, int b){
        return a*b;
    }

    // Compare 2 numbers
    public boolean doCompareTwoNumber(int a, int b){
        return a==b;
    }


    public boolean isEven(int c){
      return c % 2 == 0;
    }


}
