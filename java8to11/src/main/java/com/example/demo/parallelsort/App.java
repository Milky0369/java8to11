package com.example.demo.parallelsort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {

        int size = 1500;
        int[] numbers = new int[size];

        Random random = new Random();

        // 기존 Java Sorting : single Thread 방식의 sorting
        IntStream.range(0, size).forEach( i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();

        Arrays.sort(numbers);

        System.out.println("serial sorting took " + (System.nanoTime() - start));

        // 신규 Java Sorting : 같은 알고리즘이지만 더 빠르다.
        IntStream.range(0, size).forEach( i -> numbers[i] = random.nextInt());

        start = System.nanoTime();

        Arrays.parallelSort(numbers);

        System.out.println("parallel sorting took " + (System.nanoTime() - start));


    }

}
