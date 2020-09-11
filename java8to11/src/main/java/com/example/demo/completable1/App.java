package com.example.demo.completable1;

import java.util.concurrent.*;

public class App {

    // CompletableFuture : 조금 더 비동기 프로그래밍을 가능케 하는 인터페이스스
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 그동안 작성해 왔던 패턴
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit( () -> "hello" );

        // TODO 작업 진행

        future.get();
        // future 종료

        // 기본적인 방법
        CompletableFuture<String> future1 = new CompletableFuture<>();
        future1.complete("sewan");

        System.out.println(future.get());
        // future1 종료

        // static factory method를 이용
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("sewan");
        System.out.println(future2.get());
        // future2 종료

        // 리턴이 없는 경우
        CompletableFuture<Void> future3 = CompletableFuture.runAsync( () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future3.get();
        // future3 종료

        // 리턴타입이 있는 경우
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync( () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future4.get());
        // future4 종료

        // callback 제공하기
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync( () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply( (s) -> { // 위에께 종료 됐을때 결과값을 받아서 처리를 하는 동작
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future5.get());
        // future5 종료

        // callback을 하는데 그냥 수행만 하면 된다.
        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync( () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun( () -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(future6.get());
        // future6 종료

        //

    }

}