package com.example.demo.java8to11;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

	public static void main(String[] args) {
		// 기존 람다 식
		Function<Integer, String> intToString = (i) -> "number";
		
		// 
		//UnaryOperator<String> hi = (s) -> "hi" + s;
		UnaryOperator<String> hi = Greeting::hi;
		System.out.println(hi.apply("Hi"));
		
		Greeting greeting = new Greeting();
		UnaryOperator<String> hello = greeting::hello;
		System.out.println(hello.apply("Hello"));
		
		//
		Supplier<Greeting> newGreeting= Greeting::new;
		
		//
		Function<String, Greeting> keesunGreeting = new Greeting::new;
		
		Supplier<Greeting> breanNew = Greeting::new;
	}
}
