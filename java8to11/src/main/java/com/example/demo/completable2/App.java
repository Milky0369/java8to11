package com.example.demo.completable2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> hello = CompletableFuture.supplyAsync( () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });


        // hello와 world 두 가지를 연결하고 싶다.
        // thenCompose : 선행작업 이후 추가작업 가능
        CompletableFuture<String> future = hello.thenCompose(App::getWorld);
        System.out.println(future.get());

        // 둘이 연관 관계가 없을때 두가지 작업 모두 끝난걸 확인하고 싶다.
        CompletableFuture<String> world = CompletableFuture.supplyAsync( () -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> future1 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future.get());

        // 2개 이상일때 모든 서브 테스크를 합쳐서 실행하고 싶다.
        CompletableFuture<Void> future2 = CompletableFuture.allOf(hello, world)
                .thenAccept((result) -> {
                    System.out.println(result);
                });

        System.out.println(future2.get()); // null 이다.

        // 위의 결과를 제대로 받고 싶다면
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply( v -> {
                   return futures.stream()
                           .map(CompletableFuture::join) // join과 get은 기본적으로 같지만 join은 unCheckedException을 발생시킨다.
                           .collect(Collectors.toList());
                });

        results.get().forEach(System.out::println);


        // 아무거나 하나 빨리끝나는거 해라
        CompletableFuture<Void> future4 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future4.get();

        // 에러 처리
        boolean throwError = true;
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync( () -> {
           if(throwError) {
               throw new IllegalArgumentException();
           }

            System.out.println("Hello " + Thread.currentThread().getName());
           return "Hello";
        }).exceptionally( ex -> {
            System.out.println(ex);
            return "ERROR!";
        });

        // 좀더 일반적인 경우의 처리
        CompletableFuture<String> future6 = CompletableFuture.supplyAsync( () -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle( (result, ex) -> { // result : 정상적인 경우, ex : 에러처리리
            if(ex != null) {
                System.out.println(ex);
                return "ERROR!";
            }

            return result;
       });

    }

    public static CompletableFuture<String> getWorld(String message){
        return CompletableFuture.supplyAsync( () -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

}
