package com.example.demo.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {

    // Runnable과 달리 어떠한 결과를 return 할 수 있다.
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
          Thread.sleep(2000L);
          return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);

        System.out.println(helloFuture.isDone()); // 작업이 끝났는지 여부에 대한 플래그를 제공. 안끝 : false
        System.out.println("Started!");

        helloFuture.get(); // 블로킹 포인트
        helloFuture.cancel(true); // 현재 작업을 인터럽트하며 취소할 수 있다. -> cancel을 해도 작업은 끝난걸로 친다. isDone : true

        System.out.println(helloFuture.isDone()); // 작업 끝 : true
        System.out.println("End!");

        Callable<String> java = () -> {
            Thread.sleep(2000L);
            return "Java";
        };

        Callable<String> sewan = () -> {
            Thread.sleep(3000L);
            return "Sewan";
        };

        Callable<String> jjang = () -> {
            Thread.sleep(4000L);
            return "Jjang";
        };

        // 한꺼번에 보낸다?
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(java, sewan, jjang));

        for(Future<String> f : futures){
            System.out.println(f.get());
        }

        // 아마 java만 나올꺼다.
        // 이유는 executorService가 single Thread로 선언되어 있기 때문
        // Executors.newFixedThreadPool(2) 로 Thread를 2개로 하면 2개만 들어감. 충분히 선언해줘야 한다.
        // invokeAny를 사용하는 경우는 :
        // Server1, Server2, Server3 이 있다고 가정했을때,
        // 3개의 서버에서 같은 작업을 동일하게 수행할 경우 모든 작업이 종료되길 기다리지 않고 처리.
        String s = executorService.invokeAny(Arrays.asList(java, sewan, jjang));
        System.out.println(s);

        executorService.shutdown();

    }

}
