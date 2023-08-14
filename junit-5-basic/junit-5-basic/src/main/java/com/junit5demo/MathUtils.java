package com.junit5demo;

public class MathUtils {

    // Method Signature.
    public int add(int a, int b){
        return a + b ;
    }

    public int subtract(int a, int b){
        return a - b ;
    }

    public int multiply(int a, int b){
        return a*b;
    }

    public double computeCircleArea(int i){
        return Math.PI * i * i ;
    }

    public int divide(int a, int b){
        return a/b;
    }
}
