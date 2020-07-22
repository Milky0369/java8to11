package com.example.demo.java8to11;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class LambdaTest {

	public static void main(String[] args) {
		
		Supplier<Integer> get10 = () -> 10;
		Supplier<Integer> get11 = () -> {
			return 10;
		};
		
		//BiFunction<Integer, Integer, Integer> get12 = () -> 10;
		BinaryOperator<Integer> sum = (a, b) -> a + b;
		
		// 변수 캡쳐
		LambdaTest lam = new LambdaTest();
		lam.run();
	}
	
	private void run() {
		int base = 10; // effective final : 사실상 수정되지 않는 값
		
		// 익명클래스
		Consumer<Integer> integerConsumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println(base);
			}
		};
		
		// 로컬 클래스
		class LocalClass {
			void printBaseNumber() {
				System.out.println(base);
			}
		}
		
		// 람다
		IntConsumer printInt = (i) -> {
			System.out.println(i);
		};
		
		printInt.accept(10);
		
	}
	
	private void run2() {
		int base = 10;
		
		// 익명클래스
		Consumer<Integer> integerConsumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer base) {
				System.out.println(base); // 같은 이름의 변수를 정의할 경우 얘는 자기껄 참조한다.
			}
		};
		
		// 로컬 클래스
		class LocalClass {
			void printBaseNumber() {
				System.out.println(base);
			}
		}
		
		// 람다 -> 람다는 쉐도윙이 일어나지 않는다. 메서드와 scope이 같기 때문
		IntConsumer printInt = (i) -> { // base 변수명을 사용할 수 없음.
			System.out.println(i);
		};
		
		printInt.accept(10);
		
		// base++; // 이건 할 수 없음. base 변수가 묵시적 final인데 변경 된 시점부터 final이 아니기 때문에 람다에서 오류가 발생.
		
	}
	
}
