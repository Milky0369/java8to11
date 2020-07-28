package com.example.demo.java8to11;

public class DefaultGreetingTest {

	public static void main(String[] args) {
		GreetingInterface gi = new DefaultGreeting("sewan");
		
		gi.printName();
		gi.printNameUpperCase();
		
		GreetingInterface.printAnyting();
	}
	
}
