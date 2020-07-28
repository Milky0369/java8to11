package com.example.demo.java8to11;

public class DefaultGreeting implements GreetingInterface, Bar {

	String name;
	
	public DefaultGreeting(String name) {
		this.name = name;
	}
	
	@Override
	public void printName() {
		System.out.println(this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	// printNameUpperCase가 문제가 생길경우 재정의
	// GreetingInterface와 Bar 에 동시에 있을때 재정의를 꼭 해줘야 한다. 뭘 쓸지 모르니까.
	@Override
	public void printNameUpperCase() {
		System.out.println(getName().toUpperCase());
	}

}
