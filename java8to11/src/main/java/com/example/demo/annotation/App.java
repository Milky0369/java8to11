package com.example.demo.annotation;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("간장")
public class App {

    // Annotation의 변화
    // 1. Annotation을 Type 선언부에도 적용할 수 있다.
    // 2. Annotation을 중복해서 사용할 수 있다.

    public static void main(String[] args) throws RuntimeException{

        // 해당 클래스로 바로 가져오는 방법
        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach( c -> {
            System.out.println(c.value());
        });

        // Container Type으로 가져오는 방법
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach( c -> {
            System.out.println(c.value());
        });

    }

    // T, R : TypeParameter이다.
//    static class FeelsLikeChicken<@Chicken T> { // 여기에 선언 가능하게 해주는게 Chicken의 @Target(ElementType.TYPE_PARAMETER)
//
//        public static <@Chicken C> void print(C c) { // <C> : TypeParameter, (C c) : Type. 그렇기 때문에 앞에 C에만 Chicken을 붙일 수 있다.
//
//        }
//
//    }

}
