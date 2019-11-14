package ru.job4j.calculator;

import java.util.Scanner;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 3.0
 * @since 14.11.2019
 */

public class InteractCalc {
    public static void main(String[] args) {
        int number1;
        int number2;
        int degree;
        String operation;
        String agreement;
        Scanner numbers = new Scanner(System.in);
        Scanner operations = new Scanner(System.in);
        Scanner agreements = new Scanner(System.in);
       
        System.out.println("Enter the first number: ");
        number1 = numbers.nextInt();
       
        System.out.println("Enter the operation: ");
        operation = operations.next();
       
        System.out.println("Enter the second number: ");
        number2 = numbers.nextInt();

        if (operation.equals("+")) {
            System.out.println("Do you want to enter a degree? [y/n]");
            agreement = agreements.next();
            if (agreement.toLowerCase().trim().equals("y")) {
                System.out.println("Enter the degree: ");
                degree = numbers.nextInt();
                CalculatorFacade compoundExpression = new CompoundExpression(number1, operation, number2, degree);
                compoundExpression.getResult();
            } else {
                CalculatorFacade calc = new EngineeringCalc(number1, operation, number2);
                calc.getResult();
            }
        }
    }
}
