package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {
    // Container Annotation은 더 넓은 범위의 Retention을 지정해야 한다.
    // 그리고 자기자신이 가지고 있을 Annotation을 배열로 가지고 있어야 한다.

    Chicken[] value();
}
