package com.example.demo.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class App {

    // Optional : 비어 있을수도 있고 뭔가를 담고 있을 수 있는.
    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();

        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));


        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);

        // 프리미티브 타입은 권장하지 않음. 박싱 언박싱이 많이 발생해서 성능이 안좋아짐
        Optional.of(10);
        // 이거 사용하는게 좋음
        OptionalInt.of(10);


    }

}
