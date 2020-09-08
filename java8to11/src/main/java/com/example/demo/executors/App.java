package com.example.demo.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    // Executors : 쓰레드를 사용하고 관리하는 일을 Api에게 위임한다.
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        // Anonymous Class를 이용한 Runnable 구현
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName());
            }
        });

        // Lambda를 사용한 Runnable 구현
        executorService.submit(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
        });

        // Graceful Shutdown을 통해 작업이 완료된 후 죽인다.
        // 명예로운 죽음이다.
        executorService.shutdown();

        // 그냥 지금 죽어라
        executorService.shutdownNow();

        executorService.scheduleAtFixedRate(getRunnable("Hello "), 1, 2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }

}
