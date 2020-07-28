package com.example.demo.java8to11;

public interface Bar extends GreetingInterface {
	
	@Override
	default	void printNameUpperCase() {
		System.out.println("BAR");
	}
	
}
