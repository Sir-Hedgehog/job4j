package ru.job4j.calculator;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 31.10.2019
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
        if (operation.equals("+")) {
            Adding adding = new Adding();
            System.out.println("The result: " + adding.add(number1, number2));
        } else if (operation.equals("-")) {
            Subtraction subtraction = new Subtraction();
            System.out.println("The result: " + subtraction.substrate(number1, number2));
        } else if (operation.equals("*")) {
            Multiplication multiplication = new Multiplication();
            System.out.println("The result: " + multiplication.multiple(number1, number2));
        } else if (operation.equals("/")) {
            Division division = new Division();
            System.out.println("The result: " + division.divide(number1, number2));
        } else {
            System.out.println("You entered the incorrect value of operation!");
        }
    }
}