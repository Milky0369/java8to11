package com.example.demo.java8to11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Plus10Test {

	public static void main(String[] args) {
		
		// 기존 형식
		Plus10 plus10 = new Plus10();
		System.out.println(plus10.apply(1));
		
		
		// 람다 형식
		Function<Integer, Integer> plus11 = (i) -> i + 10;
		System.out.println(plus11.apply(1));
		
		//
		Function<Integer, Integer> multiply2 = (i) -> i * 2;
		System.out.println(multiply2.apply(1));
		
		// 함수 조합 메서드 기본제공
		// 입력값 우선 실행 및 결과 값을 앞의 입력값으로 사용
		plus11.compose(multiply2);
		
		// 함수 조합 메서드 2
		// compose와 반대 10을 우선 더하고 곱하기 2를 한다.
		plus11.andThen(multiply2);
		
		// 소비하는 것
		Consumer<Integer> printT = (i) -> System.out.println(i);
		printT.accept(10);
		
		// 가져오는것
		Supplier<Integer> get10  = () -> 10;
		System.out.println(get10.get());
		
		// 인자값을 받아서 true false 를 리턴
		Predicate<String> start = (s) -> s.startsWith("sewan");
		System.out.println(start.negate());
		
		Predicate<Integer> isOdd = (i) -> i%2 == 0; // 짝수 구하기
		Predicate<Integer> isEven = (i) -> i%2 == 1; // 홀수 구하기
		
		// 
		UnaryOperator<Integer> a = (i) -> i + 10;
		UnaryOperator<Integer> b = (i) -> i * 10;
		
		
	}
	
}
