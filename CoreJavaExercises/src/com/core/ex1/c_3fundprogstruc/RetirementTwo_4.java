package com.core.ex1.c_3fundprogstruc;

import java.util.Scanner;

public class RetirementTwo_4 {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		System.out.println("How much money do you ned to retire?");
		double goal = in.nextDouble();
		
		System.out.println("How much money will you contribute every year?");
		double payment = in.nextDouble();
		
		System.out.println("What is the interest rate in %?");
		double interestRate = in.nextDouble();
		
		double balance = 0;
		int year = 0;
		
		String input;
		
		do{
			balance += payment;
			double interest = balance * interestRate/100;
			balance+= interest;
			year++;
			
			System.out.printf("After year %d, your balance is %,.2f%n", year, balance);
		
			System.out.print("Ready to retire? (Y/N) ");
			input = in.next();
		}
		while (input.equals("N"));
	}	
}
