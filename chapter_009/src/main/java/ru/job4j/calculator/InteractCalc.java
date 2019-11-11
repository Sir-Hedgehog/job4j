package ru.job4j.calculator;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 2.0
 * @since 11.11.2019
 */

public class InteractCalc { 
    public static void main(String[] args) {     
        int number1;
        int number2;
        String operation;
        Scanner numbers = new Scanner(System.in);
        Scanner operations = new Scanner(System.in);
       
        System.out.println("Enter the first number: ");
        number1 = numbers.nextInt();
       
        System.out.println("Enter the operation: ");
        operation = operations.next();
       
        System.out.println("Enter the second number: ");
        number2 = numbers.nextInt();
        TrigonometricCalc calc = new TrigonometricCalc();
        calc.getResult(number1, operation, number2);
    }
}
