package com.junitdemo;

import java.util.Scanner;

public class EvenOrOdd {

    public static void main(String args[]) {


//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter a number");
//
//        int num = scanner.nextInt();
//
//        if (num % 2 == 0) {
//            System.out.println("Even");
//        } else {
//            System.out.println("Odd");
//        }

        int number = 20;


      //checking whether the number is even or odd
        if (number % 2 == 0)
            System.out.println(number + " is Even");
        else
            System.out.println(number + " is odd");

    }
}
