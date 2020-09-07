package com.example.demo.concurrent;

public class App {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            // Thread1 : 1초 잠들어 있는 쓰레드
            try {
                // 요런 경우 다른 쓰레드가 먼저 일한다. 즉, 메인쓰레드가 먼저 종료된다.
                Thread.sleep(1000L);
            } catch(InterruptedException e) { // InterruptedException : 누군가가 이 쓰레드를 깨우면 발생하는 Exception
                e.printStackTrace();
            }

            System.out.println("Thread : " + Thread.currentThread().getName());

            // Thread2 : 3초 잠들어 있는 쓰레드
            try {
                Thread.sleep(3000L);
            } catch(InterruptedException e) {
                throw new IllegalStateException(e);
            }

            System.out.println("Thread : " + Thread.currentThread().getName());

            // Thread3 : 무한 루프 쓰레드
            // 인터럽트 : 다른 쓰레드를 깨우는 역활
            // 기대 : 반복하며 돌다가 interrupt 가 발생하며 종료.
            while(true) {
                System.out.println("Thread : " + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000L);
                } catch(InterruptedException e) {
                    System.out.println("exit!");
                    return ;
                }
            }

        });

        thread.start();
        System.out.println("Hello : " + Thread.currentThread().getName());

        // 쓰레드를 기다리게 한다.
        thread.join();
        System.out.println(thread + " is finished.");

        Thread.sleep(3000L);
        thread.interrupt();

        // 사용경험 : 단 두개의 쓰레드만 가지고도 이리저리 인터럽트 되며 힘든데 많은 쓰레드를 코드로 관리하기란 불가능에 가깝다.
        // 그래서 등장한게 Executors 이다.

    }

}
