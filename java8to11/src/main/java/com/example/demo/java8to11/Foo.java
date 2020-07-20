package com.example.demo.java8to11;

public class Foo {

	public static void main(String[] args) {
		
		int num = 10;
		
		RunSomething run = new RunSomething() {
			@Override
			public int doIt(int number) {
				return number + num;
			}
		};
		 
		System.out.println(run.doIt(1));
		System.out.println(run.doIt(1));
		System.out.println(run.doIt(1));

		System.out.println("================================================");
		
		RunSomething runLambda = number -> number + num;
		
		System.out.println(runLambda.doIt(1));
		System.out.println(runLambda.doIt(1));
		System.out.println(runLambda.doIt(1));
		
	}
	
}
